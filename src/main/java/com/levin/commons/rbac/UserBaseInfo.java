package com.levin.commons.rbac;


import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.service.domain.Identifiable;


/**
 * 用户基本信息
 */
public interface UserBaseInfo extends Identifiable, NamedObject {

    String getNickname();

    String getEmail();

    String getTelephone();

    String getAvatar();

    /**
     * 是否超级用户
     *
     * @return
     */
//    boolean isSuperAdmin();
}
