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
@Schema(title = "JsonSchema编辑器", description = "通常注解在DTO对象的复杂字段上, 用于动态编辑")
public @interface JsonSchemaEditor {

    @Schema(title = "标题", description = "默认为被注解字段的@Schema.title属性")
    String title() default "";

    @Schema(title = "JsonSchema", description = "支持3种格式，按以下顺序解析：包含斜杠 / 时视为 URL 地址，如 /sys/jsonSchema/userAddr.json；否则以冒号 : 开头时视为被注解字段所在类的属性名，如 :attrName；其他情况视为类名，如 com.test.UserAddress。")
    String jsonSchema();

    @Schema(title = "是否内联", description = "内联模式,表单项将铺开展示, 否则将弹窗展示")
    boolean inlineMode() default false;

    @Schema(title = "列数", description = "默认为-1, 表示自动处理, 建议总字段数除7行,得出列数,但是不能超过父布局器的列数")
    int columns() default -1;

    @Schema(title = "描述", description = "默认取@Schema.description属性")
    String desc() default "";
}
