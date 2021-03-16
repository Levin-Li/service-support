package com.levin.commons.service.support;

/**
 * 变量注入异常
 */
public class VariableInjectException extends RuntimeException {

    public VariableInjectException(String message) {
        super(message);
    }

    public VariableInjectException(String message, Throwable cause) {
        super(message, cause);
    }

}
