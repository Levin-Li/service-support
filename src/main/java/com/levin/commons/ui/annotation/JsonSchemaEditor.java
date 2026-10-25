package com.levin.commons.ui.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 为 DTO 的复杂字段指定 JSON Schema 来源。
 * <ul>
 *     <li>{@code url:/sys/jsonSchema/userAddr.json}：{@code url:} 后的内容是 URL。</li>
 *     <li>{@code attr:jsonSchemaEditor} 或 {@code :jsonSchemaEditor}：读取被注解字段所在类的属性或 JSON 数据中的属性。</li>
 *     <li>{@code class:com.levin.oak.base.entities.vo.InvoiceInfo}：{@code class:} 后的内容是类名。</li>
 *     <li>无前缀：先尝试按类名解析；找不到类时，将整个值作为原始 JSON Schema。</li>
 * </ul>
 * 属性引用只解析一次。取出的属性值可以是 {@code url:}、{@code class:} 或无前缀的类名/原始 JSON Schema，
 * 不能再以 {@code attr:} 或 {@code :} 引用另一个属性。
 *
 * @author llw
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "JsonSchema编辑器", description = "用于 DTO 复杂字段的动态编辑。jsonSchema 来源规则："
        + "url: 后接 URL，如 url:/sys/jsonSchema/userAddr.json；"
        + "attr: 或 : 后接被注解字段所在类的属性名或 JSON 数据中的属性名，如 attr:jsonSchemaEditor、:jsonSchemaEditor；"
        + "class: 后接类名，如 class:com.levin.oak.base.entities.vo.InvoiceInfo。"
        + "无前缀时先尝试按类名解析；找不到类时，将整个值作为原始 JSON Schema，如 {\"type\":\"object\"}。默认值为 :jsonSchemaEditor。"
        + "属性引用只解析一次：取出的属性值只能是 url:、class: 或无前缀的类名/原始 JSON Schema，不允许再以 attr: 或 : 引用属性。")
public @interface JsonSchemaEditor {

    @Schema(title = "标题", description = "默认为被注解字段的@Schema.title属性")
    String title() default "";

    @Schema(title = "JsonSchema", description = "来源写法：url:/sys/jsonSchema/userAddr.json 表示 URL；"
            + "attr:jsonSchemaEditor 或 :jsonSchemaEditor 表示所在类属性或 JSON 数据属性；"
            + "class:com.levin.oak.base.entities.vo.InvoiceInfo 表示类。"
            + "无前缀先按类名查找，找不到类时将整个值作为原始 JSON Schema，如 {\"type\":\"object\"}。默认值为 :jsonSchemaEditor。"
            + "属性引用只解析一次，属性值不得再次以 attr: 或 : 引用属性；属性值只能是 URL、类或原始 JSON Schema。")
    String jsonSchema() default ":jsonSchemaEditor";

    @Schema(title = "是否内联", description = "内联模式,表单项将铺开展示, 否则将弹窗展示")
    boolean inlineMode() default false;

    @Schema(title = "列数", description = "默认为-1, 表示自动处理, 建议总字段数除7行,得出列数,但是不能超过父布局器的列数")
    int columns() default -1;

    @Schema(title = "描述", description = "默认取@Schema.description属性")
    String desc() default "";
}
