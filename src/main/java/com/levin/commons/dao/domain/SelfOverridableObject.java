package com.levin.commons.dao.domain;

import java.util.List;

/**
 * 可自替换的对象
 * <p>
 * 主要用于数据的个性化
 *
 * <p>
 * <p>
 * Override
 */
@FunctionalInterface
public interface SelfOverridableObject {

    /**
     * 方法返回一个字段名列表，字段名对应的值组合成一个标识，然后用标识进行相等比较，用自己直接拥有的数据将替换公共数据。
     *
     * <p>
     *
     * @return 返回字段名列表
     */
    List<String> overrideColumnNames();

}
