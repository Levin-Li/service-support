package com.levin.commons.rbac;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrSplitter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.plugin.ResLoader;
import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.ExpressionUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.commons.rbac.RbacRoleInfo.ROLE_PREFIX;
import static org.springframework.util.StringUtils.*;


/**
 * 逻辑
 */
@Slf4j
@Schema(title = "权限验证服务", description = "权限验证的关键实现")
public class AbstractRbacAuthorizeService implements RbacAuthorizeService {

    protected final BiConsumer<String, String> emptyConsumer = (v1, v2) -> {
    };

    @Autowired(required = false)
    ApplicationContext context;

    @Autowired(required = false)
    PluginManager pluginManager;

    @Autowired(required = false)
    RbacBaseService defaultRbacBaseService;

    final InheritableThreadLocal<RbacBaseService> userLoadServiceHolder = new InheritableThreadLocal<>();

    final ContextHolder<String, ResConditionAction> actionContextHolder = ContextHolder.buildContext(true);

    protected ContextHolder<String, ResConditionAction> getActionContext() {

        synchronized (actionContextHolder) {
            if (actionContextHolder.isEmpty()) {
                for (Plugin plugin : getPluginManager().getInstalledPlugins()) {
                    //资源加载器
                    ResLoader resLoader = plugin.getResLoader();
                    if (resLoader == null) {
                        continue;
                    }
                    //第二层循环 资源类型
                    for (Identifiable resType : resLoader.getResTypes()) {
                        //全部加入
                        Collection<Res> resItems = resLoader.getResItems(resType.getId(), 0);
                        //第三层循环 资源列表
                        for (Res res : resItems) {
                            String prefix = String.join(getPermissionDelimiter(), res.getDomain().toString(), res.getType().toString(), res.getId().toString());
                            //第四层循环 资源操作
                            res.getActionList()
                                    .parallelStream()
                                    .filter(Objects::nonNull)
                                    .forEach(action -> {
                                        actionContextHolder.put(trimAllWhitespace(prefix + getPermissionDelimiter() + action.action()), action);
                                    });
                        }
                    }
                }
            }
        }

        return actionContextHolder;
    }

    /**
     * @param requirePermission
     * @return
     */
    protected ResConditionAction getAction(String requirePermission) {
        return getActionContext().get(requirePermission);
    }


    /**
     * 获取匹配清单
     *
     * @param requirePermissionPattern
     * @return
     */
    protected Map<String, ResConditionAction> getMatchActions(String requirePermissionPattern) {

        Map<String, ResConditionAction> actionMap = new LinkedHashMap<>();

        getActionContext().getAll(true).forEach((k, v) -> {
            if (textPatternMatch(requirePermissionPattern, k)) {
                actionMap.put(k, v);
            }
        });

        return actionMap;
    }

    /**
     * 设置用户加载服务
     *
     * @param rbacBaseService
     * @return
     */
    public RbacAuthorizeService setRbacBaseService(RbacBaseService rbacBaseService) {
        this.userLoadServiceHolder.set(rbacBaseService);
        return this;
    }


    /**
     * 获取用户加载服务
     *
     * @return
     */
    @Override
    public RbacBaseService getRbacBaseLoadService() {

        RbacBaseService rbacBaseService = this.userLoadServiceHolder.get();

        if (rbacBaseService == null) {
            rbacBaseService = this.defaultRbacBaseService;
        }

        Assert.notNull(rbacBaseService, "用户加载服务未配置");

        return rbacBaseService;
    }


    protected ApplicationContext getContext() {
        return context;
    }

    protected PluginManager getPluginManager() {
        Assert.notNull(pluginManager, "插件管理服务未配置");
        return pluginManager;
    }

