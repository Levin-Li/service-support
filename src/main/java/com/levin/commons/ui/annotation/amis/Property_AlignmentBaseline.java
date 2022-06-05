package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_AlignmentBaseline
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_AlignmentBaseline")
public @interface Property_AlignmentBaseline {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"after-edge"},{"type":"string","const":"alphabetic"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"before-edge"},{"type":"string","const":"central"},{"type":"string","const":"hanging"},{"type":"string","const":"ideographic"},{"type":"string","const":"mathematical"},{"type":"string","const":"middle"},{"type":"string","const":"text-after-edge"},{"type":"string","const":"text-before-edge"}]
   */

    String[] consts = { "after-edge", "alphabetic", "auto", "baseline", "before-edge", "central", "hanging", "ideographic", "mathematical", "middle", "text-after-edge", "text-before-edge" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
