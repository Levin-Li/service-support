package com.levin.commons.service.domain;


import java.lang.annotation.*;

/**
 * 类，方法，参数，字段的描述注解
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
public @interface Desc {

    /**
     * 默认名称
     *
     * @return String
     */
    String value() default "";


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
