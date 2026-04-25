package com.levin.commons.rbac;

import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.utils.ObjectWrapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.time.Duration;
import java.util.*;
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
    void shouldFindFirstExclusiveRolePair() {
        // 业务规则：角色互斥配置生效时，应返回第一组冲突角色。
        TestRbacRole financeRole = new TestRbacRole("R1", "R_FINANCE", "T1",
                Collections.emptyList(), Collections.singletonList("R_AUDIT"), 100);
        TestRbacRole auditRole = new TestRbacRole("R2", "R_AUDIT", "T1",
                Collections.emptyList(), Collections.emptyList(), 100);

        Collection<RbacRoleInfo> pair = authorizeService.findFirstExclusiveRolePair(Arrays.asList(financeRole, auditRole));
        Set<String> codes = pair.stream().map(RbacRoleInfo::getCode).collect(Collectors.toSet());

        assertEquals(2, pair.size(), "互斥角色冲突应返回两个角色");
        assertTrue(codes.contains("R_FINANCE"), "互斥角色结果应包含 R_FINANCE");
        assertTrue(codes.contains("R_AUDIT"), "互斥角色结果应包含 R_AUDIT");
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
                Collections.singletonList(scope("A", true, OrgScope.Scope.All))
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
    void shouldPreferUserOrgScopeOverRoleOrgScope() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U2",
                "bob",
                "T1",
                "OPS",
                Arrays.asList("R_MANAGER"),
                5000,
                "B",
                Collections.singletonList(scope("B", true, OrgScope.Scope.All))
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
                Collections.singletonList(scope("A", true, OrgScope.Scope.All))
        ));

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("B", "B1"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "用户自己定义了组织范围时，应优先于角色上的组织范围");
    }

    @Test
    void shouldApplyAntPathOrgScopeWithDenyOverride() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3",
                "cindy",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Arrays.asList(
                        scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All),
                        customScope("A", false, "/A2/**")
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A", "A1", "B", "B1"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "allow all 后叠加 deny ant path 时，应移除命中的子树");
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
                        scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All),
                        scope(OrgScope.ALL_ROOT_ORG, false, OrgScope.Scope.All)
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
                        scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All),
                        scope("A", true, OrgScope.Scope.OnlySelf)
                )
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        assertIterableEquals(Arrays.asList("A", "A1", "A2", "A21", "B", "B1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "allow all 且没有任何 deny 时，应直接返回全部已加载组织");
    }

    @Test
    void shouldSupportCrossTenantOrgScopeByTenantId() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U302",
                "carol",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("T2", OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All))
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
                "OrgScope 指定 tenantExpression 时，应只返回命中的租户组织");
    }

    @Test
    void shouldLoadUserCanAccessTenantListByTenantExpression() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U3021",
                "carol",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("T2", OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Arrays.asList(
                        new TestTenant("T1", "Tenant1"),
                        new TestTenant("T2", "Tenant2"),
                        new TestTenant("T3", "Tenant3")
                ));

        assertIterableEquals(Collections.singletonList("T2"),
                scopedService.loadUserAccessibleTenantList(scopedUser, true).stream().map(tenant -> Objects.toString(tenant.getId(), "")).collect(Collectors.toList()),
                "loadUserCanAccessTenantList 应按 tenantExpression 返回可访问租户");
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
                Collections.singletonList(scope(OrgScope.DEFAULT_TENANT, OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All))
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
    void shouldMatchOnlyPublicOrgWhenTenantExpressionIsBlank() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U304",
                "tenant-user",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(scope("", OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setTenantList(Collections.singletonList(new TestTenant("T1", "Tenant1")))
                .setOrgList(Arrays.asList(
                        new TestOrg("P", null, null, "Public"),
                        new TestOrg("P1", "P", null, "PublicChild"),
                        new TestOrg("A", null, "T1", "A"),
                        new TestOrg("A1", "A", "T1", "A1")
                ));

        assertIterableEquals(Arrays.asList("P", "P1"),
                scopedService.loadUserOrgList(scopedUser, false).stream().map(org -> Objects.toString(org.getId(), "")).collect(Collectors.toList()),
                "tenantExpression 为空时，应只命中公共组织");
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
                Collections.singletonList(customScope("A", true, "return org.name == 'A2' || relativeIdPath == '/A2/A21/'", OrgScope.ExpressionType.Groovy))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A2", "A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义 Groovy 表达式应支持通过 org 和相对路径过滤组织");
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
                Collections.singletonList(customScope("A", true, "name == 'A1' or #relativeNamePath == '/A2/'", OrgScope.ExpressionType.SpringEL))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Arrays.asList("A1", "A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义 Spring EL 表达式应支持根对象 org 和上下文变量");
    }

    @Test
    void shouldDistinguishNameAntPathFromIdAntPath() {
        TestRbacUser scopedUser = new TestRbacUser(
                "U321",
                "helen",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/A2/", OrgScope.ExpressionType.NameAntPath))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A2"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "NameAntPath 应基于名称路径匹配，而不是基于组织 ID 路径匹配");
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
                Collections.singletonList(customScope("A", true, "/A2/A21/", OrgScope.ExpressionType.IdAntPath))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A21"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "IdAntPath 应以 OrgScope.getOrgId() 作为起点构造相对路径");

        TestRbacUser absolutePathUser = new TestRbacUser(
                "U323",
                "jack",
                "T1",
                "A",
                Collections.emptyList(),
                5000,
                "A",
                Collections.singletonList(customScope("A", true, "/A/A2/A21/", OrgScope.ExpressionType.IdAntPath))
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
                Collections.singletonList(customScope("A", true, "#user.loginName == 'grace' and #org.name == 'A1'", OrgScope.ExpressionType.SpringEL))
        );

        StubRbacBaseService scopedService = new StubRbacBaseService(scopedUser)
                .setOrgList(baseOrgTree());

        Collection<TestOrg> orgList = scopedService.loadUserOrgList(scopedUser, false);

        assertIterableEquals(Collections.singletonList("A1"),
                orgList.stream().map(TestOrg::getId).collect(Collectors.toList()),
                "自定义表达式上下文应暴露当前用户变量 user");
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
                Collections.singletonList(scope(OrgScope.ALL_ROOT_ORG, true, OrgScope.Scope.All))
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
                Collections.singletonList(scope("ROOT", true, OrgScope.Scope.All))
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
                customScope("A", true, "/A2/", OrgScope.ExpressionType.IdAntPath),
                customScope("A", true, "/A2/", OrgScope.ExpressionType.NameAntPath)
        ));

        assertEquals(2, merged.size(),
                "相同表达式文本但不同 expressionType 的规则不应被误合并");
    }

    @Test
    void shouldKeepScopesWithSameExpressionButDifferentTenantIds() {
        StubRbacBaseService scopedService = new StubRbacBaseService(user);

        Collection<OrgScope> merged = scopedService.mergeOrgScopeList(Arrays.asList(
                customScope("T1", "A", true, "/A2/", OrgScope.ExpressionType.IdAntPath),
                customScope("T2", "A", true, "/A2/", OrgScope.ExpressionType.IdAntPath)
        ));

        assertEquals(2, merged.size(),
                "相同表达式文本但 tenantExpression 不同的规则不应被误合并");
    }

    private static SimpleOrgScope scope(String orgId, boolean allow, OrgScope.Scope scope) {
        return new SimpleOrgScope()
                .setOrgId(orgId)
                .setAllow(allow)
                .setScope(scope);
    }

    private static SimpleOrgScope scope(String tenantExpression, String orgId, boolean allow, OrgScope.Scope scope) {
        return new SimpleOrgScope()
                .setTenantExpression(tenantExpression)
                .setOrgId(orgId)
                .setAllow(allow)
                .setScope(scope);
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
                .setExpressionType(expressionType)
                .setOrgScopeExpression(expression);
    }

    private static SimpleOrgScope customScope(String tenantExpression, String orgId, boolean allow, String expression, OrgScope.ExpressionType expressionType) {
        return new SimpleOrgScope()
                .setTenantExpression(tenantExpression)
                .setOrgId(orgId)
                .setAllow(allow)
                .setExpressionType(expressionType)
                .setOrgScopeExpression(expression);
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

    private static class TestAuthorizeService extends AbstractRbacAuthorizeService {
        private final Map<String, ResConditionAction> actionMap = new LinkedHashMap<>();

        TestAuthorizeService addAction(String permission, ResConditionAction action) {
            actionMap.put(permission, action);
            return this;
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
            this.id = id;
            this.code = code;
            this.tenantId = tenantId;
            this.permissionList = permissionList == null ? Collections.emptyList() : new ArrayList<>(permissionList);
            this.exclusiveRoleList = exclusiveRoleList == null ? Collections.emptyList() : new ArrayList<>(exclusiveRoleList);
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
