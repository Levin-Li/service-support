package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ContainerDraggableConfig
 *
 * 容器拖拽配置
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "容器拖拽配置")
public @interface ContainerDraggableConfig {
///////////////////////////////////////////

	//可拖拽的方向, 默认为所有方向, 支持设置为X或Y轴
	enum Axis{
		both,
		x,
		y,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

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
     * 可拖拽的方向, 默认为所有方向, 支持设置为X或Y轴
     *
     * 参考定义: {"type":"string","enum":["both","x","y"],"description":"可拖拽的方向, 默认为所有方向, 支持设置为X或Y轴"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可拖拽的方向, 默认为所有方向, 支持设置为X或Y轴")
    Axis axis() ;

    /**
     * 元素的起始位置
     *
     * 参考定义: {"type":"object","properties":{"x":{"type":"number"},"y":{"type":"number"}},"required":["x","y"],"additionalProperties":false,"description":"元素的起始位置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "元素的起始位置")
    String defaultPosition() default "	";

    /**
     * 拖拽的边界, 可以设置坐标, 也可以设置父级元素的选择器
     *
     * 参考定义: {"anyOf":[{"type":"object","properties":{"left":{"type":"number"},"right":{"type":"number"},"top":{"type":"number"},"bottom":{"type":"number"}},"additionalProperties":false},{"type":"string"}],"description":"拖拽的边界, 可以设置坐标, 也可以设置父级元素的选择器"}
     *
     * [{"type":"object","properties":{"left":{"type":"number"},"right":{"type":"number"},"top":{"type":"number"},"bottom":{"type":"number"}},"additionalProperties":false},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "拖拽的边界, 可以设置坐标, 也可以设置父级元素的选择器")
    String bounds() default "	";

    /**
     * 以网格模式拖拽的步长
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"minItems":2,"maxItems":2,"description":"以网格模式拖拽的步长"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "以网格模式拖拽的步长")
    double[] grid() default 0;

    /**
     * 初始化拖拽的选择器
     *
     * 参考定义: {"type":"string","description":"初始化拖拽的选择器"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "初始化拖拽的选择器")
    String handle() default "	";

    /**
     * 禁止拖拽的选择器
     *
     * 参考定义: {"type":"string","description":"禁止拖拽的选择器"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "禁止拖拽的选择器")
    String cancel() default "	";

    /**
     * 拖拽距离的缩放比, 默认为1
     *
     * 参考定义: {"type":"number","description":"拖拽距离的缩放比, 默认为1"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "拖拽距离的缩放比, 默认为1")
    double scale() default 0;

    /**
     * 默认设置容器内部为'user-select:none', 可以设置true关闭
     *
     * 参考定义: {"type":"boolean","description":"默认设置容器内部为'user-select:none', 可以设置true关闭"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认设置容器内部为'user-select:none', 可以设置true关闭")
    boolean enableUserSelect() default false;

}
