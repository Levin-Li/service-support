package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_WhiteSpaceCollapse
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_WhiteSpaceCollapse")
public @interface Property_WhiteSpaceCollapse {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"break-spaces"},{"type":"string","const":"collapse"},{"type":"string","const":"discard"},{"type":"string","const":"preserve"},{"type":"string","const":"preserve-breaks"},{"type":"string","const":"preserve-spaces"}]
   *
   *
   */
    String[] consts = { "break-spaces", "collapse", "discard", "preserve", "preserve-breaks", "preserve-spaces" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
