package com.levin.commons.plugin.support;

import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginException;
import com.levin.commons.plugin.PluginManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;


/**
 * 默认插件管理器
 */

@Slf4j
public class PluginManagerImpl implements PluginManager {

    final String id = UUID.randomUUID().toString();

    final Map<String, Plugin> pluginMap = new ConcurrentReferenceHashMap<>();

    @Autowired
    ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    void init() {
        log.info("PluginManager init...");
    }

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
    public boolean sendEvent(String pluginId, Object... events) throws PluginException {

        scheduledExecutorService.submit(() -> syncSendEvent(pluginId, events));

        return true;
    }

    protected boolean syncSendEvent(String pluginId, Object... events) throws PluginException {

        if (StringUtils.hasText(pluginId)) {

            Plugin plugin = getInstalledPlugin(pluginId);

            if (plugin != null) {
                return plugin.onEvent(events);
            }
        }

        return false;
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
