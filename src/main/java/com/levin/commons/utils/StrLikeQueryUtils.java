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

public abstract class StrLikeQueryUtils {

    private StrLikeQueryUtils() {
    }


    public static final String DEFAULT_DELIMITER = "|";

    public static String surround(String delimiter, Iterable elements) {
        return joinAndSurround(delimiter, true, true, elements,
                Objects::isNull,
                e -> e instanceof CharSequence && !StringUtils.hasText((CharSequence) e)).toString();
    }

    public static String surround(Object... elements) {
        return surround(Arrays.asList(elements));
    }

    public static String surround(Iterable elements) {
        return surround(DEFAULT_DELIMITER, elements);
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
    public static String joinAndSurround(String delimiter, boolean isSurroundStart, boolean isSurroundEnd, Iterable elements, Predicate<Object>... ignoreElementPredicates) {

        final StringBuilder builder = new StringBuilder();

        if (isSurroundStart) {
            builder.append(delimiter);
        }


        boolean isEmpty = true;

        for (Object element : elements) {

            if (ignoreElementPredicates != null
                    && Arrays.stream(ignoreElementPredicates)
                    .anyMatch(objectPredicate -> objectPredicate.test(element))) {
                continue;
            }

            builder.append(element)
                    .append(delimiter);

            isEmpty = false;
        }

        //如果时空集合，返回空字符串
        if (isEmpty) {
            return "";
        }

        if (!isSurroundEnd
                && builder.length() > delimiter.length()) {
            //如果需要删除
            builder.delete(builder.length() - delimiter.length(), builder.length());
        }

        return builder.toString();
    }


    /**
     * @param txt
     * @param delimiterRegex
     * @param convert
     * @param <T>
     * @return
     */
    public static <T> List<T> split(String txt, String delimiterRegex, Function<String, T> convert, Predicate<String>... ignoreTextPredicates) {

        if (convert == null) {
            convert = item -> (T) item;
        }

        return Arrays.stream(txt.split(delimiterRegex))
                .filter(item -> ignoreTextPredicates == null
                        || Arrays.stream(ignoreTextPredicates).noneMatch(predicate -> predicate.test(item)))
                .map(convert)
                .collect(Collectors.toList());
    }

    public static <T> List<T> split(String text, Function<String, T> convert, Predicate<String>... ignoreTextPredicates) {
        return split(text, DEFAULT_DELIMITER, convert, ignoreTextPredicates);
    }

    public static <T> List<T> split(String text, Function<String, T> convert) {
        return split(text, convert, txt -> !StringUtils.hasText(txt));
    }

    public static List<String> splitToString(String text) {
        return split(text, null);
    }

    public static List<Long> splitToLong(String text) {
        return split(text, txt -> Long.parseLong(txt));
    }


}
