package com.levin.commons.service.exception;

import com.levin.commons.service.domain.Desc;
import com.levin.commons.service.exception.AccessDeniedException;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 授权异常
 *
 * @author llw
 */
public class AuthorizationException
        extends AccessDeniedException {

    public AuthorizationException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public AuthorizationException(int code, String message, String friendlyTips) {
        super(code, message, friendlyTips);
    }

    public AuthorizationException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }
}
