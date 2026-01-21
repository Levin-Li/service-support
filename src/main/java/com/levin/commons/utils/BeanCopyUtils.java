package com.levin.commons.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lilw
 */
public abstract class BeanCopyUtils {

    // 缓存 BeanCopier 实例（避免重复生成字节码，核心性能优化）
    private static final Map<String, BeanCopier> copierCache = new ConcurrentHashMap<>();

    public static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass, boolean useConverter) {

        final String cacheKey = sourceClass.getName() + "_" + targetClass.getName() + "_" + useConverter;

        BeanCopier beanCopier = copierCache.get(cacheKey);

        // 双重检查锁，避免并发创建
        if (beanCopier == null) {
            synchronized (copierCache) {

                beanCopier = copierCache.get(cacheKey);

                //如果还是为空，则创建
                if (beanCopier == null) {
                    beanCopier = BeanCopier.create(sourceClass, targetClass, useConverter);
                    copierCache.put(cacheKey, beanCopier);
                }
            }
        }

        return beanCopier;
    }

    /**
     * 获取/缓存 BeanCopier 实例（核心：按 源类+目标类+是否带转换器 缓存）
     */
    public static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        return getBeanCopier(sourceClass, targetClass, false);
    }


    public static <T> T copyProperties(Object sourceBean, Class<T> targetClass) {

        BeanCopier beanCopier = getBeanCopier(sourceBean.getClass(), targetClass);

        T targetBean = BeanUtils.instantiateClass(targetClass);

        beanCopier.copy(sourceBean, targetBean, null);

        return targetBean;
    }

    public static <T> T copyProperties(Object sourceBean, T targetBean) {
        return copyProperties(sourceBean, targetBean, null, null);
    }

    public static <T> T copyProperties(Object sourceBean, Class<T> targetClass, Converter converter) {
        return copyProperties(sourceBean, BeanUtils.instantiateClass(targetClass), targetClass, converter);
    }

    /**
     * 拷贝属性
     *
     * @param sourceBean
     * @param targetBean
     * @param targetClass
     * @param converter   转换器, 比如 时间转换器, String ->Integer 等
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object sourceBean, T targetBean, Class<T> targetClass, Converter converter) {

        if (targetBean == null || sourceBean == null) {
            return targetBean;
        }

        if (targetClass == null) {
            targetClass = (Class<T>) targetBean.getClass();
        }


        BeanCopier beanCopier = getBeanCopier(sourceBean.getClass(), targetClass, converter != null);

        beanCopier.copy(sourceBean, targetBean, converter);

        return targetBean;
    }

    public static final Converter DEFAULT_SPRING_CONVERTER = new Converter() {

        @Override
        public Object convert(Object sourceValue, Class targetFieldType, Object context) {

            // context 是当前拷贝的字段名（如 "ageStr" "createTime"）
            //String fieldName = (String) context;

            // 空值直接返回 null
            if (sourceValue == null) {
                return null;
            }

            return DefaultConversionService.getSharedInstance().convert(sourceValue, targetFieldType);
        }
    };

}
