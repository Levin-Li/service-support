package com.levin.commons.plugin;

import com.levin.commons.service.domain.Desc;

import java.util.Collection;
import java.util.Collections;


/**
 * 资源抽象
 *
 * @param <PARENT>
 * @param <CHILD>
 */
public interface ResInfo<PARENT, CHILD>
        extends Identifiable {

    /**
     * 归属域
     * <p>
     * 通常是子系统，子模块等
     *
     * @return
     */
    @Desc("资源域")
    String getDomain();

    /**
     * 资源类型
     *
     * @return
     */
    @Desc("资源类型")
    String getType();

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
     * 授权标识
     * 逗号隔开
     * eg.
     * userManager:user:add
     *
     * @return
     */
    default String getRequireAuthorizations() {
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
     * 获取操作
     *
     * @return
     */
    @Desc(value = "资源操作", detail = "资源的操作项，比如新建，添加，修改，删除")
    default <O extends Operation> Collection<O> getOperations() {
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取父节点
     *
     * @return
     */
    default <P extends PARENT> P getParent() {
        return null;
    }

    /**
     * 获取子节点
     *
     * @return
     */
    default <C extends CHILD> Collection<C> getChildren() {
        return Collections.EMPTY_LIST;
    }


    /**
     * 操作权限
     */
    interface Operation extends Identifiable {

        /**
         * 授权标识
         * 逗号隔开
         * eg.
         * userManager:user:add
         *
         * @return
         */
        default String getRequireAuthorizations() {
            return null;
        }

    }

}
