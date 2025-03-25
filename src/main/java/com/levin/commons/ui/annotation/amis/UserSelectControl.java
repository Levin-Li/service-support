package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * UserSelectControl
 *
 * UserSelect 移动端人员选择。
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "UserSelect 移动端人员选择。")
public @interface UserSelectControl {
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
   * Any Of
   * 
   *
   *
   */

//////////////////////////////////////////////

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
    
    @Schema(title = "选项集合")
    String options() default "	";

    /**
     * 可用来通过 API 拉取 options。
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"可用来通过 API 拉取 options。"}
     *
     * [{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可用来通过 API 拉取 options。")
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
    
    @Schema(title = "默认选择选项第一个值。")
    boolean selectFirst() default false;

    /**
     * 用表达式来配置 source 接口初始要不要拉取
     *
     * 参考定义: {"type":"string","description":"用表达式来配置 source 接口初始要不要拉取"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "用表达式来配置 source 接口初始要不要拉取")
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
    
    @Schema(title = "配置 source 接口初始拉不拉取。")
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
    
    @Schema(title = "是否为多选模式")
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
    
    @Schema(title = "单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交，否则，整个选项对象都会作为该表单项的值提交。 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来，否则直接将以数组的形式提交值。")
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
    
    @Schema(title = "分割符")
    String delimiter() default "	";

    /**
     * 多选模式，值太多时是否避免折行
     *
     * 参考定义: {"type":"boolean","description":"多选模式，值太多时是否避免折行"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "多选模式，值太多时是否避免折行")
    boolean valuesNoWrap() default false;

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
    
    @Schema(title = "开启后将选中的选项 value 的值封装为数组，作为当前表单项的值。")
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
    
    @Schema(title = "是否可清除。")
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
    
    @Schema(title = "点清除按钮时，将表单项设置成当前配置的值。")
    String resetValue() default "	";

    /**
     * 懒加载字段
     *
     * 参考定义: {"type":"string","description":"懒加载字段"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "懒加载字段")
    String deferField() default "	";

    /**
     * 延时加载的 API，当选项中有 defer: true 的选项时，点开会通过此接口扩充。
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"延时加载的 API，当选项中有 defer: true 的选项时，点开会通过此接口扩充。"}
     *
     * [{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "延时加载的 API，当选项中有 defer: true 的选项时，点开会通过此接口扩充。")
    String deferApi() default "	";

    /**
     * 添加时调用的接口
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"添加时调用的接口"}
     *
     * [{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "添加时调用的接口")
    String addApi() default "	";

    /**
     * 新增时的表单项。
     *
     * 参考定义: "#/definitions/PlainObject"
     *
     * 
     *
     * 
     *
     * @see PlainObject
     */
    
    @Schema(title = "新增时的表单项。")
    String[] addControls() default "	";

    /**
     * 控制新增弹框设置项
     *
     * 参考定义: "#/definitions/PlainObject"
     *
     * 
     *
     * 
     *
     * @see PlainObject
     */
    
    @Schema(title = "控制新增弹框设置项")
    String addDialog() default "	";

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
    
    @Schema(title = "是否可以新增")
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
    
    @Schema(title = "新增文字")
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
    
    @Schema(title = "是否可以编辑")
    boolean editable() default false;

    /**
     * 编辑时调用的 API
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"编辑时调用的 API"}
     *
     * [{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "编辑时调用的 API")
    String editApi() default "	";

    /**
     * 选项修改的表单项
     *
     * 参考定义: "#/definitions/PlainObject"
     *
     * 
     *
     * 
     *
     * @see PlainObject
     */
    
    @Schema(title = "选项修改的表单项")
    String[] editControls() default "	";

    /**
     * 控制编辑弹框设置项
     *
     * 参考定义: "#/definitions/PlainObject"
     *
     * 
     *
     * 
     *
     * @see PlainObject
     */
    
    @Schema(title = "控制编辑弹框设置项")
    String editDialog() default "	";

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
    
    @Schema(title = "是否可删除")
    boolean removable() default false;

