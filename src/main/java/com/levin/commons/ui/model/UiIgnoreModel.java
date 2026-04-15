package com.levin.commons.ui.model;

import com.levin.commons.ui.annotation.UiIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * {@link UiIgnore} 的模型对象。
 */
@Data
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
public class UiIgnoreModel implements UiIgnore {

    @Schema(title = "类名", description = "注解所在的类全名，用于全局定位")
    String className = "";

    @Schema(title = "字段名", description = "注解所在字段名，用于全局定位")
    String fieldName = "";

    @Schema(title = "方法名", description = "注解所在方法名，用于全局定位")
    String methodName = "";

    @Schema(title = "别名", description = "用于全局标识的别名")
    String alias = "";

    String[] value = {};

    @Override
    public Class<? extends Annotation> annotationType() {
        return UiIgnore.class;
    }
}
