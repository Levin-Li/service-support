package com.levin.commons.ui.model;

import com.levin.commons.ui.annotation.Options;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link Options} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
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

    Class<?> refTargetType = Void.class;

    String dictCode = "";

    String loadApi = "";

    String defaultParams = "";

    @Schema(title = "重新加载的触发条件", description = "Js脚本, 通常是通过Api加载的时候才需要")
    String reloadOn = "";

    String[] columnMapExpr = {};

    boolean searchable = true;

    @Override
    public Class<? extends Annotation> annotationType() {
        return Options.class;
    }
}
