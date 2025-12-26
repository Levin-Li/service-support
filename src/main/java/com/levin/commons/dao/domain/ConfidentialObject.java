package com.levin.commons.dao.domain;

/**
 * 保密数据对象
 *
 * @author llw
 */
public interface ConfidentialObject {

    int PLATFORM_PUBLIC = -1000;

    // 平台内共享
    int PLATFORM_SHARED = -1;

    // 租户内共享
    int TENANT_SHARED = 0;

    // 组织内共享
    int ORG_SHARED = 1000;

    // 个人数据
    int PERSON_PRIVATE = 2000;

    // 组织管理员
    int ORG_ADMIN_ACCESSIBLE = 3000;

    // 租户管理员
    int TENANT_ADMIN_ACCESSIBLE = 4000;

    /// /////////////////////////////////////////////////////////////
    // SAAS用户
    int SAAS_USER_ACCESSIBLE = 10000;

    // SAAS管理员
    int SAAS_ADMIN_ACCESSIBLE = 11000;

    // 超级管理员
    int SUPER_ADMIN_ACCESSIBLE = 12000;

    // 平台专家
    int PLATFORM_EXPERT_ACCESSIBLE = 13000;

    /**
     * 获取保密级别
     * 数值越大，级别越高
     *
     * @return
     */
    default Integer getConfidentialLevel() {
        return TENANT_SHARED;
    }
}
