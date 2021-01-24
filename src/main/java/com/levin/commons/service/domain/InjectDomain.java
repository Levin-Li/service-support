package com.levin.commons.service.domain;


import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Repeatable(InjectVar.class)
/**
 *
 * 字段注入
 *
 * 基于动态脚本实现注入逻辑
 *
 * 可以字段，参数，方法
 *
 */
@Deprecated
public @interface InjectDomain {

    /**
     * 是否需要覆盖原字段值
     *
     *
     * <p>
     * 表达式必须返回 boolean 值
     *
     * <p>
     * 如果需要强制注入，但没有注入成功将抛出安全异常
     *
     * <p>
     * SPEL or Groovy表达式
     * <p>
     *
     * @return
     */
    String overrideExpr() default "true";

    /**
     * 注入值的求值表达式
     * <p>
     * 建议 SPEL or Groovy
     * <p>
     *
     * <p>
     * 举例，如登录用户ID，用户名，IP地址，Token，用户所在的部门等
     *
     * @return
     */
    String injectValueExpr() default "";

    /**
     * 默认值
     * <p>
     * SPEL or Groovy
     *
     * <p>
     * 如果 forceInjectExpr 为 false，并且原值为 null，则这个字段生效
     *
     * @return
     */
    String defaultValueExpr() default "";

    /**
     * 备注
     *
     * @return
     */
    String remark() default "";

}
