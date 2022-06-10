package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * RowSelectionOptions
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "RowSelectionOptions")
public @interface RowSelectionOptions {
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
     * 选择类型 选择全部
     *
     * 参考定义: {"type":"string","description":"选择类型 选择全部"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "选择类型 选择全部")
    String key() default "	";

    /**
     * 选项显示文本
     *
     * 参考定义: {"type":"string","description":"选项显示文本"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "选项显示文本")
    String text() default "	";

}
