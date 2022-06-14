package com.levin.commons.rbac;

import java.io.Serializable;

public interface AuthReq
        extends Serializable {

    /**
     * 获取租户ID
     *
     * @return
     */
    String getTenantId();

    /**
     * 获取帐号信息
     *
     * @return
     */
    String getAccount();

    /**
     * 获取密码
     *
     * @return
     */
    String getPassword();

    /**
     * 获取验证码
     *
     * @return
     */
    String getVerificationCode();

    /**
     * 获取验证码类型
     *
     * @return
     */
    String getVerificationCodeType();

    /**
     * 获取客户端类型
     *
     * @return
     */
    String getClientType();

    /**
     * 获取应用标识
     *
     * @return
     */
    String getAppId();

}
