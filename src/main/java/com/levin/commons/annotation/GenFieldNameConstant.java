package com.levin.commons.annotation;

import java.lang.annotation.*;

/**
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface GenFieldNameConstant {

    /**
     * 忽略
     *
     * @return
     */
    boolean ignore() default false;


    /**
     * 生成字段是否用继承的模式
     *
     * @return
     */
    boolean extendsMode() default true;

}
