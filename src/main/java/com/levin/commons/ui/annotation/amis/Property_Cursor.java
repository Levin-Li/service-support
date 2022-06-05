package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Cursor
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Cursor")
public @interface Property_Cursor {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-grab"},{"type":"string","const":"-webkit-grab"},{"type":"string","const":"alias"},{"type":"string","const":"all-scroll"},{"type":"string","const":"auto"},{"type":"string","const":"cell"},{"type":"string","const":"col-resize"},{"type":"string","const":"context-menu"},{"type":"string","const":"copy"},{"type":"string","const":"crosshair"},{"type":"string","const":"default"},{"type":"string","const":"e-resize"},{"type":"string","const":"ew-resize"},{"type":"string","const":"grab"},{"type":"string","const":"grabbing"},{"type":"string","const":"help"},{"type":"string","const":"move"},{"type":"string","const":"n-resize"},{"type":"string","const":"ne-resize"},{"type":"string","const":"nesw-resize"},{"type":"string","const":"no-drop"},{"type":"string","const":"none"},{"type":"string","const":"not-allowed"},{"type":"string","const":"ns-resize"},{"type":"string","const":"nw-resize"},{"type":"string","const":"nwse-resize"},{"type":"string","const":"pointer"},{"type":"string","const":"progress"},{"type":"string","const":"row-resize"},{"type":"string","const":"s-resize"},{"type":"string","const":"se-resize"},{"type":"string","const":"sw-resize"},{"type":"string","const":"text"},{"type":"string","const":"vertical-text"},{"type":"string","const":"w-resize"},{"type":"string","const":"wait"},{"type":"string","const":"zoom-in"},{"type":"string","const":"zoom-out"},{"type":"string"}]
   */

    String[] consts = { "-moz-grab", "-webkit-grab", "alias", "all-scroll", "auto", "cell", "col-resize", "context-menu", "copy", "crosshair", "default", "e-resize", "ew-resize", "grab", "grabbing", "help", "move", "n-resize", "ne-resize", "nesw-resize", "no-drop", "none", "not-allowed", "ns-resize", "nw-resize", "nwse-resize", "pointer", "progress", "row-resize", "s-resize", "se-resize", "sw-resize", "text", "vertical-text", "w-resize", "wait", "zoom-in", "zoom-out" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
