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

@Schema(title = "树形资源")
@Data
@Accessors(chain = true)
@FieldNameConstants
public class SimpleTreeRes extends SimpleRes
        implements TreeRes<Res, Res> {

    @Schema(title = "父节点")
    Res parent;

    @Schema(title = "下级节点")
    List<Res> children;

    @Schema(title = "是否有子节点")
    boolean hasChildren;

    @Override
    public Boolean hasChildren() {
        return hasChildren;
    }

}
