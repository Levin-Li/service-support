package com.levin.commons.dao.domain;


/**
 * 可转换对象
 * @author llw
 */
public interface CastableObject<T extends CastableObject<T>> {
    default T cast() {
        return (T) this;
    }

}
