package com.levin.commons.plugin;


import java.util.List;

/**
 * 插件规范
 *
 * @author llw
 */
public interface Plugin extends Identifiable<String> {

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
     * 插件拥有的数据资源
     * <p>
     * 插件定义的资源不包含菜单
     *
     * <p>
     * <p>
     * 资源：比如地区资源，用户资源，部门资源，文档资源，栏目资源
     * 正常需要和权限模块结合处理
     * 资源通常是树形结构
     *
     * @return
     */
    List<DataItem> getDataItems();


    /**
     * 获取菜单项
     * <p>
     * 菜单的权限由权限管理模块处理
     *
     * @return
     */
    List<MenuItem> getMenuItems();


    /**
     * 销毁插件
     */
    void destroy() throws PluginException;

}
