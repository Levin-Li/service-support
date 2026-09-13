package com.levin.commons.rbac;

import com.levin.commons.utils.PathPatternUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrgScopePathsTest {
    private record Org(String id, String parent, String name) implements RbacOrgInfo {
        @Override public String getId() { return id; }
        @Override public String getParentId() { return parent; }
        @Override public String getName() { return name; }
        @Override public String getTenantId() { return "T1"; }
        @Override public String getDomainId() { return null; }
    }

    @Test
    void interleavedCacheVariantsMatchSpringForEveryPath() {
        Map<String, Org> orgs = Map.of(
                "root", new Org("root", null, "Root"),
                "child", new Org("child", "root", "中文%20名称;p=v"),
                "leaf", new Org("leaf", "child", ""));
        OrgScopePaths paths = new OrgScopePaths("root", orgs);
        String[] expressions = {"/", "/*", "/*/", "/**", "/child", "/child/",
                "/child/*", "/child/**", "/中文 名称/**", "/{first}/{second}", "/missing/**"};
        for (int repeat = 0; repeat < 3; repeat++) {
            for (String id : orgs.keySet()) {
                for (String expression : expressions) {
                    for (boolean names : new boolean[]{false, true}) {
                        String path = paths.path(id, names);
                        if (!expression.endsWith("/") || expression.equals("/")) {
                            int end = path.length();
                            while (end > 1 && path.charAt(end - 1) == '/') end--;
                            path = path.substring(0, end);
                        }
                        assertEquals(PathPatternUtils.matchPath(expression, path),
                                paths.matches(expression, id, names),
                                expression + " -> " + path + " names=" + names);
                    }
                }
            }
        }
    }

    @Test
    void missingPathsStillSkipInvalidExpressionParsing() {
        OrgScopePaths paths = new OrgScopePaths("root", Map.of("root", new Org("root", null, "Root")));
        for (boolean names : new boolean[]{false, true}) {
            assertFalse(paths.matches("/{broken", "missing", names));
            assertFalse(paths.matches("/{broken/", "missing", names));
            assertThrows(IllegalArgumentException.class, () -> paths.matches("/{broken", "root", names));
        }
    }
}
