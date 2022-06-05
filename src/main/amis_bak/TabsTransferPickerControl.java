package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * TabsTransferPickerControl
 *
 * TabsTransferPicker 穿梭器的弹框形态 文档：https://baidu.gitee.io/amis/docs/components/form/tabs-transfer-picker
 *
 * @author auto gen by service-support at 2022-2-10 12:04:43
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "TabsTransferPicker 穿梭器的弹框形态 文档：https://baidu.gitee.io/amis/docs/components/form/tabs-transfer-picker")
public @interface TabsTransferPickerControl {
///////////////////////////////////////////

	//勾选展示模式
	enum SelectMode{
		table,
		list,
		tree,
		chained,
		associated,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//当 selectMode 为 associated 时用来定义左侧的选择模式
	enum LeftMode{
		tree,
		list,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//当 selectMode 为 associated 时用来定义右侧的选择模式
	enum RightMode{
		table,
		list,
		tree,
		chained,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//搜索结果展示模式
	enum SearchResultMode{
		table,
		list,
		tree,
		chained,
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
     * 是否显示剪头
     *
     * 参考定义: {"type":"boolean","description":"是否显示剪头"}
     *
     * @see 
     */
    @Schema(description = "是否显示剪头")
    boolean showArrow() default false;

    /**
     * 可排序？
     *
     * 参考定义: {"type":"boolean","description":"可排序？"}
     *
     * @see 
     */
    @Schema(description = "可排序？")
    boolean sortable() default false;

    /**
     * 勾选展示模式
     *
     * 参考定义: {"type":"string","enum":["table","list","tree","chained","associated"],"description":"勾选展示模式"}
     *
     * @see 
     */
    @Schema(description = "勾选展示模式")
    SelectMode selectMode() ;

    /**
     * 当 selectMode 为 associated 时用来定义左侧的选项
     *
     * 参考定义: "#/definitions/Option"
     *
     * @see 
     */
    @Schema(description = "当 selectMode 为 associated 时用来定义左侧的选项")
    Option[] leftOptions() ;

    /**
     * 当 selectMode 为 associated 时用来定义左侧的选择模式
     *
     * 参考定义: {"type":"string","enum":["tree","list"],"description":"当 selectMode 为 associated 时用来定义左侧的选择模式"}
     *
     * @see 
     */
    @Schema(description = "当 selectMode 为 associated 时用来定义左侧的选择模式")
    LeftMode leftMode() ;

    /**
     * 当 selectMode 为 associated 时用来定义右侧的选择模式
     *
     * 参考定义: {"type":"string","enum":["table","list","tree","chained"],"description":"当 selectMode 为 associated 时用来定义右侧的选择模式"}
     *
     * @see 
     */
    @Schema(description = "当 selectMode 为 associated 时用来定义右侧的选择模式")
    RightMode rightMode() ;

    /**
     * 搜索结果展示模式
     *
     * 参考定义: {"type":"string","enum":["table","list","tree","chained"],"description":"搜索结果展示模式"}
     *
     * @see 
     */
    @Schema(description = "搜索结果展示模式")
    SearchResultMode searchResultMode() ;

    /**
     * 当 selectMode 为 table 时定义表格列信息。
     *
     * 参考定义: {"type":"array","items":{},"description":"当 selectMode 为 table 时定义表格列信息。"}
     *
     * @see 
     */
    @Schema(description = "当 selectMode 为 table 时定义表格列信息。")
    String[] columns() default "	";

    /**
     * 当 searchResultMode 为 table 时定义表格列信息。
     *
     * 参考定义: {"type":"array","items":{},"description":"当 searchResultMode 为 table 时定义表格列信息。"}
     *
     * @see 
     */
    @Schema(description = "当 searchResultMode 为 table 时定义表格列信息。")
    String[] searchResultColumns() default "	";

    /**
     * 可搜索？
     *
     * 参考定义: {"type":"boolean","description":"可搜索？"}
     *
     * @see 
     */
    @Schema(description = "可搜索？")
    boolean searchable() default false;

