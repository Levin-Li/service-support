package com.levin.commons.ui.annotation;

import com.levin.commons.service.domain.EnumDesc;

import java.lang.annotation.*;

/**
 * 表单项
 * <p>
 * 参考 amis
 * https://aisuda.bce.baidu.com/amis/zh-CN/docs/concepts/datascope-and-datachain
 *
 * @author llw
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FormItem {

    /**
     * 分组
     * 名称相同的项展示时会放在一起
     * <p>
     * 默认为无分分组，也是空分组
     * <p>
     * 一个表单项可以属于多个分组
     */
    String[] groups() default "";

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
    String name() default "";

    /**
     * 表单填写描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 值选项
     * <p>
     * 正常只支持一个Options对象
     * <p>
     *
     * @return
     */
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
    String[] verifyRules() default {};

    /**
     * 求值过滤器
     * <p>
     * 提交时对填入的数据进行转换处理
     * 默认不处理
     *
     * @return
     */
    String[] dataToUiConvertors() default {};

    /**
     * 求值过滤器
     * <p>
     * 提交时对填入的数据进行转换处理
     * 默认不处理
     *
     * @return
     */
    String[] uiToDataConvertors() default {};

    /**
     * 表单尾部提示
     * <p>
     * 比如单位：元，秒等
     *
     * <p>
     * 格式为：颜色:文字
     * 比如必填显示红色 * 号    :*
     * 默认颜色为红色
     *
     * @return
     */
    String inputPrompt() default "";

    /**
     * 占位符
     * 同时也是Label
     *
     * @return
     */
    String placeholder() default "";

    /**
     * 样式
     *
     * @return
     */
    String style() default "";


    /**
     * 创建的UI类型
     *
     * @return
     */
    String createUiType() default "";

    /**
     * 修改时的UI类型
     *
     * @return
     */
    String updateUiType() default "";

    /**
     * 查看时的UI类型
     *
     * @return
     */
    String viewUiType() default "";

}
