package com.levin.commons.service.exception;

import com.levin.commons.service.domain.ServiceResp;

/**
 * 拒绝访问异常
 */
public class ResourceException
        extends BaseException {

    public ResourceException(String friendlyTips) {
        super(friendlyTips);
    }

    public ResourceException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public ResourceException(int code, String friendlyTips, String message) {
        super(code, friendlyTips, message);
    }

    public ResourceException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

    @Override
    protected int getBaseCode() {
        return ServiceResp.ErrorType.ResourceError.code();
    }
}
