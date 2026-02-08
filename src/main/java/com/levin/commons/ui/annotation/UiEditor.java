package com.levin.commons.ui.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * @author llw
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UiEditor {

    /**
     * 表单定义, 表单url 或是表单id
     * url: http://xxx.xxx.xxx/xxx.json
     * id: xx
     * <p>
     * 无默认值
     * <p>
     * 表单优先级高于jsonSchema
     *
     * @return
     */
    @Schema(description = "表单定义, 表单url 或是表单id,     * class: xx.xx.xx\n" +
            "     * attrName: 当前类的属性名\n" +
            "     * id: xx\n" +
            "     * 默认当前字段的类型")
    String form() default "";

    /**
     * class: xx.xx.xx
     * attrName: 当前类的属性名
     * id: xx
     * 默认当前字段的类型
     *
     * @return
     */
    @Schema(description = "     * class: xx.xx.xx\n" +
            "     * attrName: 当前类的属性名\n" +
            "     * id: xx\n" +
            "     * 默认当前字段的类型")
    String jsonSchema() default "";

    /**
     * 扩展信息
     *
     * @return
     */
    String exInfo() default "";

    /**
     * 动作描述
     *
     * @return
     */
    String desc() default "";

}