    /**
     * 搜索 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "搜索 API")
    String searchApi() default "	";

    /**
     * 左侧的标题文字
     *
     * 参考定义: {"type":"string","description":"左侧的标题文字"}
     *
     * @see 
     */
    @Schema(description = "左侧的标题文字")
    String selectTitle() default "	";

    /**
     * 右侧结果的标题文字
     *
     * 参考定义: {"type":"string","description":"右侧结果的标题文字"}
     *
     * @see 
     */
    @Schema(description = "右侧结果的标题文字")
    String resultTitle() default "	";

    /**
     * 用来丰富选项展示
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "用来丰富选项展示")
    String menuTpl() default "	";

    /**
     * 用来丰富值的展示
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "用来丰富值的展示")
    String valueTpl() default "	";

    /**
     * 选项集合
     *
     * 参考定义: {"anyOf":[{"type":"array","items":{"$ref":"#/definitions/Option"}},{"type":"array","items":{"type":"string"}},{"$ref":"#/definitions/PlainObject"}],"description":"选项集合"}
     *
     * @see 
     */
    @Schema(description = "选项集合")
    String[] options() default "	";

    /**
     * 可用来通过 API 拉取 options。
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/SchemaApi"},{"$ref":"#/definitions/SchemaTokenizeableString"}],"description":"可用来通过 API 拉取 options。"}
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
     * @see 
     */
    @Schema(description = "默认选择选项第一个值。")
    boolean selectFirst() default false;

    /**
     * 用表达式来配置 source 接口初始要不要拉取
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(description = "用表达式来配置 source 接口初始要不要拉取")
    String initFetchOn() default "	";

    /**
     * 配置 source 接口初始拉不拉取。
     *
     * 参考定义: {"type":"boolean","description":"配置 source 接口初始拉不拉取。"}
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
     * @see 
     */
    @Schema(description = "是否为多选模式")
    boolean multiple() default false;

    /**
     * 单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交，否则，整个选项对象都会作为该表单项的值提交。 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来，否则直接将以数组的形式提交值。
     *
     * 参考定义: {"type":"boolean","description":"单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交，否则，整个选项对象都会作为该表单项的值提交。 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来，否则直接将以数组的形式提交值。"}
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
     * @see 
     */
    @Schema(description = "分割符")
    String delimiter() default "	";

    /**
     * 开启后将选中的选项 value 的值封装为数组，作为当前表单项的值。
     *
     * 参考定义: {"type":"boolean","description":"开启后将选中的选项 value 的值封装为数组，作为当前表单项的值。"}
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
     * @see 
     */
    @Schema(description = "是否可清除。")
    boolean clearable() default false;

    /**
     * 点清除按钮时，将表单项设置成当前配置的值。
     *
     * 参考定义: {"type":"string","description":"点清除按钮时，将表单项设置成当前配置的值。","default":""}
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
     * @see 
     */
    @Schema(description = "延时加载的 API，当选项中有 defer: true 的选项时，点开会通过此接口扩充。")
    String deferApi() default "	";

    /**
     * 添加时调用的接口
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "添加时调用的接口")
    String addApi() default "	";

    /**
     * 新增时的表单项。
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "新增时的表单项。")
    String[] addControls() default "	";

    /**
     * 是否可以新增
     *
     * 参考定义: {"type":"boolean","description":"是否可以新增"}
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
     * @see 
     */
    @Schema(description = "新增文字")
    String createBtnLabel() default "	";

    /**
     * 是否可以编辑
     *
     * 参考定义: {"type":"boolean","description":"是否可以编辑"}
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
     * @see 
     */
    @Schema(description = "编辑时调用的 API")
    String editApi() default "	";

    /**
     * 选项修改的表单项
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "选项修改的表单项")
    String[] editControls() default "	";

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
     * 选项删除 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "选项删除 API")
    String deleteApi() default "	";

    /**
     * 选项删除提示文字。
     *
     * 参考定义: {"type":"string","description":"选项删除提示文字。"}
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
     * @see 
     */
    @Schema(description = "自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。")
    String autoFill() default "	";

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
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
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
     * type
     *
     * 参考定义: {"type":"string","const":"tabs-transfer-picker"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "tabs-transfer-picker";

}
