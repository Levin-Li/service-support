package com.levin.commons.dao.domain;

import com.levin.commons.service.domain.Identifiable;
import io.swagger.v3.oas.annotations.Operation;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 可自审对象
 *
 * @author llw
 */
public interface SelfAuditableObject {

    @Operation(summary = "自审", description = "通过则放回true, 具体错误信息通过errorInfoConsumers接收")
    default boolean selfAudit(Consumer<String>... errorInfoConsumers) {

        final Function<String, Boolean> auditErrorFun = (error) -> {
            if (errorInfoConsumers != null) {
                Stream.of(errorInfoConsumers).filter(Objects::nonNull).forEachOrdered(ec -> ec.accept(error));
            }
            return false;
        };

        if (this instanceof Identifiable) {
            // id 不能为空, 不能为空字符串
            Serializable objectTempAuditId = ((Identifiable) this).getId();
            if (objectTempAuditId == null
                    || (objectTempAuditId instanceof CharSequence && ((CharSequence) objectTempAuditId).toString().trim().isEmpty())) {
                return auditErrorFun.apply("object id is required");
            }
        }

        if (this instanceof EnableObject && !((EnableObject) this).isEnable()) {
            // 不能是未启用的
            return auditErrorFun.apply("object is not enable");
        }

        if (this instanceof LogicDeletableObject && ((LogicDeletableObject) this).isDeleted()) {
            // 不能是逻辑删除的
            return auditErrorFun.apply("object logic deleted");
        }

        if (this instanceof ExpiredObject
                && ((ExpiredObject) this).getExpiredTime() != null
                && ((ExpiredObject) this).getExpiredTime().before(new Date())) {
            return auditErrorFun.apply("object expired");
        }

        return true;
    }
}
