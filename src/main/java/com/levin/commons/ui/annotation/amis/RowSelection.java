package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * RowSelection
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "RowSelection")
public @interface RowSelection {
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
     * 选择类型 单选/多选
     *
     * 参考定义: {"type":"string","description":"选择类型 单选/多选"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "选择类型 单选/多选")
    String type() default "	";

    /**
     * 对应数据源的key值
     *
     * 参考定义: {"type":"string","description":"对应数据源的key值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "对应数据源的key值")
    String keyField() default "	";

    /**
     * 行是否禁用表达式
     *
     * 参考定义: {"type":"string","description":"行是否禁用表达式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "行是否禁用表达式")
    String disableOn() default "	";

    /**
     * 自定义选择菜单
     *
     * 参考定义: "#/definitions/RowSelectionOptionsSchema"
     *
     * 
     *
     * 
     *
     * @see RowSelectionOptions
     */
    
    @Schema(title = "自定义选择菜单")
    RowSelectionOptions[] selections() ;

    /**
     * 已选择的key值
     *
     * 参考定义: {"type":"array","items":{"type":["string","number"]},"description":"已选择的key值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "已选择的key值")
    String[] selectedRowKeys() default "	";

    /**
     * 已选择的key值表达式
     *
     * 参考定义: {"type":"string","description":"已选择的key值表达式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "已选择的key值表达式")
    String selectedRowKeysExpr() default "	";

    /**
     * 已选择的key值表达式
     *
     * 参考定义: {"type":"number","description":"已选择的key值表达式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "已选择的key值表达式")
    double columnWidth() default 0;

    /**
     * 是否点击行触发选中或取消选中
     *
     * 参考定义: {"type":"boolean","description":"是否点击行触发选中或取消选中"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否点击行触发选中或取消选中")
    boolean rowClick() default false;

}
