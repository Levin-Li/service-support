package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;


/**
 * 简单资源
 */

@Data
@Accessors(chain = true)
@FieldNameConstants
public class SimpleRes
        implements Res {

    @Schema(description = "资源域", required = true)
    protected String domain;

    @Schema(description = "类型", required = true)
    protected String type;

    @Schema(description = "ID", required = true)
    protected String id;

    @Schema(description = "名称", required = true)
    protected String name;

    @Schema(description = "是否启用")
    protected boolean enable = true;

    @Schema(description = "排序码")
    protected Integer orderCode = 100;

    @Schema(description = "备注")
    protected String remark;

    @Schema(description = "图标")
    protected String icon;

    @Schema(description = "是否总是显示")
    protected boolean alwaysShow;

    @Schema(description = "操作列表")
    protected List<Res.Action> actionList;


    @Override
    public String toString() {
        return String.format("%s:%s:%s:%s", domain, type, id, actionList);
    }

}
