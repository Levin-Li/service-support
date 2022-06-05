package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * TableControl
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:43
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "TableControl")
public @interface TableControl {
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
     * 是否固定表头
     *
     * 参考定义: {"type":"boolean","description":"是否固定表头"}
     *
     * @see 
     */
    @Schema(description = "是否固定表头")
    boolean affixHeader() default false;

    /**
     * 表格的列信息
     *
     * 参考定义: "#/definitions/TableColumn"
     *
     * @see 
     */
    @Schema(description = "表格的列信息")
    String[] columns() default "	";

    /**
     * 展示列显示开关，自动即：列数量大于或等于5个时自动开启
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string","const":"auto"}],"description":"展示列显示开关，自动即：列数量大于或等于5个时自动开启"}
     *
     * @see 
     */
    @Schema(description = "展示列显示开关，自动即：列数量大于或等于5个时自动开启")
    boolean columnsTogglable() default false;

    /**
     * 是否开启底部展示功能，适合移动端展示
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"expand":{"type":"string","enum":["first","all","none"]},"accordion":{"type":"boolean","description":"是否为手风琴模式"}},"additionalProperties":false}],"description":"是否开启底部展示功能，适合移动端展示"}
     *
     * @see 
     */
    @Schema(description = "是否开启底部展示功能，适合移动端展示")
    boolean footable() default false;

    /**
     * 底部外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "底部外层 CSS 类名")
    String footerClassName() default "	";

    /**
     * 顶部外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "顶部外层 CSS 类名")
    String headerClassName() default "	";

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
     * 是否显示底部
     *
     * 参考定义: {"type":"boolean","description":"是否显示底部"}
     *
     * @see 
     */
    @Schema(description = "是否显示底部")
    boolean showFooter() default false;

    /**
     * 是否显示头部
     *
     * 参考定义: {"type":"boolean","description":"是否显示头部"}
     *
     * @see 
     */
    @Schema(description = "是否显示头部")
    boolean showHeader() default false;

    /**
     * 数据源：绑定当前环境变量
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * @see 
     */
    @Schema(description = "数据源：绑定当前环境变量")
    String source() default "	";

    /**
     * 表格 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "表格 CSS 类名")
    String tableClassName() default "	";

    /**
     * 标题
     *
     * 参考定义: {"type":"string","description":"标题"}
     *
     * @see 
     */
    @Schema(description = "标题")
    String title() default "	";

    /**
     * 工具栏 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "工具栏 CSS 类名")
    String toolbarClassName() default "	";

    /**
     * 合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。
     *
     * 参考定义: {"type":"number","description":"合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。"}
     *
     * @see 
     */
    @Schema(description = "合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。")
    double combineNum() default 0;

    /**
     * 合并单元格配置，配置从第几列开始合并。
     *
     * 参考定义: {"type":"number","description":"合并单元格配置，配置从第几列开始合并。"}
     *
     * @see 
     */
    @Schema(description = "合并单元格配置，配置从第几列开始合并。")
    double combineFromIndex() default 0;

    /**
     * 顶部总结行
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "顶部总结行")
    String[] prefixRow() default "	";

    /**
     * 底部总结行
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "底部总结行")
    String[] affixRow() default "	";

    /**
     * 是否可调整列宽
     *
     * 参考定义: {"type":"boolean","description":"是否可调整列宽"}
     *
     * @see 
     */
    @Schema(description = "是否可调整列宽")
    boolean resizable() default false;

    /**
     * 行样式表表达式
     *
     * 参考定义: {"type":"string","description":"行样式表表达式"}
     *
     * @see 
     */
    @Schema(description = "行样式表表达式")
    String rowClassNameExpr() default "	";

    /**
     * 行角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * @see 
     */
    @Schema(description = "行角标")
    Badge itemBadge() ;

    /**
     * 开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单
     *
     * 参考定义: {"type":"boolean","description":"开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单"}
     *
     * @see 
     */
    @Schema(description = "开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单")
    boolean autoGenerateFilter() default false;

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
     * 表单项类型
     *
     * 参考定义: {"type":"string","const":"input-table","description":"表单项类型"}
     *
     * @see 
     */
    @Schema(description = "表单项类型")
    String type() default "input-table";

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
     * 可新增
     *
     * 参考定义: {"type":"boolean","description":"可新增"}
     *
     * @see 
     */
    @Schema(description = "可新增")
    boolean addable() default false;

    /**
     * 可复制新增
     *
     * 参考定义: {"type":"boolean","description":"可复制新增"}
     *
     * @see 
     */
    @Schema(description = "可复制新增")
    boolean copyable() default false;

    /**
     * 复制按钮文字
     *
     * 参考定义: {"type":"string","description":"复制按钮文字"}
     *
     * @see 
     */
    @Schema(description = "复制按钮文字")
    String copyBtnLabel() default "	";

    /**
     * 复制按钮图标
     *
     * 参考定义: {"type":"string","description":"复制按钮图标"}
     *
     * @see 
     */
    @Schema(description = "复制按钮图标")
    String copyBtnIcon() default "	";

