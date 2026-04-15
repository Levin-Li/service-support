package com.levin.commons.ui.annotation;

import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 表单布局定义
 *
 * @author llw
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FormLayout {

    enum Type implements EnumDesc {

        @Schema(title = "行布局")
        vbox,

        @Schema(title = "面板布局")
        panel,

        @Schema(title = "卡片布局")
        tabs,

        //https://aisuda.bce.baidu.com/amis/examples/wizard
        @Schema(title = "向导式布局")
        wizard,

        @Schema(title = "格子布局")
        grid,

        //参考：https://aisuda.bce.baidu.com/amis/examples/form/anchor-nav
        @Schema(title = "左侧锚点布局")
        anchor;

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Schema(title = "标题")
    String title() default "";

    @Schema(title = "本布局在父布局中的占位顺序", description = "默认为-1，表示默认")
    int order() default -1;

    @Schema(title = "布局项栅格占位", description = "默认为-1，表示使用布局默认值")
    int span() default -1;

    @Schema(title = "列数", description = "默认为-1, 表示自动处理, 建议总字段数除7行,得出列数,但是不能超过父分组的列数")
    int columns() default -1;

    @Schema(title = "最大列数", description = "默认为4,超过4列不方便")
    int maxColumns() default 4;

    @Schema(title = "展示分类", description = "通常用于表单项过多时, 展示基本部分还是全部, 比如高级搜索,简单搜索, 基本信息, 更多信息等场景")
    String[] showCategories() default {"更多"};

    /**
     * 要布局的分组
     * 默认为空分组，即无分组的表单项
     * <p>
     * 通过组标识实现布局嵌套，通过（dot）分割
     *
     * @return
     */
    @Schema(title = "分组", description = "分组关联表单项, 通过布局分组实现布局嵌套，通过点分割,如base.addr,标识这个布局是嵌套在base组中")
    String group() default "";

    /**
     * 默认行布局
     *
     * @return
     */
    Type type() default Type.vbox;


    @Schema(title = "布局参数", description = "json格式, 具体有布局类型解析和使用")
    String layoutParams() default "";

    @Schema(title = "显示条件", description = "前端Js表达式，返回true时布局显示")
    String visibleOn() default "";

    @Schema(title = "样式")
    String style() default "";

}
