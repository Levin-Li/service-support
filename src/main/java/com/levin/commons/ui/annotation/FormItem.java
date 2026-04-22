package com.levin.commons.ui.annotation;

import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 表单项
 * <p>
 * 参考 amis
 * https://aisuda.bce.baidu.com/amis/zh-CN/docs/concepts/datascope-and-datachain
 *
 * @author llw
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FormItem {

    @Schema(title = "表单项名称", description = "对应查询对象字段名称，或是API接口的URL参数名称")
    String name() default "";

    @Schema(title = "表单项在布局中的占位顺序", description = "默认为-1，表示默认")
    int order() default -1;

    @Schema(title = "表单项栅格占位", description = "默认为-1，表示使用布局默认值")
    int span() default -1;

    @Schema(title = "标题", description = "为空默认取字段的@Schema的title")
    String label() default "";

    @Schema(title = "占位符", description = "为空时,默认取label值")
    String placeholder() default "";

    @Schema(title = "表单项尾部提示", description = "格式：颜色:文字, 比如单位：元，秒等")
    String inputPrompt() default "";

    @Schema(title = "表单项描述", description = "表单项的描述信息, 通常用于帮助填写")
    String desc() default "";

    @Schema(title = "适用场景", description = "适用的业务场景, 场景名称通常是api接口的名称 , 默认无限制, 如query/create/update/view/detail/list/")
    String[] scenes() default {};

    @Schema(title = "适用展示分类", description = "关联@FormLayout, 通常用于表单项过多时, 展示基本部分还是全部, 比如高级搜索,简单搜索, 基本信息, 更多信息等场景, 默认不限制,可在所有分类中展示")
    String[] showCategories() default {};

    @Schema(title = "适用展示分组", description = "关联@FormLayout, 组名称相同的表单项展示时会放在一起, 一个表单项可以属于多个分组, 分组关联表单布局")
    String[] groups() default {};

    @Schema(title = "值选项", description = "正常只支持只取第一个Options对象, 为了注解使用方便, 用数组表示")
    Options[] options() default {};

    @Schema(title = "默认值", description = "表单项的默认值")
    String defaultValue() default "";

    @Schema(title = "数据校验规则", description = "Js表达式或是固定规则名称, 多个规则必须同时满足; 固定规则按[JSR 380]注解规则,以@开头,如@NotBlank")
    String[] verifyRules() default {};

    /// /////////////////////////

    @Schema(title = "显示条件", description = "前端Js表达式，返回true时布局显示")
    String visibleOn() default "";

    @Schema(title = "禁用条件", description = "前端Js表达式，返回true时字段显示但不可编辑")
    String disabledOn() default "";

    @Schema(title = "必填条件", description = "前端Js表达式，返回true时字段为必填，优先级高于静态required推导")
    String requiredOn() default "";

    @Schema(title = "只读条件", description = "前端Js表达式，返回true时字段只读，可见、可提交、不可修改")
    String readOnlyOn() default "";

    @Schema(title = "隐藏时是否提交", description = "当字段被隐藏时，是否提交")
    boolean submitOnHidden() default false;

    /// //////////////////////////////////

    @Schema(title = "求值转换器", description = "Js表达式或是固定转换器名称")
    String[] dataToUiConvertors() default {};

    @Schema(title = "求值转换器", description = "Js表达式或是固定转换器名称")
    String[] uiToDataConvertors() default {};

    @Schema(title = "表单项Ui类型", description = "表单项的UI类型,如text, Upload, Date等")
    String uiType() default "";

    @Schema(title = "表单项样式", description = "表单项的样式")
    String style() default "";

}
