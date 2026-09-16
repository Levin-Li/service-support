package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.domain.DomainObject;
import com.levin.commons.utils.ExpressionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.levin.commons.rbac.RbacMiscUtils.isAllBlank;
import static com.levin.commons.rbac.RbacMiscUtils.isAllNull;

/**
 * Rbac 授权服务
 * <p>
 * 1、获取可以使用的资源清单
 * 2、获取可以使用的菜单清单
 * 3、方法授权检查
 *
 * @author lilw
 */
@Tag(name = "RBAC 授权与角色分配服务", description = "领域访问是授权前置门槛，不被管理员或菜单 alwaysShow 绕过。角色分配还须检查每个真实角色独立范围及分配后有效范围不能超过操作者，按本次完整有效目录逐项验证；DEFAULT 按双方各自归属解释，含范围脚本时目标真实对象须携带最终角色列表。目录/规则/上下文变化需重新校验，授权缓存不得保留过期结论。")
public interface RbacAuthorizeService extends RbacBaseAuthorizeService {

    /**
     * 角色分配条件表达式缓存
     */
    Map<String, Class<Object>> ROLE_ASSIGN_GROOVY_CLASS_CACHE = new ConcurrentReferenceHashMap<>();

    /**
     * 获取权限分隔符
     *
     * @return
     */
    default String getPermissionDelimiter() {
        return Permission.DELIMITER;
    }

    /**
     * 获取认证上下文
     *
     * @return
     */
    default Map<String, Object> getAuthorizeContext() {
        return Collections.emptyMap();
    }


    /**
     * 获取用户加载服务
     *
     * @return
     */
    RbacBaseService getRbacBaseLoadService();


