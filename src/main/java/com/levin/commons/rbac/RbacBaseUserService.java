package com.levin.commons.rbac;


import cn.hutool.core.lang.Assert;
import com.levin.commons.service.exception.AuthorizationException;
import io.swagger.v3.oas.annotations.Operation;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Supplier;

import static com.levin.commons.rbac.RbacMiscUtils.isBlank;

/**
 * 用户基本服务
 *
 * @author lilw
 */
public interface RbacBaseUserService {

    /**
     * 加密密码
     * 一般的单向加密
     *
     * @param pwd 原始密码
     * @return
     */
    @Operation(summary = "加密密码")
    String encryptUserPwd(String pwd);


    /**
     * 加载用户
     *
     * @param tenantId
     * @param account  手机号或是邮箱或是其他用于登录的用户名称
     * @param <U>
     * @return
     */
    @Operation(summary = "加载用户", description = "账号可以是手机号或是邮箱等")
    <U extends RbacUserInfo> U loadUser(Serializable tenantId, String account);

    /**
     * 加载用户
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    @Operation(summary = "加载用户", description = "用户对象或是用户ID")
    <U extends RbacUserInfo> U loadUser(Serializable userPrincipal);


    @Operation(summary = "获取用户的机密数据访问级别", description = "当用户本身没有定义访问级别时,运行成本比较高,尽量不要多次调用")
    default Integer getUserConfidentialDataAccessLevel(Serializable userPrincipal) {

        RbacUserInfo loadUser = loadUser(userPrincipal);

        // 0 重要逻辑,任何角色都要检查机密数据级别,除了顶级SA账号, 其他账号都要检查
        if (loadUser.isTopSuperAdmin()) {
            return Integer.MAX_VALUE;
        }

        return loadUser.getConfidentialDataAccessLevel();
    }

    /**
     * 检查数据访问级别
     *
     * @param userPrincipal
     * @param requireDataConfidentialLevels
     * @return
     */
    @Operation(summary = "获取用户的机密数据访问级别", description = "当用户本身没有定义访问级别时,运行成本比较高,尽量不要多次调用")
    default boolean canAccessConfidentialDataByUser(Serializable userPrincipal, Integer... requireDataConfidentialLevels) {
        return canAccessConfidentialData(() -> getUserConfidentialDataAccessLevel(userPrincipal), requireDataConfidentialLevels);
    }

    /**
     * 检查数据访问级别
     *
     * @param userConfidentialDataAccessLevelSupplier 用户的机密数据访问级别
     * @param requireDataConfidentialLevels           目标数据机密级别  , null 表示非机密数据
     * @return
     */
    default boolean canAccessConfidentialData(Supplier<Integer> userConfidentialDataAccessLevelSupplier, Integer... requireDataConfidentialLevels) {

        if (requireDataConfidentialLevels == null
                || requireDataConfidentialLevels.length == 0) {
            return true;
        }

        if (!(userConfidentialDataAccessLevelSupplier instanceof CacheSupplier)) {
            //缓存提升性能
            userConfidentialDataAccessLevelSupplier = new CacheSupplier<>(userConfidentialDataAccessLevelSupplier);
        }

        for (Integer requireDataConfidentialLevel : requireDataConfidentialLevels) {

            //非机密数据, 允许访问
            if (requireDataConfidentialLevel == null) {
                continue;
            }

            Integer userConfidentialDataAccessLevel = userConfidentialDataAccessLevelSupplier.get();

            if (userConfidentialDataAccessLevel == null
                    || userConfidentialDataAccessLevel < requireDataConfidentialLevel) {
                return false;
            }
        }

        return true;
    }

    /**
     * 是否能管理指定用户
     *
     * @param operator
     * @param targetUser
     * @return
     */
    @Operation(summary = "是否能管理指定用户", description = "在不考虑操作权限的情况下")
    default boolean canAdminUser(Serializable operator, Serializable targetUser) {

        Assert.notNull(operator, "无操作人");
        Assert.notNull(targetUser, "无目标用户");

        // 自己
        if (operator.equals(targetUser)) {
            return true;
        }

        RbacUserInfo operatorInfo = loadUser(operator);
        Assert.notNull(operatorInfo, "无操作人信息");

        //1
        if (operatorInfo.isTopSuperAdmin()) {
            return true;
        }

        RbacUserInfo targetUserInfo = loadUser(targetUser);
        Assert.notNull(targetUserInfo, "无目标用户信息");

        //2 自己
        if (operatorInfo.getId().equals(targetUserInfo.getId())) {
            return true;
        }

        ///////////////////////////////////////
        //检查跨租户
        //不能夸租户管理
        final boolean isSaasTargetUser = isBlank(targetUserInfo.getTenantId());
        final boolean isOperatorSaasUser = isBlank(operatorInfo.getTenantId());

        //是SAAS 角色, 但是用户不是 SAAS用户
        if (isSaasTargetUser && !isOperatorSaasUser) {
            // matchErrorConsumer.accept(roleCode, "用户不可管理");
            return false;
        }

        //如果是有租户的角色, 要求用户必须是saas或是同个租户
        if (!isSaasTargetUser && !(isOperatorSaasUser || targetUserInfo.getTenantId().equals(operatorInfo.getTenantId()))) {
            // matchErrorConsumer.accept(roleCode, "跨租户校验失败");
            return false;
        }
        /// ///////////////////////////////////////

        //3 机密级别不够
        if (!canAccessConfidentialData(() -> getUserConfidentialDataAccessLevel(operatorInfo), targetUserInfo.getConfidentialLevel())) {
            return false;
        }

        //4 目标用户是超管,操作人也要超管
        if (targetUserInfo.isSuperAdmin()) {
            return operatorInfo.isSuperAdmin();
        }

        //4 是超管
        if (operatorInfo.isSuperAdmin()) {
            return true;
        }

        //5 目标用户是超管,操作人也要超管
        if (targetUserInfo.isSaasAdmin()) {
            return operatorInfo.isSaasAdmin();
        }

        if (operatorInfo.isSaasAdmin()) {
            return true;
        }

        //6 目标用户是SAAS用户,操作人也要SAAS用户
        if (targetUserInfo.isSaasUser()) {
            return operatorInfo.isSaasUser();
        }

        if (targetUserInfo.isTenantAdmin()) {
            return operatorInfo.isSaasUser()
                    || operatorInfo.isTenantAdmin();
        }

        //同级可以管理,只要有权限就行
        return true;
    }


    /**
     * 审计用户
     * 检查用户的状态, 到期, 是否被禁用等
     *
     * @param userInfo
     * @return
     * @throws AuthorizationException
     */
    @Operation(summary = "审计用户", description = "检查用户状态,到期,是否被禁用等")
    <U extends RbacUserInfo> U auditUser(U userInfo) throws AuthorizationException;

    /**
     * 审计用户登录
     * 检查用户登录是否合法, 包括登录密码, 域名, IP, 设备类型
     *
     * @param userInfo
     * @param tenantId
     * @param loginPwd        为空则不验证
     * @param loginDomain     为空则不验证
     * @param loginIp
     * @param loginDeviceType
     * @param exLoginParams   额外的登录参数
     * @return
     * @throws AuthorizationException
     */
    @Operation(summary = "审计用户登录", description = "检查用户登录是否合法, 包括登录密码, 登录域名, 登录IP, 设备类型等")
    <U extends RbacUserInfo> U auditUserLogin(U userInfo, String tenantId, String loginPwd, String loginDomain, String loginIp, String loginDeviceType, Map<String, Serializable> exLoginParams) throws AuthorizationException;

}
