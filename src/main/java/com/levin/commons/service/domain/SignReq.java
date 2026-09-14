package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @author lilw
 */
@Schema(title = "客户端接入请求")
public interface SignReq extends Castable, Serializable {

    @Schema(title = "客户端ID")
    String getAppId();

    @Schema(title = "一次使用临时字符串")
    String getNonce();

    @Schema(title = "时间戳 yyyy-MM-dd hh24:mm:ss")
    String getTimestamp();

    @Schema(title = "签名串")
    String getSignature();

    @Schema(title = "渠道编码")
    String getChannelCode();

    /**
     * 设置签名串
     *
     * @param signature
     * @param <T>
     * @return
     */
    <T extends SignReq> T setSignature(String signature);

}
