package com.levin.commons.dao.domain;

import java.io.Serializable;

/**
 * 有归属组织的对象
 *
 * @author llw
 */
public interface OrganizedObject {



    /**
     * 获取归属的组织ID
     * <p>
     * 通常比如归属部门，归属地区
     *
     * @return
     */
    <ORG_ID extends Serializable> ORG_ID getOrgId();

}
