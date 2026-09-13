package com.levin.commons.rbac;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.service.support.InjectConst;
import com.levin.commons.ui.annotation.CRUD;
import com.levin.commons.utils.ObjectWrapperUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.StaticWebApplicationContext;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RbacAuthorizeServiceRolePermissionTest {

    private TestAuthorizeService authorizeService;
    private StubRbacBaseService baseService;
    private TestRbacUser user;

    @BeforeEach
    void setUp() {
        // 构造一个典型租户管理员用户，作为角色/权限校验的主体。
        user = new TestRbacUser(
                "U1",
                "alice",
                "T1",
                "OPS",
                Arrays.asList(RbacRoleInfo.ADMIN_ROLE, "R_USER"),
                5000
        );

        baseService = new StubRbacBaseService(user);
        authorizeService = new TestAuthorizeService();
        authorizeService.setRbacBaseService(baseService);
    }

    @Test
    void shouldMatchPermissionWithWildcardAndOrToken() {
        // 业务规则：权限表达式支持 * 通配和 | 或条件。
        boolean authorized = authorizeService.isAuthorized(
                user,
                Collections.singleton("R_USER"),
                Collections.singleton("sys:order:*:read|list"),
                "sys:order:1001:list",
                null
        );

        assertTrue(authorized, "订单列表权限应命中表达式 sys:order:*:read|list");
    }

    @Test
    @SuppressWarnings("deprecation")
    void shouldDistinguishPlatformAndTenantUsers() {
        TestRbacUser platformUser = new TestRbacUser(
                "U_PLATFORM", "platform", null, "PLATFORM", Collections.emptyList(), null
        );
        TestRbacUser blankTenantUser = new TestRbacUser(
                "U_BLANK", "blank", " ", "PLATFORM", Collections.emptyList(), null
        );
        TestRbacUser tenantUser = new TestRbacUser(
                "U_TENANT", "tenant", "T1", "OPS", Collections.emptyList(), null
        );

        assertTrue(platformUser.isPlatformUser());
        assertTrue(blankTenantUser.isPlatformUser());
        assertFalse(platformUser.isTenantUser());
        assertTrue(tenantUser.isTenantUser());
        assertFalse(tenantUser.isPlatformUser());
        assertEquals(platformUser.isPlatformUser(), platformUser.isSaasUser(),
                "废弃别名必须保持兼容语义");
        assertEquals("isPlatformUser", InjectConst.IS_PLATFORM_USER);
        assertEquals("isTenantUser", InjectConst.IS_TENANT_USER);
        assertEquals("isSaasUser", InjectConst.IS_SAAS_USER,
                "废弃注入键必须保持兼容值");
    }

    @Test
    void shouldMatchEmptyResourceIdWithWildcardPermissionSegment() {
        assertTrue(authorizeService.simpleMatch(
                "com.levin.oak.base:系统数据-角色::查询列表",
                "*:系统数据-*:*:*"
        ), "资源 ID 为空时，* 应匹配该权限分段");
    }

    @Test
    void shouldMatchAllPermissionSegmentsWithSingleWildcard() {
        assertTrue(authorizeService.simpleMatch(
                "com.levin.oak.base:系统数据-角色::查询列表",
                "*"
        ), "单独的 * 应匹配权限表达式的全部分段");
    }

    @Test
    void shouldReuseTrailingWildcardForOmittedPermissionSegments() {
        assertTrue(authorizeService.simpleMatch(
                "com.levin.oak.base:系统数据-角色::查询列表",
                "com.levin.oak.base:系统数据-角色:*"
        ), "末尾 * 应匹配后续省略的资源 ID 和操作分段");
    }

    @Test
    void shouldRejectSpecificResourceIdForEmptyPermissionSegment() {
        assertFalse(authorizeService.simpleMatch(
                "com.levin.oak.base:系统数据-角色::查询列表",
                "*:系统数据-*:role-42:查询列表"
        ), "具体资源 ID 不能匹配空资源 ID");
    }

    @Test
    void shouldKeepWildcardPermissionMatchingForNonEmptyResourceId() {
        assertTrue(authorizeService.simpleMatch(
                "com.levin.oak.base:系统数据-角色:role-42:查询列表",
                "*:系统数据-*:*:查询*"
        ), "非空资源 ID 的通配匹配不应回归");
    }

    @Test
    void shouldMatchEmptyResourceIdOnlyWhenAlternativesContainWildcard() {
        String requirePermission = "com.levin.oak.base:系统数据-角色::查询列表";

        assertTrue(authorizeService.simpleMatch(
                requirePermission,
                "*:系统数据-*:role-42|*:查询列表"
        ), "资源 ID 备选表达式包含 * 时应匹配空资源 ID");
        assertFalse(authorizeService.simpleMatch(
                requirePermission,
                "*:系统数据-*:role-42|role-43:查询列表"
        ), "资源 ID 备选表达式均为具体值时不应匹配空资源 ID");
    }

    @Test
    void shouldKeepGeneralTextMatchingFailClosedForEmptyValues() {
        assertFalse(authorizeService.textPatternMatch("*", ""),
                "用户类型和角色等通用文本匹配仍应拒绝空值");
    }

    @Test
    void shouldHandleRequireAllAndRequireAnyPermissions() {
        // 业务规则：requireAll 需要全部命中，requireAny 命中任意一个即可通过。
        baseService.setUserPermissions(Arrays.asList("sys:user:*:read", "sys:order:*:view"));

        boolean requireAllPass = authorizeService.isAuthorized(
                user,
                true,
                Arrays.asList("sys:user:1:read", "sys:order:2:view"),
                null
        );

        boolean requireAllFail = authorizeService.isAuthorized(
                user,
                true,
                Arrays.asList("sys:user:1:read", "sys:order:2:delete"),
                null
        );

        boolean requireAnyPass = authorizeService.isAuthorized(
                user,
                false,
                Arrays.asList("sys:user:1:read", "sys:order:2:delete"),
                null
        );

        assertTrue(requireAllPass, "要求全部权限时，用户已具备全部权限应通过");
        assertFalse(requireAllFail, "要求全部权限时，只缺一个权限也应失败");
        assertTrue(requireAnyPass, "要求任一权限时，命中一个权限即应通过");
    }

    @Test
    void shouldAuthorizeByAnyRolesInOrModeWithoutPermission() {
        // 业务规则：默认 OR 模式下，匹配到 anyRoles 即可放行。
        authorizeService.addAction(
                "sys:report:monthly:view",
                new ResConditionActionObject()
                        .action("view")
                        .anyRoles(new String[]{"R_OPS_*"})
        );

        boolean authorized = authorizeService.isAuthorized(
                user,
                Collections.singleton("R_OPS_AUDITOR"),
                Collections.emptySet(),
                "sys:report:monthly:view",
                null
        );

        assertTrue(authorized, "OR 模式下命中角色 R_OPS_* 时应允许访问月报查看");
    }

    @Test
    void shouldRequireBothPermissionAndRoleWhenAndModeEnabled() {
        // 业务规则：AND 模式下，角色和权限必须同时满足。
        authorizeService.addAction(
                "sys:report:monthly:export",
                new ResConditionActionObject()
                        .action("export")
                        .anyRoles(new String[]{"R_OPS_*"})
                        .isAndMode(true)
        );

        boolean deniedWithoutPermission = authorizeService.isAuthorized(
                user,
                Collections.singleton("R_OPS_AUDITOR"),
                Collections.emptySet(),
                "sys:report:monthly:export",
                null
        );

        boolean authorizedWithPermission = authorizeService.isAuthorized(
                user,
                Collections.singleton("R_OPS_AUDITOR"),
                Collections.singleton("sys:report:monthly:export"),
                "sys:report:monthly:export",
                null
        );

        assertFalse(deniedWithoutPermission, "AND 模式下仅有角色、缺少权限时应拒绝");
        assertTrue(authorizedWithPermission, "AND 模式下角色与权限同时满足时应通过");
    }

    @Test
    void shouldAuthorizeByResAuthorizeWrapperAndCopyConditionAction() {
        ResConditionActionObject sourceAction = new ResConditionActionObject()
                .action("view")
                .anyUserTypes(new String[]{"OPS"})
                .anyRoles(new String[]{"R_USER"})
                .verifyExpression("#user.loginName == 'alice'")
                .confidentialLevel(100)
                .remark("profile view");
        ResAuthorize resAuthorize = RbacBaseAuthorizeService.newResAuthorize("sys", "profile", "self", sourceAction);
        ResConditionAction copiedAction = RbacBaseAuthorizeService.newResConditionAction(resAuthorize);

        assertEquals("view", copiedAction.action());
        assertArrayEquals(new String[]{"OPS"}, copiedAction.anyUserTypes());
        assertArrayEquals(new String[]{"R_USER"}, copiedAction.anyRoles());
        assertEquals("#user.loginName == 'alice'", copiedAction.verifyExpression());
        assertEquals(100, copiedAction.confidentialLevel());
        assertEquals("profile view", copiedAction.remark());

        assertTrue(authorizeService.isAuthorized(user, resAuthorize),
                "ResAuthorize 包装入口应委托到 domain/type/res/action 授权");
    }

    @Test
    void shouldEvaluateRoleAuthorizationCollectionWithAllOrAnyMode() {
        TestRbacRole reportRole = new TestRbacRole(
                "R1C",
                "R_REPORT_MANAGER",
                "T1",
                Collections.singletonList("sys:report:*:assign"),
                Collections.emptyList(),
                100
        );
        TestRbacRole financeRole = new TestRbacRole(
                "R1D",
                "R_FINANCE_MANAGER",
                "T1",
                Collections.singletonList("sys:finance:*:assign"),
                Collections.emptyList(),
                100
        );

        baseService.registerRole(reportRole);
        baseService.registerRole(financeRole);
        baseService.setUserPermissions(Collections.singletonList("sys:report:*:assign"));

        assertTrue(authorizeService.isRoleAuthorized(user, false, Arrays.asList(reportRole, financeRole), null),
                "任一模式下，只要一个角色可分配就应通过");
        assertFalse(authorizeService.isRoleAuthorized(user, true, Arrays.asList(reportRole, financeRole), null),
                "全部模式下，任一角色不可分配都应失败");
        assertTrue(authorizeService.isRoleAuthorized(user, true, Collections.emptyList(), null),
                "空角色集合应视为无需校验");
    }

    @Test
    void shouldApplyRoleAdminHierarchyRules() {
        assertTrue(authorizeService.canAdmin(RbacRoleInfo.SA_ROLE, RbacRoleInfo.SAAS_ADMIN),
                "超级管理员角色应能管理全部角色");
        assertTrue(authorizeService.canAdmin(RbacRoleInfo.SAAS_ADMIN, RbacRoleInfo.ADMIN_ROLE),
                "SaaS 管理员应能管理租户管理员角色");
        assertFalse(authorizeService.canAdmin(RbacRoleInfo.ADMIN_ROLE, RbacRoleInfo.SAAS_ADMIN),
                "租户管理员不能管理 SaaS 管理员角色");
        assertFalse(authorizeService.canAdmin("R_USER", RbacRoleInfo.ADMIN_ROLE),
                "普通角色不能管理租户管理员角色");
        assertTrue(authorizeService.canAdmin("R_USER", "R_AUDITOR"),
                "普通角色之间保持平权管理语义");
    }

    @Test
    void shouldCacheConfidentialLevelSupplierWithinSingleCheck() {
        AtomicInteger callCount = new AtomicInteger();

        assertTrue(baseService.canAccessConfidentialData(() -> {
            callCount.incrementAndGet();
            return 100;
        }, 10, 20, null));
        assertEquals(1, callCount.get(), "单次密级检查中应缓存 supplier 结果，避免重复计算");

        assertFalse(baseService.canAccessConfidentialDataByUser(user, 5001),
                "要求密级超过用户访问级别时应拒绝");
        assertTrue(baseService.canAccessConfidentialDataByUser(user, 5000),
                "要求密级等于用户访问级别时应允许");
    }

    @Test
    void shouldAllowPlatformPublicLevelWithoutConfidentialAccessLevel() {
        assertTrue(baseService.canAccessConfidentialData(
                () -> null,
                ConfidentialLevel.PLATFORM_PUBLIC.code()
        ), "平台公开数据不应要求访问者具备机密数据访问级别");
        assertFalse(baseService.canAccessConfidentialData(
                () -> null,
                ConfidentialLevel.PLATFORM_SHARED.code()
        ), "平台共享数据仍应要求访问者具备对应机密数据访问级别");
        assertTrue(baseService.canAccessConfidentialData(
                () -> ConfidentialLevel.PLATFORM_SHARED.code(),
                ConfidentialLevel.PLATFORM_SHARED.code()
        ), "负数机密级别仍按数值比较");
    }

    @Test
    void shouldDefaultAuthorizeActionsToPlatformPublicConfidentialLevel() throws NoSuchMethodException {
        assertEquals(ConfidentialLevel.PLATFORM_PUBLIC.code(),
                ((Integer) ResAuthorize.class.getDeclaredMethod("confidentialLevel").getDefaultValue()).intValue());
        assertEquals(ConfidentialLevel.PLATFORM_PUBLIC.code(),
                ((Integer) ResConditionAction.class.getDeclaredMethod("confidentialLevel").getDefaultValue()).intValue());
        assertEquals(ConfidentialLevel.PLATFORM_PUBLIC.code(), new ResConditionActionObject().confidentialLevel());
    }

    @Test
    void shouldMatchRoleExpressionAndReportUnauthorizedRole() {
        // 业务规则：角色表达式支持通配，未命中时返回 role not authorized 原因。
        List<String> errors = new ArrayList<>();

        boolean matched = authorizeService.isAuthorized(
                user,
                Collections.singleton("R_OPS_*"),
                Collections.emptySet(),
                "R_OPS_AUDITOR",
                null
        );

        boolean unmatched = authorizeService.isAuthorized(
                user,
                Collections.singleton("R_USER"),
                Collections.emptySet(),
                "R_OPS_AUDITOR",
                (require, reason) -> errors.add(reason)
        );

        assertTrue(matched, "角色表达式 R_OPS_* 应命中角色 R_OPS_AUDITOR");
        assertFalse(unmatched, "用户仅有 R_USER 时不应通过 R_OPS_AUDITOR 角色校验");
        assertTrue(errors.stream().anyMatch(reason -> reason.contains("role not authorized")),
                "角色不匹配时应返回 role not authorized 错误原因");
    }

    @Test
    void shouldCacheVerifyExpressionForResourceAuthorization() {
        authorizeService.addAction(
                "sys:expr:item:view",
                new ResConditionActionObject()
                        .action("view")
                        .verifyExpression("#user.type == 'OPS'")
        );

        assertTrue(authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:expr:item:view",
                null
        ));
        assertEquals(1, authorizeService.verifyExpressionCacheSize(), "首次执行后应缓存已编译的 SpEL 表达式");

        assertTrue(authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:expr:item:view",
                null
        ));
        assertEquals(1, authorizeService.verifyExpressionCacheSize(), "重复执行同一表达式不应重复解析");
    }

    @Test
    void shouldAllowBlankRequirementsAndReportUnknownPermissionAction() {
        List<String> errors = new ArrayList<>();

        assertTrue(authorizeService.isAuthorized(
                user,
                true,
                Arrays.asList(null, "", " \t "),
                (permission, reason) -> errors.add(reason)
        ), "空权限要求应视为无需校验");

        boolean authorized = authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:missing:item:view",
                (require, reason) -> errors.add(require + "|" + reason)
        );

        assertFalse(authorized, "未注册的资源操作且用户没有直接匹配权限时应拒绝");
        assertTrue(errors.stream().anyMatch(error -> error.contains("sys:missing:item:view") && error.contains("操作不存在")),
                "未知权限应通过 matchErrorConsumer 暴露缺失操作原因");
    }

    @Test
    void shouldAllowIgnoredAndAuthenticatedOnlyActionsWithoutExplicitPermission() {
        authorizeService
                .addAction("sys:health:ping:view",
                        new ResConditionActionObject().action("view").ignored(true))
                .addAction("sys:profile:self:view",
                        new ResConditionActionObject().action("view").onlyRequireAuthenticated(true));

        assertTrue(authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:health:ping:view",
                null
        ), "ignored 操作不应要求权限");
        assertTrue(authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:profile:self:view",
                null
        ), "onlyRequireAuthenticated 操作在已登录用户上下文中应放行");
    }

    @Test
    void shouldApplyUserTypeAndVerifyExpressionBoundaries() {
        authorizeService.addAction(
                "sys:approval:item:submit",
                new ResConditionActionObject()
                        .action("submit")
                        .anyUserTypes(new String[]{"OPS"})
                        .verifyExpression("#user.loginName == 'alice'")
        );

        TestRbacUser financeUser = new TestRbacUser(
                "U_EXPR_FIN",
                "alice",
                "T1",
                "FIN",
                Collections.singletonList("R_USER"),
                5000
        );
        TestRbacUser otherOpsUser = new TestRbacUser(
                "U_EXPR_OPS",
                "bob",
                "T1",
                "OPS",
                Collections.singletonList("R_USER"),
                5000
        );

        assertTrue(authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:approval:item:submit",
                null
        ), "用户类型和表达式都满足时应允许访问");
        assertFalse(authorizeService.isAuthorized(
                financeUser,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:approval:item:submit",
                null
        ), "用户类型不匹配且没有直接权限命中时应拒绝");
        assertTrue(authorizeService.isAuthorized(
                financeUser,
                Collections.emptySet(),
                Collections.singleton("sys:approval:item:submit"),
                "sys:approval:item:submit",
                null
        ), "直接拥有完全相同权限表达式时，当前快速匹配逻辑会在 action 条件前放行");
        assertFalse(authorizeService.isAuthorized(
                otherOpsUser,
                Collections.emptySet(),
                Collections.emptySet(),
                "sys:approval:item:submit",
                null
        ), "OR 模式下没有权限且表达式不满足时应拒绝");
    }

    @Test
    void shouldEvaluateEveryMatchedActionWhenRequiredPermissionIsWildcard() {
        authorizeService
                .addAction("sys:batch:item:read", new ResConditionActionObject().action("read"))
                .addAction("sys:batch:item:delete", new ResConditionActionObject().action("delete"));
        List<String> errors = new ArrayList<>();

        assertFalse(authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Collections.singleton("sys:batch:item:read"),
                "sys:batch:item:*",
                (require, reason) -> errors.add(reason)
        ), "通配权限匹配到多个操作时，任一具体操作缺权限都应拒绝");
        assertTrue(errors.stream().anyMatch(error -> error.contains("sys:batch:item:delete")),
                "通配权限失败时应报告未通过的具体操作");

        assertTrue(authorizeService.isAuthorized(
                user,
                Collections.emptySet(),
                Arrays.asList("sys:batch:item:read", "sys:batch:item:delete"),
                "sys:batch:item:*",
                null
        ), "通配权限匹配到的所有具体操作都授权时才应通过");
    }

    @Test
    void shouldCheckAnnotatedMethodAccessByResolvedResAuthorize() throws Exception {
        Method viewMethod = MethodAccessController.class.getDeclaredMethod("view");
        Method approveMethod = MethodAccessController.class.getDeclaredMethod("approve");
        Method authenticatedOnlyMethod = MethodAccessController.class.getDeclaredMethod("authenticatedOnly");
        Method ignoredMethod = MethodAccessController.class.getDeclaredMethod("ignored");

        baseService.setUserPermissions(Collections.singleton("sys:method:case:查看"));
        assertTrue(authorizeService.canAccess(user, MethodAccessController.class, viewMethod),
                "方法访问应使用 @Operation.summary 作为默认 action");

        baseService.setUserPermissions(Collections.emptyList());
        assertFalse(authorizeService.canAccess(user, MethodAccessController.class, viewMethod),
                "缺少方法对应权限时应拒绝访问");

        baseService.setUserPermissions(Collections.singleton("sys:method:case:approve"));
        assertTrue(authorizeService.canAccess(user, MethodAccessController.class, approveMethod),
                "方法级 @ResAuthorize.action 非空时应覆盖 @Operation.summary");
        baseService.setUserPermissions(Collections.singleton("sys:method:case:审批"));
        assertFalse(authorizeService.canAccess(user, MethodAccessController.class, approveMethod),
                "显式 action 覆盖后，不应再按 Operation.summary 授权");

        baseService.setUserPermissions(Collections.emptyList());
        assertTrue(authorizeService.canAccess(user, MethodAccessController.class, authenticatedOnlyMethod),
                "onlyRequireAuthenticated 的方法应允许已登录用户访问");
        assertTrue(authorizeService.canAccess(user, MethodAccessController.class, ignoredMethod),
                "ignored 或无可用授权注解的方法应视为不需要方法权限");
    }

    @Test
    void shouldApplyMethodAccessConfidentialAndTopSuperAdminRules() throws Exception {
        Method secretMethod = MethodAccessController.class.getDeclaredMethod("secret");

        baseService.setUserPermissions(Collections.singleton("sys:method:case:secret"));
        assertFalse(authorizeService.canAccess(user, MethodAccessController.class, secretMethod),
                "用户密级低于方法要求时，即使具备权限也应拒绝访问");

        TestRbacUser topSuperAdmin = new TestRbacUser(
                "U_TOP_SA",
                RbacUserInfo.TOP_SA_ACCOUNT_NAME,
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                0
        );
        StubRbacBaseService scopedService = new StubRbacBaseService(topSuperAdmin);
        TestAuthorizeService scopedAuthorizeService = new TestAuthorizeService();
        scopedAuthorizeService.setRbacBaseService(scopedService);

        assertTrue(scopedAuthorizeService.canAccess(topSuperAdmin, MethodAccessController.class, secretMethod),
                "顶级超级管理员应绕过方法权限和密级限制");
    }

    @Test
    void shouldFindExclusiveRolePair() {
        // 业务规则：角色互斥配置生效时，应返回第一组冲突角色。
        TestRbacRole financeRole = new TestRbacRole("R1", "R_FINANCE", "T1",
                Collections.emptyList(), Collections.singletonList("R_AUD*"), 100);
        TestRbacRole auditRole = new TestRbacRole("R2", "R_AUDIT", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        DataPair<TestRbacRole, TestRbacRole> pair = authorizeService.findExclusiveRolePair(user, Arrays.asList(financeRole, auditRole));
        assertNotNull(pair, "互斥角色冲突应返回角色对");
        Set<String> codes = Arrays.asList(pair.getA(), pair.getB()).stream().map(RbacRoleInfo::getCode).collect(Collectors.toSet());

        assertTrue(codes.contains("R_FINANCE"), "互斥角色结果应包含 R_FINANCE");
        assertTrue(codes.contains("R_AUDIT"), "互斥角色结果应包含 R_AUDIT");
    }

    @Test
    void shouldFindExclusiveRolePairWithSingleCharWildcard() {
        TestRbacRole financeRole = new TestRbacRole("R1A", "R_FINANCE", "T1",
                Collections.emptyList(), Collections.singletonList("R_AUDI?"), 100);
        TestRbacRole auditRole = new TestRbacRole("R1B", "R_AUDIT", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        DataPair<TestRbacRole, TestRbacRole> pair = authorizeService.findExclusiveRolePair(user, Arrays.asList(financeRole, auditRole));

        assertNotNull(pair, "? 单字符通配应能命中 R_AUDIT");
        assertEquals("R_AUDIT", pair.getB().getCode());
    }

    @Test
    void shouldFindMissingCoexistRolePair() {
        TestRbacRole advancedRole = new TestRbacRole(
                "R2A",
                "R_ADVANCED",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_BASE_*")
        );
        TestRbacRole baseRole = new TestRbacRole("R2B", "R_BASE_USER", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        baseService.registerRole(baseRole);

        DataPair<TestRbacRole, Collection<TestRbacRole>> missingPair = authorizeService.findMissingCoexistRolePair(user, Collections.singletonList(advancedRole));

        assertEquals("R_ADVANCED", missingPair.getA().getCode(), "缺失共存角色时应返回当前角色");
        assertIterableEquals(Collections.singletonList("R_BASE_USER"),
                missingPair.getB().stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "缺失共存角色时应返回缺失的共存角色对象");
        assertNull(authorizeService.findMissingCoexistRolePair(user, Arrays.asList(advancedRole, baseRole)),
                "补齐共存角色后不应再报告缺失");
    }

    @Test
    void shouldRejectMissingCoexistRoleWhenConfiguredRoleCannotBeLoaded() {
        TestRbacRole advancedRole = new TestRbacRole(
                "R2D",
                "R_ADVANCED",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_UNKNOWN_*")
        );

        assertThrows(IllegalArgumentException.class,
                () -> authorizeService.findMissingCoexistRolePair(user, Collections.singletonList(advancedRole)),
                "共存角色表达式无法加载到角色对象时应抛出异常");
    }

    @Test
    void shouldLoadMissingCoexistRoleCandidatesByWildcard() {
        TestRbacRole advancedRole = new TestRbacRole(
                "R2E",
                "R_ADVANCED",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_BASE_*")
        );
        TestRbacRole baseRole = new TestRbacRole("R2F", "R_BASE_USER", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        baseService.registerRole(advancedRole);
        baseService.registerRole(baseRole);

        DataPair<TestRbacRole, Collection<TestRbacRole>> missingPair =
                authorizeService.findMissingCoexistRolePair(user, Collections.singletonList(advancedRole));

        assertEquals("R_ADVANCED", missingPair.getA().getCode(), "应返回缺失约束所属角色");
        assertIterableEquals(Collections.singletonList("R_BASE_USER"),
                missingPair.getB().stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "缺失共存角色对象应按通配表达式加载候选角色");
    }

    @Test
    void shouldLoadMissingCoexistRoleCandidatesBySingleCharWildcard() {
        TestRbacRole advancedRole = new TestRbacRole(
                "R2G1",
                "R_ADVANCED",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_BASE_?")
        );
        TestRbacRole baseRole = new TestRbacRole("R2G2", "R_BASE_A", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);
        TestRbacRole longBaseRole = new TestRbacRole("R2G3", "R_BASE_AB", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        baseService.registerRole(baseRole);
        baseService.registerRole(longBaseRole);

        DataPair<TestRbacRole, Collection<TestRbacRole>> missingPair =
                authorizeService.findMissingCoexistRolePair(user, Collections.singletonList(advancedRole));

        assertIterableEquals(Collections.singletonList("R_BASE_A"),
                missingPair.getB().stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "? 单字符通配只应命中单个字符的角色编码");
    }

    @Test
    void shouldResolveMissingCoexistRoleClosure() {
        TestRbacRole advancedRole = new TestRbacRole(
                "R2H",
                "R_ADVANCED",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_BASE_*")
        );
        TestRbacRole baseRole = new TestRbacRole(
                "R2I",
                "R_BASE_USER",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_CORE_*")
        );
        TestRbacRole coreRole = new TestRbacRole("R2J", "R_CORE_USER", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        baseService.registerRole(baseRole);
        baseService.registerRole(coreRole);

        DataPair<TestRbacRole, Collection<TestRbacRole>> missingPair =
                authorizeService.findMissingCoexistRolePair(user, Collections.singletonList(advancedRole));

        assertEquals("R_ADVANCED", missingPair.getA().getCode(), "应返回触发闭包缺失的原始角色");
        assertIterableEquals(Arrays.asList("R_BASE_USER", "R_CORE_USER"),
                missingPair.getB().stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "缺失共存角色应包含多级共存闭包");
    }

    @Test
    void shouldStopCoexistClosureOnCycles() {
        TestRbacRole roleA = new TestRbacRole(
                "R2K",
                "R_A",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_B")
        );
        TestRbacRole roleB = new TestRbacRole(
                "R2L",
                "R_B",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_A")
        );

        baseService.registerRole(roleB);

        DataPair<TestRbacRole, Collection<TestRbacRole>> missingPair =
                assertTimeoutPreemptively(Duration.ofSeconds(1),
                        () -> authorizeService.findMissingCoexistRolePair(user, Collections.singletonList(roleA)));

        assertIterableEquals(Collections.singletonList("R_B"),
                missingPair.getB().stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "循环共存关系应在已知角色码上收敛，不应无限循环");
    }

    @Test
    void shouldEvaluateRoleAssignPreConditionWithTargetUserAndRole() {
        TestRbacRole contextualRole = new TestRbacRole(
                "R2C",
                "R_CONTEXTUAL",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100
        ) {
            @Override
            public String getAssignPreCondition() {
                return "_user.type == 'OPS' && _role.code == 'R_CONTEXTUAL'";
            }
        };

        assertTrue(authorizeService.isRoleAssignPreConditionMatched(user, contextualRole),
                "角色分配前置条件应使用目标用户 _user 和目标角色 _role 计算");

        TestRbacUser financeUser = new TestRbacUser(
                "U2C",
                "finance-user",
                "T1",
                "FIN",
                Collections.emptyList(),
                100
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(financeUser);
        TestAuthorizeService scopedAuthorizeService = new TestAuthorizeService();
        scopedAuthorizeService.setRbacBaseService(scopedService);

        assertFalse(scopedAuthorizeService.isRoleAssignPreConditionMatched(financeUser, contextualRole),
                "目标用户不满足前置条件时应拒绝分配");
    }

    @Test
    void shouldEvaluateRoleAssignPreConditionWithTenantContext() {
        TestRbacRole tenantScopedRole = new TestRbacRole(
                "R2C1",
                "R_TENANT_CONTEXTUAL",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100
        ) {
            @Override
            public String getAssignPreCondition() {
                return "_tenant != null && _tenant.id == 'T1' && _user.tenantId == _tenant.id && _role.tenantId == _tenant.id";
            }
        };

        baseService.setTenantList(Collections.singletonList(new TestTenant("T1", "Tenant1")));

        assertTrue(authorizeService.isRoleAssignPreConditionMatched(user, tenantScopedRole),
                "角色分配前置条件应支持 _tenant、_user 和 _role 三个变量");
    }

    @Test
    void shouldCheckRoleAssignmentWithAuthorizationPreConditionAndCoexistRules() {
        TestRbacRole advancedRole = new TestRbacRole(
                "R2F",
                "R_ADVANCED",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                null,
                Collections.singletonList("R_BASE_*")
        ) {
            @Override
            public String getAssignPreCondition() {
                return "_user.type == 'OPS'";
            }
        };
        TestRbacRole baseRole = new TestRbacRole("R2G", "R_BASE_USER", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        baseService.registerRole(advancedRole);
        baseService.registerRole(baseRole);
        assertThrows(IllegalArgumentException.class,
                () -> authorizeService.checkRoleAssignment(user, user, Collections.singletonList(advancedRole)),
                "缺少共存角色时，统一角色分配校验应拒绝");

        assertDoesNotThrow(() -> authorizeService.checkRoleAssignment(user, user, Arrays.asList(advancedRole, baseRole)),
                "操作人可分配、目标用户满足前置条件且共存角色齐全时应通过");
    }

    @Test
    void shouldRejectSaRoleForNonSuperAdmin() {
        // 业务规则：非超级管理员不能分配 R_SA。
        TestRbacRole saRole = new TestRbacRole(
                "R3",
                RbacRoleInfo.SA_ROLE,
                null,
                Collections.singletonList("sys:*:*:*"),
                Collections.emptyList(),
                100
        );

        boolean authorized = authorizeService.isRoleAuthorized(user, saRole, null);

        assertFalse(authorized, "普通租户管理员不应拥有分配 R_SA 的权限");
    }

    @Test
    void shouldAuthorizeRoleAssignmentByTenantAndRequiredPermissions() {
        // 业务规则：同租户且满足角色所需权限时，允许分配角色。
        TestRbacRole reportManagerRole = new TestRbacRole(
                "R4",
                "R_REPORT_MANAGER",
                "T1",
                Collections.singletonList("sys:member:*:assign"),
                Collections.emptyList(),
                100
        );

        baseService.registerRole(reportManagerRole);
        baseService.setUserPermissions(Collections.singletonList("sys:member:*:assign"));

        boolean authorized = authorizeService.isRoleAuthorized(user, reportManagerRole, null);

        assertTrue(authorized, "同租户且具备 sys:member:*:assign 权限时应允许分配角色");
    }

    @Test
    void shouldRejectRoleAssignmentWhenOperatorLacksRequiredPermission() {
        TestRbacRole reportManagerRole = new TestRbacRole(
                "R4_DENY",
                "R_REPORT_MANAGER",
                "T1",
                Collections.singletonList("sys:member:*:assign"),
                Collections.emptyList(),
                100
        );
        List<String> errors = new ArrayList<>();
        baseService.registerRole(reportManagerRole);

        assertFalse(authorizeService.isRoleAuthorized(user, reportManagerRole, (permission, reason) -> errors.add(reason)),
                "操作人缺少角色要求的权限时不能分配该角色");
        assertThrows(IllegalArgumentException.class,
                () -> authorizeService.checkRoleAssignment(user, user, Collections.singletonList(reportManagerRole)),
                "统一角色分配校验也应拒绝缺少授权的操作人");
        assertFalse(errors.isEmpty(), "角色分配授权失败时应提供匹配失败原因");
    }

    @Test
    void shouldRejectRoleAssignmentWhenTargetPreConditionOrExclusiveRoleFails() {
        TestRbacRole financeOnlyRole = new TestRbacRole(
                "R4_PRE",
                "R_FINANCE_ONLY",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100
        ) {
            @Override
            public String getAssignPreCondition() {
                return "_user.type == 'FIN'";
            }
        };
        TestRbacRole roleA = new TestRbacRole(
                "R4_EX_A",
                "R_EX_A",
                "T1",
                Collections.emptyList(),
                Collections.singletonList("R_EX_B"),
                100
        );
        TestRbacRole roleB = new TestRbacRole(
                "R4_EX_B",
                "R_EX_B",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100
        );

        baseService.registerRole(financeOnlyRole);
        baseService.registerRole(roleA);
        baseService.registerRole(roleB);
        assertThrows(IllegalArgumentException.class,
                () -> authorizeService.checkRoleAssignment(user, user, Collections.singletonList(financeOnlyRole)),
                "目标用户不满足角色分配前置条件时应拒绝");
        assertThrows(IllegalArgumentException.class,
                () -> authorizeService.checkRoleAssignment(user, user, Arrays.asList(roleA, roleB)),
                "最终角色集合包含互斥角色时应拒绝");
    }

    @Test
    void shouldRejectSaasRoleForTenantOperatorAndAllowTopSuperAdminRoleAssignment() {
        TestRbacRole saasAdminRole = new TestRbacRole(
                "R4_SAAS",
                RbacRoleInfo.SAAS_ADMIN,
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                100
        );
        TestRbacRole protectedSaRole = new TestRbacRole(
                "R4_SA",
                RbacRoleInfo.SA_ROLE,
                null,
                Collections.singletonList("sys:*:*:*"),
                Collections.emptyList(),
                10000
        );
        TestRbacUser topSuperAdmin = new TestRbacUser(
                "U_ROLE_TOP_SA",
                RbacUserInfo.TOP_SA_ACCOUNT_NAME,
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                0
        );
        StubRbacBaseService scopedService = new StubRbacBaseService(topSuperAdmin);
        TestAuthorizeService scopedAuthorizeService = new TestAuthorizeService();
        scopedAuthorizeService.setRbacBaseService(scopedService);

        assertFalse(authorizeService.isRoleAuthorized(user, saasAdminRole, null),
                "租户用户不能分配公共 SaaS 管理员角色");
        assertTrue(scopedAuthorizeService.isRoleAuthorized(topSuperAdmin, protectedSaRole, null),
                "顶级超级管理员应能分配受保护的超级管理员角色");
        scopedService.registerRole(protectedSaRole);
        assertDoesNotThrow(() -> scopedAuthorizeService.checkRoleAssignment(topSuperAdmin, topSuperAdmin, Collections.singletonList(protectedSaRole)),
                "顶级超级管理员的统一角色分配校验应通过");
    }

    @Test
    void shouldRejectCrossTenantRoleAssignment() {
        // 业务规则：角色分配不允许跨租户。
        TestRbacRole roleInAnotherTenant = new TestRbacRole(
                "R5",
                "R_REPORT_MANAGER",
                "T2",
                Collections.singletonList("sys:member:*:assign"),
                Collections.emptyList(),
                100
        );

        boolean authorized = authorizeService.isRoleAuthorized(user, roleInAnotherTenant, null);

        assertFalse(authorized, "跨租户角色分配应被拒绝");
    }

    @Test
    void shouldCheckConfidentialLevelBeforeGrantingSuperAdminRoleAuthorization() {
        TestRbacUser superAdmin = new TestRbacUser(
                "U6",
                "sa-helper",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                10
        );

        TestRbacRole protectedRole = new TestRbacRole(
                "R6",
                "R_PROTECTED",
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                100
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(superAdmin);
        TestAuthorizeService scopedAuthorizeService = new TestAuthorizeService();
        scopedAuthorizeService.setRbacBaseService(scopedService);

        assertFalse(scopedAuthorizeService.isRoleAuthorized(superAdmin, protectedRole, null),
                "普通超级管理员也必须先通过机密数据访问级别校验");
    }

    @Test
    void shouldLeaveRoleAvailabilityValidationToAuthorizationCaller() {
        TestRbacUser topSuperAdmin = new TestRbacUser(
                "U_ROLE_DISABLED_TOP_SA",
                RbacUserInfo.TOP_SA_ACCOUNT_NAME,
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                0
        );
        TestRbacRole newRole = new TestRbacRole(
                null,
                "R_TEAM_ADMIN",
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                0
        );
        TestRbacRole newRoleWithoutCode = new TestRbacRole(
                null,
                null,
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                0
        );
        DisabledTestRbacRole disabledPersistedRole = new DisabledTestRbacRole(
                "R_DISABLED_ASSIGN",
                "R_TEAM_ADMIN",
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                0
        );

        TestAuthorizeService authorizeService = new TestAuthorizeService();
        authorizeService.setRbacBaseService(new StubRbacBaseService(topSuperAdmin));

        assertTrue(authorizeService.isRoleAuthorized(topSuperAdmin, newRole, null),
                "新建角色可用性应由创建调用方检查");
        assertThrows(IllegalArgumentException.class,
                () -> authorizeService.isRoleAuthorized(topSuperAdmin, newRoleWithoutCode, null),
                "新建角色缺少 code 时必须在落库前拒绝，不能把 null 更新到 base_role.code");
        assertTrue(authorizeService.isRoleAuthorized(topSuperAdmin, disabledPersistedRole, null),
                "已持久化角色的启用、删除和过期状态也应由调用方检查");
    }

    @Test
    void shouldCheckConfidentialLevelBeforeGrantingSaasAdminRoleAuthorization() {
        TestRbacUser saasAdmin = new TestRbacUser(
                "U7",
                "saas-admin",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SAAS_ADMIN),
                10
        );

        TestRbacRole protectedRole = new TestRbacRole(
                "R7",
                "R_PLATFORM_MANAGER",
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                100
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(saasAdmin);
        TestAuthorizeService scopedAuthorizeService = new TestAuthorizeService();
        scopedAuthorizeService.setRbacBaseService(scopedService);

        assertFalse(scopedAuthorizeService.isRoleAuthorized(saasAdmin, protectedRole, null),
                "SaaS 管理员也必须先通过机密数据访问级别校验");
    }

    @Test
    void shouldFilterVisibleRoleListByRoleConfidentialLevel() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U7A",
                "role-viewer",
                "T1",
                "OPS",
                Arrays.asList("R_LOW", "R_HIGH"),
                50
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser);
        scopedService.registerRole(new TestRbacRole(
                "R7A1",
                "R_LOW",
                "T1",
                Collections.singletonList("sys:low:view"),
                Collections.emptyList(),
                10,
                Collections.emptyList(),
                10
        ));
        scopedService.registerRole(new TestRbacRole(
                "R7A2",
                "R_HIGH",
                "T1",
                Collections.singletonList("sys:high:view"),
                Collections.emptyList(),
                100,
                Collections.emptyList(),
                100
        ));

        assertIterableEquals(Collections.singletonList("R_LOW"),
                scopedService.loadUserAccessibleRoleList(scopedUser).stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "对外读取用户角色列表时，应按角色对象机密级别过滤");
    }

    @Test
    void shouldUseEffectiveRolesForPermissionAndConfidentialLevelCalculation() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U7B",
                "role-engine",
                "T1",
                "OPS",
                Arrays.asList("R_LOW", "R_HIGH"),
                null
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser);
        scopedService.registerRole(new TestRbacRole(
                "R7B1",
                "R_LOW",
                "T1",
                Collections.singletonList("sys:low:view"),
                Collections.emptyList(),
                10
        ));
        scopedService.registerRole(new TestRbacRole(
                "R7B2",
                "R_HIGH",
                "T1",
                Collections.singletonList("sys:high:view"),
                Collections.emptyList(),
                100,
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        ));

        assertEquals(100, scopedService.getUserConfidentialDataAccessLevel(scopedUser),
                "用户机密级别计算必须基于生效角色，而不是可见角色");

        assertTrue(scopedService.loadUserPermissionExprList(scopedUser).contains("sys:high:view"),
                "权限汇总必须基于生效角色，不能因为角色对象不可见就丢失权限");

        assertFalse(scopedService.getUserDataScope(scopedUser).getOrgScopeList() == null
                        || scopedService.getUserDataScope(scopedUser).getOrgScopeList().isEmpty(),
                "数据范围汇总也必须基于生效角色，避免角色可见性过滤影响授权");
    }

    @Test
    void shouldExcludeDisabledRolesFromConfidentialDataAccessLevel() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U7B_DISABLED",
                "role-level-user",
                "T1",
                "OPS",
                Arrays.asList("R_EFFECTIVE", "R_DISABLED"),
                null
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser);
        scopedService.registerRole(new TestRbacRole(
                "R7B_ENABLED",
                "R_EFFECTIVE",
                "T1",
                Collections.singletonList("sys:effective:*:read"),
                Collections.emptyList(),
                5
        ));
        scopedService.registerRole(new DisabledTestRbacRole(
                "R7B_DISABLED",
                "R_DISABLED",
                "T1",
                Collections.singletonList("sys:disabled:*:read"),
                Collections.emptyList(),
                6
        ));

        assertEquals(5, scopedService.getUserConfidentialDataAccessLevel(scopedUser),
                "禁用角色不能提高用户的机密数据访问级别");
        assertIterableEquals(Collections.singletonList("R_EFFECTIVE"),
                scopedService.loadUserOwnerRoleList(scopedUser).stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "禁用角色不能作为用户生效角色参与授权");
        assertIterableEquals(Collections.singletonList("sys:effective:*:read"),
                scopedService.loadUserPermissionExprList(scopedUser),
                "禁用角色不能授予资源权限");
    }

    @Test
    void shouldRecalculateConfidentialLevelWithoutTransientCache() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U7B1",
                "role-level-refresh",
                "T1",
                "OPS",
                Collections.singletonList("R_DYNAMIC_LEVEL"),
                null
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser);
        scopedService.registerRole(new TestRbacRole(
                "R7B11",
                "R_DYNAMIC_LEVEL",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                10
        ));

        assertEquals(10, scopedService.getUserConfidentialDataAccessLevel(scopedUser));

        scopedService.registerRole(new TestRbacRole(
                "R7B12",
                "R_DYNAMIC_LEVEL",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                80
        ));

        assertEquals(80, scopedService.getUserConfidentialDataAccessLevel(scopedUser),
                "用户角色密级不应缓存在 user.transientExInfo 中，否则同一用户对象会读到旧角色密级");
        assertTrue(scopedUser.getTransientExInfo().isEmpty());
    }

    @Test
    void shouldLoadRoleCodesPermissionsAndRolesThroughDefaultHelpers() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U7B2",
                "role-helper",
                "T1",
                "OPS",
                Arrays.asList("R_HELPER", "R_PUBLIC"),
                null
        );
        DefaultRoleHelperRbacBaseService scopedService = new DefaultRoleHelperRbacBaseService(scopedUser);
        scopedService.registerRole(new TestRbacRole(
                "R7B21",
                "R_HELPER",
                "T1",
                Collections.singletonList("sys:helper:*:view"),
                Collections.emptyList(),
                10
        ));
        scopedService.registerRole(new TestRbacRole(
                "R7B22",
                "R_PUBLIC",
                null,
                Collections.singletonList("sys:public:*:view"),
                Collections.emptyList(),
                5
        ));
        scopedService.registerRole(new TestRbacRole(
                "R7B23",
                "R_OTHER_TENANT",
                "T2",
                Collections.singletonList("sys:other:*:view"),
                Collections.emptyList(),
                100
        ));

        assertEquals(new LinkedHashSet<>(Arrays.asList("R_HELPER", "R_PUBLIC")),
                scopedService.loadUserRoleCodeList(scopedUser).stream().collect(Collectors.toCollection(LinkedHashSet::new)),
                "默认角色编码加载应基于用户生效角色，并包含公共角色");
        assertEquals(new LinkedHashSet<>(Arrays.asList("sys:helper:*:view", "sys:public:*:view")),
                scopedService.loadUserPermissionExprList(scopedUser).stream().collect(Collectors.toCollection(LinkedHashSet::new)),
                "默认权限汇总应基于用户生效角色");
        assertEquals(new LinkedHashSet<>(Arrays.asList("R_HELPER", "R_PUBLIC")),
                scopedService.loadTenantRoleListByCodes("T1", Arrays.asList("R_HELPER", "R_PUBLIC", "R_OTHER_TENANT"))
                        .stream().map(RbacRoleInfo::getCode).collect(Collectors.toCollection(LinkedHashSet::new)),
                "按 code 加载角色时应包含同租户和公共角色，排除其他租户角色");
        assertIterableEquals(Collections.singletonList("R_HELPER"),
                scopedService.loadTenantRoleListByCodePatterns("T1", Collections.singletonList("R_HELP?R"))
                        .stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()),
                "按 code 表达式加载角色应支持 ? 单字符通配");
        assertEquals(Collections.singleton("sys:helper:*:view"),
                scopedService.loadRolePermissionList("T1", "R_HELPER")
                        .stream().collect(Collectors.toSet()),
                "varargs 权限加载入口应委托到集合入口");
    }

    @Test
    void shouldPreferTenantSpecificRoleWhenPublicRoleHasSameCode() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U7B3",
                "role-shadow",
                "T1",
                "OPS",
                Collections.singletonList("R_SHARED"),
                null
        );
        DefaultRoleHelperRbacBaseService scopedService = new DefaultRoleHelperRbacBaseService(scopedUser);
        scopedService.registerRole(new TestRbacRole(
                "R7B31",
                "R_SHARED",
                null,
                Collections.singletonList("sys:shared:public:view"),
                Collections.emptyList(),
                10
        ));
        scopedService.registerRole(new TestRbacRole(
                "R7B32",
                "R_SHARED",
                "T1",
                Collections.singletonList("sys:shared:tenant:view"),
                Collections.emptyList(),
                80
        ));

        assertEquals(Collections.singleton("sys:shared:tenant:view"),
                scopedService.loadUserPermissionExprList(scopedUser).stream().collect(Collectors.toSet()),
                "公共角色和租户角色编码相同时，用户生效角色应优先使用本租户角色，避免公共角色覆盖租户专属权限");
        assertEquals(80, scopedService.getUserConfidentialDataAccessLevel(scopedUser),
                "同编码公共角色和租户角色并存时，用户密级也应来自本租户角色");
    }

    @Test
    void shouldBuildMenuOpButtonsFromCrudOpControllerMethods() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerSingleton("menuController", MenuController.class);
        context.refresh();

        try {
            List<SimpleMenu> menuList = RbacUtils.getMenuItemByController(
                    context,
                    RbacAuthorizeServiceRolePermissionTest.class.getPackageName(),
                    "菜单入口"
            );

            assertEquals(1, menuList.size());
            SimpleMenu menu = menuList.get(0);

            assertEquals("菜单测试", menu.getName());
            assertEquals("/api/menu", menu.getPath());
            assertEquals(Collections.singletonList("sys:menu:page:菜单入口"), menu.getRequireAuthorizations());

            Set<MenuItem.OpButton> opButtonList = menu.getOpButtonList();
            assertEquals(3, opButtonList.size(), "只有标注 @CRUD.Op 的控制器方法才应生成操作按钮");

            MenuItem.OpButton createButton = opButtonList.stream().filter(button -> "新增按钮".equals(button.getLabel())).findFirst().orElseThrow();
            assertEquals("新增按钮", createButton.getOpName());
            assertEquals("新增按钮", createButton.getLabel());
            assertEquals(List.of("sys:menu:page:新增"), createButton.getRequireAuthorizations());
            assertEquals("新增备注", createButton.getRemark());
            assertFalse(createButton.isDisabled());

            MenuItem.OpButton deleteButton = opButtonList.stream().filter(button -> "deleteOp".equals(button.getLabel())).findFirst().orElseThrow();
            assertEquals("deleteOp", deleteButton.getOpName());
            assertEquals("deleteOp", deleteButton.getLabel());
            assertEquals(List.of("sys:menu:page:删除"), deleteButton.getRequireAuthorizations());
            assertEquals("删除记录", deleteButton.getRemark());
            assertFalse(deleteButton.isDisabled());

            MenuItem.OpButton updateButton = opButtonList.stream().filter(button -> "更新".equals(button.getLabel())).findFirst().orElseThrow();
            assertEquals("更新", updateButton.getLabel());
            assertEquals(List.of("sys:menu:page:更新"), updateButton.getRequireAuthorizations());
            assertFalse(updateButton.isDisabled());
        } finally {
            context.close();
        }
    }

    @Test
    void shouldExposeMenuAuthorizationsAndOpButtonsWhenSerialized() {
        StaticWebApplicationContext context = new StaticWebApplicationContext();
        context.registerSingleton("menuController", MenuController.class);
        context.refresh();

        try {
            List<SimpleMenu> menuList = RbacUtils.getMenuItemByController(
                    context,
                    RbacAuthorizeServiceRolePermissionTest.class.getPackageName(),
                    "菜单入口"
            );

            JsonNode menuNode = new ObjectMapper().valueToTree(menuList.get(0));

            assertTrue(menuNode.has("requireAuthorizations"), "菜单序列化后必须保留 requireAuthorizations 字段");
            assertEquals("sys:menu:page:菜单入口", menuNode.path("requireAuthorizations").get(0).asText());

            assertTrue(menuNode.has("opButtonList"), "菜单序列化后必须保留 opButtonList 字段");
            assertEquals(3, menuNode.path("opButtonList").size());
            Map<String, JsonNode> buttons = new LinkedHashMap<>();
            menuNode.path("opButtonList").forEach(button -> buttons.put(button.path("label").asText(), button));
            assertEquals(Set.of("新增按钮", "deleteOp", "更新"), buttons.keySet());
            assertEquals("新增按钮", buttons.get("新增按钮").path("opName").asText());
            assertEquals("deleteOp", buttons.get("deleteOp").path("opName").asText());
            assertEquals("sys:menu:page:新增", buttons.get("新增按钮").path("requireAuthorizations").get(0).asText());
            assertEquals("sys:menu:page:删除", buttons.get("deleteOp").path("requireAuthorizations").get(0).asText());
            assertEquals("sys:menu:page:更新", buttons.get("更新").path("requireAuthorizations").get(0).asText());
        } finally {
            context.close();
        }
    }

    @Test
    void shouldCheckUserAdminRulesWithoutRecursiveConfidentialChecks() {
        TestRbacUser operator = new TestRbacUser(
                "U7B3_OP",
                "tenant-admin",
                "T1",
                "OPS",
                Collections.singletonList(RbacRoleInfo.ADMIN_ROLE),
                100
        );
        TestRbacUser sameTenantTarget = new TestRbacUser(
                "U7B3_TARGET",
                "target",
                "T1",
                "OPS",
                Collections.singletonList("R_USER"),
                50
        );
        TestRbacUser crossTenantTarget = new TestRbacUser(
                "U7B3_CROSS",
                "cross",
                "T2",
                "OPS",
                Collections.singletonList("R_USER"),
                50
        );
        TestRbacUser saasTarget = new TestRbacUser(
                "U7B3_SAAS",
                "saas",
                null,
                "OPS",
                Collections.singletonList("R_USER"),
                50
        );
        MultiUserRbacBaseService scopedService = new MultiUserRbacBaseService(operator, sameTenantTarget, crossTenantTarget, saasTarget);

        assertTrue(scopedService.canAdminUser(operator.getId(), sameTenantTarget.getId()),
                "同租户且密级足够时应允许管理目标用户");
        assertFalse(scopedService.canAdminUser(operator.getId(), crossTenantTarget.getId()),
                "租户用户不能跨租户管理用户");
        assertFalse(scopedService.canAdminUser(operator.getId(), saasTarget.getId()),
                "租户用户不能管理无租户 SaaS 用户");
        assertTrue(scopedService.canAdminUser(operator.getId(), operator.getId()),
                "管理自己应快速通过");
    }

    @Test
    void shouldRecalculateDataScopeWithoutTransientCache() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U7C",
                "role-scope-refresh",
                "T1",
                "OPS",
                Collections.singletonList("R_DYNAMIC"),
                null
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser);
        scopedService.registerRole(new TestRbacRole(
                "R7C1",
                "R_DYNAMIC",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                10,
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        ));

        assertEquals("A|SelfAndAllChild", scopedService.getUserDataScope(scopedUser).getOrgScopeList()
                .iterator().next());

        scopedService.registerRole(new TestRbacRole(
                "R7C2",
                "R_DYNAMIC",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                10,
                Collections.singletonList(scope("B", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        ));

        assertEquals("B|SelfAndAllChild", scopedService.getUserDataScope(scopedUser).getOrgScopeList()
                .iterator().next(),
                "DataScope 不应再缓存在 user.transientExInfo 中，否则同一用户对象会读到旧角色范围");
        assertFalse(scopedUser.getTransientExInfo().containsKey(DataScope.class.getName()));
    }

    @Test
    void shouldPreferUserOrgScopeOverRoleOrgScope() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U2",
                "bob",
                "T1",
                "OPS",
                Arrays.asList("R_MANAGER"),
                5000,
                "B",
                Collections.singletonList(scope("B", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());
        scopedService.registerRole(new TestRbacRole(
                "R6",
                "R_MANAGER",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                100,
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        ));

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("B", "B1"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "用户自己定义了组织范围时，应优先于角色上的组织范围");
    }

    @Test
    void shouldApplyPathPatternOrgScopeWithDenyOverride() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3",
                "cindy",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild),
                        customScope("A", false, "/A2/**")
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A", "A1", "B", "B1"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "allow all 后叠加 deny PathPattern 时，应移除命中的子树");
    }

    @Test
    void shouldApplyOnlyDirectChildScopeMatchingMode() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3A",
                "direct-child",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.DirectChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "OnlyDirectChild 应只包含直接子节点，不包含本节点和孙节点");
    }

    @Test
    void shouldApplySelfAndDirectChildScopeMatchingMode() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3B",
                "self-and-direct-child",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.SelfAndDirectChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A", "A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "SelfAndDirectChild 应包含本节点和直接子节点，不包含孙节点");
    }

    @Test
    void shouldApplyAllRootOrgWithDirectChildPatterns() {
        TestRbacUser onlyDirectChildUser = new TestRbacUser(
                "U3C",
                "all-root-direct-child",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.DirectChild))
        );
        TestRbacUser selfAndDirectChildUser = new TestRbacUser(
                "U3D",
                "all-root-self-and-direct-child",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndDirectChild))
        );

        StubRbacBaseService onlyDirectChildService = new StubRbacBaseService(onlyDirectChildUser)
                .setOrgList(baseOrgTree());
        StubRbacBaseService selfAndDirectChildService = new StubRbacBaseService(selfAndDirectChildUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A1", "A2", "B1"),
                onlyDirectChildService.loadUserOrgList(onlyDirectChildUser, false)
                        .stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "ALL_ROOT_ORG + OnlyDirectChild 应返回所有根组织的直接子节点，不包含根节点和孙节点");
        assertIterableEquals(Arrays.asList("A", "A1", "A2", "B", "B1"),
                selfAndDirectChildService.loadUserOrgList(selfAndDirectChildUser, false)
                        .stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "ALL_ROOT_ORG + SelfAndDirectChild 应返回所有根组织及直接子节点，不包含孙节点");
    }

    @Test
    void shouldResolveUserOrgAsScopeRoot() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3E",
                "user-org-scope",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A2",
                Collections.singletonList(scope("_DEFAULT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A2", "A21"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "_DEFAULT_ 应解析为用户默认组织，并按指定 ScopeMatchingMode 继续扩展");
    }

    @Test
    void shouldApplyStandardDenyScopeWithoutRemovingGrandchildren() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3F",
                "standard-deny",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild),
                        scope("A", false, DataScope.OrgMatchingMode.SelfAndDirectChild)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A21", "B", "B1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "标准 deny scope 应按自身匹配模式移除节点，SelfAndDirectChild 不应误删孙节点");
    }

    @Test
    void shouldReturnEmptyWhenAnyOrgScopeDenyAll() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U30",
                "bruce",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild),
                        scope("_ALL_ROOT_", false, DataScope.OrgMatchingMode.SelfAndAllChild)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertTrue(scopedService.loadUserOrgList(scopedUser, false).isEmpty(),
                "只要命中 deny all，就应直接返回空组织列表");
    }

    @Test
    void shouldReturnAllWhenAllowAllWithoutDeny() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U301",
                "bella",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild),
                        scope("A", true, DataScope.OrgMatchingMode.Self)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A", "A1", "A2", "A21", "B", "B1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "allow all 且没有任何 deny 时，应直接返回全部已加载组织");
    }

    @Test
    void shouldUseAllRootOrgAsRangeStart() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U301A",
                "root-only",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.Self))
        );
        TestRbacUser allFromRootUser = new TestRbacUser(
                "U301B",
                "all-from-root",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());
        StubRbacBaseService allFromRootService = new StubRbacBaseService(allFromRootUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A", "B"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "ALL_ROOT_ORG + OnlySelf 应只返回所有根组织本身");
        assertIterableEquals(Arrays.asList("A", "A1", "A2", "A21", "B", "B1"),
                allFromRootService.loadUserOrgList(allFromRootUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "ALL_ROOT_ORG + All 应从所有根组织起点扩展成所有组织");
    }

    @Test
    void shouldSupportCrossTenantOrgScopeByTenantIdForPlatformUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302",
                "carol",
                null,
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("T2", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("T2", "Tenant2")))
                .setOrgList(Arrays.asList(
                        new TestOrg("A", null, "T1", "A"),
                        new TestOrg("A1", "A", "T1", "A1"),
                        new TestOrg("C", null, "T2", "C"),
                        new TestOrg("C1", "C", "T2", "C1")
                ));

        assertIterableEquals(Arrays.asList("C", "C1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "平台用户的 OrgScope 指定 tenantScopeList 时，应只返回命中的租户组织");
    }

    @Test
    void shouldSupportTenantSelectionScriptForPlatformUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302P",
                "platform-pattern",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("Groovy#_tenant?.id?.startsWith('tenant-')", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("tenant-a", "TenantA"),
                        new TestTenant("tenant-b", "TenantB"),
                        new TestTenant("other", "Other")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("A", null, "tenant-a", "A"),
                        new TestOrg("A1", "A", "tenant-a", "A1"),
                        new TestOrg("C", null, "tenant-b", "C"),
                        new TestOrg("D", null, "other", "D")
                ));

        assertIterableEquals(Arrays.asList("tenant-a", "tenant-b"),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "平台用户的 tenantScopeList 应支持 Groovy# 匹配多个租户");
        assertIterableEquals(Arrays.asList("A", "A1", "C"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "平台用户的 脚本租户范围应只加载命中租户下的组织");
    }

    @Test
    void shouldCalculateOrgScopePerTenantWhenOrgIdsOverlap() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302D",
                "platform-duplicate-org-id",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "ROOT",
                Arrays.asList(
                        customScope("T1", "_ALL_ROOT_", true, "_org.tenantId == 'T1'", DataScope.OrgMatchingMode.Groovy),
                        scope("T2", "ROOT", true, DataScope.OrgMatchingMode.Self)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("ROOT", null, "T1", "T1Root"),
                        new TestOrg("CHILD", "ROOT", "T1", "T1Child"),
                        new TestOrg("ROOT", null, "T2", "T2Root"),
                        new TestOrg("CHILD", "ROOT", "T2", "T2Child")
                ));

        List<String> accessibleOrgKeys = scopedService.loadUserOrgList(scopedUser, false).stream()
                .map(org -> Objects.toString(org.getTenantId(), "") + ":" + Objects.toString(org.getId(), ""))
                .collect(Collectors.toList());

        assertIterableEquals(Arrays.asList("T1:ROOT", "T1:CHILD", "T2:ROOT"),
                accessibleOrgKeys,
                "跨租户组织 ID 重复时，应按租户独立计算组织范围，不能用全局 orgId map 互相污染: " + accessibleOrgKeys);
    }

    @Test
    void shouldLimitTenantSelectionScriptToOwnTenantForTenantUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302P1",
                "tenant-pattern",
                "tenant-a",
                "OPS",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("Groovy#_tenant?.id?.startsWith('tenant-')", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("tenant-a", "TenantA"),
                        new TestTenant("tenant-b", "TenantB")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("A", null, "tenant-a", "A"),
                        new TestOrg("A1", "A", "tenant-a", "A1"),
                        new TestOrg("C", null, "tenant-b", "C"),
                        new TestOrg("C1", "C", "tenant-b", "C1")
                ));

        assertIterableEquals(Collections.singletonList("tenant-a"),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "普通租户用户即使命中租户脚本，也只能访问自己的租户");
        assertIterableEquals(Arrays.asList("A", "A1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "普通租户用户的 脚本组织范围不能扩展到其他租户");
    }

    @Test
    void shouldSupportPrefixedTenantGroovyForPlatformUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302G",
                "platform-groovy",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("Groovy#" + "_tenant?.id?.startsWith('tenant-')",
                        "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("tenant-a", "TenantA"),
                        new TestTenant("tenant-b", "TenantB"),
                        new TestTenant("other", "Other")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("A", null, "tenant-a", "A"),
                        new TestOrg("C", null, "tenant-b", "C"),
                        new TestOrg("D", null, "other", "D")
                ));

        assertIterableEquals(Arrays.asList("tenant-a", "tenant-b"),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "平台用户的 tenantScopeList 应支持 Groovy# 前缀脚本");
        assertIterableEquals(Arrays.asList("A", "C"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "带 ? 的 Groovy 脚本不应被误判成 Spring PathPattern");
    }

    @Test
    void shouldNotTreatUnprefixedTenantScriptAsGroovy() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302G1",
                "platform-unprefixed-script",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("_tenant?.id == 'T2'", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("T2", "Tenant2")))
                .setOrgList(Collections.singletonList(new TestOrg("C", null, "T2", "C")));

        assertTrue(scopedService.loadUserAccessibleTenantList(scopedUser, true).isEmpty(),
                "未带 Groovy# 前缀的脚本不应被当成 Groovy 执行");
        assertTrue(scopedService.loadUserOrgList(scopedUser, false).isEmpty(),
                "未带 Groovy# 前缀的脚本不应产生组织范围");
    }

    @Test
    void shouldAllowTenantGroovyWithinOwnTenant() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302G2",
                "tenant-groovy",
                "tenant-a",
                "OPS",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("Groovy#" + "_tenant?.id == 'tenant-a'",
                        "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("tenant-a", "TenantA")))
                .setOrgList(Collections.singletonList(new TestOrg("A", null, "tenant-a", "A")));

        assertEquals(1, scopedService.loadUserAccessibleTenantList(scopedUser, true).size(),
                "普通用户的脚本可以匹配自身租户");
        assertEquals(1, scopedService.loadUserOrgList(scopedUser, false).size(),
                "普通用户只可看到自身租户组织");
    }

    @Test
    void shouldIgnoreCrossTenantOrgScopeForTenantUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302A",
                "tenant-carol",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("T2", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("A", null, "T1", "A"),
                        new TestOrg("A1", "A", "T1", "A1"),
                        new TestOrg("C", null, "T2", "C"),
                        new TestOrg("C1", "C", "T2", "C1")
                ));

        assertTrue(scopedService.loadUserOrgList(scopedUser, false).isEmpty(),
                "普通租户用户配置其他租户 OrgScope 时，应忽略该 scope，不应拿到跨租户组织");
    }

    @Test
    void shouldApplyOrganizationDenialWithinOwnTenant() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302B",
                "tenant-dave",
                "T1",
                "OPS",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope("A", true, DataScope.OrgMatchingMode.SelfAndAllChild),
                        scope("_ALL_", "_ALL_ROOT_", false, DataScope.OrgMatchingMode.SelfAndAllChild)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Collections.emptyList(),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "独立组织拒绝范围在自身租户内同样生效");
    }

    @Test
    void shouldLoadPlatformUserCanAccessTenantListByTenantExpression() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3021",
                "carol",
                null,
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("T2", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2"),
                        new TestTenant("T3", "Tenant3")
                ));

        assertIterableEquals(Collections.singletonList("T2"),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "平台用户 loadUserCanAccessTenantList 应按 tenantScopeList 返回可访问租户");
    }

    @Test
    void shouldIgnoreCrossTenantTenantScopeForTenantUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3021A",
                "tenant-carol",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("T2", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2"),
                        new TestTenant("T3", "Tenant3")
                ));

        assertTrue(scopedService.loadUserAccessibleTenantList(scopedUser, true).isEmpty(),
                "普通租户用户配置其他租户 OrgScope 时，应忽略该 scope，不应拿到跨租户租户列表");
    }

    @Test
    void shouldAllowOnlyTopSuperAdminToLoadAllTenantsDirectly() {
        TestRbacUser topSuperAdmin = new TestRbacUser(
                "U3021_TOP",
                RbacUserInfo.TOP_SA_ACCOUNT_NAME,
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                1
        );

        StubRbacBaseService topSuperService = new StubRbacBaseService(topSuperAdmin)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ));

        TestRbacUser normalSuperAdmin = new TestRbacUser(
                "U3021_SA",
                "sa-helper",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                1
        );

        StubRbacBaseService superService = new StubRbacBaseService(normalSuperAdmin)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ));

        assertIterableEquals(Arrays.asList("T1", "T2"),
                topSuperService.loadUserAccessibleTenantList(topSuperAdmin, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "顶级超级管理员应直接拿到全部租户");

        assertIterableEquals(Arrays.asList("T1", "T2"),
                superService.loadUserAccessibleTenantList(normalSuperAdmin, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "普通超级管理员应在机密级别通过后直接拿到全量租户结果");
    }

    @Test
    void shouldNotInferTenantGrantWhenUserAndRolesHaveNoConfiguration() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3022",
                "doris",
                "T1",
                "A",
                Collections.emptyList(),
                5000
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ));

        assertIterableEquals(Collections.emptyList(),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "用户和角色均没有租户授权时，不应隐式授权默认租户");
    }

    @Test
    void shouldAllowOnlyTopSuperAdminToLoadAllOrgsDirectly() {
        TestRbacUser topSuperAdmin = new TestRbacUser(
                "U3022_TOP",
                RbacUserInfo.TOP_SA_ACCOUNT_NAME,
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                1
        );

        StubRbacBaseService topSuperService = new StubRbacBaseService(topSuperAdmin)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("P", null, null, "Public"),
                        new TestOrg("A", null, "T1", "A"),
                        new TestOrg("B", null, "T2", "B")
                ));

        TestRbacUser normalSuperAdmin = new TestRbacUser(
                "U3022_SA",
                "sa-helper",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                1
        );

        StubRbacBaseService superService = new StubRbacBaseService(normalSuperAdmin)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("P", null, null, "Public"),
                        new TestOrg("A", null, "T1", "A"),
                        new TestOrg("B", null, "T2", "B")
                ));

        assertIterableEquals(Arrays.asList("A", "B", "P"),
                topSuperService.loadUserAccessibleOrgList(topSuperAdmin, true).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "顶级超级管理员应直接拿到全部组织结果");

        assertIterableEquals(Arrays.asList("A", "B", "P"),
                superService.loadUserAccessibleOrgList(normalSuperAdmin, true).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "普通超级管理员应在机密级别通过后直接拿到全量组织结果");
    }

    @Test
    void shouldFilterTenantListByConfidentialLevelForSuperAdmin() {
        TestRbacUser superAdmin = new TestRbacUser(
                "U3023_SA",
                "sa-helper",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                50
        );

        StubRbacBaseService superService = new StubRbacBaseService(superAdmin)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1", 10),
                        new TestTenant("T2", "Tenant2", 100)
                ));

        assertIterableEquals(Collections.singletonList("T1"),
                superService.loadUserAccessibleTenantList(superAdmin, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "普通超级管理员直返租户结果时，仍需按租户机密级别过滤");
    }

    @Test
    void shouldFilterOrgListByConfidentialLevelForSuperAdmin() {
        TestRbacUser superAdmin = new TestRbacUser(
                "U3024_SA",
                "sa-helper",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                50
        );

        StubRbacBaseService superService = new StubRbacBaseService(superAdmin)
                .setTenantList(Collections.singletonList(new TestTenant("T1", "Tenant1")))
                .setOrgList(Arrays.asList(
                        new TestOrg("P", null, null, "Public", 10),
                        new TestOrg("A", null, "T1", "A", 20),
                        new TestOrg("B", null, "T1", "B", 100)
                ));

        assertIterableEquals(Arrays.asList("A", "P"),
                superService.loadUserAccessibleOrgList(superAdmin, true).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "普通超级管理员直返组织结果时，仍需按组织机密级别过滤");
    }

    @Test
    void shouldLoadOrgChildrenAndParentsWithoutLooping() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A1", "A2"),
                scopedService.loadOrgChildren("T1", "A").stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "直接下级组织加载应只返回 parentId 命中的子节点");
        assertIterableEquals(Arrays.asList("A21", "A2", "A"),
                scopedService.loadOrgParentList("T1", true, "A21", true).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "父链加载包含自身时应按由近到远返回");
        assertIterableEquals(Arrays.asList("A2", "A"),
                scopedService.loadOrgParentList("T1", false, "A21", false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "父链加载不包含自身时应从父节点开始返回");
    }

    @Test
    void shouldRejectCyclicOrgParentChain() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user)
                .setOrgList(Arrays.asList(
                        new TestOrg("A", "B", "T1", "A"),
                        new TestOrg("B", "A", "T1", "B")
                ));

        assertThrows(IllegalArgumentException.class,
                () -> scopedService.loadOrgParentList("T1", true, "A", false),
                "父链存在循环引用时必须抛出异常，避免死循环");
    }

    @Test
    void shouldCheckNormalUserOrgAccessibilityByAccessibleOrgSet() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3025",
                "org-operator",
                "T1",
                "OPS",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );
        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertDoesNotThrow(() -> scopedService.checkOrgAccessible(scopedUser, "T1", "A", "A1"),
                "普通用户应能操作其可访问组织范围内的父子组织");
        assertThrows(IllegalArgumentException.class,
                () -> scopedService.checkOrgAccessible(scopedUser, "T1", "B", "B1"),
                "普通用户不能操作不可访问组织");
    }

    @Test
    void shouldExcludeDisabledAndExpiredTenantOrgsFromAccessibleCandidates() {
        TestRbacUser platformUser = new TestRbacUser(
                "U_SELF_AUDIT_PLATFORM",
                "self-audit-platform",
                null,
                "PLATFORM",
                Collections.emptyList(),
                100,
                "T1_ROOT",
                Arrays.asList(scope("_ALL_", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild),
                        scope("_NONE_", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );
        StubRbacBaseService scopedService = new StubRbacBaseService(platformUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new ExpiredTestTenant("T2", "Tenant2")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("T1_ROOT", null, "T1", "T1Root"),
                        new DisabledTestOrg("T1_DISABLED", null, "T1", "T1Disabled"),
                        new TestOrg("T2_ROOT", null, "T2", "T2Root"),
                        new TestOrg("PUBLIC_ROOT", null, null, "PublicRoot"),
                        new DisabledTestOrg("PUBLIC_DISABLED", null, null, "PublicDisabled")
                ));

        assertIterableEquals(Collections.singletonList("T1"),
                scopedService.loadUserAccessibleTenantList(platformUser, true).stream()
                        .map(tenant -> Objects.toString(tenant.getId(), ""))
                        .collect(Collectors.toList()),
                "过期租户不能进入可访问租户列表");
        assertIterableEquals(Arrays.asList("T1_ROOT", "PUBLIC_ROOT"),
                scopedService.loadUserOrgList(platformUser, false).stream()
                        .map(org -> Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()),
                "禁用组织和过期租户下的组织不能进入平台用户的数据范围");

        TestRbacUser topSuperAdmin = new TestRbacUser(
                "U_SELF_AUDIT_TOP",
                RbacUserInfo.TOP_SA_ACCOUNT_NAME,
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                1
        );

        assertIterableEquals(Collections.singletonList("T1"),
                scopedService.loadUserAccessibleTenantList(topSuperAdmin, true).stream()
                        .map(tenant -> Objects.toString(tenant.getId(), ""))
                        .collect(Collectors.toList()),
                "TopSuperAdmin 也不能取得过期租户");
        assertIterableEquals(Arrays.asList("T1_ROOT", "PUBLIC_ROOT"),
                scopedService.loadUserOrgList(topSuperAdmin, false).stream()
                        .map(org -> Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()),
                "TopSuperAdmin 的最大候选组织集也应排除 selfAudit 未通过的对象");

        TestRbacUser tenantAdmin = new TestRbacUser(
                "U_SELF_AUDIT_ADMIN",
                "self-audit-admin",
                "T1",
                "OPS",
                Collections.singletonList(RbacRoleInfo.ADMIN_ROLE),
                100,
                "T1_ROOT",
                List.of(scope("_DEFAULT_", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );
        assertDoesNotThrow(() -> scopedService.checkOrgAccessible(tenantAdmin, "T1", null, "T1_ROOT"));
        assertThrows(IllegalArgumentException.class,
                () -> scopedService.checkOrgAccessible(tenantAdmin, "T1", null, "T1_DISABLED"),
                "即使租户管理员绕过组织范围，仍不能操作 selfAudit 未通过的目标组织");
    }

    @Test
    void shouldApplyResourcePermissionTenantScopeOrgScopeAndConfidentialLevelAsIndependentGates() {
        List<TestTenant> tenantList = Arrays.asList(
                new TestTenant("T1", "Tenant1", 10),
                new TestTenant("T2", "Tenant2", 20)
        );
        List<TestOrg> orgList = Arrays.asList(
                new TestOrg("T1_ROOT", null, "T1", "T1Root", 10),
                new TestOrg("T1_SALES", "T1_ROOT", "T1", "T1Sales", 20),
                new TestOrg("T2_ROOT", null, "T2", "T2Root", 10),
                new TestOrg("T2_FINANCE", "T2_ROOT", "T2", "T2Finance", 20),
                new TestOrg("PUBLIC_ROOT", null, null, "PublicRoot", 30)
        );

        ResConditionActionObject readAtLevel50 = new ResConditionActionObject()
                .action("read")
                .confidentialLevel(50);
        ResConditionActionObject readAtLevel51 = new ResConditionActionObject()
                .action("read")
                .confidentialLevel(51);

        TestRbacUser tenantUser = new TestRbacUser(
                "U_SCOPE_T1",
                "tenant-scope-user",
                "T1",
                "OPS",
                Collections.singletonList("R_TENANT_REPORTER"),
                null,
                "T1_ROOT",
                Collections.singletonList(scope("_DEFAULT_", "T1_ROOT", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );
        StubRbacBaseService tenantService = new StubRbacBaseService(tenantUser)
                .setTenantList(tenantList)
                .setOrgList(orgList);
        tenantService.registerRole(new TestRbacRole(
                "R_SCOPE_T1",
                "R_TENANT_REPORTER",
                "T1",
                Collections.singletonList("sys:report:*:read"),
                Collections.emptyList(),
                50
        ));
        TestAuthorizeService tenantAuthorizeService = new TestAuthorizeService();
        tenantAuthorizeService.setRbacBaseService(tenantService);

        assertEquals(50, tenantService.getUserConfidentialDataAccessLevel(tenantUser),
                "用户未设置密级时应取生效角色授予的最高密级");
        assertIterableEquals(Collections.singletonList("T1"),
                tenantService.loadUserAccessibleTenantList(tenantUser, true).stream()
                        .map(tenant -> Objects.toString(tenant.getId(), ""))
                        .collect(Collectors.toList()),
                "租户用户的 DEFAULT_TENANT scope 只能枚举自身租户");
        assertIterableEquals(Arrays.asList("T1_ROOT", "T1_SALES"),
                tenantService.loadUserOrgList(tenantUser, false).stream()
                        .map(org -> Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()),
                "单租户 All scope 应只展开指定根组织的子树");
        assertDoesNotThrow(() -> tenantService.checkOrgAccessible(tenantUser, "T1", "T1_ROOT", "T1_SALES"));
        assertThrows(IllegalArgumentException.class,
                () -> tenantService.checkOrgAccessible(tenantUser, "T2", "T2_ROOT", "T2_FINANCE"),
                "组织访问必须受用户租户边界限制");
        assertTrue(tenantAuthorizeService.isAuthorized(tenantUser, "sys:report:*", readAtLevel50),
                "资源权限和动作密级都通过时应授权");
        assertFalse(tenantAuthorizeService.isAuthorized(tenantUser, "sys:report:*", readAtLevel51),
                "动作密级是独立前置门槛，不能由权限表达式绕过");

        TestRbacUser platformUser = new TestRbacUser(
                "U_SCOPE_PLATFORM",
                "platform-scope-user",
                null,
                "PLATFORM",
                Collections.singletonList("R_PLATFORM_REPORTER"),
                null,
                "T1_ROOT",
                Arrays.asList(scope("_ALL_", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild),
                        scope("_NONE_", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );
        StubRbacBaseService platformService = new StubRbacBaseService(platformUser)
                .setTenantList(tenantList)
                .setOrgList(orgList);
        platformService.registerRole(new TestRbacRole(
                "R_SCOPE_PLATFORM",
                "R_PLATFORM_REPORTER",
                null,
                Collections.singletonList("sys:report:*:read"),
                Collections.emptyList(),
                50
        ));

        assertTrue(platformService.canAccessAllOrg(platformUser),
                "平台用户允许 ALL_TENANT + ALL_ROOT_ORG + All 时应被识别为全租户全组织范围");
        assertIterableEquals(Arrays.asList("T1", "T2"),
                platformService.loadUserAccessibleTenantList(platformUser, true).stream()
                        .map(tenant -> Objects.toString(tenant.getId(), ""))
                        .collect(Collectors.toList()),
                "ALL_TENANT scope 应枚举所有租户");
        assertIterableEquals(Arrays.asList("T1_ROOT", "T1_SALES", "T2_ROOT", "T2_FINANCE", "PUBLIC_ROOT"),
                platformService.loadUserOrgList(platformUser, false).stream()
                        .map(org -> Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()),
                "ALL_TENANT 的组织范围应包含所有租户组织和公共组织");

        TestRbacUser topSuperAdmin = new TestRbacUser(
                "U_SCOPE_TOP",
                RbacUserInfo.TOP_SA_ACCOUNT_NAME,
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                1
        );
        StubRbacBaseService topSuperAdminService = new StubRbacBaseService(topSuperAdmin)
                .setTenantList(tenantList)
                .setOrgList(orgList);
        TestAuthorizeService topSuperAdminAuthorizeService = new TestAuthorizeService();
        topSuperAdminAuthorizeService.setRbacBaseService(topSuperAdminService);

        assertIterableEquals(Arrays.asList("T1", "T2"),
                topSuperAdminService.loadUserAccessibleTenantList(topSuperAdmin, true).stream()
                        .map(tenant -> Objects.toString(tenant.getId(), ""))
                        .collect(Collectors.toList()),
                "TopSuperAdmin 应跳过租户范围过滤");
        assertIterableEquals(Arrays.asList("T1_ROOT", "T1_SALES", "T2_ROOT", "T2_FINANCE", "PUBLIC_ROOT"),
                topSuperAdminService.loadUserOrgList(topSuperAdmin, false).stream()
                        .map(org -> Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()),
                "TopSuperAdmin 应跳过组织范围和对象密级过滤，拿到最大候选组织集");
        assertTrue(topSuperAdminAuthorizeService.isAuthorized(topSuperAdmin, "sys:report:*", readAtLevel51),
                "TopSuperAdmin 应跳过资源权限与动作密级校验");
    }

    @Test
    void shouldDetectAllOrgAccessFromMergedDataScope() {
        ScopeUser scopedUser = new ScopeUser(null, List.of());
        scopedUser.fields[0] = Set.of("_ALL_");
        scopedUser.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser);

        assertTrue(scopedService.canAccessAllOrg(scopedUser),
                "允许所有租户的所有组织时，应快速判定为可访问全部组织");
    }

    @Test
    void shouldKeepGlobalAllOrgCheckFalseWhenPlatformUserHasTenantDeny() {
        ScopeUser scopedUser = new ScopeUser(null, List.of());
        scopedUser.fields[0] = Set.of("_ALL_");
        scopedUser.fields[1] = Set.of("T2");
        scopedUser.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("A", null, "T1", "A"),
                        new TestOrg("C", null, "T2", "C")
                ));

        assertFalse(scopedService.canAccessAllOrg(scopedUser),
                "平台用户存在任一租户 deny 时，不应被公开 canAccessAllOrg 判断为全局所有组织");
        assertIterableEquals(Collections.singletonList("T1:A"),
                scopedService.loadUserOrgList(scopedUser, false).stream()
                        .map(org -> Objects.toString(org.getTenantId(), "") + ":" + Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()),
                "按租户计算时，T1 仍可全量直返，T2 被 deny 移除");
    }

    @Test
    void shouldCheckConfidentialLevelWhenSuperAdminAccessesCrossTenantOrg() {
        TestRbacUser superAdmin = new TestRbacUser(
                "U3025_SA",
                "sa-helper",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                50
        );

        StubRbacBaseService superService = new StubRbacBaseService(superAdmin)
                .setTenantList(Collections.singletonList(new TestTenant("T2", "Tenant2", 10)))
                .setOrgList(Collections.singletonList(new TestOrg("B", null, "T2", "B", 20)));

        assertDoesNotThrow(() -> superService.checkOrgAccessible(superAdmin, "T2", null, "B"),
                "普通超级管理员跨租户访问组织时，应仅按机密级别校验");
    }

    @Test
    void shouldRejectSuperAdminAccessWhenOrgConfidentialLevelIsTooHigh() {
        TestRbacUser superAdmin = new TestRbacUser(
                "U3026_SA",
                "sa-helper",
                null,
                "PLATFORM",
                Collections.singletonList(RbacRoleInfo.SA_ROLE),
                50
        );

        StubRbacBaseService superService = new StubRbacBaseService(superAdmin)
                .setTenantList(Collections.singletonList(new TestTenant("T2", "Tenant2", 10)))
                .setOrgList(Collections.singletonList(new TestOrg("B", null, "T2", "B", 100)));

        assertThrows(IllegalArgumentException.class, () -> superService.checkOrgAccessible(superAdmin, "T2", null, "B"),
                "普通超级管理员跨租户访问组织时，若机密级别不足应拒绝");
    }

    @Test
    void shouldTreatDefaultTenantAsPublicOrgForSaasUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U303",
                "saas-user",
                null,
                "SAAS",
                Collections.emptyList(),
                5000,
                "P",
                Collections.singletonList(scope("_DEFAULT_", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(Arrays.asList(
                        new TestOrg("P", null, null, "Public"),
                        new TestOrg("P1", "P", null, "PublicChild"),
                        new TestOrg("A", null, "T1", "A")
                ));

        assertIterableEquals(Arrays.asList("P", "P1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "无租户用户下，DEFAULT_TENANT 应等同公共组织");
    }

    @Test
    void shouldIgnoreNoTenantOrgScopeForTenantUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U304",
                "tenant-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("", "_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("T1", "Tenant1")))
                .setOrgList(Arrays.asList(
                        new TestOrg("P", null, null, "Public"),
                        new TestOrg("P1", "P", null, "PublicChild"),
                        new TestOrg("A", null, "T1", "A"),
                        new TestOrg("A1", "A", "T1", "A1")
                ));

        assertTrue(scopedService.loadUserOrgList(scopedUser, false).isEmpty(),
                "普通租户用户配置无租户 OrgScope 时，应忽略该 scope，不应拿到公共组织");
    }

    @Test
    void shouldSupportGroovyCustomOrgScope() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U31",
                "eve",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "return _org.name == 'A2' || _relativeIdPath == '/A2/A21/'", DataScope.OrgMatchingMode.Groovy))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A2", "A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义 Groovy 表达式应支持通过 _org 和相对路径过滤组织");
    }

    @Test
    void shouldSupportGroovyBooleanCustomOrgScope() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U32",
                "frank",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "_org.name == 'A1' || _relativeNamePath == '/A2/'", DataScope.OrgMatchingMode.Groovy))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义 Groovy 表达式应支持 _org 和上下文变量");
    }

    @Test
    void shouldDistinguishNamePathFromIdPath() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321",
                "helen",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/A2/", DataScope.OrgMatchingMode.NamePath))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "NamePath 应基于名称路径匹配，而不是基于组织 ID 路径匹配");
    }

    @Test
    void shouldMatchOnlyDirectChildrenForStandardTrailingSingleWildcardScopeExpression() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321_DIRECT_PATH",
                "direct-path-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.DirectChild))
        );

        Collection<TestOrg> orgList = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree())
                .loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "标准 /*/ 应只匹配一级子节点，不包含本节点或二级节点");
    }

    @Test
    void shouldKeepTrailingSlashSemanticsForCustomPathPattern() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321_CUSTOM_DIRECT_PATH",
                "custom-direct-path-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/*/", DataScope.OrgMatchingMode.IdPath))
        );

        Collection<TestOrg> orgList = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree())
                .loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "Custom 的 /*/ 应保留原始 PathPattern 尾斜杠语义，只匹配一级子节点");
    }

    @Test
    void shouldMatchSelfAndDirectChildrenForStandardSingleWildcardScopeExpression() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321_SELF_AND_DIRECT_PATH",
                "self-and-direct-path-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("A", true, DataScope.OrgMatchingMode.SelfAndDirectChild))
        );

        Collection<TestOrg> orgList = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree())
                .loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A", "A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "标准 /* 应匹配本节点和一级子节点，不包含二级节点");
    }

    @Test
    void shouldMatchOnlySecondLevelIdPathsWithTwoSegments() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321_ID_PATH",
                "id-path-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/*/*", DataScope.OrgMatchingMode.IdPath))
        );

        Collection<TestOrg> orgList = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree())
                .loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "/*/* 不应把一级组织尾随的空路径段当作第二段");
    }

    @Test
    void shouldMatchOnlySecondLevelNamePathsWithTwoSegments() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321_NAME_PATH",
                "name-path-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/*/*", DataScope.OrgMatchingMode.NamePath))
        );

        Collection<TestOrg> orgList = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree())
                .loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "NamePath 的 /*/* 也不应匹配一级组织");
    }

    @Test
    void shouldKeepCustomPathPatternInsideConfiguredScopeRoot() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321_SCOPE_ROOT_BOUNDARY",
                "scope-root-boundary-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/**", DataScope.OrgMatchingMode.IdPath))
        );

        Collection<TestOrg> orgList = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree())
                .loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A", "A1", "A2", "A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "Custom PathPattern 必须只在配置组织根节点的子树内匹配，不能扩展到兄弟根组织 B");
    }

    @Test
    void shouldUseScopeRootRelativePathInsteadOfAbsolutePath() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U322",
                "iris",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/A2/A21/", DataScope.OrgMatchingMode.IdPath))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "IdPath 应以 DataScope.OrgScope.startOrg() 作为起点构造相对路径");

        TestRbacUser absolutePathUser = new TestRbacUser(
                "U323",
                "jack",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/A/A2/A21/", DataScope.OrgMatchingMode.IdPath))
        );

        Collection<TestOrg> absolutePathResult = new StubRbacBaseService(absolutePathUser)
                .setOrgList(baseOrgTree())
                .loadUserOrgList(absolutePathUser, false);

        assertTrue(absolutePathResult.isEmpty(),
                "把 scope 根节点也写进表达式时不应命中，因为路径是相对而不是绝对");
    }

    @Test
    void shouldExposeUserInCustomScopeContext() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U33",
                "grace",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "_user.loginName == 'grace' && _org.name == 'A1'", DataScope.OrgMatchingMode.Groovy))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A1"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义表达式上下文应暴露当前用户变量 _user");
    }

    @Test
    void shouldAssembleOrgTreeWithinSpecifiedRoot() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U4",
                "dylan",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("_ALL_ROOT_", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        List<TestOrg> tree = new ArrayList<>(scopedService.loadUserOrgList(scopedUser, true, "A"));
        Collection<TestOrg> rootChildren = tree.get(0).getChildren();

        assertEquals(1, tree.size(), "限制根节点后只应返回指定根节点的树");
        assertEquals("A", tree.get(0).getId());
        assertIterableEquals(Arrays.asList("A1", "A2"),
                rootChildren.stream().map(TestOrg::getId).collect(Collectors.toList()));
        TestOrg a2 = rootChildren.stream()
                .filter(org -> "A2".equals(org.getId()))
                .findFirst()
                .orElseThrow();
        assertIterableEquals(Collections.singletonList("A21"),
                a2.getChildren().stream().map(org -> org.getId()).collect(Collectors.toList()));
    }

    @Test
    void shouldAssembleTreeByCopyingProxyWrappedOrg() {
        TestOrg original = new TestOrg("A", null, "T1", "A");
        TestOrg originalChild = new TestOrg("A1", "A", "T1", "A1");

        TestOrg readonlyRoot = ObjectWrapperUtils.wrapper2Readonly(original);
        TestOrg readonlyChild = ObjectWrapperUtils.wrapper2Readonly(originalChild);

        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        List<TestOrg> tree = new ArrayList<>(scopedService.assembleOrgTree(Arrays.asList(readonlyRoot, readonlyChild), "A"));

        assertEquals(1, tree.size(), "指定根节点后应返回复制后的根节点");
        assertNotSame(readonlyRoot, tree.get(0), "树节点应当是复制对象，而不是输入的只读代理对象");
        assertEquals("/A/", tree.get(0).getNodePath(), "复制节点应回填 nodePath");
        assertEquals(1, tree.get(0).getChildren().size(), "复制后的根节点应包含子节点");
        assertNotSame(readonlyChild, tree.get(0).getChildren().iterator().next(), "子节点也应为复制对象");
        assertTrue(original.getChildren().isEmpty(), "组树过程不能污染原始对象");
    }

    @Test
    void shouldSkipNodePathConstructionWhenDisabled() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        List<TestOrg> tree = new ArrayList<>(scopedService.assembleOrgTree(baseOrgTree(), false, "A"));

        assertEquals(1, tree.size(), "应正常返回指定根节点的树");
        assertNull(tree.get(0).getNodePath(), "关闭 nodePath 构建时不应补写 nodePath");
    }

    @Test
    void shouldAssembleOrgTreeWhenChildrenPropertyIsSet() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        SetOrg root = new SetOrg("A", null, "T1", "A");
        SetOrg child = new SetOrg("A1", "A", "T1", "A1");

        List<SetOrg> tree = new ArrayList<>(scopedService.assembleOrgTree(Arrays.asList(root, child), false, "A"));

        assertEquals(1, tree.size(), "Set 类型 children 也应能正常组树");
        assertNotSame(root, tree.get(0), "组树返回的根节点应为复制对象");
        assertInstanceOf(Set.class, tree.get(0).getChildren(), "复制节点的 children 应保持 Set 类型");
        assertEquals(1, tree.get(0).getChildren().size(), "根节点应挂载子节点");
        assertEquals("A1", tree.get(0).getChildren().iterator().next().getId());
        assertTrue(root.getChildren().isEmpty(), "组树过程不能污染原始 Set children");
    }

    @Test
    void shouldAssembleMultiLevelTreeWhenChildrenPropertyIsSet() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        SetOrg root = new SetOrg("A", null, "T1", "A");
        SetOrg child = new SetOrg("A1", "A", "T1", "A1");
        SetOrg grandChild = new SetOrg("A11", "A1", "T1", "A11");

        List<SetOrg> tree = new ArrayList<>(scopedService.assembleOrgTree(Arrays.asList(root, child, grandChild), false, "A"));

        assertEquals(1, tree.size(), "Set 类型 children 多层级也应正常组树");
        SetOrg copiedChild = (SetOrg) tree.get(0).getChildren().iterator().next();
        assertEquals("A1", copiedChild.getId());
        assertInstanceOf(Set.class, copiedChild.getChildren(), "子节点的 children 也应保持 Set 类型");
        assertEquals("A11", copiedChild.getChildren().iterator().next().getId(),
                "多层级组织树不应只保留第一层 children");
    }

    @Test
    void shouldAssembleOrgTreePerTenantWhenOrgIdsOverlap() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U_TREE_TENANT_DUP",
                "tree-tenant-dup",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "ROOT",
                Arrays.asList(
                        customScope("T1", "_ALL_ROOT_", true, "_org.tenantId == 'T1'", DataScope.OrgMatchingMode.Groovy),
                        scope("T2", "ROOT", true, DataScope.OrgMatchingMode.Self)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2")
                ))
                .setOrgList(Arrays.asList(
                        new TestOrg("ROOT", null, "T1", "T1Root"),
                        new TestOrg("CHILD", "ROOT", "T1", "T1Child"),
                        new TestOrg("ROOT", null, "T2", "T2Root"),
                        new TestOrg("CHILD", "ROOT", "T2", "T2Child")
                ));

        List<TestOrg> tree = new ArrayList<>(scopedService.loadUserOrgList(scopedUser, true));

        assertEquals(2, tree.size(), "不同租户组织 ID 重复时，不应在组树阶段互相覆盖");
        assertIterableEquals(Arrays.asList("T1:ROOT", "T2:ROOT"),
                tree.stream()
                        .map(org -> Objects.toString(org.getTenantId(), "") + ":" + Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()));

        TestOrg t1Root = tree.stream()
                .filter(org -> Objects.equals(org.getTenantId(), "T1"))
                .findFirst()
                .orElseThrow();
        TestOrg t2Root = tree.stream()
                .filter(org -> Objects.equals(org.getTenantId(), "T2"))
                .findFirst()
                .orElseThrow();

        assertIterableEquals(Collections.singletonList("T1:CHILD"),
                t1Root.getChildren().stream()
                        .map(org -> Objects.toString(org.getTenantId(), "") + ":" + Objects.toString(org.getId(), ""))
                        .collect(Collectors.toList()),
                "T1 的子节点应挂在 T1 根节点下");
        assertTrue(t2Root.getChildren().isEmpty(),
                "T2 只授权 ROOT 本节点时，不应因为 T1 同名 CHILD 被错误挂载");
    }

    @Test
    void shouldAssembleLargeOrgTreeWithinReasonableTime() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);
        List<TestOrg> orgList = largeLayeredOrgTree("ROOT", "T1", 50000, 100);

        List<TestOrg> tree = assertTimeout(Duration.ofSeconds(2),
                () -> new ArrayList<>(scopedService.assembleOrgTree(orgList, true, "ROOT")),
                "50000 个组织节点、100 个层级的组树应在 2 秒内完成");

        assertEquals(1, tree.size(), "大组织树应返回一个根节点");
        assertEquals(50000, countTreeNodes(tree), "组树后节点总数应完整保留");
        assertEquals("/ROOT/", tree.get(0).getNodePath(), "根节点应正常构建 nodePath");
    }

    @Test
    void shouldLoadUserAccessibleOrgListWithinReasonableTime() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U_PERF",
                "perf-user",
                "T1",
                "OPS",
                Collections.emptyList(),
                5000,
                "ROOT",
                Collections.singletonList(scope("ROOT", true, DataScope.OrgMatchingMode.SelfAndAllChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("T1", "Tenant1")))
                .setOrgList(largeLayeredOrgTree("ROOT", "T1", 50000, 100));

        Collection<TestOrg> orgList = assertTimeout(Duration.ofSeconds(2),
                () -> scopedService.loadUserAccessibleOrgList(scopedUser, true),
                "50000 个组织节点、100 个层级的用户可访问组织计算应在 2 秒内完成");

        assertEquals(50000, orgList.size(), "允许根组织全部子树时，应返回完整组织集合");
    }

    @Test
    void shouldThrowExceptionWhenCycleDetectedAndNodePathDisabled() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> scopedService.assembleOrgTree(Arrays.asList(
                        new TestOrg("A", "A1", "T1", "A"),
                        new TestOrg("A1", "A", "T1", "A1")
                ), false, "A"));

        assertTrue(ex.getMessage().contains("循环引用"),
                "即使关闭 nodePath 构建，父链出现环也应抛出循环引用异常");
    }

    @Test
    void shouldThrowExceptionWhenCycleDetectedWhileAssemblingTree() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> scopedService.assembleOrgTree(Arrays.asList(
                        new TestOrg("A", "A1", "T1", "A"),
                        new TestOrg("A1", "A", "T1", "A1")
                ), "A"));

        assertTrue(ex.getMessage().contains("循环引用"),
                "组装组织树时如果父链出现环，应抛出循环引用异常");
    }

    @Test
    void shouldKeepDifferentExpressionModesWhenMergingRoles() {
        TestRbacUser u = new TestRbacUser("merge", "merge", "T1", "OPS", Arrays.asList("R1", "R2"), 5000);
        StubRbacBaseService service = new StubRbacBaseService(u);
        service.registerRole(new TestRbacRole("r1", "R1", "T1", List.of(), List.of(), 10,
                List.of(customScope("A", true, "/A2/", DataScope.OrgMatchingMode.IdPath))));
        service.registerRole(new TestRbacRole("r2", "R2", "T1", List.of(), List.of(), 10,
                List.of(customScope("A", true, "/A2/", DataScope.OrgMatchingMode.NamePath))));
        assertEquals(Set.of("A|IdPath#/A2/", "A|NamePath#/A2/"), service.getUserDataScope(u).getOrgScopeList());
    }

    @Test
    void shouldMergeTenantAndOrgFieldsIndependentlyAndDeduplicate() {
        TestRbacUser u = new TestRbacUser("merge", "merge", null, "PLATFORM", Arrays.asList("R1", "R2"), 5000);
        StubRbacBaseService service = new StubRbacBaseService(u);
        for (String tenant : List.of("T1", "T2")) {
            String role = tenant.equals("T1") ? "R1" : "R2";
            service.registerRole(new TestRbacRole(role, role, null, List.of(), List.of(), 10,
                    List.of(customScope(tenant, "A", true, "/A2/", DataScope.OrgMatchingMode.IdPath))));
        }
        assertEquals(Set.of("T1", "T2"), service.getUserDataScope(u).getTenantScopeList());
        assertEquals(Set.of("A|IdPath#/A2/"), service.getUserDataScope(u).getOrgScopeList());
    }

    @Test
    void shouldKeepStandardModeSeparateFromCustomExpressions() {
        assertEquals("DirectChild", DataScope.OrgScope.parse("A|DirectChild").orgMatchingMode());
        assertEquals("IdPath#/*/", DataScope.OrgScope.parse("A|IdPath#/*/").orgMatchingMode());
        assertThrows(IllegalArgumentException.class, () -> DataScope.OrgScope.parse("A|SpringEL#true"));
    }

    @Test
    void shouldReplaceEachRoleFieldWhenUserDefinesEmptyOrNonEmptyCollection() {
        for (int field = 0; field < 6; field++) {
            ScopeUser u = new ScopeUser("T1", List.of("R1", "R2"));
            StubRbacBaseService service = new StubRbacBaseService(u);
            ScopeRole r1 = new ScopeRole("R1"), r2 = new ScopeRole("R2");
            String one = field >= 4 ? "role-one|Self" : "role-one";
            String two = field >= 4 ? "role-two|Self" : "role-two";
            String own = field >= 4 ? "user-only|Self" : "user-only";
            r1.fields[field] = Set.of(one);
            r2.fields[field] = Set.of(two, one);
            service.registerRole(r1);
            service.registerRole(r2);
            assertEquals(Set.of(one, two), scopeFields(service.getUserDataScope(u)).get(field),
                    "null must inherit field " + field);
            u.fields[field] = Set.of();
            assertEquals(Set.of(), scopeFields(service.getUserDataScope(u)).get(field),
                    "empty must replace field " + field);
            u.fields[field] = Set.of(own);
            assertEquals(Set.of(own), scopeFields(service.getUserDataScope(u)).get(field),
                    "nonempty must replace field " + field);
        }
    }

    @Test
    void shouldReplaceDenyIndependentlyWithoutReplacingInheritedAllow() {
        ScopeUser u = new ScopeUser("T1", List.of("R1"));
        ScopeRole role = new ScopeRole("R1");
        role.fields[0] = Set.of("T1");
        role.fields[1] = Set.of("T1");
        u.fields[1] = Set.of();
        StubRbacBaseService service = new StubRbacBaseService(u);
        service.registerRole(role);
        assertTrue(service.canAccessTenant(u, "T1"));
        assertEquals(Set.of("T1"), service.getUserDataScope(u).getTenantScopeList());
        assertEquals(Set.of(), service.getUserDataScope(u).getDeniedTenantScopeList());
    }

    @Test
    void shouldKeepOrganizationDenialOutOfTenantAuthorization() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        u.fields[5] = Set.of("A|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree())
                .setTenantList(List.of(new TestTenant("T1", "Tenant1")));
        assertTrue(service.canAccessTenant(u, "T1"));
        assertEquals(List.of("T1"), service.loadUserAccessibleTenantList(u, true).stream()
                .map(t -> Objects.toString(t.getId())).collect(Collectors.toList()));
        assertFalse(service.canAccessOrg(u, "T1", "A1"));
        assertTrue(service.canAccessOrg(u, "T1", "B1"));
        Set<String> listed = service.loadUserAccessibleOrgList(u, true).stream()
                .map(o -> Objects.toString(o.getId())).collect(Collectors.toSet());
        for (TestOrg org : baseOrgTree()) {
            assertEquals(listed.contains(org.getId()), service.canAccessOrg(u, "T1", org.getId()));
        }
    }

    @Test
    void shouldEnforcePlatformBoundaryForAllExplicitNoneAndGroovyRules() {
        for (String expression : List.of("_ALL_", "T2", "_NONE_", "Groovy#true")) {
            ScopeUser u = new ScopeUser("T1", List.of("R1"));
            ScopeRole role = new ScopeRole("R1");
            role.fields[0] = Set.of(expression);
            StubRbacBaseService service = new StubRbacBaseService(u)
                    .setTenantList(List.of(new TestTenant("T1", "One"), new TestTenant("T2", "Two")));
            service.registerRole(role);
            assertFalse(service.canAccessTenant(u, "T2"), expression);
            assertFalse(service.canAccessTenant(u, null), expression);
            u.fields[0] = Set.of(expression);
            assertFalse(service.canAccessTenant(u, "T2"), expression);
            assertFalse(service.canAccessTenant(u, null), expression);
        }
    }

    @Test
    void shouldApplyAllAndNoneTenantAndOrganizationGrants() {
        ScopeUser u = new ScopeUser(null, List.of());
        StubRbacBaseService service = new StubRbacBaseService(u);
        u.fields[0] = Set.of("_ALL_");
        assertTrue(service.canAccessTenant(u, null));
        u.fields[0] = Set.of("_NONE_");
        assertTrue(service.canAccessTenant(u, null));
        assertFalse(service.canAccessOrg(u, null, null));
        u.fields[4] = Set.of("_NONE_|ignored");
        assertTrue(service.canAccessOrg(u, null, null));
        u.fields[5] = Set.of("_NONE_|ignored");
        assertFalse(service.canAccessOrg(u, null, null));
    }

    @Test
    void shouldApplyDomainAllowAndDenyWithoutWildcardExpansion() {
        ScopeUser u = new ScopeUser("T1", List.of());
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")));
        assertFalse(service.canAccessDomain(u, "sales"));
        u.fields[2] = Set.of("sales", "finance");
        u.fields[3] = Set.of("finance");
        assertTrue(service.canAccessDomain(u, "sales"));
        assertFalse(service.canAccessDomain(u, "finance"));
        assertFalse(service.canAccessDomain(u, "other"));
        u.fields[3] = Set.of();
        assertTrue(service.canAccessDomain(u, "finance"));
    }

    @Test
    void shouldIgnoreDisabledRoleScopesForAllSixFields() {
        ScopeUser u = new ScopeUser("T1", List.of("active", "disabled"));
        ScopeRole active = new ScopeRole("active");
        ScopeRole disabled = new ScopeRole("disabled") {
            @Override public boolean isEnable() { return false; }
        };
        for (int field = 0; field < 6; field++) {
            active.fields[field] = Set.of(field >= 4 ? "A|Self" : "active");
            disabled.fields[field] = Set.of(field >= 4 ? "B|Self" : "disabled");
        }
        StubRbacBaseService service = new StubRbacBaseService(u);
        service.registerRole(active);
        service.registerRole(disabled);
        List<Set<String>> resolved = scopeFields(service.getUserDataScope(u));
        for (int field = 0; field < 6; field++) {
            assertEquals(active.fields[field], resolved.get(field), "disabled role field " + field);
        }
    }

    @Test
    void shouldReturnScopeSnapshotWithoutMutatingSourceSets() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = new LinkedHashSet<>(Set.of("T1"));
        StubRbacBaseService service = new StubRbacBaseService(u);
        DataScope snapshot = service.getUserDataScope(u);
        assertThrows(UnsupportedOperationException.class, () -> snapshot.getTenantScopeList().clear());
        u.fields[0].clear();
        assertEquals(Set.of("T1"), snapshot.getTenantScopeList());
        assertEquals(Set.of(), service.getUserDataScope(u).getTenantScopeList());
    }

    @Test
    void shouldPreventTenantSuperAdminFromCrossingIdentityBoundary() {
        ScopeUser u = new ScopeUser("T1", List.of(RbacRoleInfo.SA_ROLE));
        u.fields[0] = Set.of("_ALL_", "_NONE_");
        u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild", "_NONE_|Self");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setTenantList(List.of(new TestTenant("T1", "One"), new TestTenant("T2", "Two")))
                .setOrgList(List.of(new TestOrg("A", null, "T1", "A"), new TestOrg("B", null, "T2", "B")));
        assertFalse(service.canAccessTenant(u, "T2"));
        assertFalse(service.canAccessTenant(u, null));
        assertFalse(service.canAccessOrg(u, "T2", "B"));
        assertEquals(List.of("T1"), service.loadUserAccessibleTenantList(u, true).stream()
                .map(t -> Objects.toString(t.getId())).collect(Collectors.toList()));
    }

    @Test
    void shouldKeepAllOrganizationClaimConsistentForOrphans() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(List.of(
                new TestOrg("ROOT", null, "T1", "Root"),
                new TestOrg("ORPHAN", "MISSING", "T1", "Orphan")));
        assertFalse(service.canAccessAllOrg(u, "T1"));
        assertFalse(service.canAccessOrg(u, "T1", "ORPHAN"));
        assertEquals(Set.of("ROOT"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(o -> Objects.toString(o.getId())).collect(Collectors.toSet()));
    }

    @Test
    void shouldExcludeDisconnectedCyclesFromAllRootOrganizationGrant() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(List.of(
                new TestOrg("ROOT", null, "T1", "Root"),
                new TestOrg("C1", "C2", "T1", "Cycle1"),
                new TestOrg("C2", "C1", "T1", "Cycle2")));
        assertFalse(service.canAccessAllOrg(u, "T1"));
        assertFalse(service.canAccessOrg(u, "T1", "C1"));
        assertFalse(service.canAccessOrg(u, "T1", "C2"));
        assertEquals(Set.of("ROOT"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(o -> Objects.toString(o.getId())).collect(Collectors.toSet()));
    }

    @Test
    void shouldTreatLegacyTenantWildcardAsLiteralId() {
        ScopeUser u = new ScopeUser(null, List.of());
        u.fields[0] = Set.of("tenant-*");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setTenantList(List.of(new TestTenant("tenant-a", "A"), new TestTenant("tenant-b", "B")));
        assertFalse(service.canAccessTenant(u, "tenant-a"));
        assertFalse(service.canAccessTenant(u, "tenant-b"));
        assertTrue(service.loadUserAccessibleTenantList(u, true).isEmpty());
    }

    @Test
    void shouldResolveDefaultOrganizationOnlyWithinUsersOwnTenant() {
        TestRbacUser u = new TestRbacUser("platform-default", "platform-default", null, "PLATFORM",
                List.of(), 5000, "ROOT", List.of(scope("_ALL_", "_DEFAULT_", true, DataScope.OrgMatchingMode.Self)));
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setTenantList(List.of(new TestTenant("T1", "One")))
                .setOrgList(List.of(new TestOrg("ROOT", null, "T1", "Other tenant root")));
        assertFalse(service.canAccessOrg(u, "T1", "ROOT"),
                "默认组织不得因其他租户恰好具有同ID而授权");
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
    }

    @Test
    void shouldRejectForeignOrganizationReturnedByTenantLoader() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("ROOT|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u) {
            @Override
            public <ORG extends RbacOrgInfo> List<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyEffect) {
                return (List<ORG>) (List<?>) List.of(new TestOrg("ROOT", null, "T2", "Foreign"));
            }
        };
        assertTrue(service.canAccessTenant(u, "T1"));
        assertFalse(service.canAccessOrg(u, "T1", "ROOT"));
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> service.checkOrgAccessible(u, "T1", "ROOT", null));
        assertTrue(error.getMessage().contains("不存在于租户"), error.getMessage());
    }

    @Test
    void shouldAllowPlatformScopedChildManagementWithoutGrantingRootManagement() {
        ScopeUser u = new ScopeUser(null, List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("ROOT|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setTenantList(List.of(new TestTenant("T1", "Tenant")))
                .setOrgList(List.of(new TestOrg("ROOT", null, "T1", "Root"),
                        new TestOrg("CHILD", "ROOT", "T1", "Child")));
        assertDoesNotThrow(() -> service.checkOrgAccessible(u, "T1", "ROOT", "CHILD"));
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> service.checkOrgAccessible(u, "T1", null, "ROOT"));
        assertTrue(error.getMessage().contains("上级节点不能为空"), error.getMessage());
    }

    @Test
    void shouldRejectUnknownDisabledAndExpiredTenantsAtSingleAndListEntrypoints() {
        ScopeUser u = new ScopeUser(null, List.of());
        u.fields[0] = Set.of("_ALL_", "missing");
        StubRbacBaseService service = new StubRbacBaseService(u).setTenantList(List.of(
                new TestTenant("active", "Active"),
                new TestTenant("disabled", "Disabled") { @Override public boolean isEnable() { return false; } },
                new ExpiredTestTenant("expired", "Expired")));
        assertTrue(service.canAccessTenant(u, "active"));
        for (String tenant : List.of("missing", "disabled", "expired")) {
            assertFalse(service.canAccessTenant(u, tenant), tenant);
        }
        assertEquals(List.of("active"), service.loadUserAccessibleTenantList(u, true).stream()
                .map(t -> Objects.toString(t.getId())).collect(Collectors.toList()));
    }

    @Test
    void shouldSkipTenantAllowScriptWhenTenantIsStructurallyDenied() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("Groovy#throw new IllegalStateException('allow script must not execute')");
        u.fields[1] = Set.of("_ALL_");
        StubRbacBaseService service = new StubRbacBaseService(u);
        assertFalse(service.canAccessTenant(u, "T1"));
        assertTrue(service.loadUserAccessibleTenantList(u, true).isEmpty());
    }

    @Test
    void shouldMatchStructuralTenantDenialBeforeEarlierScript() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[1] = new LinkedHashSet<>(List.of(
                "Groovy#throw new IllegalStateException('deny script must not execute')", "_ALL_"));
        StubRbacBaseService service = new StubRbacBaseService(u);
        assertFalse(service.canAccessTenant(u, "T1"));
        assertTrue(service.loadUserAccessibleTenantList(u, true).isEmpty());
    }

    @Test
    void shouldAvoidTenantAndOrganizationEnumerationWhenAllTenantSpacesAreDenied() {
        ScopeUser u = new ScopeUser(null, List.of());
        u.fields[0] = Set.of("_ALL_", "_NONE_");
        u.fields[1] = Set.of("_ALL_", "_NONE_");
        u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        AtomicInteger tenantLoads = new AtomicInteger();
        AtomicInteger orgLoads = new AtomicInteger();
        StubRbacBaseService service = new StubRbacBaseService(u) {
            @Override
            public <TENANT extends RbacTenantInfo> Collection<TENANT> loadAllTenantList(boolean onlyEffect) {
                tenantLoads.incrementAndGet();
                return (Collection<TENANT>) (Collection<?>) List.of(new TestTenant("T1", "Tenant"));
            }
            @Override
            public <ORG extends RbacOrgInfo> List<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyEffect) {
                orgLoads.incrementAndGet();
                return (List<ORG>) (List<?>) List.of(new TestOrg("ROOT", null, tenantId == null ? null : tenantId.toString(), "Root"));
            }
        };
        assertTrue(service.loadUserAccessibleTenantList(u, true).isEmpty());
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
        assertEquals(0, tenantLoads.get(), "全租户及无租户均拒绝时，不应加载候选租户");
        assertEquals(0, orgLoads.get(), "全租户及无租户均拒绝时，不应加载组织");
    }

    @Test
    void shouldDenyLargeOrganizationTreeBeforeEvaluatingAllowScript() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|Groovy#throw new IllegalStateException('allow script must not execute')");
        u.fields[5] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setOrgList(largeLayeredOrgTree("ROOT", "T1", 50000, 100));
        Collection<RbacOrgInfo> allowed = assertTimeoutPreemptively(Duration.ofSeconds(2),
                () -> service.loadUserAccessibleOrgList(u, true),
                "50000 个节点、100 层的全组织拒绝应在 2 秒内完成，且不执行允许脚本");
        assertTrue(allowed.isEmpty());
    }

    @Test
    void shouldNotEvaluateAllowScriptForAlreadyDeniedOrganization() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|Groovy#if (_org.id == 'A1') { throw new IllegalStateException('denied leaf evaluated') }; return true");
        u.fields[5] = Set.of("A1|Self");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree());
        assertEquals(List.of("A", "A2", "A21", "B", "B1"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()));
        assertFalse(service.canAccessOrg(u, "T1", "A1"));
        assertTrue(service.canAccessOrg(u, "T1", "A2"));
    }

    @Test
    void shouldSkipOrganizationDenyScriptWhenAllowSetIsEmpty() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of();
        u.fields[5] = Set.of("_ALL_ROOT_|Groovy#throw new IllegalStateException('deny script must not execute')");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree());
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
        assertFalse(service.canAccessOrg(u, "T1", "A1"));
    }

    @Test
    void shouldSkipTenantDenyScriptWhenAllowSetIsEmpty() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of();
        u.fields[1] = Set.of("Groovy#throw new IllegalStateException('deny script must not execute')");
        StubRbacBaseService service = new StubRbacBaseService(u);
        assertFalse(service.canAccessTenant(u, "T1"));
        assertTrue(service.loadUserAccessibleTenantList(u, true).isEmpty());
    }

    @Test
    void shouldNotTreatAllRootDenialAsDenialOfExplicitlyAllowedOrphan() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("ORPHAN|Self");
        u.fields[5] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(List.of(
                new TestOrg("ROOT", null, "T1", "Root"),
                new TestOrg("CHILD", "ROOT", "T1", "Child"),
                new TestOrg("ORPHAN", "MISSING", "T1", "Orphan")));
        assertTrue(service.canAccessOrg(u, "T1", "ORPHAN"));
        assertFalse(service.canAccessOrg(u, "T1", "ROOT"));
        assertEquals(List.of("ORPHAN"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()));
    }

    @Test
    void shouldPreservePlatformAdministratorShortcutBeforeScopeScripts() {
        ScopeUser u = new ScopeUser(null, List.of(RbacRoleInfo.SA_ROLE));
        u.fields[0] = Set.of("Groovy#throw new IllegalStateException('admin allow script evaluated')");
        u.fields[1] = Set.of("_ALL_", "_NONE_");
        u.fields[4] = Set.of("_ALL_ROOT_|Groovy#throw new IllegalStateException('admin org script evaluated')");
        u.fields[5] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setTenantList(List.of(new TestTenant("T1", "Tenant")))
                .setOrgList(List.of(new TestOrg("ROOT", null, "T1", "Root")));
        assertTrue(service.canAccessTenant(u, "T1"));
        assertTrue(service.canAccessOrg(u, "T1", "ROOT"));
        assertEquals(1, service.loadUserAccessibleTenantList(u, true).size());
        assertEquals(1, service.loadUserAccessibleOrgList(u, true).size());
    }

    @Test
    void shouldSkipAllAllowEvaluationForSingleOrganizationAlreadyDenied() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|Groovy#throw new IllegalStateException('single denied target must not evaluate any allow node')");
        u.fields[5] = Set.of("A1|Self");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree());
        assertFalse(service.canAccessOrg(u, "T1", "A1"));
    }

    @Test
    void shouldMatchStructuralOrganizationDenialBeforeEarlierDenyScript() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|Groovy#throw new IllegalStateException('allow script must not execute')");
        u.fields[5] = new LinkedHashSet<>(List.of(
                "_ALL_ROOT_|Groovy#throw new IllegalStateException('earlier deny script must not execute')",
                "_ALL_ROOT_|SelfAndAllChild"));
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree());
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
        assertFalse(service.canAccessOrg(u, "T1", "A1"));
    }

    @Test
    void shouldMatchStructuralOrganizationAllowanceBeforeEarlierAllowScript() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = new LinkedHashSet<>(List.of(
                "_ALL_ROOT_|Groovy#throw new IllegalStateException('earlier allow script must not execute')",
                "_ALL_ROOT_|SelfAndAllChild"));
        u.fields[5] = Set.of();
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree());
        assertEquals(List.of("A", "A1", "A2", "A21", "B", "B1"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()));
        assertTrue(service.canAccessOrg(u, "T1", "A1"));
    }

    @Test
    void shouldEvaluateAllowScriptOnlyForRequestedOrganization() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|Groovy#if (_org.id != 'A2') { throw new IllegalStateException('non-target organization evaluated') }; return true");
        u.fields[5] = Set.of();
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree());
        assertTrue(service.canAccessOrg(u, "T1", "A2"));
    }

    @Test
    void shouldEvaluateTwentySameRootPathRulesOnLargeTreeWithinTwoSeconds() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = new LinkedHashSet<>();
        for (int rule = 0; rule < 20; rule++) {
            u.fields[4].add("ROOT|IdPath#/never-" + rule + "/**");
        }
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setOrgList(largeLayeredOrgTree("ROOT", "T1", 50000, 100));
        Collection<RbacOrgInfo> allowed = assertTimeoutPreemptively(Duration.ofSeconds(2),
                () -> service.loadUserAccessibleOrgList(u, true),
                "50000 个节点、100 层、20 条同起点路径规则应在 2 秒内完成");
        assertTrue(allowed.isEmpty(), "20 条路径都不命中，不得误授权任何组织");
    }

    @Test
    void shouldNotReadOrganizationNamesForIdPathMatching() {
        AtomicInteger nameReads = new AtomicInteger();
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("A|IdPath#/A1/");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(nameCountingOrgTree(nameReads));
        assertEquals(List.of("A1"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()));
        assertEquals(0, nameReads.get(), "IdPath 只依赖 ID，不应构造名称路径或读取组织名称");
    }

    @Test
    void shouldReuseNamePathsAcrossRulesSharingTheSameRoot() {
        AtomicInteger nameReads = new AtomicInteger();
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("A|NamePath#/never-one/**");
        List<TestOrg> tree = nameCountingOrgTree(nameReads);
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(tree);
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
        int singleRuleReads = nameReads.get();
        assertTrue(singleRuleReads > 0, "名称路径匹配必须实际读取名称，保证计数器覆盖求值过程");

        nameReads.set(0);
        u.fields[4] = new LinkedHashSet<>();
        for (int rule = 0; rule < 20; rule++) {
            u.fields[4].add("A|NamePath#/never-" + rule + "/**");
        }
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
        assertTrue(nameReads.get() <= singleRuleReads + tree.size(),
                "同起点20条规则只应构造一份名称路径；容许每节点额外读取一次，单规则读取="
                        + singleRuleReads + "，多规则读取=" + nameReads.get());
    }

    private static List<TestOrg> nameCountingOrgTree(AtomicInteger nameReads) {
        return baseOrgTree().stream().map(org -> new TestOrg(org.getId(), org.getParentId(), org.getTenantId(), org.getName()) {
            @Override
            public String getName() {
                nameReads.incrementAndGet();
                return super.getName();
            }
        }).collect(Collectors.toList());
    }

    @Test
    void shouldKeepInterleavedNamePathRulesRelativeToTheirOwnRoots() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = new LinkedHashSet<>(List.of(
                "A|NamePath#/Branch/First/",
                "B|NamePath#/Second/",
                "A|NamePath#/Branch/Third/"));
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(List.of(
                new TestOrg("A", null, "T1", "Root"),
                new TestOrg("B", "A", "T1", "Branch"),
                new TestOrg("L1", "B", "T1", "First"),
                new TestOrg("L2", "B", "T1", "Second"),
                new TestOrg("L3", "B", "T1", "Third")));
        assertEquals(List.of("L1", "L2", "L3"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()),
                "A→B→A切换起点时，同一候选节点的相对名称路径必须随起点重算");
    }

    @Test
    void shouldRebuildNamePathsWhenSourceNamesOrParentRelationshipsChangeBetweenCalls() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("ROOT|NamePath#/Visible/");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(List.of(
                new TestOrg("ROOT", null, "T1", "Root"),
                new TestOrg("LEAF", "ROOT", "T1", "Visible")));
        assertEquals(List.of("LEAF"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()));

        service.setOrgList(List.of(new TestOrg("ROOT", null, "T1", "Root"),
                new TestOrg("LEAF", "ROOT", "T1", "Renamed")));
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty(),
                "相同用户、规则和组织ID不变时，名称变更也必须立即影响授权");

        service.setOrgList(List.of(new TestOrg("ROOT", null, "T1", "Root"),
                new TestOrg("OTHER", null, "T1", "Other"),
                new TestOrg("LEAF", "OTHER", "T1", "Visible")));
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty(),
                "节点移到授权起点之外后，即使名称恢复也不能复用旧子树或路径授权");

        service.setOrgList(List.of(new TestOrg("ROOT", null, "T1", "Root"),
                new TestOrg("LEAF", "ROOT", "T1", "Visible")));
        assertEquals(List.of("LEAF"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()),
                "节点重新移回授权子树后应恢复授权，不缓存上一次的拒绝结果");
    }

    @Test
    void shouldEvaluateDenyScriptOnlyForRequestedOrganization() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("A2|Self");
        u.fields[5] = Set.of("_ALL_ROOT_|Groovy#if (_org.id != 'A2') { throw new IllegalStateException('non-target deny') }; return false");
        StubRbacBaseService service = new StubRbacBaseService(u).setOrgList(baseOrgTree());
        assertTrue(service.canAccessOrg(u, "T1", "A2"));
    }

    @Test
    void shouldLoadAccessibleDomainsOnceInSourceOrderAndForwardEffectFlag() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("sales", "finance");
        StubRbacBaseService service = new StubRbacBaseService(u).setDomainList(Arrays.asList(
                new TestDomain("finance"), null, new TestDomain("other"), new TestDomain("sales")));
        for (boolean onlyEffect : List.of(false, true)) {
            service.domainListLoads.set(0);
            assertEquals(List.of("finance", "sales"), service.loadUserAccessibleDomainList(u, onlyEffect).stream()
                    .map(domain -> Objects.toString(domain.getId())).collect(Collectors.toList()));
            assertEquals(onlyEffect, service.lastDomainEffectFlag);
            assertEquals(1, service.domainListLoads.get());
            assertEquals(0, service.domainLoads.get(), "枚举后的领域对象无需逐个再次loadDomain");
        }
        assertEquals("sales", service.loadDomain("sales").getId());
        assertEquals(1, service.domainLoads.get());
    }

    @Test
    void shouldDelegateDomainLoadersFromDefaultRoleHelper() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("sales");
        DefaultRoleHelperRbacBaseService service = new DefaultRoleHelperRbacBaseService(u);
        service.delegate.setDomainList(List.of(new TestDomain("sales")));
        assertEquals("sales", service.loadDomain("sales").getId());
        assertEquals(1, service.loadAllDomainList(false).size());
        assertFalse(service.delegate.lastDomainEffectFlag);
        assertEquals(1, service.loadUserAccessibleDomainList(u, true).size());
        assertTrue(service.delegate.lastDomainEffectFlag);
    }

    @Test
    void shouldTreatNullDomainEnumerationAsEmpty() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("sales");
        StubRbacBaseService service = new StubRbacBaseService(u) {
            @Override public <DOMAIN extends RbacDomainInfo> Collection<DOMAIN> loadAllDomainList(boolean onlyEffect) {
                return null;
            }
        };
        assertTrue(service.loadUserAccessibleDomainList(u, true).isEmpty());
    }

    @Test
    void shouldRejectUnavailableDomainObjectsAtSingleAndListEntrypoints() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("active", "disabled", "deleted", "expired", "missing");
        StubRbacBaseService service = new StubRbacBaseService(u).setDomainList(Arrays.asList(
                new TestDomain("active"),
                new TestDomain("disabled") { @Override public boolean isEnable() { return false; } },
                new TestDomain("deleted") { @Override public boolean isDeleted() { return true; } },
                new TestDomain("expired") {
                    @Override public java.time.LocalDateTime getExpiredTime() { return java.time.LocalDateTime.now().minusDays(1); }
                },
                new TestDomain(null), null));
        assertTrue(service.canAccessDomain(u, "active"));
        for (String id : List.of("disabled", "deleted", "expired", "missing")) {
            assertFalse(service.canAccessDomain(u, id), id);
        }
        assertEquals(List.of("active"), service.loadUserAccessibleDomainList(u, true).stream()
                .map(domain -> Objects.toString(domain.getId())).collect(Collectors.toList()));
        assertEquals(List.of("active"), service.loadUserAccessibleDomainList(u, false).stream()
                .map(domain -> Objects.toString(domain.getId())).collect(Collectors.toList()),
                "加载标志不能绕过授权层的selfAudit检查");
    }

    @Test
    void shouldRejectDomainLoaderReturningWrongOrNullIdentity() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("sales");
        for (String returnedId : Arrays.asList("other", null)) {
            StubRbacBaseService service = new StubRbacBaseService(u) {
                @Override public <DOMAIN extends RbacDomainInfo> DOMAIN loadDomain(Serializable principal) {
                    return (DOMAIN) new TestDomain(returnedId);
                }
            };
            assertFalse(service.canAccessDomain(u, "sales"), "loader返回ID=" + returnedId);
        }
    }

    @Test
    void shouldAvoidDomainEnumerationForEmptyOrFullyDeniedAllowSet() {
        ScopeUser u = new ScopeUser("T1", List.of());
        StubRbacBaseService service = new StubRbacBaseService(u).setDomainList(List.of(new TestDomain("sales")));
        u.fields[2] = Set.of();
        assertTrue(service.loadUserAccessibleDomainList(u, true).isEmpty());
        u.fields[2] = Set.of("sales");
        u.fields[3] = Set.of("sales");
        assertTrue(service.loadUserAccessibleDomainList(u, true).isEmpty());
        assertEquals(0, service.domainListLoads.get());
        assertEquals(0, service.domainLoads.get());
    }

    @Test
    void shouldAvoidSingleDomainLoadForUnauthorizedDeniedOrBlankId() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("sales");
        u.fields[3] = Set.of("sales");
        StubRbacBaseService service = new StubRbacBaseService(u).setDomainList(List.of(new TestDomain("sales")));
        for (String id : Arrays.asList("other", "sales", null, "", " ")) {
            assertFalse(service.canAccessDomain(u, id));
        }
        assertEquals(0, service.domainLoads.get());
        assertEquals(0, service.domainListLoads.get());
    }

    @Test
    void shouldApplyUserDomainEmptyOverrideAndNullInheritanceWhenLoadingDomains() {
        ScopeUser u = new ScopeUser("T1", List.of("R1", "R2"));
        ScopeRole one = new ScopeRole("R1"), two = new ScopeRole("R2");
        one.fields[2] = Set.of("sales");
        two.fields[2] = Set.of("finance");
        two.fields[3] = Set.of("finance");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")));
        service.registerRole(one);
        service.registerRole(two);
        assertEquals(List.of("sales"), service.loadUserAccessibleDomainList(u, true).stream()
                .map(domain -> Objects.toString(domain.getId())).collect(Collectors.toList()));
        u.fields[3] = Set.of();
        assertEquals(2, service.loadUserAccessibleDomainList(u, true).size(), "用户空拒绝集合清除角色拒绝");
        u.fields[2] = Set.of();
        int previousLoads = service.domainListLoads.get();
        assertTrue(service.loadUserAccessibleDomainList(u, true).isEmpty());
        assertEquals(previousLoads, service.domainListLoads.get(), "用户空允许集合覆盖角色后无需枚举");
        u.fields[2] = null;
        assertEquals(2, service.loadUserAccessibleDomainList(u, true).size());
    }

    @Test
    void shouldApplyDomainAllAndNoneMarkers() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("_ALL_");
        StubRbacBaseService service = new StubRbacBaseService(u).setDomainList(List.of(
                new TestDomain("sales"), new TestDomain("*"), new TestDomain("_ALL_")));
        assertTrue(service.canAccessDomain(u, "sales"));
        assertTrue(service.canAccessDomain(u, "*"));
        assertTrue(service.canAccessDomain(u, "_ALL_"));
        assertTrue(service.canAccessDomain(u, null));
        assertEquals(List.of("sales", "*", "_ALL_"), service.loadUserAccessibleDomainList(u, true).stream()
                .map(domain -> Objects.toString(domain.getId())).collect(Collectors.toList()));
        u.fields[2] = Set.of("_NONE_");
        assertFalse(service.canAccessDomain(u, "sales"));
        assertTrue(service.canAccessDomain(u, null));
        assertTrue(service.loadUserAccessibleDomainList(u, true).isEmpty());
        u.fields[2] = Set.of("_ALL_");
        u.fields[3] = Set.of("_NONE_");
        assertFalse(service.canAccessDomain(u, null));
        assertTrue(service.canAccessDomain(u, "sales"));
        u.fields[3] = Set.of("_ALL_");
        assertFalse(service.canAccessDomain(u, "sales"));
    }

    @Test
    void shouldNotBypassDomainScopesForPlatformAdministrator() {
        ScopeUser u = new ScopeUser(null, List.of(RbacRoleInfo.SA_ROLE));
        StubRbacBaseService service = new StubRbacBaseService(u).setDomainList(List.of(new TestDomain("sales")));
        assertTrue(u.isSuperAdmin());
        assertFalse(service.canAccessDomain(u, "sales"));
        assertTrue(service.loadUserAccessibleDomainList(u, true).isEmpty());
        u.fields[2] = Set.of("sales");
        u.fields[3] = Set.of("sales");
        assertFalse(service.canAccessDomain(u, "sales"));
        u.fields[3] = Set.of();
        assertTrue(service.canAccessDomain(u, "sales"));
    }

    @Test
    void shouldKeepDomainScopeIndependentOfTenantAndConfidentialLevelChecks() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of();
        u.fields[2] = Set.of("sales");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setDomainList(List.of(new TestDomain("sales", Integer.MAX_VALUE)));
        assertFalse(service.canAccessTenant(u, "T1"));
        assertFalse(service.canAccessConfidentialDataByUser(u, Integer.MAX_VALUE));
        assertTrue(service.canAccessDomain(u, "sales"));
        assertEquals(1, service.loadUserAccessibleDomainList(u, true).size());
    }

    @Test
    void shouldApplyTenantDomainGateBeforeEveryAdministratorShortcut() {
        for (TestRbacUser u : List.of(
                new TestRbacUser("normal", "normal", null, "PLATFORM", List.of(), 5000),
                new TestRbacUser("admin", "admin", null, "PLATFORM", List.of(RbacRoleInfo.SA_ROLE), 5000),
                new TestRbacUser("top", RbacUserInfo.TOP_SA_ACCOUNT_NAME, null, "PLATFORM", List.of(RbacRoleInfo.SA_ROLE), 5000))) {
            StubRbacBaseService service = new StubRbacBaseService(u)
                    .setDomainList(List.of(new TestDomain("sales")))
                    .setTenantList(List.of(domainTenant("T1", "sales")))
                    .setOrgList(List.of(domainOrg("ROOT", null, "T1", null)));
            assertFalse(service.canAccessTenant(u, "T1"), u.getLoginName());
            assertFalse(service.canAccessOrg(u, "T1", "ROOT"), u.getLoginName());
            assertTrue(service.loadUserAccessibleTenantList(u, true).isEmpty());
            assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
            IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                    () -> service.checkOrgAccessible(u, "T1", null, "ROOT"));
            assertNotNull(error.getMessage());
            assertEquals(1, service.loadAllTenantList(true).size(), "raw loader保留原数据，不承担用户权限过滤");
            assertEquals(1, service.loadTenantOrgList("T1", true).size());
        }
    }

    @Test
    void shouldAllowBlankObjectDomainButRetainTenantDomainGate() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setDomainList(List.of(new TestDomain("sales")))
                .setTenantList(List.of(domainTenant("T1", "sales")))
                .setOrgList(List.of(domainOrg("ROOT", null, "T1", null)));
        assertTrue(service.canAccessObjectDomain(u, domainOrg("FREE", null, "T1", null)));
        assertFalse(service.canAccessOrg(u, "T1", "ROOT"), "空组织域不能绕过所属租户域");
        u.fields[2] = Set.of("sales");
        assertTrue(service.canAccessOrg(u, "T1", "ROOT"));
        service.setTenantList(List.of(domainTenant("T1", null)));
        u.fields[2] = Set.of();
        service.domainLoads.set(0);
        assertTrue(service.canAccessOrg(u, "T1", "ROOT"));
        assertEquals(0, service.domainLoads.get(), "全部空域不加载领域目录");
    }

    @Test
    void shouldRejectConflictingTenantAndOrganizationDomainsEvenWhenBothAreGranted() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[2] = Set.of("sales", "finance");
        u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")))
                .setTenantList(List.of(domainTenant("T1", "sales")))
                .setOrgList(List.of(domainOrg("ROOT", null, "T1", "sales"),
                        domainOrg("WRONG", "ROOT", "T1", "finance")));
        assertTrue(service.canAccessTenant(u, "T1"));
        assertTrue(service.canAccessOrg(u, "T1", "ROOT"));
        assertFalse(service.canAccessOrg(u, "T1", "WRONG"));
        assertFalse(service.canAccessAllOrg(u, "T1"));
        assertEquals(List.of("ROOT"), service.loadUserAccessibleOrgList(u, true).stream()
                .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()));
        assertThrows(IllegalArgumentException.class, () -> service.checkOrgAccessible(u, "T1", "ROOT", "WRONG"));
    }

    @Test
    void shouldExcludeOrganizationDomainBeforeAdminAndAllOrganizationClaims() {
        for (List<String> roles : List.of(List.<String>of(), List.of(RbacRoleInfo.ADMIN_ROLE), List.of(RbacRoleInfo.SA_ROLE))) {
            ScopeUser u = new ScopeUser("T1", roles);
            u.fields[0] = Set.of("T1");
            u.fields[2] = Set.of("sales");
            u.fields[4] = Set.of("_ALL_ROOT_|SelfAndAllChild");
            StubRbacBaseService service = new StubRbacBaseService(u)
                    .setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")))
                    .setOrgList(List.of(domainOrg("ROOT", null, "T1", "sales"), domainOrg("SECRET", "ROOT", "T1", "finance")));
            assertFalse(service.canAccessOrg(u, "T1", "SECRET"));
            assertFalse(service.canAccessAllOrg(u, "T1"));
            assertEquals(List.of("ROOT"), service.loadUserAccessibleOrgList(u, true).stream()
                    .map(org -> Objects.toString(org.getId())).collect(Collectors.toList()));
            assertThrows(IllegalArgumentException.class, () -> service.checkOrgAccessible(u, "T1", "ROOT", "SECRET"));
        }
    }

    @Test
    void shouldCheckParentOrganizationDomainBeforeManagingVisibleChild() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[2] = Set.of("sales");
        u.fields[4] = Set.of("CHILD|Self");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")))
                .setOrgList(List.of(domainOrg("ROOT", null, "T1", "finance"), domainOrg("CHILD", "ROOT", "T1", "sales")));
        assertThrows(IllegalArgumentException.class, () -> service.checkOrgAccessible(u, "T1", "ROOT", "CHILD"));
    }

    @Test
    void shouldLoadEachDomainOnceForLargeOrganizationBatchWithoutMutatingSource() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[2] = Set.of("sales");
        u.fields[4] = Set.of("ROOT|SelfAndAllChild");
        List<TestOrg> source = largeLayeredOrgTree("ROOT", "T1", 50000, 100);
        source.forEach(org -> org.domainId = "sales");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setTenantList(List.of(domainTenant("T1", "sales")))
                .setDomainList(List.of(new TestDomain("sales"))).setOrgList(source);
        Collection<RbacOrgInfo> allowed = assertTimeoutPreemptively(Duration.ofSeconds(2),
                () -> service.loadUserAccessibleOrgList(u, true));
        assertEquals(50000, allowed.size());
        assertEquals(1, service.domainLoads.get(), "租户和5万组织同域时同批只加载领域一次");
        assertEquals(50000, source.size());
        assertEquals(50000, service.loadTenantOrgList("T1", true).size());
        assertTrue(source.stream().allMatch(org -> "sales".equals(org.getDomainId())));
    }

    @Test
    void shouldBootstrapRoleDomainsWithoutGrantingOtherFieldsFromInvisibleRoles() {
        ScopeUser u = new ScopeUser("T1", List.of("bootstrap", "hidden")) {
            @Override public Integer getConfidentialDataAccessLevel() { return null; }
        };
        ScopeRole bootstrap = new ScopeRole("bootstrap");
        bootstrap.fields[2] = Set.of("sales");
        ScopeRole hidden = new ScopeRole("hidden") {
            @Override public Integer getConfidentialDataAccessLevel() { return 9000; }
            @Override public Collection<String> getPermissionList() { return List.of("secret:*:*:read"); }
        };
        hidden.domainId = "finance";
        hidden.fields[0] = Set.of("T2");
        hidden.fields[4] = Set.of("SECRET|Self");
        DefaultRoleHelperRbacBaseService service = new DefaultRoleHelperRbacBaseService(u);
        service.delegate.setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")));
        service.registerRole(bootstrap);
        service.registerRole(hidden);
        DataScope scope = assertTimeoutPreemptively(Duration.ofSeconds(2), () -> service.getUserDataScope(u));
        assertEquals(Set.of("sales"), scope.getDomainScopeList());
        assertTrue(scope.getTenantScopeList().isEmpty());
        assertTrue(scope.getOrgScopeList().isEmpty());
        assertEquals(100, scope.getConfidentialDataAccessLevel());
        assertFalse(service.loadUserPermissionExprList(u).contains("secret:*:*:read"));
        assertEquals(List.of("bootstrap"), service.loadUserOwnerRoleList(u).stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()));
        u.fields[2] = Set.of("finance");
        assertEquals(9000, service.getUserDataScope(u).getConfidentialDataAccessLevel());
        assertTrue(service.loadUserPermissionExprList(u).contains("secret:*:*:read"));
        u.fields[2] = Set.of();
        assertEquals(List.of("bootstrap"), service.loadUserOwnerRoleList(u).stream().map(RbacRoleInfo::getCode).collect(Collectors.toList()));
    }

    @Test
    void shouldSelectTenantRoleBeforeBootstrappingDuplicateCodeDomains() {
        ScopeUser u = new ScopeUser("T1", List.of("SAME"));
        ScopeRole local = new ScopeRole("SAME");
        local.fields[2] = Set.of("sales");
        ScopeRole shared = new ScopeRole("SAME") {
            @Override public <TID extends Serializable> TID getTenantId() { return null; }
        };
        shared.fields[2] = Set.of("finance");
        StubRbacBaseService service = new StubRbacBaseService(u) {
            @Override public <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable tenantId, boolean onlyEffect) {
                return (Collection<R>) (Collection<?>) List.of(shared, local);
            }
        };
        service.setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")));
        assertEquals(Set.of("sales"), service.getUserDataScope(u).getDomainScopeList());
        assertSame(local, service.loadUserOwnerRoleList(u).iterator().next());
    }

    @Test
    void shouldNotReadRoleObjectConfidentialLevelWhileResolvingGrantedAccessLevel() {
        ScopeUser u = new ScopeUser("T1", List.of("DYNAMIC")) {
            @Override public Integer getConfidentialDataAccessLevel() { return null; }
        };
        DefaultRoleHelperRbacBaseService service = new DefaultRoleHelperRbacBaseService(u);
        AtomicInteger objectLevelReads = new AtomicInteger();
        TestRbacRole role = new TestRbacRole("dynamic", "DYNAMIC", "T1", List.of(), List.of(), 500) {
            @Override public Integer getConfidentialLevel() {
                objectLevelReads.incrementAndGet();
                return service.getUserConfidentialDataAccessLevel(u);
            }
        };
        service.registerRole(role);
        assertEquals(500, assertTimeoutPreemptively(Duration.ofSeconds(2), () -> service.getUserDataScope(u)).getConfidentialDataAccessLevel());
        assertEquals(0, objectLevelReads.get(), "计算授予密级不可反向查询角色对象可见密级");
        Collection<RbacRoleInfo> roles = assertTimeoutPreemptively(Duration.ofSeconds(2), () -> service.loadUserAccessibleRoleList(u));
        assertEquals(List.of(role), new ArrayList<>(roles));
        assertTrue(objectLevelReads.get() > 0, "角色可见性入口才读取角色对象密级，getter回调应正常终止");
    }

    @Test
    void shouldPreventSelfAndTopAdministratorManagementFromBypassingUserDomain() {
        ScopeUser own = new ScopeUser("T1", List.of());
        own.domainId = "finance";
        StubRbacBaseService service = new StubRbacBaseService(own).setDomainList(List.of(new TestDomain("finance")));
        assertFalse(service.canAdminUser(own, own));
        own.fields[2] = Set.of("finance");
        assertTrue(service.canAdminUser(own, own));
        TestRbacUser top = new TestRbacUser("top", RbacUserInfo.TOP_SA_ACCOUNT_NAME, null, "PLATFORM", List.of(RbacRoleInfo.SA_ROLE), 5000);
        assertTrue(top.isTopSuperAdmin());
        assertFalse(service.canAdminUser(top, own));
    }

    @Test
    void shouldApplyRoleAndAssignmentTargetDomainsBeforeTopAdministratorShortcut() {
        TestRbacUser top = new TestRbacUser("top", RbacUserInfo.TOP_SA_ACCOUNT_NAME, null, "PLATFORM", List.of(RbacRoleInfo.SA_ROLE), 5000);
        ScopeUser target = new ScopeUser("T1", List.of());
        target.domainId = "finance";
        TestRbacRole role = new TestRbacRole("role", "R_DOMAIN", null, List.of(), List.of(), 100);
        role.domainId = "finance";
        StubRbacBaseService service = new StubRbacBaseService(top)
                .setTenantList(List.of(domainTenant("T1", null)))
                .setDomainList(List.of(new TestDomain("finance")));
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        assertFalse(auth.isRoleAuthorized(top, role, null));
        assertThrows(IllegalArgumentException.class, () -> auth.checkRoleAssignment(top, target, List.of()));
        role.domainId = null;
        assertTrue(auth.isRoleAuthorized(top, role, null));
        target.domainId = null;
        service.setTenantList(List.of(domainTenant("T1", "finance")));
        assertThrows(IllegalArgumentException.class, () -> auth.checkRoleAssignment(top, target, List.of()));
    }

    @Test
    void shouldFilterMenuDomainsWithoutMutatingCachedTreesAcrossUsers() {
        ScopeUser sales = new ScopeUser("T1", List.of());
        sales.fields[2] = Set.of("sales");
        ScopeUser finance = new ScopeUser("T1", List.of());
        finance.fields[2] = Set.of("finance");
        StubRbacBaseService service = new StubRbacBaseService(sales)
                .setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")));
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        DomainMenu root = domainMenu("root", null);
        DomainMenu salesParent = domainMenu("sales", "sales");
        DomainMenu financeChild = domainMenu("finance-child", "finance");
        financeChild.setAlwaysShow(true);
        salesParent.children.add(financeChild);
        DomainMenu financeParent = domainMenu("finance", "finance");
        DomainMenu emptyChild = domainMenu("empty-child", null);
        financeParent.children.add(emptyChild);
        root.children.addAll(List.of(salesParent, financeParent));
        MenuItem.OpButton button = new MenuItem.OpButton().setOpName("view").setRequireAuthorizations(List.of());
        salesParent.setOpButtonList(new LinkedHashSet<>(List.of(button)));
        List<SimpleMenu> first = auth.filterAccessibleMenuList(sales, List.of(root));
        SimpleMenu salesCopy = (SimpleMenu) first.get(0).getChildren().iterator().next();
        assertEquals("sales", salesCopy.getId());
        assertTrue(salesCopy.getChildren().isEmpty(), "alwaysShow不可绕过子菜单领域");
        assertNotSame(salesParent, salesCopy);
        assertNotSame(button, salesCopy.getOpButtonList().iterator().next());
        List<SimpleMenu> second = auth.filterAccessibleMenuList(finance, List.of(root));
        SimpleMenu financeCopy = (SimpleMenu) second.get(0).getChildren().iterator().next();
        assertEquals("finance", financeCopy.getId());
        assertEquals("empty-child", financeCopy.getChildren().iterator().next().getId());
        assertEquals(2, root.children.size());
        assertEquals(1, salesParent.children.size());
        assertEquals(1, financeParent.children.size());
        assertSame(button, salesParent.getOpButtonList().iterator().next());
        assertEquals(1, first.get(0).getChildren().size(), "第二个用户过滤不得污染第一个用户结果");
    }

    @Test
    void shouldKeepEmptyDomainMenuActionChecksAndAlwaysShowBehavior() {
        ScopeUser u = new ScopeUser("T1", List.of());
        StubRbacBaseService service = new StubRbacBaseService(u);
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        DomainMenu denied = domainMenu("denied", null);
        denied.setRequireAuthorizations(List.of("sys:secret:1:view"));
        DomainMenu shown = domainMenu("shown", null);
        shown.setRequireAuthorizations(List.of("sys:secret:1:view")).setAlwaysShow(true);
        assertEquals(List.of("shown"), auth.filterAccessibleMenuList(u, List.of(denied, shown)).stream()
                .map(SimpleMenu::getId).collect(Collectors.toList()));
        assertEquals(0, service.domainLoads.get());
    }

    @Test
    void shouldRetainRoleAuthorizationOverridesAfterDomainGate() {
        ScopeUser operator = new ScopeUser("T1", List.of());
        ScopeUser target = new ScopeUser("T1", List.of());
        TestRbacRole role = new TestRbacRole("role", "R_OVERRIDE", "T1", List.of(), List.of(), 100);
        StubRbacBaseService service = new StubRbacBaseService(operator).setDomainList(List.of(new TestDomain("finance")));
        AtomicInteger overrides = new AtomicInteger();
        TestAuthorizeService auth = new TestAuthorizeService() {
            @Override public boolean isRoleAuthorized(Serializable principal, RbacRoleInfo requestedRole,
                    java.util.function.BiConsumer<String, String> errors) {
                overrides.incrementAndGet();
                return true;
            }
        };
        auth.setRbacBaseService(service);
        service.registerRole(role);
        assertDoesNotThrow(() -> auth.checkRoleAssignment(operator, target, List.of(role)));
        assertTrue(overrides.get() > 0, "保留业务覆写角色授权的扩展点");
        overrides.set(0);
        role.domainId = "finance";
        assertThrows(IllegalArgumentException.class, () -> auth.checkRoleAssignment(operator, target, List.of(role)));
        assertEquals(0, overrides.get(), "领域外层门槛在可覆写授权方法之前执行");
    }

    @Test
    void shouldBatchObjectDomainFilteringAndKeepBlankDomainObjects() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("sales");
        StubRbacBaseService service = new StubRbacBaseService(u)
                .setDomainList(List.of(new TestDomain("sales"), new TestDomain("finance")));
        TestOrg one = domainOrg("ONE", null, "T1", "sales");
        TestOrg two = domainOrg("TWO", null, "T1", "sales");
        TestOrg denied = domainOrg("DENIED", null, "T1", "finance");
        TestOrg blank = domainOrg("BLANK", null, "T1", null);
        List<TestOrg> source = Arrays.asList(one, denied, two, null, blank);
        assertEquals(List.of(one, two, blank), new ArrayList<>(service.filterByDomainAccess(u, source)));
        assertEquals(1, service.domainLoads.get(), "相同授权领域只加载一次，未授权领域无需加载");
        assertEquals(5, source.size());
        assertFalse(service.canAccessObjectDomain(u, null));
    }

    @Test
    void shouldRecheckDomainStatusWhenImplementationReusesDataScopeSnapshot() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[2] = Set.of("sales");
        DataScope snapshot = new StubRbacBaseService(u).getUserDataScope(u);
        StubRbacBaseService service = new StubRbacBaseService(u) {
            @Override public DataScope getUserDataScope(Serializable principal) { return snapshot; }
        };
        service.setTenantList(List.of(domainTenant("T1", "sales")))
                .setDomainList(List.of(new TestDomain("sales")));
        assertTrue(service.canAccessTenant(u, "T1"));
        service.setDomainList(List.of(new TestDomain("sales") {
            @Override public boolean isEnable() { return false; }
        }));
        assertFalse(service.canAccessTenant(u, "T1"), "复用范围快照不能复用上一次领域有效性结果");
        assertEquals(2, service.domainLoads.get());
    }

    @Test
    void shouldClearDomainEvaluationContextAfterLoaderFailure() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[2] = Set.of("sales");
        AtomicInteger attempts = new AtomicInteger();
        boolean[] enabled = {true};
        StubRbacBaseService service = new StubRbacBaseService(u) {
            @Override public <DOMAIN extends RbacDomainInfo> DOMAIN loadDomain(Serializable principal) {
                if (attempts.incrementAndGet() == 1) throw new IllegalStateException("domain storage unavailable");
                return (DOMAIN) new TestDomain("sales") {
                    @Override public boolean isEnable() { return enabled[0]; }
                };
            }
        };
        service.setTenantList(List.of(domainTenant("T1", "sales")));
        IllegalStateException failure = assertThrows(IllegalStateException.class, () -> service.canAccessTenant(u, "T1"));
        assertEquals("domain storage unavailable", failure.getMessage());
        assertTrue(service.canAccessTenant(u, "T1"));
        enabled[0] = false;
        assertFalse(service.canAccessTenant(u, "T1"));
        assertEquals(3, attempts.get(), "失败和正常退出均须清理单次计算上下文");
    }

    @Test
    void shouldNotShareDomainMetadataBetweenServiceInstances() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[0] = Set.of("T1");
        u.fields[2] = Set.of("sales");
        StubRbacBaseService first = new StubRbacBaseService(u)
                .setTenantList(List.of(domainTenant("T1", "sales")))
                .setDomainList(List.of(new TestDomain("sales")));
        StubRbacBaseService second = new StubRbacBaseService(u)
                .setTenantList(List.of(domainTenant("T1", "sales")))
                .setDomainList(List.of(new TestDomain("sales") { @Override public boolean isEnable() { return false; } }));
        assertTrue(first.canAccessTenant(u, "T1"));
        assertFalse(second.canAccessTenant(u, "T1"));
        assertTrue(first.canAccessTenant(u, "T1"));
        assertEquals(2, first.domainLoads.get());
        assertEquals(1, second.domainLoads.get());
    }

    @Test
    void shouldSerializeFilteredMenuTreeWithoutParentCycles() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.fields[2] = Set.of("sales");
        StubRbacBaseService service = new StubRbacBaseService(u).setDomainList(List.of(new TestDomain("sales")));
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        DomainMenu root = domainMenu("root", null);
        root.children.add(domainMenu("child", "sales"));
        List<SimpleMenu> menus = auth.filterAccessibleMenuList(u, List.of(root));
        String json = assertDoesNotThrow(() -> new ObjectMapper().writeValueAsString(menus));
        JsonNode result = assertDoesNotThrow(() -> new ObjectMapper().readTree(json));
        assertEquals("root", result.get(0).path("id").asText());
        assertEquals("child", result.get(0).path("children").get(0).path("id").asText());
        assertFalse(result.get(0).path("children").get(0).has("parent"), "序列化不输出循环父引用");
    }

    @Test
    void shouldRejectRoleAssignmentWhenTenantLoaderReturnsAnotherTenant() {
        ScopeUser top = new ScopeUser(null, List.of(RbacRoleInfo.SA_ROLE)) {
            @Override public String getLoginName() { return RbacUserInfo.TOP_SA_ACCOUNT_NAME; }
        };
        top.fields[2] = Set.of("sales");
        TestRbacRole role = new TestRbacRole("role", "R_T2", "T2", List.of(), List.of(), 100);
        role.domainId = "sales";
        TestRbacUser target = new TestRbacUser("target", "target", "T2", "OPS", List.of(), 100);
        StubRbacBaseService service = new StubRbacBaseService(top) {
            @Override public <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable principal) {
                return (TENANT) domainTenant("T1", "sales");
            }
        };
        service.setDomainList(List.of(new TestDomain("sales")));
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        assertTrue(top.isTopSuperAdmin());
        assertTrue(auth.isRoleAuthorized(top, role, null), "单角色授权只看角色自身领域，不查询定义租户");
        service.registerRole(role);
        assertThrows(IllegalArgumentException.class, () -> auth.checkRoleAssignment(top, target, List.of(role)));
    }

    @Test
    void shouldRejectUnavailableAssignmentTargetTenantEvenForTopAdministrator() {
        ScopeUser top = new ScopeUser(null, List.of(RbacRoleInfo.SA_ROLE)) {
            @Override public String getLoginName() { return RbacUserInfo.TOP_SA_ACCOUNT_NAME; }
        };
        top.fields[2] = Set.of("sales");
        TestRbacRole role = new TestRbacRole("role", "R_T1", "T1", List.of(), List.of(), 100);
        TestRbacUser target = new TestRbacUser("target", "target", "T1", "OPS", List.of(), 100);
        TestTenant disabled = new TestTenant("T1", "Disabled") { @Override public boolean isEnable() { return false; } };
        TestTenant expired = new ExpiredTestTenant("T1", "Expired");
        for (TestTenant tenant : List.of(disabled, expired)) {
            tenant.domainId = "sales";
            StubRbacBaseService service = new StubRbacBaseService(top)
                    .setTenantList(List.of(tenant)).setDomainList(List.of(new TestDomain("sales")));
            TestAuthorizeService auth = new TestAuthorizeService();
            auth.setRbacBaseService(service);
            assertTrue(auth.isRoleAuthorized(top, role, null), "角色定义tenant不作为领域父级");
            service.registerRole(role);
            assertThrows(IllegalArgumentException.class, () -> auth.checkRoleAssignment(top, target, List.of(role)));
        }
    }

    @Test
    void shouldShareOneDomainAndTenantLoadAcrossSingleOrganizationCheck() {
        ScopeUser u = new ScopeUser("T1", List.of());
        u.domainId = "sales";
        u.fields[0] = Set.of("T1");
        u.fields[2] = Set.of("sales");
        u.fields[4] = Set.of("ROOT|SelfAndAllChild");
        AtomicInteger tenantLoads = new AtomicInteger();
        StubRbacBaseService service = new StubRbacBaseService(u) {
            @Override public <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable principal) {
                tenantLoads.incrementAndGet();
                return super.loadTenant(principal);
            }
        };
        service.setTenantList(List.of(domainTenant("T1", "sales")))
                .setDomainList(List.of(new TestDomain("sales")))
                .setOrgList(List.of(domainOrg("ROOT", null, "T1", "sales"), domainOrg("A1", "ROOT", "T1", "sales")));
        assertTrue(service.canAccessOrg(u, "T1", "A1"));
        assertEquals(1, service.domainLoads.get(), "单点组织检查的嵌套领域门槛共享一次目录加载");
        assertEquals(1, tenantLoads.get(), "单点组织检查只加载目标租户一次");
    }

    @Test
    void shouldHonorOverriddenEffectiveDomainDenialAcrossAllPermissionEntrypoints() {
        ScopeUser u = new ScopeUser("T1", List.of("R_SALES"));
        u.fields[2] = Set.of("sales");
        DataScope overridden = new DataScope() {
            @Override public Set<String> getTenantScopeList() { return Set.of("T1"); }
            @Override public Set<String> getDomainScopeList() { return Set.of("sales"); }
            @Override public Set<String> getDeniedDomainScopeList() { return Set.of("sales"); }
            @Override public Set<String> getOrgScopeList() { return Set.of("ROOT|SelfAndAllChild"); }
        };
        DefaultRoleHelperRbacBaseService service = new DefaultRoleHelperRbacBaseService(u) {
            @Override public DataScope getUserDataScope(Serializable principal) { return overridden; }
        };
        service.delegate.setDomainList(List.of(new TestDomain("sales")))
                .setTenantList(List.of(domainTenant("T1", "sales")))
                .setOrgList(List.of(domainOrg("ROOT", null, "T1", "sales")));
        TestRbacRole role = new TestRbacRole("sales-role", "R_SALES", "T1", List.of("sales:report:*:read"), List.of(), 100);
        role.domainId = "sales";
        service.registerRole(role);
        TestOrg object = domainOrg("OBJECT", null, "T1", "sales");
        assertFalse(service.canAccessDomain(u, "sales"));
        assertFalse(service.canAccessObjectDomain(u, object));
        assertTrue(service.filterByDomainAccess(u, List.of(object)).isEmpty());
        assertFalse(service.canAccessTenant(u, "T1"));
        assertFalse(service.canAccessOrg(u, "T1", "ROOT"));
        assertTrue(service.loadUserAccessibleTenantList(u, true).isEmpty());
        assertTrue(service.loadUserAccessibleOrgList(u, true).isEmpty());
        assertTrue(service.loadUserOwnerRoleList(u).isEmpty(), "覆写后的领域拒绝同样约束生效角色");
        assertFalse(service.loadUserPermissionExprList(u).contains("sales:report:*:read"));
    }

    @Test
    void shouldResolveAssignmentRolesOnceByCodeAndInvokeOverridesWithCanonicalObjects() {
        ScopeUser target = new ScopeUser("T1", List.of());
        TestRbacRole localA = new TestRbacRole("local-a", "A", "T1", List.of(), List.of(), 100);
        TestRbacRole sharedA = new TestRbacRole("shared-a", "A", null, List.of(), List.of(), 100);
        TestRbacRole sharedB = new TestRbacRole("shared-b", "B", null, List.of(), List.of(), 100);
        RoleCatalogService service = new RoleCatalogService(target, List.of(sharedA, sharedB, localA));
        List<RbacRoleInfo> selected = new ArrayList<>();
        TestAuthorizeService auth = canonicalCapturingAuthorizeService(service, selected);
        TestRbacRole forgedA = new TestRbacRole("forged-a", "A", "OTHER", List.of("forged:metadata"), List.of("B"), Integer.MAX_VALUE);
        forgedA.domainId = "untrusted-domain";
        assertDoesNotThrow(() -> auth.checkRoleAssignment(target, target, List.of(sharedB, forgedA, sharedB)));
        assertEquals(List.of(sharedB, localA), selected, "按请求编码顺序去重，虚拟授权接收目录中的canonical对象");
        assertEquals(1, service.catalogLoads.get());
        assertEquals("T1", service.lastCatalogTenant);
        assertEquals(Boolean.TRUE, service.lastCatalogEffectFlag);
    }

    @Test
    void shouldPreferValidLocalRoleRegardlessOfCatalogOrder() {
        ScopeUser target = new ScopeUser("T1", List.of("ROLE"));
        TestRbacRole local = new TestRbacRole("local", "ROLE", "T1", List.of(), List.of(), 100);
        TestRbacRole shared = new TestRbacRole("shared", "ROLE", null, List.of(), List.of(), 100);
        TestRbacRole foreign = new TestRbacRole("foreign", "ROLE", "T2", List.of(), List.of(), 100);
        for (List<TestRbacRole> catalog : List.of(List.of(local, shared, foreign), List.of(foreign, shared, local))) {
            RoleCatalogService service = new RoleCatalogService(target, catalog);
            List<RbacRoleInfo> selected = new ArrayList<>();
            TestAuthorizeService auth = canonicalCapturingAuthorizeService(service, selected);
            assertDoesNotThrow(() -> auth.checkRoleAssignment(target, target, List.of(shared)));
            assertEquals(List.of(local), selected);
            assertSame(local, service.loadUserOwnerRoleList(target).iterator().next(), "实际拥有角色与分配解析选中同一目录定义");
        }
    }

    @Test
    void shouldFallbackToSharedRoleWhenLocalDefinitionIsDisabledOrExpired() {
        ScopeUser target = new ScopeUser("T1", List.of("ROLE"));
        TestRbacRole shared = new TestRbacRole("shared", "ROLE", null, List.of(), List.of(), 100);
        TestRbacRole disabled = new DisabledTestRbacRole("disabled", "ROLE", "T1", List.of(), List.of(), 100);
        TestRbacRole expired = new TestRbacRole("expired", "ROLE", "T1", List.of(), List.of(), 100) {
            @Override public java.time.LocalDateTime getExpiredTime() { return java.time.LocalDateTime.now().minusDays(1); }
        };
        for (TestRbacRole invalid : List.of(disabled, expired)) {
            RoleCatalogService service = new RoleCatalogService(target, List.of(shared, invalid));
            List<RbacRoleInfo> selected = new ArrayList<>();
            TestAuthorizeService auth = canonicalCapturingAuthorizeService(service, selected);
            assertDoesNotThrow(() -> auth.checkRoleAssignment(target, target, List.of(invalid)));
            assertEquals(List.of(shared), selected, "传入失效定义只提供code，使用有效共享定义");
            assertSame(shared, service.loadUserOwnerRoleList(target).iterator().next());
        }
    }

    @Test
    void shouldRejectRequestedRoleWithoutAnEffectiveDefinitionForTargetTenant() {
        ScopeUser target = new ScopeUser("T1", List.of());
        TestRbacRole request = new TestRbacRole("fabricated", "MISSING", null, List.of(), List.of(), 100);
        for (List<TestRbacRole> catalog : List.of(List.<TestRbacRole>of(),
                List.of(new TestRbacRole("foreign", "MISSING", "T2", List.of(), List.of(), 100)),
                List.<TestRbacRole>of(new DisabledTestRbacRole("disabled", "MISSING", "T1", List.of(), List.of(), 100)))) {
            RoleCatalogService service = new RoleCatalogService(target, catalog);
            List<RbacRoleInfo> selected = new ArrayList<>();
            TestAuthorizeService auth = canonicalCapturingAuthorizeService(service, selected);
            IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                    () -> auth.checkRoleAssignment(target, target, List.of(request)));
            assertTrue(error.getMessage().contains("MISSING"), error.getMessage());
            assertTrue(selected.isEmpty(), "不存在有效目录定义时不进入可覆写的角色授权入口");
        }
    }

    @Test
    void shouldNotUseSharedRoleMetadataToBypassLocalPermissionOrConfidentialLevel() {
        TestRbacRole shared = new TestRbacRole("shared", "ROLE", null, List.of(), List.of(), 0);
        TestRbacRole requiresPermission = new TestRbacRole("local-permission", "ROLE", "T1",
                List.of("sys:secret:*:assign"), List.of(), 100);
        TestRbacRole requiresLevel = new TestRbacRole("local-level", "ROLE", "T1", List.of(), List.of(), 6000, List.of(), 100);
        for (TestRbacRole local : List.of(requiresPermission, requiresLevel)) {
            RoleCatalogService service = new RoleCatalogService(user, List.of(shared, local));
            service.setUserPermissions(List.of("sys:basic:*:view"));
            TestAuthorizeService auth = new TestAuthorizeService();
            auth.setRbacBaseService(service);
            assertThrows(IllegalArgumentException.class, () -> auth.checkRoleAssignment(user, user, List.of(shared)));
        }
    }

    @Test
    void shouldNotUseSharedRoleToBypassLocalDomainOrAssignmentConditions() {
        ScopeUser target = new ScopeUser("T1", List.of());
        TestRbacRole shared = new TestRbacRole("shared", "ROLE", null, List.of(), List.of(), 0);
        TestRbacRole requiresDomain = new TestRbacRole("local-domain", "ROLE", "T1", List.of(), List.of(), 100);
        requiresDomain.domainId = "finance";
        TestRbacRole requiresCondition = new TestRbacRole("local-condition", "ROLE", "T1", List.of(), List.of(), 100) {
            @Override public String getAssignPreCondition() { return "false"; }
        };
        for (TestRbacRole local : List.of(requiresDomain, requiresCondition)) {
            RoleCatalogService service = new RoleCatalogService(target, List.of(shared, local));
            service.setDomainList(List.of(new TestDomain("finance")));
            TestAuthorizeService auth = canonicalCapturingAuthorizeService(service, new ArrayList<>());
            assertThrows(IllegalArgumentException.class, () -> auth.checkRoleAssignment(target, target, List.of(shared)));
        }
    }

    @Test
    void shouldCheckCanonicalExclusiveAndCoexistingRoleRequirements() {
        ScopeUser target = new ScopeUser("T1", List.of());
        TestRbacRole shared = new TestRbacRole("shared", "ROLE", null, List.of(), List.of(), 100);
        TestRbacRole other = new TestRbacRole("other", "OTHER", "T1", List.of(), List.of(), 100);
        TestRbacRole exclusive = new TestRbacRole("local-exclusive", "ROLE", "T1", List.of(), List.of("OTHER"), 100);
        TestRbacRole coexist = new TestRbacRole("local-coexist", "ROLE", "T1", List.of(), List.of(), 100,
                List.of(), null, List.of("REQUIRED"));
        RoleCatalogService exclusiveService = new RoleCatalogService(target, List.of(shared, other, exclusive));
        TestAuthorizeService exclusiveAuth = canonicalCapturingAuthorizeService(exclusiveService, new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> exclusiveAuth.checkRoleAssignment(target, target, List.of(shared, other)));
        RoleCatalogService coexistService = new RoleCatalogService(target, List.of(shared, coexist));
        TestAuthorizeService coexistAuth = canonicalCapturingAuthorizeService(coexistService, new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> coexistAuth.checkRoleAssignment(target, target, List.of(shared)));
    }

    @Test
    void shouldUseOnlySelectedRolePermissionsWithoutAggregatingSameCodeDefinitions() {
        TestRbacRole local = new TestRbacRole("local", "ROLE", "T1", List.of("sys:basic:*:assign"), List.of(), 100);
        TestRbacRole shared = new TestRbacRole("shared", "ROLE", null, List.of("sys:secret:*:assign"), List.of(), 100);
        RoleCatalogService service = new RoleCatalogService(user, List.of(shared, local)) {
            @Override public Collection<String> loadRolePermissionList(Serializable tenantId, Collection<String> codes) {
                throw new AssertionError("selected role permissions must not be reloaded or merged by code");
            }
        };
        service.setUserPermissions(List.of("sys:basic:*:assign"));
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        assertTrue(auth.isRoleAuthorized(user, local, null));
        assertDoesNotThrow(() -> auth.checkRoleAssignment(user, user, List.of(shared)));
    }

    @Test
    void shouldNotLoadDefinitionTenantForStandaloneRoleDomainAuthorization() {
        ScopeUser top = new ScopeUser(null, List.of(RbacRoleInfo.SA_ROLE)) {
            @Override public String getLoginName() { return RbacUserInfo.TOP_SA_ACCOUNT_NAME; }
        };
        top.fields[2] = Set.of("sales");
        TestRbacRole role = new TestRbacRole("role", "ROLE", "UNAVAILABLE_DEFINITION_TENANT", List.of(), List.of(), 100);
        role.domainId = "sales";
        StubRbacBaseService service = new StubRbacBaseService(top) {
            @Override public <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable principal) {
                throw new AssertionError("role definition tenant is not a parent domain");
            }
        };
        service.setDomainList(List.of(new TestDomain("sales")));
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        assertTrue(auth.isRoleAuthorized(top, role, null));
    }

    @Test
    void shouldKeepTargetTenantBoundaryWhenAssigningSharedRole() {
        TestRbacRole shared = new TestRbacRole("shared", "BASIC", null, List.of(), List.of(), 100);
        ScopeUser target = new ScopeUser("T2", List.of());
        ScopeUser tenantOperator = new ScopeUser("T1", List.of());
        tenantOperator.fields[0] = Set.of("T2");
        RoleCatalogService tenantService = new RoleCatalogService(tenantOperator, List.of(shared));
        tenantService.setTenantList(List.of(new TestTenant("T1", "One"), new TestTenant("T2", "Two")));
        TestAuthorizeService tenantAuth = new TestAuthorizeService();
        tenantAuth.setRbacBaseService(tenantService);
        assertThrows(IllegalArgumentException.class, () -> tenantAuth.checkRoleAssignment(tenantOperator, target, List.of(shared)),
                "共享角色仍不能使普通T1用户跨租户管理T2用户");

        ScopeUser platformOperator = new ScopeUser(null, List.of());
        platformOperator.fields[0] = Set.of("T2");
        RoleCatalogService platformService = new RoleCatalogService(platformOperator, List.of(shared));
        platformService.setTenantList(List.of(new TestTenant("T2", "Two")));
        TestAuthorizeService platformAuth = new TestAuthorizeService();
        platformAuth.setRbacBaseService(platformService);
        assertDoesNotThrow(() -> platformAuth.checkRoleAssignment(platformOperator, target, List.of(shared)));
    }

    @Test
    void shouldResolveCoexistingRoleClosureUsingLocalDefinitionOverSharedDefinition() {
        ScopeUser target = new ScopeUser("T1", List.of());
        TestRbacRole advanced = new TestRbacRole("advanced", "ADVANCED", "T1", List.of(), List.of(), 100,
                List.of(), null, List.of("DEPENDENCY"));
        TestRbacRole sharedDependency = new TestRbacRole("shared-dependency", "DEPENDENCY", null, List.of(), List.of(), 100);
        TestRbacRole localDependency = new TestRbacRole("local-dependency", "DEPENDENCY", "T1", List.of(), List.of(), 100,
                List.of(), null, List.of("CORE"));
        TestRbacRole core = new TestRbacRole("core", "CORE", "T1", List.of(), List.of(), 100);
        RoleCatalogService service = new RoleCatalogService(target, List.of(advanced, sharedDependency, localDependency, core));
        TestAuthorizeService auth = new TestAuthorizeService();
        auth.setRbacBaseService(service);
        DataPair<TestRbacRole, Collection<TestRbacRole>> missing = auth.findMissingCoexistRolePair(target, List.of(advanced));
        assertNotNull(missing);
        assertSame(advanced, missing.getA());
        assertEquals(List.of(localDependency, core), new ArrayList<>(missing.getB()),
                "缺失依赖闭包须继续读取本租户覆盖定义上的额外共存要求");
    }

    @Test
    void shouldNotInterpretReservedTenantMarkersAsLiteralTenantIds() {
        ScopeUser user = new ScopeUser(null, List.of());
        StubRbacBaseService service = new StubRbacBaseService(user).setTenantList(List.of(
                new TestTenant(DataScope.TenantScope.Default.getExpression(), "Reserved default"),
                new TestTenant(DataScope.TenantScope.None.getExpression(), "Reserved none")));

        user.fields[0] = Set.of(DataScope.TenantScope.Default.getExpression());
        assertFalse(service.canAccessTenant(user, DataScope.TenantScope.Default.getExpression()),
                "_DEFAULT_ 是范围标记，不能作为同名真实租户的精确授权");

        user.fields[0] = Set.of(DataScope.TenantScope.None.getExpression());
        assertFalse(service.canAccessTenant(user, DataScope.TenantScope.None.getExpression()),
                "_NONE_ 只匹配无租户目标，不能作为同名真实租户的精确授权");
    }

    @Test
    void shouldRejectTenantLoaderResultWhoseIdDiffersFromRequestedTenant() {
        ScopeUser user = new ScopeUser(null, List.of());
        user.fields[0] = Set.of("T1");
        StubRbacBaseService service = new StubRbacBaseService(user) {
            @Override
            public <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable tenantPrincipal) {
                return (TENANT) new TestTenant("T2", "Wrong tenant");
            }
        };

        assertFalse(service.canAccessTenant(user, "T1"),
                "租户加载器返回的对象 ID 与请求租户不一致时必须拒绝");
    }

    @Test
    void shouldExerciseTenantGroovyAndEnumerationFallbackBranches() {
        ScopeUser platformUser = new ScopeUser(null, List.of());
        platformUser.fields[0] = Set.of("T1");
        platformUser.fields[1] = Set.of("Groovy#true");
        StubRbacBaseService platformService = new StubRbacBaseService(platformUser)
                .setTenantList(List.of(new TestTenant("T1", "Tenant one")));
        assertFalse(platformService.canAccessTenant(platformUser, "T1"),
                "动态拒绝规则命中时必须优先于静态允许规则");

        ScopeUser tenantUser = new ScopeUser("T1", List.of());
        tenantUser.fields[0] = Set.of("T1");
        tenantUser.fields[1] = Set.of("T2");
        StubRbacBaseService tenantService = new StubRbacBaseService(tenantUser)
                .setTenantList(List.of(new TestTenant("T1", "Tenant one")));
        assertEquals(List.of("T1"), tenantService.loadUserAccessibleTenantList(tenantUser, true).stream()
                .map(tenant -> Objects.toString(tenant.getId())).collect(Collectors.toList()),
                "未命中自身租户的静态拒绝规则不能阻止候选租户枚举");

        ScopeUser groovyNamedTenantUser = new ScopeUser(null, List.of());
        groovyNamedTenantUser.fields[0] = Set.of("Groovy#true");
        StubRbacBaseService groovyNamedTenantService = new StubRbacBaseService(groovyNamedTenantUser)
                .setTenantList(List.of(new TestTenant("Groovy#true", "Groovy-named tenant")));
        assertTrue(groovyNamedTenantService.canAccessTenant(groovyNamedTenantUser, "Groovy#true"),
                "Groovy# 前缀规则必须走脚本匹配，而不能退化为对保留字符串的精确 ID 匹配");
    }

    private static TestAuthorizeService canonicalCapturingAuthorizeService(RbacBaseService service, List<RbacRoleInfo> selected) {
        TestAuthorizeService auth = new TestAuthorizeService() {
            @Override public boolean isRoleAuthorized(Serializable principal, RbacRoleInfo role,
                    java.util.function.BiConsumer<String, String> errors) {
                selected.add(role);
                return true;
            }
        };
        auth.setRbacBaseService(service);
        return auth;
    }

    private static class RoleCatalogService extends StubRbacBaseService {
        final List<TestRbacRole> catalog;
        final AtomicInteger catalogLoads = new AtomicInteger();
        Serializable lastCatalogTenant;
        Boolean lastCatalogEffectFlag;
        RoleCatalogService(TestRbacUser user, List<TestRbacRole> catalog) {
            super(user);
            this.catalog = catalog;
        }
        @Override public <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable tenantId, boolean onlyEffect) {
            catalogLoads.incrementAndGet();
            lastCatalogTenant = tenantId;
            lastCatalogEffectFlag = onlyEffect;
            return (Collection<R>) (Collection<?>) catalog;
        }
    }

    private static TestTenant domainTenant(String id, String domain) {
        TestTenant tenant = new TestTenant(id, id);
        tenant.domainId = domain;
        return tenant;
    }

    private static TestOrg domainOrg(String id, String parentId, String tenantId, String domain) {
        TestOrg org = new TestOrg(id, parentId, tenantId, id);
        org.domainId = domain;
        return org;
    }

    private static DomainMenu domainMenu(String id, String domain) {
        DomainMenu menu = new DomainMenu();
        menu.setId(id).setName(id).setDomainId(domain).setPath("/" + id).setRequireAuthorizations(List.of());
        return menu;
    }

    private static class DomainMenu extends SimpleMenu {
        final List<MenuItem> children = new ArrayList<>();
        @Override public <C extends MenuItem> Collection<C> getChildren() { return (Collection<C>) children; }
    }

    private static List<Set<String>> scopeFields(DataScope scope) {
        return Arrays.asList(scope.getTenantScopeList(), scope.getDeniedTenantScopeList(),
                scope.getDomainScopeList(), scope.getDeniedDomainScopeList(),
                scope.getOrgScopeList(), scope.getDeniedOrgScopeList());
    }

    private static class ScopeUser extends TestRbacUser {
        final Set<String>[] fields = new Set[6];
        ScopeUser(String tenant, List<String> roles) { super("scope", "scope", tenant, "OPS", roles, 5000); }
        public Set<String> getTenantScopeList() { return fields[0]; }
        public Set<String> getDeniedTenantScopeList() { return fields[1]; }
        public Set<String> getDomainScopeList() { return fields[2]; }
        public Set<String> getDeniedDomainScopeList() { return fields[3]; }
        public Set<String> getOrgScopeList() { return fields[4]; }
        public Set<String> getDeniedOrgScopeList() { return fields[5]; }
    }

    private static class ScopeRole extends TestRbacRole {
        final Set<String>[] fields = new Set[6];
        ScopeRole(String code) { super(code, code, "T1", List.of(), List.of(), 100); }
        public Set<String> getTenantScopeList() { return fields[0]; }
        public Set<String> getDeniedTenantScopeList() { return fields[1]; }
        public Set<String> getDomainScopeList() { return fields[2]; }
        public Set<String> getDeniedDomainScopeList() { return fields[3]; }
        public Set<String> getOrgScopeList() { return fields[4]; }
        public Set<String> getDeniedOrgScopeList() { return fields[5]; }
    }

    // Test fixture: keep tenant and organization inputs readable, expose only the new Set fields.
    private static class ScopeGrant {
        final String tenant;
        final String rule;
        final boolean allow;
        ScopeGrant(String tenant, String rule, boolean allow) {
            this.tenant = tenant;
            this.rule = rule;
            this.allow = allow;
        }
    }

    private static ScopeGrant scope(String orgId, boolean allow, DataScope.OrgMatchingMode mode) {
        return scope("_DEFAULT_", orgId, allow, mode);
    }

    private static ScopeGrant scope(String tenant, String orgId, boolean allow, DataScope.OrgMatchingMode mode) {
        return new ScopeGrant(tenant.isEmpty() ? "_NONE_" : tenant, orgId + "|" + mode.getExpression(), allow);
    }

    private static ScopeGrant customScope(String orgId, boolean allow, String expression) {
        return customScope(orgId, allow, expression, DataScope.OrgMatchingMode.IdPath);
    }

    private static ScopeGrant customScope(String orgId, boolean allow, String expression, DataScope.OrgMatchingMode mode) {
        return customScope("_DEFAULT_", orgId, allow, expression, mode);
    }

    private static ScopeGrant customScope(String tenant, String orgId, boolean allow, String expression, DataScope.OrgMatchingMode mode) {
        return new ScopeGrant(tenant, orgId + "|" + mode.getExpression() + expression, allow);
    }

    private static Set<String> grantField(Collection<ScopeGrant> grants, boolean allow, boolean tenant) {
        if (grants == null) return null;
        return grants.stream().filter(g -> tenant || g.allow == allow)
                .map(g -> tenant ? g.tenant : g.rule).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Controller
    @CRUD(title = "菜单测试", desc = "菜单描述")
    @Tag(name = "菜单Tag", description = "Tag描述")
    @RequestMapping("/api/menu")
    @ResAuthorize(domain = "sys", type = "menu", res = "page")
    static class MenuController {

        @GetMapping("/list")
        @Operation(summary = "菜单入口", description = "主菜单")
        public void list() {
        }

        @PostMapping("/create")
        @CRUD.Op(label = "新增按钮", desc = "新增备注", level = CRUD.Level.Primary)
        @Operation(summary = "新增", description = "创建记录")
        public void create() {
        }

        @RequestMapping("/delete/{id}")
        @CRUD.Op(name = "deleteOp")
        @Operation(summary = "删除", description = "删除记录")
        public void delete() {
        }

        @PostMapping(path = "update/")
        @CRUD.Op
        @Operation(summary = "更新", description = "更新记录")
        public void update() {
        }

        @PostMapping("/export")
        @Operation(summary = "导出", description = "不应生成按钮")
        public void export() {
        }
    }

    @Controller
    @Tag(name = "方法权限", description = "方法权限测试")
    @RequestMapping("/api/method")
    @ResAuthorize(domain = "sys", type = "method", res = "case")
    static class MethodAccessController {

        @GetMapping("/view")
        @Operation(summary = "查看", description = "查看记录")
        public void view() {
        }

        @PostMapping("/approve")
        @Operation(summary = "审批", description = "审批记录")
        @ResAuthorize(action = "approve")
        public void approve() {
        }

        @PostMapping("/authenticated")
        @Operation(summary = "登录可访问", description = "只要求登录")
        @ResAuthorize(onlyRequireAuthenticated = true)
        public void authenticatedOnly() {
        }

        @PostMapping("/ignored")
        @Operation(summary = "忽略权限", description = "忽略授权")
        @ResAuthorize(ignored = true)
        public void ignored() {
        }

        @PostMapping("/secret")
        @Operation(summary = "secret", description = "高密级操作")
        @ResAuthorize(action = "secret", confidentialLevel = 6000)
        public void secret() {
        }
    }

    private static List<TestOrg> baseOrgTree() {
        return Arrays.asList(
                new TestOrg("A", null, "T1", "A"),
                new TestOrg("A1", "A", "T1", "A1"),
                new TestOrg("A2", "A", "T1", "A2"),
                new TestOrg("A21", "A2", "T1", "A21"),
                new TestOrg("B", null, "T1", "B"),
                new TestOrg("B1", "B", "T1", "B1")
        );
    }

    private static List<TestOrg> largeLayeredOrgTree(String rootId, String tenantId, int size, int levels) {
        List<TestOrg> orgList = new ArrayList<>(size);
        orgList.add(new TestOrg(rootId, null, tenantId, rootId));

        List<String> previousLevelIds = Collections.singletonList(rootId);
        int remaining = size - 1;
        int nextIndex = 1;

        for (int level = 1; level < levels && remaining > 0; level++) {
            int remainingLevels = levels - level;
            int levelSize = Math.max(1, remaining / remainingLevels);
            List<String> currentLevelIds = new ArrayList<>(levelSize);

            for (int levelIndex = 0; levelIndex < levelSize; levelIndex++) {
                String orgId = rootId + "-" + nextIndex++;
                String parentId = previousLevelIds.get(levelIndex % previousLevelIds.size());
                orgList.add(new TestOrg(orgId, parentId, tenantId, orgId));
                currentLevelIds.add(orgId);
            }

            remaining -= levelSize;
            previousLevelIds = currentLevelIds;
        }

        return orgList;
    }

    private static int countTreeNodes(Collection<? extends RbacOrgInfo> roots) {
        int count = 0;
        Deque<RbacOrgInfo> stack = new ArrayDeque<>(roots);

        while (!stack.isEmpty()) {
            RbacOrgInfo org = stack.pop();
            count++;
            Collection<RbacOrgInfo> children = org.getChildren();
            if (children != null) {
                children.forEach(stack::push);
            }
        }

        return count;
    }

    private static class TestAuthorizeService extends AbstractRbacAuthorizeService implements RbacMethodService {
        private final Map<String, ResConditionAction> actionMap = new LinkedHashMap<>();

        TestAuthorizeService addAction(String permission, ResConditionAction action) {
            actionMap.put(permission, action);
            return this;
        }

        int verifyExpressionCacheSize() {
            return verifyExpressionCache.size();
        }

        @Override
        protected ResConditionAction getAction(String requirePermission) {
            return actionMap.get(requirePermission);
        }

        @Override
        protected Map<String, ResConditionAction> getMatchActions(String requirePermissionPattern) {
            Map<String, ResConditionAction> result = new LinkedHashMap<>();
            actionMap.forEach((permission, action) -> {
                if (textPatternMatch(requirePermissionPattern, permission)) {
                    result.put(permission, action);
                }
            });
            return result;
        }

        @Override
        public boolean canAccess(Serializable principal, Object beanOrClass, Method method) {
            ResAuthorize resAuthorize = RbacUtils.getMethodResAuthorize(beanOrClass, method);
            return resAuthorize == null || isAuthorized(principal, resAuthorize);
        }
    }

    private static class StubRbacBaseService implements RbacBaseService {
        private final TestRbacUser user;
        private final Map<String, TestRbacRole> roleMap = new LinkedHashMap<>();
        private Collection<String> userPermissions = new LinkedHashSet<>();
        private List<TestOrg> orgList = Collections.emptyList();
        private List<TestTenant> tenantList = Collections.emptyList();
        private List<TestDomain> domainList = Collections.emptyList();
        private final AtomicInteger domainListLoads = new AtomicInteger();
        private final AtomicInteger domainLoads = new AtomicInteger();
        private Boolean lastDomainEffectFlag;

        StubRbacBaseService(TestRbacUser user) {
            this.user = user;
            if (user.getTenantId() != null) {
                tenantList = Collections.singletonList(new TestTenant(user.getTenantId(), "User tenant"));
            }
        }

        StubRbacBaseService setOrgList(Collection<TestOrg> orgList) {
            this.orgList = orgList == null ? Collections.emptyList() : new ArrayList<>(orgList);
            return this;
        }

        StubRbacBaseService setTenantList(Collection<TestTenant> tenantList) {
            this.tenantList = tenantList == null ? Collections.emptyList() : new ArrayList<>(tenantList);
            return this;
        }

        StubRbacBaseService setDomainList(Collection<TestDomain> domains) {
            domainList = domains == null ? Collections.emptyList() : new ArrayList<>(domains);
            return this;
        }

        @Override
        public <DOMAIN extends RbacDomainInfo> Collection<DOMAIN> loadAllDomainList(boolean onlyLoadEffectDomain) {
            domainListLoads.incrementAndGet();
            lastDomainEffectFlag = onlyLoadEffectDomain;
            return (Collection<DOMAIN>) domainList;
        }

        @Override
        public <DOMAIN extends RbacDomainInfo> DOMAIN loadDomain(Serializable domainPrincipal) {
            domainLoads.incrementAndGet();
            return (DOMAIN) domainList.stream().filter(Objects::nonNull)
                    .filter(domain -> Objects.equals(domain.getId(), domainPrincipal)).findFirst().orElse(null);
        }

        void setUserPermissions(Collection<String> permissions) {
            this.userPermissions = permissions == null
                    ? Collections.emptySet()
                    : new LinkedHashSet<>(permissions);
        }

        void registerRole(TestRbacRole role) {
            roleMap.put(role.getCode(), role);
        }

        @Override
        public String encryptUserPwd(String pwd) {
            return pwd;
        }

        @Override
        public <U extends RbacUserInfo> U loadUser(Serializable tenantId, String account) {
            return (U) user;
        }

        @Override
        public <U extends RbacUserInfo> U loadUser(Serializable userPrincipal) {
            if (userPrincipal instanceof RbacUserInfo) {
                return (U) userPrincipal;
            }
            return (U) user;
        }

        @Override
        public <U extends RbacUserInfo> U auditUser(U userInfo) throws AuthorizationException {
            return userInfo;
        }

        @Override
        public <U extends RbacUserInfo> U auditUserLogin(U userInfo, Serializable tenantId, String loginPwd, String loginDomain, String loginIp, String loginDeviceType, Map<String, Serializable> exLoginParams) throws AuthorizationException {
            return userInfo;
        }

        @Override
        public <ORG extends RbacOrgInfo> ORG loadOrg(Serializable orgPrincipal) {
            return (ORG) orgList.stream()
                    .filter(org -> Objects.equals(org.getId(), orgPrincipal))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public <ORG extends RbacOrgInfo> List<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyLoadEffectOrg) {
            return (List<ORG>) orgList.stream()
                    .filter(org -> Objects.equals(org.getTenantId(), tenantId))
                    .collect(Collectors.toList());
        }

        @Override
        public <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable tenantPrincipal) {
            return (TENANT) tenantList.stream()
                    .filter(tenant -> Objects.equals(tenant.getId(), tenantPrincipal))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public <TENANT extends RbacTenantInfo> Collection<TENANT> loadAllTenantList(boolean onlyLoadEffectTenant) {
            return (Collection<TENANT>) tenantList;
        }

        @Override
        public <R extends RbacRoleInfo> R loadRole(Serializable rolePrincipal) {
            if (rolePrincipal instanceof RbacRoleInfo) {
                return (R) rolePrincipal;
            }
            return (R) roleMap.get(Objects.toString(rolePrincipal, ""));
        }

        @Override
        public <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable tenantId, boolean onlyLoadEffectRole) {
            return (Collection<R>) roleMap.values().stream()
                    .filter(role -> role.getTenantId() == null || Objects.equals(role.getTenantId(), tenantId))
                    .collect(Collectors.toList());
        }

        @Override
        public Collection<String> loadUserRoleCodeList(Serializable userPrincipal) {
            return user.getRoleList().stream().map(Objects::toString).collect(Collectors.toCollection(LinkedHashSet::new));
        }

        @Override
        public Collection<String> loadUserPermissionExprList(Serializable userPrincipal) {
            return userPermissions.isEmpty()
                    ? RbacBaseService.super.loadUserPermissionExprList(userPrincipal)
                    : new LinkedHashSet<>(userPermissions);
        }

        @Override
        public Collection<String> loadRolePermissionList(Serializable tenantId, Collection<String> roleCodeList) {
            return roleMap.values().stream()
                    .filter(role -> roleCodeList.contains(role.getCode()))
                    .filter(role -> role.getTenantId() == null || Objects.equals(role.getTenantId(), tenantId))
                    .map(TestRbacRole::getPermissionList)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }

    private static class DefaultRoleHelperRbacBaseService implements RbacBaseService {
        private final StubRbacBaseService delegate;

        DefaultRoleHelperRbacBaseService(TestRbacUser user) {
            delegate = new StubRbacBaseService(user);
        }

        void registerRole(TestRbacRole role) {
            delegate.registerRole(role);
        }

        @Override
        public String encryptUserPwd(String pwd) {
            return delegate.encryptUserPwd(pwd);
        }

        @Override
        public <U extends RbacUserInfo> U loadUser(Serializable tenantId, String account) {
            return delegate.loadUser(tenantId, account);
        }

        @Override
        public <U extends RbacUserInfo> U loadUser(Serializable userPrincipal) {
            return delegate.loadUser(userPrincipal);
        }

        @Override
        public <U extends RbacUserInfo> U auditUser(U userInfo) throws AuthorizationException {
            return delegate.auditUser(userInfo);
        }

        @Override
        public <U extends RbacUserInfo> U auditUserLogin(U userInfo, Serializable tenantId, String loginPwd, String loginDomain, String loginIp, String loginDeviceType, Map<String, Serializable> exLoginParams) throws AuthorizationException {
            return delegate.auditUserLogin(userInfo, tenantId, loginPwd, loginDomain, loginIp, loginDeviceType, exLoginParams);
        }

        @Override
        public <DOMAIN extends RbacDomainInfo> Collection<DOMAIN> loadAllDomainList(boolean onlyLoadEffectDomain) {
            return delegate.loadAllDomainList(onlyLoadEffectDomain);
        }

        @Override
        public <DOMAIN extends RbacDomainInfo> DOMAIN loadDomain(Serializable domainPrincipal) {
            return delegate.loadDomain(domainPrincipal);
        }

        @Override
        public <TENANT extends RbacTenantInfo> Collection<TENANT> loadAllTenantList(boolean onlyLoadEffectTenant) {
            return delegate.loadAllTenantList(onlyLoadEffectTenant);
        }

        @Override
        public <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable tenantPrincipal) {
            return delegate.loadTenant(tenantPrincipal);
        }

        @Override
        public <ORG extends RbacOrgInfo> ORG loadOrg(Serializable orgPrincipal) {
            return delegate.loadOrg(orgPrincipal);
        }

        @Override
        public <ORG extends RbacOrgInfo> Collection<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyLoadEffectOrg) {
            return delegate.loadTenantOrgList(tenantId, onlyLoadEffectOrg);
        }

        @Override
        public <R extends RbacRoleInfo> R loadRole(Serializable rolePrincipal) {
            return delegate.loadRole(rolePrincipal);
        }

        @Override
        public <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable tenantId, boolean onlyLoadEffectRole) {
            return delegate.loadTenantRoleList(tenantId, onlyLoadEffectRole);
        }

        @Override
        public Collection<String> loadUserRoleCodeList(Serializable userPrincipal) {
            return RbacBaseService.super.loadUserRoleCodeList(userPrincipal);
        }

        @Override
        public Collection<String> loadUserPermissionExprList(Serializable userPrincipal) {
            return RbacBaseService.super.loadUserPermissionExprList(userPrincipal);
        }

        @Override
        public Collection<String> loadRolePermissionList(Serializable tenantId, Collection<String> roleCodeList) {
            return RbacBaseService.super.loadRolePermissionList(tenantId, roleCodeList);
        }
    }

    private static class MultiUserRbacBaseService extends StubRbacBaseService {
        private final Map<String, TestRbacUser> userMap = new LinkedHashMap<>();

        MultiUserRbacBaseService(TestRbacUser firstUser, TestRbacUser... users) {
            super(firstUser);
            registerUser(firstUser);
            Arrays.stream(users).forEach(this::registerUser);
        }

        private void registerUser(TestRbacUser user) {
            userMap.put(Objects.toString(user.getId(), ""), user);
        }

        @Override
        public <U extends RbacUserInfo> U loadUser(Serializable tenantId, String account) {
            return (U) userMap.values().stream()
                    .filter(user -> Objects.equals(user.getTenantId(), tenantId))
                    .filter(user -> Objects.equals(user.getLoginName(), account))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public <U extends RbacUserInfo> U loadUser(Serializable userPrincipal) {
            if (userPrincipal instanceof RbacUserInfo) {
                return (U) userPrincipal;
            }
            return (U) userMap.get(Objects.toString(userPrincipal, ""));
        }
    }

    private static class TestRbacUser implements RbacUserInfo {
        String domainId;
        @Override public String getDomainId() { return domainId; }
        private final String id;
        private final String loginName;
        private final String tenantId;
        private final String type;
        private final List<Serializable> roleList;
        private final Integer confidentialDataAccessLevel;
        private final String orgId;
        private final Collection<ScopeGrant> orgScopeList;
        private final Map<String, Object> transientExInfo = new LinkedHashMap<>();

        TestRbacUser(String id, String loginName, String tenantId, String type, List<? extends Serializable> roleList, Integer confidentialDataAccessLevel) {
            this(id, loginName, tenantId, type, roleList, confidentialDataAccessLevel, null, null);
        }

        TestRbacUser(String id, String loginName, String tenantId, String type, List<? extends Serializable> roleList, Integer confidentialDataAccessLevel, String orgId, Collection<ScopeGrant> orgScopeList) {
            this.id = id;
            this.loginName = loginName;
            this.tenantId = tenantId;
            this.type = type;
            this.roleList = new ArrayList<>(roleList);
            this.confidentialDataAccessLevel = confidentialDataAccessLevel;
            this.orgId = orgId;
            this.orgScopeList = orgScopeList == null ? null : new ArrayList<>(orgScopeList);
        }

        @Override
        public <ID extends Serializable> ID getId() {
            return (ID) id;
        }

        @Override
        public <TID extends Serializable> TID getTenantId() {
            return (TID) tenantId;
        }

        @Override
        public <ORG_ID extends Serializable> ORG_ID getOrgId() {
            return (ORG_ID) orgId;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getLoginName() {
            return loginName;
        }

        @Override
        public String getEmail() {
            return loginName + "@example.com";
        }

        @Override
        public String getTelephone() {
            return "13800000000";
        }

        @Override
        public Integer getConfidentialDataAccessLevel() {
            return confidentialDataAccessLevel;
        }

        @Override
        public <ROLE extends Serializable> List<ROLE> getRoleList() {
            return (List<ROLE>) roleList;
        }

        @Override
        public Set<String> getOrgScopeList() {
            return grantField(orgScopeList, true, false);
        }

        @Override
        public Set<String> getDeniedOrgScopeList() {
            return grantField(orgScopeList, false, false);
        }

        @Override
        public Set<String> getTenantScopeList() {
            return grantField(orgScopeList, true, true);
        }

        @Override
        public Set<String> getDeniedTenantScopeList() { return null; }

        @Override
        public Set<String> getDomainScopeList() { return null; }

        @Override
        public Set<String> getDeniedDomainScopeList() { return null; }

        @Override
        public Map<String, Object> getTransientExInfo() {
            return transientExInfo;
        }
    }

    private static class TestRbacRole implements RbacRoleInfo {
        String domainId;
        @Override public String getDomainId() { return domainId; }
        private final String id;
        private final String code;
        private final String tenantId;
        private final List<String> permissionList;
        private final List<String> exclusiveRoleList;
        private final List<String> coexistRoleList;
        private final Integer confidentialLevel;
        private final Integer confidentialDataAccessLevel;
        private final Collection<ScopeGrant> orgScopeList;
        private final Map<String, Object> transientExInfo = new LinkedHashMap<>();

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel) {
            this(id, code, tenantId, permissionList, exclusiveRoleList, confidentialDataAccessLevel, Collections.emptyList());
        }

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel, Collection<ScopeGrant> orgScopeList) {
            this(id, code, tenantId, permissionList, exclusiveRoleList, confidentialDataAccessLevel, orgScopeList, null);
        }

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel, Collection<ScopeGrant> orgScopeList, Integer confidentialLevel) {
            this(id, code, tenantId, permissionList, exclusiveRoleList, confidentialDataAccessLevel, orgScopeList, confidentialLevel, Collections.emptyList());
        }

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel, Collection<ScopeGrant> orgScopeList, Integer confidentialLevel, Collection<String> coexistRoleList) {
            this.id = id;
            this.code = code;
            this.tenantId = tenantId;
            this.permissionList = permissionList == null ? Collections.emptyList() : new ArrayList<>(permissionList);
            this.exclusiveRoleList = exclusiveRoleList == null ? Collections.emptyList() : new ArrayList<>(exclusiveRoleList);
            this.coexistRoleList = coexistRoleList == null ? Collections.emptyList() : new ArrayList<>(coexistRoleList);
            this.confidentialLevel = confidentialLevel;
            this.confidentialDataAccessLevel = confidentialDataAccessLevel;
            this.orgScopeList = orgScopeList == null ? null : new ArrayList<>(orgScopeList);
        }

        @Override
        public <ID extends Serializable> ID getId() {
            return (ID) id;
        }

        @Override
        public <TID extends Serializable> TID getTenantId() {
            return (TID) tenantId;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public Collection<String> getExclusiveRoleList() {
            return exclusiveRoleList;
        }

        @Override
        public Collection<String> getCoexistRoleList() {
            return coexistRoleList;
        }

        @Override
        public Collection<String> getPermissionList() {
            return permissionList;
        }

        @Override
        public Integer getConfidentialLevel() {
            return confidentialLevel;
        }

        @Override
        public Integer getConfidentialDataAccessLevel() {
            return confidentialDataAccessLevel;
        }

        @Override
        public Set<String> getOrgScopeList() {
            return grantField(orgScopeList, true, false);
        }

        @Override
        public Set<String> getDeniedOrgScopeList() {
            return grantField(orgScopeList, false, false);
        }

        @Override
        public Set<String> getTenantScopeList() {
            return grantField(orgScopeList, true, true);
        }

        @Override
        public Set<String> getDeniedTenantScopeList() { return null; }

        @Override
        public Set<String> getDomainScopeList() { return null; }

        @Override
        public Set<String> getDeniedDomainScopeList() { return null; }

        @Override
        public Map<String, Object> getTransientExInfo() {
            return transientExInfo;
        }
    }

    private static class DisabledTestRbacRole extends TestRbacRole {

        DisabledTestRbacRole(String id, String code, String tenantId, List<String> permissionList,
                             List<String> exclusiveRoleList, Integer confidentialDataAccessLevel) {
            super(id, code, tenantId, permissionList, exclusiveRoleList, confidentialDataAccessLevel);
        }

        @Override
        public boolean isEnable() {
            return false;
        }
    }

    private static class DisabledTestOrg extends TestOrg {

        DisabledTestOrg(String id, String parentId, String tenantId, String name) {
            super(id, parentId, tenantId, name);
        }

        @Override
        public boolean isEnable() {
            return false;
        }
    }

    private static class TestOrg implements RbacOrgInfo {
        String domainId;
        @Override public String getDomainId() { return domainId; }
        private String id;
        private String parentId;
        private String tenantId;
        private String name;
        private Integer confidentialLevel;
        private String nodePath;
        private Collection<TestOrg> children = new ArrayList<>();

        TestOrg() {
        }

        TestOrg(String id, String parentId, String tenantId, String name) {
            this(id, parentId, tenantId, name, null);
        }

        TestOrg(String id, String parentId, String tenantId, String name, Integer confidentialLevel) {
            this.id = id;
            this.parentId = parentId;
            this.tenantId = tenantId;
            this.name = name;
            this.confidentialLevel = confidentialLevel;
        }

        @Override
        public <ID extends Serializable> ID getId() {
            return (ID) id;
        }

        @Override
        public <TID extends Serializable> TID getTenantId() {
            return (TID) tenantId;
        }

        @Override
        public <ID extends Serializable> ID getParentId() {
            return (ID) parentId;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Integer getConfidentialLevel() {
            return confidentialLevel;
        }

        @Override
        public String getNodePath() {
            return nodePath;
        }

        @Override
        public <ORG extends RbacOrgInfo> Collection<ORG> getChildren() {
            return (Collection<ORG>) children;
        }

        public void setNodePath(String nodePath) {
            this.nodePath = nodePath;
        }

        public void setChildren(Collection<TestOrg> children) {
            this.children = children;
        }
    }

    private static class SetOrg implements RbacOrgInfo {
        String domainId;
        @Override public String getDomainId() { return domainId; }
        private String id;
        private String parentId;
        private String tenantId;
        private String name;
        private String nodePath;
        private Set<SetOrg> children = new LinkedHashSet<>();

        SetOrg() {
        }

        SetOrg(String id, String parentId, String tenantId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.tenantId = tenantId;
            this.name = name;
        }

        @Override
        public <ID extends Serializable> ID getId() {
            return (ID) id;
        }

        @Override
        public <TID extends Serializable> TID getTenantId() {
            return (TID) tenantId;
        }

        @Override
        public <ID extends Serializable> ID getParentId() {
            return (ID) parentId;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getNodePath() {
            return nodePath;
        }

        @Override
        public <ORG extends RbacOrgInfo> Set<ORG> getChildren() {
            return (Set<ORG>) children;
        }

        public void setNodePath(String nodePath) {
            this.nodePath = nodePath;
        }

        public void setChildren(Set<SetOrg> children) {
            this.children = children;
        }
    }

    private static class TestDomain implements RbacDomainInfo, com.levin.commons.dao.domain.LogicDeletableObject {
        private final String id;
        private final Integer confidentialLevel;

        TestDomain(String id) { this(id, null); }
        TestDomain(String id, Integer confidentialLevel) {
            this.id = id;
            this.confidentialLevel = confidentialLevel;
        }
        @Override public <ID extends Serializable> ID getId() { return (ID) id; }
        @Override public String getName() { return id; }
        @Override public boolean isDeleted() { return false; }
        @Override public Integer getConfidentialLevel() { return confidentialLevel; }
    }

    private static class TestTenant implements RbacTenantInfo {
        String domainId;
        @Override public String getDomainId() { return domainId; }
        private final String id;
        private final String name;
        private final Integer confidentialLevel;

        TestTenant(String id, String name) {
            this(id, name, null);
        }

        TestTenant(String id, String name, Integer confidentialLevel) {
            this.id = id;
            this.name = name;
            this.confidentialLevel = confidentialLevel;
        }

        @Override
        public <ID extends Serializable> ID getId() {
            return (ID) id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Integer getConfidentialLevel() {
            return confidentialLevel;
        }
    }

    private static class ExpiredTestTenant extends TestTenant {

        ExpiredTestTenant(String id, String name) {
            super(id, name);
        }

        @Override
        public java.time.LocalDateTime getExpiredTime() {
            return java.time.LocalDateTime.now().minusDays(1);
        }
    }
}
