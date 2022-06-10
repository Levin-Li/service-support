package com.levin.commons.rbac;


/**
 * 授权对象
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
    default String getRequireAuthorizations() {
        return null;
    }

}
