package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.media.Schema;

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
     * spring el 表达式前缀
     */
    String SPEL_PREFIX = "#!spel:";

    /**
     * 关联的实体类
     *
     * @return
     */
    Class<?> refEntityClass();

    /**
     * 关联实体Id的字段名
     *
     * @return
     */
    String refEntityIdFieldName();

    /**
     * <p>
     * 也可以是表达式，如： SPEL_PREFIX 前缀
     *
     * @return
     */
    @Schema(title = "取值表达式", description = "可以是加载的实体数据的多级属性值(用.向下索引)，如addr.ip，同时可以用|分隔多个优先级属性")
    String valueExpr();

    /**
     * 目标字段名称
     * 默认为被注解字段名称
     *
     * @return
     */
    String targetFieldName() default "";

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "关联注入";

}
