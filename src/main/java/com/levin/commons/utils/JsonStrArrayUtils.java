package com.levin.commons.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Json 字符串数组 处理工具
 */
public abstract class JsonStrArrayUtils {

    private static Gson gson = new Gson();
    // public static final Type listStrType = new TypeToken<List<String>>() {}.getType();


    private JsonStrArrayUtils() {
    }

    /**
     * 获取 一个元素的 Like 比较字符串
     *
     * @param element
     * @return
     */
    public static String getLikeQueryStr(Object element) {

        //只有一个元素，去除前后空格
        String json = toStrArrayJson(element).trim();

        //去除前后[]，再去除前后空格
        return json.substring(1, json.length() - 1).trim();

    }

    public static List<String> getLikeQueryStrList(Object... elements) {

        if (elements == null || elements.length == 0) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>(elements.length);

        for (Object element : elements) {
            result.add(getLikeQueryStr(element));
        }

        return result;
    }

    /**
     * 获取 like 查询条件
     *
     * @param elements
     * @return
     */
    public static List<String> getLikeQueryStrList(Iterable<?> elements) {

        if (elements == null) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>(5);

        elements.forEach(v -> result.add(getLikeQueryStr(v)));

        return result;
    }


    /**
     * 转换成数组，数组内容是字符串
     *
     * @param elements
     * @return
     */
    public static String toStrArrayJson(Object... elements) {
        return iterableToStrArrayJson(Arrays.asList(elements));
    }

    /**
     * 集合元素转换为Json 字符串数组
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

                if (element == null) {
                    jsonArray.add((String) null);
                } else if (element.getClass().isEnum()) {
                    jsonArray.add(((Enum) element).name());
                } else {
                    jsonArray.add(element.toString());
                }
            }
        }

        return gson.toJson(jsonArray);
    }


    /**
     * Json数组字符串，转换成集合对象
     * <p>
     * 解析
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
