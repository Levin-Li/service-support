package com.levin.commons.dao.domain;


import io.swagger.v3.oas.annotations.Operation;

/**
 * 可转换对象
 *
 * @author llw
 */
public interface CastableObject<T extends CastableObject<T>> {

    @Operation(summary = "类型强转")
    default <E extends CastableObject<T>> E cast() {
        return (E) this;
    }

}
