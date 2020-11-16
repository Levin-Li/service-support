package com.levin.commons.service.domain;


import java.lang.annotation.*;


@Target({ElementType.PACKAGE, ElementType.TYPE,ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD,
        ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Param {

    /**
     *
     * @return
     */
    String name() default "";

    /**
     * 参数值
     *
     * @return String
     */
    String value() default "";

}
