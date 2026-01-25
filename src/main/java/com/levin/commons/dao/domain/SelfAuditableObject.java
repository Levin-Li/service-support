package com.levin.commons.dao.domain;


import cn.hutool.core.lang.Assert;
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

    @Operation(summary = "自审", description = "")
    default <E extends SelfAuditableObject> E selfAudit() {

        if (this instanceof Identifiable) {

            // id 不能为空, 不能为空字符串
            Serializable objectTempAuditId = ((Identifiable) this).getId();

            Assert.isTrue(objectTempAuditId != null
                    && (!(objectTempAuditId instanceof CharSequence) || !((CharSequence) objectTempAuditId).toString().trim().isEmpty()), "object id is required");
        }

        if (this instanceof EnableObject) {
            // 不能是未启用的
            Assert.isTrue(((EnableObject) this).isEnable(), "object disable");
        }

        if (this instanceof LogicDeletableObject) {
            // 不能是逻辑删除的
            Assert.isTrue(!((LogicDeletableObject) this).isDeleted(), "object logic deleted");
        }

        if (this instanceof ExpiredObject) {
            // 不能是过期的
            Assert.isTrue(((ExpiredObject) this).getExpiredTime() == null
                    || ((ExpiredObject) this).getExpiredTime().after(new Date()), "object expired");
        }

        return (E) this;
    }

}
