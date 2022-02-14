package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Mapping
 *
 * Mapping 映射展示控件。 文档：https://baidu.gitee.io/amis/docs/components/mapping
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Mapping 映射展示控件。 文档：https://baidu.gitee.io/amis/docs/components/mapping")
public @interface Mapping {
///////////////////////////////////////////

	//指定为映射展示控件
	enum Type{
		map,
		mapping,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为映射展示控件
     *
     * 参考定义: {"type":"string","enum":["map","mapping"],"description":"指定为映射展示控件"}
     *
     * @see 
     */
    @Schema(description = "指定为映射展示控件")
    Type type() ;

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
     * 关联字段名。
     *
     * 参考定义: {"type":"string","description":"关联字段名。"}
     *
     * @see 
     */
    @Schema(description = "关联字段名。")
    String name() default "	";

    /**
     * 配置映射规则，值可以使用模板语法。当 key 为 * 时表示 else，也就是说值没有映射到任何规则时用 * 对应的值展示。
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/SchemaTpl"},"description":"配置映射规则，值可以使用模板语法。当 key 为 * 时表示 else，也就是说值没有映射到任何规则时用 * 对应的值展示。"}
     *
     * @see 
     */
    @Schema(description = "配置映射规则，值可以使用模板语法。当 key 为 * 时表示 else，也就是说值没有映射到任何规则时用 * 对应的值展示。")
    String map() default "	";

    /**
     * 如果想远程拉取字典，请配置 source 为接口。
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/SchemaApi"},{"$ref":"#/definitions/SchemaTokenizeableString"}],"description":"如果想远程拉取字典，请配置 source 为接口。"}
     *
     * @see 
     */
    @Schema(description = "如果想远程拉取字典，请配置 source 为接口。")
    String source() default "	";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "	";

}
