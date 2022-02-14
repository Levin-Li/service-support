package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * QuickEdit
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "QuickEdit")
public @interface QuickEdit {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   boolean value() default false;

}
