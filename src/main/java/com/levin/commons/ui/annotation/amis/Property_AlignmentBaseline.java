package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_AlignmentBaseline
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_AlignmentBaseline")
public @interface Property_AlignmentBaseline {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"after-edge"},{"type":"string","const":"alphabetic"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"before-edge"},{"type":"string","const":"central"},{"type":"string","const":"hanging"},{"type":"string","const":"ideographic"},{"type":"string","const":"mathematical"},{"type":"string","const":"middle"},{"type":"string","const":"text-after-edge"},{"type":"string","const":"text-before-edge"}]
   *
   *
   */
    String[] consts = { "after-edge", "alphabetic", "auto", "baseline", "before-edge", "central", "hanging", "ideographic", "mathematical", "middle", "text-after-edge", "text-before-edge" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
