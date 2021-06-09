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
        public final boolean isHasValue() {
            return hasValue();
        }

        @Override
        public final Object getValue() {
            throw new IllegalStateException("not value");
        }
    };

    Object root;

    String name;

    T value;

    boolean hasValue = false;

    public Object getValue() {

        if (!hasValue()) {
            throw new IllegalStateException("not value");
        }

        return value;
    }

    /**
     * 是否有值
     *
     * @return
     */
    public boolean hasValue() {
        return hasValue;
    }

    public ValueHolder<T> hasValue(boolean hasValue) {
        this.hasValue = hasValue;
        return this;
    }

    @Override
    public T get() {
        return (T) getValue();
    }

}
