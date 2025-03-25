package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MsContentZoomSnap
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_MsContentZoomSnap")
public @interface Property_MsContentZoomSnap {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "mandatory", "none", "proximity" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
