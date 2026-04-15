package com.levin.commons.ui.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.ui.annotation.Form;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link Form.Action} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class FormActionModel implements Form.Action {

    @Schema(title = "展示名称", description = "按钮展示名称")
    String label = "";

    @Schema(title = "表单验证规则", description = "可覆盖表单的验证规则,如先暂存数据,不提交")
    String[] verifyRules = {};

    String beforeConfirmInfo = "";

    @Schema(title = "提交后执行的动作", description = "参数变量名:actionResult")
    String afterExpr = "";

    @Schema(title = "动作参数", description = "Json格式, 比暂存和提交 调用同一个API接口,但单参数可能不同")
    String params = "";

    @Schema(title = "动作表达式", description = "默认动作表达式为空，也就是表单提交到API")
    String expr = "";

    String desc = "";

    @Override
    public Class<? extends Annotation> annotationType() {
        return Form.Action.class;
    }
}
