package com.levin.commons.service.domain;


import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

/**
 *
 * @author lilw
 *
 */
public @interface RefInject {

    /**
     * 关联的实体类
     *
     * @return
     */
    Class<?> refEntity();

    /**
     * 关联实体Id的字段名
     *
     * @return
     */
    String refEntityIdFieldName();

    /**
     * 目标字段名称
     * 默认为被注解字段名称
     *
     * @return
     */
    String injectTargetFieldName() default "";

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "";

}
