package com.levin.commons.service.support;

import com.levin.commons.utils.JsonStrArrayUtils;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public class PrimitiveArrayJsonConverter implements GenericConverter {

    private final static ConfigurableConversionService conversionService = new DefaultFormattingConversionService();

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
            if (source instanceof Iterable) {
                return JsonStrArrayUtils.iterableToStrArrayJson((Iterable<?>) source);
            } else if (source.getClass().isArray()) {
                return JsonStrArrayUtils.toStrArrayJson((Object[]) source);
            } else {
                throw new IllegalArgumentException("source[" + source.getClass() + "] is not a Iterable or Array");
            }
        } else if ((source instanceof CharSequence)) {

            if (!StringUtils.hasText((CharSequence) source)) {
                return null;
            }

            ResolvableType type = targetType.getResolvableType();

            Class<?> rawType = null;

            if (type.isArray()) {
                rawType = type.getComponentType().resolve();
            } else if (Iterable.class.isAssignableFrom(type.resolve())) {
                rawType = type.resolveGeneric(0);
            } else {
                throw new IllegalArgumentException("target[" + targetType + "] is not a Iterable or Array");
            }

            Class<?> tempRawType = rawType;

            List<?> list = JsonStrArrayUtils.parse(source.toString(), null, txt -> conversionService.convert(txt, tempRawType));

            return conversionService.convert(list, new TypeDescriptor(ResolvableType.forClassWithGenerics(List.class, tempRawType), null, new Annotation[]{}), targetType);

        } else {
            throw new IllegalArgumentException("source[" + source.getClass() + "] is not a Iterable or Array or String");
        }

    }

}
