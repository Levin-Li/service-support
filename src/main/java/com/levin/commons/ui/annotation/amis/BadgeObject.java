package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * BadgeObject
 *
 * Badge 角标。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/badge
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Badge 角标。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/badge")
public @interface BadgeObject {
///////////////////////////////////////////

	//角标类型
	enum Mode{
		text,
		dot,
		ribbon,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//角标位置
	enum Position{
		top_right,
		top_left,
		bottom_right,
		bottom_left,
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
     * className
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "className")
    String className() default "	";

    /**
     * 文本内容
     *
     * 参考定义: {"type":["string","number"],"description":"文本内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "文本内容")
    String text() default "	";

    /**
     * 大小
     *
     * 参考定义: {"type":"number","description":"大小"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "大小")
    double size() default 0;

    /**
     * 角标类型
     *
     * 参考定义: {"type":"string","enum":["text","dot","ribbon"],"description":"角标类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "角标类型")
    Mode mode() ;

    /**
     * 角标位置，相对于position的位置进行偏移
     *
     * 参考定义: {"type":"array","items":{"type":["number","string"]},"minItems":2,"maxItems":2,"description":"角标位置，相对于position的位置进行偏移"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "角标位置，相对于position的位置进行偏移")
    String[] offset() default "	";

    /**
     * 角标位置
     *
     * 参考定义: {"type":"string","enum":["top-right","top-left","bottom-right","bottom-left"],"description":"角标位置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "角标位置")
    Position position() ;

    /**
     * 封顶的数字值
     *
     * 参考定义: {"type":"number","description":"封顶的数字值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "封顶的数字值")
    double overflowCount() default 0;

    /**
     * 动态控制是否显示
     *
     * 参考定义: {"type":"string","description":"动态控制是否显示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "动态控制是否显示")
    String visibleOn() default "	";

    /**
     * 是否显示动画
     *
     * 参考定义: {"type":"boolean","description":"是否显示动画"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示动画")
    boolean animation() default false;

    /**
     * 角标的自定义样式
     *
     * 参考定义: {"type":"object","description":"角标的自定义样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "角标的自定义样式")
    String style() default "	";

    /**
     * 提示类型
     *
     * 参考定义: {"type":"string","description":"提示类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "提示类型")
    String level() default "	";

}
