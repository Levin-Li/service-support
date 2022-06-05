package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ColumnFill
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ColumnFill")
public @interface Property_ColumnFill {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"balance"},{"type":"string","const":"balance-all"}]
   */

    String[] consts = { "auto", "balance", "balance-all" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
