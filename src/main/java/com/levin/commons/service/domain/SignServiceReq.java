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
public abstract class SignServiceReq
        implements ServiceReq {

    @Schema(description = "应用标识")
    @Ignore
    String appId;

    @Schema(description = "应用密钥")
    @Ignore
    String appSecret;

    @Schema(description = "一次使用临时字符串")
    @Ignore
    String nonceStr;

    @Schema(description = "时间串")
    @Ignore
    String timeStamp;

    @Schema(description = "渠道编码")
    @Ignore
    String channelCode;

    @Schema(description = "签名串")
    @Ignore
    String sign;

    @Override
    public final boolean requireSignVerification() {
        return true;
    }

}
