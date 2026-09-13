package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.Serializable;


/**
 * 资源许可
 *
 * @author lilw
 */

@Tag(name = "资源许可定义", description = "许可由领域、资源类型、资源和动作组成；字段以 : 分隔，| 表示逻辑或。许可定义本身不绕过领域、租户、机密级别或拒绝优先等授权门槛。")
public interface Permission {

    /**
     * 字段分隔符
     */
    String DELIMITER = ":";

    /**
     * 逻辑或分隔符
     */
    String OR_DELIMITER = "|";

    /**
     * 资源域
     *
     * @param <D>
     */
    @Operation(summary = "获取许可领域", description = "返回资源所属业务领域；领域访问门槛不满足时应优先拒绝该许可匹配。")
    <D extends Serializable> D getDomain();

    /**
     * 资源类型
     *
     * @param < T>
     */
    @Operation(summary = "获取许可资源类型", description = "返回资源类型，是组成完整许可表达式的一段。")
    <T extends Serializable> T getType();

    /**
     * 资源
     *
     * @param < T>
     */
    @Operation(summary = "获取许可资源", description = "返回资源标识，是组成完整许可表达式的一段。")
    <R extends Serializable> R getRes();

    /**
     * 允许的操作
     *
     * @param < T>
     */
    @Operation(summary = "获取许可动作", description = "返回受许可约束的动作；资源、领域或前置授权不满足时不能仅凭动作匹配通过。")
    <A extends Serializable> A getAction();

    /**
     * 备注
     *
     * @return
     */
    String getRemark();
}
