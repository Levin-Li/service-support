package com.levin.commons.rbac;

import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.service.domain.Castable;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.SimpleIdentifiable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 菜单
 */
@Schema(title = "抽象菜单项")
public interface MenuItem<PARENT extends MenuItem, CHILD extends MenuItem>
        extends TreeObject<PARENT, CHILD>, AuthorizedObject, SimpleIdentifiable {

    @Schema(title = "动作类型")
    enum ActionType implements EnumDesc {

        @Schema(title = "默认", description = "具体如何展示,前端自行处理")
        Default,

        @Schema(title = "Tab栏")
        TabPanel,

        @Schema(title = "模态窗口")
        ModalWindow,

        @Schema(title = "新浏览器窗口", description = "新浏览器窗口打开path指定的页面加参数params")
        NewWindow,

        @Schema(title = "地址栏重定向")
        Redirect,

        @Schema(title = "jsonp回调", description = "调用远程path指定的远程js, 同时携带参数params")
        Jsonp,

        @Schema(title = "服务端动作", description = "调用path指定的API, 同时携带参数params")
        ServerSideAction;

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }


    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    @Schema(title = "操作按钮")
    class OpButton implements Castable, Serializable {

        @Schema(title = "api地址")
        protected String apiUrl;

        @Schema(title = "标签")
        @Column(nullable = false)
        protected String label;

        @Schema(title = "需要权限")
        protected String requireAuthorization;

        @Schema(title = "是否禁用", description = "禁用后页面中不显示")
        protected Boolean disabled;

        @Schema(title = "备注")
        protected String remark;

        public boolean isDisabled() {
            return disabled != null && disabled;
        }
    }


    @Schema(title = "操作按钮列表", description = "通常是控制器中有CRUD.OP注解的方法")
    default List<OpButton> getOpButtonList() {
        return Collections.emptyList();
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
    @Schema(title = "target", description = "href中的target")
    default String getTarget() {
        return null;
    }

    /**
     * 资源路径
     *
     * @return
     */
    @Schema(title = "资源路径")
    default String getPath() {
        return null;
    }

    /**
     * 获取资源参数
     *
     * @return
     */
    @Schema(title = "资源参数", description = "可以是 json，vue 路由参数 或是 url参数")
    default String getParams() {
        return null;
    }

    /**
     * 获取资源图标
     *
     * @return
     */
    @Schema(title = "资源图标")
    default String getIcon() {
        return null;
    }

    /**
     * 没权限时是否展示
     *
     * @return
     */
    @Schema(title = "是否总是显示", description = "当没有权限的时候，是否显示，true为显示，false不显示")
    default boolean isAlwaysShow() {
        return false;
    }
}
