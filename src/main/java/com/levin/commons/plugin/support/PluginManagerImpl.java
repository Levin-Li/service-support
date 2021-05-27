package com.levin.commons.plugin.support;

import com.levin.commons.plugin.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 默认插件管理器
 */

@Slf4j
public class PluginManagerImpl implements PluginManager,
        DestructionAwareBeanPostProcessor,
        Ordered,
        BeanFactoryAware,
        ApplicationContextAware,
        SmartInitializingSingleton,
        ApplicationListener<ContextRefreshedEvent>,
        DisposableBean {

    final String id = UUID.randomUUID().toString();

    final Map<String, Plugin> pluginMap = new ConcurrentReferenceHashMap<>();

    @Nullable
    private BeanFactory beanFactory;

    @Nullable
    private ApplicationContext applicationContext;

    @Autowired(required = false)
    @Qualifier("pluginAsyncTaskExecutor")
    AsyncTaskExecutor asyncTaskExecutor;

    @PostConstruct
    void init() {
        log.info("PluginManager init...");
    }

    private void finishRegistration() {

        log.info("start register plugins ...");

        PluginManager pluginManager = beanFactory.getBean(PluginManager.class);

        this.beanFactory.getBeanProvider(Plugin.class).forEach(bean -> {
                    try {
                        pluginManager.installPlugin(bean, true);
                        if (bean instanceof PluginManagerAware) {
                            ((PluginManagerAware) bean).setPluginManager(pluginManager);
                        }
                    } catch (PluginException e) {
                        log.error("installPlugin[" + bean.getClass() + "] error", e);
                    }

                }

        );

        this.beanFactory.getBeanProvider(PluginConfigurer.class).forEach(bean -> bean.configPlugin(pluginManager));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void destroy() throws Exception {

        for (Plugin value : pluginMap.values()) {
            uninstallPlugin(value.getId());
        }

        pluginMap.clear();
    }

    @Override
    public void afterSingletonsInstantiated() {

        if (this.applicationContext == null) {
            // Not running in an ApplicationContext -> register tasks early...
            finishRegistration();
        }
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

        if (this.beanFactory == null) {
            this.beanFactory = applicationContext;
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() == this.applicationContext) {
            this.finishRegistration();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE / 2;
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

        log.info("*** plugin " + pluginId + " installed.");

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

        if (asyncTaskExecutor == null) {
            log.warn("plugin manager asyncTaskExecutor [pluginAsyncTaskExecutor] not set");
            //使用 Spring boot 标准的名字
            asyncTaskExecutor = beanFactory.getBean("applicationTaskExecutor", AsyncTaskExecutor.class);
        }

        asyncTaskExecutor.execute(() -> syncSendEvent(pluginId, events));

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
