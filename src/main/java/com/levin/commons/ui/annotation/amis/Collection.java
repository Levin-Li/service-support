package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Collection
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-7 23:06:28
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Collection")
public @interface Collection {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

}
