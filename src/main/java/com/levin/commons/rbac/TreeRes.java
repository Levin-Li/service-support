package com.levin.commons.rbac;


import com.levin.commons.dao.domain.BaseTreeObject;

/**
 * 树形资源
 *
 * @param <PARENT>
 * @param <CHILD>
 */
public interface TreeRes<PARENT, CHILD>
        extends Res, BaseTreeObject<PARENT, CHILD> {
}
