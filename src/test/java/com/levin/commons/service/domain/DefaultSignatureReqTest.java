package com.levin.commons.service.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultSignatureReqTest {

    @Test
    void shouldKeepTraceIdDefaultValueWhenBuiltByBuilder() {
        // 业务规则：Builder 构建请求时，traceId 应自动生成默认值。
        DefaultSignatureReq req = DefaultSignatureReq.builder()
                .clientId("client")
                .clientSecret("secret")
                .nonceStr("nonce")
                .timestamp("1700000000")
                .channelCode("api")
                .sign("sign")
                .build();

        Assertions.assertNotNull(req.getTraceId(), "未显式传入 traceId 时应自动生成");
        Assertions.assertFalse(req.getTraceId().trim().isEmpty(), "自动生成的 traceId 不应为空白字符串");
    }

    @Test
    void shouldAllowCustomTraceIdWhenProvided() {
        // 业务规则：显式传入 traceId 时，应保留业务方提供值。
        DefaultSignatureReq req = DefaultSignatureReq.builder()
                .traceId("custom-trace-id")
                .clientId("client")
                .clientSecret("secret")
                .nonceStr("nonce")
                .timestamp("1700000000")
                .channelCode("api")
                .sign("sign")
                .build();

        Assertions.assertEquals("custom-trace-id", req.getTraceId(), "显式传入 traceId 时应原样保留");
        Assertions.assertTrue(req.requireSignVerification(), "签名请求默认应要求验签");
    }
}
