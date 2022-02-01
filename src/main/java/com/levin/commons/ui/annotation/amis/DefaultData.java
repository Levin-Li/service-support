package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * DefaultData
 *
 * \"初始数据，设置得值可用于组件内部模板使用。\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"初始数据，设置得值可用于组件内部模板使用。\"")
public @interface DefaultData {


}
