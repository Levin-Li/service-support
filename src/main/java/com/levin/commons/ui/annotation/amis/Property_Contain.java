package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_Contain
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_Contain")
public @interface Property_Contain {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"content"},{"type":"string","const":"inline-size"},{"type":"string","const":"layout"},{"type":"string","const":"none"},{"type":"string","const":"paint"},{"type":"string","const":"size"},{"type":"string","const":"strict"},{"type":"string","const":"style"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "content", "inline-size", "layout", "none", "paint", "size", "strict", "style" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
