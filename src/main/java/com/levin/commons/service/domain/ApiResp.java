package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 服务响应类
 * <p>
 * 可以继承
 *
 * @param <T>
 * @author lilw
 */


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@Schema(description = "服务响应对象")
@FieldNameConstants
//@Builder
public class ApiResp<T>
        extends BaseResp<T>
        implements Serializable {

    private static final long serialVersionUID = -944707546677849710L;

    @Schema(description = "HTTP响应状态码，辅助结果判读")
    protected int httpStatusCode;

    @Schema(description = "友好提示信息，只是提示")
    protected String friendlyTips;

    @Schema(description = "服务编码，由服务端返回，可能是服务节点编码")
    protected String serviceCode;

    @Schema(description = "响应消息的签名验证，防止响应消息被拦截篡改")
    protected String sign;

    public ApiResp(T data) {
        this.data = data;
    }

    public ApiResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResp(int code, String msg, String detailMsg) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
    }

    public static <T> ApiResp<T> error(String msg) {
        return error(-1, msg);
    }

    public static <T> ApiResp<T> error(int code, String msg) {
        return new ApiResp<>(code, msg);
    }

    public static <T> ApiResp<T> ok(T data) {
        return new ApiResp<>(data);
    }
    public static <T> ApiResp<T> ok() {
        return new ApiResp<>();
    }

}
