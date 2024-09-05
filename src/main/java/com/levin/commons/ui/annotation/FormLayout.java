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

    /**
     * 名称
     *
     * @return
     */
    String name() default "";

    /**
     * 描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 要布局的分组
     * 默认为空分组，即无分组的表单项
     * <p>
     * 通过组标识实现布局嵌套，通过（dot）分割
     *
     * @return
     */
    String group() default "";

    /**
     * 默认行布局
     *
     * @return
     */
    Type type() default Type.vbox;

    /**
     * 布局参数
     * 如表格，[2,3], 横纵向，最大多少列，
     *
     * @return
     */
    String layoutParams() default "";

    /**
     * 布局样式
     *
     * @return
     */
    String style() default "";

}
