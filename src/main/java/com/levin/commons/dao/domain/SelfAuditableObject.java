package com.levin.commons.dao.domain;

import com.levin.commons.service.domain.Identifiable;
import io.swagger.v3.oas.annotations.Operation;

import java.io.Serializable;
import java.util.Date;

/**
 * 可自审对象
 *
 * @author llw
 */
public interface SelfAuditableObject {

    @Operation(summary = "自审并返回错误信息", description = "没有错误,则放回null")
    default String selfAudit() {

        if (this instanceof Identifiable) {
            // id 不能为空, 不能为空字符串
            Serializable objectTempAuditId = ((Identifiable) this).getId();

            if (objectTempAuditId == null
                    || (objectTempAuditId instanceof CharSequence && ((CharSequence) objectTempAuditId).toString().trim().isEmpty())) {
                return "object id is required";
            }
        }

        if (this instanceof EnableObject && !((EnableObject) this).isEnable()) {
            // 不能是未启用的
            return "object is not enable";
        }

        if (this instanceof LogicDeletableObject && ((LogicDeletableObject) this).isDeleted()) {
            // 不能是逻辑删除的
            return ("object logic deleted");
        }

        if (this instanceof ExpiredObject
                && ((ExpiredObject) this).getExpiredTime() != null
                && ((ExpiredObject) this).getExpiredTime().before(new Date())) {

            return "object expired";
        }

        return null;
    }

}
