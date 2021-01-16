package com.levin.commons.service.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * 服务响应
 *
 * @param <T>
 * @author lilw
 */

@Data
@Accessors(chain = true)
@Desc("服务响应")
@FieldNameConstants
public class ServiceResponse<T>
        implements Serializable {

    private static final long serialVersionUID = -944707546677849710L;

    //错误错误，不为0表示有错误
    @Desc("服务响应码，不为0表示有异常")
    protected int code;

    @Desc("友好提示信息，只是提示")
    protected String friendlyTips;

    @Desc("错误消息，可用于展示")
    protected String msg;

    @Desc("错误消息，不用于展示，用于追查问题")
    protected String detailMsg;

    @Desc(value = "服务编码", detail = "服务编码，由服务端返回，可能是服务节点编码")
    protected String serviceCode;

    @Desc(value = "响应消息的签名验证", detail = "防止响应消息被拦截篡改")
    protected String sign;

    //数据
    @Desc("业务数据")
    protected T data;


    public ServiceResponse() {
    }

    public ServiceResponse(T data) {
        this.data = data;
    }


    public ServiceResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceResponse(int code, String msg, String detailMsg) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
    }


    public boolean isSuccessful() {
        return code == 0;
    }


    public static <T> ServiceResponse<T> error(int code, String msg) {
        return new ServiceResponse<>(code, msg);
    }


    public static <T> ServiceResponse<T> ok(T data) {
        return new ServiceResponse<>(data);
    }


}
