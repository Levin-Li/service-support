package com.levin.commons.dao.domain;


import java.io.Serializable;

/**
 * 有状态的对象
 *
 * @author llw
 */
public interface StatefulObject {

    /**
     * 获取对象状态
     *
     * @return
     */
    <STATE extends Serializable> STATE getState();
}
