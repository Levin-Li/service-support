package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

/**
 * 服务响应
 * <p>
 * 可以继承
 *
 * @param <T>
 * @author lilw
 */

@Schema(title = "服务响应")
public interface ServiceResp<T>
        extends Traceable, Serializable {

    @Schema(title = "响应码", description = "响应码，非零表示有错误或异常")
    int getCode();

    @Schema(title = "请求是否成功", description = "请求是否成功，等同于code == 0")
    default boolean isSuccessful() {
        return getCode() == 0;
    }

    @Schema(title = "可展示信息", description = "一般是错误信息")
    String getMsg();

    @Schema(title = "详细信息", description = "错误详细信息，如：堆栈信息")
    String getDetailMsg();

    @Operation(summary = "交互信息", description = "交互信息，如：跳转地址，重定向地址等")
    List<Interaction> getInteractions();

    @Schema(title = "业务数据")
    T getData();

    @Schema(title = "异常类型", description = "异常类型，如果没有异常则返回null")
    default ErrorType getErrorType() {
        return ErrorType.getErrorType(getCode());
    }

    @Schema(title = "是否业务异常", description = "错误发生时，是否为业务异常")
    default boolean isBizError() {
        return !isSuccessful() && getCode() < ErrorType.AuthenticationError.baseErrorCode;
    }


    @Operation(summary = "类型强转")
    default <E extends ServiceResp<T>> E cast() {
        return (E) this;
    }

    @Schema(title = "错误类型")
    enum ErrorType implements EnumDesc {

//    http 错误码 参考
//    1**	信息，服务器收到请求，需要请求者继续执行操作
//    2**	成功，操作被成功接收并处理
//    3**	重定向，需要进一步的操作以完成请求
//    4**	客户端错误，请求包含语法错误或无法完成请求
//    5**	服务器错误，服务器在处理请求的过程中发生了错误


        @Schema(title = "业务警告")
        BizWarning(1),

        @Schema(title = "业务异常")
        BizError(10000),

        @Schema(title = "认证异常")
        AuthenticationError(20000),

        @Schema(title = "鉴权异常")
        AuthorizationError(25000),

        @Schema(title = "资源异常")
        ResourceError(30000),

        @Schema(title = "系统内部异常")
        SystemInnerError(40000),

        @Schema(title = "未知系统异常")
        UnknownError(50000);


        /**
         * 基本错误码
         */
        private int baseErrorCode;

        /**
         * @param baseErrorCode
         */
        ErrorType(int baseErrorCode) {
            this.baseErrorCode = baseErrorCode;
        }

        /**
         * 错误起始码
         *
         * @return
         */
        public int getBaseErrorCode() {
            return baseErrorCode;
        }

        /**
         * 根据范围获取异常类型
         *
         * @param errorCode
         * @return
         */
        public static ErrorType getErrorType(int errorCode) {
            if (errorCode == 0) {
                return null;
            } else if (errorCode < BizError.baseErrorCode) {
                return BizWarning;
            } else if (errorCode < AuthenticationError.baseErrorCode) {
                return BizError;
            } else if (errorCode < AuthorizationError.baseErrorCode) {
                return AuthenticationError;
            } else if (errorCode < ResourceError.baseErrorCode) {
                return AuthorizationError;
            } else if (errorCode < SystemInnerError.baseErrorCode) {
                return ResourceError;
            } else if (errorCode < UnknownError.baseErrorCode) {
                return SystemInnerError;
            } else {
                return UnknownError;
            }
        }

        @Override
        public int code() {
            return baseErrorCode;
        }

        @Override
        public String toString() {
            return codeAndNameAndDesc();
        }
    }

}
