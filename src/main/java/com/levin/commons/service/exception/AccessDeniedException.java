package com.levin.commons.service.exception;

/**
 * 拒绝访问异常
 */
public class AccessDeniedException
        extends BaseException {

    public AccessDeniedException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public AccessDeniedException(int code, String message, String friendlyTips) {
        super(code, message, friendlyTips);
    }

    public AccessDeniedException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }
}
