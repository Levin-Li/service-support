package com.levin.commons.dao.domain;


/**
 * 是否是可用的对象
 * <p/>
 * 1 表示启用
 * 0 表示禁用
 *
 * @author llw
 */
public interface ProxyWrapperObject {

    /**
     * 获取代理对象类型
     *
     * @return
     */
    <T> Class<T> proxyTargetClass();

    /**
     * 获取原代理被对象
     *
     * @return
     */
    <T> T getOriginalObject();

}
