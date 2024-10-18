package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;
import java.util.List;

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
//@Builder
@Data
@Accessors(chain = true)
@Schema(title = "服务响应对象")
@FieldNameConstants
//@Builder
public class BaseResp<T>
        implements ServiceResp<T> {

    private static final long serialVersionUID = -944707546677849710L;

//    http 错误码参数
//    1**	信息，服务器收到请求，需要请求者继续执行操作
//    2**	成功，操作被成功接收并处理
//    3**	重定向，需要进一步的操作以完成请求
//    4**	客户端错误，请求包含语法错误或无法完成请求
//    5**	服务器错误，服务器在处理请求的过程中发生了错误

    @Schema(title = "服务响应码", description = "服务响应码，不为0表示有异常，一般情况数字越大表示错误等级越高")
    @NotNull
    protected int code = 0;

    @Schema(title = "追踪标识", description = "请求唯一标识，用于链路追踪")
    protected String traceId;

    @Schema(title = "信息摘要", description = "信息摘要，可用于界面展示")
    protected String msg;

    @Schema(title = "详情信息", description = "详情信息，不用于展示，用于追查问题")
    protected String detailMsg;

    //数据
    @Schema(title = "业务数据")
    protected T data;

    @Schema(title = "服务提示-用于互动")
    protected List<Interaction> interactions;

    public BaseResp(T data) {
        this.data = data;
    }

    public BaseResp(@NotNull int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResp(@NotNull int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResp(@NotNull int code, String msg, String detailMsg) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
    }

    @Schema(title = "服务响应码", description = "服务响应码，等同于code，不为0表示有异常，特别增加为了兼容部分平台或是框架的API规范，如百度amis API")
    public int getStatus() {
        return code;
    }

    public static <T> BaseResp<T> error(String msg) {
        return error(ErrorType.BizError.getBaseErrorCode(), msg);
    }

    public static <T> BaseResp<T> error(int code, String msg) {
        return new BaseResp<>(code, msg);
    }

    public static <T> BaseResp<T> ok(T data) {
        return new BaseResp<>(data);
    }

    public static <T> BaseResp<T> ok() {
        return new BaseResp<>();
    }

}
