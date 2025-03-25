package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * debounceConfig
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "debounceConfig")
public @interface debounceConfig {
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
     * maxWait
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "maxWait")
    double maxWait() default 0;

    /**
     * wait
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "wait")
    double _wait() default 0;

    /**
     * leading
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "leading")
    boolean leading() default false;

    /**
     * trailing
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "trailing")
    boolean trailing() default false;

}
