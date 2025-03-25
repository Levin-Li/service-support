package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataProviderCollection
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "DataProviderCollection")
public @interface DataProviderCollection {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * 
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * inited
     *
     * 参考定义: "#/definitions/DataProvider"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","properties":{"prototype":{},"length":{"type":"number"},"arguments":{},"caller":{"$ref":"#/definitions/interface-1922134811-9821-11285-1922134811-0-212510"}},"required":["prototype","length","arguments","caller"],"additionalProperties":false}]
     *
     * @see DataProvider
     */
    
    @Schema(title = "inited")
    String inited() default "	";

    /**
     * onApiFetched
     *
     * 参考定义: "#/definitions/DataProvider"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","properties":{"prototype":{},"length":{"type":"number"},"arguments":{},"caller":{"$ref":"#/definitions/interface-1922134811-9821-11285-1922134811-0-212510"}},"required":["prototype","length","arguments","caller"],"additionalProperties":false}]
     *
     * @see DataProvider
     */
    
    @Schema(title = "onApiFetched")
    String onApiFetched() default "	";

    /**
     * onWsFetched
     *
     * 参考定义: "#/definitions/DataProvider"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","properties":{"prototype":{},"length":{"type":"number"},"arguments":{},"caller":{"$ref":"#/definitions/interface-1922134811-9821-11285-1922134811-0-212510"}},"required":["prototype","length","arguments","caller"],"additionalProperties":false}]
     *
     * @see DataProvider
     */
    
    @Schema(title = "onWsFetched")
    String onWsFetched() default "	";

}
