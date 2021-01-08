package com.levin.commons.plugin.support;

import com.levin.commons.conditional.ConditionalOn;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginConfigurer;
import com.levin.commons.plugin.PluginManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import java.util.Arrays;

@Configuration
@Role(BeanDefinition.ROLE_SUPPORT)
@Slf4j
@ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = PluginConfiguration.class)
public class PluginConfiguration implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor, BeanPostProcessor {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        log.debug("*** setApplicationContext:" + Arrays.asList(applicationContext.getBeanDefinitionNames()));

        this.context = applicationContext;

        //Bean 初始化前
        //postProcessBeforeInitialization

        PluginManager pluginManager = context.getBean(PluginManager.class);

        this.context.getBeanProvider(Plugin.class).forEach(bean ->
                pluginManager.installPlugin(bean, true)
        );

        this.context.getBeanProvider(PluginConfigurer.class).forEach(bean -> bean.configPlugin(pluginManager));
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.debug("*** postProcessBeanDefinitionRegistry:" + Arrays.asList(registry.getBeanDefinitionNames()));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.debug("*** postProcessBeanFactory:" + Arrays.asList(beanFactory.getBeanDefinitionNames()));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Plugin) {

        } else if (bean instanceof PluginConfigurer) {
            log.debug("*** postProcessBeforeInitialization:" + beanName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Plugin) {

        } else if (bean instanceof PluginConfigurer) {
            log.debug("*** postProcessAfterInitialization:" + beanName);
        }
        return bean;
    }

    @Bean
    @ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = PluginManager.class)
    PluginManager pluginManager() {
        log.info("New PluginManager.");
        return new PluginManagerImpl();
    }

}
