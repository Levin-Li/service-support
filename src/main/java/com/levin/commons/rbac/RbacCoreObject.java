package com.levin.commons.rbac;


import com.levin.commons.dao.domain.*;
import com.levin.commons.service.domain.Identifiable;
import io.swagger.v3.oas.annotations.media.Schema;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 角色对象
 */
public interface RbacCoreObject extends Serializable, Identifiable, NamedObject, EnableObject, StatefulObject, TypeableObject, ExpiredObject, EditableObject, ConfidentialObject, SelfAuditableObject {

    /**
     * 获取对象标识
     *
     * @return id
     */
    @Override
    default <ID extends Serializable> ID getId() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取类型
     *
     * @return
     */
    @Override
    default <T extends Serializable> T getType() {
        return null;
    }

    /**
     * 获取保密级别
     * 数值越大，级别越高
     *
     * @return
     */
    @Override
    default Integer getConfidentialLevel() {
        return null;
    }

    /**
     * 是否禁用
     *
     * @return isDisable
     */
    @Override
    default boolean isEnable() {
        return true;
    }

    /**
     * 过期时间
     *
     * @return date
     */
    @Override
    default Date getExpiredTime() {
        return null;
    }

    /**
     * 获取对象名称
     *
     * @return name
     */
    @Override
    default String getName() {
        return getId();
    }

    /**
     * 获取对象状态
     *
     * @return
     */
    @Override
    default <STATE extends Serializable> STATE getState() {
        return null;
    }

    /**
     * 是否可编辑
     *
     * @return
     */
    @Override
    default boolean isEditable() {
        return true;
    }

    /**
     * 扩展信息
     *
     * @return
     */
    @Schema(title = "临时扩展信息")
    @Transient
    default Map<String, Object> getTransientExInfo() {
        return null;
    }

}
