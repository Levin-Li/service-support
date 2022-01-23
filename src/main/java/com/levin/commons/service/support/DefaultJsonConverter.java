package com.levin.commons.service.support;

import com.google.gson.Gson;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Set;

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

            return gson.toJson(source);

        } else if ((source instanceof CharSequence)) {
            return StringUtils.hasText((CharSequence) source) ? gson.fromJson(source.toString(), targetType.getResolvableType().getType()) : null;
        }

        return source;

    }

}
