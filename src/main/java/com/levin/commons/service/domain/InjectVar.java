package com.levin.commons.service.domain;


import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented

/**
 *
 * 注入域
 *
 * 把表达式求解后的值注入到被注解的字段中
 *
 * 样例：
 * class A{
 *     @InjectVar("user.name") //把 user.name 的值 注入到 userName 字段
 *     String userName;
 * }
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
     * 变量名称或是表达式
     * <p>
     * 默认取值字段名称
     *
     * @return
     */
    String value() default "";

    /**
     * 是否强制覆盖原字段值，表达式必须返回 true or false
     * <p>
     * <p>
     * 通常用于判定，如果当前用户是超级管理员，可以不覆盖原值。
     *
     * @return
     */
    String isOverride() default SPEL_PREFIX + "true";

    /**
     * 变量是否是必须的，表达式必须返回 true or false
     * <p>
     * <p>
     * 必须的概念是，变量必须存在，并且不为 null 值
     * <p>
     * 如果不满足条件，注入处理器应该抛出异常
     *
     * @return
     */
    String isRequired() default SPEL_PREFIX + "true";

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "";

}
