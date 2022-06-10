package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * CRUD
 *
 * CRUD 增删改查渲染器。 文档：https://baidu.gitee.io/amis/docs/components/crud
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
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
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
