package com.levin.commons.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathPatternUtilsTest {

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
}
