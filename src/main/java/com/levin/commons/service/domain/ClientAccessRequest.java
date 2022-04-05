package com.levin.commons.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Desc("客户端接入请求")
public interface ClientAccessRequest {

    @Schema(description = "客户端ID")
    String getClientId();

    @Schema(description = "时间戳 yyyy-MM-dd hh:mm:ss")
    String getTimestamp();

    @Schema(description = "渠道编码")
    String getChannelCode();

    @Schema(description = "签名串")
    String getSign();

    ClientAccessRequest setSign(String sign);

}
