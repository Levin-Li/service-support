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

    @Schema(title = "访问当前对象需要的授权清单", description = "要求授权清单要全部匹配")
    Collection<String> getRequireAuthorizations();

}
