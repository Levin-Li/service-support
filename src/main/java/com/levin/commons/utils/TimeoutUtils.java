package com.levin.commons.utils;

import org.springframework.util.Assert;

import java.util.function.Supplier;

/**
 * 类工具
 */

public final class TimeoutUtils {

    /**
     * 获取结果，直到超时
     *
     * @param timeout
     * @param require
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T get(long timeout, boolean require, Supplier<T> supplier) {

        Assert.notNull(supplier, "supplier is null");

        T result = null;

        while (timeout-- > 0
                && (result = supplier.get()) == null
                && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }

        Assert.isTrue(!require || result != null, "can't get result");

        return result;
    }

}
