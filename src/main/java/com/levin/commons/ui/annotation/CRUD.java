package com.levin.commons.ui.annotation;


import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

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

    @Schema(title = "视图容器类型")
    enum ViewContainerType implements EnumDesc {

        @Schema(title = "自动选择")
        Auto,

        @Schema(title = "对话框")
        Dialog,

        @Schema(title = "抽屉")
        Drawer,

        @Schema(title = "内联")
        Inline
    }

    @Schema(title = "信息等级", description = "或是操作等级")
    enum Level implements EnumDesc {
        Auto, Primary, Secondary, Link, Light, Dark, Info, Success, Warning, Danger
    }

    //来指定该 action 的作用类型，支持：ajax、link、url、drawer、dialog、confirm、cancel、prev、next、copy、close。
    @Schema(title = "动作")
    enum Action implements EnumDesc {

        @Schema(title = "自动选择", description = "会根据情况自动处理")
        Auto,

        @Schema(title = "显示提示")
        Toast,

        @Schema(title = "显示二维码")
        ShowQrCode,

        @Schema(title = "显示图片", description = "支持多张图,https://aisuda.bce.baidu.com/amis/zh-CN/components/images")
        ShowImage,

        @Schema(title = "显示视频", description = "参考文档:https://aisuda.bce.baidu.com/amis/zh-CN/components/video")
        ShowVideo,

        @Schema(title = "显示网页", description = "")
        ShowIFrame,

        @Schema(title = "显示表单", description = "自动构建表单页面")
        ShowForm,

        @Schema(title = "显示Schema描述的界面", description = "参考:https://aisuda.bce.baidu.com/amis/zh-CN/components/service#%E5%8A%A8%E6%80%81%E6%B8%B2%E6%9F%93%E9%A1%B5%E9%9D%A2")
        ShowSchema,

        @Schema(title = "ajax请求")
        Ajax,

        @Schema(title = "执行JS代码")
        Js,

        @Schema(title = "执行JSONP请求")
        Jsonp,

        @Schema(title = "跳转链接")
        Link,

        @Schema(title = "当前也跳转")
        Url,

        @Schema(title = "拨打电话")
        Tel,

        @Schema(title = "发送邮件", description = "参考:https://aisuda.bce.baidu.com/amis/zh-CN/components/action?page=1#%E5%8F%91%E9%80%81%E9%82%AE%E4%BB%B6")
        Email,

        @Schema(title = "取消")
        Cancel,

        @Schema(title = "后退")
        Prev,

        @Schema(title = "前进")
        Next,

        @Schema(title = "复制")
        Copy,

        @Schema(title = "关闭")
        Close,

        @Schema(title = "更新当前数据")
        UpdateData,

        @Schema(title = "刷新列表数据")
        ReloadDataList
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
         * 操作按钮的图标
         * 可以是图标的链接,也可以是fontawesome的字体图标
         * <p>
         * 图标使用 v5/v6 版本的 fontawesome
         * icon默认支持fontawesome v4，如果想要支持 v5 以及 v6 版本的 fontawesome 请设置vendor为空字符串。
         * https://fontawesome.com/v4/icons/
         *
         * @return
         */
        @Schema(title = "操作按钮的图标", description = "可以是图标的链接,也可以是fontawesome的字体图标,目前支持V4版本,https://fontawesome.com/v4/icons/")
        String icon() default "";

        /**
         * 可以通过配置confirmText，实现在任意操作前，弹出提示框确认是否进行该操作。
         *
         * @return
         */
        @Schema(title = "操作确认提示内容", description = "None,作为特殊关键字,表示无需确认")
        String confirmText() default "";

        /**
         * 弹窗标题
         *
         * @return
         */
        @Schema(title = "弹窗标题")
        String confirmTitle() default "";

        /**
         * 操作等级
         *
         * @return
         */
        @Schema(title = "操作等级")
        Level level() default Level.Auto;

        /**
         * 操作按钮作用类型
         *
         * @return
         */
        @Schema(title = "操作动作")
        Action action() default Action.Auto;

        /**
         * @return
         */
        @Schema(title = "视图容器类型", description = "整对操作是视图时有效")
        ViewContainerType viewContainerType() default ViewContainerType.Auto;

        /**
         * 操作的数据
         * <p>
         * 要支持${}类型的变量
         * <p>
         * 对于
         *
         * @return
         */
        @Schema(title = "操作使用的数据", description = "一般情况下使用当前记录的数据")
        String actionData() default "";

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
        @Schema(title = "操作按钮的显示条件")
        String visibleOn() default "";

        /**
         * 记录关联类型
         * <p>
         * 默认关联单条记录
         *
         * @return
         */
        @Schema(title = "记录关联类型")
        RecordRefType recordRefType() default RecordRefType.Single;

        ////////////////////////////////////////////////////////////////////////

        /**
         * 操作成功后后的动作类型
         *
         * @return
         */
        @Schema(title = "操作成功后后的动作")
        Action successAction() default Action.Auto;

        /**
         * 操作失败后的动作类y
         *
         * @return
         */
        @Schema(title = "操作失败后的动作")
        Action failAction() default Action.Auto;


        /**
         * 操作后动作的数据
         * <p>
         * 要支持${}类型的变量
         * <p>
         * 对于
         *
         * @return
         */
        @Schema(title = "操作后动作使用的数据", description = "一般情况下使用当前结果的数据")
        String resultActionData() default "";

        /**
         * 操作关联的列表
         * <p>
         * 如果没有关联，则表示是整个页面的操作
         *
         * @return
         */
        @Schema(title = "操作关联的列表")
        String refListTable() default "default";

        /**
         * 列表描述
         *
         * @return
         */
        String desc() default "";
    }

}
