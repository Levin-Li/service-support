package com.levin.commons.rbac;

import com.levin.commons.plugin.Res;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;


/**
 * 简单资源
 */

@Schema(description = "树形资源")
@Data
@Accessors(chain = true)
@FieldNameConstants
public class SimpleTreeRes extends SimpleRes
        implements TreeRes<Res, Res> {

    @Schema(description = "父节点")
    Res parent;

    @Schema(description = "下级节点")
    List<Res> children;

    @Schema(description = "是否有子节点")
    boolean hasChildren;

    @Override
    public Boolean hasChildren() {
        return hasChildren;
    }

}
