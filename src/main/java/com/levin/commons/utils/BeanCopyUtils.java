package com.levin.commons.utils;


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;


/**
 * @author lilw
 */
public abstract class BeanCopyUtils {

    public static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static <S, T> T copyProperties(S sourceBean, Class<T> targetClass) {
        return mapperFactory.getMapperFacade().map(sourceBean, targetClass);
    }

    public static <S, T> T copyProperties(S sourceBean, T targetBean) {
        mapperFactory.getMapperFacade().map(sourceBean, targetBean);

        return targetBean;
    }

    /**
     * 拷贝属性
     *
     * @param sourceBean
     * @param targetBean
     * @param sourceType
     * @param targetType
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T copyProperties(S sourceBean, T targetBean, Class<S> sourceType, Class<T> targetType) {

        if (targetBean == null || sourceBean == null) {
            return targetBean;
        }

        if (sourceType == null) {
            sourceType = (Class<S>) sourceBean.getClass();
        }

        if (targetType == null) {
            targetType = (Class<T>) targetBean.getClass();
        }

        mapperFactory.getMapperFacade().map(sourceBean, targetBean, TypeFactory.valueOf(sourceType), TypeFactory.valueOf(targetType));

        return targetBean;
    }

}
