package com.levin.commons.annotation;

import java.lang.annotation.*;

/**
 * 生成名称常量
 * <p>
 * 如果标记在注解类上，则是生成方法名常量，如果时在接口上，也是方法名称，如果是标记在普通的类上，则可以生成字段和方法。
 * <p>
 * 字段只是非静态的字段
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface GenNameConstant {

    /**
     * 忽略
     *
     * @return
     */
    boolean ignore() default false;

    /**
     * 生成字段是否用继承的模式
     *
     * @return
     */
    boolean extendsMode() default true;

    /**
     * 生成的类型
     *
     * @return
     */
    Type genType() default Type.AUTO;

    enum Type {
        FIELD, METHOD, BOTH, AUTO
    }

}
