package com.levin.commons.service.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 服务异常，服务方法都应该声明异常
 * <p/>
 * <p/>
 * Created by echo on 2015/11/22.
 *
 * @author lilw
 */
@Schema(title = "服务异常类")
public class ServiceException
        extends RuntimeException {

    private static final long serialVersionUID = -677894470754649710L;

    //异常编码
    @Schema(title = "异常编码")
    String code;

    @Schema(title = "友好提示信息", description = "可以直接展示给用户看")
    String friendlyTips;

    public ServiceException(String code, String message) {
        this(code, message, null);
    }


    public ServiceException(String message, Throwable cause, String code, String friendlyTips) {
        super(message, cause);
        this.code = code;
        this.friendlyTips = friendlyTips;
    }

    public ServiceException(String code, String message, Throwable cause) {
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
