package com.levin.commons.service.domain;


import java.lang.annotation.*;

/**
 * 类，方法，参数，字段的扩展描述注解
 *
 * @author Laishr
 * @version 1.0, 2015/11/06
 * Copyright (c) 2015.
 */
@Target({ElementType.PACKAGE, ElementType.TYPE,
        ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD,
        ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Deprecated//使用 FormItem 注解代替
public @interface Desc {

    /**
     * 默认名称
     *
     * @return String
     */
    String value() default "";

    /**
     * 字典代码
     * 用于可选择值
     * 特殊值 $@ 表示被注解字段的类名+"."+字段名，例如 User.type
     *
     * @return int
     */
    String dictCode() default "";

    /**
     * 选项,逗号隔开，格式为：值:名称,例如：1:男,2:女
     *
     * @return
     */
    String options() default "";

    /**
     * UI 表单输入类型
     *
     * @return
     */
    String uiInputType() default "";

    /**
     * UI 显示类型
     *
     * @return
     */
    String uiShowType() default "";

    /**
     * 标识或代码
     *
     * @return int
     */
    String code() default "";

    /**
     * 名称
     * 通常指中文名称
     *
     * @return
     */
    String name() default "";

    /**
     * 分组名称
     * <p>
     * 可以对属性或是服务进行分组
     * <p>
     * 按照字段或是方法的的自然顺序进行分组
     *
     * @return
     * @since 1.1.8
     */
    String group() default "";

    /**
     * 详细信息
     *
     * @return
     */
    String detail() default "";

    /**
     * 是否做为文档内容
     *
     * @return
     */
    boolean toDoc() default true;

    /**
     * 隐藏的
     *
     * @return
     */
    boolean hide() default false;

}
