package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "机密级别")
@GenNameConstant
public enum ConfidentialLevel implements EnumDesc {

    @Schema(title = "平台公开", description = "平台内公开,不需要登录,匿名用户可查看")
    PLATFORM_PUBLIC(Integer.MIN_VALUE),

    @Schema(title = "平台共享", description = "平台内共享,要求登录")
    PLATFORM_SHARED(-1_0000),

    @Schema(title = "系统共享", description = "租户内共享")
    TENANT_SHARED(0),

    @Schema(title = "组织共享")
    ORG_SHARED(1000),

    @Schema(title = "个人数据")
    PERSON_PRIVATE(2000),

    @Schema(title = "组织管理")
    ORG_ADMIN_LEVEL(3000),

    @Schema(title = "系统管理")
    TENANT_ADMIN_LEVEL(4000),

    @Schema(title = "平台运维")
    PLATFORM_USER_LEVEL(10000),

    @Schema(title = "平台管理")
    PLATFORM_ADMIN_LEVEL(11000),

    @Schema(title = "平台超管")
    PLATFORM_SUPER_ADMIN_LEVEL(12000),

    @Schema(title = "平台专家")
    PLATFORM_EXPERT_LEVEL(13000),
    ;


    private final int level;

    ConfidentialLevel(int level) {
        this.level = level;
    }

    /**
     * 放回编码
     *
     * @return
     */
    @Override
    public int code() {
        return level;
    }


    @Override
    public String toString() {
        return nameAndDesc();
    }


}
