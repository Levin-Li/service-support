package com.levin.commons.rbac;

import java.util.Map;

public interface SimpleAuthService<U extends UserBaseInfo> {

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
     * 认证，并返回token
     *
     * @param userBaseInfo
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    String auth(U userBaseInfo, String userAgent, Map<String, Object>... params);


    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    U getUserInfo(String token);


    /**
     * 使 token 失效
     *
     * @param token
     */
    void invalidate(String token);

//    /**
//     * 密码加密
//     *
//     * @param pwd
//     * @return
//     */
//    String encryptPassword(String pwd);

}
