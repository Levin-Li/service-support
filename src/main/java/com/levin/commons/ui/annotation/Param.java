package com.levin.commons.ui.annotation;

/**
 * @author llw
 */
public @interface Param {

    /**
     * 参数名称
     * 对应查询对象的字段名称，或是API接口的URL参数名称
     *
     * @return
     */
    String name();

    /**
     * 参数名称对齐的 求值表达式
     * <p>
     * 默认是属性名称
     *
     * @return
     */
    String evalExpr();

    /**
     * 是否是可搜索的
     * <p>
     * 只支持文本搜索
     *
     * @return
     */
    boolean searchable() default false;

    /**
     * 占位符，通常用于搜索框
     *
     * @return
     */
    String placeholder() default "";

    /**
     * 参数默认值
     *
     * @return
     */
    String defaultValue() default "";

}
