package com.levin.commons.service.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import jakarta.annotation.PostConstruct;

import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_SUPPORT;

/**
 * @author lilw
 */
@Configuration
@ConditionalOnMissingBean(VariableResolverManager.class)
@ConditionalOnProperty(
        name = "com.levin.commons.service.support.VariableResolverConfiguration.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class VariableResolverConfiguration {

    private static final Logger log = LoggerFactory.getLogger(VariableResolverConfiguration.class);

    @PostConstruct
    public void init() {
        log.info("*** 变量解析器配置已经启用，可以使用 " + VariableResolverConfiguration.class.getName() + ".enabled=false 禁用");
    }

    @Bean
    @Role(ROLE_SUPPORT)
    @ConditionalOnMissingBean(VariableResolverManager.class)
    VariableResolverManager defaultVariableResolverManager() {

        log.debug("*** init default variable resolver manager ...");

        return new DefaultVariableResolverManager();
    }

    @Bean
    @Role(ROLE_SUPPORT)
    @ConditionalOnMissingBean(VariableInjector.class)
    VariableInjector defaultVariableInjector(@Autowired VariableResolverManager vrm) {

        log.debug("*** init default variable injector ...");

        return new SimpleVariableInjector() {
            @Override
            public List<VariableResolver> getDefaultVariableResolvers() {
                return vrm.getVariableResolvers();
            }
        };

    }

}
