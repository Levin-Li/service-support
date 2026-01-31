package com.levin.commons.utils;


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;


/**
 * @author lilw
 */
public abstract class BeanCopyUtils {

    public static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static <S, T> T copyProperties(S sourceBean, Class<T> targetClass) {

        T targetBean = BeanUtils.instantiateClass(targetClass);

//        return mapperFactory.getMapperFacade().map(sourceBean, targetClass);



        return copyProperties(sourceBean, targetBean );
    }

    public static <S, T> T copyProperties(S sourceBean, T targetBean) {

      //  mapperFactory.getMapperFacade().map(sourceBean, targetBean);

        BeanUtils.copyProperties(sourceBean, targetBean);

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


        //MappingContext context = new MappingContext(new HashMap<>());

        //mapperFactory.getMapperFacade().map(sourceBean, targetBean, TypeFactory.valueOf(sourceType), TypeFactory.valueOf(targetType), context);

        return targetBean;
    }

}
