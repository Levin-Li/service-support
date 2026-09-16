package com.levin.commons.service;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.function.Supplier;


@Tag(name = "映射值上下文", description = "实现代码中要去区分null值和没有值;希望它能够代替一些线程变量;")
public interface MapValueContext<K, V> {

    @Operation(summary = "是否有值", description = "如果放入null也算有值, 也就是说，只要设置了值，它就是有值")
    boolean hasValue(K key);

    @Operation(summary = "设置值", description = "如果放入null也算有值")
    MapValueContext<K, V> set(K key, V value);

    @Operation(summary = "清空一个值")
    MapValueContext<K, V> clear(K key);

    @Operation(summary = "获取值", description = "null不代表没有值")
    V get(K key);

    @Operation(summary = "获取值", description = "但是如果原来有值, 就返回旧职, 没有没有则获取新的，然后并且放进去")
    default V computeIfAbsent(K key, Supplier<V> supplier) {

        if (hasValue(key)) {
            return get(key);
        }

        V value = supplier.get();

        set(key, value);

        return value;
    }

}
