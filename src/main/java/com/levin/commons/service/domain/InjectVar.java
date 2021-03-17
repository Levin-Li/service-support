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
     * 表达式类型
     */
    enum ExprType {

        /**
         * Spring el
         */
        Spel,
        /**
         * groovy
         */
        Groovy,

        /**
         * 默认
         */
        Default
    }

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
     * 变量值是否是必须的
     * <p>
     * <p>
     * 如果变量找不到，应该抛出异常
     *
     * @return
     */
    boolean isRequired() default true;


    /**
     * 默认为普通变量名
     *
     * @return
     */
    ExprType exprType() default ExprType.Default;

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "";

}
