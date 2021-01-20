package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * API 服务请求
 *
 * @author lilw
 */

@Data
@Accessors(chain = true)
@Schema(description = "API请求")
public class ApiServiceReq
        implements ServiceReq {

    @Schema(description = "应用标识")
    String appId;

    @Schema(description = "应用密钥")
    String appSecret;

    @Schema(description = "一次使用临时字符串")
    String nonceStr;

    @Schema(description = "时间串")
    String timeStamp;

    @Schema(description = "渠道编码")
    String channelCode;

    @Schema(description = "签名串")
    String sign;

}
