package com.levin.commons.service.domain;


import java.lang.annotation.*;


@Target({ElementType.PACKAGE, ElementType.TYPE,
        ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD,
        ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

/**
 *
 * @author echo
 *
 *
 *
 */
public @interface Ignore {

    /**
     * 忽略的目标
     *
     * @return String
     */
    String[] value() default {};


    /**
     * 忽略的条件
     *
     * @return
     */
    String condition() default "";

}
