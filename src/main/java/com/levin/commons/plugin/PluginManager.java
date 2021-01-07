package com.levin.commons.plugin;


import java.util.List;

/**
 * 插件管理器
 *
 * @author llw
 */
public interface PluginManager extends Identifiable<String> {

    /**
     * 安装插件
     *
     * @param plugin
     * @param isOverrideExists 是否覆盖已经存在的插件
     */
    void installPlugin(Plugin plugin, boolean isOverrideExists) throws PluginException;

    /**
     * 卸载插件
     *
     * @param pluginId
     * @return 被卸载的插件
     */
    Plugin uninstallPlugin(String pluginId) throws PluginException;


    /**
     * 插件
     *
     * @param pluginId
     * @return 插件
     */
    Plugin getInstalledPlugin(String pluginId) throws PluginException;

    /**
     * 获取已经安装的插件列表
     *
     * @return
     */
    List<Plugin> getInstalledPlugins();

}