    /**
     * 返回用户可见菜单的独立副本。领域门槛先于动作权限，拒绝父节点时移除整支。
     * alwaysShow 仅控制动作权限不足时的展示，不绕过领域或禁用状态。
     */
    @Operation(summary = "过滤用户可见菜单", description = "先按领域访问过滤，再判断动作权限；拒绝父节点会移除整棵子树。alwaysShow 仅在动作权限不足时保留展示，不绕过领域门槛或禁用状态。一次调用内复用用户、角色和权限读取结果。")
    default List<SimpleMenu> filterAccessibleMenuList(Serializable userPrincipal,
                                                      Collection<? extends MenuItem> menuList) {
        Assert.notNull(userPrincipal, "无用户主体");
        if (menuList == null || menuList.isEmpty()) {
            return Collections.emptyList();
        }
        Set<MenuItem> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        List<MenuItem> all = new ArrayList<>();
        Deque<MenuItem> pending = new ArrayDeque<>();
        menuList.stream().filter(Objects::nonNull).forEach(pending::add);
        while (!pending.isEmpty()) {
            MenuItem menu = pending.removeFirst();
            if (!visited.add(menu)) {
                continue;
            }
            all.add(menu);
            Collection<MenuItem> children = menu.getChildren();
            if (children != null) {
                children.stream().filter(Objects::nonNull).forEach(pending::addLast);
            }
        }
        Set<MenuItem> allowed = Collections.newSetFromMap(new IdentityHashMap<>());
        allowed.addAll(getRbacBaseLoadService().filterByDomainAccess(userPrincipal, all));
        RbacBaseService service = getRbacBaseLoadService();
        CacheSupplier<RbacUserInfo> user = new CacheSupplier<>(() -> userPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) userPrincipal : service.loadUser(userPrincipal));
        CacheSupplier<Collection<String>> roleCodes = new CacheSupplier<>(() -> service.loadUserRoleCodeList(user.get()));
        CacheSupplier<Collection<String>> permissions = new CacheSupplier<>(() -> service.loadUserPermissionExprList(user.get()));
        Predicate<Collection<String>> authorized = requirements -> {
            if (isAllBlank(requirements)) {
                return true;
            }
            Assert.notNull(user.get(), "用户不存在");
            return user.get().isTopSuperAdmin() || isAuthorized(user.get(), roleCodes.get(), permissions.get(),
                    true, requirements, null);
        };
        List<SimpleMenu> result = new ArrayList<>();
        Set<MenuItem> ancestors = Collections.newSetFromMap(new IdentityHashMap<>());
        for (MenuItem menu : menuList) {
            AccessibleMenu copy = copyAccessibleMenu(menu, allowed, ancestors, authorized);
            if (copy != null) {
                result.add(copy);
            }
        }
        return result;
    }

    private AccessibleMenu copyAccessibleMenu(MenuItem menu, Set<MenuItem> allowed,
                                              Set<MenuItem> ancestors, Predicate<Collection<String>> authorized) {
        if (menu == null || !allowed.contains(menu) || !menu.isEnable() || !ancestors.add(menu)) {
            return null;
        }
        try {
            if (!menu.isAlwaysShow() && !authorized.test(menu.getRequireAuthorizations())) {
                return null;
            }
            AccessibleMenu copy = new AccessibleMenu();
            copy.setId(Objects.toString(menu.getId(), null));
            copy.setParentId(Objects.toString(menu.getParentId(), null));
            copy.setDomainId(menu.getDomainId());
            if (menu instanceof SimpleMenu) {
                copy.setDomain(((SimpleMenu) menu).getDomain());
            }
            copy.setName(menu.getName());
            copy.setEnable(menu.isEnable());
            copy.setOrderCode(menu.getOrderCode());
            copy.setRemark(menu.getRemark());
            copy.setAlwaysShow(menu.isAlwaysShow());
            copy.setTarget(menu.getTarget());
            copy.setActionType(menu.getActionType());
            copy.setPath(menu.getPath());
            copy.setParams(menu.getParams());
            copy.setIcon(menu.getIcon());
            Collection<String> requirements = menu.getRequireAuthorizations();
            copy.setRequireAuthorizations(requirements == null ? null : new ArrayList<>(requirements));
            Set<MenuItem.OpButton> buttons = new LinkedHashSet<>();
            Set<MenuItem.OpButton> originalButtons = menu.getOpButtonList();
            if (originalButtons != null) {
                for (MenuItem.OpButton button : originalButtons) {
                    if (button != null && !button.isDisabled()
                            && authorized.test(button.getRequireAuthorizations())) {
                        buttons.add(new MenuItem.OpButton().setOpName(button.getOpName()).setLabel(button.getLabel())
                                .setDisabled(button.getDisabled()).setRemark(button.getRemark())
                                .setRequireAuthorizations(button.getRequireAuthorizations() == null ? null
                                        : new ArrayList<>(button.getRequireAuthorizations())));
                    }
                }
            }
            copy.setOpButtonList(buttons);
            Collection<MenuItem> children = menu.getChildren();
            if (children != null) {
                for (MenuItem child : children) {
                    AccessibleMenu childCopy = copyAccessibleMenu(child, allowed, ancestors, authorized);
                    if (childCopy != null) {
                        copy.addChild(childCopy);
                    }
                }
            }
            return copy;
        } finally {
            ancestors.remove(menu);
        }
    }

    @Operation(summary = "找出互斥的角色对", description = "按输入角色顺序检查互斥约束，发现首组互斥角色即返回；返回 null 表示没有互斥关系。此校验不通过时角色分配会被拒绝。")
    default <ROLE extends RbacRoleInfo> DataPair<ROLE, ROLE> findExclusiveRolePair(Serializable targetUserPrincipal, Collection<? extends ROLE> roleList) {

        if (isAllNull(roleList)) {
            return null;
        }

        final Map<String, Pattern> singleCharRolePatternCache = new LinkedHashMap<>();
        final BiPredicate<String, String> roleCodePatternMatcher = (rolePattern, roleCode) -> {

            final String trimmedRolePattern = rolePattern != null ? rolePattern.trim() : null;

            if (!StringUtils.hasText(trimmedRolePattern) || !StringUtils.hasText(roleCode)) {
                return false;
            }

            if (trimmedRolePattern.indexOf('?') < 0) {
                return PatternMatchUtils.simpleMatch(trimmedRolePattern, roleCode);
            }

            final Pattern regexPattern = singleCharRolePatternCache.computeIfAbsent(trimmedRolePattern, pattern -> {
                final StringBuilder regex = new StringBuilder(pattern.length() * 2);
                for (int i = 0; i < pattern.length(); i++) {
                    final char ch = pattern.charAt(i);
                    if (ch == '*') {
                        regex.append(".*");
                    } else if (ch == '?') {
                        regex.append('.');
                    } else {
                        regex.append(Pattern.quote(String.valueOf(ch)));
                    }
                }
                return Pattern.compile(regex.toString());
            });

            return regexPattern.matcher(roleCode).matches();
        };

        final Map<String, ROLE> roleMap = roleList.stream()
                .filter(Objects::nonNull)
                .filter(role -> StrUtil.isNotBlank(role.getCode()))
                .collect(Collectors.toMap(RbacRoleInfo::getCode, role -> role, (current, ignored) -> current, LinkedHashMap::new));

        for (ROLE role : roleList) {

            if (role == null || isAllBlank(role.getExclusiveRoleList())) {
                continue;
            }

            for (String rolePattern : role.getExclusiveRoleList()) {

                if (StrUtil.isBlank(rolePattern)) {
                    continue;
                }

                final ROLE exclusiveRole = roleMap.entrySet().stream()
                        .filter(entry -> roleCodePatternMatcher.test(rolePattern, entry.getKey()))
                        .map(Map.Entry::getValue)
                        .filter(matchRole -> matchRole != role)
                        .findFirst()
                        .orElse(null);

                if (exclusiveRole != null) {
                    return DataPair.of(role, exclusiveRole);
                }
            }
        }

        return null;
    }

    @Operation(summary = "找出缺失的共存角色", description = "按目标用户租户解析共存角色；发现首个缺失依赖即返回当前角色和缺失角色集合。表达式无法解析为角色对象时抛出异常，此校验不通过时角色分配会被拒绝。")
    default <ROLE extends RbacRoleInfo> DataPair<ROLE, Collection<ROLE>> findMissingCoexistRolePair(Serializable targetUserPrincipal, Collection<? extends ROLE> roleList) {

        if (isAllNull(roleList)) {
            return null;
        }

        final Set<String> roleCodes = roleList.stream()
                .filter(Objects::nonNull)
                .map(RbacRoleInfo::getCode)
                .filter(StringUtils::hasText)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (roleCodes.isEmpty()) {
            return null;
        }

        Assert.notNull(targetUserPrincipal, "目标用户不能为空");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = targetUserPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) targetUserPrincipal
                : rbacBaseService.loadUser(targetUserPrincipal);

        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);

        final Map<String, Pattern> singleCharRolePatternCache = new LinkedHashMap<>();
        final BiPredicate<String, String> roleCodePatternMatcher = (rolePattern, roleCode) -> {

            final String trimmedRolePattern = rolePattern != null ? rolePattern.trim() : null;

            if (!StringUtils.hasText(trimmedRolePattern) || !StringUtils.hasText(roleCode)) {
                return false;
            }

            if (trimmedRolePattern.indexOf('?') < 0) {
                return PatternMatchUtils.simpleMatch(trimmedRolePattern, roleCode);
            }

            final Pattern regexPattern = singleCharRolePatternCache.computeIfAbsent(trimmedRolePattern, pattern -> {
                final StringBuilder regex = new StringBuilder(pattern.length() * 2);
                for (int i = 0; i < pattern.length(); i++) {
                    final char ch = pattern.charAt(i);
                    if (ch == '*') {
                        regex.append(".*");
                    } else if (ch == '?') {
                        regex.append('.');
                    } else {
                        regex.append(Pattern.quote(String.valueOf(ch)));
                    }
                }
                return Pattern.compile(regex.toString());
            });

            return regexPattern.matcher(roleCode).matches();
        };

        final Map<String, Collection<ROLE>> roleListByCodePattern = new LinkedHashMap<>();

        for (ROLE role : roleList) {

            if (role == null || StrUtil.isBlank(role.getCode()) || isAllBlank(role.getCoexistRoleList())) {
                continue;
            }

            final Set<String> effectiveRoleCodes = new LinkedHashSet<>(roleCodes);
            final Map<String, ROLE> missingRoleMap = new LinkedHashMap<>();
            final Set<String> checkedRoleCodes = new LinkedHashSet<>();
            final Deque<ROLE> waitCheckRoles = new ArrayDeque<>();

            waitCheckRoles.add(role);

            while (!waitCheckRoles.isEmpty()) {

                final ROLE checkingRole = waitCheckRoles.poll();

                if (checkingRole == null
                        || StrUtil.isBlank(checkingRole.getCode())
                        || !checkedRoleCodes.add(checkingRole.getCode())
                        || isAllBlank(checkingRole.getCoexistRoleList())) {
                    continue;
                }

                final List<String> missingRoleCodePatterns = checkingRole.getCoexistRoleList().stream()
                        .filter(StringUtils::hasText)
                        .map(String::trim)
                        .filter(rolePattern -> effectiveRoleCodes.stream().noneMatch(roleCode -> roleCodePatternMatcher.test(rolePattern, roleCode)))
                        .collect(Collectors.toList());

                if (missingRoleCodePatterns.isEmpty()) {
                    continue;
                }

                final Collection<String> unloadedRoleCodePatterns = missingRoleCodePatterns.stream()
                        .filter(rolePattern -> !roleListByCodePattern.containsKey(rolePattern))
                        .collect(Collectors.toList());

                if (!unloadedRoleCodePatterns.isEmpty()) {
                    final Collection<ROLE> loadedRoleList = RoleDefinitionResolver.select(targetUser.getTenantId(),
                            rbacBaseService.<ROLE>loadTenantRoleListByCodePatterns(targetUser.getTenantId(), unloadedRoleCodePatterns), null);

                    for (String rolePattern : unloadedRoleCodePatterns) {
                        final List<ROLE> matchedRoleList = loadedRoleList.stream()
                                .filter(Objects::nonNull)
                                .filter(loadedRole -> StrUtil.isNotBlank(loadedRole.getCode()))
                                .filter(loadedRole -> roleCodePatternMatcher.test(rolePattern, loadedRole.getCode()))
                                .collect(Collectors.toList());

                        roleListByCodePattern.put(rolePattern, matchedRoleList);
                    }
                }

                final Collection<ROLE> missingRoleList = missingRoleCodePatterns.stream()
                        .map(roleListByCodePattern::get)
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toCollection(LinkedHashSet::new));

                for (String rolePattern : missingRoleCodePatterns) {
                    final boolean loaded = !isAllNull(roleListByCodePattern.get(rolePattern));

                    Assert.isTrue(loaded, "角色{}({})配置的共存角色表达式[{}]无法加载到角色对象",
                            checkingRole.getName(), checkingRole.getCode(), rolePattern);
                }

                for (ROLE missingRole : missingRoleList) {

                    if (missingRole == null || StrUtil.isBlank(missingRole.getCode())) {
                        continue;
                    }

                    if (!roleCodes.contains(missingRole.getCode())) {
                        missingRoleMap.putIfAbsent(missingRole.getCode(), missingRole);
                    }

                    if (effectiveRoleCodes.add(missingRole.getCode())
                            && !checkedRoleCodes.contains(missingRole.getCode())) {
                        waitCheckRoles.add(missingRole);
                    }
                }
            }

            if (!missingRoleMap.isEmpty()) {
                return DataPair.of(role, missingRoleMap.values());
            }

        }

        return null;
    }


    @Operation(summary = "校验角色分配", description = "解析目标租户真实角色后依次校验权限、前置条件、互斥、共存及数据范围上限；独立角色范围和最终有效范围均不得超出操作者范围与密级，清空角色也须检查最终状态。DEFAULT 按双方各自归属解释；对含 Groovy 范围的角色/用户，目标真实对象必须已携带最终角色编码，否则拒绝，操作人须保持当前已授权上下文。按本次完整一致的有效目录快照验证，目录/规则/上下文变化须重校验。任何失败抛异常拒绝，管理员与 isRoleAuthorized 扩展点的放行不能跳过范围上限。")
    default void checkRoleAssignment(Serializable operatorPrincipal, Serializable targetUserPrincipal, Collection<? extends RbacRoleInfo> finalRoles) {
        DomainAccess.evaluate(() -> {
            checkResolvedRoleAssignment(operatorPrincipal, targetUserPrincipal, finalRoles);
            return null;
        });
    }

    private void checkResolvedRoleAssignment(Serializable operatorPrincipal, Serializable targetUserPrincipal,
                                             Collection<? extends RbacRoleInfo> finalRoles) {

        Assert.notNull(operatorPrincipal, "操作用户不能为空");
        Assert.notNull(targetUserPrincipal, "目标用户不能为空");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = targetUserPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) targetUserPrincipal
                : rbacBaseService.loadUser(targetUserPrincipal);

        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);
        final RbacUserInfo operator = operatorPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) operatorPrincipal : rbacBaseService.loadUser(operatorPrincipal);
        Assert.notNull(operator, "操作用户({})不存在", operatorPrincipal);
        Assert.isTrue(operator.isPlatformUser()
                        || Objects.equals(Objects.toString(operator.getTenantId(), null), Objects.toString(targetUser.getTenantId(), null)),
                "不能跨租户分配角色");

        // 用户持有编码，传入对象的其它字段不能替代目标租户真正生效的角色定义。
        final Set<String> requestedCodes = new LinkedHashSet<>();
        if (finalRoles != null) {
            for (RbacRoleInfo role : finalRoles) {
                if (role == null) continue;
                Assert.notBlank(role.getCode(), "角色编码不能为空");
                requestedCodes.add(role.getCode());
            }
        }
        final List<RbacRoleInfo> resolvedRoles = requestedCodes.isEmpty() ? Collections.emptyList()
                : RoleDefinitionResolver.select(targetUser.getTenantId(),
                rbacBaseService.<RbacRoleInfo>loadTenantRoleList(targetUser.getTenantId(), true), requestedCodes);
        final Set<String> resolvedCodes = resolvedRoles.stream().map(RbacRoleInfo::getCode).collect(Collectors.toSet());
        for (String code : requestedCodes) {
            Assert.isTrue(resolvedCodes.contains(code), "角色编码[{}]在目标租户中没有有效定义或共享定义", code);
        }
        final List<DomainObject> domainObjects = new ArrayList<>();
        domainObjects.add(targetUser);
        domainObjects.addAll(resolvedRoles);
        // 目标租户是用户管理边界；角色定义的覆盖租户不是角色的领域父对象。
        if (RbacMiscUtils.isNotBlank(targetUser.getTenantId())) {
            final Serializable tenantId = targetUser.getTenantId();
            RbacTenantInfo tenant = rbacBaseService.loadTenant(tenantId);
            Assert.notNull(tenant, "租户({})不存在", tenantId);
            Assert.isTrue(Objects.equals(Objects.toString(tenantId, null), Objects.toString(tenant.getId(), null))
                    && tenant.selfAudit(), "租户({})信息不匹配或不可用", tenantId);
            domainObjects.add(tenant);
        }
        Assert.isTrue(rbacBaseService.filterByDomainAccess(operatorPrincipal, domainObjects).size() == domainObjects.size(),
                "操作用户无权访问目标用户或角色所属领域");
        for (RbacRoleInfo role : resolvedRoles) {

            if (role == null) {
                continue;
            }

            Assert.isTrue(isRoleAuthorized(operatorPrincipal, role, null), "操作用户无权分配角色{}({})", role.getName(), role.getCode());

            Assert.isTrue(isRoleAssignPreConditionMatched(targetUser, role), "目标用户不满足角色{}({})分配前置条件", role.getName(), role.getCode());
        }

        final DataPair<? extends RbacRoleInfo, ? extends RbacRoleInfo> exclusivePair = findExclusiveRolePair(targetUser, resolvedRoles);

        if (exclusivePair != null) {
            Assert.isNull(exclusivePair, "角色{}({})与角色{}({})互斥，不能同时分配",
                    exclusivePair.getA().getName(), exclusivePair.getA().getCode(),
                    exclusivePair.getB().getName(), exclusivePair.getB().getCode()
            );
        }

        final DataPair<? extends RbacRoleInfo, ? extends Collection<? extends RbacRoleInfo>> missingCoexistPair = findMissingCoexistRolePair(targetUser, resolvedRoles);
        if (missingCoexistPair != null) {
            Assert.isNull(
                    missingCoexistPair, "角色{}({})缺少必须共存的角色:{}",
                    missingCoexistPair.getA().getName(),
                    missingCoexistPair.getA().getCode(),
                    missingCoexistPair.getB().stream()
                            .filter(Objects::nonNull)
                            .map(role -> role.getName() + "(" + role.getCode() + ")")
                            .collect(Collectors.joining(", "))
            );
        }
        rbacBaseService.checkRoleDataScopeAssignment(operator, targetUser, resolvedRoles);
    }

    @Operation(summary = "检查目标用户是否满足角色分配前置条件", description = "仅校验目标用户与目标角色的前置条件，不替代操作人授权判断。前置条件脚本的编译结果可缓存复用；脚本返回非 true 或执行失败时视为不满足并拒绝分配。")
    default boolean isRoleAssignPreConditionMatched(Serializable targetUserPrincipal, RbacRoleInfo role) {

        Assert.notNull(targetUserPrincipal, "目标用户不能为空");
        Assert.notNull(role, "角色不能为空");

        final String expression = role.getAssignPreCondition();

        if (StrUtil.isBlank(expression)) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = targetUserPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) targetUserPrincipal
                : rbacBaseService.loadUser(targetUserPrincipal);
        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);

        final Map<String, Object> context = new LinkedHashMap<>();
        final RbacTenantInfo targetTenant = expression.contains("_tenant") && RbacMiscUtils.isNotBlank(targetUser.getTenantId())
                ? rbacBaseService.loadTenant(targetUser.getTenantId())
                : null;

        context.put("_tenant", targetTenant);
        context.put("_user", targetUser);
        context.put("_role", role);

        Object value = ExpressionUtils.evalGroovy(ROLE_ASSIGN_GROOVY_CLASS_CACHE, null, expression,
                "role-assign-pre-condition-" + Integer.toHexString(expression.hashCode()) + ".groovy", context);

        return Boolean.TRUE.equals(value);
    }

    /**
     * 是否授权
     *
     * @param principal       用户对象或是用户ID
     * @param domain
     * @param resType
     * @param res
     * @param conditionAction
     * @return
     */
    @Override
    @Operation(summary = "检查用户资源动作授权", description = "先将 domain、资源类型和资源编码组合为资源表达式，再执行资源授权；领域门槛不满足时优先拒绝，不会回退到角色或权限表达式匹配。")
    default boolean isAuthorized(@NotNull Serializable principal, String domain, String resType, String res, ResConditionAction conditionAction) {
        return isAuthorized(principal, String.join(getPermissionDelimiter(), domain, resType, res), conditionAction);
    }

    /**
     * 用户对指定的注解是否有权限
     * 最基础的权限检查
     *
     * @param principal
     * @param resExpr   资源表达式
     * @param action
     * @return
     */
    @Operation(summary = "用户对指定的资源是否有权限", description = "资源授权先受领域访问门槛约束；门槛不满足即拒绝，不会回退到角色或权限表达式匹配。具体动作条件由 ResConditionAction 决定。")
    boolean isAuthorized(Serializable principal, String resExpr, ResConditionAction action);

    /**
     * 是否授权
     *
     * @param principal 用户对象或是用户ID
     * @param role
     * @return
     */
    @Override
    @Operation(summary = "检查用户对角色的授权", description = "领域访问先于角色、租户、机密级别、管理员层级和权限表达式检查；任一前置门槛失败即拒绝，不能被后续管理员快捷路径绕过。")
    default boolean isRoleAuthorized(Serializable principal, RbacRoleInfo role, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        Assert.notNull(principal, "无用户主体");
        Assert.notNull(role, "角色为空");
        RbacBaseService service = getRbacBaseLoadService();
        // 该入口也用于新角色定义的创建校验；分配时的目录有效性由解析阶段保证。
        if (!service.canAccessObjectDomain(principal, role)) {
            if (matchErrorConsumer != null) {
                matchErrorConsumer.accept(role.getCode(), "无角色所属领域权限");
            }
            return false;
        }
        return isRoleAuthorizedAfterDomainCheck(principal, role, matchErrorConsumer);
    }

    private boolean isRoleAuthorizedAfterDomainCheck(Serializable principal, RbacRoleInfo role,
                                                     BiConsumer<String, String> matchErrorConsumer) {
        Assert.notNull(principal, "无用户主体");
        Assert.notNull(role, "角色为空");

        final String roleCode = role.getCode();
        Assert.notBlank(roleCode, "角色的编码为空");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();

        RbacUserInfo userInfo = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
        Assert.notNull(userInfo, "用户({})不存在", principal);

        // 如果是顶级超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

        if (matchErrorConsumer == null) {
            matchErrorConsumer = (permission, reason) -> {
            };
        }

        final boolean isPlatformUser = userInfo.isPlatformUser();

        //如果是租户用户
        if (!isPlatformUser) {

            if (role.isPublicRole()) {
                //如果角色是SAAS角色，则不能访问
                if (roleCode.startsWith(RbacRoleInfo.SAAS_ROLE_PREFIX)) {
                    return false;
                }
            } else if (!role.getTenantId().equals(userInfo.getTenantId())) {
                //不能跨租户访问
                return false;
            }
        }

        principal = userInfo;

        // 检查数据访问级别, 任何用户都检查, 除了TopSA
        if (!rbacBaseService.canAccessConfidentialDataByUser(userInfo, role.getConfidentialDataAccessLevel())) {
            return false;
        }

        //1、如果是超级管理员
        if (userInfo.isSuperAdmin()) {
            return true;
        }

        //只有超级管理员才能分配超级管理员
        if (RbacRoleInfo.SA_ROLE.equals(roleCode)) {
            return false;
        }

        if (userInfo.isSaasAdmin()) {
            return true;
        }

        if (RbacRoleInfo.SAAS_ADMIN.equals(roleCode)) {
            return false;
        }

        //管理员要求也是管理员
        if (RbacRoleInfo.ADMIN_ROLE.equals(roleCode) && !(isPlatformUser || userInfo.isTenantAdmin())) {
            return false;
        }

        // 带目标用户上下文的数据范围上限由 checkRoleAssignment 统一检查。

        //除了sa 和 saas_admin, 其他都要按权限检查
        //接下来开始检查角色的权限列表,比对角色需要的权限列表 和 用户拥有的权限列表

        return isAuthorized(principal, true, role.getPermissionList(), matchErrorConsumer);
    }

    /**
     * 当前用户是否 拥有指定的权限列表
     *
     * @param matchErrorConsumer
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList
     * @return
     */
    default boolean isAuthorized(Serializable principal, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer,
                                 boolean isRequireAllPermission, String... requirePermissionList) {
        return isAuthorized(principal, isRequireAllPermission, Arrays.asList(requirePermissionList), matchErrorConsumer);
    }

    /**
     * 当前用户是否 拥有指定的权限列表
     *
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList  权限列表可以包括角色，如果
     * @param matchErrorConsumer
     * @return
     */
    default boolean isAuthorized(Serializable principal, boolean isRequireAllPermission, Collection<String> requirePermissionList,
                                 BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        Assert.notNull(principal, "无用户主体");

        //如果不需要权限
        if (isAllBlank(requirePermissionList)) {
            return true;
        }

        //过滤空的权限列表
        requirePermissionList = requirePermissionList.stream().filter(StringUtils::hasText).collect(Collectors.toList());

        if (isAllBlank(requirePermissionList)) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        RbacUserInfo userInfo = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
        Assert.notNull(userInfo, "用户({})不存在", principal);

        //如果是超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

        principal = userInfo;

        return isAuthorized(userInfo, rbacBaseService.loadUserRoleCodeList(userInfo), rbacBaseService.loadUserPermissionExprList(userInfo),
                isRequireAllPermission, requirePermissionList, matchErrorConsumer);
    }

    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 关键方法
     *
     * @param principal              用户主体
     * @param ownerRoleCodeList      已经拥有的角色列表
     * @param ownerPermissionList    已经拥有的权限列表
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList  请求的权限
     * @param matchErrorConsumer     匹配错误回调 参数1为请求的权限，参数2为错误原因
     * @return 是否可以访问指定资源
     */
    default boolean isAuthorized(Serializable principal, Collection<String> ownerRoleCodeList, Collection<String> ownerPermissionList,
                                 boolean isRequireAllPermission, Collection<String> requirePermissionList,
                                 BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {
        //如果不需要权限
        if (requirePermissionList == null
                || requirePermissionList.isEmpty()) {
            return true;
        }

        //过滤空的权限列表
        requirePermissionList = requirePermissionList.stream().filter(StringUtils::hasText).collect(Collectors.toList());

        if (requirePermissionList.isEmpty()) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        RbacUserInfo userInfo = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
        Assert.notNull(userInfo, "用户({})不存在", principal);
        principal = userInfo;

        //如果是超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

        ///////////////////////////////////////////////////////////////

        Predicate<String> predicate = requirePermission -> isAuthorized(userInfo, ownerRoleCodeList, ownerPermissionList, requirePermission, matchErrorConsumer);

        //是否要求匹配所有权限
        return isRequireAllPermission ?
                requirePermissionList.stream().allMatch(predicate)
                : requirePermissionList.stream().anyMatch(predicate);
    }


    /**
     * 授权验证
     *
     * @param principal           用户主体
     * @param ownerRoleCodeList   拥有的角色列表
     * @param ownerPermissionList 拥有的权限列表
     * @param requirePermission   需要的权限
     * @param matchErrorConsumer  匹配错误回调
     * @return
     */
    boolean isAuthorized(Serializable principal, Collection<String> ownerRoleCodeList, Collection<String> ownerPermissionList, String requirePermission
            , BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer);

}
