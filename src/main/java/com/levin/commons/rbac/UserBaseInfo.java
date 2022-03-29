package com.levin.commons.rbac;


import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.service.domain.Identifiable;

import java.util.Collection;
import java.util.Collections;


/**
 * 用户基本信息
 */
public interface UserBaseInfo<ROLE> extends Identifiable, NamedObject {

    /**
     * 租户 ID
     *
     * @return
     */
    String getTenantId();

    /**
     * 是否超级用户
     *
     * @return
     */
    boolean isSuperAdmin();

    /**
     * 昵称
     *
     * @return
     */
    String getNickname();

    /**
     * 邮箱
     *
     * @return
     */
    String getEmail();

    /**
     * 电话
     *
     * @return
     */
    String getTelephone();

    /**
     * 头像
     *
     * @return
     */
    String getAvatar();

    /**
     * 获取角色列表
     *
     * @return
     */
    default Collection<ROLE> getRoleList() {
        return Collections.emptyList();
    }

}
