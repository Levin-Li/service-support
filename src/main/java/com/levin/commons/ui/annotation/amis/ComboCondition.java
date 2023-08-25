package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ComboCondition
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ComboCondition")
public @interface ComboCondition {
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
     * test
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "test")
    String test() default "	";

    /**
     * items
     *
     * 参考定义: "#/definitions/ComboSubControl"
     *
     *
     *
     *
     *
     * @see ComboSubControl
     */

    @Schema(title = "items")
    String[] items() default "	";

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "label")
    String label() default "	";

    /**
     * scaffold
     *
     * 参考定义: {}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "scaffold")
    String scaffold() default "	";

    /**
     * mode
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "mode")
    String mode() default "	";

}
