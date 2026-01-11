package com.levin.commons.rbac;


/**
 * 用户基本信息
 */
public interface RbacUserInfo extends RbacUserObject {

    /**
     * 昵称
     *
     * @return
     */
    default String getNickname() {
        return null;
    }

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
     * 获取MFA密钥
     *
     * @return
     */
    default String getMfaSecretKey() {
        return null;
    }

    /**
     * 头像
     *
     * @return
     */
    default String getAvatar() {
        return null;
    }

}
