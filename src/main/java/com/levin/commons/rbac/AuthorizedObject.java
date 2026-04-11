package com.levin.commons.rbac;


import java.util.Collection;
import java.util.List;

/**
 * 授权对象
 *
 * @author llw
 */
@FunctionalInterface
public interface AuthorizedObject {

    /**
     * 授权标识
     * 逗号隔开
     * eg.
     * userManager:user:add
     *
     * @return
     */
    Collection<String> getRequireAuthorizations();

}
