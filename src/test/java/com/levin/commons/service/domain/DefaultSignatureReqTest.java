package com.levin.commons.service.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSignatureReqTest {

    @Test
    void builderPreservesAllSignatureRequestFields() {
        SignReq req = DefaultSignatureReq.builder()
                .appId("client")
                .nonce("nonce")
                .timestamp("1700000000")
                .channelCode("api")
                .signature("sign")
                .build();

        assertEquals("client", req.getAppId());
        assertEquals("nonce", req.getNonce());
        assertEquals("1700000000", req.getTimestamp());
        assertEquals("api", req.getChannelCode());
        assertEquals("sign", req.getSignature());
    }

    @Test
    void signatureCanBeReplacedThroughTheSignReqContract() {
        SignReq req = DefaultSignatureReq.builder()
                .appId("client")
                .nonce("nonce")
                .timestamp("1700000000")
                .channelCode("api")
                .signature("old-sign")
                .build();

        assertSame(req, req.setSignature("new-sign"));
        assertEquals("new-sign", req.getSignature());
        assertEquals("client", req.getAppId());
        assertEquals("nonce", req.getNonce());
        assertEquals("1700000000", req.getTimestamp());
        assertEquals("api", req.getChannelCode());
    }
}
