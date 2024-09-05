package com.levin.commons.ui.annotation;

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
public @interface Form {

    /**
     * 标题
     *
     * @return
     */
    String name() default "";

    /**
     * 描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 表单样式
     *
     * @return
     */
    String style() default "";

    /**
     * 数据初始化API
     *
     * @return
     */
    String loadApi() default "";

    /**
     * 表单组合校验规则
     *
     * @return
     */
    String[] verifyRules() default {};

    /**
     * 表单布局
     * <p>
     * 一个表单有多个布局器，多个布局器通过布局分组实现布局嵌套
     *
     * @return
     */
    FormLayout[] layouts() default {@FormLayout()};

    /**
     * 表单动作
     *
     * @return
     */
    Action[] actions() default {
            @Action(name = "提交", beforeConfirmInfo = "确认提交？", api = "${api}"),
            @Action(name = "取消", beforeConfirmInfo = "确认取消？", expr = "cancel"),
    };

}
