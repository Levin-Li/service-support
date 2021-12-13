package com.levin.commons.rbac;


import com.levin.commons.dao.domain.Identifiable;
import com.levin.commons.dao.domain.NamedObject;


/**
 * 用户基本信息
 */
public interface UserBaseInfo extends Identifiable, NamedObject {

    String getNickname();

    String getEmail();

    String getTelephone();

    String getAvatar();

}
