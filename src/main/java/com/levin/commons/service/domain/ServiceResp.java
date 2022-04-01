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
        return !isSuccessful() && getCode() < ErrorType.BizError.baseErrorCode;
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
        @Schema(description = "数据访问异常")
        DataAccessError(200),

        /**
         * 300 - 399
         */
        @Schema(description = "系统内部异常")
        SystemInnerError(300),

        /**
         * 400 - 499
         */
        @Schema(description = "网络异常")
        NetworkError(400),

        /**
         * 500 - n
         */
        @Schema(description = "未知异常")
        UnknownError(500);

        /**
         * 基本错误码
         */
        private int baseErrorCode;

        /**
         * @param baseErrorCode
         */
        private ErrorType(int baseErrorCode) {
            this.baseErrorCode = baseErrorCode;
        }

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
            } else if (errorCode < DataAccessError.baseErrorCode) {
                return BizError;
            } else if (errorCode < SystemInnerError.baseErrorCode) {
                return DataAccessError;
            } else if (errorCode < NetworkError.baseErrorCode) {
                return SystemInnerError;
            } else if (errorCode < UnknownError.baseErrorCode) {
                return NetworkError;
            } else {
                return UnknownError;
            }
        }
    }

}
