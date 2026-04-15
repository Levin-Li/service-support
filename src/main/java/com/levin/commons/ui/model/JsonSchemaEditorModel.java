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
@Schema(title = "JsonSchema编辑器", description = "通常注解在DTO对象的复杂字段上, 用于动态编辑")
public class JsonSchemaEditorModel implements JsonSchemaEditor {

    @Schema(title = "字段名", description = "注解所在字段名，用于全局定位")
    String fieldName = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    @Schema(title = "标题", description = "默认为被注解字段的@Schema.title属性")
    String title = "";

    @Schema(description = "支持3种格式: 1类名, 2 Url, 3 被注解字段同个类的属性名. 如: 1 com.test.UserAddress  2 :attrName 3 /sys/jsonSchema/userAddr.json")
    String jsonSchema = "";

    @Schema(title = "是否内联", description = "内联模式,表单项将铺开展示, 否则将弹窗展示")
    boolean inlineMode = false;

    @Schema(title = "列数", description = "默认为-1, 表示自动处理, 建议总字段数除7行,得出列数,但是不能超过父布局器的列数")
    int columns = -1;

    String desc = "";

    @Override
    public Class<? extends Annotation> annotationType() {
        return JsonSchemaEditor.class;
    }
}
