package com.levin.commons.service.support;

/**
 * 变量未找到异常
 */
public class VariableNotFoundException extends RuntimeException {

    public VariableNotFoundException(String message) {
        super(message);
    }

    public VariableNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
