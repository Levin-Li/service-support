package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Reload
 *
 * 配置刷新动作，这个动作通常在完成渲染器本省的固定动作后出发。nn一般用来配置目标组件的 name 属性。多个目标可以用逗号隔开。nn当目标是 windows 时表示刷新整个页面。nn刷新目标的同时还支持传递参数如： `foo?a=${a}&b=${b},boo?c=${c}`
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "配置刷新动作，这个动作通常在完成渲染器本省的固定动作后出发。nn一般用来配置目标组件的 name 属性。多个目标可以用逗号隔开。nn当目标是 windows 时表示刷新整个页面。nn刷新目标的同时还支持传递参数如： `foo?a=${a}&b=${b},boo?c=${c}`")
public @interface Reload {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   *
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
