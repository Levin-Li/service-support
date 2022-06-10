package com.levin.commons.ui.annotation;

import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 表单布局定义
 *
 * @author llw
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FormLayout {

    enum Type implements EnumDesc {

        @Schema(description = "行布局")
        hbox,

        @Schema(description = "面板布局")
        panel,

        @Schema(description = "卡片布局")
        tabs,

        //https://aisuda.bce.baidu.com/amis/examples/wizard
        @Schema(description = "向导式布局")
        wizard,

        @Schema(description = "格子布局")
        grid,

        //参考：https://aisuda.bce.baidu.com/amis/examples/form/anchor-nav
        @Schema(description = "左侧锚点布局")
        anchor
    }

    /**
     * 默认行布局
     *
     * @return
     */
    Type type() default Type.hbox;

    /**
     * 要布局的分组
     * 默认为空分组，即无分组的表单项
     *
     * @return
     */
    String[] groups() default {""};

}
