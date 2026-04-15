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

    /**
     * 数据初始化API
     *
     * @return
     */
    @Schema(title = "数据初始化API", description = "数据初始化API")
    String loadApi() default "";

    /**
     * 表单组合校验规则
     *
     * @return
     */
    @Schema(title = "表单验证规则", description = "多个表单验证规则")
    String[] verifyRules() default {};

    /**
     * 表单布局
     * <p>
     * 一个表单有多个布局器，多个布局器通过布局分组实现布局嵌套
     *
     * @return
     */
    @Schema(title = "表单布局", description = "多个表单布局, 多个布局之间采用vbox布局, 就是按一列纵向布局")
    FormLayout[] layouts() default {@FormLayout()};

    /**
     * 表单动作
     *
     * @return
     */
    @Schema(title = "表单操作", description = "默认未配置, 提交和取消 2个操作按钮")
    Action[] actions() default {};

    @Schema(title = "描述", description = "表单描述")
    String desc() default "";


    @Schema(title = "表单样式", description = "表单样式")
    String style() default "";

    /**
     * 表单动作
     *
     * @author llw
     */
    @Target({ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @interface Action {

        /**
         * 动作名称
         *
         * @return
         */
        @Schema(title = "展示名称", description = "按钮展示名称")
        String label();

        /**
         * 执行表单动作前的校验规则
         *
         * @return
         */
        @Schema(title = "表单验证规则", description = "可覆盖表单的验证规则,如先暂存数据,不提交")
        String[] verifyRules() default {};

        /**
         * 执行动作前的确认提示信息
         *
         * @return
         */
        String beforeConfirmInfo() default "";

        /**
         * 提交后执行的动作
         * <p>
         * 提交API或是动作表达式的返回结果作为参数 actionResult
         *
         * @return
         */
        @Schema(title = "提交后执行的动作", description = "参数变量名:actionResult")
        String afterExpr() default "";


        @Schema(title = "动作参数", description = "Json格式, 比暂存和提交 调用同一个API接口,但单参数可能不同")
        String params() default "";

        /**
         * 动作表达式
         * <p>
         * 默认的动作表达式为空，把表单提交到API
         *
         * @return
         */
        @Schema(title = "动作表达式", description = "默认动作表达式为空，也就是表单提交到API")
        String expr() default "";

        /**
         * 动作描述
         *
         * @return
         */
        String desc() default "";

    }
}
