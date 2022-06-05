package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * CRUD
 *
 * CRUD 增删改查渲染器。 文档：https://baidu.gitee.io/amis/docs/components/crud
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
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
   * Any Of
   * [{"$ref":"#/definitions/CRUDCardsSchema"},{"$ref":"#/definitions/CRUDListSchema"},{"$ref":"#/definitions/CRUDTableSchema"}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
