package com.levin.commons.service.domain;


import java.lang.annotation.*;
import java.util.Map;
import java.util.function.Function;


@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Repeatable(InjectDomains.class)
/**
 *
 * 注入域
 *
 * 可以字段，参数，方法
 *
 */
public @interface InjectDomain {

    /**
     * 注入域的名字
     *
     * @return
     */
    String name() default "";

    /**
     * 注入域的类型
     *
     * @return String
     */
    String type() default "default";

    /**
     * 默认值
     * <p>
     * 如果 forceInjectExpr 为 false，并且原值为 null，则这个字段生效
     *
     * @return
     */
    String defaultValueExpr() default "";

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
     * 是否需要强制注入
     * <p>
     * 表达式必须返回 boolean 值
     *
     * <p>
     * 如果需要，但没有注入成功将抛出安全异常
     *
     * <p>
     * SPEL 表达式
     * <p>
     * 检查表达式
     *
     * @return
     */
    String forceInjectExpr() default "true";

    /**
     * 备注
     *
     * @return
     */
    String remark() default "注入字段说明：例如注入用户 ID";

}
