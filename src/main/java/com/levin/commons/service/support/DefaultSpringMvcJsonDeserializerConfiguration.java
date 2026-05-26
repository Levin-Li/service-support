package com.levin.commons.service.support;

import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;
import tools.jackson.databind.DeserializationConfig;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author lilw
 */
@Configuration
@Order
@ConditionalOnProperty(
        name = "com.levin.commons.service.support.DefaultSpringMvcJsonDeserializerConfiguration.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class DefaultSpringMvcJsonDeserializerConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(DefaultSpringMvcJsonDeserializerConfiguration.class);

    @PostConstruct
    public void init() {
        log.info("*** 自定义[Spring mvc 字符串转JSONObject] 配置已经启用，可以使用 " + DefaultSpringMvcJsonDeserializerConfiguration.class.getName() + ".enabled=false 禁用");
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

        log.info("*** 注册[字符串到JSONObject]转换器({}) -> Spring mvc", DefaultSpringMvcJsonDeserializerConfiguration.class.getName());

    }

    @Bean
    @ConditionalOnClass(name = "tools.jackson.databind.json.JsonMapper")
    tools.jackson.databind.JacksonModule string2JsonObjectJacksonModule() {
        return new String2JsonObjectJacksonModule();
    }

    public static class String2JsonObjectJacksonModule extends tools.jackson.databind.JacksonModule {

        @Override
        public String getModuleName() {
            return String2JsonObjectJacksonModule.class.getName();
        }

        @Override
        public tools.jackson.core.Version version() {
            return tools.jackson.core.Version.unknownVersion();
        }

        @Override
        public void setupModule(SetupContext context) {

            context.addDeserializers(new tools.jackson.databind.deser.Deserializers() {

                @Override
                public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {

                    if (valueType == null) {
                        return false;
                    }

                    return Stream.of(JsonElement.class, JSONObject.class, com.alibaba.fastjson.JSONObject.class, Map.class).anyMatch(c -> c.isAssignableFrom(valueType));
                }

                @Override
                public tools.jackson.databind.ValueDeserializer<?> findBeanDeserializer(tools.jackson.databind.JavaType type,
                                                                                        tools.jackson.databind.DeserializationConfig config,
                                                                                        tools.jackson.databind.BeanDescription.Supplier beanDesc) {

                    if (type.isTypeOrSubTypeOf(JsonElement.class)) {
                        return googleJson;
                    } else if (type.isTypeOrSubTypeOf(JSONObject.class)) {
                        return fastJson2;
                    } else if (type.isTypeOrSubTypeOf(com.alibaba.fastjson.JSONObject.class)) {
                        return fastJson1;
                    } else if (type.isTypeOrSubTypeOf(Map.class)) {
                        return fastJson2;
                    }

                    return null;
                }


                final tools.jackson.databind.ValueDeserializer<JsonElement> googleJson = new tools.jackson.databind.ValueDeserializer<JsonElement>() {
                    @Override
                    public JsonElement deserialize(tools.jackson.core.JsonParser p, tools.jackson.databind.DeserializationContext deserializationContext) {

                        if (p == null || p.hasToken(tools.jackson.core.JsonToken.VALUE_NULL) || !p.hasToken(tools.jackson.core.JsonToken.VALUE_STRING)) {
                            return null;
                        }
                        return JsonParser.parseString(p.getString());
                    }
                };

                final tools.jackson.databind.ValueDeserializer<JSONObject> fastJson2 = new tools.jackson.databind.ValueDeserializer<JSONObject>() {
                    @Override
                    public JSONObject deserialize(tools.jackson.core.JsonParser p, tools.jackson.databind.DeserializationContext deserializationContext) {

                        if (p == null || p.hasToken(tools.jackson.core.JsonToken.VALUE_NULL) || !p.hasToken(tools.jackson.core.JsonToken.VALUE_STRING)) {
                            return null;
                        }
                        return JSONObject.parseObject(p.getString());
                    }
                };

                final tools.jackson.databind.ValueDeserializer<com.alibaba.fastjson.JSONObject> fastJson1 = new tools.jackson.databind.ValueDeserializer<com.alibaba.fastjson.JSONObject>() {
                    @Override
                    public com.alibaba.fastjson.JSONObject deserialize(tools.jackson.core.JsonParser p, tools.jackson.databind.DeserializationContext deserializationContext) {

                        if (p == null || p.hasToken(tools.jackson.core.JsonToken.VALUE_NULL) || !p.hasToken(tools.jackson.core.JsonToken.VALUE_STRING)) {
                            return null;
                        }
                        return com.alibaba.fastjson.JSONObject.parseObject(p.getString());
                    }
                };
            });
        }
    }

}
