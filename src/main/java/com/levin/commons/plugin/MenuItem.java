package com.levin.commons.plugin;

import com.levin.commons.service.domain.Desc;

/**
 *
 * 菜单资源
 *
 */
public interface MenuItem extends ResInfo<String, MenuItem, MenuItem> {

    enum ActionType {
        Default,
        TabPanel, //TabPanel，默认包含
        TabPanelIFrame, //TabPanel，默认包含
        NewWindow, //新窗口打开
        Redirect, //地址栏重定向
        ModalWindow, //以模态窗口方式
        JavaScript, //执行js
        ServerSideAction //执行服务端动作
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

}
