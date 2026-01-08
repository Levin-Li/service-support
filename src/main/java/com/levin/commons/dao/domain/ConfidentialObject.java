package com.levin.commons.dao.domain;

/**
 * 保密数据对象
 *
 * @author llw
 */
public interface ConfidentialObject {

    /**
     * 获取保密级别
     * 数值越大，级别越高
     *
     * @return
     */
    Integer getConfidentialLevel();

}
