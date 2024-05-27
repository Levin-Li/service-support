package com.levin.commons.service.support;

import java.util.concurrent.Callable;

/**
 * 支持定制异常的调用
 *
 * @param <R>
 * @param <EX>
 */
@FunctionalInterface
public interface ExCallable<R, EX extends Exception> extends Callable<R> {
    R call() throws EX;
}
