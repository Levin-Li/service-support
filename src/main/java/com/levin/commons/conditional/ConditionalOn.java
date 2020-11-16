package com.levin.commons.conditional;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Conditional(SimpleOnCondition.class)
public @interface ConditionalOn {

    enum Action {
        OnBean,
        OnMissingBean,
        OnClass,
        OnMissingClass,
        OnProperty,
        //  OnExpr,
    }

    /**
     * 条件类型
     *
     * @return
     */
    Action action();

    /**
     * 是否要求所有的条件都匹配
     * 默认匹配所有条件
     *
     * @return
     */
    boolean requireAllMatch() default true;

    /**
     * 类型
     *
     * @return
     */
    Class<?>[] types() default {};

    /**
     * 类名，或是属性名，或是表达式脚本
     *
     * @return
     */
    String[] value() default {};

}
