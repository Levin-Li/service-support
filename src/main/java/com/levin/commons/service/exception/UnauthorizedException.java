package com.levin.commons.service.exception;

import com.levin.commons.service.domain.ServiceResp;

/**
 * 未认证异常
 */
public class UnauthorizedException
        extends BaseException {

    public UnauthorizedException(String friendlyTips) {
        super(friendlyTips);
    }

    public UnauthorizedException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public UnauthorizedException(int code, String friendlyTips, String message) {
        super(code, friendlyTips, message);
    }

    public UnauthorizedException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

    @Override
    protected int getBaseCode() {
        return ServiceResp.ErrorType.AuthenticationError.code();
    }

}
