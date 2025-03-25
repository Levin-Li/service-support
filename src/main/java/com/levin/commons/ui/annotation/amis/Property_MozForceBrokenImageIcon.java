package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MozForceBrokenImageIcon
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_MozForceBrokenImageIcon")
public @interface Property_MozForceBrokenImageIcon {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"number","const":0},{"type":"string"},{"type":"number","const":1}]
   *
   *
   */
    String[] consts = { "0", "1" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
