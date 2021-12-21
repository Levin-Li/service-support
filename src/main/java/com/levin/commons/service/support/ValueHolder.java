package com.levin.commons.service.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
        public final boolean hasValue() {
            return false;
        }

        @Override
        public final Object getValue() {
            throw new IllegalStateException("not value");
        }
    };


    public static <T> ValueHolder<T> notValue(boolean throwEx, String name) {

        if (throwEx) {
            throw new VariableNotFoundException("variable " + name + " not found");
        }

        return NOT_VALUE;
    }


    /**
     * @param <T>
     * @return
     */
    public static <T> ValueHolder<T> notValue() {
        return NOT_VALUE;
    }


    private Object root;

    private String name;

    private T value;

    @Setter
    private boolean hasValue = false;

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

    @Override
    public T get() {
        return (T) getValue();
    }

}
