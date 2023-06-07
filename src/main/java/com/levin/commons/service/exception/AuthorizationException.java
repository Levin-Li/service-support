package com.levin.commons.service.exception;

import com.levin.commons.service.domain.Desc;
import com.levin.commons.service.domain.ServiceResp;
import com.levin.commons.service.exception.AccessDeniedException;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 授权异常
 *
 * @author llw
 */
public class AuthorizationException
        extends AccessDeniedException {

    public AuthorizationException(String friendlyTips) {
        super(friendlyTips);
    }

    public AuthorizationException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public AuthorizationException(int code, String friendlyTips, String message) {
        super(code, friendlyTips, message);
    }

    public AuthorizationException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

    @Override
    protected int getBaseCode() {
        return ServiceResp.ErrorType.AuthenticationError.code();
    }
}
