package com.levin.commons.rbac;


import io.swagger.v3.oas.annotations.media.Schema;

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
    @Schema(title = "访问当前对象需要的授权清单")
    Collection<String> getRequireAuthorizations();

}
