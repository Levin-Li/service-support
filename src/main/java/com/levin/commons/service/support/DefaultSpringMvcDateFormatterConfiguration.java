package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.format.DefaultDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;
import tools.jackson.databind.DeserializationConfig;

import java.text.ParseException;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author lilw
 */
@Configuration
@Order
@ConditionalOnProperty(
        name = "com.levin.commons.service.support.DefaultSpringMvcDateFormatterConfiguration.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class DefaultSpringMvcDateFormatterConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(DefaultSpringMvcDateFormatterConfiguration.class);

    private static final DefaultDateFormat dateFormat = new DefaultDateFormat();

    @PostConstruct
    public void init() {
        log.info("***自定义[Spring MVC 日期值转换]配置已经启用，可以使用 " + DefaultSpringMvcDateFormatterConfiguration.class.getName() + ".enabled=false 禁用");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        //移除默认的转换器
        registry.removeConvertible(String.class, Date.class);
        registry.removeConvertible(Date.class, String.class);

        //Spring MVC默认的日期转换器，请求参数转换为日期类型

        registry.addFormatter(dateFormat);

        log.info("*** 注册日期值转换器({}) -> Spring mvc", DefaultDateFormat.class.getName());

    }

    @Bean
    @ConditionalOnClass(name = "tools.jackson.databind.json.JsonMapper")
    tools.jackson.databind.JacksonModule dateJacksonModule() {
        return new DateJacksonModule();
    }

    public static class DateJacksonModule extends tools.jackson.databind.JacksonModule {

        @Override
        public String getModuleName() {
            return DateJacksonModule.class.getName();
        }

        @Override
        public tools.jackson.core.Version version() {
            return tools.jackson.core.Version.unknownVersion();
        }

        @Override
        public void setupModule(SetupContext context) {
            context.addDeserializers(new DateJacksonDeserializers());
        }
    }

    static class DateJacksonDeserializers implements tools.jackson.databind.deser.Deserializers {

        @Override
        public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {

            if (valueType == null) {
                return false;
            }

            return Stream.of(Date.class) .anyMatch(c -> c.isAssignableFrom(valueType));
        }

        @Override
        public tools.jackson.databind.ValueDeserializer<?> findBeanDeserializer(tools.jackson.databind.JavaType type,
                                                                                tools.jackson.databind.DeserializationConfig config,
                                                                                tools.jackson.databind.BeanDescription.Supplier beanDesc) {

            if (!type.isTypeOrSubTypeOf(Date.class)) {
                return null;
            }

            return new DateJacksonDeserializer<>((Class<? extends Date>) type.getRawClass());
        }
    }

    static class DateJacksonDeserializer<T extends Date> extends tools.jackson.databind.ValueDeserializer<T> {

        private final Class<? extends Date> type;

        DateJacksonDeserializer(Class<? extends Date> type) {
            this.type = type;
        }

        @Override
        public T deserialize(tools.jackson.core.JsonParser p, tools.jackson.databind.DeserializationContext ctx) {

            Class<? extends Date> realType = type;

            if (realType == null) {
                tools.jackson.databind.JavaType javaType = ctx.getContextualType();
                Assert.isTrue(javaType.isTypeOrSubTypeOf(Date.class), "not a date type");
                realType = (Class<? extends Date>) javaType.getRawClass();
            }

            if (p == null || p.hasToken(tools.jackson.core.JsonToken.VALUE_NULL)) {
                return null;
            }

            Object value = null;

            if (p.hasToken(tools.jackson.core.JsonToken.VALUE_STRING)) {
                value = p.getString();
            } else if (p.hasToken(tools.jackson.core.JsonToken.VALUE_NUMBER_INT)) {
                value = p.getLongValue();
            }

            Date date = BeanUtils.instantiateClass(realType);

            if (value == null) {
                return null;
            } else if (value instanceof CharSequence && StringUtils.hasText(value.toString())) {
                try {
                    date.setTime(dateFormat.parse(value.toString()).getTime());
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            } else if (value instanceof Number) {
                date.setTime(((Number) value).longValue());
            } else if (value instanceof Date) {
                date.setTime(((Date) value).getTime());
            }

            return (T) date;
        }
    }

}
