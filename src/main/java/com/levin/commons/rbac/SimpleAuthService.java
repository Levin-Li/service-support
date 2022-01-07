package com.levin.commons.rbac;

import java.io.Serializable;
import java.util.Map;

/**
 * 简单认证服务
 */
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
    default String auth(String account, String password, String userAgent, Map<String, Object>... params) {
        throw new UnsupportedOperationException("不支持用户密码进行认证");
    }

    /**
     * 直接认证，并返回token
     *
     * @param loginId   登录标识
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    <T extends Serializable> String auth(T loginId, String userAgent, Map<String, Object>... params);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    <T extends Serializable> T getLoginIdByToken(String token);

    /**
     * 使 token 失效
     *
     * @param token
     */
    void invalidate(String token);

}
