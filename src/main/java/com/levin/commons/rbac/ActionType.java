package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "操作类型")
@GenNameConstant
public enum ActionType implements EnumDesc {

    @Schema(title = "创建")
    C,

    @Schema(title = "读取")
    R,

    @Schema(title = "更新")
    U,

    @Schema(title = "删除")
    D,

    ;

    @Override
    public String toString() {
        return nameAndDesc();
    }


}
