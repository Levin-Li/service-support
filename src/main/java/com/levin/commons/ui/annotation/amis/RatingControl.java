package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * RatingControl
 *
 * Rating 文档：https://baidu.gitee.io/amis/docs/components/form/rating
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Rating 文档：https://baidu.gitee.io/amis/docs/components/form/rating")
public @interface RatingControl {
///////////////////////////////////////////

	//表单项大小
	enum Size{
		xs,
		sm,
		md,
		lg,
		full,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//配置当前表单项展示模式
	enum Mode{
		normal,
		inline,
		horizontal,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "容器 css 类名")
    String className() default "";

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
    String disabledOn() default "";

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
    String hiddenOn() default "";

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
    String visibleOn() default "";

    /**
     * 表单项类型
     *
     * 参考定义: {"type":"string","const":"input-rating","description":"表单项类型"}
     *
     * @see 
     */
    @Schema(description = "表单项类型")
    String type() default "input-rating";

    /**
     * 表单项大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg","full"],"description":"表单项大小"}
     *
     * @see 
     */
    @Schema(description = "表单项大小")
    Size size() ;

    /**
     * 描述标题
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"boolean","const":false}],"description":"描述标题"}
     *
     * @see 
     */
    @Schema(description = "描述标题")
    String label() default "";

    /**
     * 配置 label className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "配置 label className")
    String labelClassName() default "";

    /**
     * 字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c
     *
     * 参考定义: {"type":"string","description":"字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c"}
     *
     * @see 
     */
    @Schema(description = "字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c")
    String name() default "";

    /**
     * 显示一个小图标, 鼠标放上去的时候显示提示内容
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * @see 
     */
    @Schema(description = "显示一个小图标, 鼠标放上去的时候显示提示内容")
    Remark remark() ;

    /**
     * 显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * @see 
     */
    @Schema(description = "显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起")
    Remark labelRemark() ;

    /**
     * 输入提示，聚焦的时候显示
     *
     * 参考定义: {"type":"string","description":"输入提示，聚焦的时候显示"}
     *
     * @see 
     */
    @Schema(description = "输入提示，聚焦的时候显示")
    String hint() default "";

    /**
     * 当修改完的时候是否提交表单。
     *
     * 参考定义: {"type":"boolean","description":"当修改完的时候是否提交表单。"}
     *
     * @see 
     */
    @Schema(description = "当修改完的时候是否提交表单。")
    boolean submitOnChange() default false;

    /**
     * 是否只读
     *
     * 参考定义: {"type":"boolean","description":"是否只读"}
     *
     * @see 
     */
    @Schema(description = "是否只读")
    boolean readOnly() default false;

    /**
     * 不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。
     *
     * 参考定义: {"type":"boolean","description":"不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。"}
     *
     * @see 
     */
    @Schema(description = "不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。")
    boolean validateOnChange() default false;

    /**
     * 描述内容，支持 Html 片段。
     *
     * 参考定义: {"type":"string","description":"描述内容，支持 Html 片段。"}
     *
     * @see 
     */
    @Schema(description = "描述内容，支持 Html 片段。")
    String description() default "";

    /**
     * desc
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "desc")
    String desc() default "";

    /**
     * 配置描述上的 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "配置描述上的 className")
    String descriptionClassName() default "";

    /**
     * 配置当前表单项展示模式
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置当前表单项展示模式"}
     *
     * @see 
     */
    @Schema(description = "配置当前表单项展示模式")
    Mode mode() ;

    /**
     * 当配置为水平布局的时候，用来配置具体的左右分配。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see 
     */
    @Schema(description = "当配置为水平布局的时候，用来配置具体的左右分配。")
    FormHorizontal horizontal() ;

    /**
     * 表单 control 是否为 inline 模式。
     *
     * 参考定义: {"type":"boolean","description":"表单 control 是否为 inline 模式。"}
     *
     * @see 
     */
    @Schema(description = "表单 control 是否为 inline 模式。")
    boolean inline() default false;