    /**
     * 是否显示复制按钮
     *
     * 参考定义: {"type":"boolean","description":"是否显示复制按钮"}
     *
     * @see 
     */
    @Schema(description = "是否显示复制按钮")
    boolean copyAddBtn() default false;

    /**
     * 是否可以拖拽排序
     *
     * 参考定义: {"type":"boolean","description":"是否可以拖拽排序"}
     *
     * @see 
     */
    @Schema(description = "是否可以拖拽排序")
    boolean draggable() default false;

    /**
     * 新增 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "新增 API")
    String addApi() default "	";

    /**
     * 新增按钮文字
     *
     * 参考定义: {"type":"string","description":"新增按钮文字"}
     *
     * @see 
     */
    @Schema(description = "新增按钮文字")
    String addBtnLabel() default "	";

    /**
     * 新增按钮图标
     *
     * 参考定义: {"type":"string","description":"新增按钮图标"}
     *
     * @see 
     */
    @Schema(description = "新增按钮图标")
    String addBtnIcon() default "	";

    /**
     * 显示新增按钮
     *
     * 参考定义: {"type":"boolean","description":"显示新增按钮"}
     *
     * @see 
     */
    @Schema(description = "显示新增按钮")
    boolean showAddBtn() default false;

    /**
     * 可否删除
     *
     * 参考定义: {"type":"boolean","description":"可否删除"}
     *
     * @see 
     */
    @Schema(description = "可否删除")
    boolean removable() default false;

    /**
     * 删除的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "删除的 API")
    String deleteApi() default "	";

    /**
     * 可否编辑
     *
     * 参考定义: {"type":"boolean","description":"可否编辑"}
     *
     * @see 
     */
    @Schema(description = "可否编辑")
    boolean editable() default false;

    /**
     * 更新按钮名称
     *
     * 参考定义: {"type":"string","description":"更新按钮名称"}
     *
     * @see 
     */
    @Schema(description = "更新按钮名称")
    String editBtnLabel() default "	";

    /**
     * 更新按钮图标
     *
     * 参考定义: {"type":"string","description":"更新按钮图标"}
     *
     * @see 
     */
    @Schema(description = "更新按钮图标")
    String editBtnIcon() default "	";

    /**
     * 确认按钮文字
     *
     * 参考定义: {"type":"string","description":"确认按钮文字"}
     *
     * @see 
     */
    @Schema(description = "确认按钮文字")
    String confirmBtnLabel() default "	";

    /**
     * 确认按钮图标
     *
     * 参考定义: {"type":"string","description":"确认按钮图标"}
     *
     * @see 
     */
    @Schema(description = "确认按钮图标")
    String confirmBtnIcon() default "	";

    /**
     * 取消按钮文字
     *
     * 参考定义: {"type":"string","description":"取消按钮文字"}
     *
     * @see 
     */
    @Schema(description = "取消按钮文字")
    String cancelBtnLabel() default "	";

    /**
     * 取消按钮图标
     *
     * 参考定义: {"type":"string","description":"取消按钮图标"}
     *
     * @see 
     */
    @Schema(description = "取消按钮图标")
    String cancelBtnIcon() default "	";

    /**
     * 删除按钮文字
     *
     * 参考定义: {"type":"string","description":"删除按钮文字"}
     *
     * @see 
     */
    @Schema(description = "删除按钮文字")
    String deleteBtnLabel() default "	";

    /**
     * 删除按钮图标
     *
     * 参考定义: {"type":"string","description":"删除按钮图标"}
     *
     * @see 
     */
    @Schema(description = "删除按钮图标")
    String deleteBtnIcon() default "	";

    /**
     * 更新 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "更新 API")
    String updateApi() default "	";

    /**
     * 初始值，新增的时候
     *
     * 参考定义: {"description":"初始值，新增的时候"}
     *
     * @see 
     */
    @Schema(description = "初始值，新增的时候")
    String scaffold() default "	";

    /**
     * 删除确认文字
     *
     * 参考定义: {"type":"string","description":"删除确认文字"}
     *
     * @see 
     */
    @Schema(description = "删除确认文字")
    String deleteConfirmText() default "	";

    /**
     * 值字段
     *
     * 参考定义: {"type":"string","description":"值字段"}
     *
     * @see 
     */
    @Schema(description = "值字段")
    String valueField() default "	";

    /**
     * 是否为确认的编辑模式。
     *
     * 参考定义: {"type":"boolean","description":"是否为确认的编辑模式。"}
     *
     * @see 
     */
    @Schema(description = "是否为确认的编辑模式。")
    boolean needConfirm() default false;

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
     * 是否显示序号
     *
     * 参考定义: {"type":"boolean","description":"是否显示序号"}
     *
     * @see 
     */
    @Schema(description = "是否显示序号")
    boolean showIndex() default false;

    /**
     * 分页个数，默认不分页
     *
     * 参考定义: {"type":"number","description":"分页个数，默认不分页"}
     *
     * @see 
     */
    @Schema(description = "分页个数，默认不分页")
    double perPage() default 0;

}
