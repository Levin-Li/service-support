package com.levin.commons.ui.annotation;

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
     * 名称相同会放在一起
     * <p>
     * 默认为无分分组，也是空分组
     * <p>
     * 一个表单项可以属于多个分组
     */
    String[] groups() default "";

    /**
     * 是否 多选/多值
     */
    boolean multiValue() default false;

    /**
     * 值选项
     * <p>
     * 通常都是只定义一个，为了解决空值，故意设置成数组
     *
     * @return
     */
    Options[] options() default {};

    /**
     * 参数名称
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
     * 数据校验表达式
     * <p>
     * 对填入的数据数据校验表达式
     * 默认不处理
     *
     * @return
     */
    String verifyRules() default "";

    /**
     * 求值过滤器
     * <p>
     * 对填入的数据进行转换处理
     * 默认不处理
     *
     * @return
     */
    String convertFilters() default "";

    /**
     * 是否是可搜索的
     * <p>
     * 只支持文本搜索
     *
     * @return
     */
    boolean searchable() default true;

    /**
     * 占位符，通常用于搜索框
     *
     * @return
     */
    String placeholder() default "";

    /**
     * 参数默认值
     *
     * @return
     */
    String defaultValue() default "";
}
