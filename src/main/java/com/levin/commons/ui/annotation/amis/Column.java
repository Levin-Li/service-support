package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Column
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Column")
public @interface Column {
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
     * 指定列唯一标识
     *
     * 参考定义: {"type":"string","description":"指定列唯一标识"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定列唯一标识")
    String key() default "	";

    /**
     * 指定列标题
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaObject"}],"description":"指定列标题"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定列标题")
    String title() default "	";

    /**
     * 指定列内容渲染器
     *
     * 参考定义: {"type":"string","description":"指定列内容渲染器"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定列内容渲染器")
    String type() default "	";

    /**
     * 指定行合并表达式
     *
     * 参考定义: {"type":"string","description":"指定行合并表达式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定行合并表达式")
    String rowSpanExpr() default "	";

    /**
     * 指定列合并表达式
     *
     * 参考定义: {"type":"string","description":"指定列合并表达式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定列合并表达式")
    String colSpanExpr() default "	";

    /**
     * 表头分组
     *
     * 参考定义: "#/definitions/ColumnSchema"
     *
     * 
     *
     * 
     *
     * @see Column
     */
    
    @Schema(description = "表头分组")
    String[] children() default "	";

    /**
     * 可复制
     *
     * 参考定义: {"type":"boolean","description":"可复制"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "可复制")
    boolean copyable() default false;

    /**
     * 列表头提示
     *
     * 参考定义: {"type":"string","description":"列表头提示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "列表头提示")
    String remark() default "	";

    /**
     * 快速搜索
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"$ref":"#/definitions/SchemaObject"}],"description":"快速搜索"}
     *
     * [{"type":"boolean"},{"$ref":"#/definitions/SchemaObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "快速搜索")
    String searchable() default "	";

    /**
     * 快速排序
     *
     * 参考定义: {"type":"boolean","description":"快速排序"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "快速排序")
    boolean sorter() default false;

    /**
     * 内容居左、居中、居右
     *
     * 参考定义: {"type":"string","description":"内容居左、居中、居右"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "内容居左、居中、居右")
    String align() default "	";

    /**
     * 是否固定在左侧/右侧
     *
     * 参考定义: {"type":["boolean","string"],"description":"是否固定在左侧/右侧"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否固定在左侧/右侧")
    String fixed() default "	";

    /**
     * 当前列是否展示
     *
     * 参考定义: {"type":"boolean","description":"当前列是否展示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "当前列是否展示")
    boolean toggled() default false;

    /**
     * 列样式
     *
     * 参考定义: {"type":"string","description":"列样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "列样式")
    String className() default "	";

    /**
     * 表头单元格样式
     *
     * 参考定义: {"type":"string","description":"表头单元格样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "表头单元格样式")
    String titleClassName() default "	";

    /**
     * 单元格样式
     *
     * 参考定义: {"type":"string","description":"单元格样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "单元格样式")
    String classNameExpr() default "	";

}
