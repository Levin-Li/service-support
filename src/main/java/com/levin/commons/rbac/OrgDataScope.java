package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author echo
 */

@Schema(title = "树形组织数据权限")
@GenNameConstant
public enum OrgDataScope implements EnumDesc {

    @Schema(title = "所有部门") All,
    @Schema(title = "指定部门") Assigned,
    @Schema(title = "本部门及子部门", description = "本部门及所有下级部门") MyOrgAndChildren,
    @Schema(title = "仅本部门") OnlyMyOrg,
    @Schema(title = "全部子部门", description = "所有下级部门") OnlyChildren,
    @Schema(title = "仅直接子部门", description = "仅直接下级部门,不含孙及以下部门") OnlyDirectChildren,

    @Schema(title = "仅公开数据", description = "就是orgId为空或是共享的数据") OnlyShared,

    //@Schema(title = "无部门访问权限", description = "无部门访问权限, 建议抛出异常") None,
    ;

    @Override
    public String toString() {
        return nameAndDesc();
    }

}
