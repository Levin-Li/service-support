package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collections;
import java.util.List;

/**
 * 数据范围按2个维度进行处理
 * <p>
 * 1.组织范围
 * 2.数据访问级别
 *
 * @author lilw
 */
public interface DataScope {

    @Schema(title = "组织范围列表")
    default <ORG_SCOPE extends OrgScope> List<ORG_SCOPE> getOrgScopeList() {
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
