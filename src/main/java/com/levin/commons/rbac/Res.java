package com.levin.commons.rbac;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.service.domain.Identifiable;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;


/**
 * 资源描述
 * <p>
 * 对应注解类 ResAuthorize
 */
public interface Res extends Identifiable {

    /**
     * 归属域
     * <p>
     * 通常是子系统，子模块等
     *
     * @return
     */
    @Schema(title = "资源域",description = "资源所属域，通常为子系统，子模块等")
    <T extends Serializable> T getDomain();

    /**
     * 资源类型
     *
     * @return
     */
    @Schema(title = "资源类型")
    <T extends Serializable> T getType();

    /**
     * 获取资源图标
     *
     * @return
     */
    @Schema(title = "资源图标")
    default String getIcon() {
        return null;
    }

    /**
     * 没权限时是否展示
     *
     * @return
     */
    @Schema(title = "是否总是显示", description = "当没有权限的时候，是否显示，true为显示，false不显示")
    default boolean isAlwaysShow() {
        return false;
    }

    /**
     * 获取操作列表
     *
     * @return
     */
    @Schema(title = "资源操作", description = "资源的操作项，比如新建，添加，修改，删除")
    default <O extends ResConditionAction> Collection<O> getActionList() {
        return Collections.emptyList();
    }

}
