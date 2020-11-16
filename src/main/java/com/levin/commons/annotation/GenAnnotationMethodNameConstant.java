package com.levin.commons.annotation;

import java.lang.annotation.*;

/**
 * 对注解的方法名生成常量
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface GenAnnotationMethodNameConstant {

}
