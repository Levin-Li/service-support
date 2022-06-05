package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * CopyableObject
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "CopyableObject")
public @interface CopyableObject {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
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
     * @see 
     */
    @Schema(description = "可以配置图标")
    Icon icon() ;

    /**
     * 配置复制时的内容模板。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "配置复制时的内容模板。")
    Tpl content() ;

}
