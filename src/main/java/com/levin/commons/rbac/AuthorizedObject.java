package com.levin.commons.rbac;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;

/**
 * 授权对象
 *
 * @author llw
 */
@FunctionalInterface
@Tag(name = "授权对象", description = "对象声明的授权清单采用全部匹配语义；缺少任一要求授权即拒绝访问，空清单表示不额外要求动作授权。")
public interface AuthorizedObject {

    @Schema(title = "访问当前对象需要的授权清单", description = "要求授权清单要全部匹配")
    @Operation(summary = "获取对象所需授权", description = "返回访问当前对象必须全部满足的授权表达式；任一表达式不满足即拒绝，空集合不额外限制访问。")
    Collection<String> getRequireAuthorizations();

}
