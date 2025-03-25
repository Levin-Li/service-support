package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * AutoGenerateFilterObject
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "AutoGenerateFilterObject")
public @interface AutoGenerateFilterObject {
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
     * 过滤条件单行列数
     *
     * 参考定义: {"type":"number","description":"过滤条件单行列数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "过滤条件单行列数")
    double columnsNum() default 0;

    /**
     * 是否显示设置查询字段
     *
     * 参考定义: {"type":"boolean","description":"是否显示设置查询字段"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示设置查询字段")
    boolean showBtnToolbar() default false;

    /**
     * 是否默认收起
     *
     * 参考定义: {"type":"boolean","description":"是否默认收起","default":true}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否默认收起")
    boolean defaultCollapsed() default false;

}
