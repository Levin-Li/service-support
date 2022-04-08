package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.Arrays;
import java.util.List;


/**
 * 资源操作
 * @see com.levin.commons.rbac.ResAuthorize
 * @see com.levin.commons.rbac.Res.Action
 */
@Schema(description = "资源操作")
@Data
@Accessors(chain = true)
@FieldNameConstants
@EqualsAndHashCode(of = "id")
public class SimpleResAction
        implements Res.Action {

    @Schema(description = "ID", required = true)
    protected String id;

    ///////////////////////////////////////////////////////////
    @Schema(description = "是否忽略")
    boolean ignored;

    /**
     * 仅要求认证，不做资源授权
     * <p>
     * 默认是要做资源授权
     *
     * @return
     */
    @Schema(description = "仅要求认证")
    boolean onlyRequireAuthenticated;

    @Schema(description = "验证表达式")
    String verifyExpression;

    @Schema(description = "匹配的任意角色")
    List<String> anyRoles;

    /**
     * 参考 注解 ResAuthorize
     */
    @Schema(description = "匹配模式")
    protected boolean isAndMode;
    /////////////////////////////////////////////////////

    @Schema(description = "名称", required = true)
    protected String name;

    @Schema(description = "是否启用")
    protected boolean enable = true;

    @Schema(description = "排序码")
    protected Integer orderCode = 100;

    @Schema(description = "备注")
    protected String remark;

    @Override
    public String toString() {
        return id;
    }


    public SimpleResAction setByResAuthorize(ResAuthorize resAuthorize) {

        this.setAndMode(resAuthorize.isAndMode())
                .setAnyRoles(Arrays.asList(resAuthorize.anyRoles()))
                .setVerifyExpression(resAuthorize.verifyExpression())
                .setIgnored(resAuthorize.ignored())
                .setOnlyRequireAuthenticated(resAuthorize.onlyRequireAuthenticated())
                .setName(resAuthorize.action())
                .setId(resAuthorize.action());

        return this;
    }


    public static SimpleResAction newAction(ResAuthorize resAuthorize) {
        return new SimpleResAction().setByResAuthorize(resAuthorize);
    }
}
