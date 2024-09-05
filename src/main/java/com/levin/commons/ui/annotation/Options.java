package com.levin.commons.ui.annotation;

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
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Options {

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
     * 系统的字典编码
     * <p>
     *
     * @return
     */
    String dictCode() default "";

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
     *
     * @return
     */
    String[] columnMapExpr() default {"value", "label", "desc"};

    /**
     * 级联下拉列表时，其他属性变更时，需要重新加载
     * <p>
     * 只正对API或是查询对象有效
     * <p>
     * 如果不为空，则表示默认不加载内容
     *
     * @return
     */
    String reloadOnAttrChanged() default "";

    /**
     * Dao查询类或是枚举类
     *
     * @return
     */
    Class<?> queryObjectOrEnumClass() default Void.class;

    /**
     * API接口
     * <p>
     * 建议通过权限标识来控制权限和匹配URL
     * <p>
     * 不建议使用通用的API接口，所有的API接口都通过控制器来定义。
     *
     * @return
     */
    String api() default "";

    /**
     * 是否可以搜索
     *
     * @return
     */
    boolean searchable() default true;

    /**
     * 查询参数，URL字符串参数
     * <p>
     * 包含自动补全搜索参数名称，主要搜索服务端，输入框的参数名称：searchKeywords
     * <p>
     * <p>
     * 支持百度Amis的变量
     *
     * @return
     */
    String searchParams() default "";

}
