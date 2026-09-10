package com.levin.commons.rbac;

import com.levin.commons.utils.PathPatternUtils;
import cn.hutool.core.util.StrUtil;
import org.springframework.http.server.PathContainer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** 单次匹配中当前起点的路径缓存；调用方已验证子树无环，切换起点即丢弃。 */
final class OrgScopePaths {
    private final Map<String, ? extends RbacOrgInfo> organizations;
    private final Map<String, String> idPaths = new HashMap<>();
    private final Map<String, String> namePaths = new HashMap<>();
    private final Map<PathKey, PathContainer> parsedPaths = new HashMap<>();

    OrgScopePaths(String rootId, Map<String, ? extends RbacOrgInfo> organizations) {
        this.organizations = organizations;
        idPaths.put(rootId, "/");
        namePaths.put(rootId, "/");
    }

    String path(String orgId, boolean names) {
        Map<String, String> paths = names ? namePaths : idPaths;
        if (paths.containsKey(orgId)) return paths.get(orgId);
        Deque<RbacOrgInfo> pending = new ArrayDeque<>();
        String currentId = orgId;
        while (!paths.containsKey(currentId)) {
            RbacOrgInfo org = organizations.get(currentId);
            if (org == null) return null;
            pending.push(org);
            currentId = Objects.toString(org.getParentId(), null);
        }
        String prefix = paths.get(currentId);
        while (!pending.isEmpty()) {
            RbacOrgInfo org = pending.pop();
            String id = Objects.toString(org.getId(), "");
            String segment = names ? Objects.toString(org.getName(), "") : id;
            segment = StrUtil.blankToDefault(segment, "");
            prefix = prefix + segment + "/";
            paths.put(id, prefix);
        }
        return paths.get(orgId);
    }

    boolean matches(String expression, String orgId, boolean names) {
        boolean trailingSlash = expression.endsWith("/") && !"/".equals(expression);
        PathContainer parsed = parsedPaths.computeIfAbsent(new PathKey(orgId, names, trailingSlash), key -> {
            String path = path(orgId, names);
            if (path == null) return null;
            if (!trailingSlash) {
                int end = path.length();
                while (end > 1 && path.charAt(end - 1) == '/') end--;
                path = path.substring(0, end);
            }
            return PathContainer.parsePath(path);
        });
        return PathPatternUtils.matchParsedPath(expression, parsed);
    }

    private record PathKey(String orgId, boolean names, boolean trailingSlash) {}
}