    /**
     * 配置 input className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "配置 input className")
    String inputClassName() default "";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "";

    /**
     * 是否为必填
     *
     * 参考定义: {"type":"boolean","description":"是否为必填"}
     *
     * @see 
     */
    @Schema(description = "是否为必填")
    boolean required() default false;

    /**
     * 验证失败的提示信息
     *
     * 参考定义: {"type":"object","properties":{"isAlpha":{"type":"string"},"isAlphanumeric":{"type":"string"},"isEmail":{"type":"string"},"isFloat":{"type":"string"},"isInt":{"type":"string"},"isJson":{"type":"string"},"isLength":{"type":"string"},"isNumeric":{"type":"string"},"isRequired":{"type":"string"},"isUrl":{"type":"string"},"matchRegexp":{"type":"string"},"matchRegexp2":{"type":"string"},"matchRegexp3":{"type":"string"},"matchRegexp4":{"type":"string"},"matchRegexp5":{"type":"string"},"maxLength":{"type":"string"},"maximum":{"type":"string"},"minLength":{"type":"string"},"minimum":{"type":"string"}},"description":"验证失败的提示信息"}
     *
     * @see 
     */
    @Schema(description = "验证失败的提示信息")
    String validationErrors() default "";

    /**
     * validations
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"}}}]}
     *
     * @see 
     */
    @Schema(description = "validations")
    String validations() default "";

    /**
     * 默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。
     *
     * 参考定义: {"description":"默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。"}
     *
     * @see 
     */
    @Schema(description = "默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。")
    String value() default "";

    /**
     * 表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉
     *
     * 参考定义: {"type":"boolean","description":"表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉"}
     *
     * @see 
     */
    @Schema(description = "表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉")
    boolean clearValueOnHidden() default false;

    /**
     * 远端校验表单项接口
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "远端校验表单项接口")
    String validateApi() default "";

    /**
     * 分数
     *
     * 参考定义: {"type":"number","description":"分数"}
     *
     * @see 
     */
    @Schema(description = "分数")
    double count() default 0;

    /**
     * 允许半颗星
     *
     * 参考定义: {"type":"boolean","description":"允许半颗星"}
     *
     * @see 
     */
    @Schema(description = "允许半颗星")
    boolean half() default false;

    /**
     * 是否允许再次点击后清除
     *
     * 参考定义: {"type":"boolean","description":"是否允许再次点击后清除"}
     *
     * @see 
     */
    @Schema(description = "是否允许再次点击后清除")
    boolean allowClear() default false;

    /**
     * 是否只读
     *
     * 参考定义: {"type":"boolean","description":"是否只读"}
     *
     * @see 
     */
    @Schema(description = "是否只读")
    boolean readonly() default false;

    /**
     * 星星被选中的颜色
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object","additionalProperties":{"type":"string"}}],"description":"星星被选中的颜色"}
     *
     * @see 
     */
    @Schema(description = "星星被选中的颜色")
    String colors() default "";

    /**
     * 未被选中的星星的颜色
     *
     * 参考定义: {"type":"string","description":"未被选中的星星的颜色"}
     *
     * @see 
     */
    @Schema(description = "未被选中的星星的颜色")
    String inactiveColor() default "";

    /**
     * 星星被选中时的提示文字
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"string"},"description":"星星被选中时的提示文字"}
     *
     * @see 
     */
    @Schema(description = "星星被选中时的提示文字")
    String texts() default "";

    /**
     * 文字的位置
     *
     * 参考定义: "#/definitions/textPositionType"
     *
     * @see 
     */
    @Schema(description = "文字的位置")
    TextPositionType textPosition() ;

    /**
     * _char
     *
     * 参考定义: {"type":"string","description":"自定义字符"}
     *
     * @see 
     */
    @Schema(description = "_char")
    String _char() default "";

    /**
     * 自定义字符类名
     *
     * 参考定义: {"type":"string","description":"自定义字符类名"}
     *
     * @see 
     */
    @Schema(description = "自定义字符类名")
    String charClassName() default "";

    /**
     * 自定义文字类名
     *
     * 参考定义: {"type":"string","description":"自定义文字类名"}
     *
     * @see 
     */
    @Schema(description = "自定义文字类名")
    String textClassName() default "";

}
