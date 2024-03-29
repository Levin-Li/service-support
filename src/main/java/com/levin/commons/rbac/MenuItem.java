package com.levin.commons.rbac;

import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.SimpleIdentifiable;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 菜单
 */
@Schema(description = "抽象菜单项")
public interface MenuItem<PARENT extends MenuItem, CHILD extends MenuItem>
        extends TreeObject<PARENT, CHILD>, AuthorizedObject, SimpleIdentifiable {

    @Schema(description = "动作类型")
    enum ActionType implements EnumDesc {

        @Schema(description = "默认")
        Default,

        @Schema(description = "模态窗口")
        ModalWindow,

        @Schema(description = "Tab栏")
        TabPanel,

        @Schema(description = "Tab栏Iframe")
        TabPanelIFrame,

        @Schema(description = "新浏览器窗口")
        NewWindow,

        @Schema(description = "地址栏重定向")
        Redirect,

        @Schema(description = "路径重写")
        Rewrite,

        @Schema(description = "jsonp回调")
        Jsonp,

        @Schema(description = "服务端动作")
        ServerSideAction
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
    @Schema(description = "target", title = "href中的target")
    default String getTarget() {
        return null;
    }

    /**
     * 资源路径
     *
     * @return
     */
    @Schema(description = "资源路径")
    default String getPath() {
        return null;
    }

    /**
     * 获取资源参数
     *
     * @return
     */
    @Schema(description = "资源参数", title = "可以是 json，vue 路由参数 或是 url参数")
    default String getParams() {
        return null;
    }

    /**
     * 获取资源图标
     *
     * @return
     */
    @Schema(description = "资源图标")
    default String getIcon() {
        return null;
    }

    /**
     * 没权限时是否展示
     *
     * @return
     */
    @Schema(description = "是否总是显示", title = "当没有权限的时候，是否显示，true为显示，false不显示")
    default boolean isAlwaysShow() {
        return false;
    }
}