    /**
     * 选项删除 API
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"选项删除 API"}
     *
     * [{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "选项删除 API")
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
    
    @Schema(title = "选项删除提示文字。")
    String deleteConfirmText() default "	";

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
     * label自定义宽度，默认单位为px
     *
     * 参考定义: {"type":["number","string"],"description":"label自定义宽度，默认单位为px"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "label自定义宽度，默认单位为px")
    String labelWidth() default "	";

    /**
     * 配置 label className
     *
     * 参考定义: {"type":"string","description":"配置 label className"}
     *
     * 
     *
     * 
     *
     * @see 
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
     * 额外的字段名，当为范围组件时可以用来将另外一个值打平出来
     *
     * 参考定义: {"type":"string","description":"额外的字段名，当为范围组件时可以用来将另外一个值打平出来"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "额外的字段名，当为范围组件时可以用来将另外一个值打平出来")
    String extraName() default "	";

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
    
    @Schema(title = "是否只读")
    boolean readOnly() default false;

    /**
     * 只读条件
     *
     * 参考定义: {"type":"string","description":"只读条件"}
     *
     * 
     *
     * 
     *
     * @see 
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
     * 参考定义: "#/definitions/ClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
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
     * 参考定义: "#/definitions/FormHorizontal"
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
     * 参考定义: "#/definitions/ClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
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
     * 参考定义: {"type":"object","properties":{"isAlpha":{"type":"string"},"isAlphanumeric":{"type":"string"},"isEmail":{"type":"string"},"isFloat":{"type":"string"},"isInt":{"type":"string"},"isJson":{"type":"string"},"isLength":{"type":"string"},"isNumeric":{"type":"string"},"isRequired":{"type":"string"},"isUrl":{"type":"string"},"matchRegexp":{"type":"string"},"matchRegexp2":{"type":"string"},"matchRegexp3":{"type":"string"},"matchRegexp4":{"type":"string"},"matchRegexp5":{"type":"string"},"maxLength":{"type":"string"},"maximum":{"type":"string"},"minLength":{"type":"string"},"minimum":{"type":"string"},"isDateTimeSame":{"type":"string"},"isDateTimeBefore":{"type":"string"},"isDateTimeAfter":{"type":"string"},"isDateTimeSameOrBefore":{"type":"string"},"isDateTimeSameOrAfter":{"type":"string"},"isDateTimeBetween":{"type":"string"},"isTimeSame":{"type":"string"},"isTimeBefore":{"type":"string"},"isTimeAfter":{"type":"string"},"isTimeSameOrBefore":{"type":"string"},"isTimeSameOrAfter":{"type":"string"},"isTimeBetween":{"type":"string"}},"description":"验证失败的提示信息"}
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
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"},"isDateTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"日期处于目标日期范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"},"isTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间，支持指定粒度，默认到毫秒"},"isTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间，支持指定粒度，默认到毫秒"},"isTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"时间处于目标时间范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"}}}]}
     *
     * [{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"},"isDateTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"日期处于目标日期范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"},"isTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间，支持指定粒度，默认到毫秒"},"isTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间，支持指定粒度，默认到毫秒"},"isTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"时间处于目标时间范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"}}}]
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
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}],"description":"远端校验表单项接口"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "远端校验表单项接口")
    String validateApi() default "	";

    /**
     * 自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。
     *
     * 参考定义: {"anyOf":[{"type":"object","additionalProperties":{"type":"string"}},{"type":"object","properties":{"showSuggestion":{"type":"boolean","description":"是否为参照录入模式，参照录入会展示候选值供用户选择，而不是直接填充。"},"api":{"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"自动填充 api"},"silent":{"type":"boolean","description":"是否展示数据格式错误提示，默认为不展示","default":true},"fillMappinng":{"type":"object","description":"填充时的数据映射"},"trigger":{"type":"string","enum":["change","foucs"],"description":"触发条件，默认为 change"},"mode":{"type":"string","enum":["popOver","dialog","drawer"],"description":"弹窗方式，当为参照录入时用可以配置"},"position":{"type":"string","description":"当参照录入为抽屉时可以配置弹出位置"},"size":{"type":"string","description":"当为参照录入时可以配置弹出容器的大小"},"columns":{"type":"array","items":{},"description":"参照录入展示的项"},"filter":{"description":"参照录入时的过滤条件"}},"additionalProperties":false}],"description":"自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。"}
     *
     * [{"type":"object","additionalProperties":{"type":"string"}},{"type":"object","properties":{"showSuggestion":{"type":"boolean","description":"是否为参照录入模式，参照录入会展示候选值供用户选择，而不是直接填充。"},"api":{"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"自动填充 api"},"silent":{"type":"boolean","description":"是否展示数据格式错误提示，默认为不展示","default":true},"fillMappinng":{"type":"object","description":"填充时的数据映射"},"trigger":{"type":"string","enum":["change","foucs"],"description":"触发条件，默认为 change"},"mode":{"type":"string","enum":["popOver","dialog","drawer"],"description":"弹窗方式，当为参照录入时用可以配置"},"position":{"type":"string","description":"当参照录入为抽屉时可以配置弹出位置"},"size":{"type":"string","description":"当为参照录入时可以配置弹出容器的大小"},"columns":{"type":"array","items":{},"description":"参照录入展示的项"},"filter":{"description":"参照录入时的过滤条件"}},"additionalProperties":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。")
    String autoFill() default "	";

    /**
     * initAutoFill
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string","const":"fillIfNotSet"}]}
     *
     * [{"type":"boolean"},{"type":"string","const":"fillIfNotSet"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "initAutoFill")
    String initAutoFill() default "	";

    /**
     * 组件唯一 id，主要用于页面设计器中定位 json 节点
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于页面设计器中定位 json 节点"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "组件唯一 id，主要用于页面设计器中定位 json 节点")
    String $$id() default "	";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
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
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}},"debounce":{"$ref":"#/definitions/debounceConfig"},"track":{"$ref":"#/definitions/trackConfig"}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}
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
     * 是否静态展示
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否静态展示")
    String _static() default "	";

    /**
     * 是否静态展示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "是否静态展示表达式")
    String staticOn() default "	";

    /**
     * 静态展示空值占位
     *
     * 参考定义: {"type":"string","description":"静态展示空值占位"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "静态展示空值占位")
    String staticPlaceholder() default "	";

    /**
     * 静态展示表单项类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "静态展示表单项类名")
    String staticClassName() default "	";

    /**
     * 静态展示表单项Label类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "静态展示表单项Label类名")
    String staticLabelClassName() default "	";

    /**
     * 静态展示表单项Value类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "静态展示表单项Value类名")
    String staticInputClassName() default "	";

    /**
     * 组件样式
     *
     * 参考定义: {"type":"object","description":"组件样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "组件样式")
    String style() default "	";

    /**
     * 编辑器配置，运行时可以忽略
     *
     * 参考定义: {"type":"object","properties":{"behavior":{"type":"string","description":"组件行为、用途，如 create、update、remove"},"displayName":{"type":"string","description":"组件名称，通常是业务名称方便定位"},"mock":{"description":"编辑器假数据，方便展示"}},"description":"编辑器配置，运行时可以忽略"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "编辑器配置，运行时可以忽略")
    String editorSetting() default "	";

    /**
     * 可以组件级别用来关闭移动端样式
     *
     * 参考定义: {"type":"boolean","description":"可以组件级别用来关闭移动端样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可以组件级别用来关闭移动端样式")
    boolean useMobileUI() default false;

    /**
     * testIdBuilder
     *
     * 参考定义: "#/definitions/TestIdBuilder"
     *
     * 
     *
     * 
     *
     * @see TestIdBuilder
     */
    
    @Schema(title = "testIdBuilder")
    TestIdBuilder testIdBuilder() ;

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
     * 表单项类型
     *
     * 参考定义: {"type":"string","const":"users-select","description":"表单项类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单项类型")
    String type() default "users-select";

}
