package com.levin.commons.service.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;


@Schema(title = "基本运行异常")
public abstract class BaseException
        extends RuntimeException {

    private static final long serialVersionUID = -677894470754649710L;

    @Schema(description = "错误码，不为0表示有异常，一般情况数字越大表示错误等级越高")
    @NotNull
    protected int code = -1;

    @Schema(description = "友好提示信息，可用于界面展示")
    protected String friendlyTips;

    public BaseException(int code, String friendlyTips) {
        this(code, friendlyTips, friendlyTips);
    }

    public BaseException(int code, String message, String friendlyTips) {
        this(code, friendlyTips, message, null);
    }

    public BaseException(int code, String friendlyTips, String message, Throwable cause) {
        super(message != null ? message : friendlyTips, cause);
        this.code = code;
        this.friendlyTips = friendlyTips;
    }

    public int getCode() {
        return code;
    }


    public String getFriendlyTips() {
        return friendlyTips;
    }
}
