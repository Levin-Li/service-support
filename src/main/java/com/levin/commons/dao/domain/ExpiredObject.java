package com.levin.commons.dao.domain;


import java.util.Date;

/**
 * 会过期的对象
 *
 * @author llw
 */
public interface ExpiredObject {

    /**
     * 过期时间
     *
     * @return date
     */
    Date getExpiredTime();

}
