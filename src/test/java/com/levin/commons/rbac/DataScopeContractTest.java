package com.levin.commons.rbac;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DataScopeContractTest {

    @Test
    void preservesExactProtocolValues() {
        assertEquals("_ALL_", DataScope.TenantScope.All.getExpression());
        assertEquals("_DEFAULT_", DataScope.TenantScope.Default.getExpression());
        assertEquals("_NONE_", DataScope.TenantScope.None.getExpression());
        assertEquals("Groovy#", DataScope.TenantScope.Groovy.getExpression());
        assertTrue(DataScope.TenantScope.Groovy.isPrefix());
        assertFalse(DataScope.TenantScope.All.isPrefix());
        assertEquals("_ALL_ROOT_", DataScope.StartOrg.AllRoot.getExpression());
    }

    @Test
    void roundTripsEveryOrganizationModeWithoutAddingSeparators() {
        for (DataScope.OrgMatchingMode mode : DataScope.OrgMatchingMode.values()) {
            String expression = mode.getExpression() + (mode.isPrefix() ? "/**" : "");
            String encoded = "dept|" + expression;
            DataScope.OrgScope scope = DataScope.OrgScope.parse(encoded);
            assertEquals("dept", scope.startOrg());
            assertEquals(expression, scope.orgMatchingMode());
            assertEquals(encoded, DataScope.OrgScope.format(scope));
            assertEquals(encoded, DataScope.OrgScope.format(DataScope.OrgScope.parse(
                    DataScope.OrgScope.format(scope))));
        }
    }

    @Test
    void preservesScriptOrOperatorsAndTrimsOnlyOuterWhitespace() {
        String script = "Groovy#_org.id == 'a' || _org.id == 'b'";
        DataScope.OrgScope scope = DataScope.OrgScope.parse(" dept | " + script + " ");
        assertEquals("dept", scope.startOrg());
        assertEquals(script, scope.orgMatchingMode());
        assertEquals("dept|" + script, DataScope.OrgScope.format(scope));
    }

    @Test
    void absentRulesDoNotCreateAnImplicitOrganizationGrant() {
        assertNull(DataScope.OrgScope.parse(null));
        assertNull(DataScope.OrgScope.parse(""));
        assertNull(DataScope.OrgScope.parse(" \t "));
        assertNull(DataScope.OrgScope.format(null));
    }

    @Test
    void rejectsMalformedRulesAndEmptyCustomExpressions() {
        for (String malformed : new String[]{"dept", "|Self", " |Self", "dept|",
                "dept|Unknown", "dept|Self|Self", "dept|IdPath#", "dept|NamePath#  ",
                "dept|Groovy#", "dept|SpringEL#true", "dept|self"}) {
            assertThrows(IllegalArgumentException.class, () -> DataScope.OrgScope.parse(malformed), malformed);
        }
    }

    @Test
    void noOrganizationDoesNotRequireOrValidateMatchingMode() {
        for (String mode : new String[]{"", "Self", "unknown", "Groovy#"}) {
            DataScope.OrgScope scope = DataScope.OrgScope.parse("_NONE_|" + mode);
            assertEquals("_NONE_", scope.startOrg());
            assertEquals(mode, scope.orgMatchingMode());
        }
        assertThrows(IllegalArgumentException.class, () -> DataScope.OrgScope.parse("_NONE_"));
    }

    @Test
    void interfaceDefaultsAreExplicitDefinitionsRatherThanInheritance() {
        DataScope defaults = new DataScope() {};
        assertEquals(Set.of("_DEFAULT_"), defaults.getTenantScopeList());
        assertEquals(Set.of(), defaults.getDeniedTenantScopeList());
        assertEquals(Set.of(), defaults.getDomainScopeList());
        assertEquals(Set.of(), defaults.getDeniedDomainScopeList());
        assertEquals(Set.of(), defaults.getOrgScopeList());
        assertEquals(Set.of(), defaults.getDeniedOrgScopeList());
        assertNull(defaults.getConfidentialDataAccessLevel());
        assertThrows(UnsupportedOperationException.class, () -> defaults.getTenantScopeList().add("other"));
    }

    @Test
    void effectiveScopeKeepsThePublicJsonFieldNames() {
        DataScope scope = new EffectiveDataScope(Set.of("T1"), Set.of(), Set.of("sales"),
                Set.of(), Set.of("dept|Self"), Set.of(), 1000);
        JsonNode json = new ObjectMapper().valueToTree(scope);
        assertEquals(7, json.size());
        assertEquals("T1", json.get("tenantScopeList").get(0).asText());
        assertEquals("sales", json.get("domainScopeList").get(0).asText());
        assertEquals("dept|Self", json.get("orgScopeList").get(0).asText());
        assertTrue(json.get("deniedTenantScopeList").isEmpty());
        assertTrue(json.get("deniedDomainScopeList").isEmpty());
        assertTrue(json.get("deniedOrgScopeList").isEmpty());
        assertEquals(1000, json.get("confidentialDataAccessLevel").asInt());
        assertFalse(json.has("getTenantScopeList"));
    }
}
