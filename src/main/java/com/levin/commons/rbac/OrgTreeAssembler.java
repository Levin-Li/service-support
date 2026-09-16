package com.levin.commons.rbac;

import cn.hutool.core.util.StrUtil;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/** 组织树算法；节点复制和写入由调用方提供的适配器完成。 */
final class OrgTreeAssembler {

    private OrgTreeAssembler() {
    }

    static <ORG extends RbacOrgInfo> Collection<ORG> assemble(Collection<ORG> orgList,
                                                                boolean buildNodePath,
                                                                String[] rootIdList,
                                                                BiFunction<ORG, String, ORG> copier) {
        if (orgList == null || orgList.isEmpty()) return Collections.emptyList();

        List<ORG> sources = orgList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (sources.isEmpty()) return Collections.emptyList();

        Map<String, List<ORG>> byTenant = new LinkedHashMap<>();
        for (ORG source : sources) {
            byTenant.computeIfAbsent(tenantKey(source), key -> new ArrayList<>()).add(source);
        }

        List<ORG> roots = new ArrayList<>();
        for (List<ORG> tenantOrgs : byTenant.values()) {
            roots.addAll(assembleSingleTenant(tenantOrgs, buildNodePath, rootIdList, copier));
        }
        return roots;
    }

    private static <ORG extends RbacOrgInfo> Collection<ORG> assembleSingleTenant(List<ORG> sources,
                                                                                     boolean buildNodePath,
                                                                                     String[] rootIdList,
                                                                                     BiFunction<ORG, String, ORG> copier) {
        Map<String, ORG> sourceById = sources.stream()
                .filter(org -> !RbacMiscUtils.isBlank(org.getId()))
                .collect(Collectors.toMap(org -> Objects.toString(org.getId(), ""), Function.identity(),
                        (left, right) -> left, LinkedHashMap::new));
        if (sourceById.isEmpty()) return Collections.emptyList();

        Map<String, List<String>> childrenByParent = new LinkedHashMap<>();
        sourceById.values().forEach(org -> childrenByParent
                .computeIfAbsent(Objects.toString(org.getParentId(), ""), key -> new ArrayList<>())
                .add(Objects.toString(org.getId(), "")));
        Set<String> roots = Arrays.stream(rootIdList == null ? new String[0] : rootIdList)
                .filter(StrUtil::isNotBlank).collect(Collectors.toCollection(LinkedHashSet::new));
        Set<String> selected = roots.isEmpty() ? new LinkedHashSet<>(sourceById.keySet())
                : collectDescendants(roots, sourceById, childrenByParent);
        if (selected.isEmpty()) return Collections.emptyList();
        validateAcyclic(selected, sourceById);

        Map<String, String> pathCache = buildNodePath ? new HashMap<>() : Collections.emptyMap();
        Map<String, Node<ORG>> copiedById = new LinkedHashMap<>();
        for (ORG source : sourceById.values()) {
            String id = Objects.toString(source.getId(), "");
            if (!selected.contains(id)) continue;
            String nodePath = buildNodePath ? resolvePath(source, sourceById, pathCache, new LinkedHashSet<>()) : source.getNodePath();
            ORG copy = Objects.requireNonNull(copier.apply(source, nodePath), "组织树节点副本不能为空");
            Collection<ORG> children = copy.getChildren();
            if (children == null) throw new IllegalArgumentException("组织树节点副本 children 不能为空");
            children.clear();
            copiedById.put(id, new Node<>(copy));
        }

        List<ORG> result = new ArrayList<>();
        for (ORG source : sourceById.values()) {
            String id = Objects.toString(source.getId(), "");
            if (!selected.contains(id)) continue;
            Node<ORG> node = copiedById.get(id);
            String parentId = Objects.toString(source.getParentId(), "");
            Node<ORG> parent = copiedById.get(parentId);
            if (StrUtil.isBlank(parentId) || !selected.contains(parentId) || parent == null) result.add(node.value);
            else parent.value.getChildren().add(node.value);
        }
        return result;
    }

    private static Set<String> collectDescendants(Set<String> roots, Map<String, ? extends RbacOrgInfo> orgs,
                                                   Map<String, List<String>> childrenByParent) {
        Set<String> selected = new LinkedHashSet<>();
        Deque<String> pending = new ArrayDeque<>(roots);
        while (!pending.isEmpty()) {
            String id = pending.pop();
            if (orgs.containsKey(id) && selected.add(id)) pending.addAll(childrenByParent.getOrDefault(id, Collections.emptyList()));
        }
        return selected;
    }

    private static void validateAcyclic(Set<String> selected, Map<String, ? extends RbacOrgInfo> orgs) {
        Set<String> checked = new HashSet<>();
        for (String id : selected) {
            Set<String> visiting = new LinkedHashSet<>();
            String current = id;
            while (StrUtil.isNotBlank(current) && selected.contains(current) && !checked.contains(current)) {
                if (!visiting.add(current)) throw new IllegalStateException("组织节点出现循环引用: " + String.join(" -> ", visiting) + " -> " + current);
                RbacOrgInfo org = orgs.get(current);
                current = org == null ? null : Objects.toString(org.getParentId(), "");
            }
            checked.addAll(visiting);
        }
    }

    private static String resolvePath(RbacOrgInfo org, Map<String, ? extends RbacOrgInfo> orgs,
                                      Map<String, String> cache, Set<String> visiting) {
        String id = Objects.toString(org.getId(), "");
        if (cache.containsKey(id)) return cache.get(id);
        if (!visiting.add(id)) {
            throw new IllegalStateException("组织节点出现循环引用: " + String.join(" -> ", visiting) + " -> " + id);
        }
        String existing;
        try {
            existing = org.getNodePath();
        } catch (UnsupportedOperationException ignored) {
            existing = null;
        }
        if (StrUtil.isNotBlank(existing)) {
            cache.put(id, existing);
            visiting.remove(id);
            return existing;
        }
        String parentId = Objects.toString(org.getParentId(), "");
        String path = StrUtil.isBlank(parentId) || !orgs.containsKey(parentId) ? "/" + id + "/"
                : resolvePath(orgs.get(parentId), orgs, cache, visiting) + id + "/";
        cache.put(id, path);
        visiting.remove(id);
        return path;
    }

    private static String tenantKey(RbacOrgInfo org) {
        try {
            return Objects.toString(org.getTenantId(), "");
        } catch (UnsupportedOperationException ignored) {
            return "";
        }
    }

    private record Node<ORG extends RbacOrgInfo>(ORG value) {
    }
}
