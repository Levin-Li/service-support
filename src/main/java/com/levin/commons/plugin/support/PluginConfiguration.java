package com.levin.commons.plugin.support;

import com.levin.commons.plugin.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import jakarta.annotation.PostConstruct;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_SUPPORT;

@Configuration
@ConditionalOnMissingBean(PluginManager.class)
@ConditionalOnProperty(
        name = "com.levin.commons.plugin.support.PluginConfiguration.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class PluginConfiguration {

    private static final Logger log = LoggerFactory.getLogger(PluginConfiguration.class);

    @PostConstruct
    public void init() {
        log.info("*** 插件管理器配置已经启用，可以使用 " + PluginConfiguration.class.getName() + ".enabled=false 禁用");
    }

    @Bean
    @Role(ROLE_SUPPORT)
    @ConditionalOnMissingBean(PluginManager.class)
    PluginManager defaultPluginManager() {

        log.debug("*** init default plugin manager ...");

        return new PluginManagerImpl();
    }

}
