package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * 表单
 * @author llw
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface Form {

}
