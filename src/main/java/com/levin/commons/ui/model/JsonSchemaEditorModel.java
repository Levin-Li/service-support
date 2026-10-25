package com.levin.commons.ui.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.ui.annotation.JsonSchemaEditor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link JsonSchemaEditor} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(title = "JsonSchema编辑器", description = "用于 DTO 复杂字段的动态编辑。jsonSchema 来源规则："
        + "url: 后接 URL，如 url:/sys/jsonSchema/userAddr.json；"
        + "attr: 或 : 后接被注解字段所在类的属性名或 JSON 数据中的属性名，如 attr:jsonSchemaEditor、:jsonSchemaEditor；"
        + "class: 后接类名，如 class:com.levin.oak.base.entities.vo.InvoiceInfo。"
        + "无前缀时先尝试按类名解析；找不到类时，将整个值作为原始 JSON Schema，如 {\"type\":\"object\"}。注解默认值为 :jsonSchemaEditor。"
        + "属性引用只解析一次：取出的属性值只能是 url:、class: 或无前缀的类名/原始 JSON Schema，不允许再以 attr: 或 : 引用属性。")
public class JsonSchemaEditorModel implements JsonSchemaEditor {

    @Schema(title = "字段名", description = "注解所在字段名，用于全局定位")
    String fieldName = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    @Schema(title = "标题", description = "默认为被注解字段的@Schema.title属性")
    String title = "";

    @Schema(title = "JsonSchema", description = "来源写法：url:/sys/jsonSchema/userAddr.json 表示 URL；"
            + "attr:jsonSchemaEditor 或 :jsonSchemaEditor 表示所在类属性或 JSON 数据属性；"
            + "class:com.levin.oak.base.entities.vo.InvoiceInfo 表示类。"
            + "无前缀先按类名查找，找不到类时将整个值作为原始 JSON Schema，如 {\"type\":\"object\"}。注解默认值为 :jsonSchemaEditor。"
            + "属性引用只解析一次，属性值不得再次以 attr: 或 : 引用属性；属性值只能是 URL、类或原始 JSON Schema。")
    String jsonSchema = "";

    @Schema(title = "是否内联", description = "内联模式,表单项将铺开展示, 否则将弹窗展示")
    boolean inlineMode = false;

    @Schema(title = "列数", description = "默认为-1, 表示自动处理, 建议总字段数除7行,得出列数,但是不能超过父布局器的列数")
    int columns = -1;

    @Schema(title = "描述", description = "默认取@Schema.description属性")
    String desc = "";

    @Override
    public Class<? extends Annotation> annotationType() {
        return JsonSchemaEditor.class;
    }
}
