package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;


/**
 * 简单资源
 */
@Schema(description = "资源")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"domain", "path"})
@FieldNameConstants
public class SimpleMenu
        implements MenuItem<MenuItem, MenuItem> {

    @Override
    public <P extends MenuItem> P getParent() {
        return null;
    }

    @Override
    public Boolean hasChildren() {
        return false;
    }

    @Override
    public <C extends MenuItem> Collection<C> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public <ID extends Serializable> ID getId() {
        return null;
    }

    @Schema(description = "子域")
    protected String domain;

    @Schema(description = "名称")
    protected String name;

    @Schema(description = "需要的授权，Json数组")
    @Column(length = 1800)
    protected String requireAuthorizations;

    @Schema(description = "无权限时是否展示")
    @Column(nullable = false)
    protected Boolean alwaysShow;

    @Schema(description = "目标")
    protected String target;

    @Schema(description = "打开方式")
    @Enumerated(EnumType.STRING)
    protected MenuItem.ActionType actionType;

    @Schema(description = "路径/链接")
    protected String path;

    @Schema(description = "参数")
    @Column(length = 1800)
    protected String params;

    @Schema(description = "是否启用")
    protected boolean enable = true;

    @Schema(description = "排序码")
    protected Integer orderCode = 100;

    @Schema(description = "备注")
    protected String remark;

    @Schema(description = "图标")
    protected String icon;

    @Override
    public boolean isAlwaysShow() {
        return Boolean.TRUE.equals(alwaysShow);
    }


    @Override
    public String toString() {
        return String.format("%s:%s", domain, path);
    }

}
