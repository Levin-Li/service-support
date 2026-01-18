package com.levin.commons.rbac;

import cn.hutool.core.util.StrUtil;

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
     * 检查数据访问级别
     *
     * @param targetDataConfidentialLevel 目标数据机密级别  , null 表示非机密数据
     * @return
     */
    public static boolean canAccessConfidentialData(Integer targetDataConfidentialLevel, Supplier<Integer> userConfidentialDataAccessLevelSupplier) {

        if (targetDataConfidentialLevel == null) {
            return true;
        }

        Integer userConfidentialDataAccessLevel = userConfidentialDataAccessLevelSupplier.get();

        return userConfidentialDataAccessLevel != null
                && userConfidentialDataAccessLevel >= targetDataConfidentialLevel;
    }
}
