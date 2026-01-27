package com.levin.commons.rbac;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class RbacMiscUtils {

    public static boolean hasAnyNotBlank(Collection<? extends CharSequence> data) {
        return !isAllBlank(data);
    }

    /**
     * 判断集合是否为空或者全部为空
     *
     * @param data
     * @return
     */
    public static boolean isAllBlank(Collection<? extends CharSequence> data) {
        return data == null || data.isEmpty() || data.stream().noneMatch(StrUtil::isNotBlank);
    }

    public static boolean hasAnyNotNull(Collection<?> data) {
        return !isAllNull(data);
    }

    /**
     * 判断集合是否为空或者全部为空
     *
     * @param data
     * @return
     */
    public static boolean isAllNull(Collection<?> data) {
        return data == null || data.isEmpty() || data.stream().noneMatch(Objects::nonNull);
    }

    public static boolean isNotBlank(Serializable data) {
        return !isBlank(data);
    }

    /**
     * 判断对象是否为空或者为空字符串
     *
     * @param data
     * @return
     */
    public static boolean isBlank(Serializable data) {
        return  data == null || (data instanceof CharSequence && StrUtil.isBlank((CharSequence) data));
    }

    /**
     * 判断集合是否相等
     *
     * @param data1
     * @param data2
     * @return
     */
    public static boolean isStrSetEquals(Collection<? extends CharSequence> data1, Collection<? extends CharSequence> data2) {

        // 判断集合是否相等
        if (data1 == null || data2 == null) {
            return data1 == data2;
        }

        data1 = data1.stream().filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        data2 = data2.stream().filter(StrUtil::isNotBlank).collect(Collectors.toSet());

        return data1.equals(data2);
    }
}
