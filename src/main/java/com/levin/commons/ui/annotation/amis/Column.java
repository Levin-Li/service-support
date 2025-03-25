package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Column
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Column")
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
    
    @Schema(title = "指定列唯一标识")
    String name() default "	";

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
    
    @Schema(title = "指定列标题")
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
    
    @Schema(title = "指定列内容渲染器")
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
    
    @Schema(title = "指定行合并表达式")
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
    
    @Schema(title = "指定列合并表达式")
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
    
    @Schema(title = "表头分组")
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
    
    @Schema(title = "可复制")
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
    
    @Schema(title = "列表头提示")
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
    
    @Schema(title = "快速搜索")
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
    
    @Schema(title = "快速排序")
    boolean sorter() default false;

    /**
     * 兼容table快速排序
     *
     * 参考定义: {"type":"boolean","description":"兼容table快速排序"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "兼容table快速排序")
    boolean sortable() default false;

    /**
     * 兼容table列筛选
     *
     * 参考定义: {"type":"object","properties":{"source":{"type":"string"},"options":{"type":"array","items":{}}},"additionalProperties":false,"description":"兼容table列筛选"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "兼容table列筛选")
    String filterable() default "	";

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
    
    @Schema(title = "内容居左、居中、居右")
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
    
    @Schema(title = "是否固定在左侧/右侧")
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
    
    @Schema(title = "当前列是否展示")
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
    
    @Schema(title = "列样式")
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
    
    @Schema(title = "表头单元格样式")
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
    
    @Schema(title = "单元格样式")
    String classNameExpr() default "	";

    /**
     * 配置快速编辑功能
     *
     * 参考定义: "#/definitions/SchemaQuickEdit"
     *
     * 
     *
     * [{"type":"boolean"},{"$ref":"#/definitions/SchemaQuickEditObject"}]
     *
     * @see QuickEdit
     */
    
    @Schema(title = "配置快速编辑功能")
    String quickEdit() default "	";

    /**
     * width
     *
     * 参考定义: {"type":["string","number"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "width")
    String width() default "	";

    /**
     * 表格列单元格是否可以获取父级数据域值，默认为true，该配置对当前列内单元格生效
     *
     * 参考定义: {"type":"boolean","description":"表格列单元格是否可以获取父级数据域值，默认为true，该配置对当前列内单元格生效"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表格列单元格是否可以获取父级数据域值，默认为true，该配置对当前列内单元格生效")
    boolean canAccessSuperData() default false;

}
