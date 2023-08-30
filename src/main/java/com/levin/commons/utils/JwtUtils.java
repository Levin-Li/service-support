package com.levin.commons.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.Map;

public abstract class JwtUtils {

    /**
     * 加密
     *
     * @param secureKey
     * @param timeoutSeconds
     * @param data
     * @return
     */
    public static String encodeHMAC256(String secureKey, int timeoutSeconds, Map<String, Object> head, Map<String, String> data) {

        Algorithm algorithm = Algorithm.HMAC256(secureKey);

        JWTCreator.Builder builder = JWT.create();

        if (head != null) {
            builder.withHeader(head);
        }

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                builder.withClaim(entry.getKey(), entry.getValue());
            }
        }

        builder.withExpiresAt(new Date(System.currentTimeMillis() + timeoutSeconds * 1000));

        return builder.sign(algorithm);
    }

    /**
     * 解码验证jwt
     */
    public static DecodedJWT decodeHMAC256(String token, String secureKey) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(secureKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token);

    }


}
