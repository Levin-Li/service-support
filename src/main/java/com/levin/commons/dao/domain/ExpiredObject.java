package com.levin.commons.dao.domain;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * 会过期的对象
 *
 * @author llw
 */
@FunctionalInterface
public interface ExpiredObject {

    /**
     * 过期时间
     *
     * @return date 返回null表示不过期
     */
    LocalDateTime getExpiredTime();

}
