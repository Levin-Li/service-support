package com.levin.commons.rbac;

import java.util.Map;

public interface SimpleAuthService {

    /**
     * 认证，并返回token
     *
     * @param account
     * @param password
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    String auth(String account, String password, String userAgent, Map<String, Object>... params);


    /**
     * 直接认证，并返回token
     *
     * @param userBaseInfo
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    String auth(UserBaseInfo userBaseInfo, String userAgent, Map<String, Object>... params);


    /**
     * 获取用户信息
     *
     * @param token
     * @param <T>
     * @return
     */
    <T extends UserBaseInfo> T getUserInfo(String token);

    /**
     * 密码加密
     *
     * @param pwd
     * @return
     */
    String encryptPassword(String pwd);

}
