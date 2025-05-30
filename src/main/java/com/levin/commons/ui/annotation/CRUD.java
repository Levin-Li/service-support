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

    /**
     * 列表名称
     *
     * @return
     */
    String name() default "default";

    /**
     * 关联的实体类
     *
     * @return
     */
    Class<?> refEntityClass() default Void.class;

    /**
     * 显示名称
     *
     * @return
     */
    String label() default "";

    /**
     * 列表描述
     *
     * @return
     */
    String desc() default "";


    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface ListTable {

        /**
         * 列表名称
         *
         * @return
         */
        String name() default "default";

        /**
         * 列表显示条件
         * <p>
         * 默认无条件
         *
         * @return
         */
        String visibleOn() default "";

        /**
         * 显示名称
         *
         * @return
         */
        String label() default "";

        /**
         * 列表描述
         *
         * @return
         */
        String desc() default "";

    }

    enum RecordRefType {
        None, Single, Multiple
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
        String name() default "";

        /**
         * 操作按钮的显示名称
         *
         * @return
         */
        String label() default "";

        /**
         * 操作类型
         * 如，删除，修改，新增，查看，导出，打印，批量删除，批量修改等
         *
         * @return
         */
        String type() default "";

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
         * 显示是否结果视图
         *
         * @return
         */
        boolean showResultView() default false;

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

}
