package com.levin.commons.plugin;

import com.levin.commons.rbac.Res;
import com.levin.commons.service.domain.SimpleIdentifiable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;

/**
 * 资源加载器
 *
 * @author llw
 * @version 1.0
 * @since 1.1.17
 */
@Tag(name = "插件资源加载器", description = "提供插件声明的资源类型与资源树；资源加载本身不执行 RBAC 授权，调用方应在展示或操作资源前执行相应的权限校验。")
public interface ResLoader {

    /**
     * 获取资源类型
     *
     * @return
     */
    @Operation(summary = "获取插件全部资源类型", description = "返回插件声明的资源类型，不包含菜单类型；结果是资源目录，不代表当前用户已获得访问或操作权限。")
    Collection<SimpleIdentifiable> getResTypes();

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
     * @param resTypeId 资源类型ID
     * @param loadDeep  加载层级 , -1 表示不限制层级，0 表示只加载自己
     * @return
     */
    @Schema(title = "获取插件的资源", description = "资源：比如地区资源，用户资源，部门资源，文档资源，栏目资源. 正常需要和权限模块结合处理")
    <R extends Res> Collection<R> getResItems(String resTypeId, int loadDeep);

    /**
     * 获取下级资源列表
     *
     * @param resTypeId 资源类型ID
     * @param resId     资源ID
     * @param loadDeep  加载层级 , -1 表示不限制层级，0 表示只加载自己
     * @param <R>
     * @return
     */
    @Schema(title = "获取下级资源列表", description = "获取下级资源列表")
    <R extends Res> Collection<R> getSubItems(String resTypeId, String resId, int loadDeep);

}
