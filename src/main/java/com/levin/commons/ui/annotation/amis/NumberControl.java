package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * NumberControl
 *
 * 数字输入框 文档：https://baidu.gitee.io/amis/docs/components/form/number
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "数字输入框 文档：https://baidu.gitee.io/amis/docs/components/form/number")
public @interface NumberControl {
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

	//边框模式，全边框，还是半边框，或者没边框。
	enum BorderMode{
		full,
		half,
		none,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//输入框为基础输入框还是加强输入框
	enum DisplayMode{
		base,
		enhance,
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

    @Schema(title = "容器 css 类名")
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

    @Schema(title = "是否禁用")
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

    @Schema(title = "是否禁用表达式")
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

    @Schema(title = "是否隐藏")
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

    @Schema(title = "是否隐藏表达式")
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

    @Schema(title = "是否显示")
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

    @Schema(title = "是否显示表达式")
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

    @Schema(title = "组件唯一 id，主要用于日志采集")
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

    @Schema(title = "事件动作配置")
    String onEvent() default "	";

    /**
     * 表单项类型
     *
     * 参考定义: {"type":"string","const":"input-number","description":"表单项类型"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "表单项类型")
    String type() default "input-number";

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

    @Schema(title = "表单项大小")
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

    @Schema(title = "描述标题")
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

    @Schema(title = "描述标题")
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

    @Schema(title = "配置 label className")
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

    @Schema(title = "字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c")
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

    @Schema(title = "显示一个小图标, 鼠标放上去的时候显示提示内容")
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

    @Schema(title = "显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起")
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

    @Schema(title = "输入提示，聚焦的时候显示")
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

    @Schema(title = "当修改完的时候是否提交表单。")
    boolean submitOnChange() default false;

    /**
     * 只读
     *
     * 参考定义: {"type":"boolean","description":"只读"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "只读")
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

    @Schema(title = "只读条件")
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

    @Schema(title = "不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。")
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

    @Schema(title = "描述内容，支持 Html 片段。")
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

    @Schema(title = "desc")
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

    @Schema(title = "配置描述上的 className")
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

    @Schema(title = "配置当前表单项展示模式")
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

    @Schema(title = "当配置为水平布局的时候，用来配置具体的左右分配。")
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

    @Schema(title = "表单 control 是否为 inline 模式。")
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

    @Schema(title = "配置 input className")
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

    @Schema(title = "占位符")
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

    @Schema(title = "是否为必填")
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

    @Schema(title = "验证失败的提示信息")
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

    @Schema(title = "validations")
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

    @Schema(title = "默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。")
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

    @Schema(title = "表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉")
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

    @Schema(title = "远端校验表单项接口")
    String validateApi() default "	";

    /**
     * 最大值
     *
     * 参考定义: {"type":"number","description":"最大值"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "最大值")
    double max() default 0;

    /**
     * 最小值
     *
     * 参考定义: {"type":"number","description":"最小值"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "最小值")
    double min() default 0;

    /**
     * 步长
     *
     * 参考定义: {"type":"number","description":"步长"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "步长")
    double step() default 0;

    /**
     * 精度
     *
     * 参考定义: {"type":"number","description":"精度"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "精度")
    double precision() default 0;

    /**
     * 默认当然是
     *
     * 参考定义: {"type":"boolean","description":"默认当然是"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "默认当然是")
    boolean showSteps() default false;

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

    @Schema(title = "边框模式，全边框，还是半边框，或者没边框。")
    BorderMode borderMode() ;

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

    @Schema(title = "前缀")
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

    @Schema(title = "后缀")
    String suffix() default "	";

    /**
     * 单位列表
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"array","items":{"$ref":"#/definitions/Option"}},{"type":"array","items":{"type":"string"}},{"$ref":"#/definitions/PlainObject"}],"description":"单位列表"}
     *
     * [{"type":"string"},{"type":"array","items":{"$ref":"#/definitions/Option"}},{"type":"array","items":{"type":"string"}},{"$ref":"#/definitions/PlainObject"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "单位列表")
    String unitOptions() default "	";

    /**
     * 是否千分分隔
     *
     * 参考定义: {"type":"boolean","description":"是否千分分隔"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否千分分隔")
    boolean kilobitSeparator() default false;

    /**
     * 是否启用键盘行为
     *
     * 参考定义: {"type":"boolean","description":"是否启用键盘行为"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否启用键盘行为")
    boolean keyboard() default false;

    /**
     * 输入框为基础输入框还是加强输入框
     *
     * 参考定义: {"type":"string","enum":["base","enhance"],"description":"输入框为基础输入框还是加强输入框"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "输入框为基础输入框还是加强输入框")
    DisplayMode displayMode() ;

}
