package com.levin.commons.utils;

import org.springframework.beans.BeanUtils;


/**
 * @author echo
 */
public abstract class BeanCopyUtils {

    public static <S, T> T copyProperties(S sourceBean, Class<T> targetClass) {

        T targetBean = BeanUtils.instantiateClass(targetClass);

        return copyProperties(sourceBean, targetBean);
    }

    public static <S, T> T copyProperties(S sourceBean, T targetBean) {

        BeanUtils.copyProperties(sourceBean, targetBean);

        return targetBean;
    }

}
