package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * CRUD
 *
 * CRUD 增删改查渲染器。 文档：https://baidu.gitee.io/amis/docs/components/crud
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "CRUD 增删改查渲染器。 文档：https://baidu.gitee.io/amis/docs/components/crud")
public @interface CRUD {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

}
