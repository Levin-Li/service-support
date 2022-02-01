package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Reload
 *
 * \"配置刷新动作，这个动作通常在完成渲染器本省的固定动作后出发。\"n\"n一般用来配置目标组件的 name 属性。多个目标可以用逗号隔开。\"n\"n当目标是 windows 时表示刷新整个页面。\"n\"n刷新目标的同时还支持传递参数如： `foo?a=${a}&b=${b},boo?c=${c}`\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"配置刷新动作，这个动作通常在完成渲染器本省的固定动作后出发。\"n\"n一般用来配置目标组件的 name 属性。多个目标可以用逗号隔开。\"n\"n当目标是 windows 时表示刷新整个页面。\"n\"n刷新目标的同时还支持传递参数如： `foo?a=${a}&b=${b},boo?c=${c}`\"")
public @interface Reload {


}
