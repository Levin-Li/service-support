package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;

import java.lang.annotation.*;

/**
 * 资源访问验证
 * <p>
 * 可以注解在类上，表示应用在所有的方法上。
 * <p>
 * 在调用方法前验证方法是否指定的资源授权。
 * <p>
 * 该注解出于简单考虑
 * <p>
 * <p>
 * 复杂的判断建议使用表达式
 * <p>
 * <p>
 * 注意，可以设置空格覆盖类定义
 *
 * @author llw
 */

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@GenNameConstant
public @interface ResAuthorize {

    /**
     * 资源域
     * 可从父对象获取
     * 支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String domain() default "";

    /**
     * 资源类型
     * 可从父对象获取
     * 支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String type() default "";

    /**
     * 资源
     * 可从父对象获取
     * 支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String res() default "";

    ///////////////////// 资源许可 Permission //////////////////////

    /**
     * 授权的操作
     * 支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String action() default "";


    /**
     * 操作类型
     * 创建,查询,更新,删除
     * Create,Update,Delete
     *
     * @return
     */
    ActionType[] actionTypes() default {};

    /// //////////////////////////////////////////////////////////////////
    /**
     * 忽略的
     * 不做资源授权检查
     *
     * @return
     */
    boolean ignored() default false;

    /**
     * 仅要求认证，不做资源授权
     * <p>
     * 默认是要做资源授权
     *
     * @return
     */
    boolean onlyRequireAuthenticated() default false;

    /**
     * 权限，角色和表达式3个条件是否都必须满足，否则任意一个满足即视为验证通过
     * <p>
     * 默认任意一个条件满足都可以
     *
     * @return
     */
    boolean isAndMode() default false;

    /**
     * 要求的角色，任意一个满足都视为验证通过
     * 角色支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String[] anyRoles() default {};

    /**
     * 要求的用户类型，任意一个满足都视为验证通过
     * 用户支持 * 通配符，支持|或选择符
     * <p>
     * 注意，用户类型要求属于固定匹配要求, 和 isAndMode() 无关
     *
     * @return
     */
    String[] anyUserTypes() default {};

    /**
     * 验证表达式
     * <p>
     * 建议 应用 spel 表达式
     * 建议应用 spring security 表达式
     *
     * @return 表达式应该返回 true 或 false
     */
    String verifyExpression() default "";

    ///////////////////////////////////////////////////////////////////////

    /**
     * 访问需要的保密级别
     * 数值越大，级别越高
     *
     * @return
     */
    int confidentialLevel() default 0;

    //////////////////////////////////////////////////////////////////

    /**
     * 因为注解不允许空值, 所有采用字符串来定义
     * 数据权限范围, 默认为空表示未定义
     * 取值范围, 参考枚举类:OrgDataScope
     * 期望指定部门时, 直接在本属性中填入部门ID,部门ID直接用逗号分隔
     * eg
     * Id123,id234,id567
     *
     * @return
     */
    String orgDataScope() default "";

    /**
     * 备注
     *
     * @return
     */
    String remark() default "角色支持 * 通配符";
}
