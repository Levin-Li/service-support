package com.levin.commons.rbac;


import java.util.List;

/**
 * 授权对象
 *
 * @author llw
 */
public interface AuthorizedObject {

    /**
     * 授权标识
     * 逗号隔开
     * eg.
     * userManager:user:add
     *
     * @return
     */
    default List<String> getRequireAuthorizations() {
        return null;
    }

}
