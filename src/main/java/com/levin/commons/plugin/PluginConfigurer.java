package com.levin.commons.plugin;

@FunctionalInterface
public interface PluginConfigurer {

    /**
     * 配置插件
     *
     * @param pluginManager
     */
    void configPlugin(PluginManager pluginManager);

}
