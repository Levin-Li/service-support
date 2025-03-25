package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TransitionBehavior
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TransitionBehavior")
public @interface Property_TransitionBehavior {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"allow-discrete"},{"type":"string","const":"normal"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "allow-discrete", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
