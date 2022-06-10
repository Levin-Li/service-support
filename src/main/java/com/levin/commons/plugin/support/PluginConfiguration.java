package com.levin.commons.plugin.support;

import com.levin.commons.conditional.ConditionalOn;
import com.levin.commons.plugin.PluginManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import javax.annotation.PostConstruct;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_SUPPORT;

@Configuration
@Slf4j
@ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = PluginConfiguration.class)
public class PluginConfiguration {

    @PostConstruct
    public void init() {

    }

    @Bean
    @Role(ROLE_SUPPORT)
    @ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = PluginManager.class)
    PluginManager pluginManager() {

        log.debug("*** init default plugin manager ...");

        return new PluginManagerImpl();
    }

}
