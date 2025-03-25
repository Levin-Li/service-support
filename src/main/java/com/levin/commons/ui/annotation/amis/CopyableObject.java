package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * CopyableObject
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "CopyableObject")
public @interface CopyableObject {
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
     * 可以配置图标
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * 
     *
     * 
     *
     * @see Icon
     */
    
    @Schema(title = "可以配置图标")
    Icon icon() ;

    /**
     * 配置复制时的内容模板。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "配置复制时的内容模板。")
    Tpl content() ;

    /**
     * 提示文字内容
     *
     * 参考定义: {"type":"string","description":"提示文字内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "提示文字内容")
    String tooltip() default "	";

}
