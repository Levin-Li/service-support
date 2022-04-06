package com.levin.commons.rbac;

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

    @Schema(description = "异常编码")
    String code;

    @Schema(description = "友好提示信息", title = "可以直接展示给用户看")
    String friendlyTips;

    public AuthorizationException(String code, String message) {
        this(code, message, null);
    }

    public AuthorizationException(String message, Throwable cause, String code, String friendlyTips) {
        super(message, cause);
        this.code = code;
        this.friendlyTips = friendlyTips;
    }

    public AuthorizationException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getFriendlyTips() {
        return friendlyTips;
    }

}
