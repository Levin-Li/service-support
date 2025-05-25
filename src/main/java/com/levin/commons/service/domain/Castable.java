package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.Operation;

/**
 * 可转换对象
 *
 * @author llw
 */
public interface Castable {

    @Operation(summary = "类型强转", description = "")
    default <E extends Castable> E cast() {
        return (E) this;
    }

}
