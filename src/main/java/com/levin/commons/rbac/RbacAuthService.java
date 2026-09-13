package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.Serializable;
import java.util.Map;

/**
 * 简单认证服务
 * @author lilw
 */
@Tag(name = "RBAC 认证服务", description = "认证成功后返回 token；失效或登出后 token 不得再用于获取登录主体。未登录时 getLoginId 必须拒绝并抛出异常，不允许回退为空主体。")
public interface RbacAuthService<TOKEN extends Serializable, UID extends Serializable> {

    /**
     * 是否登录
     *
     * @return
     */
    @Operation(summary = "检查是否已登录", description = "仅返回当前认证上下文是否存在有效登录态，不触发认证或 token 刷新。")
    boolean isLogin();

    /**
     * 获取登录用户ID
     * <p>
     * 必须返回数据，如果用户没有登录，必须抛出异常
     *
     * @return
     */
    @Operation(summary = "获取当前登录用户 ID", description = "当前未登录时必须拒绝并抛出异常；不得返回 null 或匿名主体作为回退。")
    UID getLoginId();

    /**
     * 获取登录Token
     *
     * @return
     */
    @Operation(summary = "获取当前登录令牌", description = "返回当前认证上下文 token；未登录或令牌失效时的返回/异常语义由实现定义。")
    TOKEN getLoginToken();

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @Operation(summary = "获取当前登录用户", description = "返回当前登录主体信息；该读取不替代领域、数据范围或资源动作授权。")
    <U extends RbacUserInfo> U getUserInfo();

    /**
     * 认证，并返回token
     *
     * @param authReq 认证请求
     * @param extras  附加参数
     * @return 认证成功后的token
     */
    @Operation(summary = "按认证请求登录", description = "认证通过后返回 token；默认实现拒绝未实现的认证请求。认证失败不得回退为已登录状态。")
    default <REQ extends AuthReq> TOKEN auth(REQ authReq, Map<String, Object>... extras) {
        throw new UnsupportedOperationException("未实现认证");
    }

    /**
     * 直接认证，并返回token
     *
     * @param loginId 登录标识
     * @param extras  附加参数
     * @return 认证成功后的token
     */
    @Operation(summary = "按登录标识认证", description = "实现验证登录标识及扩展条件后返回 token；认证失败应拒绝，不得生成可用 token。")
    TOKEN auth(UID loginId, Map<String, Object>... extras);

    /**
     * 获取登录ID
     *
     * @param token
     * @return
     */
    @Operation(summary = "按令牌获取登录用户 ID", description = "仅有效且未失效的 token 可解析为用户 ID；无效 token 的处理由实现拒绝或返回空值。")
    UID getLoginIdByToken(TOKEN token);

    /**
     * 使 token 失效
     *
     * @param token
     */
    @Operation(summary = "使令牌失效", description = "使指定 token 后续认证失败；不会影响其他 token，失效结果不得被缓存的旧认证状态绕过。")
    void invalidate(TOKEN token);


    /**
     * 用户登出
     */
    @Operation(summary = "当前用户登出", description = "清除当前认证上下文并使当前登录态失效；后续需要重新认证。")
    void logout();

}
