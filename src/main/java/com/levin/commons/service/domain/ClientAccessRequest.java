package com.levin.commons.service.domain;

@Desc("客户端接入请求")
public interface ClientAccessRequest {

    @Desc("客户端ID")
    String getClientId();

    @Desc(value = "时间戳", detail = "yyyy-MM-dd hh:mm:ss")
    String getTimestamp();

    @Desc("渠道编码")
    String getChannelCode();

    @Desc("签名串")
    String getSign();

    @Desc(value = "设置签名", detail = "同时返回签名")
    ClientAccessRequest setSign(String sign);

}
