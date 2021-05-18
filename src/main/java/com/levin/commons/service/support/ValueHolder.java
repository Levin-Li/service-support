package com.levin.commons.service.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ValueHolder<T> implements Supplier<T> {

    public static final ValueHolder NOT_VALUE = new ValueHolder() {
        @Override
        public boolean isHasValue() {
            return false;
        }

        @Override
        public Object getValue() {
            throw new IllegalStateException("not value");
        }
    };

    Object root;

    String name;

    T value;

    boolean hasValue = false;

    public Object getValue() {

        if (!isHasValue()) {
            throw new IllegalStateException("not value");
        }

        return value;
    }


    @Override
    public T get() {
        return (T) getValue();
    }

}
