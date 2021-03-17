package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleVariableInjectorTest {


    @Test
    void getVariableResolvers() {
    }

    @Test
    void getSimpleVariableResolver() {
    }

    @Test
    void getSpelVariableResolver() {


    }

    @Test
    void getGroovyVariableResolver() {
    }

    @Test
    void inject() {

       // SimpleVariableInjector.defaultSimpleVariableInjector.

    }

    @Test
    void injectByVariableResolver() {
    }

    @Data
    class Dto {


        @InjectVar
        String userId;

        @InjectVar
        String orgId;

        @InjectVar(InjectVar.SPEL_PREFIX + "#" + InjectConsts.ORG)
        Object org;

        @InjectVar(InjectVar.SPEL_PREFIX + "#" + InjectConsts.USER)
        Object user;


        @InjectVar
        String ipAddr;

    }

}