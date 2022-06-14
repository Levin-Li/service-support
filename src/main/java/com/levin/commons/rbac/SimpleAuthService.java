package com.levin.commons.rbac;

import java.util.Map;

/**
 * 简单认证服务
 */
public interface SimpleAuthService<TOKEN, UID> {

    /**
     * 认证，并返回token
     *
     * @param authReq 认证请求
     * @param extras  附加参数
     * @return 认证成功后的token
     */
    default <REQ extends AuthReq> TOKEN auth(REQ authReq, Map<String, Object>... extras) {
        throw new UnsupportedOperationException("未实现认证");
    }

    /**
     * 直接认证，并返回token
     *
     * @param loginId 登录标识
     * @param extras  附加参数
     * @return 认证成功后的token
     */
    TOKEN auth(UID loginId, Map<String, Object>... extras);

    /**
     * 获取登录ID
     *
     * @param token
     * @return
     */
    UID getLoginIdByToken(TOKEN token);

    /**
     * 使 token 失效
     *
     * @param token
     */
    void invalidate(TOKEN token);

}
