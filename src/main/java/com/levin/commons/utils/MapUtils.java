package com.levin.commons.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class MapUtils {

    private MapUtils() {
    }

    public static class Builder<K, V> {

        final Map<K, V> map;

        private Builder(Map<K, V> map) {
            this.map = map != null ? map : new LinkedHashMap<>();
        }

        private Builder() {
            this(null);
        }

        private Builder(K key, V value) {
            this();
            map.put(key, value);
        }

        public Builder<K, V> put(K k, V v) {

            map.put(k, v);

            return this;
        }

        public Builder<K, V> put(Map<K, V>... maps) {

            if (maps != null) {
                for (Map<K, V> aMap : maps) {
                    if (aMap != null) {
                        map.putAll(aMap);
                    }
                }
            }

            return this;
        }

        public Builder<K, V> putPairs(Object... paramPairs) {

            if (paramPairs.length % 2 != 0) {
                throw new IllegalArgumentException("参数必须成对出现，参数个数必须为偶数");
            }

            for (int i = 0; i < paramPairs.length; i++) {
                map.put((K) paramPairs[i], (V) paramPairs[++i]);
            }

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
     * @return
     */
    public static Builder<String, Object> putFirst(String key, Object value) {
        return new Builder<>(key, value);
    }


    public static <K, V> Builder<K, V> asRef(Map<K, V> map) {
        return new Builder<K, V>(map);
    }


    /**
     * 合并
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Builder<K, V> put(Map<K, V>... maps) {
        return new Builder<K, V>().put(maps);
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
        return new Builder<K, V>().putPairs(paramPairs).build();
    }

    /**
     * 取出缓存，如果没有，则先去用Supplier获取，然后放入缓存，在返回获取的值
     *
     * @param kvMap
     * @param key
     * @param putCondition
     * @param suppliers
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V getAndAutoPut(@NonNull Map kvMap, K key, Predicate<V> putCondition, @Nullable Supplier<V>... suppliers) {

        V value = (V) kvMap.get(key);

        if (value == null && suppliers != null) {

            for (Supplier<V> supplier : suppliers) {

                if (supplier == null) {
                    continue;
                }

                value = supplier.get();


                if (putCondition == null) {

                    if (value != null) {
                        //只要有一个不为 null，则返回
                        kvMap.put(key, value);
                        break;
                    }

                } else if (putCondition.test(value)) {
                    kvMap.put(key, value);
                    break;
                }

            }

        }

        return value;
    }


    public static <K, V> Map<K, V> newMap(boolean isStrongReference, boolean isWeakReference) {

        return isStrongReference ? new ConcurrentHashMap<>(16) : new ConcurrentReferenceHashMap<>(16
                , isWeakReference ? ConcurrentReferenceHashMap.ReferenceType.WEAK : ConcurrentReferenceHashMap.ReferenceType.SOFT);

    }

    public static void main(String[] args) {


        System.out.println(asMap("k1", 2, "k2", "adsfds"));

        System.out.println(asMap("k1", 2, "k2", "adsfds"));

        System.out.println(asMap("k1", 2, "k2", "adsfds", "dasfdsaf", 111));


        put("sadfsad", (Object) 1232).put("adfds", "dsafadsf").build();
    }

}
