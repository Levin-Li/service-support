package com.levin.commons.utils;

import org.springframework.http.server.PathContainer;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Map;

/**
 * Spring PathPattern 工具。
 */
public abstract class PathPatternUtils {

    private static final Map<String, PathPattern> PATH_PATTERN_CACHE = new ConcurrentReferenceHashMap<>();

    private static final PathPatternParser PATH_PATTERN_PARSER = new PathPatternParser();

    private PathPatternUtils() {
    }

    public static boolean matchPath(String expression, String path) {
        if (!StringUtils.hasText(expression) || !StringUtils.hasText(path)) {
            return false;
        }

        return matchParsedPath(expression, PathContainer.parsePath(path));
    }

    /** 同一路径匹配多条规则时，调用方可以在一次计算内复用已解析的路径。 */
    public static boolean matchParsedPath(String expression, PathContainer path) {
        if (!StringUtils.hasText(expression) || path == null || !StringUtils.hasText(path.value())) {
            return false;
        }
        return PATH_PATTERN_CACHE.computeIfAbsent(expression, PATH_PATTERN_PARSER::parse).matches(path);
    }

    public static boolean matchPathWithOptionalTrailingSlash(String expression, String path) {
        if (!StringUtils.hasText(expression) || !StringUtils.hasText(path)) {
            return false;
        }

        if (matchPath(expression, path)) {
            return true;
        }

        if ("/".equals(path)) {
            return false;
        }

        return matchPath(expression, path.endsWith("/")
                ? path.substring(0, path.length() - 1)
                : path + "/");
    }

    public static boolean matchName(String expression, String name) {
        if (!StringUtils.hasText(expression) || !StringUtils.hasText(name)) {
            return false;
        }

        return matchPath(normalizeNameAsPath(expression), normalizeNameAsPath(name));
    }

    public static String normalizeNameAsPath(String name) {
        if (!StringUtils.hasText(name)) {
            return "/";
        }

        final String path = name.trim().replaceAll("/{2,}", "/");
        return path.startsWith("/") ? path : "/" + path;
    }

}
