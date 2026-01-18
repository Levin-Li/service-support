package com.levin.commons.rbac;

import java.util.function.Supplier;

/**
 * @author lilw
 * <p>
 * 缓存, 拥有提升性能
 */
public class CacheSupplier<T> implements Supplier<T> {

    private final Supplier<T> supplier;

    private boolean cached;

    private T cacheResult;

    public CacheSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {

        if (!cached) {
            cacheResult = supplier.get();
            cached = true;
        }

        return cacheResult;
    }
}
