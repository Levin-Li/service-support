package com.levin.commons.plugin;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 插件组件
 *
 * @author llw
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface PluginComponent {

    /**
     * 资源标识
     *
     * @return
     */
    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     * 资源域
     *
     * @return
     */
    String domain() default "";

}
