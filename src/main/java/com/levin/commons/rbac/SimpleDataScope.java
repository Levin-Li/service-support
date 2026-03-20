package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collection;

@Schema(title = "数据范围")

@GenNameConstant
@Data
@Accessors(chain = true)
@ToString
public class SimpleDataScope implements DataScope {

    @Schema(title = "组织范围")
    Collection<? extends OrgScope> orgScopeList;

    @Schema(title = "数据访问级别")
    Integer confidentialDataAccessLevel;

}
