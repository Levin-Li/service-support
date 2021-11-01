package com.levin.commons.plugin;


import com.levin.commons.rbac.MenuItem;
import com.levin.commons.rbac.ResLoader;
import com.levin.commons.service.domain.Identifiable;

import java.util.Collections;
import java.util.List;

/**
 * 插件接口规范
 *
 * @author llw
 * @version 1.0
 * @since 1.1.17
 */
public interface Plugin extends Identifiable {

    /**
     * 插件类型
     * <p>
     * 如系统插件，应用插件
     *
     * @return
     */
    default String getType() {
        return "";
    }

    /**
     * 获取插件菜单列表
     *
     * @return
     */
    default <M extends MenuItem> List<M> getMenuList() {
        return Collections.emptyList();
    }

    /**
     * 获取插件的资源加载器
     *
     * @return
     */
    default ResLoader getResLoader() {
        return null;
    }

    /**
     * 插件实现该方法，接收发送给插件的事件
     *
     * @param events
     * @return 返回事件是否已经接受
     */
    boolean onEvent(Object... events);

    /**
     * 销毁插件
     */
    default void destroy() throws PluginException {
    }

}
