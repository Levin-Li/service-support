package com.levin.commons.uform.annotation;


import java.lang.annotation.*;

/**
 * 主要参考 https://uformjs.org/
 *
 *
 * https://uformjs.org/#/YZCyCk/pRSKSDFECp
 *
 *
 * <p>
 * 表单字段注解
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UIField {

    enum Type {

        F_boolean("开关选择"),
        F_cards(""),
        F_checkbox("字符串"),
        F_daterange("日期范围"),
        F_year("字符串"),
        F_date("字符串"),
        F_time("字符串"),
        F_number("字符串"),
        F_password("字符串"),
        F_radio("字符串"),
        F_range("数值范围"),
        F_rating("等级"), //
        F_string("字符串"),
        F_table("字符串"),
        F_textarea("字符串"),

        F_transfer("穿梭框"),
        F_upload_card("卡片上传"),
        F_upload_dragger("拖拽上传","可以传多张图片"),
        F_upload_text("普通上传","会显示上传进度");

        final String label;
        final String remark;

        Type(String label) {
            this.label = label;
            this.remark = label;
        }

        Type(String label, String remark) {
            this.label = label;
            this.remark = remark;
        }
    }


    enum Rule {

//        None, //无

        R_url,   //匹配url路径
        R_email,   //匹配邮箱
        R_ipv6,   //匹配ipv6格式
        R_ipv4,   //匹配ipv4格式
        R_number,   //匹配number格式
        R_integer,   //匹配整型格式
        R_qq,   //匹配qq格式
        R_phone,   //匹配手机号码
        R_idcard,   //匹配大陆身份证号码
        R_taodomain,   //匹配淘系域名
        R_money,   //匹配货币格式
        R_zh,   //匹配中文字符串
        R_date,   //匹配日期格式
        R_zip,   //匹配邮编

    }


    String defaultValue() default "";                 //  默认值	any

    String enumValues() default "";                   //  枚举值，配置该值在默认情况下会显示Select形态，指定x-component会显示对应的组件形态	(string | { label: string, value:any })[]	[]

    int maxItems() default -1;                       //  最大条目数，只有布局在type="array"时可以使用 number

    int minItems() default -1;                       //  最小条目数，只有在type="array"时可以使用	number

    String name() default "";                        // 字段名称	string	''

    boolean required() default false;                  //  字段是否必填	boolean	false

    String description() default "";                  // 字段描述，如果字符串字数超过30字或内容是JSX.Element，会自动以pop形式展示	JSX.Element | string | null	''

    Type type() default Type.F_string;                  //  字段类型，具体类型枚举参考 fields	string

    String x_component() default "";                   //  字段UI组件，用于指定该字段应该用什么组件做渲染，具体类型枚举参考 fields	string

    String x_effect() default "";                       //  副作用事件绑定对象	(dispatch: (eventName: string, ...params: any[]) => void) => void

    String x_index() default "";                       //  字段索引顺序	number

    String x_props() default "";                       // 字段UI组件属性，API请参考对应fusion next/ant design组件API	{[key: string]: any}	{}

    boolean x_props_editable() default false;          //	字段是否可编辑	boolean	true

    String x_render() default "";                      //	字段渲染函数	(fieldProps : IFieldProps) => string | JSX.Element | null

    Rule[] x_rules() default {};                       //	字段校验规则	Rule | Rule[]

}
