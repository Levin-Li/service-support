package com.levin.commons.plugin.support;

import com.levin.commons.conditional.ConditionalOn;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginException;
import com.levin.commons.plugin.PluginManager;
import org.springframework.stereotype.Service;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.levin.commons.conditional.ConditionalOn.Action.OnMissingBean;

/**
 * 默认插件管理器
 */

public class PluginManagerImpl implements PluginManager {

    final String id = UUID.randomUUID().toString();

    final Map<String, Plugin> pluginMap = new ConcurrentReferenceHashMap<>();

    @Override
    public void installPlugin(Plugin plugin, boolean isOverrideExists) throws PluginException {

        final String pluginId = plugin.getId();

        Plugin oldPlugin = pluginMap.get(pluginId);

        if (oldPlugin != null) {
            if (isOverrideExists) {
                //先卸载
                uninstallPlugin(pluginId);
            } else {
                throw new PluginException("Plugin [" + pluginId + "] exists.");
            }
        }

        pluginMap.put(pluginId, plugin);

    }

    @Override
    public Plugin getInstalledPlugin(String pluginId) throws PluginException {
        return pluginMap.get(pluginId);
    }

    @Override
    public Plugin uninstallPlugin(String pluginId) throws PluginException {

        Plugin remove = pluginMap.remove(pluginId);

        if (remove != null) {
            remove.destroy();
        }

        return remove;
    }

    @Override
    public List<Plugin> getInstalledPlugins() {
        return new ArrayList<>(pluginMap.values());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return "default";
    }

    @Override
    public String getDescription() {
        return "default plugin manager";
    }

}
