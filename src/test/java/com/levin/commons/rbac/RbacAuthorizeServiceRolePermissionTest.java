package com.levin.commons.rbac;

import com.levin.commons.service.exception.AuthorizationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
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

        StubRbacBaseService(TestRbacUser user) {
            this.user = user;
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
            return null;
        }

        @Override
        public <ORG extends RbacOrgInfo> List<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyLoadEffectOrg) {
            return Collections.emptyList();
        }

        @Override
        public <ORG extends RbacOrgInfo> List<ORG> loadUserOrgList(Serializable userPrincipal, boolean assembleTree, String... rootIdList) {
            return Collections.emptyList();
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
            return new LinkedHashSet<>(userPermissions);
        }

        @Override
        public Collection<String> loadRolePermissionList(Serializable tenantId, List<String> roleCodeList) {
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
        private final Map<String, Object> transientExInfo = new LinkedHashMap<>();

        TestRbacUser(String id, String loginName, String tenantId, String type, List<? extends Serializable> roleList, Integer confidentialDataAccessLevel) {
            this.id = id;
            this.loginName = loginName;
            this.tenantId = tenantId;
            this.type = type;
            this.roleList = new ArrayList<>(roleList);
            this.confidentialDataAccessLevel = confidentialDataAccessLevel;
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
        private final Integer confidentialDataAccessLevel;
        private final Map<String, Object> transientExInfo = new LinkedHashMap<>();

        TestRbacRole(String id, String code, String tenantId, List<String> permissionList, List<String> exclusiveRoleList, Integer confidentialDataAccessLevel) {
            this.id = id;
            this.code = code;
            this.tenantId = tenantId;
            this.permissionList = permissionList == null ? Collections.emptyList() : new ArrayList<>(permissionList);
            this.exclusiveRoleList = exclusiveRoleList == null ? Collections.emptyList() : new ArrayList<>(exclusiveRoleList);
            this.confidentialDataAccessLevel = confidentialDataAccessLevel;
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
        public Integer getConfidentialDataAccessLevel() {
            return confidentialDataAccessLevel;
        }

        @Override
        public Map<String, Object> getTransientExInfo() {
            return transientExInfo;
        }
    }
}
