package com.levin.commons.service.exception;

import com.levin.commons.service.domain.ServiceResp;

/**
 * 鉴权异常 通常对应http 403 状态
 * <p>
 * 认证（Authentication）
 * 认证是指验证用户身份的过程。在信息系统中，认证确保只有合法的用户才能证明其身份并访问系统资源。认证机制通常涉及以下几种方式：
 * <p>
 * 用户名和密码认证：最常见的认证方法，用户输入用户名和密码，系统验证后允许访问。
 * 令牌（Token）认证：用户通过提供令牌（如JWT）来证明其身份。
 * 生物识别认证：通过指纹、虹膜、面部识别等生物特征进行认证。
 * 智能卡或证书认证：使用物理设备（如智能卡、YubiKey、SSL证书）来验证用户身份。
 * <p>
 * <p>
 * 鉴权（Authorization）
 * 鉴权是指确定已认证用户可以访问的资源和允许执行的操作的过程。一旦用户被认证，系统需要确定该用户被授予的权限，以确保他们只能访问授权的资源。鉴权通常涉及以下几个方面：
 * <p>
 * 角色基础鉴权（RBAC）：用户被分配一个或多个角色，每个角色有特定的权限集合。
 * 访问控制列表（ACL）：为每个资源定义一个权限列表，明确哪些用户或角色可以执行哪些操作。
 * 属性基础鉴权（ABAC）：基于用户属性（如部门、职位等）动态地授予权限。
 * 基于策略的鉴权：根据一系列预定义的策略来决定用户对资源的访问权限。
 *
 * @author llw
 */
public class AuthorizationException
        extends AccessDeniedException {

    public AuthorizationException(String friendlyTips) {
        super(friendlyTips);
    }

    public AuthorizationException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public AuthorizationException(int code, String friendlyTips, String message) {
        super(code, friendlyTips, message);
    }

    public AuthorizationException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

    @Override
    protected int getBaseCode() {
        return ServiceResp.ErrorType.AuthorizationError.code();
    }
}
