package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

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
//@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@Schema(description = "服务响应对象")
@FieldNameConstants
//@Builder
public class ApiResp<T>
        extends BaseResp<T>
        implements Serializable {

    private static final long serialVersionUID = -944707546677849710L;

    @Schema(description = "HTTP响应状态码，辅助结果判读")
    protected int httpStatusCode;

    @Schema(description = "服务编码，由服务端返回，可能是服务节点编码")
    protected String serviceCode;

    @Schema(description = "响应消息的签名验证，防止响应消息被拦截篡改")
    protected String sign;

    public ApiResp(T data) {
        this.data = data;
    }

    public ApiResp(int code, String msg) {
        super(code, msg);
    }

    public ApiResp(int code, String msg, String detailMsg) {
        super(code, msg, detailMsg);
    }

    public ApiResp(@NotNull int code, String msg, T data) {
        super(code, msg, data);
    }

    public static <T> ApiResp<T> newResp(int code, String msg, T data) {
        return new ApiResp<>(code, msg, data);
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
