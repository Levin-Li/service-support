package com.levin.commons.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonStrArrayUtils {

    private static Gson gson = new Gson();

    public static String toStrArrayJson(Object... elements) {
        return iterableToStrArrayJson(Arrays.asList(elements));
    }

    public static String getLikeQueryStr(Object element) {
        return "\"" + element.toString() + "\"";
    }


    /**
     * 转换为字符串对象数组对象
     *
     * @param elements
     * @param ignoreElementPredicates
     * @return
     */
    public static String iterableToStrArrayJson(Iterable elements, Predicate<Object>... ignoreElementPredicates) {

        final JsonArray jsonArray = new JsonArray();

        if (elements != null) {
            for (Object element : elements) {

                if (ignoreElementPredicates != null
                        && Arrays.stream(ignoreElementPredicates)
                        .filter(Objects::nonNull)
                        .anyMatch(predicate -> predicate.test(element))) {
                    //如果是忽略的对象
                    continue;
                }

                jsonArray.add(element != null ? element.toString() : null);
            }
        }

        return gson.toJson(jsonArray);
    }


    /**
     * 转换成对象
     *
     * @param jsonArray
     * @param filter
     * @param convert
     * @param <T>
     * @return
     */
    public static <T> List<T> parse(String jsonArray, Predicate<String> filter, Function<String, T> convert) {

        if (convert == null) {
            convert = item -> (T) item;
        }

        if (filter == null) {
            filter = StringUtils::hasText;
        }

        return Stream.of(gson.fromJson(jsonArray, String[].class))
                .filter(filter)
                .map(convert)
                .collect(Collectors.toList());
    }

}
