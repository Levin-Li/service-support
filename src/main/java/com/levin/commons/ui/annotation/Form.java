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
    String title() default "";

    /**
     * 描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 布局样式
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
     * 可以通过布局分组实现布局嵌套
     *
     * @return
     */
    FormLayout[] layouts() default {@FormLayout()};

    /**
     * 表单动作
     *
     * @return
     */
    FormAction[] actions() default {
            @FormAction(name = "提交", beforeConfirmInfo = "确认提交？", api = "${api}"),
            @FormAction(name = "取消", beforeConfirmInfo = "确认取消？", expr = "cancel"),
    };

}
