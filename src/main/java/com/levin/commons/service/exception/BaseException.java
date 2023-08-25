package com.levin.commons.service.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;


@Schema(title = "基本运行异常")
public abstract class BaseException
        extends RuntimeException {

    private static final long serialVersionUID = -677894470754649710L;

    @Schema(title = "错误码，不为0表示有异常，一般情况数字越大表示错误等级越高")
    @NotNull
    protected int code = -1;

    @Schema(title = "友好提示信息，可用于界面展示")
    protected String friendlyTips;

    public BaseException(String friendlyTips) {
        this(0, friendlyTips);
    }

    public BaseException(int code, String friendlyTips) {
        this(code, friendlyTips, friendlyTips);
    }

    public BaseException(int code, String friendlyTips, String message) {
        this(code, friendlyTips, message, null);
    }

    public BaseException(int code, String friendlyTips, String message, Throwable cause) {

        super(message != null ? message : friendlyTips, cause);

        //重新计算错误码
        code = calculateCode(code);

        //不允许 code 等于 0
        this.code = code != 0 ? code : -1;
        this.friendlyTips = friendlyTips;

    }

    /**
     * 基础错误码
     *
     * @return
     */
    protected abstract int getBaseCode();

    /**
     * 获取错误码
     *
     * @param subCode
     * @return
     */
    protected int calculateCode(int subCode) {
        return subCode >= getBaseCode() ? subCode : (getBaseCode() + subCode);
    }

    /**
     * 异常错误码
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 友好提示
     *
     * @return
     */
    public String getFriendlyTips() {
        return friendlyTips;
    }
}
