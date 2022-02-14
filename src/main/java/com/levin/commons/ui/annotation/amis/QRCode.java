package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * QRCode
 *
 * 二维码展示控件。 文档：https://baidu.gitee.io/amis/docs/components/qrcode
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "二维码展示控件。 文档：https://baidu.gitee.io/amis/docs/components/qrcode")
public @interface QRCode {
///////////////////////////////////////////

	//null
	enum Type{
		qrcode,
		qr_code,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//二维码复杂级别
	enum Level{
		L,
		M,
		Q,
		H,
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
     * type
     *
     * 参考定义: {"type":"string","enum":["qrcode","qr-code"]}
     *
     * @see 
     */
    @Schema(description = "type")
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
     * css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "css 类名")
    String qrcodeClassName() default "	";

    /**
     * 二维码的宽高大小，默认 128
     *
     * 参考定义: {"type":"number","description":"二维码的宽高大小，默认 128","default":128}
     *
     * @see 
     */
    @Schema(description = "二维码的宽高大小，默认 128")
    double codeSize() default 0;

    /**
     * 背景色
     *
     * 参考定义: {"type":"string","description":"背景色"}
     *
     * @see 
     */
    @Schema(description = "背景色")
    String backgroundColor() default "	";

    /**
     * 前景色
     *
     * 参考定义: {"type":"string","description":"前景色"}
     *
     * @see 
     */
    @Schema(description = "前景色")
    String foregroundColor() default "	";

    /**
     * 二维码复杂级别
     *
     * 参考定义: {"type":"string","enum":["L","M","Q","H"],"description":"二维码复杂级别"}
     *
     * @see 
     */
    @Schema(description = "二维码复杂级别")
    Level level() ;

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
