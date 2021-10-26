package com.levin.commons.plugin;

import java.util.List;

/**
 * 资源加载器
 *
 * @author llw
 * @version 1.0
 * @since 1.1.17
 */
public interface ResLoader {

    /**
     * 获取资源类型
     *
     * @return
     */
    List<Identifiable> getResTypes();

    /**
     * 获取菜单项
     * <p>
     * 菜单的权限由权限管理模块处理
     *
     * @return
     */
    List<MenuItem> getMenuItems();


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
     * @param resTypeIds 资源类型 ID
     * @return
     */
    List<DataItem> getDataItems(String... resTypeIds);

    /**
     * 获取下级资源列表
     *
     * @param resTypeId 资源类型
     * @param resId     资源ID
     * @param <R>
     * @return
     */
    <R extends ResInfo> List<R> getSubItems(String resTypeId, String resId);

}
