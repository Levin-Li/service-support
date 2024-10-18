package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 服务请求
 * 请求格式不做具体的限制
 *
 * @author lilw
 */

@Schema(title = "服务请求类")
public interface ServiceReq
        extends Serializable {

    @Schema(title = "请求ID", description = "请求ID，用于跟踪请求，便于调试")
    default String getRequestId() {
        return null;
    }

    @Schema(title = "是否超级管理员", hidden = true)
    default boolean isSuperAdmin() {
        return false;
    }

    @Schema(title = "是否租户管理员", hidden = true)
    default boolean isTenantAdmin() {
        return false;
    }

    @Schema(title = "是否需要签名验证")
    default boolean requireSignVerification() {
        return false;
    }

    @Operation(summary = "类型强转")
    default <T extends ServiceReq> T cast() {
        return (T) this;
    }

}
