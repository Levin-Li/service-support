package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * TextControl
 *
 * Text 文本输入框。 文档：https://baidu.gitee.io/amis/docs/components/form/text
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Text 文本输入框。 文档：https://baidu.gitee.io/amis/docs/components/form/text")
public @interface TextControl {
///////////////////////////////////////////

	//表单项类型
	enum Type{
		input_text,
		input_email,
		input_url,
		input_password,
		native_date,
		native_time,
		native_number,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

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

	//边框模式，全边框，还是半边框，或者没边框。
	enum BorderMode{
		full,
		half,
		none,
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
     * 表单项类型
     *
     * 参考定义: {"type":"string","enum":["input-text","input-email","input-url","input-password","native-date","native-time","native-number"],"description":"表单项类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "表单项类型")
    Type type() ;

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
     * 选项集合
     *
     * 参考定义: {"anyOf":[{"type":"array","items":{"$ref":"#/definitions/Option"}},{"type":"array","items":{"type":"string"}},{"$ref":"#/definitions/PlainObject"}],"description":"选项集合"}
     *
     * [{"type":"array","items":{"$ref":"#/definitions/Option"}},{"type":"array","items":{"type":"string"}},{"$ref":"#/definitions/PlainObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "选项集合")
    String options() default "	";

    /**
     * 可用来通过 API 拉取 options。
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/SchemaApi"},{"$ref":"#/definitions/SchemaTokenizeableString"}],"description":"可用来通过 API 拉取 options。"}
     *
     * [{"$ref":"#/definitions/SchemaApi"},{"$ref":"#/definitions/SchemaTokenizeableString"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "可用来通过 API 拉取 options。")
    String source() default "	";

    /**
     * 默认选择选项第一个值。
     *
     * 参考定义: {"type":"boolean","description":"默认选择选项第一个值。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "默认选择选项第一个值。")
    boolean selectFirst() default false;

    /**
     * 用表达式来配置 source 接口初始要不要拉取
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "用表达式来配置 source 接口初始要不要拉取")
    String initFetchOn() default "	";

    /**
     * 配置 source 接口初始拉不拉取。
     *
     * 参考定义: {"type":"boolean","description":"配置 source 接口初始拉不拉取。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "配置 source 接口初始拉不拉取。")
    boolean initFetch() default false;

    /**
     * 是否为多选模式
     *
     * 参考定义: {"type":"boolean","description":"是否为多选模式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否为多选模式")
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
     * 是否可清除。
     *
     * 参考定义: {"type":"boolean","description":"是否可清除。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否可清除。")
    boolean clearable() default false;

    /**
     * 点清除按钮时，将表单项设置成当前配置的值。
     *
     * 参考定义: {"type":"string","description":"点清除按钮时，将表单项设置成当前配置的值。","default":""}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "点清除按钮时，将表单项设置成当前配置的值。")
    String resetValue() default "	";

    /**
     * 延时加载的 API，当选项中有 defer: true 的选项时，点开会通过此接口扩充。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "延时加载的 API，当选项中有 defer: true 的选项时，点开会通过此接口扩充。")
    String deferApi() default "	";

    /**
     * 添加时调用的接口
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "添加时调用的接口")
    String addApi() default "	";

    /**
     * 新增时的表单项。
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * 
     *
     * 
     *
     * @see Object
     */
    
    @Schema(description = "新增时的表单项。")
    String[] addControls() default "	";

    /**
     * 是否可以新增
     *
     * 参考定义: {"type":"boolean","description":"是否可以新增"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否可以新增")
    boolean creatable() default false;

    /**
     * 新增文字
     *
     * 参考定义: {"type":"string","description":"新增文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "新增文字")
    String createBtnLabel() default "	";

    /**
     * 是否可以编辑
     *
     * 参考定义: {"type":"boolean","description":"是否可以编辑"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否可以编辑")
    boolean editable() default false;

    /**
     * 编辑时调用的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "编辑时调用的 API")
    String editApi() default "	";

    /**
     * 选项修改的表单项
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * 
     *
     * 
     *
     * @see Object
     */
    
    @Schema(description = "选项修改的表单项")
    String[] editControls() default "	";

    /**
     * 是否可删除
     *
     * 参考定义: {"type":"boolean","description":"是否可删除"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否可删除")
    boolean removable() default false;

    /**
     * 选项删除 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "选项删除 API")
    String deleteApi() default "	";

    /**
     * 选项删除提示文字。
     *
     * 参考定义: {"type":"string","description":"选项删除提示文字。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "选项删除提示文字。")
    String deleteConfirmText() default "	";

    /**
     * 自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/SchemaTokenizeableString"},"description":"自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。")
    String autoFill() default "	";

    /**
     * addOn
     *
     * 参考定义: {"anyOf":[{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"ajax","description":"指定为发送 ajax 的行为。"},"api":{"$ref":"#/definitions/SchemaApi","description":"配置 ajax 发送地址"},"feedback":{"$ref":"#/definitions/FeedbackDialog"},"reload":{"$ref":"#/definitions/SchemaReload"},"redirect":{"type":"string"},"ignoreConfirm":{"type":"boolean"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","api","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"url","description":"指定为打开链接"},"blank":{"type":"boolean","description":"是否新窗口打开"},"url":{"type":"string","description":"打开的目标地址"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","type","url"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"link","description":"指定为打开链接行为，跟 url 不同的时这个行为为单页模式。"},"link":{"type":"string","description":"跳转到哪？支持配置相对路径。"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","link","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"dialog","description":"指定为打开弹窗"},"dialog":{"$ref":"#/definitions/DialogSchemaBase","description":"弹框详情 文档：https://baidu.gitee.io/amis/docs/components/dialog"},"nextCondition":{"$ref":"#/definitions/SchemaExpression","description":"是否有下一个的表达式，正常可以不用配置，如果想要刷掉某些数据可以配置这个。"},"reload":{"$ref":"#/definitions/SchemaReload"},"redirect":{"type":"string"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","dialog","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"drawer","description":"指定为打开弹窗，抽出式弹窗"},"drawer":{"$ref":"#/definitions/DrawerSchemaBase","description":"抽出式弹框详情 文档：https://baidu.gitee.io/amis/docs/components/drawer"},"nextCondition":{"$ref":"#/definitions/SchemaExpression","description":"是否有下一个的表达式，正常可以不用配置，如果想要刷掉某些数据可以配置这个。"},"reload":{"$ref":"#/definitions/SchemaReload"},"redirect":{"type":"string"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","drawer","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"toast","description":"指定为打开弹窗，抽出式弹窗"},"toast":{"$ref":"#/definitions/ToastSchemaBase","description":"轻提示详情 文档：https://baidu.gitee.io/amis/docs/components/toast"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","toast","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"copy","description":"指定为复制内容行为"},"copy":{"$ref":"#/definitions/SchemaTpl","description":"复制啥内容由此配置，支持模板语法。"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","copy","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"$ref":"#/definitions/SchemaReload","description":"指定目标组件。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"reload","description":"指定为刷新目标组件。"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"type":"string","description":"邮件正文"},"actionType":{"type":"string","const":"email","description":"指定为打开邮箱行为"},"to":{"type":"string","description":"收件人邮箱"},"cc":{"type":"string","description":"抄送邮箱"},"bcc":{"type":"string","description":"匿名抄送邮箱"},"subject":{"type":"string","description":"邮件主题"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","to","type"]},{"type":"object","properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","enum":["prev","next","cancel","close","submit","confirm","add","reset","reset-and-submit"]},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string"},"position":{"type":"string","enum":["left","right"]}},"required":["type"]}]}
     *
     * [{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"ajax","description":"指定为发送 ajax 的行为。"},"api":{"$ref":"#/definitions/SchemaApi","description":"配置 ajax 发送地址"},"feedback":{"$ref":"#/definitions/FeedbackDialog"},"reload":{"$ref":"#/definitions/SchemaReload"},"redirect":{"type":"string"},"ignoreConfirm":{"type":"boolean"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","api","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"url","description":"指定为打开链接"},"blank":{"type":"boolean","description":"是否新窗口打开"},"url":{"type":"string","description":"打开的目标地址"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","type","url"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"link","description":"指定为打开链接行为，跟 url 不同的时这个行为为单页模式。"},"link":{"type":"string","description":"跳转到哪？支持配置相对路径。"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","link","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"dialog","description":"指定为打开弹窗"},"dialog":{"$ref":"#/definitions/DialogSchemaBase","description":"弹框详情 文档：https://baidu.gitee.io/amis/docs/components/dialog"},"nextCondition":{"$ref":"#/definitions/SchemaExpression","description":"是否有下一个的表达式，正常可以不用配置，如果想要刷掉某些数据可以配置这个。"},"reload":{"$ref":"#/definitions/SchemaReload"},"redirect":{"type":"string"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","dialog","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"drawer","description":"指定为打开弹窗，抽出式弹窗"},"drawer":{"$ref":"#/definitions/DrawerSchemaBase","description":"抽出式弹框详情 文档：https://baidu.gitee.io/amis/docs/components/drawer"},"nextCondition":{"$ref":"#/definitions/SchemaExpression","description":"是否有下一个的表达式，正常可以不用配置，如果想要刷掉某些数据可以配置这个。"},"reload":{"$ref":"#/definitions/SchemaReload"},"redirect":{"type":"string"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","drawer","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"toast","description":"指定为打开弹窗，抽出式弹窗"},"toast":{"$ref":"#/definitions/ToastSchemaBase","description":"轻提示详情 文档：https://baidu.gitee.io/amis/docs/components/toast"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","toast","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"copy","description":"指定为复制内容行为"},"copy":{"$ref":"#/definitions/SchemaTpl","description":"复制啥内容由此配置，支持模板语法。"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","copy","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"$ref":"#/definitions/SchemaReload","description":"指定目标组件。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","const":"reload","description":"指定为刷新目标组件。"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"type":"string","description":"邮件正文"},"actionType":{"type":"string","const":"email","description":"指定为打开邮箱行为"},"to":{"type":"string","description":"收件人邮箱"},"cc":{"type":"string","description":"抄送邮箱"},"bcc":{"type":"string","description":"匿名抄送邮箱"},"subject":{"type":"string","description":"邮件主题"},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","to","type"]},{"type":"object","properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string","enum":["prev","next","cancel","close","submit","confirm","add","reset","reset-and-submit"]},"position":{"type":"string","enum":["left","right"]}},"required":["actionType","type"]},{"type":"object","additionalProperties":false,"properties":{"type":{"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"},"className":{"type":"string"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"block":{"type":"boolean","description":"是否为块状展示，默认为内联。"},"disabledTip":{"type":"string","description":"禁用时的文案提示。"},"icon":{"type":"string"},"iconClassName":{"$ref":"#/definitions/SchemaClassName","description":"icon 上的css 类名"},"rightIcon":{"$ref":"#/definitions/SchemaIcon","description":"右侧按钮图标， iconfont 的类名"},"rightIconClassName":{"$ref":"#/definitions/SchemaClassName","description":"右侧 icon 上的 css 类名"},"loadingClassName":{"$ref":"#/definitions/SchemaClassName","description":"loading 上的css 类名"},"label":{"type":"string"},"level":{"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"},"primary":{"type":"boolean"},"size":{"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"},"tooltip":{"$ref":"#/definitions/SchemaTooltip"},"tooltipPlacement":{"type":"string","enum":["top","right","bottom","left"]},"confirmText":{"type":"string","description":"提示文字，配置了操作前会要求用户确认。"},"required":{"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"},"activeLevel":{"type":"string","description":"激活状态时的样式"},"activeClassName":{"type":"string","description":"激活状态时的类名"},"close":{"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"},"requireSelected":{"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"},"mergeData":{"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"},"target":{"type":"string","description":"可以指定让谁来触发这个动作。"},"countDown":{"type":"number","description":"点击后的禁止倒计时（秒）"},"countDownTpl":{"type":"string","description":"倒计时文字自定义"},"badge":{"$ref":"#/definitions/BadgeSchema","description":"角标"},"hotKey":{"type":"string","description":"键盘快捷键"},"loadingOn":{"type":"string","description":"是否显示loading效果"},"onClick":{"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"},"body":{"$ref":"#/definitions/SchemaCollection","description":"子内容"},"actionType":{"type":"string"},"position":{"type":"string","enum":["left","right"]}},"required":["type"]}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "addOn")
    String addOn() default "	";

    /**
     * 是否去除首尾空白文本。
     *
     * 参考定义: {"type":"boolean","description":"是否去除首尾空白文本。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否去除首尾空白文本。")
    boolean trimContents() default false;

    /**
     * 自动完成 API，当输入部分文字的时候，会将这些文字通过 ${term} 可以取到，发送给接口。 接口可以返回匹配到的选项，帮助用户输入。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "自动完成 API，当输入部分文字的时候，会将这些文字通过 ${term} 可以取到，发送给接口。 接口可以返回匹配到的选项，帮助用户输入。")
    String autoComplete() default "	";

    /**
     * 边框模式，全边框，还是半边框，或者没边框。
     *
     * 参考定义: {"type":"string","enum":["full","half","none"],"description":"边框模式，全边框，还是半边框，或者没边框。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "边框模式，全边框，还是半边框，或者没边框。")
    BorderMode borderMode() ;

    /**
     * 限制文字个数
     *
     * 参考定义: {"type":"number","description":"限制文字个数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "限制文字个数")
    double maxLength() default 0;

    /**
     * 是否显示计数
     *
     * 参考定义: {"type":"boolean","description":"是否显示计数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否显示计数")
    boolean showCounter() default false;

    /**
     * 前缀
     *
     * 参考定义: {"type":"string","description":"前缀"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "前缀")
    String prefix() default "	";

    /**
     * 后缀
     *
     * 参考定义: {"type":"string","description":"后缀"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "后缀")
    String suffix() default "	";

    /**
     * 自动转换值
     *
     * 参考定义: {"type":"object","properties":{"lowerCase":{"type":"boolean","description":"用户输入的字符自动转小写"},"upperCase":{"type":"boolean","description":"用户输入的字符自动转大写"}},"additionalProperties":false,"description":"自动转换值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "自动转换值")
    String transform() default "	";

}
