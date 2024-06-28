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

    enum DataType implements EnumDesc {

        DEFAULT,

        DATE_TIME, DATE, TIME,

        DATE_TIME_RANGE, DATE_RANGE, TIME_RANGE,

        TEXT,

        URL,

        EMAIL,

        PHONE,

        INTEGER,

        FLOAT,

        UPLOAD_IMG,

        UPLOAD_FILE,

        //开关
        SWITCH,

        //进度条
        PROGRESS,

        //默认是下拉展示
        OPTIONS_DROP_DOWN,

        //横向展示
        OPTIONS_H,

        //纵向展示
        OPTIONS_V,

        COLOR,

        HTML,

        CODE

    }


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
     * 值选项
     *
     *
     * <p>
     *
     * @return
     */
    Options[] options() default {};

    /**
     * 表单的输入方式
     * <p>
     * 默认自动根据字段类型，如整形，字符串，日期，时间等
     * 对应 Type
     *
     * @return
     */
    DataType dataType() default DataType.DEFAULT;


    /**
     * 表单的输入方式
     *
     * @return
     */
    String inputType() default "";

    /**
     * 编辑器
     * 默认由表单的输入方式决定
     *
     * @return
     */
    String editor() default "";

    /**
     * 表单的展示方式
     *
     * @return
     */
    String viewType() default "";

    /**
     * 参数默认值
     * 自动填入
     *
     * @return
     */
    String defaultValue() default "";

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
     * 提交时对填入的数据进行转换处理
     * 默认不处理
     *
     * @return
     */
    String dataToUiConvertors() default "";

    /**
     * 求值过滤器
     * <p>
     * 提交时对填入的数据进行转换处理
     * 默认不处理
     *
     * @return
     */
    String uiToDataConvertors() default "";

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
     * 表单填写描述
     *
     * @return
     */
    String desc() default "";

}
