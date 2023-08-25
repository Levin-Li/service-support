package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Date
 *
 * Date 展示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/date
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Date 展示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/date")
public @interface Date {
///////////////////////////////////////////

	//指定为日期展示类型
	enum Type{
		date,
		datetime,
		time,
		static_date,
		static_datetime,
		static_time,
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
     * 指定为日期展示类型
     *
     * 参考定义: {"type":"string","enum":["date","datetime","time","static-date","static-datetime","static-time"],"description":"指定为日期展示类型"}
     *
     * @see
     */
    @Schema(title = "指定为日期展示类型")
    Type type() ;

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
     * 展示的时间格式，参考 moment 中的格式说明。
     *
     * 参考定义: {"type":"string","description":"展示的时间格式，参考 moment 中的格式说明。"}
     *
     * @see
     */
    @Schema(title = "展示的时间格式，参考 moment 中的格式说明。")
    String format() default "	";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see
     */
    @Schema(title = "占位符")
    String placeholder() default "	";

    /**
     * 值的时间格式，参考 moment 中的格式说明。
     *
     * 参考定义: {"type":"string","description":"值的时间格式，参考 moment 中的格式说明。"}
     *
     * @see
     */
    @Schema(title = "值的时间格式，参考 moment 中的格式说明。")
    String valueFormat() default "	";

    /**
     * 显示成相对时间，比如1分钟前
     *
     * 参考定义: {"type":"boolean","description":"显示成相对时间，比如1分钟前"}
     *
     * @see
     */
    @Schema(title = "显示成相对时间，比如1分钟前")
    boolean fromNow() default false;

    /**
     * 更新频率， 默认为1分钟
     *
     * 参考定义: {"type":"number","description":"更新频率， 默认为1分钟"}
     *
     * @see
     */
    @Schema(title = "更新频率， 默认为1分钟")
    double updateFrequency() default 0;

}
