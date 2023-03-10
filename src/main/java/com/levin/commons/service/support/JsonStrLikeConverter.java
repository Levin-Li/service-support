package com.levin.commons.service.support;

import com.levin.commons.utils.JsonStrArrayUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonStrLikeConverter implements GenericConverter {

    // private final static ConfigurableConversionService conversionService = new DefaultFormattingConversionService();

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

        if (isToCharSequence && (source instanceof CharSequence)) {

            if (!StringUtils.hasText((CharSequence) source)) {
                return null;
            }

            return JsonStrArrayUtils.getLikeQueryStr(source);

        } else if (sourceType.isCollection() && (targetType.isCollection() || targetType.getType().isAssignableFrom(Collection.class))) {

            Stream stream = ((Collection) source).stream().map(JsonStrArrayUtils::getLikeQueryStr);

            if (Set.class.isAssignableFrom(targetType.getType())) {
                return stream.collect(Collectors.toSet());
            } else {
                return stream.collect(Collectors.toList());
            }

        } else {
            throw new IllegalArgumentException("source[" + source.getClass() + "] is not a Iterable or String");
        }

    }

}
