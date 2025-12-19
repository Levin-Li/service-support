package com.levin.commons.rbac;

import com.levin.commons.dao.domain.ConfidentialObject;
import com.levin.commons.plugin.Res;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 资源操作
 *
 * @see com.levin.commons.rbac.ResAuthorize
 * @see Res.Action
 */
@Schema(title = "资源操作")
@Data
@Accessors(chain = true)
@FieldNameConstants
@EqualsAndHashCode(of = "id")
public class SimpleResAction
        implements Res.Action {

    @Schema(title = "ID")
    protected String id;

    /// ////////////////////////////////////////////////////////
    @Schema(title = "是否忽略")
    boolean ignored;

    /**
     * 仅要求认证，不做资源授权
     * <p>
     * 默认是要做资源授权
     *
     * @return
     */
    @Schema(title = "仅要求认证")
    boolean onlyRequireAuthenticated = false;

    @Schema(title = "验证表达式")
    String verifyExpression;

    @Schema(title = "数据保密级别", description = "数值越大，级别越高")
    int confidentialLevel = ConfidentialObject.TENANT_SHARED;

    @Schema(title = "匹配的任意角色")
    List<String> anyRoles = Collections.emptyList();

    @Schema(title = "匹配的任意用户类型")
    List<String> anyUserTypes = Collections.emptyList();

    /**
     * 参考 注解 ResAuthorize
     */
    @Schema(title = "匹配模式")
    protected boolean isAndMode;
    /// //////////////////////////////////////////////////

    @Schema(title = "名称")
    protected String name;

    @Schema(title = "是否启用")
    protected boolean enable = true;

    @Schema(title = "排序码")
    protected Integer orderCode = 100;

    @Schema(title = "备注")
    protected String remark;

    @Override
    public String toString() {
        return id;
    }


    public SimpleResAction setByResAuthorize(ResAuthorize resAuthorize) {

        this.setAndMode(resAuthorize.isAndMode())
                .setAnyRoles(Arrays.asList(resAuthorize.anyRoles()))
                .setAnyUserTypes(Arrays.asList(resAuthorize.anyUserTypes()))
                .setVerifyExpression(resAuthorize.verifyExpression())
                .setConfidentialLevel(resAuthorize.confidentialLevel())
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
