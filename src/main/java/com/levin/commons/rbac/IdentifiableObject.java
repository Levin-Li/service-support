package com.levin.commons.rbac;

import com.levin.commons.service.domain.Identifiable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;


/**
 * 简单资源
 */

@Data
@Accessors(chain = true)
@FieldNameConstants
@EqualsAndHashCode(of = "id")
public class IdentifiableObject
        implements Identifiable {

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
