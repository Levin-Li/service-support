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

    @Schema(title = "名称")
    String name = "";

    @Schema(title = "描述")
    String desc = "";

    @Schema(title = "是否可多选")
    boolean multiSelect = false;

    @Schema(title = "样式")
    String style = "";

    @Schema(title = "未选择时的默认值")
    String defaultValue = "";

    @Schema(title = "固定选项列表", description = "每一个选项支持 3个属性，value, label, desc ，其中value是必须的，属性之间用||分隔")
    String[] items = {};

    @Schema(title = "关联的目标类型", description = "枚举类,或是关联的实体类,或是Dao查询对象")
    String refTargetTypeName = "";

    @Schema(title = "系统的字典编码")
    String dictCode = "";

    @Schema(title = "自定义加载选项加载API", description = "优先级最低")
    String loadApi = "";

    @Schema(title = "默认参数", description = "json,调用服务端接口时的默认参数")
    String defaultParams = "";

    @Schema(title = "重新加载的触发条件", description = "Js脚本, 通常是通过Api加载的时候才需要")
    String reloadOn = "";

    @Schema(title = "列映射表达式", description = "每一个选项支持 3个属性，value, label, desc;; 如果都不配置，则默认为value, label")
    String[] columnMapExpr = {};

    @Schema(title = "搜索参数名称", description = "搜索时附加的参数名称")
    String searchParamName = "name";

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
        return (refTargetTypeName == null || refTargetTypeName.isBlank() || refTargetTypeName.trim().equals(Void.class.getName())) ? Void.class : getClass().getClassLoader().loadClass(refTargetTypeName);
    }

    public OptionsModel refTargetType(Class<?> refTargetType) {
        this.refTargetTypeName = refTargetType == null ? "" : refTargetType.getName();
        return this;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Options.class;
    }
}
