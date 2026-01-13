package com.levin.commons.rbac;


import com.levin.commons.service.exception.AuthorizationException;
import io.swagger.v3.oas.annotations.Operation;

import java.io.Serializable;
import java.util.Map;

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
