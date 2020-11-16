package com.levin.commons.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class MapUtils {


    public static class Builder<K, V> {

        LinkedHashMap<K, V> map = new LinkedHashMap<>();


        public Builder(K key, V value) {
            map.put(key, value);
        }

        public Builder<K, V> put(K k, V v) {

            map.put(k, v);

            return this;
        }


        public Map<K, V> build() {
            return map;
        }

    }

    /**
     * 放入第一个元素并返回一个 map 的 builder
     *
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Builder<K, V> put(K key, V value) {
        return new Builder<K, V>(key, value);
    }


    /**
     * 参数对
     *
     * @param paramPairs
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> asMap(Object... paramPairs) {

        LinkedHashMap<K, V> map = new LinkedHashMap<>();

        if (paramPairs.length % 2 != 0)
            throw new IllegalArgumentException("Map 必须参数成对出现，参数个数必须为偶数");

        for (int i = 0; i < paramPairs.length; i++) {

            map.put((K) paramPairs[i], (V) paramPairs[++i]);
        }

        return map;

    }


    public static void main(String[] args) {

        System.out.println(asMap("k1", 2, "k2", "adsfds"));

        System.out.println(asMap("k1", 2, "k2", "adsfds"));

        System.out.println(asMap("k1", 2, "k2", "adsfds", "dasfdsaf", 111));


        put("sadfsad", (Object) 1232).put("adfds", "dsafadsf").build();
    }

}
