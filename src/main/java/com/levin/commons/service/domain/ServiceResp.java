package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * 服务响应类
 * <p>
 * 可以继承
 *
 * @param <T>
 * @author lilw
 */

@Data
@Accessors(chain = true)
@Schema(description = "服务响应对象")
@FieldNameConstants
@Builder
public class ServiceResp<T>
        implements Serializable {

    private static final long serialVersionUID = -944707546677849710L;

    @Schema(description = "服务响应码，不为0表示有异常")
    protected int code;

    @Schema(description = "HTTP响应状态码，辅助结果判读")
    protected int httpStatusCode;

    @Schema(description = "友好提示信息，只是提示")
    protected String friendlyTips;

    @Schema(description = "信息摘要，可用于界面展示")
    protected String msg;

    @Schema(description = "详情信息，不用于展示，用于追查问题")
    protected String detailMsg;

    @Schema(description = "服务编码，由服务端返回，可能是服务节点编码")
    protected String serviceCode;

    @Schema(description = "响应消息的签名验证，防止响应消息被拦截篡改")
    protected String sign;

    //数据
    @Schema(description = "业务数据")
    protected T data;


    public ServiceResp() {
    }

    public ServiceResp(T data) {
        this.data = data;
    }


    public ServiceResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceResp(int code, String msg, String detailMsg) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
    }


    public boolean isSuccessful() {
        return code == 0;
    }


    public static <T> ServiceResp<T> error(int code, String msg) {
        return new ServiceResp<>(code, msg);
    }


    public static <T> ServiceResp<T> ok(T data) {
        return new ServiceResp<>(data);
    }

}
