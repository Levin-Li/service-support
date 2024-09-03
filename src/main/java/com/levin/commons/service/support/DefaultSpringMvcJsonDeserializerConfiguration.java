package com.levin.commons.service.support;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.levin.commons.conditional.ConditionalOn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j

@ConditionalOn(action = ConditionalOn.Action.OnProperty, value = "com.levin.commons.service.support.DefaultSpringMvcJsonDeserializerConfiguration!=disable")
public class DefaultSpringMvcJsonDeserializerConfiguration implements WebMvcConfigurer {

    @PostConstruct
    public void init() {
        log.info("*** Spring mvc 枚举值转换配置已经启用，可以使用 " + DefaultSpringMvcJsonDeserializerConfiguration.class.getName() + "=disable 禁用");
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {

        //移除默认的转换器
        registry.removeConvertible(String.class, JSONObject.class);
        registry.removeConvertible(String.class, com.alibaba.fastjson.JSONObject.class);
        registry.removeConvertible(String.class, com.google.gson.JsonElement.class);


        registry.addConverter(String.class, JSONObject.class, JSONObject::parseObject);
        registry.addConverter(String.class, com.alibaba.fastjson.JSONObject.class, com.alibaba.fastjson.JSONObject::parseObject);
        registry.addConverter(String.class, JsonElement.class, JsonParser::parseString);

        //字符串到Map的转换
        registry.addConverter(String.class, Map.class, JSONObject::parse);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper o = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                o.registerModule(new String2JsonObjectModule());
            }
        }
    }

    static class String2JsonObjectModule extends Module {

        @Override
        public String getModuleName() {
            return "String2JsonObjectModule";
        }

        @Override
        public Version version() {
            return Version.unknownVersion();
        }

        @Override
        public void setupModule(SetupContext context) {
            context.addDeserializers(new Deserializers.Base() {
                final JsonDeserializer<JsonElement> googleJson = new JsonDeserializer<JsonElement>() {
                    @Override
                    public JsonElement deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext deserializationContext) throws IOException {

                        if (p == null || p.hasToken(JsonToken.VALUE_NULL) || !p.hasToken(JsonToken.VALUE_STRING)) {
                            return null;
                        }
                        return JsonParser.parseString(p.getText());
                    }
                };

                final JsonDeserializer<JSONObject> fastJson2 = new JsonDeserializer<JSONObject>() {
                    @Override
                    public JSONObject deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext deserializationContext) throws IOException {

                        if (p == null || p.hasToken(JsonToken.VALUE_NULL) || !p.hasToken(JsonToken.VALUE_STRING)) {
                            return null;
                        }
                        return JSONObject.parseObject(p.getText());
                    }
                };

                final JsonDeserializer<com.alibaba.fastjson.JSONObject> fastJson1 = new JsonDeserializer<com.alibaba.fastjson.JSONObject>() {
                    @Override
                    public com.alibaba.fastjson.JSONObject deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext deserializationContext) throws IOException {

                        if (p == null || p.hasToken(JsonToken.VALUE_NULL) || !p.hasToken(JsonToken.VALUE_STRING)) {
                            return null;
                        }
                        return com.alibaba.fastjson.JSONObject.parseObject(p.getText());
                    }
                };

                @Override
                public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {

                    //如果不是字符串类型，则不进行转换
                    if (type.isTypeOrSubTypeOf(JsonElement.class)) {
                        return googleJson;
                    } else if (type.isTypeOrSubTypeOf(JSONObject.class)
                            || type.isTypeOrSubTypeOf(Map.class)) {
                        return fastJson2;
                    } else if (type.isTypeOrSubTypeOf(com.alibaba.fastjson.JSONObject.class)) {
                        return fastJson1;
                    }
                    return null;
                }
            });
        }
    }


}
