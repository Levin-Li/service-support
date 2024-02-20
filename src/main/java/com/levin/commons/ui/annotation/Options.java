package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * 值选项
 * <p>
 * 来源支持以下几个方面
 * <p>
 * 1、支持固定配置
 * 2、API接口
 * 3、枚举类
 * 4、查询对象
 * 5、支持系统的字典编码
 * <p>
 * 支持自动完成 autoCompleteSearchParamName
 * <p>
 * <p>
 * 可以单独存在，支持amis CRUD 组件
 *
 *
 * <p>
 *
 * @author llw
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Options {

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @interface Item {
        /**
         * 值
         *
         * @return
         */
        String value();

        /**
         * 标签
         *
         * @return
         */
        String label() default "";

        /**
         * 动作描述
         *
         * @return
         */
        String desc() default "";

    }

    /**
     * 是否 多选/多值
     */
    boolean multiValue() default false;

    /**
     * 未选择是的默认值
     *
     * @return
     */
    String defaultValue() default "";

    /**
     * 固定选项列表
     *
     * @return
     */
    Item[] items() default {};

    /**
     * 是否可以搜索
     *
     * @return
     */
    boolean searchable() default true;

    /**
     * 地址
     * 格式返回必须是对象，并且至少具备value属性
     *
     * @return
     */
    String api() default "";

    /**
     * 系统的字典编码
     * <p>
     * 一个字典编码-有一到多个选项
     *
     * @return
     */
    String[] dictCodes() default {};

    /**
     * 查询类或是枚举类
     *
     * @return
     */
    Class<?> queryObjectOrEnumClass() default Void.class;

    /**
     * 自动补全搜索参数名称，主要搜索服务端
     * <p>
     * 目前建议只针对查询对象和API接口
     * <p>
     * 默认不搜索
     *
     * @return
     */
    String autoCompleteSearchParamName() default "";

}
