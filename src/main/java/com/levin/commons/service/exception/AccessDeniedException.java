package com.levin.commons.service.exception;

import com.levin.commons.service.domain.ServiceResp;

/**
 * 拒绝访问异常
 */
public class AccessDeniedException
        extends BaseException {

    public AccessDeniedException(String friendlyTips) {
        super(friendlyTips);
    }

    public AccessDeniedException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public AccessDeniedException(int code, String friendlyTips, String message) {
        super(code, friendlyTips, message);
    }

    public AccessDeniedException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

    @Override
    protected int getBaseCode() {
        return ServiceResp.ErrorType.ResourceError.code();
    }
}
