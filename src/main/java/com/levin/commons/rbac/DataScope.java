package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;
import java.util.Collections;

/**
 * 数据范围按2个维度进行处理
 * <p>
 * 1.组织范围
 * 2.机密数据访问级别
 *
 * @author lilw
 */
@Schema(title = "数据范围", description = "数据范围按2个维度进行处理, 1.组织范围 2.机密数据访问级别")
public interface DataScope {

    @Schema(title = "组织范围列表")
    default <ORG_SCOPE extends OrgScope> Collection<ORG_SCOPE> getOrgScopeList() {
        return Collections.emptyList();
    }

    /**
     * 获取机密数据的访问级别
     * 数值越大，级别越高
     *
     * @return
     */
    @Schema(title = "机密数据访问级别")
    default Integer getConfidentialDataAccessLevel() {
        return null;
    }

}
