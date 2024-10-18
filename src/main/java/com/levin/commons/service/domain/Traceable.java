package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.media.Schema;


/**
 * 可追踪的
 *
 * @author llw
 */
public interface Traceable {
    @Schema(title = "追踪标识",description = "用于追踪信息")
    default String getTraceId() {
        return null;
    }
}
