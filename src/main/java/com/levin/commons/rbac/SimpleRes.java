package com.levin.commons.rbac;

import com.levin.commons.plugin.Res;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;


/**
 * 简单资源
 */
@Schema(title = "简单资源", description = "简单资源定义")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"domain", "type", "id"})
@FieldNameConstants
public class SimpleRes
        implements Res {

    @Schema(title = "资源域", required = true)
    protected String domain;

    @Schema(title = "类型", required = true)
    protected String type;

    @Schema(title = "ID", required = true)
    protected String id;

    @Schema(title = "名称", required = true)
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

    @Schema(title = "操作列表")
    protected List<Res.Action> actionList;

    @Override
    public String toString() {
        return String.format("%s:%s:%s:%s", domain, type, id, actionList);
    }

}
