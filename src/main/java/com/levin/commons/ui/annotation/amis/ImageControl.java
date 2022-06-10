package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ImageControl
 *
 * Image 图片上传控件 文档：https://baidu.gitee.io/amis/docs/components/form/image
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Image 图片上传控件 文档：https://baidu.gitee.io/amis/docs/components/form/image")
public @interface ImageControl {
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

	//缩路图展示模式
	enum ThumbMode{
		w_full,
		h_full,
		contain,
		cover,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//缩路图展示比率。
	enum ThumbRatio{
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
     * 是否显示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "是否显示表达式")
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
     * 指定为图片上传控件
     *
     * 参考定义: {"type":"string","const":"input-image","description":"指定为图片上传控件"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定为图片上传控件")
    String type() default "input-image";

    /**
     * 表单项大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg","full"],"description":"表单项大小"}
     *
     * 
     *
     * 
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
     * [{"type":"string"},{"type":"boolean","const":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "描述标题")
    String label() default "	";

    /**
     * 描述标题
     *
     * 参考定义: "#/definitions/LabelAlign"
     *
     * 
     *
     * 
     *
     * @see LabelAlign
     */
    
    @Schema(description = "描述标题")
    String labelAlign() default "	";

    /**
     * 配置 label className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置 label className")
    String labelClassName() default "	";

    /**
     * 字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c
     *
     * 参考定义: {"type":"string","description":"字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c")
    String name() default "	";

    /**
     * 显示一个小图标, 鼠标放上去的时候显示提示内容
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * 
     *
     * 
     *
     * @see Remark
     */
    
    @Schema(description = "显示一个小图标, 鼠标放上去的时候显示提示内容")
    Remark remark() ;

    /**
     * 显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * 
     *
     * 
     *
     * @see Remark
     */
    
    @Schema(description = "显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起")
    Remark labelRemark() ;

    /**
     * 输入提示，聚焦的时候显示
     *
     * 参考定义: {"type":"string","description":"输入提示，聚焦的时候显示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "输入提示，聚焦的时候显示")
    String hint() default "	";

    /**
     * 当修改完的时候是否提交表单。
     *
     * 参考定义: {"type":"boolean","description":"当修改完的时候是否提交表单。"}
     *
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否只读")
    boolean readOnly() default false;

    /**
     * 只读条件
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "只读条件")
    String readOnlyOn() default "	";

    /**
     * 不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。
     *
     * 参考定义: {"type":"boolean","description":"不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。"}
     *
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "描述内容，支持 Html 片段。")
    String description() default "	";

    /**
     * desc
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "desc")
    String desc() default "	";

    /**
     * 配置描述上的 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置描述上的 className")
    String descriptionClassName() default "	";

    /**
     * 配置当前表单项展示模式
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置当前表单项展示模式"}
     *
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see FormHorizontal
     */
    
    @Schema(description = "当配置为水平布局的时候，用来配置具体的左右分配。")
    FormHorizontal horizontal() ;

    /**
     * 表单 control 是否为 inline 模式。
     *
     * 参考定义: {"type":"boolean","description":"表单 control 是否为 inline 模式。"}
     *
     * 
     *
     * 
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
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置 input className")
    String inputClassName() default "	";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "占位符")
    String placeholder() default "	";

    /**
     * 是否为必填
     *
     * 参考定义: {"type":"boolean","description":"是否为必填"}
     *
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "验证失败的提示信息")
    String validationErrors() default "	";

    /**
     * validations
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"}}}]}
     *
     * [{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"}}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "validations")
    String validations() default "	";

    /**
     * 默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。
     *
     * 参考定义: {"description":"默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。")
    String value() default "	";

    /**
     * 表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉
     *
     * 参考定义: {"type":"boolean","description":"表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉"}
     *
     * 
     *
     * 
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
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "远端校验表单项接口")
    String validateApi() default "	";

    /**
     * 默认展示图片的链接
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * 
     *
     * 
     *
     * @see UrlPath
     */
    
    @Schema(description = "默认展示图片的链接")
    String src() default "	";

    /**
     * 默认展示图片的类名
     *
     * 参考定义: {"type":"string","description":"默认展示图片的类名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "默认展示图片的类名")
    String imageClassName() default "	";

    /**
     * 配置接收的图片类型\n\n建议直接填写文件后缀 如：.txt,.csv\n\n多个类型用逗号隔开。
     *
     * 参考定义: {"type":"string","description":"配置接收的图片类型\n\n建议直接填写文件后缀 如：.txt,.csv\n\n多个类型用逗号隔开。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "配置接收的图片类型\n\n建议直接填写文件后缀 如：.txt,.csv\n\n多个类型用逗号隔开。")
    String accept() default "	";

    /**
     * 默认都是通过用户选择图片后上传返回图片地址，如果开启此选项，则可以允许用户图片地址。
     *
     * 参考定义: {"type":"boolean","description":"默认都是通过用户选择图片后上传返回图片地址，如果开启此选项，则可以允许用户图片地址。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "默认都是通过用户选择图片后上传返回图片地址，如果开启此选项，则可以允许用户图片地址。")
    boolean allowInput() default false;

    /**
     * 是否自动开始上传
     *
     * 参考定义: {"type":"boolean","description":"是否自动开始上传"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否自动开始上传")
    boolean autoUpload() default false;

    /**
     * 选择图片按钮的 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "选择图片按钮的 CSS 类名")
    String btnClassName() default "	";

    /**
     * 上传按钮的 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "上传按钮的 CSS 类名")
    String btnUploadClassName() default "	";

    /**
     * compress
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "compress")
    boolean compress() default false;

    /**
     * compressOptions
     *
     * 参考定义: {"type":"object","properties":{"maxHeight":{"type":"number"},"maxWidth":{"type":"number"}},"additionalProperties":false}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "compressOptions")
    String compressOptions() default "	";

    /**
     * crop
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"aspectRatio":{"type":"number","description":"默认 `1` 即 `1:1`\n\n留空将不限制"},"guides":{"type":"boolean"},"dragMode":{"type":"string"},"viewMode":{"type":"number"},"rotatable":{"type":"boolean"},"scalable":{"type":"boolean"}},"additionalProperties":false}]}
     *
     * [{"type":"boolean"},{"type":"object","properties":{"aspectRatio":{"type":"number","description":"默认 `1` 即 `1:1`\n\n留空将不限制"},"guides":{"type":"boolean"},"dragMode":{"type":"string"},"viewMode":{"type":"number"},"rotatable":{"type":"boolean"},"scalable":{"type":"boolean"}},"additionalProperties":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "crop")
    String crop() default "	";

    /**
     * 裁剪后的图片类型
     *
     * 参考定义: {"type":"string","description":"裁剪后的图片类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "裁剪后的图片类型")
    String cropFormat() default "	";

    /**
     * 裁剪后的质量
     *
     * 参考定义: {"type":"number","description":"裁剪后的质量"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "裁剪后的质量")
    double cropQuality() default 0;

    /**
     * 是否允许二次裁剪。
     *
     * 参考定义: {"type":"boolean","description":"是否允许二次裁剪。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否允许二次裁剪。")
    boolean reCropable() default false;

    /**
     * 是否隐藏上传按钮
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏上传按钮"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否隐藏上传按钮")
    boolean hideUploadButton() default false;

    /**
     * 限制图片大小，超出不让上传。
     *
     * 参考定义: {"type":"object","properties":{"aspectRatioLabel":{"type":"string","description":"比率不对时的提示文字"},"aspectRatio":{"type":"number","description":"限制比率"},"height":{"type":"number","description":"限制图片高度"},"width":{"type":"number","description":"限制图片宽度"},"maxHeight":{"type":"number","description":"限制图片最大高度"},"maxWidth":{"type":"number","description":"限制图片最大宽度"},"minHeight":{"type":"number","description":"限制图片最小高度"},"minWidth":{"type":"number","description":"限制图片最小宽度"}},"additionalProperties":false,"description":"限制图片大小，超出不让上传。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "限制图片大小，超出不让上传。")
    String limit() default "	";

    /**
     * 最多的个数
     *
     * 参考定义: {"type":"number","description":"最多的个数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "最多的个数")
    double maxLength() default 0;

    /**
     * 默认没有限制，当设置后，文件大小大于此值将不允许上传。
     *
     * 参考定义: {"type":"number","description":"默认没有限制，当设置后，文件大小大于此值将不允许上传。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "默认没有限制，当设置后，文件大小大于此值将不允许上传。")
    double maxSize() default 0;

    /**
     * 默认 `/api/upload` 如果想自己存储，请设置此选项。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "默认 `/api/upload` 如果想自己存储，请设置此选项。")
    String receiver() default "	";

    /**
     * 默认为 false, 开启后，允许用户输入压缩选项。
     *
     * 参考定义: {"type":"boolean","description":"默认为 false, 开启后，允许用户输入压缩选项。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "默认为 false, 开启后，允许用户输入压缩选项。")
    boolean showCompressOptions() default false;

    /**
     * 是否为多选
     *
     * 参考定义: {"type":"boolean","description":"是否为多选"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否为多选")
    boolean multiple() default false;

    /**
     * 单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交，否则，整个选项对象都会作为该表单项的值提交。 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来，否则直接将以数组的形式提交值。
     *
     * 参考定义: {"type":"boolean","description":"单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交，否则，整个选项对象都会作为该表单项的值提交。 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来，否则直接将以数组的形式提交值。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交，否则，整个选项对象都会作为该表单项的值提交。 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来，否则直接将以数组的形式提交值。")
    boolean joinValues() default false;

    /**
     * 分割符
     *
     * 参考定义: {"type":"string","description":"分割符"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "分割符")
    String delimiter() default "	";

    /**
     * 开启后将选中的选项 value 的值封装为数组，作为当前表单项的值。
     *
     * 参考定义: {"type":"boolean","description":"开启后将选中的选项 value 的值封装为数组，作为当前表单项的值。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "开启后将选中的选项 value 的值封装为数组，作为当前表单项的值。")
    boolean extractValue() default false;

    /**
     * 清除时设置的值
     *
     * 参考定义: {"description":"清除时设置的值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "清除时设置的值")
    String resetValue() default "	";

    /**
     * 缩路图展示模式
     *
     * 参考定义: {"type":"string","enum":["w-full","h-full","contain","cover"],"description":"缩路图展示模式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "缩路图展示模式")
    ThumbMode thumbMode() ;

    /**
     * 缩路图展示比率。
     *
     * 参考定义: {"type":"string","enum":["1:1","4:3","16:9"],"description":"缩路图展示比率。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "缩路图展示比率。")
    ThumbRatio thumbRatio() ;

    /**
     * 上传后把其他字段同步到表单内部。
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/SchemaTokenizeableString"},"description":"上传后把其他字段同步到表单内部。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "上传后把其他字段同步到表单内部。")
    String autoFill() default "	";

    /**
     * 初始化时是否把其他字段同步到表单内部。
     *
     * 参考定义: {"type":"boolean","description":"初始化时是否把其他字段同步到表单内部。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "初始化时是否把其他字段同步到表单内部。")
    boolean initAutoFill() default false;

    /**
     * 默认占位图图片地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * 
     *
     * 
     *
     * @see UrlPath
     */
    
    @Schema(description = "默认占位图图片地址")
    String frameImage() default "	";

    /**
     * 是否开启固定尺寸
     *
     * 参考定义: {"type":"boolean","description":"是否开启固定尺寸"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否开启固定尺寸")
    boolean fixedSize() default false;

    /**
     * 固定尺寸的 CSS类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "固定尺寸的 CSS类名")
    String fixedSizeClassName() default "	";

}
