package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ArrayControl
 *
 * InputArray 数组输入框。 combo 的别名。 文档：https://baidu.gitee.io/amis/docs/components/form/array
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "InputArray 数组输入框。 combo 的别名。 文档：https://baidu.gitee.io/amis/docs/components/form/array")
public @interface ArrayControl {
///////////////////////////////////////////

	//子表单的模式。
	enum SubFormMode{
		normal,
		horizontal,
		inline,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//Tabs 的展示模式。
	enum TabsStyle{
		line,
		card,
		radio,
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

////////////////////////////////////////////

    /**
     * 单组表单项初始值。默认为 `{}`
     *
     * 参考定义: {"description":"单组表单项初始值。默认为 `{}`","default":{}}
     *
     * @see 
     */
    @Schema(description = "单组表单项初始值。默认为 `{}`")
    String scaffold() default "	";

    /**
     * 是否含有边框
     *
     * 参考定义: {"type":"boolean","description":"是否含有边框"}
     *
     * @see 
     */
    @Schema(description = "是否含有边框")
    boolean noBorder() default false;

    /**
     * 确认删除时的提示
     *
     * 参考定义: {"type":"string","description":"确认删除时的提示"}
     *
     * @see 
     */
    @Schema(description = "确认删除时的提示")
    String deleteConfirmText() default "	";

    /**
     * 删除时调用的api
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "删除时调用的api")
    String deleteApi() default "	";

    /**
     * 是否可切换条件，配合`conditions`使用
     *
     * 参考定义: {"type":"boolean","description":"是否可切换条件，配合`conditions`使用"}
     *
     * @see 
     */
    @Schema(description = "是否可切换条件，配合`conditions`使用")
    boolean typeSwitchable() default false;

    /**
     * 内部单组表单项的类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "内部单组表单项的类名")
    String formClassName() default "	";

    /**
     * 新增按钮CSS类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "新增按钮CSS类名")
    String addButtonClassName() default "	";

    /**
     * 新增按钮文字
     *
     * 参考定义: {"type":"string","description":"新增按钮文字"}
     *
     * @see 
     */
    @Schema(description = "新增按钮文字")
    String addButtonText() default "	";

    /**
     * 是否可新增
     *
     * 参考定义: {"type":"boolean","description":"是否可新增"}
     *
     * @see 
     */
    @Schema(description = "是否可新增")
    boolean addable() default false;

    /**
     * 是否可拖拽排序
     *
     * 参考定义: {"type":"boolean","description":"是否可拖拽排序"}
     *
     * @see 
     */
    @Schema(description = "是否可拖拽排序")
    boolean draggable() default false;

    /**
     * 可拖拽排序的提示信息。
     *
     * 参考定义: {"type":"string","description":"可拖拽排序的提示信息。"}
     *
     * @see 
     */
    @Schema(description = "可拖拽排序的提示信息。")
    String draggableTip() default "	";

    /**
     * 是否将结果扁平化(去掉name),只有当controls的length为1且multiple为true的时候才有效
     *
     * 参考定义: {"type":"boolean","description":"是否将结果扁平化(去掉name),只有当controls的length为1且multiple为true的时候才有效"}
     *
     * @see 
     */
    @Schema(description = "是否将结果扁平化(去掉name),只有当controls的length为1且multiple为true的时候才有效")
    boolean flat() default false;

    /**
     * 当扁平化开启并且joinValues为true时，用什么分隔符
     *
     * 参考定义: {"type":"string","description":"当扁平化开启并且joinValues为true时，用什么分隔符"}
     *
     * @see 
     */
    @Schema(description = "当扁平化开启并且joinValues为true时，用什么分隔符")
    String delimiter() default "	";

    /**
     * 当扁平化开启的时候，是否用分隔符的形式发送给后端，否则采用array的方式
     *
     * 参考定义: {"type":"boolean","description":"当扁平化开启的时候，是否用分隔符的形式发送给后端，否则采用array的方式"}
     *
     * @see 
     */
    @Schema(description = "当扁平化开启的时候，是否用分隔符的形式发送给后端，否则采用array的方式")
    boolean joinValues() default false;

    /**
     * 限制最大个数
     *
     * 参考定义: {"type":"number","description":"限制最大个数"}
     *
     * @see 
     */
    @Schema(description = "限制最大个数")
    double maxLength() default 0;

    /**
     * 限制最小个数
     *
     * 参考定义: {"type":"number","description":"限制最小个数"}
     *
     * @see 
     */
    @Schema(description = "限制最小个数")
    double minLength() default 0;

    /**
     * 是否多行模式，默认一行展示完
     *
     * 参考定义: {"type":"boolean","description":"是否多行模式，默认一行展示完"}
     *
     * @see 
     */
    @Schema(description = "是否多行模式，默认一行展示完")
    boolean multiLine() default false;

    /**
     * 是否可多选
     *
     * 参考定义: {"type":"boolean","description":"是否可多选"}
     *
     * @see 
     */
    @Schema(description = "是否可多选")
    boolean multiple() default false;

    /**
     * 是否可删除
     *
     * 参考定义: {"type":"boolean","description":"是否可删除"}
     *
     * @see 
     */
    @Schema(description = "是否可删除")
    boolean removable() default false;

