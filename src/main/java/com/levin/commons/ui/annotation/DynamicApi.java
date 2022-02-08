package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * API接口定义
 *
 * @author llw
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
public @interface DynamicApi {

    /**
     * API 的 URL
     * 可包含参数
     * <p>
     * url 优先
     *
     * @return
     */
    String url() default "";

    /**
     * API 调用的服务类 或是查询对象
     * <p>
     * 服务类的定义，通常是在spring context中，或是静态类
     *
     * @return
     */
    Class<?> serviceOrQueryDtoClass() default Void.class;

    /**
     * 服务方法名
     * <p>
     * 仅对服务有效
     *
     * <p>
     * 方法必须只要一个参数
     * 方法名必须唯一
     *
     * @return
     */
    String serviceMethodName() default "";

    /**
     * 部分参数列表
     *
     * @return
     */
    Param[] params() default {};

}
