package com.levin.commons.rbac;


/**
 * 树形资源
 *
 * @param <PARENT>
 * @param <CHILD>
 */
public interface TreeRes<PARENT, CHILD>
        extends Res, TreeObject<PARENT, CHILD> {
}
