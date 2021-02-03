package com.levin.commons.utils;

import com.levin.commons.service.VariableResolver;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.Locker;
import com.levin.commons.service.support.ValueHolder;
import com.oracle.tools.packager.Log;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ObjectInjetUtils {


    //线程安全的解析器
//    private static final ExpressionParser expressionParser = new SpelExpressionParser();
//    private static final ConversionService conversionService = new DefaultFormattingConversionService();

    private static final Locker LOCKER = Locker.build();


    /**
     * 根据上下文自动注入变量
     *
     * @param bean
     * @param contexts
     * @throws IllegalAccessException
     */
    public static void autoInject(Object bean, Map<String, Object>... contexts) throws IllegalAccessException {

        VariableResolver variableResolver = new VariableResolver() {
            @Override
            public <T> ValueHolder<T> resolve(String name, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException {

                for (Map<String, Object> context : contexts) {
                    if (context.containsKey(name)) {
                        return new ValueHolder<T>().setValue((T) context.get(name)).setHasValue(true);
                    }
                }

                if (throwExWhenNotFound) {
                    throw new RuntimeException("variable " + name + " not found");
                }

                return ValueHolder.NOT_VALUE;
            }
        };

        autoInject(bean, variableResolver);

    }


    /**
     * 根据变量解析器自动注入
     *
     * @param bean
     * @param variableResolvers
     * @throws IllegalAccessException
     */
    public static void autoInject(Object bean, VariableResolver... variableResolvers) throws IllegalAccessException {

        //ResolvableType resolvableType = ResolvableType.forClass(bean.getClass());

        for (Field field : getFields(bean.getClass(), InjectVar.class)) {

            InjectVar injectVar = field.getAnnotation(InjectVar.class);

            ValueHolder<Object> valueHolder = ValueHolder.NOT_VALUE;

            for (VariableResolver variableResolver : variableResolvers) {
                try {
                    valueHolder = variableResolver.resolve(injectVar.value(), false);

                    if (valueHolder.isHasValue()) {
                        break;
                    }

                } catch (Exception e) {
                    if (Log.isDebug()) {
                        Log.debug(e);
                    }
                }
            }

            //注入变量
            if (valueHolder.isHasValue()) {
                field.setAccessible(true);
                field.set(bean, valueHolder.getValue());
            }

            if (injectVar.isRequired()
                    && !valueHolder.isHasValue()) {

                throw new IllegalAccessException(field.getDeclaringClass().getName()
                        + "." + field.getName() + " inject var " + injectVar.value() + " can't resolve," + injectVar.remark());
            }

        }

    }

    /**
     * 根据上下文自动注入变量
     *
     * @param variableResolver
     * @param beans
     * @throws IllegalAccessException
     */
    public static void autoInject(VariableResolver variableResolver, Object... beans) throws IllegalAccessException {

        for (Object bean : beans) {
            autoInject(bean, variableResolver);
        }

    }

    private static final Map<String, List<Field>> fieldMap = new ConcurrentReferenceHashMap<>();

    private static List<Field> getFields(Class clazz, Class<? extends Annotation> type) {

        final String className = clazz.getName();

        synchronized (LOCKER.getLock(className)) {

            List<Field> fields = fieldMap.get(className);

            if (fields == null) {

                final List<Field> tempList = new ArrayList<>(16);

                ReflectionUtils.doWithFields(clazz, field -> tempList.add(field), field -> field.isAnnotationPresent(type));

                fields = tempList;

                fieldMap.put(className, fields);
            }

            return fields;
        }

    }

}
