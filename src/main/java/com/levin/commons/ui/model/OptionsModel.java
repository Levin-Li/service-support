package com.levin.commons.ui.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.ui.annotation.Options;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link Options} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class OptionsModel implements Options {

    @Schema(title = "字段名", description = "注解所在字段名，用于全局定位")
    String fieldName = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    String name = "";

    String desc = "";

    boolean multiSelect = false;

    String style = "";

    String defaultValue = "";

    String[] items = {};

    String refTargetTypeName;

    String dictCode = "";

    String loadApi = "";

    String defaultParams = "";

    @Schema(title = "重新加载的触发条件", description = "Js脚本, 通常是通过Api加载的时候才需要")
    String reloadOn = "";

    String[] columnMapExpr = {};

    boolean searchable = true;

    /**
     * 关联的目标类型, 枚举或是实体类
     * <p>
     * 枚举类,或是关联的实体类
     * <p>
     *
     * @return
     */
    @SneakyThrows
    @Override
    public Class<?> refTargetType() {
        return (refTargetTypeName == null || refTargetTypeName.isBlank() || refTargetTypeName.trim().equals(Void.class.getName())) ? null : getClass().getClassLoader().loadClass(refTargetTypeName);
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Options.class;
    }
}
