package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * FileControl
 *
 * File 文件上传控件 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/file
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "File 文件上传控件 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/file")
public @interface FileControl {
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
    
    @Schema(title = "上传后把其他字段同步到表单内部。")
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
    
    @Schema(title = "初始化时是否把其他字段同步到表单内部。")
    boolean initAutoFill() default false;

    /**
     * 指定为文件上传
     *
     * 参考定义: {"type":"string","const":"input-file","description":"指定为文件上传"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为文件上传")
    String type() default "input-file";

    /**
     * 上传文件按钮说明
     *
     * 参考定义: {"type":"string","description":"上传文件按钮说明"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "上传文件按钮说明")
    String btnLabel() default "	";

    /**
     * 默认只支持纯文本，要支持其他类型，请配置此属性。建议直接填写文件后缀 如：.txt,.csv\n\n多个类型用逗号隔开。
     *
     * 参考定义: {"type":"string","description":"默认只支持纯文本，要支持其他类型，请配置此属性。建议直接填写文件后缀 如：.txt,.csv\n\n多个类型用逗号隔开。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认只支持纯文本，要支持其他类型，请配置此属性。建议直接填写文件后缀 如：.txt,.csv\n\n多个类型用逗号隔开。")
    String accept() default "	";

    /**
     * 控制 input 标签的 capture 属性，用于移动端拍照或录像。
     *
     * 参考定义: {"type":"string","description":"控制 input 标签的 capture 属性，用于移动端拍照或录像。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "控制 input 标签的 capture 属性，用于移动端拍照或录像。")
    String capture() default "	";

    /**
     * 如果上传的文件比较小可以设置此选项来简单的把文件 base64 的值给 form 一起提交，目前不支持多选。
     *
     * 参考定义: {"type":"boolean","description":"如果上传的文件比较小可以设置此选项来简单的把文件 base64 的值给 form 一起提交，目前不支持多选。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "如果上传的文件比较小可以设置此选项来简单的把文件 base64 的值给 form 一起提交，目前不支持多选。")
    boolean asBase64() default false;

    /**
     * 如果不希望 File 组件上传，可以配置 `asBlob` 或者 `asBase64`，采用这种方式后，组件不再自己上传了，而是直接把文件数据作为表单项的值，文件内容会在 Form 表单提交的接口里面一起带上。
     *
     * 参考定义: {"type":"boolean","description":"如果不希望 File 组件上传，可以配置 `asBlob` 或者 `asBase64`，采用这种方式后，组件不再自己上传了，而是直接把文件数据作为表单项的值，文件内容会在 Form 表单提交的接口里面一起带上。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "如果不希望 File 组件上传，可以配置 `asBlob` 或者 `asBase64`，采用这种方式后，组件不再自己上传了，而是直接把文件数据作为表单项的值，文件内容会在 Form 表单提交的接口里面一起带上。")
    boolean asBlob() default false;

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
    
    @Schema(title = "是否自动开始上传")
    boolean autoUpload() default false;

    /**
     * 默认 `/api/upload/chunk` 想自己存储时才需要关注。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "默认 `/api/upload/chunk` 想自己存储时才需要关注。")
    String chunkApi() default "	";

    /**
     * 分块大小，默认为 5M.
     *
     * 参考定义: {"type":"number","description":"分块大小，默认为 5M.","default":5242880}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "分块大小，默认为 5M.")
    double chunkSize() default 0;

    /**
     * 分块上传的并发数
     *
     * 参考定义: {"type":"number","description":"分块上传的并发数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "分块上传的并发数")
    double concurrency() default 0;

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
     * 默认显示文件路径的时候会支持直接下载， 可以支持加前缀如：`http://xx.dom/filename=` ， 如果不希望这样，可以把当前配置项设置为 `false`。\n\n1.1.6 版本开始将支持变量 ${xxx} 来自己拼凑个下载地址，并且支持配置成 post.
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "默认显示文件路径的时候会支持直接下载， 可以支持加前缀如：`http://xx.dom/filename=` ， 如果不希望这样，可以把当前配置项设置为 `false`。\n\n1.1.6 版本开始将支持变量 ${xxx} 来自己拼凑个下载地址，并且支持配置成 post.")
    String downloadUrl() default "	";

    /**
     * 模板下载地址
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "模板下载地址")
    String templateUrl() default "	";

    /**
     * 默认 `file`, 如果你不想自己存储，则可以忽略此属性。
     *
     * 参考定义: {"type":"string","description":"默认 `file`, 如果你不想自己存储，则可以忽略此属性。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认 `file`, 如果你不想自己存储，则可以忽略此属性。")
    String fileField() default "	";

    /**
     * 默认 `/api/upload/finishChunkApi` 想自己存储时才需要关注。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "默认 `/api/upload/finishChunkApi` 想自己存储时才需要关注。")
    String finishChunkApi() default "	";

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
    
    @Schema(title = "是否隐藏上传按钮")
    boolean hideUploadButton() default false;

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
    
    @Schema(title = "最多的个数")
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
    
    @Schema(title = "默认没有限制，当设置后，文件大小大于此值将不允许上传。")
    double maxSize() default 0;

    /**
     * 默认 `/api/upload/file` 如果想自己存储，请设置此选项。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "默认 `/api/upload/file` 如果想自己存储，请设置此选项。")
    String receiver() default "	";

    /**
     * 默认 `/api/upload/startChunk` 想自己存储时才需要关注。
     *
     * 参考定义: {"type":"string","description":"默认 `/api/upload/startChunk` 想自己存储时才需要关注。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认 `/api/upload/startChunk` 想自己存储时才需要关注。")
    String startChunkApi() default "	";

    /**
     * 默认为 'auto' amis 所在服务器，限制了文件上传大小不得超出10M，所以 amis 在用户选择大文件的时候，自动会改成分块上传模式。
     *
     * 参考定义: {"anyOf":[{"type":"string","const":"auto"},{"type":"boolean"}],"description":"默认为 'auto' amis 所在服务器，限制了文件上传大小不得超出10M，所以 amis 在用户选择大文件的时候，自动会改成分块上传模式。"}
     *
     * [{"type":"string","const":"auto"},{"type":"boolean"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认为 'auto' amis 所在服务器，限制了文件上传大小不得超出10M，所以 amis 在用户选择大文件的时候，自动会改成分块上传模式。")
    String useChunk() default "	";

    /**
     * 按钮 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "按钮 CSS 类名")
    String btnClassName() default "	";

    /**
     * 上传按钮 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "上传按钮 CSS 类名")
    String btnUploadClassName() default "	";

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
    
    @Schema(title = "是否为多选")
    boolean multiple() default false;

    /**
     * 1. 单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交， 否则，整个选项对象都会作为该表单项的值提交。 2. 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来， 否则直接将以数组的形式提交值。
     *
     * 参考定义: {"type":"boolean","description":"1. 单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交， 否则，整个选项对象都会作为该表单项的值提交。 2. 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来， 否则直接将以数组的形式提交值。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "1. 单选模式：当用户选中某个选项时，选项中的 value 将被作为该表单项的值提交， 否则，整个选项对象都会作为该表单项的值提交。 2. 多选模式：选中的多个选项的 `value` 会通过 `delimiter` 连接起来， 否则直接将以数组的形式提交值。")
    boolean joinValues() default false;

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
    
    @Schema(title = "清除时设置的值")
    String resetValue() default "	";

    /**
     * 接口返回的数据中，哪个用来当做值
     *
     * 参考定义: {"type":"string","description":"接口返回的数据中，哪个用来当做值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "接口返回的数据中，哪个用来当做值")
    String valueField() default "	";

    /**
     * 接口返回的数据中，哪个用来展示文件名
     *
     * 参考定义: {"type":"string","description":"接口返回的数据中，哪个用来展示文件名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "接口返回的数据中，哪个用来展示文件名")
    String nameField() default "	";

    /**
     * 接口返回的数据中哪个用来作为下载地址。
     *
     * 参考定义: {"type":"string","description":"接口返回的数据中哪个用来作为下载地址。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "接口返回的数据中哪个用来作为下载地址。")
    String urlField() default "	";

    /**
     * 按钮状态文案配置。
     *
     * 参考定义: {"type":"object","properties":{"init":{"type":"string"},"pending":{"type":"string"},"uploading":{"type":"string"},"error":{"type":"string"},"uploaded":{"type":"string"},"ready":{"type":"string"}},"required":["init","pending","uploading","error","uploaded","ready"],"additionalProperties":false,"description":"按钮状态文案配置。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "按钮状态文案配置。")
    String stateTextMap() default "	";

    /**
     * 说明文档内容配置
     *
     * 参考定义: {"type":"string","description":"说明文档内容配置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "说明文档内容配置")
    String documentation() default "	";

    /**
     * 说明文档链接配置
     *
     * 参考定义: {"type":"string","description":"说明文档链接配置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "说明文档链接配置")
    String documentLink() default "	";

    /**
     * 是否为拖拽上传
     *
     * 参考定义: {"type":"boolean","description":"是否为拖拽上传"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为拖拽上传")
    boolean drag() default false;

}
