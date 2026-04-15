package com.levin.commons.ui.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.ui.annotation.Form;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Form} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(title = "表单", description = "通常注解在控制器方法的参数上")
public class FormModel implements Form {

    @Schema(title = "表单名称", description = "关联属性名称")
    String name = "";

    @Schema(title = "标题", description = "表单标题")
    String title = "";

    @Schema(title = "数据初始化API", description = "数据初始化API")
    String loadApi = "";

    @Schema(title = "表单验证规则", description = "多个表单验证规则")
    String[] verifyRules = {};

    @Schema(title = "表单布局", description = "多个表单布局, 多个布局之间采用vbox布局, 就是按一列纵向布局")
    FormLayoutModel[] layouts = {new FormLayoutModel()};

    @Schema(title = "表单操作", description = "默认未配置, 提交和取消 2个操作按钮")
    FormActionModel[] actions = {};

    @Schema(title = "描述", description = "表单描述")
    String desc = "";

    @Schema(title = "表单样式", description = "表单样式")
    String style = "";

    @Schema(title = "表单项列表", description = "当前表单包含的多个表单项")
    List<FormItemModel> formItemList = new ArrayList<>();

    @Override
    public Class<? extends Annotation> annotationType() {
        return Form.class;
    }
}
