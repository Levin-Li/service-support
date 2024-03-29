package com.levin.commons.service.domain;

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

@Schema(description = "服务响应")
public interface ServiceResp<T>
        extends Serializable {

    /**
     * 获取响应码，非零表示有错误或异常
     *
     * @return
     */
    int getCode();

    /**
     * 是否成功
     *
     * @return
     */
    @Schema(description = "请求是否成功，等同于code == 0")
    default boolean isSuccessful() {
        return getCode() == 0;
    }

    /**
     * 获取异常类型
     * 如果没有异常则返回null
     *
     * @return
     */
    @Schema(description = "获取异常类型，如果没有异常则返回null")
    default ErrorType getErrorType() {
        return ErrorType.getErrorType(getCode());
    }

    @Schema(description = "错误发生时，是否为业务异常")
    default boolean isBizError() {
        return !isSuccessful() && getCode() < ErrorType.AuthenticationError.baseErrorCode;
    }

    /**
     * 获取信息
     *
     * @return
     */
    String getMsg();

    /**
     * 获取详细信息
     *
     * @return
     */
    String getDetailMsg();

    /**
     * 服务交互
     *
     * @return
     */
    List<Interaction> getInteractions();

    /**
     * 业务数据
     *
     * @return
     */
    T getData();


    @Schema(description = "错误类型")
    enum ErrorType implements EnumDesc {

//    http 错误码 参考
//    1**	信息，服务器收到请求，需要请求者继续执行操作
//    2**	成功，操作被成功接收并处理
//    3**	重定向，需要进一步的操作以完成请求
//    4**	客户端错误，请求包含语法错误或无法完成请求
//    5**	服务器错误，服务器在处理请求的过程中发生了错误

        /**
         * 1-99
         */
        @Schema(description = "业务警告")
        BizWarning(1),

        /**
         * 100 -199
         */
        @Schema(description = "业务异常")
        BizError(100),

        /**
         * 200 - 299
         */
        @Schema(description = "鉴权异常")
        AuthenticationError(200),

        /**
         * 300 - 399
         */
        @Schema(description = "资源异常")
        ResourceError(300),

        /**
         * 400 - 499
         */
        @Schema(description = "系统内部异常")
        SystemInnerError(400),

        /**
         * 500 - n
         */
        @Schema(description = "未知系统异常")
        UnknownError(500);

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
            } else if (errorCode < ResourceError.baseErrorCode) {
                return AuthenticationError;
            } else if (errorCode < SystemInnerError.baseErrorCode) {
                return ResourceError;
            } else if (errorCode < UnknownError.baseErrorCode) {
                return SystemInnerError;
            } else {
                return UnknownError;
            }
        }
    }

}
