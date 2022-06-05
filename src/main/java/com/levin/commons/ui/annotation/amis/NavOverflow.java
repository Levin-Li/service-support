package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * NavOverflow
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "NavOverflow")
public @interface NavOverflow {
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
     * 是否开启响应式收纳
     *
     * 参考定义: {"type":"boolean","description":"是否开启响应式收纳"}
     *
     * @see 
     */
    @Schema(description = "是否开启响应式收纳")
    boolean enable() default false;

    /**
     * 菜单触发按钮的文字
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaObject"}],"description":"菜单触发按钮的文字"}
     *
     * @see 
     */
    @Schema(description = "菜单触发按钮的文字")
    String overflowLabel() default "	";

    /**
     * 菜单触发按钮的图标
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see 
     */
    @Schema(description = "菜单触发按钮的图标")
    Icon overflowIndicator() ;

    /**
     * 菜单触发按钮CSS类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "菜单触发按钮CSS类名")
    String overflowClassName() default "	";

    /**
     * Popover浮层CSS类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "Popover浮层CSS类名")
    String overflowPopoverClassName() default "	";

    /**
     * 菜单外层CSS类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "菜单外层CSS类名")
    String overflowListClassName() default "	";

    /**
     * 导航横向布局时，开启开启响应式收纳后最大可显示数量，超出此数量的导航将被收纳到下拉菜单中
     *
     * 参考定义: {"type":"number","description":"导航横向布局时，开启开启响应式收纳后最大可显示数量，超出此数量的导航将被收纳到下拉菜单中"}
     *
     * @see 
     */
    @Schema(description = "导航横向布局时，开启开启响应式收纳后最大可显示数量，超出此数量的导航将被收纳到下拉菜单中")
    double maxVisibleCount() default 0;

    /**
     * 包裹导航的外层标签名，可以使用其他标签渲染
     *
     * 参考定义: {"type":"string","description":"包裹导航的外层标签名，可以使用其他标签渲染","default":"ul"}
     *
     * @see 
     */
    @Schema(description = "包裹导航的外层标签名，可以使用其他标签渲染")
    String wrapperComponent() default "	";

    /**
     * 导航项目宽度
     *
     * 参考定义: {"type":"number","description":"导航项目宽度","default":160}
     *
     * @see 
     */
    @Schema(description = "导航项目宽度")
    double itemWidth() default 0;

    /**
     * 导航列表后缀节点
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "导航列表后缀节点")
    String overflowSuffix() default "	";

    /**
     * 自定义样式
     *
     * 参考定义: "#/definitions/React.CSSProperties"
     *
     * @see 
     */
    @Schema(description = "自定义样式")
    React_CSSProperties style() ;

    /**
     * 菜单DOM挂载点
     *
     * 参考定义: {"description":"菜单DOM挂载点"}
     *
     * @see 
     */
    @Schema(description = "菜单DOM挂载点")
    String popOverContainer() default "	";

}
