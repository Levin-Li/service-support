package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.levin.commons.conditional.ConditionalOn;
import com.levin.commons.format.DefaultDateFormat;
import com.levin.commons.service.domain.EnumDesc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
@Order
@ConditionalOn(action = ConditionalOn.Action.OnProperty, value = "com.levin.commons.service.support.DefaultSpringMvcDateFormatterConfiguration!=disable")
public class DefaultSpringMvcDateFormatterConfiguration implements WebMvcConfigurer {

    private static final DefaultDateFormat dateFormat = new DefaultDateFormat();

    @PostConstruct
    public void init() {
        log.info("*** Spring MVC 日期值转换配置已经启用，可以使用 " + DefaultSpringMvcDateFormatterConfiguration.class.getName() + "=disable 禁用");
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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // spring boot 已经支持配置 json 字段序列化、反序列化

//        for (HttpMessageConverter<?> converter : converters) {
//            if (converter instanceof MappingJackson2HttpMessageConverter) {
//                ObjectMapper o = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
//                o.registerModule(new DateModule());
//            }
//        }
    }

    /**
     * 反序列器
     */
    @AllArgsConstructor
    static class DateJsonDeserializer<T extends Date> extends JsonDeserializer<T> {

        Class<? extends Date> type = null;

        @Override
        public T deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {

            Class<? extends Date> realType = type;

            if (realType == null) {
                JavaType javaType = ctx.getContextualType();
                Assert.isTrue(javaType.isTypeOrSubTypeOf(Date.class), "not a date type");
                realType = (Class<? extends Date>) javaType.getRawClass();
            }

            if (p == null || p.hasToken(JsonToken.VALUE_NULL)) {
                return null;
            }

            Object value = null;

            if (p.hasToken(JsonToken.VALUE_STRING)) {
                value = p.getText();
            } else if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                value = p.getLongValue();
            }

            Date date = BeanUtils.instantiateClass(realType);

            if (value == null) {
                return null;
            } else if (value instanceof CharSequence && StringUtils.hasText(value.toString())) {
                try {
                    date.setTime(dateFormat.parse(value.toString()).getTime());
                } catch (ParseException e) {
                    throw new IOException(e);
                }
            } else if (value instanceof Number) {
                date.setTime(((Number) value).longValue());
            } else if (value instanceof Date) {
                date.setTime(((Date) value).getTime());
            }

            return (T) date;
        }

    }

    /**
     * 查找器
     */
    static class DateDeserializers extends Deserializers.Base {
        @Override
        public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {

            if (!type.isTypeOrSubTypeOf(Date.class)) {
                return null;
            }

            return new DateJsonDeserializer(type.getRawClass());
        }
    }

    /**
     * 模块
     */
    static class DateModule extends Module {
        @Override
        public String getModuleName() {
            return "DateModule";
        }

        @Override
        public Version version() {
            return Version.unknownVersion();
        }

        @Override
        public void setupModule(SetupContext context) {
            context.addDeserializers(new DateDeserializers());
        }
    }

}
