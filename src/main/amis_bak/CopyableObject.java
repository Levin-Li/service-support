package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * CopyableObject
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
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
    @Schema(title = "可以配置图标")
    Icon icon() ;

    /**
     * 配置复制时的内容模板。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(title = "配置复制时的内容模板。")
    Tpl content() ;

}
