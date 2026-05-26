package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonElement;
import com.levin.commons.service.domain.EnumDesc;
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
        name = "com.levin.commons.service.support.DefaultSpringMvcEnumFormatterConfiguration.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class DefaultSpringMvcEnumFormatterConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(DefaultSpringMvcEnumFormatterConfiguration.class);

    @PostConstruct
    public void init() {
        log.info("*** 自定义[Spring mvc 枚举值转换]配置已经启用，可以使用 " + DefaultSpringMvcEnumFormatterConfiguration.class.getName() + ".enabled=false 禁用");
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

        log.info("*** 注册枚举值转换器({}) -> Spring mvc", EnumDesc.class.getName());
    }

    @Bean
    @ConditionalOnClass(name = "tools.jackson.databind.json.JsonMapper")
    tools.jackson.databind.JacksonModule enumJacksonModule() {
        return new EnumJacksonModule();
    }

    public static class EnumJacksonModule extends tools.jackson.databind.JacksonModule {

        @Override
        public String getModuleName() {
            return EnumJacksonModule.class.getName();
        }

        @Override
        public tools.jackson.core.Version version() {
            return tools.jackson.core.Version.unknownVersion();
        }

        @Override
        public void setupModule(SetupContext context) {
            context.addDeserializers(new EnumJacksonDeserializers());
        }
    }

    static class EnumJacksonDeserializers implements tools.jackson.databind.deser.Deserializers  {

        @Override
        public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {

            if (valueType == null) {
                return false;
            }

            return Stream.of(Enum.class) .anyMatch(c -> c.isAssignableFrom(valueType));
        }

        @Override
        public tools.jackson.databind.ValueDeserializer<?> findEnumDeserializer(tools.jackson.databind.JavaType type,
                                                                                tools.jackson.databind.DeserializationConfig config,
                                                                                tools.jackson.databind.BeanDescription.Supplier beanDesc) {

            if (!type.isEnumType() || !EnumDesc.class.isAssignableFrom(type.getRawClass())) {
                return null;
            }

            return new EnumJacksonDeserializer((Class<Enum<?>>) type.getRawClass());
        }
    }

    static class EnumJacksonDeserializer extends tools.jackson.databind.ValueDeserializer<Enum<?>> {

        private final Class<Enum<?>> type;

        EnumJacksonDeserializer(Class<Enum<?>> type) {
            this.type = type;
        }

        @Override
        public Enum<?> deserialize(tools.jackson.core.JsonParser p, tools.jackson.databind.DeserializationContext ctx) {

            Class<Enum<?>> realType = type;

            if (realType == null) {
                tools.jackson.databind.JavaType javaType = ctx.getContextualType();
                Assert.isTrue(javaType.isEnumType(), "not a enum type");
                realType = (Class<Enum<?>>) javaType.getRawClass();
            }

            if (p == null || p.hasToken(tools.jackson.core.JsonToken.VALUE_NULL)) {
                return null;
            }

            Object value = null;

            if (p.hasToken(tools.jackson.core.JsonToken.VALUE_STRING)) {
                value = p.getString();
            } else if (p.hasToken(tools.jackson.core.JsonToken.VALUE_NUMBER_INT)) {
                value = p.getIntValue();
            }

            return EnumDesc.parse(realType, value);
        }
    }
}
