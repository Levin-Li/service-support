package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Date
 *
 * \"Date 展示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/date\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Date 展示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/date\"")
public @interface Date {

    /**
     * 指定为日期展示类型
     *
     * 参考定义: {"type":"string","enum":["date","datetime","time","static-date","static-datetime","static-time"],"description":"指定为日期展示类型"}
     *
     * @see 
     */
    @Schema(description = "指定为日期展示类型")
    String type() default "";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "容器 css 类名")
    String className() default "";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     * @see 
     */
    @Schema(description = "是否禁用")
    boolean disabled() default false;

    /**
     * 是否禁用表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否禁用表达式")
    String disabledOn() default "";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     * @see 
     */
    @Schema(description = "是否隐藏")
    boolean hidden() default false;

    /**
     * 是否隐藏表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否隐藏表达式")
    String hiddenOn() default "";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     * @see 
     */
    @Schema(description = "是否显示")
    boolean visible() default false;

    /**
     * 是否显示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "";

    /**
     * 展示的时间格式，参考 moment 中的格式说明。
     *
     * 参考定义: {"type":"string","description":"展示的时间格式，参考 moment 中的格式说明。"}
     *
     * @see 
     */
    @Schema(description = "展示的时间格式，参考 moment 中的格式说明。")
    String format() default "";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "";

    /**
     * 值的时间格式，参考 moment 中的格式说明。
     *
     * 参考定义: {"type":"string","description":"值的时间格式，参考 moment 中的格式说明。"}
     *
     * @see 
     */
    @Schema(description = "值的时间格式，参考 moment 中的格式说明。")
    String valueFormat() default "";

    /**
     * 显示成相对时间，比如1分钟前
     *
     * 参考定义: {"type":"boolean","description":"显示成相对时间，比如1分钟前"}
     *
     * @see 
     */
    @Schema(description = "显示成相对时间，比如1分钟前")
    boolean fromNow() default false;

    /**
     * 更新频率， 默认为1分钟
     *
     * 参考定义: {"type":"number","description":"更新频率， 默认为1分钟"}
     *
     * @see 
     */
    @Schema(description = "更新频率， 默认为1分钟")
    double updateFrequency() default 0;


}
