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

    public static final ValueHolder NOT_VALUE = new ValueHolder<>();

    Object root;

    String name;

    T value;

    boolean hasValue = false;

    @Override
    public T get() {
        return value;
    }

}
