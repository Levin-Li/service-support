package com.levin.commons.rbac;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

public class RbacMiscUtils {

    /**
     * 判断集合是否为空或者全部为空
     *
     * @param data
     * @return
     */
    public static boolean isEmptyOrAllBlank(Collection<? extends CharSequence> data) {
        return data == null || data.isEmpty() || !data.stream().anyMatch(StrUtil::isNotBlank);
    }

    /**
     * 判断集合是否为空或者全部为空
     *
     * @param data
     * @return
     */
    public static boolean isEmptyOrAllNull(Collection<?> data) {
        return data == null || data.isEmpty() || !data.stream().anyMatch(Objects::nonNull);
    }

    /**
     * 判断对象是否为空或者为空字符串
     *
     * @param data
     * @return
     */
    public static boolean isNullOrBlank(Serializable data) {
        return data == null || (data instanceof CharSequence) ? StrUtil.isBlank((CharSequence) data) : false;
    }
}
