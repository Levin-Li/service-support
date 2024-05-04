package com.levin.commons.rbac;

import java.io.Serializable;

/**
 * 用户基本信息
 */
public interface RbacUserInfo<ROLE extends Serializable> extends RbacUserObject<ROLE> {

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

}
