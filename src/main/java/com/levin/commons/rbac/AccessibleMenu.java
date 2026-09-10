package com.levin.commons.rbac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** 用户菜单的独立树快照，不修改扫描缓存或原始菜单。 */
final class AccessibleMenu extends SimpleMenu {
    @com.fasterxml.jackson.annotation.JsonIgnore
    private MenuItem parent;
    private final List<MenuItem> children = new ArrayList<>();

    @Override
    @SuppressWarnings("unchecked")
    public <P extends MenuItem> P getParent() {
        return (P) parent;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C extends MenuItem> Collection<C> getChildren() {
        return (Collection<C>) (Collection<?>) children;
    }

    void addChild(AccessibleMenu child) {
        child.parent = this;
        children.add(child);
    }
}
