package com.levin.commons.utils;

import org.junit.jupiter.api.Test;
import org.springframework.http.server.PathContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PathPatternUtilsTest {

    @Test
    void parsedPathsPreserveStringMatchingSemanticsAcrossExpressions() {
        for (String path : new String[]{"/", "/A", "/A/", "/A/B/", "/中文/节点/", "/A%20B/"}) {
            PathContainer parsed = PathContainer.parsePath(path);
            for (String expression : new String[]{"/", "/*", "/*/", "/**", "/A/**", "/A/B/"}) {
                assertEquals(PathPatternUtils.matchPath(expression, path),
                        PathPatternUtils.matchParsedPath(expression, parsed), expression + " -> " + path);
            }
        }
        assertFalse(PathPatternUtils.matchParsedPath("/**", null));
        assertFalse(PathPatternUtils.matchParsedPath("", PathContainer.parsePath("/A")));
    }

    @Test
    void shouldMatchNameWithSpringPathPatternWildcards() {
        assertTrue(PathPatternUtils.matchName("Pend*", "PendingReview"));
        assertTrue(PathPatternUtils.matchName("PendingRevie?", "PendingReview"));
        assertFalse(PathPatternUtils.matchName("PendingReview?", "PendingReview"));
    }

    @Test
    void shouldMatchPathWithOptionalTrailingSlash() {
        assertTrue(PathPatternUtils.matchPathWithOptionalTrailingSlash("/*/", "/A"));
        assertTrue(PathPatternUtils.matchPathWithOptionalTrailingSlash("/*", "/A/"));
        assertFalse(PathPatternUtils.matchPathWithOptionalTrailingSlash("/*/", "/A/B"));
    }

    @Test
    void shouldKeepRawPathPatternTrailingSlashSemanticsSeparateFromOrgScopeSemantics() {
        assertFalse(PathPatternUtils.matchPath("/*/", "/"));
        assertFalse(PathPatternUtils.matchPath("/*/", "/A"));
        assertTrue(PathPatternUtils.matchPath("/*/", "/A/"));
        assertFalse(PathPatternUtils.matchPath("/*/", "/A/B/"));
    }
}
