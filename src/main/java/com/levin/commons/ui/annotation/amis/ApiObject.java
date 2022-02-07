package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ApiObject
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-7 23:06:28
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ApiObject")
public @interface ApiObject {
///////////////////////////////////////////

	//API 发送类型
	enum Method{
		get,
		post,
		put,
		delete,
		patch,
		jsonp,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//发送体的格式
	enum DataType{
		json,
		form_data,
		form,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

    /**
     * API 发送类型
     *
     * 参考定义: {"type":"string","enum":["get","post","put","delete","patch","jsonp"],"description":"API 发送类型"}
     *
     * @see 
     */
    @Schema(description = "API 发送类型")
    Method method() ;

    /**
     * API 发送目标地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see 
     */
    @Schema(description = "API 发送目标地址")
    String url() default "";

    /**
     * 用来控制携带数据. 当key 为 `&` 值为 `$$` 时, 将所有原始数据打平设置到 data 中. 当值为 $$ 将所有原始数据赋值到对应的 key 中. 当值为 $ 打头时, 将变量值设置到 key 中.
     *
     * 参考定义: {"type":"object","description":"用来控制携带数据. 当key 为 `&` 值为 `$$` 时, 将所有原始数据打平设置到 data 中. 当值为 $$ 将所有原始数据赋值到对应的 key 中. 当值为 $ 打头时, 将变量值设置到 key 中."}
     *
     * @see 
     */
    @Schema(description = "用来控制携带数据. 当key 为 `&` 值为 `$$` 时, 将所有原始数据打平设置到 data 中. 当值为 $$ 将所有原始数据赋值到对应的 key 中. 当值为 $ 打头时, 将变量值设置到 key 中.")
    String data() default "";

    /**
     * 默认数据映射中的key如果带点，或者带大括号，会转成对象比如：\n\n{   'a.b': '123' }\n\n经过数据映射后变成 {  a: {   b: '123  } }\n\n如果想要关闭此功能，请设置 convertKeyToPath 为 false
     *
     * 参考定义: {"type":"boolean","description":"默认数据映射中的key如果带点，或者带大括号，会转成对象比如：\n\n{   'a.b': '123' }\n\n经过数据映射后变成 {  a: {   b: '123  } }\n\n如果想要关闭此功能，请设置 convertKeyToPath 为 false"}
     *
     * @see 
     */
    @Schema(description = "默认数据映射中的key如果带点，或者带大括号，会转成对象比如：\n\n{   'a.b': '123' }\n\n经过数据映射后变成 {  a: {   b: '123  } }\n\n如果想要关闭此功能，请设置 convertKeyToPath 为 false")
    boolean convertKeyToPath() default false;

    /**
     * 用来做接口返回的数据映射。
     *
     * 参考定义: {"type":"object","description":"用来做接口返回的数据映射。"}
     *
     * @see 
     */
    @Schema(description = "用来做接口返回的数据映射。")
    String responseData() default "";

    /**
     * 如果 method 为 get 的接口，设置了 data 信息。 默认 data 会自动附带在 query 里面发送给后端。\n\n如果想通过 body 发送给后端，那么请把这个配置成 false。\n\n但是，浏览器还不支持啊，设置了只是摆设。除非服务端支持 method-override
     *
     * 参考定义: {"type":"boolean","description":"如果 method 为 get 的接口，设置了 data 信息。 默认 data 会自动附带在 query 里面发送给后端。\n\n如果想通过 body 发送给后端，那么请把这个配置成 false。\n\n但是，浏览器还不支持啊，设置了只是摆设。除非服务端支持 method-override"}
     *
     * @see 
     */
    @Schema(description = "如果 method 为 get 的接口，设置了 data 信息。 默认 data 会自动附带在 query 里面发送给后端。\n\n如果想通过 body 发送给后端，那么请把这个配置成 false。\n\n但是，浏览器还不支持啊，设置了只是摆设。除非服务端支持 method-override")
    boolean attachDataToQuery() default false;

    /**
     * 发送体的格式
     *
     * 参考定义: {"type":"string","enum":["json","form-data","form"],"description":"发送体的格式"}
     *
     * @see 
     */
    @Schema(description = "发送体的格式")
    DataType dataType() ;

    /**
     * 如果是文件下载接口，请配置这个。
     *
     * 参考定义: {"type":"string","const":"blob","description":"如果是文件下载接口，请配置这个。"}
     *
     * @see 
     */
    @Schema(description = "如果是文件下载接口，请配置这个。")
    String responseType() default "blob";

    /**
     * 携带 headers，用法和 data 一样，可以用变量。
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":["string","number"]},"description":"携带 headers，用法和 data 一样，可以用变量。"}
     *
     * @see 
     */
    @Schema(description = "携带 headers，用法和 data 一样，可以用变量。")
    String headers() default "";

    /**
     * 设置发送条件
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(description = "设置发送条件")
    String sendOn() default "";

    /**
     * 默认都是追加模式，如果想完全替换把这个配置成 true
     *
     * 参考定义: {"type":"boolean","description":"默认都是追加模式，如果想完全替换把这个配置成 true"}
     *
     * @see 
     */
    @Schema(description = "默认都是追加模式，如果想完全替换把这个配置成 true")
    boolean replaceData() default false;

    /**
     * 是否自动刷新，当 url 中的取值结果变化时，自动刷新数据。
     *
     * 参考定义: {"type":"boolean","description":"是否自动刷新，当 url 中的取值结果变化时，自动刷新数据。","default":true}
     *
     * @see 
     */
    @Schema(description = "是否自动刷新，当 url 中的取值结果变化时，自动刷新数据。")
    boolean autoRefresh() default false;

    /**
     * 当开启自动刷新的时候，默认是 api 的 url 来自动跟踪变量变化的。 如果你希望监控 url 外的变量，请配置 traceExpression。
     *
     * 参考定义: {"type":"string","description":"当开启自动刷新的时候，默认是 api 的 url 来自动跟踪变量变化的。 如果你希望监控 url 外的变量，请配置 traceExpression。"}
     *
     * @see 
     */
    @Schema(description = "当开启自动刷新的时候，默认是 api 的 url 来自动跟踪变量变化的。 如果你希望监控 url 外的变量，请配置 traceExpression。")
    String trackExpression() default "";

    /**
     * 如果设置了值，同一个接口，相同参数，指定的时间（单位：ms）内请求将直接走缓存。
     *
     * 参考定义: {"type":"number","description":"如果设置了值，同一个接口，相同参数，指定的时间（单位：ms）内请求将直接走缓存。"}
     *
     * @see 
     */
    @Schema(description = "如果设置了值，同一个接口，相同参数，指定的时间（单位：ms）内请求将直接走缓存。")
    double cache() default 0;

    /**
     * qs 配置项
     *
     * 参考定义: {"type":"object","properties":{"arrayFormat":{"type":"string","enum":["indices","brackets","repeat","comma"]},"indices":{"type":"boolean"},"allowDots":{"type":"boolean"}},"additionalProperties":false,"description":"qs 配置项"}
     *
     * @see 
     */
    @Schema(description = "qs 配置项")
    String qsOptions() default "";

}
