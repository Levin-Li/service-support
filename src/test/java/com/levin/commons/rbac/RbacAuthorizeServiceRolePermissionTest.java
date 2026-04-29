package com.levin.commons.rbac;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levin.commons.service.exception.AuthorizationException;
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
                Collections.singletonList(scope("A", true, OrgScope.ScopeMatchingPattern.All))
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

            List<MenuItem.OpButton> opButtonList = menu.getOpButtonList();
            assertEquals(3, opButtonList.size(), "只有标注 @CRUD.Op 的控制器方法才应生成操作按钮");

            MenuItem.OpButton createButton = opButtonList.get(0);
            assertEquals("/api/menu/create", createButton.getApiUrl());
            assertEquals("新增按钮", createButton.getLabel());
            assertEquals("sys:menu:page:新增", createButton.getRequireAuthorization());
            assertEquals("新增备注", createButton.getRemark());
            assertFalse(createButton.isDisabled());

            MenuItem.OpButton deleteButton = opButtonList.get(1);
            assertEquals("/api/menu/delete/{id}", deleteButton.getApiUrl());
            assertEquals("deleteOp", deleteButton.getLabel());
            assertEquals("sys:menu:page:删除", deleteButton.getRequireAuthorization());
            assertEquals("删除记录", deleteButton.getRemark());
            assertFalse(deleteButton.isDisabled());

            MenuItem.OpButton updateButton = opButtonList.get(2);
            assertEquals("/api/menu/update", updateButton.getApiUrl());
            assertEquals("更新", updateButton.getLabel());
            assertEquals("sys:menu:page:更新", updateButton.getRequireAuthorization());
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
            assertEquals("/api/menu/create", menuNode.path("opButtonList").get(0).path("apiUrl").asText());
            assertEquals("sys:menu:page:新增", menuNode.path("opButtonList").get(0).path("requireAuthorization").asText());
            assertEquals("/api/menu/delete/{id}", menuNode.path("opButtonList").get(1).path("apiUrl").asText());
            assertEquals("sys:menu:page:删除", menuNode.path("opButtonList").get(1).path("requireAuthorization").asText());
            assertEquals("/api/menu/update", menuNode.path("opButtonList").get(2).path("apiUrl").asText());
            assertEquals("sys:menu:page:更新", menuNode.path("opButtonList").get(2).path("requireAuthorization").asText());
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
                Collections.singletonList(scope("A", true, OrgScope.ScopeMatchingPattern.All))
        ));

        assertEquals("A", scopedService.getUserDataScope(scopedUser).getOrgScopeList()
                .iterator().next().getOrgId());

        scopedService.registerRole(new TestRbacRole(
                "R7C2",
                "R_DYNAMIC",
                "T1",
                Collections.emptyList(),
                Collections.emptyList(),
                10,
                Collections.singletonList(scope("B", true, OrgScope.ScopeMatchingPattern.All))
        ));

        assertEquals("B", scopedService.getUserDataScope(scopedUser).getOrgScopeList()
                .iterator().next().getOrgId(),
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
                Collections.singletonList(scope("B", true, OrgScope.ScopeMatchingPattern.All))
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
                Collections.singletonList(scope("A", true, OrgScope.ScopeMatchingPattern.All))
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
                        scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All),
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
    void shouldApplyOnlyDirectChildScopeMatchingPattern() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3A",
                "direct-child",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("A", true, OrgScope.ScopeMatchingPattern.OnlyDirectChild))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "OnlyDirectChild 应只包含直接子节点，不包含本节点和孙节点");
    }

    @Test
    void shouldApplySelfAndDirectChildScopeMatchingPattern() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3B",
                "self-and-direct-child",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("A", true, OrgScope.ScopeMatchingPattern.SelfAndDirectChild))
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
                Collections.singletonList(scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.OnlyDirectChild))
        );
        TestRbacUser selfAndDirectChildUser = new TestRbacUser(
                "U3D",
                "all-root-self-and-direct-child",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.SelfAndDirectChild))
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
                Collections.singletonList(scope(OrgScope.USER_ORG, true, OrgScope.ScopeMatchingPattern.All))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A2", "A21"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "_USER_ORG_ 应解析为用户默认组织，并按指定 ScopeMatchingPattern 继续扩展");
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
                        scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All),
                        scope("A", false, OrgScope.ScopeMatchingPattern.SelfAndDirectChild)
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
                        scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All),
                        scope(OrgScope.ALL_ROOT_ORG, false, OrgScope.ScopeMatchingPattern.All)
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
                        scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All),
                        scope("A", true, OrgScope.ScopeMatchingPattern.OnlySelf)
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
                Collections.singletonList(scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.OnlySelf))
        );
        TestRbacUser allFromRootUser = new TestRbacUser(
                "U301B",
                "all-from-root",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                Collections.singletonList(scope("T2", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                "平台用户的 OrgScope 指定 tenantMatchingExpression 时，应只返回命中的租户组织");
    }

    @Test
    void shouldSupportTenantPathPatternForPlatformUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302P",
                "platform-pattern",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("tenant-*", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                "平台用户的 tenantMatchingExpression 应支持 Spring PathPattern 匹配多个租户");
        assertIterableEquals(Arrays.asList("A", "A1", "C"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "平台用户的 PathPattern 租户范围应只加载命中租户下的组织");
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
                        scope("T1", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All),
                        scope("T2", "ROOT", true, OrgScope.ScopeMatchingPattern.OnlySelf)
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
    void shouldLimitTenantPathPatternToOwnTenantForTenantUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302P1",
                "tenant-pattern",
                "tenant-a",
                "OPS",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("tenant-*", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                "普通租户用户即使命中 PathPattern，也只能访问自己的租户");
        assertIterableEquals(Arrays.asList("A", "A1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "普通租户用户的 PathPattern 组织范围不能扩展到其他租户");
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
                Collections.singletonList(scope(OrgScope.TENANT_GROOVY_EXPRESSION_PREFIX + "_tenant?.startsWith('tenant-')",
                        OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                "平台用户的 tenantMatchingExpression 应支持 #!groovy: 前缀脚本");
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
                Collections.singletonList(scope("_tenant == 'T2'", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("T2", "Tenant2")))
                .setOrgList(Collections.singletonList(new TestOrg("C", null, "T2", "C")));

        assertTrue(scopedService.loadUserAccessibleTenantList(scopedUser, true).isEmpty(),
                "未带 #!groovy: 前缀的脚本不应被当成 Groovy 执行");
        assertTrue(scopedService.loadUserOrgList(scopedUser, false).isEmpty(),
                "未带 #!groovy: 前缀的脚本不应产生组织范围");
    }

    @Test
    void shouldIgnoreTenantGroovyScopeForTenantUser() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302G2",
                "tenant-groovy",
                "tenant-a",
                "OPS",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope(OrgScope.TENANT_GROOVY_EXPRESSION_PREFIX + "_tenant == 'tenant-a'",
                        OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("tenant-a", "TenantA")))
                .setOrgList(Collections.singletonList(new TestOrg("A", null, "tenant-a", "A")));

        assertTrue(scopedService.loadUserAccessibleTenantList(scopedUser, true).isEmpty(),
                "普通租户用户不通过 Groovy 租户表达式扩大组织范围");
        assertTrue(scopedService.loadUserOrgList(scopedUser, false).isEmpty(),
                "普通租户用户配置 Groovy 租户表达式时应忽略该 scope");
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
                Collections.singletonList(scope("T2", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
    void shouldIgnoreAllTenantDenyScopeForTenantUserDuringOrgCalculation() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302B",
                "tenant-dave",
                "T1",
                "OPS",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope("A", true, OrgScope.ScopeMatchingPattern.All),
                        scope(OrgScope.ALL_TENANT, OrgScope.ALL_ROOT_ORG, false, OrgScope.ScopeMatchingPattern.All)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A", "A1", "A2", "A21"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "普通租户用户的计算阶段也应忽略 ALL_TENANT deny，不应把自己的合法组织范围清空");
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
                Collections.singletonList(scope("T2", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2"),
                        new TestTenant("T3", "Tenant3")
                ));

        assertIterableEquals(Collections.singletonList("T2"),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "平台用户 loadUserCanAccessTenantList 应按 tenantMatchingExpression 返回可访问租户");
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
                Collections.singletonList(scope("T2", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
    void shouldFallbackToDefaultTenantWhenNoOrgScopeForTenantList() {
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

        assertIterableEquals(Collections.singletonList("T1"),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "没有显式组织范围时，租户访问应回落到用户默认租户");
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
                Collections.singletonList(scope("A", true, OrgScope.ScopeMatchingPattern.All))
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
    void shouldDetectAllOrgAccessFromMergedDataScope() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3026",
                "all-org",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope(OrgScope.ALL_TENANT, OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
        );
        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser);

        assertTrue(scopedService.canAccessAllOrg(scopedUser),
                "允许所有租户的所有组织时，应快速判定为可访问全部组织");
    }

    @Test
    void shouldKeepGlobalAllOrgCheckFalseWhenPlatformUserHasTenantDeny() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3027",
                "all-org-with-deny",
                null,
                "PLATFORM",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope(OrgScope.ALL_TENANT, OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All),
                        scope("T2", OrgScope.ALL_ROOT_ORG, false, OrgScope.ScopeMatchingPattern.All)
                )
        );
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
                Collections.singletonList(scope(OrgScope.DEFAULT_TENANT, OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                Collections.singletonList(scope("", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                Collections.singletonList(customScope("A", true, "return _org.name == 'A2' || _relativeIdPath == '/A2/A21/'", OrgScope.ExpressionType.Groovy))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A2", "A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义 Groovy 表达式应支持通过 _org 和相对路径过滤组织");
    }

    @Test
    void shouldSupportSpringElCustomOrgScope() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U32",
                "frank",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "#_org.name == 'A1' or #_relativeNamePath == '/A2/'", OrgScope.ExpressionType.SpringEL))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义 Spring EL 表达式应支持根对象 org 和上下文变量");
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
                Collections.singletonList(customScope("A", true, "/A2/", OrgScope.ExpressionType.NamePath))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "NamePath 应基于名称路径匹配，而不是基于组织 ID 路径匹配");
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
                Collections.singletonList(customScope("A", true, "/A2/A21/", OrgScope.ExpressionType.IdPath))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "IdPath 应以 OrgScope.getOrgId() 作为起点构造相对路径");

        TestRbacUser absolutePathUser = new TestRbacUser(
                "U323",
                "jack",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/A/A2/A21/", OrgScope.ExpressionType.IdPath))
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
                Collections.singletonList(customScope("A", true, "#_user.loginName == 'grace' and #_org.name == 'A1'", OrgScope.ExpressionType.SpringEL))
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
                Collections.singletonList(scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All))
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
                        scope("T1", OrgScope.ALL_ROOT_ORG, true, OrgScope.ScopeMatchingPattern.All),
                        scope("T2", "ROOT", true, OrgScope.ScopeMatchingPattern.OnlySelf)
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
                Collections.singletonList(scope("ROOT", true, OrgScope.ScopeMatchingPattern.All))
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
    void shouldKeepScopesWithSameExpressionButDifferentExpressionTypes() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        Collection<OrgScope> merged = scopedService.mergeOrgScopeList(Arrays.asList(
                customScope("A", true, "/A2/", OrgScope.ExpressionType.IdPath),
                customScope("A", true, "/A2/", OrgScope.ExpressionType.NamePath)
        ));

        assertEquals(2, merged.size(),
                "相同表达式文本但不同 expressionType 的规则不应被误合并");
    }

    @Test
    void shouldKeepScopesWithSameExpressionButDifferentTenantIds() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        Collection<OrgScope> merged = scopedService.mergeOrgScopeList(Arrays.asList(
                customScope("T1", "A", true, "/A2/", OrgScope.ExpressionType.IdPath),
                customScope("T2", "A", true, "/A2/", OrgScope.ExpressionType.IdPath)
        ));

        assertEquals(2, merged.size(),
                "相同表达式文本但 tenantMatchingExpression 不同的规则不应被误合并");
    }

    @Test
    void shouldDropInvalidAndDuplicateOrgScopesWhenMerging() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);
        SimpleOrgScope validScope = scope("A", true, OrgScope.ScopeMatchingPattern.All);
        SimpleOrgScope duplicateScope = scope("A", true, OrgScope.ScopeMatchingPattern.All);

        Collection<OrgScope> merged = scopedService.mergeOrgScopeList(Arrays.asList(
                null,
                new SimpleOrgScope().setOrgId("").setAllow(true).setOrgScopeMatchingPattern(OrgScope.ScopeMatchingPattern.All),
                new SimpleOrgScope().setOrgId("A").setAllow(true).setOrgScopeExpression(""),
                validScope,
                duplicateScope
        ));

        assertEquals(Collections.singletonList(validScope), new ArrayList<>(merged),
                "合并组织范围时应丢弃空值、空 orgId、空表达式和完全重复项");
    }

    private static SimpleOrgScope scope(String orgId, boolean allow, OrgScope.ScopeMatchingPattern scopeMatchingPattern) {
        return new SimpleOrgScope()
                .setOrgId(orgId)
                .setAllow(allow)
                .setOrgScopeMatchingPattern(scopeMatchingPattern);
    }

    private static SimpleOrgScope scope(String tenantMatchingExpression, String orgId, boolean allow, OrgScope.ScopeMatchingPattern scopeMatchingPattern) {
        return new SimpleOrgScope()
                .setTenantMatchingExpression(tenantMatchingExpression)
                .setOrgId(orgId)
                .setAllow(allow)
                .setOrgScopeMatchingPattern(scopeMatchingPattern);
    }

    private static SimpleOrgScope customScope(String orgId, boolean allow, String expression) {
        return new SimpleOrgScope()
                .setOrgId(orgId)
                .setAllow(allow)
                .setOrgScopeExpression(expression);
    }

    private static SimpleOrgScope customScope(String orgId, boolean allow, String expression, OrgScope.ExpressionType expressionType) {
        return new SimpleOrgScope()
                .setOrgId(orgId)
                .setAllow(allow)
                .setOrgScopeExpressionType(expressionType)
                .setOrgScopeExpression(expression);
    }

    private static SimpleOrgScope customScope(String tenantMatchingExpression, String orgId, boolean allow, String expression, OrgScope.ExpressionType expressionType) {
        return new SimpleOrgScope()
                .setTenantMatchingExpression(tenantMatchingExpression)
                .setOrgId(orgId)
                .setAllow(allow)
                .setOrgScopeExpressionType(expressionType)
                .setOrgScopeExpression(expression);
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

        StubRbacBaseService(TestRbacUser user) {
            this.user = user;
        }

        StubRbacBaseService setOrgList(Collection<TestOrg> orgList) {
            this.orgList = orgList == null ? Collections.emptyList() : new ArrayList<>(orgList);
            return this;
        }

        StubRbacBaseService setTenantList(Collection<TestTenant> tenantList) {
            this.tenantList = tenantList == null ? Collections.emptyList() : new ArrayList<>(tenantList);
            return this;
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
        private final String id;
        private final String loginName;
        private final String tenantId;
        private final String type;
        private final List<Serializable> roleList;
        private final Integer confidentialDataAccessLevel;
        private final String orgId;
        private final Collection<SimpleOrgScope> orgScopeList;
        private final Map<String, Object> transientExInfo = new LinkedHashMap<>();

        TestRbacUser(String id, String loginName, String tenantId, String type, List<? extends Serializable> roleList, Integer confidentialDataAccessLevel) {
            this(id, loginName, tenantId, type, roleList, confidentialDataAccessLevel, null, Collections.emptyList());
        }

        TestRbacUser(String id, String loginName, String tenantId, String type, List<? extends Serializable> roleList, Integer confidentialDataAccessLevel, String orgId, Collection<SimpleOrgScope> orgScopeList) {
            this.id = id;
            this.loginName = loginName;
            this.tenantId = tenantId;
            this.type = type;
            this.roleList = new ArrayList<>(roleList);
            this.confidentialDataAccessLevel = confidentialDataAccessLevel;
            this.orgId = orgId;
            this.orgScopeList = orgScopeList == null ? Collections.emptyList() : new ArrayList<>(orgScopeList);
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
        public <ORG_SCOPE extends OrgScope> Collection<ORG_SCOPE> getOrgScopeList() {
            return (Collection<ORG_SCOPE>) orgScopeList;
        }

        @Override
        public Map<String, Object> getTransientExInfo() {
            return transientExInfo;
        }
    }

    private static class TestRbacRole implements RbacRoleInfo {
        private final String id;
        private final String code;
        private final String tenantId;
        private final List<String> permissionList;
        private final List<String> exclusiveRoleList;
        private final List<String> coexistRoleList;
        private final Integer confidentialLevel;
        private final Integer confidentialDataAccessLevel;
        private final Collection<SimpleOrgScope> orgScopeList;
        private final Map<String, Object> transientExInfo = new LinkedHashMap<>();

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel) {
            this(id, code, tenantId, permissionList, exclusiveRoleList, confidentialDataAccessLevel, Collections.emptyList());
        }

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel, Collection<SimpleOrgScope> orgScopeList) {
            this(id, code, tenantId, permissionList, exclusiveRoleList, confidentialDataAccessLevel, orgScopeList, null);
        }

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel, Collection<SimpleOrgScope> orgScopeList, Integer confidentialLevel) {
            this(id, code, tenantId, permissionList, exclusiveRoleList, confidentialDataAccessLevel, orgScopeList, confidentialLevel, Collections.emptyList());
        }

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel, Collection<SimpleOrgScope> orgScopeList, Integer confidentialLevel, Collection<String> coexistRoleList) {
            this.id = id;
            this.code = code;
            this.tenantId = tenantId;
            this.permissionList = permissionList == null ? Collections.emptyList() : new ArrayList<>(permissionList);
            this.exclusiveRoleList = exclusiveRoleList == null ? Collections.emptyList() : new ArrayList<>(exclusiveRoleList);
            this.coexistRoleList = coexistRoleList == null ? Collections.emptyList() : new ArrayList<>(coexistRoleList);
            this.confidentialLevel = confidentialLevel;
            this.confidentialDataAccessLevel = confidentialDataAccessLevel;
            this.orgScopeList = orgScopeList == null ? Collections.emptyList() : new ArrayList<>(orgScopeList);
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
        public <ORG_SCOPE extends OrgScope> Collection<ORG_SCOPE> getOrgScopeList() {
            return (Collection<ORG_SCOPE>) orgScopeList;
        }

        @Override
        public Map<String, Object> getTransientExInfo() {
            return transientExInfo;
        }
    }

    private static class TestOrg implements RbacOrgInfo {
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

    private static class TestTenant implements RbacTenantInfo {
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
}
