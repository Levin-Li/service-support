package com.levin.commons.ui.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;


/**
 * @author lilw
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "值选项", description = "值加载的优先级: 固定选项,系统字典,枚举类,实体对象或是Dao查询对象,自定义API接口")
public @interface Options {

    @Schema(title = "名称")
    String name() default "";

    @Schema(title = "描述")
    String desc() default "";

    @Schema(title = "是否可多选", description = "默认为单选")
    boolean multiSelect() default false;

    @Schema(title = "样式")
    String style() default "";

    @Schema(title = "未选择时的默认值")
    String defaultValue() default "";

    @Schema(title = "固定选项列表", description = "每一个选项支持 3个属性，value, label, desc ，其中value是必须的，属性之间用||分隔")
    String[] items() default {};

    @Schema(title = "系统的字典编码")
    String dictCode() default "";

    @Schema(title = "关联的目标类型", description = "枚举类或是实体类")
    Class<?> refTargetType() default Void.class;

    @Schema(title = "自定义加载选项加载API", description = "优先级最低")
    String loadApi() default "";

    @Schema(title = "默认参数", description = "json,调用服务端接口时的默认参数")
    String defaultParams() default "";

    @Schema(title = "重新加载的触发条件", description = "Js脚本, 通常是通过Api加载的时候才需要")
    String reloadOn() default "";

    /**
     * 列映射表达式
     * <p>
     * 每一个选项支持 3个属性，value, label, desc
     * <p>
     * 本方法的返回值中：
     * <p>
     * 第一列为value对应的列名
     * 第二列为label对应的列名
     * 第三列为desc对应的列名
     * <p>
     * 如果都不配置，则默认为value, label
     * //"value", "label", "desc"
     *
     * @return
     */
    @Schema(title = "列映射表达式", description = "每一个选项支持 3个属性，value, label, desc;; 如果都不配置，则默认为value, label")
    String[] columnMapExpr() default {};

    @Schema(title = "搜索参数名称", description = "搜索时附加的参数名称, 一般根据实体类控制器的list接口参数,模糊查找参数名优先")
    String searchParamName() default "";

}
