package com.levin.commons.plugin;

import com.levin.commons.service.domain.Desc;

import java.util.Collections;
import java.util.List;


/**
 * 资源抽象
 *
 * @param <DOMAIN>
 * @param <CHILD>
 * @param <PARENT>
 */
public interface ResInfo<DOMAIN, CHILD, PARENT> extends Identifiable<DOMAIN> {

    interface Operation extends Identifiable<String> {
        @Override
        default String getId() {
            return getName();
        }
    }

    /**
     * 资源类型
     *
     * @return
     */
    @Desc("资源类型")
    default String getType() {
        return null;
    }

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
     * 资源路径
     *
     * @return
     */
    @Desc("资源路径")
    default String getPath() {
        return null;
    }

    /**
     * 获取资源参数
     *
     * @return
     */
    @Desc(value = "资源参数", detail = "可以是 json，vue 路由参数 或是 url参数")
    default String getParams() {
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


    @Desc(value = "备注", detail = "")
    default String remark() {
        return "";
    }


    /**
     * 获取操作
     *
     * @return
     */
    @Desc(value = "资源操作", detail = "资源的操作项，比如新建，添加，修改，删除")
    default List<Operation> getOperations() {
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取父节点
     *
     * @return
     */
    default PARENT getParent() {
        return null;
    }

    /**
     * 获取子节点
     *
     * @return
     */
    default List<CHILD> getChildren() {
        return Collections.EMPTY_LIST;
    }

}
