package com.levin.commons.ui.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 值选项
 * <p>
 * 来源支持以下几个方面
 * <p>
 * 1、支持固定配置
 * 2、API接口
 * 3、枚举类
 * 4、查询对象
 * 5、支持系统的字典编码
 * <p>
 *
 * @author llw
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Options {

    /**
     * 选项名称
     *
     * @return
     */
    String name() default "";

    /**
     * 选项描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 是否 多选/多值
     */
    boolean multiSelect() default false;

    /**
     * 样式
     *
     * @return
     */
    String style() default "";

    /**
     * 未选择是的默认值
     *
     * @return
     */
    String defaultValue() default "";

    /**
     * 固定选项列表
     * 每一个选项支持 3个属性，value, label, desc ，其中value是必须的，属性之间用||分隔
     * 如 Man||男||成年男性
     *
     * @return
     */
    String[] items() default {};

    /**
     * 关联的目标类型, 枚举或是实体类
     * <p>
     * 枚举类,或是关联的实体类
     * <p>
     *
     * @return
     */
    Class<?> refTargetType() default Void.class;

    /**
     * 系统的字典编码
     * <p>
     *
     * @return
     */
    String dictCode() default "";

    /**
     * API接口
     * 参考Amis 定义: https://baidu.github.io/amis/zh-CN/docs/types/api?page=1
     * <p>
     * 建议通过权限标识来控制权限和匹配URL
     * <p>
     * 不建议使用通用的API接口，所有的API接口都通过控制器来定义。
     * <p>
     *
     * @return
     */
    String loadApi() default "";

    /**
     * 默认参数
     * <p>
     * 针对API接口 , refTargetType 或是字典 的默认参数
     *
     * @return
     */
    String defaultParams() default "";

    /**
     * 级联下拉列表时，其他属性变更时，需要重新加载
     * <p>
     * 只正对API或是查询对象有效
     * <p>
     * 如果不为空，则表示默认不加载内容
     *
     * @return
     */
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
    String[] columnMapExpr() default {};

    /**
     * 是否可以搜索
     *
     * @return
     */
    boolean searchable() default true;

}
