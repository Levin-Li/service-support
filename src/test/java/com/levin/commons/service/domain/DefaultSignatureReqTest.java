package com.levin.commons.service.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSignatureReqTest {

    @Test
    void builderPreservesAllSignatureRequestFields() {
        SignReq req = DefaultSignatureReq.builder()
                .clientId("client")
                .nonceStr("nonce")
                .timestamp("1700000000")
                .channelCode("api")
                .sign("sign")
                .build();

        assertEquals("client", req.getClientId());
        assertEquals("nonce", req.getNonceStr());
        assertEquals("1700000000", req.getTimestamp());
        assertEquals("api", req.getChannelCode());
        assertEquals("sign", req.getSign());
    }

    @Test
    void signatureCanBeReplacedThroughTheSignReqContract() {
        SignReq req = DefaultSignatureReq.builder()
                .clientId("client")
                .nonceStr("nonce")
                .timestamp("1700000000")
                .channelCode("api")
                .sign("old-sign")
                .build();

        assertSame(req, req.setSign("new-sign"));
        assertEquals("new-sign", req.getSign());
        assertEquals("client", req.getClientId());
        assertEquals("nonce", req.getNonceStr());
        assertEquals("1700000000", req.getTimestamp());
        assertEquals("api", req.getChannelCode());
    }
}