    /**
     * 授权验证，是否可以访问指定资源
     *
     * @param principal
     * @param resPrefix 资源表达式
     * @param action
     * @return
     */
    @Override
    public boolean isAuthorized(Serializable principal, String resPrefix, ResConditionAction action) {

        RbacBaseService rbacBaseService = getRbacBaseLoadService();

        RbacUserInfo user = rbacBaseService.loadUser(principal);
        Assert.notNull(user, "用户({})不存在", principal);
        principal = user;

        if (user.isTopSuperAdmin()) {
            return true;
        }
        //
        return isAuthorized(
                user,
                resPrefix,
                action,
                getRbacBaseLoadService().loadUserRoleCodeList(user),
                getRbacBaseLoadService().loadUserPermissionExprList(user),
                getAuthorizeContext()
        );
    }

    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 关键方法
     *
     * @param ownerRoleList       已经拥有的角色列表
     * @param ownerPermissionList 已经拥有的权限列表
     * @param requirePermission   请求的权限
     * @param matchErrorConsumer  匹配错误回调 参数1为请求的权限，参数2为错误原因
     * @return 是否可以访问指定资源
     */
    @Override
    public boolean isAuthorized(Serializable principal, Collection<String> ownerRoleList, Collection<String> ownerPermissionList, String requirePermission, BiConsumer<String, String> matchErrorConsumer) {

        //Assert.hasText(requirePermission, "检查的权限表达式为空");

        RbacBaseService rbacBaseService = getRbacBaseLoadService();

        RbacUserInfo user = rbacBaseService.loadUser(principal);
        Assert.notNull(user, "用户({})不存在", principal);

        if (user.isTopSuperAdmin()) {
            return true;
        }

        //
        principal = user;

        /// ////////////////////////////////

        //去除所有的百空格
        requirePermission = trimWhitespace(requirePermission);

        //如果不需要权限
        if (!hasText(requirePermission)) {
            return true;
        }

        //角色简单匹配,权限列表简单匹配
        if (simpleMatch(requirePermission, ownerRoleList)
                || simpleMatch(requirePermission, ownerPermissionList)) {
            return true;
        }

        //如果是角色，不是权限，按角色处理
        if (isRole(requirePermission)) {

            boolean found = !CollUtil.isEmpty(ownerRoleList) && ownerRoleList.contains(requirePermission);

            if (!found) {
                Optional.ofNullable(matchErrorConsumer).orElse(emptyConsumer).accept(requirePermission, requirePermission + " role not authorized");
            }

            //@todo 拆解角色需要的权限，然后匹配权限
            //问题无法获取 角色对应的权限列表，只能匹配失败
            return found;
        }

        Map<String, ResConditionAction> actionMap = new LinkedHashMap<>(7);

        //是否是通配权限表达式
        if (isPattern(requirePermission)) {
            //如果包含通配权限，要拆解出权限清单
            actionMap = getMatchActions(requirePermission);
        } else {
            ResConditionAction action = getAction(requirePermission);
            if (action != null) {
                actionMap.put(requirePermission, action);
            }
        }

        if (actionMap.isEmpty()) {
            Optional.ofNullable(matchErrorConsumer).orElse(emptyConsumer).accept(requirePermission, "操作不存在");
            return false;
        }

        final Map<String, Object> authorizeContext = getAuthorizeContext();

        final AtomicBoolean result = new AtomicBoolean(true);

        for (Map.Entry<String, ResConditionAction> entry : actionMap.entrySet()) {

            String rp = entry.getKey();
            ResConditionAction action = entry.getValue();

            if (action.ignored() || action.onlyRequireAuthenticated()) {
                continue;
            }

            if (!isAuthorized(user, rp.substring(0, rp.lastIndexOf(getPermissionDelimiter())), action, ownerRoleList, ownerPermissionList, authorizeContext)) {
                result.set(false);
                Optional.ofNullable(matchErrorConsumer).orElse(emptyConsumer).accept(requirePermission, rp);
                break;
            }
        }

        return result.get();
    }


