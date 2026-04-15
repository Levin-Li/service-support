package com.levin.commons.ui.annotation;

import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 表单项
 * <p>
 * 参考 amis
 * https://aisuda.bce.baidu.com/amis/zh-CN/docs/concepts/datascope-and-datachain
 *
 * @author llw
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FormItem {

    /**
     * 表单项名称
     * 对应查询对象的字段名称，或是API接口的URL参数名称
     * <p>
     * "name": "${name}",
     * "email": "${email}",
     * <p>
     * 参考文档： https://aisuda.bce.baidu.com/amis/zh-CN/docs/concepts/data-mapping
     *
     * @return
     */
    @Schema(title = "表单项名称", description = "对应查询对象字段名称，或是API接口的URL参数名称")
    String name() default "";

    @Schema(title = "表单项在布局中的占位顺序", description = "默认为-1，表示默认")
    int order() default -1;

    @Schema(title = "表单项栅格占位", description = "默认为-1，表示使用布局默认值")
    int span() default -1;

    /**
     * 占位符
     * 同时也是Label
     *
     * @return
     */
    @Schema(title = "占位符", description = "同时也是Label")
    String placeholder() default "";


    @Schema(title = "表单项尾部提示", description = "格式：颜色:文字, 比如单位：元，秒等")
    String inputPrompt() default "";

    /**
     * 表单填写描述
     *
     * @return
     */
    String desc() default "";

    @Schema(title = "适用场景", description = "适用的业务场景, 场景名称通常是api接口的名称 , 默认无限制, 如query/create/update/view/detail/list/")
    String[] scenes() default {};


    @Schema(title = "适用展示分类", description = "关联@FormLayout, 通常用于表单项过多时, 展示基本部分还是全部, 比如高级搜索,简单搜索, 基本信息, 更多信息等场景, 默认不限制,可在所有分类中展示")
    String[] showCategories() default {};

    /**
     * 分组
     * 名称相同的项展示时会放在一起
     * <p>
     * 默认为无分分组，也是空分组
     * <p>
     * 一个表单项可以属于多个分组
     */
    @Schema(title = "适用展示分组", description = "关联@FormLayout, 组名称相同的表单项展示时会放在一起, 一个表单项可以属于多个分组, 分组关联表单布局")
    String[] groups() default {};

    /**
     * 值选项
     * <p>
     * 正常只支持一个Options对象
     * <p>
     *
     * @return
     */
    @Schema(title = "值选项", description = "正常只支持只取第一个Options对象, 为了注解使用方便, 用数组表示")
    Options[] options() default {};

    /**
     * 参数默认值
     * 自动填入
     *
     * @return
     */
    String defaultValue() default "";

    /**
     * 数据校验表达式
     * <p>
     * 对填入的数据数据校验表达式
     * 默认不处理
     *
     * @return
     */
    @Schema(title = "数据校验表达式", description = "Js表达式或是固定规则名称")
    String[] verifyRules() default {};

    /**
     * 控制可编辑状态
     * <p>
     * 字段显示，但不可编辑
     *
     * @return
     */
    @Schema(title = "禁用条件", description = "前端Js表达式，返回true时字段显示但不可编辑")
    String disabledOn() default "";

    /**
     * 条件必填
     * <p>
     * 优先级高于静态必填推导
     *
     * @return
     */
    @Schema(title = "必填条件", description = "前端Js表达式，返回true时字段为必填，优先级高于静态required推导")
    String requiredOn() default "";

    /**
     * 只读条件
     * <p>
     * 字段可见、可提交、不可改
     *
     * @return
     */
    @Schema(title = "只读条件", description = "前端Js表达式，返回true时字段只读，可见、可提交、不可修改")
    String readOnlyOn() default "";

    /**
     * 隐藏时清空值
     * <p>
     * 用于避免联动后残值污染提交数据
     *
     * @return
     */
    @Schema(title = "隐藏时是否清空值", description = "当字段被隐藏时，是否自动清空字段值，避免联动残值污染提交数据")
    boolean clearOnHidden() default false;

    /**
     * 求值过滤器
     * <p>
     * 提交时对填入的数据进行转换处理
     * 默认不处理
     *
     * @return
     */
    @Schema(title = "求值转换器", description = "Js表达式或是固定转换器名称")
    String[] dataToUiConvertors() default {};

    /**
     * 求值过滤器
     * <p>
     * 提交时对填入的数据进行转换处理
     * 默认不处理
     *
     * @return
     */
    @Schema(title = "求值转换器", description = "Js表达式或是固定转换器名称")
    String[] uiToDataConvertors() default {};


    /**
     * 样式
     *
     * @return
     */
    String style() default "";

}
