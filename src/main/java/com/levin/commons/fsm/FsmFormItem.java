package com.levin.commons.fsm;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.service.domain.Castable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * @author lilw
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(title = "状态机表单项")
public interface FsmFormItem extends Castable, Serializable {

    @Schema(title = "表单项名称", description = "也就是字段名,不允许为空")
    @NotNull
    String name();

    @Schema(title = "标签", description = "如果不填，就用字段默认的Label")
    default String label() {
        return null;
    }

    @Schema(title = "是否必填", description = "默认不设置也就是返回Null值, 由字段原有编辑表单的逻辑决定")
    default Boolean required() {
        return null;
    }

    static FsmFormItem of(String name) {
        return of(name, null, null);
    }

    static FsmFormItem of(String name, String label, Boolean required) {
        return FSMHelper.newFsmFormItem(name, label, required);
    }
}
