package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * MultipleValue
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "MultipleValue")
public @interface MultipleValue {
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
     * min
     *
     * 参考定义: {"type":"number"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "min")
    double min() default 0;

    /**
     * max
     *
     * 参考定义: {"type":"number"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "max")
    double max() default 0;

}
