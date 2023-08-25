package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Name
 *
 * 组件名字，这个名字可以用来定位，用于组件通信
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "组件名字，这个名字可以用来定位，用于组件通信")
public @interface Name {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
