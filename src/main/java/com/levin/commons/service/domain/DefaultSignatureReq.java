package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

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
@Schema(title = "签名对象")
public class DefaultSignatureReq implements SignReq {

    private static final long serialVersionUID = -944707546677849710L;

    @Schema(title = "请求标识", description = "请求标识，用于跟踪请求，便于调试")
    String traceId = UUID.randomUUID().toString().replace("-", "");

    @Schema(title = "应用标识")
    @NotBlank
    String clientId;

    @Schema(title = "应用密钥")
    @NotBlank
    String clientSecret;

    @Schema(title = "一次使用临时字符串")
    @NotBlank
    String nonceStr;

    @Schema(title = "时间串")
    @NotBlank
    String timestamp;

    @Schema(title = "渠道编码")
    @NotBlank
    String channelCode;

    @Schema(title = "签名串")
    @NotBlank
    String sign;

    @Override
    public final boolean requireSignVerification() {
        return true;
    }

}
