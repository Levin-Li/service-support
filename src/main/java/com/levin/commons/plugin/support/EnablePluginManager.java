package com.levin.commons.plugin.support;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PluginConfiguration.class)
public @interface EnablePluginManager {

}
