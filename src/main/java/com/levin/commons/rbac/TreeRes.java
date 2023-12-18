package com.levin.commons.rbac;


import com.levin.commons.dao.domain.BaseTreeObject;
import com.levin.commons.plugin.Res;

/**
 * 树形资源
 *
 * @param <PARENT>
 * @param <CHILD>
 */
public interface TreeRes<PARENT, CHILD>
        extends Res, BaseTreeObject<PARENT, CHILD> {
}
