package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 可转换对象
 *
 * @author llw
 */
@Tag(name = "可转换对象", description = "提供当前对象的泛型视图转换；转换不复制对象、不校验目标运行时类型，也不改变对象状态。")
public interface Castable {

    @Operation(summary = "类型强转", description = "返回当前对象的泛型视图，不创建副本且不改变状态；目标类型不兼容时由调用方承担运行时类型转换风险。")
    default <E extends Castable> E cast() {
        return (E) this;
    }

}
