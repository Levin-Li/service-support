package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * API 签名请求
 * <p>
 * 建议独立使用
 *
 * @author lilw
 */

@Data
@Accessors(chain = true)
@Schema(description = "API请求")
public class SignServiceReq
        implements ServiceReq {

    @Schema(description = "应用标识")
    @Ignore
    @NotNull
    String appId;

    @Schema(description = "应用密钥")
    @Ignore
    @NotNull
    String appSecret;

    @Schema(description = "一次使用临时字符串")
    @Ignore
    @NotNull
    String nonceStr;

    @Schema(description = "时间串")
    @Ignore
    @NotNull
    String timeStamp;

    @Schema(description = "渠道编码")
    @Ignore
    @NotNull
    String channelCode;

    @Schema(description = "签名串")
    @Ignore
    @NotNull
    String sign;

    @Override
    public final boolean requireSignVerification() {
        return true;
    }

}