    /**
     * 在假设用户已经登录的情况下，授权验证，是否可以访问指定资源
     *
     * <p>
     * 关键方法
     *
     * @param resPrefix           资源前缀
     * @param action              动作
     * @param ownerRoleList       已经拥有的角色列表
     * @param ownerPermissionList 已经拥有的权限列表
     * @return 是否可以访问指定资源
     */
    @SafeVarargs
    protected final boolean isAuthorized(Serializable principal, String resPrefix, ResConditionAction action, Collection<String> ownerRoleList, Collection<String> ownerPermissionList, Map<String, Object>... exprContexts) {

        if (action.ignored()
                || action.onlyRequireAuthenticated()) {
            return true;
        }

        RbacBaseService rbacBaseService = getRbacBaseLoadService();

        RbacUserInfo user = rbacBaseService.loadUser(principal);
        Assert.notNull(user, "用户({})不存在", principal);

        if (user.isTopSuperAdmin()) {
            return true;
        }

        //获取用户数据访问级别
        //如果数据访问级别小于资源访问级别，则不允许访问
        //@todo 调用这个方法可能导致大量的重复数据操作影响性能
        if (!rbacBaseService.canAccessConfidentialDataByUser(user, action.confidentialLevel())) {
            return false;
        }

        if (user.isSuperAdmin()) {
            return true;
        }

        final Collection<String> requireAnyUserTypes = toList(action.anyUserTypes()).stream().filter(StrUtil::isNotBlank).collect(Collectors.toList());

        // 判断用户类型
        final Supplier<Boolean> hasAnyUserTypesFun = requireAnyUserTypes.isEmpty() ? null : () -> requireAnyUserTypes.stream().anyMatch(
                uTypePattern -> textPatternMatch(uTypePattern, user.getType())
        );

        //判断用户类型
        if (hasAnyUserTypesFun != null
                && !hasAnyUserTypesFun.get()) {
            return false;
        }

        ///////////////////////////////////////////////////////////////////////////

        //生成表达式
        final String requirePermission = String.join(getPermissionDelimiter(), null2Empty(resPrefix), null2Empty(action.action()));

        //1、权限检查闭包
        final Supplier<Boolean> hasPermissionFun = isEmptyPermission(requirePermission) ? null : () -> simpleMatch(requirePermission, ownerPermissionList);

        //过滤出符合角色的字符串
        final List<String> requireAnyRoles = toList(action.anyRoles()).stream().filter(StrUtil::isNotBlank).filter(this::isRole).collect(Collectors.toList());

        //2、角色检查闭包，允许角色匹配表达式
        final Supplier<Boolean> hasAnyRolesFun = requireAnyRoles.isEmpty() ? null : () -> requireAnyRoles.stream().anyMatch(
                rolePattern -> ownerRoleList.stream().filter(this::isRole).anyMatch(ownerRole -> textPatternMatch(rolePattern, ownerRole))
        );

        //表达式支持
        //3、表达式闭包
        final Supplier<Boolean> expressFun = hasText(action.verifyExpression()) ? () -> (Boolean) ExpressionUtils.evalSpEL(null, action.verifyExpression(),
                (ctx) -> {

                    ctx.setBeanResolver(new BeanFactoryResolver(getContext()));

                    // ctx.setVariable("stpLogic", StpUtil.stpLogic);
                    //设置环境变量
                    if (exprContexts != null) {
                        Stream.of(exprContexts).filter(Objects::nonNull).forEach(ctx::setVariables);
                    }
                    ctx.setVariable("user", user);
                    ctx.setVariable("action", action);
                    ctx.setVariable("resPrefix", resPrefix);
                    ctx.setVariable("userType", user.getType());
                    ctx.setVariable("ownerRoleList", ownerRoleList);
                    ctx.setVariable("ownerPermissionList", ownerPermissionList);
                }) : null;

        //合并闭包
        final Stream<Supplier<Boolean>> supplierStream = Stream.of(hasAnyRolesFun, hasPermissionFun, expressFun).filter(Objects::nonNull);

        //执行判断
        return action.isAndMode() ? supplierStream.allMatch(Supplier::get) : supplierStream.anyMatch(Supplier::get);
    }

    /// ///////////////////////////////

    protected String null2Empty(String str) {
        return str == null ? "" : str;
    }

    protected boolean isEmptyPermission(String requirePermission) {
        return !StringUtils.hasText(null2Empty(requirePermission).replace(getPermissionDelimiter(), ""));
    }


    static List<String> toList(String... strArray) {
        return ArrayUtil.isEmpty(strArray) ? Collections.emptyList() : Arrays.asList(strArray);
    }

    /**
     * 是否是权限
     *
     * @param requirePermission
     * @return
     */
    protected boolean isPermission(String requirePermission) {
        //@todo 尽量优化性能
        return hasText(requirePermission)
                //权限不区分内容
//                && ( requirePermission.contains(getPermissionDelimiter())
//                        //*号也可以是权限
//                        || "*".equals(trimWhitespace(requirePermission)))
                ;
    }

    /**
     * 是否是匹配模板
     *
     * @return
     */
    protected boolean isPattern(String permission) {

//        return getAntPathMatcher().isPattern(permission);

        //@todo 尽量优化性能
        return StringUtils.hasText(permission) && (permission.indexOf('*') >= 0 || permission.indexOf('|') >= 0);
    }

    /**
     * 是否是角色
     *
     * @param requirePermission
     * @return
     */
    protected boolean isRole(String requirePermission) {
        return hasText(requirePermission)
                && trimWhitespace(requirePermission).startsWith(ROLE_PREFIX);
    }

