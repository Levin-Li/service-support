package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Audio
 *
 * Audio 音频渲染器。 文档：https://baidu.gitee.io/amis/docs/components/audio
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Audio 音频渲染器。 文档：https://baidu.gitee.io/amis/docs/components/audio")
public @interface Audio {
///////////////////////////////////////////

	//可以配置控制器
	enum Control{
		rates,
		play,
		time,
		process,
		volume,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为音频播放器
     *
     * 参考定义: {"type":"string","const":"audio","description":"指定为音频播放器"}
     *
     * @see 
     */
    @Schema(title = "指定为音频播放器")
    String type() default "audio";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(title = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     * @see 
     */
    @Schema(title = "是否禁用")
    boolean disabled() default false;

    /**
     * 是否禁用表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(title = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     * @see 
     */
    @Schema(title = "是否隐藏")
    boolean hidden() default false;

    /**
     * 是否隐藏表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(title = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     * @see 
     */
    @Schema(title = "是否显示")
    boolean visible() default false;

    /**
     * 是否显示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(title = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 是否是内联模式
     *
     * 参考定义: {"type":"boolean","description":"是否是内联模式"}
     *
     * @see 
     */
    @Schema(title = "是否是内联模式")
    boolean inline() default false;

    /**
     * 视频播放地址, 支持 $ 取变量。
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see 
     */
    @Schema(title = "视频播放地址, 支持 $ 取变量。")
    String src() default "	";

    /**
     * 是否循环播放
     *
     * 参考定义: {"type":"boolean","description":"是否循环播放"}
     *
     * @see 
     */
    @Schema(title = "是否循环播放")
    boolean loop() default false;

    /**
     * 是否自动播放
     *
     * 参考定义: {"type":"boolean","description":"是否自动播放"}
     *
     * @see 
     */
    @Schema(title = "是否自动播放")
    boolean autoPlay() default false;

    /**
     * 配置可选播放倍速
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"description":"配置可选播放倍速"}
     *
     * @see 
     */
    @Schema(title = "配置可选播放倍速")
    double[] rates() default 0;

    /**
     * 可以配置控制器
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["rates","play","time","process","volume"]},"description":"可以配置控制器"}
     *
     * @see 
     */
    @Schema(title = "可以配置控制器")
    Control[] controls() ;

}
