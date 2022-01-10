package com.levin.commons.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Json 字符串数组 处理工具
 */
public abstract class JsonStrArrayUtils {

    private static Gson gson = new Gson();
    public static final Type listStrType = new TypeToken<List<String>>() {
    }.getType();


    private JsonStrArrayUtils() {
    }

    public static String getLikeQueryStr(Object element) {

        //只有一个元素，去除前后空格
        String json = toStrArrayJson(element).trim();

        //去除前后[]，再去除前后空格
        return json.substring(1, json.length() - 1).trim();

    }

    public static String toStrArrayJson(Object... elements) {
        return iterableToStrArrayJson(Arrays.asList(elements));
    }

    /**
     * 转换为字符串对象数组对象
     *
     * @param elements
     * @param ignoreElementPredicates
     * @return
     */
    public static <T extends Object> String iterableToStrArrayJson(Iterable<T> elements, Predicate<T>... ignoreElementPredicates) {

        final JsonArray jsonArray = new JsonArray();

        if (elements != null) {

            for (T element : elements) {

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

        if (!StringUtils.hasText(jsonArray)) {
            return Collections.emptyList();
        }

        if (convert == null) {
            convert = item -> (T) item;
        }

        return Stream.of(gson.fromJson(jsonArray, String[].class))
                .filter(txt -> filter == null || filter.test(txt))
                .map(convert)
                .collect(Collectors.toList());

    }

}
