package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "组织数据权限")
@GenNameConstant
public enum OrgDataScope implements EnumDesc {

    @Schema(title = "所有部门") All,
    @Schema(title = "指定部门") Assigned,
    @Schema(title = "本部门及子部门") MyOrgAndChildren,
    @Schema(title = "仅本部门(不含子部门)") OnlyMyOrg,
    @Schema(title = "仅子部门(不含本部门)") OnlyChildren,

    @Schema(title = "仅公开数据", description = "就是orgId为空或是共享的数据") OnlyShared,
    ;

    @Override
    public String toString() {
        return nameAndDesc();
    }

}
