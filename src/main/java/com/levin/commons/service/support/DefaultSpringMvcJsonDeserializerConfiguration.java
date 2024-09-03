package com.levin.commons.service.support;

import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.levin.commons.conditional.ConditionalOn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
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


        registry.addConverter(String.class, JSONObject.class, JSONObject::parse);
        registry.addConverter(String.class, com.alibaba.fastjson.JSONObject.class, com.alibaba.fastjson.JSONObject::parseObject);
        registry.addConverter(String.class, JsonElement.class, JsonParser::parseString);

        //字符串到Map的转换
        registry.addConverter(String.class, Map.class, JSONObject::parse);
    }

}
