package com.levin.commons.plugin;

import com.levin.commons.service.domain.Desc;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 菜单资源
 */
public interface MenuItem extends ResInfo<MenuItem, MenuItem> {

    enum ActionType implements EnumDesc {
        @Schema(description = "默认")
        Default,

        @Schema(description = "Tab栏")
        TabPanel, //TabPanel，默认包含

        @Schema(description = "Tab栏Iframe")
        TabPanelIFrame, //TabPanel，默认包含

        @Schema(description = "新窗口")
        NewWindow, //新窗口打开

        @Schema(description = "地址栏重定向")
        Redirect, //地址栏重定向

        @Schema(description = "模态窗口")
        ModalWindow, //以模态窗口方式

        @Schema(description = "JS")
        JavaScript, //执行js

        @Schema(description = "服务端动作")
        ServerSideAction //执行服务端动作
    }

    @Override
    default String getType() {
        return "系统菜单";
    }

    /**
     * 获取动作类型
     *
     * @return
     */
    default ActionType getActionType() {
        return ActionType.Default;
    }

    /**
     * <a href="#" target="_blank">链接</a>
     * <p>
     * target的值
     * target="_blank":在新窗口中浏览新的页面。
     * target="_self":在同一个窗口打开新的页面。
     * target="_parent":在父窗口中打开新的页面。（页面中使用框架才有用）
     * target="_top" :以整个浏览器作为窗口显示新页面。（突破了页面框架的限制）
     *
     * @return
     */
    @Desc(value = "target", detail = "href中的target")
    default String getTarget() {
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

}
