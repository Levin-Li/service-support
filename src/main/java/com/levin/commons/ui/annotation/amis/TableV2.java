package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * TableV2
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "TableV2")
public @interface TableV2 {
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
     * 指定为表格类型
     *
     * 参考定义: {"type":"string","const":"table-v2","description":"指定为表格类型"}
     *
     * @see 
     */
    @Schema(description = "指定为表格类型")
    String type() default "table-v2";

    /**
     * 自定义表格样式
     *
     * 参考定义: {"type":"string","description":"自定义表格样式"}
     *
     * @see 
     */
    @Schema(description = "自定义表格样式")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     * @see 
     */
    @Schema(description = "是否禁用")
    boolean disabled() default false;

    /**
     * 是否禁用表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(description = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     * @see 
     */
    @Schema(description = "是否隐藏")
    boolean hidden() default false;

    /**
     * 是否隐藏表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(description = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     * @see 
     */
    @Schema(description = "是否显示")
    boolean visible() default false;

    /**
     * 是否显示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
     *
     * @see 
     */
    @Schema(description = "组件唯一 id，主要用于日志采集")
    String id() default "	";

    /**
     * 事件动作配置
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}
     *
     * @see 
     */
    @Schema(description = "事件动作配置")
    String onEvent() default "	";

    /**
     * 表格标题
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaObject"},{"type":"array","items":{"$ref":"#/definitions/SchemaObject"}}],"description":"表格标题"}
     *
     * @see 
     */
    @Schema(description = "表格标题")
    String title() default "	";

    /**
     * 表格数据源
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * @see 
     */
    @Schema(description = "表格数据源")
    String source() default "	";

    /**
     * 表格可自定义列
     *
     * 参考定义: {"type":"boolean","description":"表格可自定义列"}
     *
     * @see 
     */
    @Schema(description = "表格可自定义列")
    boolean columnsTogglable() default false;

    /**
     * 表格列配置
     *
     * 参考定义: "#/definitions/ColumnSchema"
     *
     * @see 
     */
    @Schema(description = "表格列配置")
    Column[] columns() ;

    /**
     * 表格可选择配置
     *
     * 参考定义: "#/definitions/RowSelectionSchema"
     *
     * @see 
     */
    @Schema(description = "表格可选择配置")
    RowSelection rowSelection() ;

    /**
     * 表格行可展开配置
     *
     * 参考定义: "#/definitions/ExpandableSchema"
     *
     * @see 
     */
    @Schema(description = "表格行可展开配置")
    Expandable expandable() ;

    /**
     * 表格行可展开内容配置
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "表格行可展开内容配置")
    String[] expandableBody() default "	";

    /**
     * 粘性头部
     *
     * 参考定义: {"type":"boolean","description":"粘性头部"}
     *
     * @see 
     */
    @Schema(description = "粘性头部")
    boolean sticky() default false;

    /**
     * 加载中
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string"},{"$ref":"#/definitions/SchemaObject"}],"description":"加载中"}
     *
     * @see 
     */
    @Schema(description = "加载中")
    boolean loading() default false;

    /**
     * 行角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * @see 
     */
    @Schema(description = "行角标")
    Badge itemBadge() ;

    /**
     * 指定挂载dom
     *
     * 参考定义: {"description":"指定挂载dom"}
     *
     * @see 
     */
    @Schema(description = "指定挂载dom")
    String popOverContainer() default "	";

    /**
     * 嵌套展开记录的唯一标识
     *
     * 参考定义: {"type":"string","description":"嵌套展开记录的唯一标识"}
     *
     * @see 
     */
    @Schema(description = "嵌套展开记录的唯一标识")
    String keyField() default "	";

    /**
     * 数据源嵌套自定义字段名
     *
     * 参考定义: {"type":"string","description":"数据源嵌套自定义字段名"}
     *
     * @see 
     */
    @Schema(description = "数据源嵌套自定义字段名")
    String childrenColumnName() default "	";

    /**
     * 自定义行样式
     *
     * 参考定义: {"type":"string","description":"自定义行样式"}
     *
     * @see 
     */
    @Schema(description = "自定义行样式")
    String rowClassNameExpr() default "	";

    /**
     * 是否固定内容行高度
     *
     * 参考定义: {"type":"string","description":"是否固定内容行高度"}
     *
     * @see 
     */
    @Schema(description = "是否固定内容行高度")
    String lineHeight() default "	";

    /**
     * 是否展示边框
     *
     * 参考定义: {"type":"boolean","description":"是否展示边框"}
     *
     * @see 
     */
    @Schema(description = "是否展示边框")
    boolean bordered() default false;

    /**
     * 是否展示表头
     *
     * 参考定义: {"type":"boolean","description":"是否展示表头"}
     *
     * @see 
     */
    @Schema(description = "是否展示表头")
    boolean showHeader() default false;

    /**
     * 指定表尾
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaObject"},{"type":"array","items":{"$ref":"#/definitions/SchemaObject"}}],"description":"指定表尾"}
     *
     * @see 
     */
    @Schema(description = "指定表尾")
    String footer() default "	";

}
