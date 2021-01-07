package com.levin.commons.plugin;


/**
 * 插件异常
 *
 * @author llw
 */
public class PluginException extends RuntimeException {

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }

}
