package com.levin.commons.service.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.levin.commons.service.support.ValueHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取 Schema 注解的名称描述
 */
public interface EnumDesc {

    /**
     *
     */
    Map<Enum, String> cacheNames = new ConcurrentHashMap<>();

    /**
     * 已枚举索引为key 获取枚举描述
     * 枚举索引 从 0 开始
     *
     * @param type
     * @return
     */
    static Map<Integer, String> getDescMapByIndex(Class<? extends Enum> type) {

        if (!type.isEnum()) {
            throw new IllegalArgumentException(type + "  is not a enum type");
        }

        Map<Integer, String> desc = new LinkedHashMap<>();

        Arrays.stream(type.getEnumConstants())
                .forEachOrdered(anEnum -> {
                            desc.put(anEnum.ordinal(), (anEnum instanceof EnumDesc) ? ((EnumDesc) anEnum).getDesc() : getDesc(anEnum));
                        }
                );

        return desc;
    }

    /**
     * 已枚举名称为key 获取枚举描述
     *
     * @param type
     * @return
     */
    static Map<String, String> getDescMapByName(Class<? extends Enum> type) {

        if (!type.isEnum()) {
            throw new IllegalArgumentException(type + "  is not a enum type");
        }

        Map<String, String> desc = new LinkedHashMap<>();

        Arrays.stream(type.getEnumConstants())
                .forEachOrdered(anEnum -> {
                            desc.put(anEnum.name(), (anEnum instanceof EnumDesc) ? ((EnumDesc) anEnum).getDesc() : getDesc(anEnum));
                        }
                );

        return desc;
    }

    /**
     * 放回名称
     *
     * @return
     */
    String name();

    /**
     * 放回编码
     *
     * @return
     */
    default int code() {
        return ((Enum<?>) this).ordinal();
    }

    /**
     * 替代toString
     *
     * @return
     */
    default String enumToString() {
        return code() + "-" + getDesc();
    }

    /**
     * 替代toString
     *
     * @return
     */
    default String nameAndDesc() {
        return name() + "-" + getDesc();
    }

    /**
     * 替代toString
     *
     * @return
     */
    default String codeAndNameAndDesc() {
        return code() + "-" + name() + "-" + getDesc();
    }

    /**
     * 解析出枚举值
     *
     * @param type
     * @param code
     * @return
     */
    @JsonCreator
    static Enum<?> parse(Class<? extends Enum> type, Integer code) {

        if (code == null) return null;

        for (Enum<?> value : type.getEnumConstants()) {
            if (code == ((value instanceof EnumDesc)
                    ? ((EnumDesc) value).code() : value.ordinal())) {
                return value;
            }
        }

        throw new IllegalArgumentException(type.getName() + " can't found enum by code " + code);
    }

    /**
     * 解析出枚举值
     *
     * @param type
     * @param nameOrCode
     * @return
     */
    @JsonCreator
    static Enum<?> parse(Class<? extends Enum> type, String nameOrCode) {

        if (nameOrCode == null || nameOrCode.trim().length() == 0) return null;

        nameOrCode = nameOrCode.trim();

        for (Enum<?> value : type.getEnumConstants()) {
            if (nameOrCode.equals(value.name())) {
                return value;
            }
        }

        //如果没找着，转换成编码，查找
        try {
            return parse(type, Integer.parseInt(nameOrCode));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(type.getName() + " can't found enum by name " + nameOrCode);
        }

    }

    /**
     * 获取描述
     * 该方法可以覆盖
     *
     * @return
     */
    default String getDesc() {
        return getDesc((Enum<?>) this);
    }

    /**
     * 获取 Schema 注解描述
     *
     * @param anEnum
     * @return
     */
    static String getDesc(Enum<?> anEnum) {

        if (anEnum == null) {
            return null;
        }

        String info = cacheNames.get(anEnum);

        if (info != null) {
            return info;
        }

        synchronized (anEnum) {

            Schema schema = null;

            try {
                schema = anEnum.getDeclaringClass().getField(anEnum.name()).getAnnotation(Schema.class);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            if (schema != null) {

                info = schema.description();

                if (info == null || info.trim().length() == 0) {
                    info = schema.title();
                }
            }

            if (info == null || info.trim().length() == 0) {
                info = anEnum.name();
            }

            cacheNames.put(anEnum, info);

        }

        return info;
    }


    /**
     * 值转换
     *
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    static <T> ValueHolder<T> convert(Object source, Type targetType) {

        ValueHolder<T> valueHolder = new ValueHolder<>();

        if (targetType == null
                || Object.class.equals(targetType)) {

            valueHolder.have((T) source).setType(targetType);

        } else if (source instanceof EnumDesc) {

            //如果是数值，并且源是枚举
            if (TypeUtils.isAssignable(Number.class, targetType)) {

                source = ((EnumDesc) source).code();

                valueHolder.have((T) source).setType(targetType);

            } else if (TypeUtils.isAssignable(CharSequence.class, targetType)) {

                source = ((EnumDesc) source).name();

                valueHolder.have((T) source).setType(targetType);

            }
        } else if (TypeUtils.isAssignable(EnumDesc.class, targetType)) {

            if (source instanceof Number) {

                source = EnumDesc.parse((Class<? extends Enum>) targetType, ((Number) source).intValue());

                valueHolder.have((T) source).setType(targetType);

            } else if (source instanceof CharSequence) {

                source = EnumDesc.parse((Class<? extends Enum>) targetType, source.toString());

                valueHolder.have((T) source).setType(targetType);
            }

        }

        return valueHolder;
    }

    /**
     * 转换到枚举
     */
    ConverterFactory<String, Enum> string2EnumFactory = new ConverterFactory<String, Enum>() {
        @Override
        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
            return name -> (T) EnumDesc.parse(targetType, name);
        }
    };

    /**
     * 转换到枚举
     */
    ConverterFactory<Number, Enum> number2EnumFactory = new ConverterFactory<Number, Enum>() {
        @Override
        public <T extends Enum> Converter<Number, T> getConverter(Class<T> targetType) {
            return code -> (T) EnumDesc.parse(targetType, code.intValue());
        }
    };

}
