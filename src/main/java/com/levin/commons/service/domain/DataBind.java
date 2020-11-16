package com.levin.commons.service.domain;


import com.levin.commons.enums.DataBindEnumNone;

import java.lang.annotation.*;

/*
 * 数据绑定
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataBind {

    String value() default "";

    /**
     * 数据来源定义
     *
     * @return
     */
    String source() default "";

    /**
     * 标识或代码
     *
     * @return
     */
    String code() default "";

    /**
     * 配置参数
     *
     * @return
     */
    String[] options() default {};

    /**
     * 级联加载最大层级
     *
     * @return
     */
    int level() default 3;

    /**
     * 是否绑定多值
     *
     * @return
     */
    boolean multiple() default false;

    /**
     * 绑定多值时，允许多值数量，-1表示不限制
     *
     * @return
     */
    int multipleMax() default -1;

    /**
     * 多选时的分隔字符
     *
     * @return
     */
    String separator() default "";

    /**
     * 输入类型
     *
     * @return
     */
    InputType inputType() default InputType.SELECT;

    /**
     * 枚举类型
     * @return
     */
    Class<? extends Enum> enumType() default DataBindEnumNone.class;

    enum InputType {
        TEXT, SELECT, RADIO, CHECKBOX, CHECKBOX_GROUP,LOOKUP, CASCADE, ACCORDION/*手风琴*/, TREE, SUGGEST,FILE
    }

}
