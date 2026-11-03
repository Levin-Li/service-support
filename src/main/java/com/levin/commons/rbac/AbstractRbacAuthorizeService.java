package com.levin.commons.rbac;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrSplitter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.plugin.ResLoader;
import com.levin.commons.service.SingleValueContext;
import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.ExpressionUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;
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

    @Setter
    @Autowired(required = false)
    RbacBaseService defaultRbacBaseService;


    final ContextHolder<String, ResConditionAction> actionContextHolder = ContextHolder.buildContext(true);

    final Map<String, Expression> verifyExpressionCache = new ConcurrentReferenceHashMap<>();

    final SpelExpressionParser verifyExpressionParser = new SpelExpressionParser();

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


    protected SingleValueContext<RbacBaseService> getRbacBaseServiceContext() {
        return null;
    }

    /**
     * 获取用户加载服务
     *
     * @return
     */
    @Override
    public RbacBaseService getRbacBaseLoadService() {

        RbacBaseService strictView = RbacStrictAssignmentContext.VIEW.get();
        if (strictView != null) return strictView;
        RbacBaseService rbacBaseService = Optional.ofNullable(getRbacBaseServiceContext()).map(SingleValueContext::get).orElse(null);

        if (rbacBaseService == null) {
            rbacBaseService = this.defaultRbacBaseService;
        }

        Assert.notNull(rbacBaseService, "用户加载服务未配置");

        return rbacBaseService;
    }


    /**
     * Trusted synchronous transaction only; actor and complete directories must be stable through commit.
     * The mandatory business callback must reject unless each composite grant and its tenant, org,
     * domain, field-group and allowed-value scopes are covered by one and the same effective actor role.
     * Unioning scope or residual field permissions from other roles is forbidden. The callback must use
     * the supplied view and stable authoritative action/attribute catalogs; an empty callback is unsafe.
     */
    public final void checkRoleAssignmentWithStrictView(RbacStrictAssignmentView view,
                                                        Collection<? extends RbacRoleInfo> finalRoles) {
        Assert.notNull(view, "事务权限视图不能为空");
        view.verifyFactsUnchanged();
        Assert.isTrue(Arrays.asList(getClass().getInterfaces()).contains(RbacStrictAssignmentParticipant.class),
                "授权实现必须显式声明支持严格快照，不能继承未知实现的信任");
        try {
            Assert.isTrue(getClass().getMethod("getRbacBaseLoadService").getDeclaringClass() == AbstractRbacAuthorizeService.class,
                    "授权实现覆盖了数据源，不能绑定严格快照");
        } catch (NoSuchMethodException e) { throw new IllegalArgumentException("授权数据源不可验证", e); }
        Assert.isTrue(RbacStrictAssignmentContext.VIEW.get() == null, "不允许嵌套严格事务授权");
        // 旧数据范围扩展无法安全转发给视图：存在覆盖即拒绝，不能静默绕过。
        RbacBaseService originalBase = getRbacBaseLoadService();
        requireDefaultBaseAuthorization(originalBase);
        try {
            Assert.isTrue(originalBase.getClass().getMethod("checkRoleDataScopeAssignment", RbacUserInfo.class,
                            RbacUserInfo.class, Collection.class).getDeclaringClass() == RbacBaseService.class,
                    "原数据范围服务含自定义覆盖，严格视图不支持");
            Assert.isTrue(originalBase.getClass().getMethod("checkRoleDataScopeAssignmentWithSnapshot", RbacUserInfo.class,
                            RbacUserInfo.class, Collection.class, RbacRoleAssignmentSnapshot.class).getDeclaringClass() == RbacBaseService.class,
                    "原快照范围服务含自定义覆盖，严格视图不支持");
        } catch (NoSuchMethodException e) { throw new IllegalArgumentException("原数据范围服务不可验证", e); }
        // 脚本检查早于任何可执行的旧授权扩展，且旧 bean 的 hooks 仅在视图绑定后运行。
        view.rejectDynamicRules(finalRoles);
        RbacStrictAssignmentContext.VIEW.set(view);
        try (RbacStrictAssignmentParticipant.StrictCatalogBinding catalog = Objects.requireNonNull(
                ((RbacStrictAssignmentParticipant) this).openStrictAuthorizationCatalog(view), "严格权限目录绑定不能为空")) {
            view.verifyFactsUnchanged();
            DomainAccess.isolated(() -> {
                Set<String> requested = new LinkedHashSet<>();
                if (finalRoles != null) for (RbacRoleInfo role : finalRoles) if (role != null) requested.add(role.getCode());
                List<RbacRoleInfo> resolved = RoleDefinitionResolver.select(view.target().getTenantId(),
                        new ArrayList<RbacRoleInfo>(view.targetSnapshot().roles()), requested);
                for (RbacRoleInfo role : resolved) {
                    if (role.getPermissionList() != null) for (String permission : role.getPermissionList()) {
                        if (isPattern(permission) || permission.indexOf('?') >= 0) {
                            Assert.isTrue(view.<RbacRoleInfo>loadUserOwnerRoleList(view.actor()).stream()
                                            .anyMatch(owner -> owner.getPermissionList() != null && owner.getPermissionList().stream().anyMatch(p -> strictPermissionCovers(p, permission))),
                                    "通配权限必须由签发人的同一有效角色覆盖");
                        } else requireStrictAction(permission);
                    }
                }
                view.verifyFactsUnchanged();
                Assert.isTrue(view.canAdminUser(view.actor(), view.target()), "签发人无权管理目标用户");
                checkRoleAssignmentWithSnapshot(view.actor(), view.target(), finalRoles, view.targetSnapshot());
                view.verifyFactsUnchanged();
                ((RbacStrictAssignmentParticipant) this).checkStrictPermissionCoverage(view, List.copyOf(resolved));
                view.verifyFactsUnchanged();
                return null;
            });
        } finally {
            try { view.verifyFactsUnchanged(); }
            finally { RbacStrictAssignmentContext.VIEW.remove(); }
        }
    }

    /** Strict actor-only issuance; target data scope is checked later on the real registered user. */
    public final void checkInvitationIssuerWithStrictView(RbacStrictIssuerView view,
                                                           String creationPermission,
                                                           Collection<String> targetRoleCodes,
                                                           Serializable targetOrgId) {
        checkInvitationIssuerInternal(view, creationPermission, targetRoleCodes, targetOrgId, false, null);
    }

    /** For business types that never grant a backend role; caller must enforce that invariant. */
    public final void checkInvitationIssuerWithoutRoleWithStrictView(RbacStrictIssuerView view,
                                                                      String creationPermission,
                                                                      Serializable targetOrgId) {
        checkInvitationIssuerInternal(view, creationPermission, Collections.emptyList(), targetOrgId, true, null);
    }

    /** Additional veto runs only inside the isolated strict view and must be explicitly recognized by the participant. */
    public final void checkInvitationIssuerWithStrictView(RbacStrictIssuerView view, String creationPermission,
                                                          Collection<String> targetRoleCodes, Serializable targetOrgId,
                                                          RbacStrictIssuerVeto veto) {
        Assert.notNull(veto, "附加签发校验不能为空");
        checkInvitationIssuerInternal(view, creationPermission, targetRoleCodes, targetOrgId, false, veto);
    }

    public final void checkInvitationIssuerWithoutRoleWithStrictView(RbacStrictIssuerView view, String creationPermission,
                                                                     Serializable targetOrgId, RbacStrictIssuerVeto veto) {
        Assert.notNull(veto, "附加签发校验不能为空");
        checkInvitationIssuerInternal(view, creationPermission, Collections.emptyList(), targetOrgId, true, veto);
    }

    private void checkInvitationIssuerInternal(RbacStrictIssuerView view, String creationPermission,
                                               Collection<String> targetRoleCodes, Serializable targetOrgId,
                                               boolean explicitlyWithoutRole, RbacStrictIssuerVeto veto) {
        Assert.notNull(view, "签发权限视图不能为空");
        Assert.notBlank(creationPermission, "创建操作权限不能为空");
        Assert.notNull(targetRoleCodes, "目标角色编码不能为空");
        Assert.isTrue(explicitlyWithoutRole || !targetRoleCodes.isEmpty(), "目标角色不能为空");
        Assert.isTrue(Arrays.asList(getClass().getInterfaces()).contains(RbacStrictAssignmentParticipant.class), "授权实现未显式声明严格快照");
        Assert.isTrue(RbacStrictAssignmentContext.VIEW.get() == null, "不允许嵌套严格事务授权");
        try {
            Assert.isTrue(getClass().getMethod("getRbacBaseLoadService").getDeclaringClass() == AbstractRbacAuthorizeService.class,
                    "授权实现覆盖数据源，不能绑定严格快照");
        } catch (NoSuchMethodException e) { throw new IllegalArgumentException("授权数据源不可验证", e); }
        requireDefaultBaseAuthorization(getRbacBaseLoadService());
        view.verifyFactsUnchanged();
        Set<String> codes = new LinkedHashSet<>(targetRoleCodes);
        Assert.isTrue(!codes.contains(null) && codes.stream().noneMatch(String::isBlank), "目标角色编码无效");
        List<RbacRoleInfo> roles = RoleDefinitionResolver.select(view.targetTenant().getId(), view.targetRoles(), codes);
        Assert.isTrue(roles.stream().map(RbacRoleInfo::getCode).collect(java.util.stream.Collectors.toSet()).containsAll(codes), "目标角色缺失或无效");
        view.rejectDynamicRules(roles);
        RbacStrictAssignmentContext.VIEW.set(view);
        try (RbacStrictAssignmentParticipant.StrictCatalogBinding catalog = Objects.requireNonNull(
                ((RbacStrictAssignmentParticipant) this).openStrictIssuerCatalog(view), "严格权限目录绑定不能为空")) {
            view.verifyFactsUnchanged();
            DomainAccess.isolated(() -> {
                Assert.isTrue(view.canAccessTenant(view.actor(), view.targetTenant().getId()), "签发人无目标租户权限");
                if (targetOrgId != null) {
                    RbacOrgInfo org = view.loadOrg(targetOrgId);
                    Assert.isTrue(org.selfAudit() && Objects.equals(org.getId(), targetOrgId)
                                    && Objects.equals(org.getTenantId(), view.targetTenant().getId()), "目标组织不可用或不属于目标租户");
                    Assert.isTrue(view.canAccessOrg(view.actor(), view.targetTenant().getId(), targetOrgId), "签发人无目标组织权限");
                }
                requireStrictAction(creationPermission);
                Assert.isTrue(isAuthorized(view.actor(), true, List.of(creationPermission), null), "签发人无创建操作权限");
                for (RbacRoleInfo role : roles) {
                    if (role.getPermissionList() != null) for (String permission : role.getPermissionList()) {
                        if (isPattern(permission) || permission.indexOf('?') >= 0) {
                            Assert.isTrue(view.<RbacRoleInfo>loadUserOwnerRoleList(view.actor()).stream()
                                    .anyMatch(owner -> owner.getPermissionList() != null && owner.getPermissionList().stream().anyMatch(p -> strictPermissionCovers(p, permission))),
                                    "目标通配权限不被签发人单个角色覆盖");
                        } else requireStrictAction(permission);
                    }
                    Assert.isTrue(isRoleAuthorized(view.actor(), role, null), "签发人无权授予目标角色");
                }
                view.verifyFactsUnchanged();
                RbacStrictAssignmentParticipant participant = (RbacStrictAssignmentParticipant) this;
                participant.checkStrictIssuerPermissionCoverage(view, List.copyOf(roles));
                view.verifyFactsUnchanged();
                if (veto != null) {
                    Assert.isTrue(participant.supportsStrictIssuerVeto(veto), "未知的签发附加校验提供方");
                    view.verifyFactsUnchanged();
                    veto.check(view);
                    view.verifyFactsUnchanged();
                }
                return null;
            });
        } finally {
            try { view.verifyFactsUnchanged(); } finally { RbacStrictAssignmentContext.VIEW.remove(); }
        }
    }

    /** Unknown data/identity authorisation overrides cannot be silently bypassed by a view. */
    private static void requireDefaultBaseAuthorization(RbacBaseService base) {
        if (Arrays.asList(base.getClass().getInterfaces()).contains(RbacStrictBaseParticipant.class)) return;
        try {
            for (java.lang.reflect.Method m : List.of(
                    base.getClass().getMethod("getUserDataScope", Serializable.class),
                    base.getClass().getMethod("loadUserRoleCodeList", Serializable.class),
                    base.getClass().getMethod("loadUserPermissionExprList", Serializable.class),
                    base.getClass().getMethod("loadUserOwnerRoleList", Serializable.class, boolean.class),
                    base.getClass().getMethod("getUserConfidentialDataAccessLevel", Serializable.class),
                    base.getClass().getMethod("canAccessConfidentialDataByUser", Serializable.class, Integer[].class),
                    base.getClass().getMethod("canAccessTenant", Serializable.class, Serializable.class),
                    base.getClass().getMethod("canAdminUser", Serializable.class, Serializable.class),
                    base.getClass().getMethod("canAccessDomainObject", Serializable.class, com.levin.commons.dao.domain.DomainObject.class),
                    base.getClass().getMethod("filterByDomainAccess", Serializable.class, Collection.class))) {
                Assert.isTrue(m.getDeclaringClass() == RbacBaseService.class || m.getDeclaringClass() == RbacBaseUserService.class,
                        "原授权数据服务覆盖了严格视图不支持的检查: {}", m.getName());
            }
        } catch (NoSuchMethodException e) { throw new IllegalArgumentException("授权数据服务不可验证", e); }
    }

    private static boolean strictPermissionCovers(String owned, String requested) {
        if (owned == null || requested == null) return false;
        if (owned.equals(requested)) return true;
        if (owned.startsWith("@") || requested.startsWith("@")) return false;
        String[] owners = owned.split(":", -1), targets = requested.split(":", -1);
        if (owners.length != 4 || targets.length != 4) return false;
        for (int i = 0; i < 4; i++) {
            if (!owners[i].equals("*") && !owners[i].equals(targets[i])) return false;
        }
        return true;
    }

    private void requireStrictAction(String permission) {
        Assert.isTrue(StringUtils.hasText(permission) && !isPattern(permission) && permission.indexOf('?') < 0,
                "严格角色分配不支持空值或通配权限");
        ResConditionAction action = getAction(permission);
        Assert.isTrue(action != null && !action.ignored() && !action.onlyRequireAuthenticated()
                        && action.anyRoles().length == 0 && action.anyUserTypes().length == 0
                        && !StringUtils.hasText(action.verifyExpression())
                        && action.confidentialLevel() == ConfidentialLevel.PLATFORM_PUBLIC_CODE,
                "严格角色分配不支持未知或带条件的动作");
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

        RbacUserInfo user = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
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

        RbacUserInfo user = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
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

        // 动态目录必须由可信实现固定；未知动作、条件与未来通配范围无法证明包含。
        if (RbacStrictAssignmentContext.VIEW.get() != null) {
            if (isPattern(requirePermission) || requirePermission.indexOf('?') >= 0) {
                final String strictRequired = requirePermission;
                Assert.isTrue((ownerRoleList != null && ownerRoleList.stream().anyMatch(p -> strictPermissionCovers(p, strictRequired)))
                        || (ownerPermissionList != null && ownerPermissionList.stream().anyMatch(p -> strictPermissionCovers(p, strictRequired))), "严格角色分配的通配权限缺少可证明覆盖");
            } else requireStrictAction(requirePermission);
        }
        //角色简单匹配,权限列表简单匹配
        if (simpleMatch(requirePermission, ownerRoleList)
                || simpleMatch(requirePermission, ownerPermissionList)) {
            return true;
        }

        Assert.isTrue(RbacStrictAssignmentContext.VIEW.get() == null,
                "严格角色分配不支持动态动作目录或表达式回退");

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

        RbacUserInfo user = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
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
        final Expression verifyExpression = hasText(action.verifyExpression())
                ? verifyExpressionCache.computeIfAbsent(action.verifyExpression(), verifyExpressionParser::parseExpression)
                : null;

        final Supplier<Boolean> expressFun = verifyExpression != null ? () -> Boolean.TRUE.equals(ExpressionUtils.evalSpEL(null, null, verifyExpression, Collections.emptyList(),
                (ctx) -> {

                    if (getContext() != null) {
                        ctx.setBeanResolver(new BeanFactoryResolver(getContext()));
                    }

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
                })) : null;

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
     * 权限表达式的单个分段匹配。
     * <p>
     * 权限的资源 ID 段允许为空；此时只有通配模式（例如 {@code *}）可以匹配它，
     * 具体资源 ID 不能匹配。不要复用 {@link #textPatternMatch(String, String)}，
     * 后者用于用户类型和角色等场景，应该继续拒绝空值。
     */
    private boolean permissionSegmentPatternMatch(@Nullable String pattern, @Nullable String str) {

        return StringUtils.hasText(pattern)
                && str != null
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
                .allMatch(rp -> permissionSegmentPatternMatch(
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
