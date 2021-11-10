package com.levin.commons.rbac;

import com.levin.commons.service.domain.Desc;
import com.levin.commons.service.domain.Identifiable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * 资源描述
 * <p>
 * 对应注解类 ResAuthorize
 */
public interface Res extends Identifiable {

    /**
     * 归属域
     * <p>
     * 通常是子系统，子模块等
     *
     * @return
     */
    @Desc("资源域")
    <T extends Serializable> T getDomain();

    /**
     * 资源类型
     *
     * @return
     */
    @Desc("资源类型")
    <T extends Serializable> T getType();

    /**
     * 获取资源图标
     *
     * @return
     */
    @Desc(value = "资源图标")
    default String getIcon() {
        return null;
    }

    /**
     * 没权限时是否展示
     *
     * @return
     */
    @Desc(value = "是否总是显示", detail = "当没有权限的时候，是否显示，true为显示，false不显示")
    default boolean isAlwaysShow() {
        return false;
    }

    /**
     * 获取操作列表
     *
     * @return
     */
    @Desc(value = "资源操作", detail = "资源的操作项，比如新建，添加，修改，删除")
    default <O extends Action> Collection<O> getActionList() {
        return Collections.EMPTY_LIST;
    }

    /**
     *
     * 操作权限
     */
    interface Action extends Identifiable {

        /**
         * 与验证模式
         *
         * @return
         */
        default boolean isAndMode() {
            return false;
        }

        /**
         * 权限验证表达式
         *
         * @return
         */
        default String getVerifyExpression() {
            return "";
        }

        /**
         * 权限验证表达式
         *
         * @return
         */
        default List<String> getAnyRoles() {
            return Collections.EMPTY_LIST;
        }

    }

}
