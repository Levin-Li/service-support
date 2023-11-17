package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.levin.commons.conditional.ConditionalOn;
import com.levin.commons.conditional.ConditionalOnList;
import com.levin.commons.service.domain.EnumDesc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Configuration
@Slf4j

@ConditionalOnList({
        @ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = EnumJacksonConfiguration.class),
        @ConditionalOn(action = ConditionalOn.Action.OnProperty, value = "com.levin.commons.service.support.EnumJacksonConfiguration!=disable")
})
public class EnumJacksonConfiguration implements WebMvcConfigurer {

    @PostConstruct
    public void init() {
        log.info("*** Spring mvc 枚举值转换配置已经启用，可以使用 " + EnumJacksonConfiguration.class.getName() + "=disable 禁用");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        //移除默认的转换器
        registry.removeConvertible(String.class, Enum.class);
        registry.removeConvertible(Number.class, Enum.class);
        registry.removeConvertible(int.class, Enum.class);
        registry.removeConvertible(Integer.class, Enum.class);

        registry.addConverterFactory(EnumDesc.string2EnumFactory);
        registry.addConverterFactory(EnumDesc.number2EnumFactory);

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper o = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                o.registerModule(new EnumModule());
            }
        }
    }

    /**
     * 反序列器
     */
    @AllArgsConstructor
    static class EnumJsonDeserializer extends JsonDeserializer<Enum<?>> {

        Class<Enum<?>> type = null;

        @Override
        public Enum<?> deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {

            Class<Enum<?>> realType = type;

            if (realType == null) {
                JavaType javaType = ctx.getContextualType();
                Assert.isTrue(javaType.isEnumType(), "not a enum type");
                realType = (Class<Enum<?>>) javaType.getRawClass();
            }

            if (p == null || p.hasToken(JsonToken.VALUE_NULL)) {
                return null;
            }

            Object value = null;

            if (p.hasToken(JsonToken.VALUE_STRING)) {
                value = p.getText();
            } else if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                value = p.getIntValue();
            }

            return EnumDesc.parse(realType, value);

        }

    }

    /**
     * 查找器
     */
    static class EnumDeserializers extends Deserializers.Base {
        @Override
        public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) {

            if (!type.isEnum() || !EnumDesc.class.isAssignableFrom(type)) {
                return null;
            }

            return new EnumJsonDeserializer((Class<Enum<?>>) type);
        }
    }

    /**
     * 模块
     */
    static class EnumModule extends Module {

        @Override
        public String getModuleName() {
            return "EnumModule";
        }

        @Override
        public Version version() {
            return Version.unknownVersion();
        }

        @Override
        public void setupModule(SetupContext context) {
            context.addDeserializers(new EnumDeserializers());
        }
    }
}
