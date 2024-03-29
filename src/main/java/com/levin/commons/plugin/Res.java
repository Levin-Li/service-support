package com.levin.commons.plugin;

import com.levin.commons.service.domain.SimpleIdentifiable;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * 资源描述
 * <p>
 * 对应注解类 ResAuthorize
 */
public interface Res extends SimpleIdentifiable {

    /**
     * 归属域
     * <p>
     * 通常是子系统，子模块等
     *
     * @return
     */
    @Schema(description = "资源域")
    <T extends Serializable> T getDomain();

    /**
     * 资源类型
     *
     * @return
     */
    @Schema(description = "资源类型")
    <T extends Serializable> T getType();

    /**
     * 获取资源图标
     *
     * @return
     */
    @Schema(description = "资源图标")
    default String getIcon() {
        return null;
    }

    /**
     * 没权限时是否展示
     *
     * @return
     */
    @Schema(title = "是否总是显示", description = "当没有权限的时候，是否显示，true为显示，false不显示")
    default boolean isAlwaysShow() {
        return false;
    }

    /**
     * 获取操作列表
     *
     * @return
     */
    @Schema(title = "资源操作", description = "资源的操作项，比如新建，添加，修改，删除")
    default <O extends Action> Collection<O> getActionList() {
        return Collections.EMPTY_LIST;
    }

    /**
     * 操作权限
     * 和注解完全一致
     *
     * @see com.levin.commons.rbac.ResAuthorize
     */
    interface Action extends SimpleIdentifiable {

        /**
         * 是否忽略权限要求
         *
         * @return
         */
        default boolean isIgnored() {
            return false;
        }

        /**
         * 是否仅要求登录
         *
         * @return
         */
        default boolean isOnlyRequireAuthenticated() {
            return false;
        }

        /**
         * 与验证模式
         *
         * @return
         */
        default boolean isAndMode() {
            return false;
        }

        /**
         * 操作权限验证表达式
         *
         * @return
         */
        default String getVerifyExpression() {
            return "";
        }

        /**
         * 可操作的角色列表
         *
         * @return
         */
        default List<String> getAnyRoles() {
            return Collections.EMPTY_LIST;
        }

    }

}
