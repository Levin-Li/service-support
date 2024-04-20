package com.levin.commons.service.exception;

import com.levin.commons.service.domain.ServiceResp;

/**
 * 认证异常 通常对应 http 401 状态
 */
public class AuthenticationException
        extends BaseException {

    public AuthenticationException(String friendlyTips) {
        super(friendlyTips);
    }

    public AuthenticationException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public AuthenticationException(int code, String friendlyTips, String message) {
        super(code, friendlyTips, message);
    }

    public AuthenticationException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

    @Override
    protected int getBaseCode() {
        return ServiceResp.ErrorType.AuthenticationError.code();
    }

}
