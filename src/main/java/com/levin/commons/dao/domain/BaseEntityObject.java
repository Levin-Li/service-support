package com.levin.commons.dao.domain;

import java.util.Date;

/**
 * @author llw
 */
public interface BaseEntityObject
        extends
        EntityObject ,
        BaseObject {

    /**
     * 对象创建时间
     *
     * @return createTime
     */
    Date getCreateTime();


    /**
     * 最后更新时间
     *
     * @return
     */

    Date getLastUpdateTime();

    /**
     * 获取对象描述
     *
     * @return desc
     */
    String getRemark();

}
