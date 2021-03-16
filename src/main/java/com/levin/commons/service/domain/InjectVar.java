package com.levin.commons.service.domain;


import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented

/**
 *
 * 注入域
 *
 * 可以注入字段或是参数
 *
 * @author lilw
 *
 */
public @interface InjectVar {

    /**
     * spring el 表达式前缀
     */
    String SPEL_PREFIX = "#!spel:";

    /**
     * groovy 表达式前缀
     */
    String GROOVY_PREFIX = "#!groovy:";

    /**
     * 变量名称
     * 默认为字段名称
     * <p>
     * 也可以是脚本表达式 ，以 InjectVar.SPEL_PREFIX  InjectVar.GROOVY_PREFIX 开头。
     *
     * @return
     */
    String value() default "";

    /**
     * 变量是否是必须的
     * <p>
     * 如果变量找不到，应该抛出异常
     *
     * @return
     */
    boolean isRequired() default true;

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "";

}
