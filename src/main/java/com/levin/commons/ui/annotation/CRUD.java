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
@Schema(title = "CRUD页面", description = "通常注解在控制器类上, 用于标识这个是一个CRUD页面, 一个页面中允许存在多个列表, 多个列表时使用tab样式展示")
public @interface CRUD {

    @Schema(title = "名称", description = "默认取控制器@Tag注解")
    String name() default "";

    @Schema(title = "标题")
    String title() default "";

    @Schema(title = "样式", description = "样式")
    String style() default "";

    @Schema(title = "描述")
    String desc() default "";

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Schema(title = "列表", description = "通常注解在控制器的列表方法上")
    @Inherited
    @interface ListTable {

        /**
         * 列表名称
         *
         * @return
         */
        @Schema(title = "列表名称", description = "通常用于关联[CURD.OP]的[opRefTargetName]属性")
        String name() default "default";

        /**
         * 显示名称
         *
         * @return
         */
        @Schema(title = "标题", description = "通常用于在一个CURD页面中有多个列表时, 默认获取[refEntityClass]属性指定类的@Schema注解的title属性")
        String title() default "";


        @Schema(title = "列表关联的实体类", description = "也可能不是具体的一个实体")
        Class<?> refEntityClass() default Void.class;

        /**
         * 列表显示条件
         * <p>
         * 默认无条件
         *
         * @return
         */
        @Schema(title = "显示条件", description = "Js脚本, 支持的变量:__user 当前登录用户信息;")
        String visibleOn() default "";


        @Schema(title = "样式", description = "样式")
        String style() default "";


        String desc() default "";

    }

    @Schema(title = "操作关联的目标类型")
    enum OpRefTargetType {

        @Schema(title = "无")
        None,

        @Schema(title = "单条数据")
        SingleRow,

        @Schema(title = "多条数据")
        MultipleRow,

        @Schema(title = "列表")
        ListTable,

        @Schema(title = "其他")
        Other;
    }

    @Schema(title = "视图容器类型")
    enum ViewContainerType implements EnumDesc {

        @Schema(title = "自动选择")
        Auto,

        @Schema(title = "弹出式对话框")
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

        @Schema(title = "跳转链接", description = "新窗口打开link")
        Link,

        @Schema(title = "当前页跳转", description = "当前页面替换")
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

        @Schema(title = "复制", description = "复制API接口或是动作返回的数据到操作系统黏贴板")
        Copy,

        @Schema(title = "关闭", description = "关闭当前页面或是弹窗")
        Close,

        @Schema(title = "更新当前数据", description = "把API接口或是动作返回的数据更新到当前这条数据")
        UpdateData,

        @Schema(title = "重新加载列表数据", description = "通常用于列表页面")
        ReloadDataList
    }

    /**
     * 页面操作按钮
     * 关联控制器方法
     */
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Schema(title = "操作", description = "通常注解在控制器方法上")
    @Inherited
    @interface Op {

        /**
         * 操作名称
         *
         * @return
         */
        @Schema(title = "操作名称")
        String name() default "";

        /**
         * 操作按钮的显示名称
         *
         * @return
         */
        @Schema(title = "操作按钮的显示名称")
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
        @Schema(title = "操作确认弹窗提示内容", description = "操作前确认提示内容, None,作为特殊关键字,表示无需确认")
        String confirmText() default "";

        /**
         * 弹窗标题
         *
         * @return
         */
        @Schema(title = "操作确认弹窗标题")
        String confirmTitle() default "";

        /**
         * 操作等级
         *
         * @return
         */
        @Schema(title = "操作等级", description = "操作等级,通常影响")
        Level level() default Level.Auto;

        /**
         * 操作按钮作用类型
         *
         * @return
         */
        @Schema(title = "操作动作", description = "默认自动选择, 如果注解标注在控制器方法上, [Auto]就是对应到[Ajax]")
        Action action() default Action.Auto;

        /**
         * @return
         */
        @Schema(title = "视图容器类型", description = "对操作是视图时有效")
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
        @Schema(title = "操作使用的数据", description = "一般情况下使用当前记录的数据, 一般接口通常是直接使用数据的id属性")
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
        @Schema(title = "操作按钮的显示条件", description = "js脚本,  支持的变量:__user 当前登录用户信息; 如果是关联单条记录的操作 还要支持当前记录的所有属性")
        String visibleOn() default "";
        ////////////////////////////////////////////////////////////////////////

        /**
         * 操作成功后后的动作类型
         *
         * @return
         */
        @Schema(title = "操作成功后的动作")
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
        @Schema(title = "操作后的动作使用的数据", description = "默认为空, 表示直接使用api接口返回的数据, 支持 ${} 表达式, 如${data.detail} 表示使用返回Json中data属性中的detail属性")
        String resultActionData() default "";

        /**
         * 记录关联类型
         * <p>
         * 默认关联单条记录
         *
         * @return
         */
        @Schema(title = "操作关联的目标类型")
        OpRefTargetType opRefTargetType() default OpRefTargetType.SingleRow;

        /**
         * 操作关联的列表
         * <p>
         * 如果没有关联，则表示是整个页面的操作
         *
         * @return
         */
        @Schema(title = "操作关联的目标列表名称", description = "如果为空，则表示是页面的操作")
        String opRefTargetListName() default "default";

        /**
         * 列表描述
         *
         * @return
         */
        String desc() default "";
    }

}
