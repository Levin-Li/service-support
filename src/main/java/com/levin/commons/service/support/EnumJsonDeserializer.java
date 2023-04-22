package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.levin.commons.service.domain.EnumDesc;

import java.io.IOException;

/**
 *
 */
public class EnumJsonDeserializer extends JsonDeserializer<Enum> {

    @Override
    public Enum deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {

        JavaType javaType = ctx.getContextualType();

        Assert.isTrue(javaType.isEnumType(), "not a enum type");

        if (p == null || p.hasToken(JsonToken.VALUE_NULL)) {
            return null;
        }

        String value = null;

        if (p.hasToken(JsonToken.VALUE_STRING)) {
            value = p.getText();
        } else if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            value = p.getIntValue() + "";
        }

        return EnumDesc.parse((Class<? extends Enum>) javaType.getRawClass(), value);

    }

}
