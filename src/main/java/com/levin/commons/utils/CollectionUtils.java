package com.levin.commons.utils;


import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 类工具
 */

public abstract class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 拼接字符串
     *
     * @param delimiter
     * @param isSurroundStart
     * @param isSurroundEnd
     * @param elements
     * @param ignoreElementPredicates
     * @return
     */
    public static StringBuilder join(String delimiter, boolean isSurroundStart, boolean isSurroundEnd, Iterable elements, Predicate<Object>... ignoreElementPredicates) {

        final StringBuilder builder = new StringBuilder();

        if (isSurroundStart) {
            builder.append(delimiter);
        }

        for (Object element : elements) {

            if (ignoreElementPredicates != null
                    && Arrays.stream(ignoreElementPredicates)
                    .anyMatch(objectPredicate -> objectPredicate.test(element))) {
                continue;
            }

            builder.append(element)
                    .append(delimiter);
        }

        if (!isSurroundEnd
                && builder.length() > delimiter.length()) {
            //如果需要删除
            builder.delete(builder.length() - delimiter.length(), builder.length());
        }

        return builder;
    }

    /**
     * 用delimiter 包围，并且忽略空值
     *
     * @param delimiter
     * @param elements
     * @return
     */
    public static StringBuilder joinAndSurround(String delimiter, Iterable elements) {
        return join(delimiter, true, true, elements,
                Objects::isNull,
                e -> e instanceof CharSequence && !StringUtils.hasText((CharSequence) e));
    }

    /**
     * @param txt
     * @param delimiterRegex
     * @param convert
     * @param <T>
     * @return
     */
    public static <T> List<T> parse(String txt, String delimiterRegex, Function<String, T> convert, Predicate<String>... ignoreTextPredicates) {

        if (convert == null) {
            convert = item -> (T) item;
        }

        return Arrays.stream(txt.split(delimiterRegex))
                .filter(item -> ignoreTextPredicates == null
                        || Arrays.stream(ignoreTextPredicates).noneMatch(predicate -> predicate.test(item)))
                .map(convert)
                .collect(Collectors.toList());
    }

}
