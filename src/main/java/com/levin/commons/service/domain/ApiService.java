package com.levin.commons.service.domain;


import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

public @interface ApiService {

    /**
     * 服务描述
     *
     * @return String
     */
    String value() default "";


    /**
     * 标识或代码
     *
     * @return String
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
     * 详细描述信息
     *
     * @return
     */
    String detail() default "";

    /**
     * 忽略授权
     *
     * @return
     */
    boolean ignoreAuth() default false;

    /**
     * 排序
     *
     * @return
     */
    int order() default Integer.MAX_VALUE;

}
