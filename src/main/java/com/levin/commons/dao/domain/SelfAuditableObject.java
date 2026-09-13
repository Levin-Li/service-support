package com.levin.commons.dao.domain;

import com.levin.commons.service.domain.Identifiable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.Serializable;
import java.time.LocalDateTime;
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

@Tag(name = "对象自审", description = "自审按对象标识、启用状态、逻辑删除状态和有效期依次校验；任一条件不满足即拒绝并返回 false，错误通过回调按检查顺序报告。")
public interface SelfAuditableObject {

    @Operation(summary = "自审", description = "依次检查对象 ID、启用状态、逻辑删除状态和有效期；任一项失败立即返回 false，后续规则不再执行。每个失败原因通过 errorInfoConsumers 回调报告；全部通过时返回 true。")
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
            return auditErrorFun.apply("object already logic deleted");
        }

        if (this instanceof ExpiredObject
                && ((ExpiredObject) this).getExpiredTime() != null
                && ((ExpiredObject) this).getExpiredTime().isBefore(LocalDateTime.now())) {
            return auditErrorFun.apply("object expired");
        }

        return true;
    }
}
