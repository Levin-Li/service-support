package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ClassName
 *
 * css类名，配置字符串，或者对象。nn    className: rednn用对象配置时意味着你能跟表达式一起搭配使用，如：nn    className: {         red: data.progress > 80,         blue: data.progress > 60     }
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "css类名，配置字符串，或者对象。nn    className: rednn用对象配置时意味着你能跟表达式一起搭配使用，如：nn    className: {         red: data.progress > 80,         blue: data.progress > 60     }")
public @interface ClassName {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
