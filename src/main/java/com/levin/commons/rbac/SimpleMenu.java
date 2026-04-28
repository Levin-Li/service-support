package com.levin.commons.rbac;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;


/**
 * 简单资源
 */
@Schema(title = "资源")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"domain", "path"})
@FieldNameConstants

@JsonAutoDetect(
        // 只序列化字段
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        // 忽略所有 getter
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        // 忽略 isXXX
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class SimpleMenu
        implements MenuItem<MenuItem, MenuItem> {

    @Schema(title = "id")
    protected String id;

    @Schema(title = "父节点")
    protected String parentId;

    @Schema(title = "子域")
    protected String domain;

    @Schema(title = "名称")
    protected String name;

    @Schema(title = "操作按钮列表")
    protected List<OpButton> opButtonList;

    @Schema(title = "需要的授权，Json数组")
    @Column(length = 1800)
    protected List<String> requireAuthorizations;

    @Schema(title = "无权限时是否展示")
    @Column(nullable = false)
    protected Boolean alwaysShow;

    @Schema(title = "目标")
    protected String target;

    @Schema(title = "打开方式")
    @Enumerated(EnumType.STRING)
    protected MenuItem.ActionType actionType;

    @Schema(title = "路径/链接")
    protected String path;

    @Schema(title = "参数")
    @Column(length = 1800)
    protected String params;

    @Schema(title = "是否启用")
    protected boolean enable = true;

    @Schema(title = "排序码")
    protected Integer orderCode = 100;

    @Schema(title = "备注")
    protected String remark;

    @Schema(title = "图标")
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
