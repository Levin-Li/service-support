package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FontVariantEmoji
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_FontVariantEmoji")
public @interface Property_FontVariantEmoji {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"emoji"},{"type":"string","const":"normal"},{"type":"string","const":"text"},{"type":"string","const":"unicode"}]
   *
   *
   */
    String[] consts = { "emoji", "normal", "text", "unicode" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
