package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Message
 *
 * 消息文案配置，记住这个优先级是最低的，如果你的接口返回了 msg，接口返回的优先。
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "消息文案配置，记住这个优先级是最低的，如果你的接口返回了 msg，接口返回的优先。")
public @interface Message {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 获取失败时的提示
     *
     * 参考定义: {"type":"string","description":"获取失败时的提示"}
     *
     * @see 
     */
    @Schema(description = "获取失败时的提示")
    String fetchFailed() default "	";

    /**
     * 获取成功的提示，默认为空。
     *
     * 参考定义: {"type":"string","description":"获取成功的提示，默认为空。"}
     *
     * @see 
     */
    @Schema(description = "获取成功的提示，默认为空。")
    String fetchSuccess() default "	";

    /**
     * 保存失败时的提示。
     *
     * 参考定义: {"type":"string","description":"保存失败时的提示。"}
     *
     * @see 
     */
    @Schema(description = "保存失败时的提示。")
    String saveFailed() default "	";

    /**
     * 保存成功时的提示。
     *
     * 参考定义: {"type":"string","description":"保存成功时的提示。"}
     *
     * @see 
     */
    @Schema(description = "保存成功时的提示。")
    String saveSuccess() default "	";

}
