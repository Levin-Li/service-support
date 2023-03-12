package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * 表单动作
 * <p>
 * 包括提交到指定的API和本地表达式
 *
 * @author llw
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FormAction {

    /**
     * 动作名称
     *
     * @return
     */
    String name();


    /**
     * 执行表单动作前的校验规则
     *
     * @return
     */
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
    String afterExpr() default "";

    /**
     * 参数名称对齐的 求值表达式
     * <p>
     * 默认是属性名称
     *
     * @return
     */
    String api() default "";

    /**
     * 动作表达式
     * <p>
     * 默认的动作表达式为空，把表单提交到API
     *
     * @return
     */
    String expr() default "";

    /**
     * 动作描述
     *
     * @return
     */
    String desc() default "";

}