    /**
     * 子表单的模式。
     *
     * 参考定义: {"type":"string","enum":["normal","horizontal","inline"],"description":"子表单的模式。"}
     *
     * @see 
     */
    @Schema(description = "子表单的模式。")
    SubFormMode subFormMode() ;

    /**
     * 没有成员时显示。
     *
     * 参考定义: {"type":"string","description":"没有成员时显示。"}
     *
     * @see 
     */
    @Schema(description = "没有成员时显示。")
    String placeholder() default "	";

    /**
     * 是否可以访问父级数据，正常 combo 已经关联到数组成员，是不能访问父级数据的。
     *
     * 参考定义: {"type":"boolean","description":"是否可以访问父级数据，正常 combo 已经关联到数组成员，是不能访问父级数据的。"}
     *
     * @see 
     */
    @Schema(description = "是否可以访问父级数据，正常 combo 已经关联到数组成员，是不能访问父级数据的。")
    boolean canAccessSuperData() default false;

    /**
     * 采用 Tabs 展示方式？
     *
     * 参考定义: {"type":"boolean","description":"采用 Tabs 展示方式？"}
     *
     * @see 
     */
    @Schema(description = "采用 Tabs 展示方式？")
    boolean tabsMode() default false;

    /**
     * Tabs 的展示模式。
     *
     * 参考定义: {"type":"string","enum":["","line","card","radio"],"description":"Tabs 的展示模式。"}
     *
     * @see 
     */
    @Schema(description = "Tabs 的展示模式。")
    TabsStyle tabsStyle() ;

    /**
     * 选项卡标题的生成模板。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "选项卡标题的生成模板。")
    Tpl tabsLabelTpl() ;

    /**
     * 数据比较多，比较卡时，可以试试开启。
     *
     * 参考定义: {"type":"boolean","description":"数据比较多，比较卡时，可以试试开启。"}
     *
     * @see 
     */
    @Schema(description = "数据比较多，比较卡时，可以试试开启。")
    boolean lazyLoad() default false;

    /**
     * 严格模式，为了性能默认不开的。
     *
     * 参考定义: {"type":"boolean","description":"严格模式，为了性能默认不开的。"}
     *
     * @see 
     */
    @Schema(description = "严格模式，为了性能默认不开的。")
    boolean strictMode() default false;

    /**
     * 配置同步字段。只有 `strictMode` 为 `false` 时有效。 如果 Combo 层级比较深，底层的获取外层的数据可能不同步。 但是给 combo 配置这个属性就能同步下来。输入格式：`[os]`
     *
     * 参考定义: {"type":"array","items":{"type":"string"},"description":"配置同步字段。只有 `strictMode` 为 `false` 时有效。 如果 Combo 层级比较深，底层的获取外层的数据可能不同步。 但是给 combo 配置这个属性就能同步下来。输入格式：`[\"os\"]`"}
     *
     * @see 
     */
    @Schema(description = "配置同步字段。只有 `strictMode` 为 `false` 时有效。 如果 Combo 层级比较深，底层的获取外层的数据可能不同步。 但是给 combo 配置这个属性就能同步下来。输入格式：`[os]`")
    String[] syncFields() default "	";

    /**
     * 允许为空，如果子表单项里面配置验证器，且又是单条模式。可以允许用户选择清空（不填）。
     *
     * 参考定义: {"type":"boolean","description":"允许为空，如果子表单项里面配置验证器，且又是单条模式。可以允许用户选择清空（不填）。"}
     *
     * @see 
     */
    @Schema(description = "允许为空，如果子表单项里面配置验证器，且又是单条模式。可以允许用户选择清空（不填）。")
    boolean nullable() default false;

    /**
     * 提示信息
     *
     * 参考定义: {"type":"object","properties":{"validateFailed":{"type":"string","description":"验证错误提示"},"minLengthValidateFailed":{"type":"string","description":"最小值验证错误提示"},"maxLengthValidateFailed":{"type":"string","description":"最大值验证错误提示"}},"additionalProperties":false,"description":"提示信息"}
     *
     * @see 
     */
    @Schema(description = "提示信息")
    String messages() default "	";

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
    String label() default "	";

    /**
     * 配置 label className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "配置 label className")
    String labelClassName() default "	";

    /**
     * 字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c
     *
     * 参考定义: {"type":"string","description":"字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c"}
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
    String hint() default "	";

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
    String description() default "	";

    /**
     * desc
     *
     * 参考定义: {"type":"string"}
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
     * @see 
     */
    @Schema(description = "配置描述上的 className")
    String descriptionClassName() default "	";

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
    String inputClassName() default "	";

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
    String validationErrors() default "	";

    /**
     * validations
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"}}}]}
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
     * @see 
     */
    @Schema(description = "默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。")
    String value() default "	";

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
    String validateApi() default "	";

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
     * 指定为数组输入框类型
     *
     * 参考定义: {"type":"string","const":"input-array","description":"指定为数组输入框类型"}
     *
     * @see 
     */
    @Schema(description = "指定为数组输入框类型")
    String type() default "input-array";

    /**
     * 成员渲染器配置
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "成员渲染器配置")
    String items() default "	";

}
