package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Badge
 *
 * Badge 角标。 文档：https://baidu.gitee.io/amis/docs/components/badge
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Badge 角标。 文档：https://baidu.gitee.io/amis/docs/components/badge")
public @interface Badge {
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
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否显示")
    boolean visible() default false;

    /**
     * 动态控制是否显示
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "动态控制是否显示")
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
     *
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "事件动作配置")
    String onEvent() default "	";

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
    
    @Schema(description = "文本内容")
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
    
    @Schema(description = "大小")
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
    
    @Schema(description = "角标类型")
    Mode mode() ;

    /**
     * 角标位置，优先级大于position
     *
     * 参考定义: {"type":"array","items":{"type":["number","string"]},"minItems":2,"maxItems":2,"description":"角标位置，优先级大于position"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "角标位置，优先级大于position")
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
    
    @Schema(description = "角标位置")
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
    
    @Schema(description = "封顶的数字值")
    double overflowCount() default 0;

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
    
    @Schema(description = "是否显示动画")
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
    
    @Schema(description = "角标的自定义样式")
    String style() default "	";

    /**
     * 提示类型
     *
     * 参考定义: {"anyOf":[{"type":"string","const":"info"},{"type":"string","const":"warning"},{"type":"string","const":"success"},{"type":"string","const":"danger"},{"$ref":"#/definitions/SchemaExpression"}],"description":"提示类型"}
     *
     * [{"type":"string","const":"info"},{"type":"string","const":"warning"},{"type":"string","const":"success"},{"type":"string","const":"danger"},{"$ref":"#/definitions/SchemaExpression"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "提示类型")
    String level() default "	";

}
