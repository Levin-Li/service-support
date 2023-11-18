package com.levin.commons.service.support;

import com.levin.commons.conditional.ConditionalOn;
import com.levin.commons.conditional.ConditionalOnList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import javax.annotation.PostConstruct;

import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_SUPPORT;

@Configuration
@Slf4j
@ConditionalOnList({
        @ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = VariableResolverConfiguration.class),
})
public class VariableResolverConfiguration {

    @PostConstruct
    public void init() {

    }

    @Bean
    @Role(ROLE_SUPPORT)
    @ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = VariableResolverManager.class)
    VariableResolverManager variableResolverManager() {

        log.debug("*** init default variable resolver manager ...");

        return new DefaultVariableResolverManager();
    }

    @Bean
    @Role(ROLE_SUPPORT)
    @ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = VariableInjector.class)
    VariableInjector variableInjector(@Autowired VariableResolverManager vrm) {

        log.debug("*** init default variable injector ...");

        return new SimpleVariableInjector() {
            @Override
            public List<VariableResolver> getDefaultVariableResolvers() {
                return vrm.getVariableResolvers();
            }
        };

    }

}
