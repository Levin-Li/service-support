package com.levin.commons.ui.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.ui.annotation.FormItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link FormItem} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class FormItemModel implements FormItem {

    @Schema(title = "字段名", description = "注解所在字段名，用于全局定位")
    String fieldName = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    @Schema(title = "表单项名称", description = "对应查询对象字段名称，或是API接口的URL参数名称")
    String name = "";

    @Schema(title = "表单项在布局中的占位顺序", description = "默认为-1，表示默认")
    int order = -1;

    @Schema(title = "表单项栅格占位", description = "默认为-1，表示使用布局默认值")
    int span = -1;

    @Schema(title = "占位符", description = "同时也是Label")
    String placeholder = "";

    @Schema(title = "表单项尾部提示", description = "格式：颜色:文字, 比如单位：元，秒等")
    String inputPrompt = "";

    String desc = "";

    @Schema(title = "适用场景", description = "适用的业务场景, 场景名称通常是api接口的名称 , 默认无限制, 如query/create/update/view/detail/list/")
    String[] scenes = {};

    @Schema(title = "适用展示分类", description = "关联@FormLayout, 通常用于表单项过多时, 展示基本部分还是全部, 比如高级搜索,简单搜索, 基本信息, 更多信息等场景, 默认不限制,可在所有分类中展示")
    String[] showCategories = {};

    @Schema(title = "适用展示分组", description = "关联@FormLayout, 组名称相同的表单项展示时会放在一起, 一个表单项可以属于多个分组, 分组关联表单布局")
    String[] groups = {};

    @Schema(title = "值选项", description = "正常只支持只取第一个Options对象, 为了注解使用方便, 用数组表示")
    OptionsModel[] options = {};

    @Schema(title = "JsonSchema编辑器", description = "复杂字段关联的JsonSchema编辑器")
    JsonSchemaEditorModel jsonSchemaEditor;

    String defaultValue = "";

    @Schema(title = "数据校验表达式", description = "Js表达式或是固定规则名称")
    String[] verifyRules = {};

    @Schema(title = "禁用条件", description = "前端Js表达式，返回true时字段显示但不可编辑")
    String disabledOn = "";

    @Schema(title = "必填条件", description = "前端Js表达式，返回true时字段为必填，优先级高于静态required推导")
    String requiredOn = "";

    @Schema(title = "只读条件", description = "前端Js表达式，返回true时字段只读，可见、可提交、不可修改")
    String readOnlyOn = "";

    @Schema(title = "隐藏时是否清空值", description = "当字段被隐藏时，是否自动清空字段值，避免联动残值污染提交数据")
    boolean clearOnHidden = false;

    @Schema(title = "求值转换器", description = "Js表达式或是固定转换器名称")
    String[] dataToUiConvertors = {};

    @Schema(title = "求值转换器", description = "Js表达式或是固定转换器名称")
    String[] uiToDataConvertors = {};

    String style = "";

    @Override
    public Class<? extends Annotation> annotationType() {
        return FormItem.class;
    }
}
