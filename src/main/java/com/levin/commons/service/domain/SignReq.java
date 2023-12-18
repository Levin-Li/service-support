package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Desc("客户端接入请求")
public interface SignReq extends ServiceReq {

    @Schema(title = "客户端ID")
    String getClientId();

    @Schema(title = "客户端密钥")
    String getClientSecret();

    @Schema(title = "一次使用临时字符串")
    String getNonceStr();

    @Schema(title = "时间戳 yyyy-MM-dd hh24:mm:ss")
    String getTimestamp();

    @Schema(title = "渠道编码")
    String getChannelCode();

    @Schema(title = "签名串")
    String getSign();

    /**
     * 设置签名串
     *
     * @param sign
     * @param <T>
     * @return
     */
    <T extends SignReq> T setSign(String sign);

}
