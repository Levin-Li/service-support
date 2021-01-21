package com.levin.commons.service.domain;


import com.levin.commons.enums.VerificationMode;

import java.lang.annotation.*;

/*
 *
 * @author Laishr
 * @version 1.0,2015/11/06
 * Copyright (c) 2015.
 */
@Target({ElementType.TYPE,ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Secured {

    /**
     *
     * @return
     */
    String methodName() default "";


    /**
     * 系统名称，支持多系统的权限并存
     * <p/>
     * 如果OA子系统
     *
     * @return
     */
    String system() default "";


    /**
     * 权限分类，为二级
     * <p/>
     * 如OA子系统中的人力资源分类权限
     *
     * @return
     */
    String category() default "";

    /**
     * 类别后进行分组，为三级
     * <p/>
     * 安全分组
     * <p/>
     * <p/>
     * 如A子系统中的人力资源分类权限中的员工激励分组
     *
     * @return
     */
    String group() default "";


    /**
     * 权限名称，是第4级，如用户查看，用户编辑，用户删除等
     *
     * @return
     */
    String[] value() default {};

    /**
     * 描述信息
     *
     * @return
     */
    String desc() default "";

    /**
     * 菜单名称
     *
     * @return
     */
    String menuName() default "";

    /**
     * 菜单URL
     * @return
     */
    String menuUrl() default "";

    /**
     * 菜单初始排序
     * @return
     */
    int menuSort() default 0;

    /**
     * 菜单初始启用状态
     * @return
     */
    boolean menuEnabled() default true;

    //////////////////////////////以下部分用于访问验证///////////////////////////////

    /**
     * 需要的角色
     * 角色只要有一个就匹配通过
     * 如果超级管理员，SA_admin
     *
     * @return String[]
     */
    String[] roles() default {};


    /**
     * 复杂的检验表达式
     * <p/>
     * 默认是 Spring EL 语法
     * <p/>
     * Groovy 语法前缀加个G:
     * <p/>
     * 可用变量
     * 1、ip
     * 2、user
     * <p/>
     * <p/>
     * 如果 user.name='admin' && roles
     *
     * @return String
     */
    @Deprecated
    String expr() default "";


    /**
     * 安全审计表达式
     *
     * @return
     */
    String auditExpr() default "";

    /**
     * 用于分域注入的字段集合
     *
     * @return SecuryDomain[]
     */
    @Deprecated()
    SecuryDomain[] domainField() default {};


    /**
     * 需要注入的域
     * <p>
     * 可以字段，参数，方法
     *
     * @return
     */
    InjectVar[] injectDomains() default {};


    /**
     * 参数
     *
     * @return
     */
    Param[] param() default {};

    /**
     * 验证方式
     * @return
     */
    VerificationMode verificationMode() default VerificationMode.NONE;

    /**
     * 表单token验证
     * @return
     */
    boolean checkToken() default false;

    /**
     * 生成表单token
     * @return
     */
    boolean generateToken() default false;

}
