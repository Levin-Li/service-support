package com.levin.commons.service.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Type;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ValueHolder<T> implements Supplier<T> {
//
//    private static final ValueHolder NOT_VALUE = new ValueHolder() {
//        @Override
//        public final boolean hasValue() {
//            return false;
//        }
//
//        @Override
//        public final Object getValue() {
//            throw new IllegalStateException("not value");
//        }
//    };


    public static <T> ValueHolder<T> notValue(boolean throwEx, String name) {
        return notValue(throwEx, name, null);
    }

    /**
     * notValue
     *
     * @param throwEx
     * @param name
     * @param <T>
     * @return
     */
    public static <T> ValueHolder<T> notValue(boolean throwEx, String name, Throwable valueNotFoundCause) {

        if (throwEx) {
            if (valueNotFoundCause != null)
                throw new VariableNotFoundException(name + " value not found", valueNotFoundCause);
            else
                throw new VariableNotFoundException(name + " value not found");
        }

        return (ValueHolder<T>) new ValueHolder<>()
                .setName(name)
                .setValueNotFoundCause(valueNotFoundCause);
    }


    /**
     * @param <T>
     * @return
     */
    public static <T> ValueHolder<T> notValue(String name) {
        return (ValueHolder<T>) new ValueHolder<>().setName(name);
    }

    private Object root;

    private String name;

    //类型
    private Type type;

    private T value;

    @Setter
    private boolean hasValue = false;

    private Throwable valueNotFoundCause;

    public ValueHolder(T value) {
        this.value = value;
        this.hasValue = value != null;
    }

    public ValueHolder(Object root, String name, T value) {
        this.root = root;
        this.name = name;
        this.value = value;
        this.hasValue = value != null;
    }

    public Type getType() {
        return type != null ? type : (hasValue() && value != null ? value.getClass() : null);
    }

    /**
     * @return
     */
    public T getValue() {

        if (!hasValue()) {
            if (valueNotFoundCause != null)
                throw new VariableNotFoundException(name + " value not found", valueNotFoundCause);
            else
                throw new VariableNotFoundException(name + " value not found");
        }

        return value;
    }

    /**
     * @return
     */
    public final boolean isHasValue() {
        return hasValue();
    }

    /**
     * 有值
     *
     * @param value
     * @return
     */
    public ValueHolder<T> have(T value) {
        this.value = value;
        this.hasValue = true;
        return this;
    }

    /**
     * 是否有值
     *
     * @return
     */
    public boolean hasValue() {
        return hasValue;
    }

    /**
     * 是否非空值
     *
     * @return
     */
    public boolean isNotNull() {
        return hasValue() && value != null;
    }

    /**
     * @return
     */
    @Override
    public T get() {
        return getValue();
    }

    /**
     * @param defaultValue
     * @return
     */
    public T get(T defaultValue) {
        return hasValue() ? get() : defaultValue;
    }

}
