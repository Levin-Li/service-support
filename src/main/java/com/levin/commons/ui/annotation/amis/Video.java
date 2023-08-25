package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Video
 *
 * 视频播放器 文档：https://baidu.gitee.io/amis/docs/components/video
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "视频播放器 文档：https://baidu.gitee.io/amis/docs/components/video")
public @interface Video {
///////////////////////////////////////////

	//视频比率
	enum AspectRatio{
		auto,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

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

    /**
     * 指定为视频类型
     *
     * 参考定义: {"type":"string","const":"video","description":"指定为视频类型"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为视频类型")
    String type() default "video";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "组件唯一 id，主要用于日志采集")
    String id() default "	";

    /**
     * 事件动作配置
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "事件动作配置")
    String onEvent() default "	";

    /**
     * 是否自动播放
     *
     * 参考定义: {"type":"boolean","description":"是否自动播放"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否自动播放")
    boolean autoPlay() default false;

    /**
     * 如果显示切帧，通过此配置项可以控制每行显示多少帧
     *
     * 参考定义: {"type":"number","description":"如果显示切帧，通过此配置项可以控制每行显示多少帧"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "如果显示切帧，通过此配置项可以控制每行显示多少帧")
    double columnsCount() default 0;

    /**
     * 设置后，可以显示切帧.点击帧的时候会将视频跳到对应时间。\n\nframes: {  '01:22': 'http://domain/xxx.jpg' }
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"string"},"description":"设置后，可以显示切帧.点击帧的时候会将视频跳到对应时间。\n\nframes: {  '01:22': 'http://domain/xxx.jpg' }"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "设置后，可以显示切帧.点击帧的时候会将视频跳到对应时间。\n\nframes: {  '01:22': 'http://domain/xxx.jpg' }")
    String frames() default "	";

    /**
     * 配置帧列表容器className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "配置帧列表容器className")
    String framesClassName() default "	";

    /**
     * 如果是实时的，请标记一下
     *
     * 参考定义: {"type":"boolean","description":"如果是实时的，请标记一下"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "如果是实时的，请标记一下")
    boolean isLive() default false;

    /**
     * 点击帧画面时是否跳转视频对应的点
     *
     * 参考定义: {"type":"boolean","description":"点击帧画面时是否跳转视频对应的点","default":true}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "点击帧画面时是否跳转视频对应的点")
    boolean jumpFrame() default false;

    /**
     * 是否初始静音
     *
     * 参考定义: {"type":"boolean","description":"是否初始静音"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否初始静音")
    boolean muted() default false;

    /**
     * 配置播放器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "配置播放器 className")
    String playerClassName() default "	";

    /**
     * 视频封面地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     *
     *
     *
     *
     * @see UrlPath
     */

    @Schema(title = "视频封面地址")
    String poster() default "	";

    /**
     * 是否将视频和封面分开显示
     *
     * 参考定义: {"type":"boolean","description":"是否将视频和封面分开显示"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否将视频和封面分开显示")
    boolean splitPoster() default false;

    /**
     * 视频播放地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     *
     *
     *
     *
     * @see UrlPath
     */

    @Schema(title = "视频播放地址")
    String src() default "	";

    /**
     * 视频类型如： video/x-flv
     *
     * 参考定义: {"type":"string","description":"视频类型如： video/x-flv"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "视频类型如： video/x-flv")
    String videoType() default "	";

    /**
     * 视频比率
     *
     * 参考定义: {"type":"string","enum":["auto","4:3","16:9"],"description":"视频比率"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "视频比率")
    AspectRatio aspectRatio() ;

    /**
     * 视频速率
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"description":"视频速率"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "视频速率")
    double[] rates() default 0;

    /**
     * 跳转到帧时，往前多少秒。
     *
     * 参考定义: {"type":"number","description":"跳转到帧时，往前多少秒。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "跳转到帧时，往前多少秒。")
    double jumpBufferDuration() default 0;

    /**
     * 默认播放的时候到了下一帧会继续播放，同时高亮下一帧。 如果配置这个则会停止播放，等待用户选择新的区间再播放。
     *
     * 参考定义: {"type":"boolean","description":"默认播放的时候到了下一帧会继续播放，同时高亮下一帧。 如果配置这个则会停止播放，等待用户选择新的区间再播放。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "默认播放的时候到了下一帧会继续播放，同时高亮下一帧。 如果配置这个则会停止播放，等待用户选择新的区间再播放。")
    boolean stopOnNextFrame() default false;

}
