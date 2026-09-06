package com.levin.commons.dao.domain;

/**
 * 领域对象
 *
 * @author llw
 */
@FunctionalInterface
public interface DomainObject {
    /**
     * 获取领域对象标识
     *
     * @return
     */
    String getDomainId();
}
