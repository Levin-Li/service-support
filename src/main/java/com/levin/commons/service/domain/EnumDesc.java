package com.levin.commons.service.domain;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public interface EnumDesc {

    EnumDesc helper = new EnumDesc() {
        @Override
        public String getDesc() {
            throw new RuntimeException("current object is not an enum object");
        }
    };

    Map<Enum, String> cacheNames = new ConcurrentHashMap<>();


    default String getDesc() {
        return getDesc((Enum<?>) this);
    }

    default String getDesc(Enum<?> enumValue) {

        if (enumValue == null) {
            return null;
        }

        String info = cacheNames.get(enumValue);

        if (info != null) {
            return info;
        }

        synchronized (enumValue) {

            Schema annotation = null;

            try {
                annotation = enumValue.getClass().getField(enumValue.name()).getAnnotation(Schema.class);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            info = annotation != null ? annotation.description() : enumValue.toString();

            if (info.trim().length() == 0) {
                info = enumValue.name();
            }

            cacheNames.put(enumValue, info);
        }

        return info;
    }

}
