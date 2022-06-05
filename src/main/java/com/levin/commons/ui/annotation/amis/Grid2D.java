package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Grid2D
 *
 * 二维布局渲染器。 文档：https://baidu.gitee.io/amis/docs/components/grid-2d
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "二维布局渲染器。 文档：https://baidu.gitee.io/amis/docs/components/grid-2d")
public @interface Grid2D {
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
     * 指定为 grid-2d 展示类型
     *
     * 参考定义: {"type":"string","const":"grid-2d","description":"指定为 grid-2d 展示类型"}
     *
     * @see 
     */
    @Schema(description = "指定为 grid-2d 展示类型")
    String type() default "grid-2d";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "容器 css 类名")
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
     * 列数量，默认是 12
     *
     * 参考定义: {"type":"number","description":"列数量，默认是 12"}
     *
     * @see 
     */
    @Schema(description = "列数量，默认是 12")
    double cols() default 0;

    /**
     * grid 2d 容器宽度，默认是 auto
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string"},{"type":"string","const":"auto"}],"description":"grid 2d 容器宽度，默认是 auto"}
     *
     * @see 
     */
    @Schema(description = "grid 2d 容器宽度，默认是 auto")
    double width() default 0;

    /**
     * 格子间距，默认 0，包含行和列
     *
     * 参考定义: {"type":["number","string"],"description":"格子间距，默认 0，包含行和列"}
     *
     * @see 
     */
    @Schema(description = "格子间距，默认 0，包含行和列")
    String gap() default "	";

    /**
     * 格子行级别的间距，如果不设置就和 gap 一样
     *
     * 参考定义: {"type":["number","string"],"description":"格子行级别的间距，如果不设置就和 gap 一样"}
     *
     * @see 
     */
    @Schema(description = "格子行级别的间距，如果不设置就和 gap 一样")
    String gapRow() default "	";

    /**
     * 单位行高度，默认 50 px
     *
     * 参考定义: {"type":["number","string"],"description":"单位行高度，默认 50 px"}
     *
     * @see 
     */
    @Schema(description = "单位行高度，默认 50 px")
    String rowHeight() default "	";

    /**
     * 每个格子的配置
     *
     * 参考定义: "#/definitions/Grid"
     *
     * @see 
     */
    @Schema(description = "每个格子的配置")
    String[] grids() default "	";

}
