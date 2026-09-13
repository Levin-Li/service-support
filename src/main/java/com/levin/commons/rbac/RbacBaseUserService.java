package com.levin.commons.rbac;


import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.domain.DomainObject;
import com.levin.commons.service.exception.AuthorizationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Supplier;


/**
 * 用户基本服务
 *
 * 基础实现不具备领域目录时，非空领域默认拒绝；完整领域授权由 {@link RbacBaseService} 提供。
 *
 * @author echo
 */
@Tag(name = "RBAC 用户服务", description = "基础用户服务的非空领域对象默认拒绝访问，不会回退为允许；完整领域、租户和数据范围授权由 RbacBaseService 提供。机密级别优先使用用户自身配置，缺失时由完整实现按角色补足；单次判定可复用临时缓存，但不改变授权结果。")
public interface RbacBaseUserService {

    /**
     * 加密密码
     * 一般的单向加密
     *
     * @param pwd 原始密码
     * @return
     */
    @Operation(summary = "加密密码", description = "将原始密码转换为存储或校验使用的密文；具体算法、盐值和版本由实现决定，调用方不得依赖密文可逆或跨实现一致。")
    String encryptUserPwd(String pwd);

    /**
     * 加载用户
     *
     * @param tenantId
     * @param account  手机号或是邮箱或是其他用于登录的用户名称
     * @param <U>
     * @return
     */
    @Operation(summary = "按租户和账号加载用户", description = "账号可以是手机号、邮箱或实现支持的登录标识；租户边界由实现解析，未找到用户时返回值由实现约定，不代表已通过登录或权限审计。")
    <U extends RbacUserInfo> U loadUser(Serializable tenantId, String account);

    /**
     * 加载用户
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    @Operation(summary = "按主体加载用户", description = "参数可以是用户对象或用户 ID；该方法只解析用户主体，不执行登录状态、领域、租户或动作权限审计。")
    <U extends RbacUserInfo> U loadUser(Serializable userPrincipal);

    @Operation(summary = "获取用户的机密数据访问级别", description = "基础实现只返回用户自身级别，不回退计算角色级别；需要角色回退语义时使用 RbacBaseService 的覆盖实现。")
    default Integer getUserConfidentialDataAccessLevel(Serializable userPrincipal) {

        RbacUserInfo loadUser = userPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) userPrincipal
                : loadUser(userPrincipal);

        // 0 重要逻辑,任何角色都要检查机密数据级别,除了顶级SA账号, 其他账号都要检查
        if (loadUser.isTopSuperAdmin()) {
            return Integer.MAX_VALUE;
        }

        //如果用户没有设置机密数据访问级别, 但是角色有机密数据访问级别, 则返回该级别
        //@todo 获取用户角色的机密数据访问级别, 但是要防止递归
        //考虑缓存

        return loadUser.getConfidentialDataAccessLevel();
    }

    /**
     * 检查数据访问级别
     *
     * @param userPrincipal
     * @param requireDataConfidentialLevels
     * @return
     */
    @Operation(summary = "检查用户机密数据访问级别", description = "逐项比较所需机密级别；任一非公开目标的用户级别为空或不足即拒绝。一次调用内会复用级别读取结果；基础实现不回退角色级别，完整实现可提供角色回退。")
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

            //非机密数据或平台公开数据，不限制访问者的机密数据访问级别。
            if (requireDataConfidentialLevel == null
                    || requireDataConfidentialLevel == ConfidentialLevel.PLATFORM_PUBLIC.code()) {
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
     * 对象领域访问门槛。基础用户服务没有领域目录，非空领域默认拒绝；
     * RbacBaseService 提供完整的授权与目录有效性检查。
     */
    @Operation(summary = "检查对象领域访问", description = "基础用户服务没有领域目录：对象领域为空时允许，领域非空时默认拒绝，不会回退为允许；完整领域授权请使用 RbacBaseService。")
    default boolean canAccessObjectDomain(Serializable userPrincipal, DomainObject object) {
        return object != null && RbacMiscUtils.isBlank(object.getDomainId());
    }

    /** 用户管理入口的领域门槛，完整 RBAC 服务同时检查所属租户。 */
    @Operation(summary = "检查用户领域访问", description = "沿用对象领域门槛：基础实现对非空领域默认拒绝；完整 RBAC 服务会追加领域目录和租户校验。")
    default boolean canAccessUserDomain(Serializable userPrincipal, RbacUserInfo targetUser) {
        return canAccessObjectDomain(userPrincipal, targetUser);
    }

    /**
     * 是否能管理指定用户
     *
     * @param operator
     * @param targetUser
     * @return
     */
    @Operation(summary = "操作者是否能管理指定用户", description = "不检查业务动作权限，但领域门槛优先于自管和管理员快捷路径；随后拒绝不允许的跨租户、机密级别不足及管理员层级倒置。任一门槛失败即拒绝。")
    default boolean canAdminUser(Serializable operator, Serializable targetUser) {

        Assert.notNull(operator, "无操作人");
        Assert.notNull(targetUser, "无目标用户");

        RbacUserInfo operatorInfo = loadUser(operator);
        Assert.notNull(operatorInfo, "无操作人信息");
        RbacUserInfo targetUserInfo = loadUser(targetUser);
        Assert.notNull(targetUserInfo, "无目标用户信息");

        // 领域门槛先于自我管理和管理员快捷路径。
        if (!canAccessUserDomain(operatorInfo, targetUserInfo)) {
            return false;
        }
        if (operatorInfo.isTopSuperAdmin()) {
            return true;
        }

        //2 自己
        if (operatorInfo.getId().equals(targetUserInfo.getId())) {
            return true;
        }

        ///////////////////////////////////////
        //检查跨租户
        //不能跨租户管理
        final boolean isSaasTargetUser = RbacMiscUtils.isBlank(targetUserInfo.getTenantId());
        final boolean isOperatorSaasUser = RbacMiscUtils.isBlank(operatorInfo.getTenantId());

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
        if (!canAccessConfidentialDataByUser(operatorInfo, targetUserInfo.getConfidentialLevel())) {
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

        //6 目标用户是平台用户,操作人也要平台用户
        if (targetUserInfo.isPlatformUser()) {
            return operatorInfo.isPlatformUser();
        }

        if (targetUserInfo.isTenantAdmin()) {
            return operatorInfo.isPlatformUser()
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
    @Operation(summary = "审计用户", description = "检查用户状态、到期和禁用等可用性条件；任一审计条件不满足时抛出 AuthorizationException，不会回退为可用用户。")
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
    @Operation(summary = "审计用户登录", description = "在用户审计基础上校验登录密码、域名、IP、设备类型和扩展参数；传入为空的可选登录条件可由实现跳过校验，其余任一失败均拒绝登录并抛出 AuthorizationException。")
    <U extends RbacUserInfo> U auditUserLogin(U userInfo, Serializable tenantId, String loginPwd, String loginDomain, String loginIp, String loginDeviceType, Map<String, Serializable> exLoginParams) throws AuthorizationException;

}
