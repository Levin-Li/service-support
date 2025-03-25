package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * QRCodeImageSettings
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "QRCodeImageSettings")
public @interface QRCodeImageSettings {
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
     * src
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "src")
    String src() default "	";

    /**
     * height
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "height")
    double height() default 0;

    /**
     * width
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "width")
    double width() default 0;

    /**
     * excavate
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "excavate")
    boolean excavate() default false;

    /**
     * x
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "x")
    double x() default 0;

    /**
     * y
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "y")
    double y() default 0;

}
