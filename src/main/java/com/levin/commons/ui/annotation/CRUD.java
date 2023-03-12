package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * CRUD 注解用来标准一个控制器是否的CRUD控制器
 * <p>
 * 一个页面一个查询面板，一个表格，多个记录级操作，多个页面级操作
 *
 * @author llw
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CRUD {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface ListTable {

        /**
         * 列表名称
         *
         * @return
         */
        String value() default "default";

        /**
         * 列表显示条件
         * <p>
         * 默认无条件
         *
         * @return
         */
        String visibleOn() default "";

        /**
         * 列表描述
         *
         * @return
         */
        String desc() default "";

    }

    enum RecordRefType {
        None,
        Single,
        Multiple
    }

    /**
     * 页面操作按钮
     * 关联控制器方法
     */
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Op {

        /**
         * 操作名称
         *
         * @return
         */
        String value() default "";

        /**
         * 操作按钮的显示条件
         * <p>
         * 建议不涉及权限
         *
         * <p>
         * 默认无条件
         *
         * @return
         */
        String visibleOn() default "";

        /**
         * 记录关联类型
         * <p>
         * 默认关联单条记录
         *
         * @return
         */
        RecordRefType recordRefType() default RecordRefType.Single;

        /**
         * 操作关联的列表
         * <p>
         * 如果没有关联，则表示是整个页面的操作
         *
         * @return
         */
        String refListTable() default "default";

        /**
         * 列表描述
         *
         * @return
         */
        String desc() default "";
    }

    /**
     * 标题或是名称
     *
     * @return
     */
    String value() default "";

}
