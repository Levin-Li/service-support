package com.levin.commons.service.exception;

/**
 * 未认证异常
 */
public class UnauthorizedException
        extends BaseException {

    public UnauthorizedException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public UnauthorizedException(int code, String message, String friendlyTips) {
        super(code, message, friendlyTips);
    }

    public UnauthorizedException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

}
