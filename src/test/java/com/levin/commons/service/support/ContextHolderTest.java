package com.levin.commons.service.support;

import com.levin.commons.service.MapValueContext;
import com.levin.commons.service.SingleValueContext;
import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContextHolderTest {


    static class TestObj {

                Integer[] index;
//        List<Integer> index;

    }

    @Test
    void get() {

        PrimitiveArrayJsonConverter converter = new PrimitiveArrayJsonConverter();

        Field field = ReflectionUtils.findField(TestObj.class, "index");

        Object convert = converter.convert("[\"1\",\"2\",\"3\",\"4\"]", new TypeDescriptor(ResolvableType.forClass(String.class), String.class, new Annotation[]{}), new TypeDescriptor(field));

        System.out.println(convert);

    }

    @Test
    void simpleContextComputeIfAbsentShouldPreserveExplicitNull() {
        MutableSingleValueContext<String> context = new MutableSingleValueContext<>();
        AtomicInteger supplierCalls = new AtomicInteger();
        context.set(null);

        assertNull(context.computeIfAbsent(() -> {
            supplierCalls.incrementAndGet();
            return "fallback";
        }));
        assertTrue(context.hasValue());
        assertEquals(0, supplierCalls.get(), "显式设置的 null 不能被视为缺值");

        context.clear();
        assertFalse(context.hasValue());
        assertEquals("fallback", context.computeIfAbsent(() -> "fallback"));
    }

    @Test
    void mapContextComputeIfAbsentShouldPreserveExplicitNullPerKey() {
        MutableMapValueContext<String, String> context = new MutableMapValueContext<>();
        AtomicInteger supplierCalls = new AtomicInteger();
        context.setValue("configured", null);

        assertNull(context.computeIfAbsent("configured", () -> {
            supplierCalls.incrementAndGet();
            return "fallback";
        }));
        assertEquals("created", context.computeIfAbsent("missing", () -> "created"));
        assertTrue(context.hasValue("configured"));
        assertTrue(context.hasValue("missing"));
        assertEquals(0, supplierCalls.get(), "已有 null 值不应触发回退供应器");
    }

    private static class MutableSingleValueContext<V> implements SingleValueContext<V> {
        private boolean hasValue;
        private V value;

        @Override
        public boolean hasValue() {
            return hasValue;
        }

        @Override
        public SingleValueContext<V> set(V value) {
            hasValue = true;
            this.value = value;
            return this;
        }

        @Override
        public SingleValueContext<V> clear() {
            hasValue = false;
            value = null;
            return this;
        }

        @Override
        public V get() {
            return value;
        }
    }

    private static class MutableMapValueContext<K, V> implements MapValueContext<K, V> {
        private final Map<K, V> values = new HashMap<>();

        @Override
        public boolean hasValue(K key) {
            return values.containsKey(key);
        }

        @Override
        public MapValueContext<K, V> setValue(K key, V value) {
            values.put(key, value);
            return this;
        }

        @Override
        public MapValueContext<K, V> clear(K key) {
            values.remove(key);
            return this;
        }

        @Override
        public V getValue(K key) {
            return values.get(key);
        }
    }

}
