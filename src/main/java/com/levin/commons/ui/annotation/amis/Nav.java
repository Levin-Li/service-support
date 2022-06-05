package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Nav
 *
 * Nav 导航渲染器 文档：https://baidu.gitee.io/amis/docs/components/nav
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Nav 导航渲染器 文档：https://baidu.gitee.io/amis/docs/components/nav")
public @interface Nav {
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
     * 指定为 Nav 导航渲染器
     *
     * 参考定义: {"type":"string","const":"nav","description":"指定为 Nav 导航渲染器"}
     *
     * @see 
     */
    @Schema(description = "指定为 Nav 导航渲染器")
    String type() default "nav";

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
     * 链接地址集合
     *
     * 参考定义: "#/definitions/NavItemSchema"
     *
     * @see 
     */
    @Schema(description = "链接地址集合")
    NavItem[] links() ;

    /**
     * indentSize
     *
     * 参考定义: {"type":"number","default":24}
     *
     * @see 
     */
    @Schema(description = "indentSize")
    double indentSize() default 0;

    /**
     * 可以通过 API 拉取。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "可以通过 API 拉取。")
    String source() default "	";

    /**
     * 懒加载 api，如果不配置复用 source 接口。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "懒加载 api，如果不配置复用 source 接口。")
    String deferApi() default "	";

    /**
     * true 为垂直排列，false 为水平排列类似如 tabs。
     *
     * 参考定义: {"type":"boolean","description":"true 为垂直排列，false 为水平排列类似如 tabs。"}
     *
     * @see 
     */
    @Schema(description = "true 为垂直排列，false 为水平排列类似如 tabs。")
    boolean stacked() default false;

    /**
     * 更多操作菜单列表
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "更多操作菜单列表")
    String itemActions() default "	";

    /**
     * 可拖拽
     *
     * 参考定义: {"type":"boolean","description":"可拖拽"}
     *
     * @see 
     */
    @Schema(description = "可拖拽")
    boolean draggable() default false;

    /**
     * 保存排序的 api
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "保存排序的 api")
    String saveOrderApi() default "	";

    /**
     * 角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * @see 
     */
    @Schema(description = "角标")
    Badge itemBadge() ;

    /**
     * 仅允许同层级拖拽
     *
     * 参考定义: {"type":"boolean","description":"仅允许同层级拖拽"}
     *
     * @see 
     */
    @Schema(description = "仅允许同层级拖拽")
    boolean dragOnSameLevel() default false;

    /**
     * 横向导航时自动收纳配置
     *
     * 参考定义: "#/definitions/NavOverflow"
     *
     * @see 
     */
    @Schema(description = "横向导航时自动收纳配置")
    NavOverflow overflow() ;

}
