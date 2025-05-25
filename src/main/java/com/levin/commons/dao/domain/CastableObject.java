package com.levin.commons.dao.domain;


import io.swagger.v3.oas.annotations.Operation;

/**
 * 可转换对象
 *
 * @author llw
 */
public interface CastableObject {

    @Operation(summary = "类型强转", description = "")
    default <E extends CastableObject> E cast() {
        return (E) this;
    }

}
