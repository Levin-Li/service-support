package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * SubFormControl
 *
 * SubForm 子表单 文档：https://baidu.gitee.io/amis/docs/components/form/subform
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "SubForm 子表单 文档：https://baidu.gitee.io/amis/docs/components/form/subform")
public @interface SubFormControl {
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
     * 指定为 SubForm 子表单
     *
     * 参考定义: {"type":"string","const":"input-sub-form","description":"指定为 SubForm 子表单"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定为 SubForm 子表单")
    String type() default "input-sub-form";

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
     * 是否多选
     *
     * 参考定义: {"type":"boolean","description":"是否多选"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否多选")
    boolean multiple() default false;

    /**
     * 是否可拖拽排序
     *
     * 参考定义: {"type":"boolean","description":"是否可拖拽排序"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否可拖拽排序")
    boolean draggable() default false;

    /**
     * 拖拽提示信息
     *
     * 参考定义: {"type":"string","description":"拖拽提示信息"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "拖拽提示信息")
    String draggableTip() default "	";

    /**
     * 是否可新增
     *
     * 参考定义: {"type":"boolean","description":"是否可新增"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否可新增")
    boolean addable() default false;

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
     * 最少个数
     *
     * 参考定义: {"type":"number","description":"最少个数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "最少个数")
    double minLength() default 0;

    /**
     * 最多个数
     *
     * 参考定义: {"type":"number","description":"最多个数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "最多个数")
    double maxLength() default 0;

    /**
     * 当值中存在这个字段，则按钮名称将使用此字段的值来展示。
     *
     * 参考定义: {"type":"string","description":"当值中存在这个字段，则按钮名称将使用此字段的值来展示。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "当值中存在这个字段，则按钮名称将使用此字段的值来展示。")
    String labelField() default "	";

    /**
     * 按钮默认名称
     *
     * 参考定义: {"type":"string","description":"按钮默认名称"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "按钮默认名称")
    String btnLabel() default "	";

    /**
     * 新增按钮文字
     *
     * 参考定义: {"type":"string","description":"新增按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "新增按钮文字")
    String addButtonText() default "	";

    /**
     * 新增按钮 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "新增按钮 CSS 类名")
    String addButtonClassName() default "	";

    /**
     * 值元素的类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "值元素的类名")
    String itemClassName() default "	";

    /**
     * 值列表元素的类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "值列表元素的类名")
    String itemsClassName() default "	";

    /**
     * 是否在左下角显示报错信息
     *
     * 参考定义: {"type":"boolean","description":"是否在左下角显示报错信息"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否在左下角显示报错信息")
    boolean showErrorMsg() default false;

    /**
     * 子表单详情
     *
     * 参考定义: {"type":"object","properties":{"title":{"type":"string","description":"表单标题"},"actions":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"按钮集合，会固定在底部显示。"},"body":{"$ref":"#/definitions/SchemaCollection","description":"表单项集合"},"tabs":{},"fieldSet":{},"data":{"$ref":"#/definitions/SchemaDefaultData"},"debug":{"type":"boolean","description":"是否开启调试，开启后会在顶部实时显示表单项数据。"},"initApi":{"$ref":"#/definitions/SchemaApi","description":"用来初始化表单数据"},"initAsyncApi":{"$ref":"#/definitions/SchemaApi","description":"Form 用来获取初始数据的 api,与initApi不同的是，会一直轮询请求该接口，直到返回 finished 属性为 true 才 结束。"},"initFinishedField":{"type":"string","description":"设置了initAsyncApi后，默认会从返回数据的data.finished来判断是否完成，也可以设置成其他的xxx，就会从data.xxx中获取"},"initCheckInterval":{"type":"number","description":"设置了initAsyncApi以后，默认拉取的时间间隔"},"initFetch":{"type":"boolean","description":"是否初始加载"},"initFetchOn":{"$ref":"#/definitions/SchemaExpression","description":"建议改成 api 的 sendOn 属性。"},"interval":{"type":"number","description":"设置后将轮询调用 initApi"},"silentPolling":{"type":"boolean","description":"是否静默拉取"},"stopAutoRefreshWhen":{"type":"string","description":"配置停止轮询的条件"},"persistData":{"type":"string","description":"是否开启本地缓存"},"clearPersistDataAfterSubmit":{"type":"boolean","description":"提交成功后清空本地缓存"},"api":{"$ref":"#/definitions/SchemaApi","description":"Form 用来保存数据的 api。\n\n详情：https://baidu.gitee.io/amis/docs/components/form/index#%E8%A1%A8%E5%8D%95%E6%8F%90%E4%BA%A4"},"feedback":{"$ref":"#/definitions/DialogSchemaBase","description":"Form 也可以配置 feedback。"},"asyncApi":{"$ref":"#/definitions/SchemaApi","description":"设置此属性后，表单提交发送保存接口后，还会继续轮询请求该接口，直到返回 finished 属性为 true 才 结束。"},"checkInterval":{"type":"number","description":"轮询请求的时间间隔，默认为 3秒。设置 asyncApi 才有效"},"finishedField":{"type":"string","description":"如果决定结束的字段名不是 `finished` 请设置此属性，比如 `is_success`"},"resetAfterSubmit":{"type":"boolean","description":"提交完后重置表单"},"clearAfterSubmit":{"type":"boolean","description":"提交后清空表单"},"mode":{"type":"string","enum":["normal","inline","horizontal"],"description":"配置表单项默认的展示方式。"},"columnCount":{"type":"number","description":"表单项显示为几列"},"horizontal":{"$ref":"#/definitions/FormSchemaHorizontal","description":"如果是水平排版，这个属性可以细化水平排版的左右宽度占比。"},"autoFocus":{"type":"boolean","description":"是否自动将第一个表单元素聚焦。"},"messages":{"type":"object","additionalProperties":false,"properties":{"fetchFailed":{"type":"string","description":"获取失败时的提示"},"fetchSuccess":{"type":"string","description":"获取成功的提示，默认为空。"},"saveFailed":{"type":"string","description":"保存失败时的提示。"},"saveSuccess":{"type":"string","description":"保存成功时的提示。"},"validateFailed":{"type":"string","description":"表单验证失败时的提示"}},"description":"消息文案配置，记住这个优先级是最低的，如果你的接口返回了 msg，接口返回的优先。"},"name":{"$ref":"#/definitions/SchemaName"},"panelClassName":{"$ref":"#/definitions/SchemaClassName","description":"配置容器 panel className"},"primaryField":{"type":"string","description":"设置主键 id, 当设置后，检测表单是否完成时（asyncApi），只会携带此数据。"},"redirect":{"$ref":"#/definitions/SchemaRedirect"},"reload":{"$ref":"#/definitions/SchemaReload"},"submitOnChange":{"type":"boolean","description":"修改的时候是否直接提交表单。"},"submitOnInit":{"type":"boolean","description":"表单初始先提交一次，联动的时候有用"},"submitText":{"type":"string","description":"默认的提交按钮名称，如果设置成空，则可以把默认按钮去掉。"},"target":{"type":"string","description":"默认表单提交自己会通过发送 api 保存数据，但是也可以设定另外一个 form 的 name 值，或者另外一个 `CRUD` 模型的 name 值。 如果 target 目标是一个 `Form` ，则目标 `Form` 会重新触发 `initApi` 和 `schemaApi`，api 可以拿到当前 form 数据。如果目标是一个 `CRUD` 模型，则目标模型会重新触发搜索，参数为当前 Form 数据。"},"wrapWithPanel":{"type":"boolean","description":"是否用 panel 包裹起来"},"affixFooter":{"type":"boolean","description":"是否固定底下的按钮在底部。"},"promptPageLeave":{"type":"boolean","description":"页面离开提示，为了防止页面不小心跳转而导致表单没有保存。"},"promptPageLeaveMessage":{"type":"string","description":"具体的提示信息，选填。"},"rules":{"type":"array","items":{"type":"object","properties":{"rule":{"type":"string"},"message":{"type":"string"},"name":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}]}},"required":["rule","message"],"additionalProperties":false},"description":"组合校验规则，选填"},"preventEnterSubmit":{"type":"boolean","description":"禁用回车提交"},"labelAlign":{"$ref":"#/definitions/LabelAlign","description":"表单label的对齐方式"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"组件唯一 id，主要用于日志采集"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}},"additionalProperties":false,"description":"子表单详情"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "子表单详情")
    String form() default "	";

    /**
     * scaffold
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "scaffold")
    String scaffold() default "	";

}
