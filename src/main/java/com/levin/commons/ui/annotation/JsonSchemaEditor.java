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
@Schema(title = "JsonSchema编辑器", description = "用于 DTO 复杂字段的动态编辑。jsonSchema 解析规则：url: 后接 URL 地址；attr: 或 : 后接被注解字段所在类的属性名或 JSON 数据中的属性名；class: 后接类名。无前缀时先尝试按类名解析，不能解析为类时将原值作为 JSON Schema。属性引用取得的值只能使用 url:、class: 或无前缀形式（类名或原始 JSON Schema），不得再次使用 attr: 或 : 引用属性。")
public @interface JsonSchemaEditor {

    @Schema(title = "标题", description = "默认为被注解字段的@Schema.title属性")
    String title() default "";

    @Schema(title = "JsonSchema", description = "支持 url:URL、attr:属性名或 :属性名、class:类名，以及无前缀的类名或原始 JSON Schema。无前缀时先尝试按类名解析，失败后按原始 JSON Schema 处理；属性引用取得的值不得再次使用 attr: 或 :，只能按 URL、类名或原始 JSON Schema 处理。")
    String jsonSchema() default ":jsonSchemaEditor";

    @Schema(title = "是否内联", description = "内联模式,表单项将铺开展示, 否则将弹窗展示")
    boolean inlineMode() default false;

    @Schema(title = "列数", description = "默认为-1, 表示自动处理, 建议总字段数除7行,得出列数,但是不能超过父布局器的列数")
    int columns() default -1;

    @Schema(title = "描述", description = "默认取@Schema.description属性")
    String desc() default "";
}