    /**
     * 文本*号匹配
     * <p>
     * 权限表达式：模块包名:业务模型名称:业务数据标识:操作名称
     * 权限表达式用3个冒号分割出4个部分，4个部分都是可以配置多个选择，多个选择之间用|分隔，如："com.levin.oak.base:平台数据-*|业务数据-*::查看*|查询列表*"
     * 本方法为关键逻辑方法，匹配权限表达式的单个部分。
     * <p>
     * 注意不支持问号
     * <p>
     * 支持按竖线分隔多个或的条件
     * <p>
     * 比如 修改*|删除*|查询*
     * <p>
     * Match a String against the given pattern, supporting the following simple pattern styles: "xxx*", "*xxx", "*xxx*" and "xxx*yyy" matches (with an arbitrary number of pattern parts), as well as direct equality.
     *
     * @param pattern
     * @param str
     * @return
     * @see PatternMatchUtils#simpleMatch
     */
    protected boolean textPatternMatch(@Nullable String pattern, @Nullable String str) {

        return StringUtils.hasText(pattern)
                && StringUtils.hasText(str)
                && StrUtil.split(pattern, '|').stream().filter(StringUtils::hasText).anyMatch(p -> PatternMatchUtils.simpleMatch(p, str));
    }

    /**
     * <p>
     * 权限表达式的匹配
     * 核心方法
     * <p>
     * 重要方法，能提升性能
     * <p>
     * 可以支持无限层级
     *
     * @param requirePermission 需要的权限， eg. com.oak:系统数据-租户:id2:查询
     * @param ownerPermission   拥有的权限，  eg. **:查询
     * @return
     */
    protected boolean simpleMatch(String requirePermission, String ownerPermission) {

        //去除所有空字符
        requirePermission = trimWhitespace(requirePermission);

        //如果需要去权限为空
        if (!StringUtils.hasText(requirePermission)) {
            return true;
        }

        //去除所有空字符
        ownerPermission = trimWhitespace(ownerPermission);
        if (!StringUtils.hasText(ownerPermission)) {
            return false;
        }

        //1、如果相等，直接返回
        if (ownerPermission.equals(requirePermission)) {
            return true;
        }

        //是否是角色
        boolean opIsRole = isRole(ownerPermission);
        boolean rpIsRole = isRole(requirePermission);

        if (opIsRole || rpIsRole) {
            //2、只要是角色，就只能是角色之间比较
            return opIsRole && rpIsRole && textPatternMatch(ownerPermission, requirePermission);

//            return opIsRole && rpIsRole && getAntPathMatcher().match(ownerPermission, requirePermission);
        }

        //3、如果拥有权限不是模板
        if (!isPattern(ownerPermission)) {
            return false;
        }

//        return getAntPathMatcher().match(ownerPermission, requirePermission);

//        //拥有的权限 A*:B*:C*:D*
        final String[] ownerList = StrSplitter.splitToArray(ownerPermission, getPermissionDelimiter(), 0, true, false);// ownerPermission.split(getPermissionDelimiter());

        final AtomicInteger idx = new AtomicInteger(-1);

        //切割出单个比较项目
        return StrUtil.split(requirePermission, getPermissionDelimiter()).stream()
                .allMatch(rp -> textPatternMatch(
                                //超过数组长度以后，总是取最后一个
                                (ownerList[idx.updateAndGet(oldValue -> oldValue < ownerList.length - 1 ? oldValue + 1 : oldValue)])
                                , trimWhitespace(rp)
                        )
                );
    }


    /**
     * 多个匹配
     *
     * @param requirePermission
     * @param ownerPermissions
     * @return
     */
    protected boolean simpleMatch(final String requirePermission, Collection<String> ownerPermissions) {

        if (!StringUtils.hasText(requirePermission)) {
            return true;
        }

        return !CollUtil.isEmpty(ownerPermissions) && ownerPermissions.stream().anyMatch(op -> simpleMatch(requirePermission, op));
    }

    /**
     * 多个匹配
     *
     * @param requirePermission
     * @param ownerPermissions
     * @return
     */
    protected boolean simpleMatch(final String requirePermission, String... ownerPermissions) {

        if (!StringUtils.hasText(requirePermission)) {
            return true;
        }

        return !ArrayUtil.isEmpty(ownerPermissions) && Stream.of(ownerPermissions).anyMatch(op -> simpleMatch(requirePermission, op));
    }

}
