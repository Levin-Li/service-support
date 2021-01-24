package com.levin.commons.service.exception;

import com.levin.commons.service.domain.Desc;

/**
 * 服务异常，服务方法都应该声明异常
 * <p/>
 * <p/>
 * Created by echo on 2015/11/22.
 * @author lilw
 */
@Desc("服务异常类")
public class ServiceException
        extends RuntimeException {

    private static final long serialVersionUID = -677894470754649710L;

    //异常编码
    @Desc(value = "异常编码")
    int code;

    @Desc(value = "友好提示信息", detail = "可以直接展示给用户看")
    String friendlyTips;

    public ServiceException(int code, String message) {
        this(code, message, null);
    }


    public ServiceException(String message, Throwable cause, int code, String friendlyTips) {
        super(message, cause);
        this.code = code;
        this.friendlyTips = friendlyTips;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }


    public String getFriendlyTips() {
        return friendlyTips;
    }
}
