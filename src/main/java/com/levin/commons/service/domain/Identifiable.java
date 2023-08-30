package com.levin.commons.service.domain;


import java.io.Serializable;

/**
 * 可标识的对象
 *
 * @author llw
 */
public interface Identifiable {

    /**
     * ID 常量，方便 Lombok 用于 @EqualsAndHashCode 注解
     */
    String ID = "id";

    /**
     * 获取对象标识
     *
     * @return id
     */
    <ID extends Serializable> ID getId();

}
