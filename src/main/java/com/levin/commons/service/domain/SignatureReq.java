package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;

/**
 * API 签名对象
 * <p>
 * 建议独立使用
 *
 * @author lilw
 */

@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@Schema(description = "签名对象")
public class SignatureReq
        implements ServiceReq {

    private static final long serialVersionUID = -944707546677849710L;

    @Schema(description = "应用标识")
    @Ignore
    @NotBlank
    String appId;

    @Schema(description = "应用密钥")
    @Ignore
    @NotBlank
    String appSecret;

    @Schema(description = "一次使用临时字符串")
    @Ignore
    @NotBlank
    String nonceStr;

    @Schema(description = "时间串")
    @Ignore
    @NotBlank
    String timeStamp;

    @Schema(description = "渠道编码")
    @Ignore
    @NotBlank
    String channelCode;

    @Schema(description = "签名串")
    @Ignore
    @NotBlank
    String sign;

    @Override
    public final boolean requireSignVerification() {
        return true;
    }

}
