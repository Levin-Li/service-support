package com.levin.commons.rbac;

import com.levin.commons.service.domain.SimpleIdentifiable;
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
public class DefaultSimpleIdentifiableObject
        implements SimpleIdentifiable {

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

    @Override
    public String toString() {
        return id;
    }

}
