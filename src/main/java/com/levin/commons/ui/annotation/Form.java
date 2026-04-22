package com.levin.commons.ui.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 表单
 * <p>
 * 主要定义布局和动作列表
 *
 * <p>
 * 表单最外层为单列布局
 *
 * @author llw
 */
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "表单", description = "通常注解在控制器方法的参数上")
public @interface Form {

    @Schema(title = "表单名称", description = "关联属性名称")
    String name() default "";

    @Schema(title = "标题", description = "表单标题")
    String title() default "";

    @Schema(title = "数据初始化API", description = "数据初始化API")
    String loadApi() default "";

    @Schema(title = "表单验证规则", description = "Js脚本,通常用于跨字段的验证")
    String[] verifyRules() default {};

    @Schema(title = "表单布局", description = "多个表单布局, 多个布局之间采用vbox布局, 就是按一列纵向布局")
    FormLayout[] layouts() default {@FormLayout()};

    @Schema(title = "表单操作", description = "未配置则默认提交和取消 2个操作按钮")
    Action[] actions() default {};

    @Schema(title = "描述", description = "表单描述")
    String desc() default "";

    @Schema(title = "表单样式", description = "表单样式")
    String style() default "";

    @Target({ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @interface Action {

        @Schema(title = "展示名称", description = "按钮展示名称")
        String label();

        @Schema(title = "表单验证规则", description = "覆盖表单的验证规则, 如先暂存数据, 不提交")
        String[] verifyRules() default {};

        @Schema(title = "执行动作前的确认提示信息", description = "执行动作前的确认提示信息")
        String beforeConfirmInfo() default "";

        @Schema(title = "提交后执行的动作", description = "Js脚本, 参数变量名:actionResult")
        String afterSubmitExpr() default "";

        @Schema(title = "提交动作参数", description = "Json格式, 比暂存和提交 调用同一个API接口,但是单参数可能不同")
        String submitParams() default "";

        @Schema(title = "提交动作执行表达式", description = "Js脚本, 默认动作表达式为空，也就是表单提交到API")
        String submitExpr() default "";

        @Schema(title = "动作描述", description = "动作描述")
        String desc() default "";

    }
}
