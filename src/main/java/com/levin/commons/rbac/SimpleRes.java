package com.levin.commons.rbac;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.Collections;
import java.util.List;


/**
 * 简单资源
 */
@Schema(title = "简单资源", description = "简单资源定义")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"domain", "type", "id"})
@FieldNameConstants
@JsonAutoDetect(
        // 只序列化字段
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        // 忽略所有 getter
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        // 忽略 isXXX
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class SimpleRes
        implements Res {

    @Schema(title = "资源域", description = "支持*通配符，支持|或选择符")
    protected String domain;

    @Schema(title = "类型", description = "支持*通配符，支持|或选择符")
    protected String type;

    @Schema(title = "ID", description = "支持*通配符，支持|或选择符")
    protected String id;

    @Schema(title = "名称")
    protected String name;

    @Schema(title = "是否启用")
    protected boolean enable = true;

    @Schema(title = "排序码")
    protected Integer orderCode = 100;

    @Schema(title = "备注")
    protected String remark;

    @Schema(title = "图标")
    protected String icon;

    @Schema(title = "是否总是显示")
    protected boolean alwaysShow;

    @Schema(title = "操作列表", description = "支持*通配符，支持|或选择符")
    protected List<ResConditionAction> actionList = Collections.emptyList();

    @Override
    public String toString() {
        return String.format("%s:%s:%s:%s", domain, type, id, actionList);
    }

}
