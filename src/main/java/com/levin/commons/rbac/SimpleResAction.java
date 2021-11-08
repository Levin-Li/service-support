package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;


/**
 * 资源操作
 */

@Data
@Accessors(chain = true)
@FieldNameConstants
public class SimpleResAction
        implements Res.Action {

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

    @Override
    public String toString() {
        return id;
    }
}
