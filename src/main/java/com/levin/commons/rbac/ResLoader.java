package com.levin.commons.rbac;

import com.levin.commons.service.domain.Identifiable;

import java.util.Collection;
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
     * @param resType  资源类型
     * @param loadDeep 加载层级 , -1 表示不限制层级，0 表示只加载自己
     * @return
     */
    <R extends Res> Collection<R> getResItems(String resType, int loadDeep);

    /**
     * 获取下级资源列表
     *
     * @param resType  资源类型
     * @param resId    资源ID
     * @param loadDeep 加载层级 , -1 表示不限制层级，0 表示只加载自己
     * @param <R>
     * @return
     */
    <R extends Res> Collection<R> getSubItems(String resType, String resId, int loadDeep);

}
