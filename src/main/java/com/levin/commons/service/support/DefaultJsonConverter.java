package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson2.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;

/**
 * Json 和  对象 转换器
 * <p>
 * 基于Spring 转化器
 */
public class DefaultJsonConverter implements GenericConverter {

    private static Gson gson = new Gson();

    /**
     * @return
     */
    @Override
    @Nullable
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, @NonNull TypeDescriptor targetType) {

        if (source == null) {
            return null;
        }

        boolean isToCharSequence = CharSequence.class.isAssignableFrom(targetType.getType());

        if (isToCharSequence) {

            if (source instanceof CharSequence) {
                return source;
            }

            if (source instanceof JsonElement
                    || source instanceof com.alibaba.fastjson2.JSONObject
                    || source instanceof com.alibaba.fastjson.JSON
                    || source instanceof cn.hutool.json.JSONObject
            ) {
                return source.toString();
            }

            return gson.toJson(source);

        } else if ((source instanceof CharSequence)) {

            if (!StringUtils.hasText((CharSequence) source)) {
                return null;
            }

            ResolvableType rt = targetType.getResolvableType();
            Assert.isTrue(!rt.hasUnresolvableGenerics(), "目标类型中有未识别的泛型：" + rt);

            String json = source.toString();

            //如果没有内容
            if (!StringUtils.hasText(json)) {
                return null;
            }

            Type type = rt.hasGenerics() ? rt.getType() : rt.resolve();
            Assert.notNull(type, "Json转换时目标类型未指定");

            //以下代码主要为优化兼容性
            //去除前后的空格，回车的字符
            json = StringUtils.trimWhitespace(json);

            while (json.length() > 0 && json.charAt(0) == ',') {
                json = StringUtils.trimWhitespace(json.substring(1));
            }

            //循环删除最后一个逗号
            while (json.length() > 0 && json.charAt(json.length() - 1) == ',') {
                json = StringUtils.trimWhitespace(json.substring(0, json.length() - 1));
            }

            //自动补全JSON格式塔

            //数组，集合
            if (rt.isArray()
                    || (rt.resolve() != null && Collection.class.isAssignableFrom(rt.resolve()))) {

                if (json.charAt(0) != '[') {
                    json = "[" + json;
                }
                if (json.charAt(json.length() - 1) != ']') {
                    json = json + "]";
                }
            } else {
                if (json.charAt(0) != '{') {
                    json = "{" + json;
                }
                if (json.charAt(json.length() - 1) != '}') {
                    json = json + "}";
                }
            }

            if (type instanceof Class) {

                if (type == com.alibaba.fastjson2.JSONObject.class) {
                    return com.alibaba.fastjson2.JSONObject.parse(json);
                } else if (com.alibaba.fastjson.JSON.class.isAssignableFrom((Class<?>) type)) {
                    return com.alibaba.fastjson.JSONObject.parseObject(json);
                } else if (cn.hutool.json.JSON.class.isAssignableFrom((Class<?>) type)) {
                    return cn.hutool.json.JSONUtil.parse(json);
                }

            }

            return gson.fromJson(json, type);

        } else if (source instanceof JsonElement) {
            return gson.fromJson((JsonElement) source, targetType.getType());
        }

        return source;
    }


}
