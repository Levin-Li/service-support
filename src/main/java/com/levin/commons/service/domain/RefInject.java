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
     * 关联对象的类型
     *
     * @return
     */
    @Schema(title = "关联对象类型", description = "关联对象类型、关联对象服务类2选1, 必须设置一个")
    String refObjectType() default "";

    @Schema(title = "关联对象服务类", description = "关联对象类型、关联对象服务类2选1, 必须设置一个")
    Class<?> refObjectServiceClass() default Void.class;

    @Schema(title = "关联对象服务方法")
    String refObjectServiceMethod() default "findById";

    /**
     * 获取关联对象的标识表达式
     *
     * @return
     */
    @Schema(title = "获取关联对象标识的表达式", description = "可以是加载的实体数据的多级属性值(用.向下索引)，如addr.ip，同时可以用|分隔多个优先级属性，也可以是spel(以SPEL_PREFIX:为前缀)")
    String idExpr();

    /**
     * <p>
     * 也可以是表达式，如： SPEL_PREFIX 前缀
     *
     * @return
     */
    @Schema(title = "获取注入值的表达式", description = "可以是加载的实体数据的多级属性值(用.向下索引)，如addr.ip，同时可以用|分隔多个优先级属性，也可以是spel(以SPEL_PREFIX:为前缀)")
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
