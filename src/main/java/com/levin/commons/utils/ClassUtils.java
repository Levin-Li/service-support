package com.levin.commons.utils;


import com.levin.commons.service.support.Locker;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 类工具
 */

public final class ClassUtils {


    private static Logger logger = Logger.getLogger(ClassUtils.class.getName());

    private static final Map<String, Map<String, Method>> cacheSetMethods = new ConcurrentReferenceHashMap<>();

    private static final Map<String, Map<String, Method>> cacheGetMethods = new ConcurrentReferenceHashMap<>();

    private static final Map<String, Map<String, Field>> cacheFields = new ConcurrentReferenceHashMap<>();

    private static final Locker LOCKER = Locker.build();

    private static final Map<String, List<Field>> fieldMap = new ConcurrentReferenceHashMap<>();

    private static final Map<String, Method> postConstructMethodCache = new ConcurrentReferenceHashMap<>();


    /**
     * 执行 postConstruct 方法
     *
     * @param bean
     * @return
     */
    public static void invokeFirstPostConstructMethod(Object bean) {

        if (bean != null) {

            String name = bean.getClass().getName();

            Method method = postConstructMethodCache.get(name);

            if (method == null
                    && !postConstructMethodCache.containsKey(name)) {

                //找出第一个
                method = Arrays.stream(ReflectionUtils.getAllDeclaredMethods(bean.getClass()))
                        .filter(m -> m.isAnnotationPresent(PostConstruct.class))
                        .findFirst()
                        .orElse(null);

                //空值也存入，避免下次还去查找方法
                postConstructMethodCache.put(name, method);
            }

            if (method != null) {
                method.setAccessible(true);
                ReflectionUtils.invokeMethod(method, bean);
            }
        }
    }


    /**
     * 获取拥有指定注解的字段清单
     *
     * @param clazz
     * @param type
     * @return
     */
    public static List<Field> getFields(Class clazz, Class<? extends Annotation> type) {

        final String key = clazz.getName() + "@" + type.getName();

        List<Field> fields = fieldMap.get(key);

        synchronized (LOCKER.getLock(key)) {

             fields = fieldMap.get(key);

            if (fields == null) {

                final List<Field> tempList = new ArrayList<>(5);

                ReflectionUtils.doWithFields(clazz, field -> tempList.add(field), field -> field.isAnnotationPresent(type));

                fields = Collections.unmodifiableList(tempList);

                fieldMap.put(key, fields);
            }

        }

        return fields;
    }

    /**
     * 格式化包名
     * <p>
     * 主要用于路径扫描
     *
     * @param packages
     * @return
     */
    public static List<String> formatPackages(String... packages) {
        return formatPackages(Arrays.asList(packages));
    }

    /**
     * 格式化包名
     * <p>
     * 去除空格，去除多余的.号
     * 压缩包名
     *
     * <p>
     * 主要用于路径扫描
     *
     * @param packages
     * @return
     */
    public static List<String> formatPackages(Collection<String> packages) {

        LinkedList<String> minList = new LinkedList<>();

        packages.stream()
                .filter(StringUtils::hasText)
                .map(p -> p.replace(" ", ""))
                .map(p -> {
                    //去替换2个点
                    while (p.contains("..")) {
                        p = p.replace("..", ".");
                    }
                    return p;
                })
                .map(p -> {
                    //去除最后一个点
                    while (p.length() > 0 && p.endsWith(".")) {
                        p = p.substring(0, p.length() - 1);
                    }
                    return p;
                })
                .filter(StringUtils::hasText)
                .sorted()
                .forEach(p -> {
                    if (minList.isEmpty()) {
                        minList.add(p);
                    } else {
                        String last = minList.getLast();

                        int indexOf = p.indexOf(last);

                        //last = com.levin
                        // p = com.levina.a
                        //如果不等于0，表示不同包
                        if (indexOf != 0
                                || p.length() == last.length()
                                || p.charAt(last.length()) != '.') {
                            minList.add(p);
                        }
                    }

                });

        return minList;
    }


    /**
     * 获取第一个符号条件的属性值
     * <p>
     * 如果条件没设置默认返回非空的值
     *
     * @param annotation
     * @param filter     如果条件没设置默认返回非空的值
     * @param attrNames
     * @param <T>
     * @return
     */
    public static <T> T getFirstValue(Annotation annotation, Predicate<T> filter, String... attrNames) {

        for (String attrName : attrNames) {

            T value = getValue(annotation, attrName, false);

            if (filter != null && filter.test(value)) {
                return value;
            } else if (value != null) {
                return value;
            }
        }

        return null;
    }

    /**
     * 获取注解的属性值
     *
     * @param annotation
     * @param attrName
     * @param allowThrowEx
     * @param <T>
     * @return
     */
    public static <T> T getValue(Annotation annotation, String attrName, boolean allowThrowEx) {

        Method method = ReflectionUtils.findMethod(annotation.annotationType(), attrName);

        if (method == null) {
            if (allowThrowEx) {
                throw new IllegalArgumentException(annotation.annotationType() + "." + attrName + " not found");
            } else {
                return null;
            }
        }

        try {
            return (T) ReflectionUtils.invokeMethod(method, annotation);
        } catch (Exception ex) {
            if (allowThrowEx) {
                ReflectionUtils.rethrowRuntimeException(ex);
            }
        }

        return null;
    }

    /**
     * 获取注解的属性值
     *
     * @param annotation
     * @param properties
     * @param <T>
     * @return 返回和属性一样的数量的列表
     */
    public static <T> List<T> getValues(Annotation annotation, String... properties) {

        Class<? extends Annotation> annotationType = annotation.annotationType();

        return (List<T>) Arrays.stream(properties)
                .map(p -> Optional.ofNullable(p)
                        .map(p2 -> ReflectionUtils.findMethod(annotationType, p2))
                        .orElse(null))
                .map(m -> Optional.ofNullable(m)
                        .map(m2 -> ReflectionUtils.invokeMethod(m2, annotation))
                        .orElse(null))
                .collect(Collectors.toList());

    }

    /**
     * 创建注解实例
     *
     * @param annotationClass
     * @param copyProps
     * @return
     */
    public static Annotation newAnnotation(Class<? extends Annotation> annotationClass, Map<String, Object> copyProps) {
        return newAnnotation(annotationClass, null, copyProps, false);
    }

    /**
     * New 注解实例
     *
     * @param annotationClass             可以为 Null，为空是从copySource获取注解类型
     * @param copySource                  如果覆盖的属性值不存在，则从源注解中获取
     * @param overrideProps
     * @param isDirectRefOverridePropsMap 是否自己引用覆盖的Map，是则可以实现动态的注解属性值
     * @return
     */
    public static Annotation newAnnotation(Class<? extends Annotation> annotationClass, final Annotation copySource, Map<String, Object> overrideProps, boolean isDirectRefOverridePropsMap) {


        if (annotationClass == null && copySource != null) {
            annotationClass = copySource.annotationType();
        }

        if (annotationClass == null) {
            throw new IllegalArgumentException("annotationClass must be specific");
        }

        if (overrideProps == null) {
            throw new IllegalArgumentException("overrideProps must be specific");
        }


        final Class<? extends Annotation> type = annotationClass;

        final Map<String, Object> props = isDirectRefOverridePropsMap ? overrideProps : new HashMap<>(overrideProps);

        Annotation annotation = (Annotation) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{annotationClass}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object v = null;

                if (ReflectionUtils.isToStringMethod(method)) {
                    return "Annotation JDK proxy (" + type.getName() + ")";
                } else if (ReflectionUtils.isObjectMethod(method)) {
                    return method.invoke(proxy, args);
                }

                String name = method.getName();

                if (props.containsKey(name)) {
                    v = props.get(name);
                } else if (copySource != null) {
                    v = Optional.ofNullable(ReflectionUtils.findMethod(copySource.annotationType(), name))
                            .map(m1 -> ReflectionUtils.invokeMethod(m1, copySource))
                            .orElse(null);
                }

                //注解属性值不允许为null
                if (v == null) {
                    throw new RuntimeException("注解[" + type.getName() + "]属性[" + name + "]值为 null");
                }

                return v;
            }
        });


        return annotation;

    }


    /**
     * @param copySource    源注解示例
     * @param overrideProps 覆盖的属性
     * @return
     */
    public static Annotation newAnnotation(Annotation copySource, Map<String, Object> overrideProps) {
        return newAnnotation(null, copySource, overrideProps, false);
    }

    /**
     * 获取所有属性和方法的映射
     *
     * @param clazz
     * @param isGet 是否是获取get方法
     * @return
     */
    public static Map<String, Method> findMethod(Class clazz, boolean isGet) {

        Map<String, Method> methodMap = new LinkedHashMap<>();


        Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);

        for (int i = 0; i < methods.length; i++) {

            Method method = methods[i];

            String name = method.getName();

            //如果是get要求是没有参数的方法
            if (isGet && method.getParameterTypes().length > 0)
                continue;

            if (name.startsWith(isGet ? "get" : "set") && name.length() > 3) {
                name = Character.toUpperCase(name.charAt(3)) + name.substring(4);
                methodMap.put(name, method);
            }
        }


        return methodMap;
    }


    /**
     * 获取类所有的字段，包括父类的字段
     *
     * @param type
     * @return
     */
    public static Map<String, Field> findFields(Class type) {

        Map<String, Field> fieldMap = new LinkedHashMap<>();

        int excludeModifiers = Modifier.FINAL | Modifier.TRANSIENT;

        ReflectionUtils.doWithFields(type, field -> {

            field.setAccessible(true);
            fieldMap.put(field.getName(), field);

        }, field -> !fieldMap.containsKey(field.getName())
                && (field.getModifiers() & excludeModifiers) == 0);

        return fieldMap;
    }

    private static Map<String, Method> getCachedSetMethodMap(Class clazz) {

        Map<String, Method> methodMap = cacheSetMethods.get(clazz.getName());

        if (methodMap == null) {
            methodMap = findMethod(clazz, false);
            cacheSetMethods.put(clazz.getName(), methodMap);
        }

        return methodMap;
    }

    private static Map<String, Method> getCachedGetMethodMap(Class clazz) {

        Map<String, Method> methodMap = cacheGetMethods.get(clazz.getName());

        if (methodMap == null) {
            methodMap = findMethod(clazz, true);
            cacheGetMethods.put(clazz.getName(), methodMap);
        }

        return methodMap;
    }


    private static Map<String, Field> getCachedFieldMap(Class clazz) {

        Map<String, Field> methodMap = cacheFields.get(clazz.getName());

        if (methodMap == null) {
            methodMap = findFields(clazz);
            cacheFields.put(clazz.getName(), methodMap);
        }

        return methodMap;
    }


    /**
     * 复制对象的所有属性到 map
     *
     * @param source
     * @param dest
     * @return
     */
    public static Map<String, ? extends Object> copyFields2Map(Object source, Map<String, Object> dest) {

        if (dest == null)
            dest = new LinkedHashMap<>();

        if (source == null)
            return dest;

        Map<String, Field> fieldMap = getCachedFieldMap(source.getClass());

        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            try {
                dest.put(entry.getKey(), entry.getValue().get(source));
            } catch (Exception e) {
                logger.warning("copyFields2Map, " + e);
            }
        }

        return dest;
    }


    public static <T> T getStaticFieldValue(String className, String fieldName) throws NoSuchFieldError, ClassNotFoundException, IllegalAccessException {

        Field field = getCachedFieldMap(Class.forName(className)).get(fieldName);

        if (field == null)
            throw new NoSuchFieldError(className + "." + fieldName);

        return (T) field.get(null);
    }


    /**
     * 复制 map 到目标对象中
     *
     * @param source
     * @param target
     * @return
     */
    public static Map<String, ? extends Object> copy(Map<String, ? extends Object> source, Object target) {
        return copy(source, null, target);
    }

    /**
     * 复制 map 值到 对象或是静态类方法中
     *
     * @param source
     * @param type
     * @param target
     * @return noSetValues
     */
    public static Map<String, ? extends Object> copy(Map<String, ? extends Object> source, Class type, final Object target) {

        if (source == null || (type == null && target == null))
            return source;

        Map<String, Object> noSetValues = new LinkedHashMap();

        if (target instanceof Map) {
            ((Map) target).putAll(source);
        } else {

            Map<String, Method> methodMap = getCachedSetMethodMap(type != null ? type : target.getClass());
            Map<String, Field> fieldMap = getCachedFieldMap(type != null ? type : target.getClass());

            for (Map.Entry<String, ? extends Object> entry : source.entrySet()) {

                String name = entry.getKey();
                Object value = entry.getValue();

                Method method = methodMap.get(name);
                Field field = null;

                if (method != null) {
                    try {
                        //如果参数数量大于一，且值是数组，则以多参数的方式执行
                        if (value != null && value.getClass().isArray() && method.getParameterTypes().length > 1)
                            method.invoke(target, (Object[]) value);
                        else
                            method.invoke(target, value);
                    } catch (Exception e) {
                        noSetValues.put(name, value);
                        logger.warning("copyValue [" + name + "] error, " + e);
                    }
                } else if ((field = fieldMap.get(name)) != null) {
                    try {
                        field.setAccessible(true);
                        field.set(target, value);
                    } catch (Exception e) {
                        noSetValues.put(name, value);
                        logger.warning("copyValue [" + name + "] error, " + e);
                    }
                } else {
                    noSetValues.put(name, value);
                }
            }
        }

        return noSetValues;
    }


    /**
     * 获取属性值
     *
     * @param target
     * @param name
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getValue(Object target, String name) throws InvocationTargetException, IllegalAccessException {

        if (target instanceof Map) {
            if (((Map) target).containsKey(name))
                return ((Map) target).get(name);
            else
                throw new IllegalArgumentException("Map key[" + name + "] not found");
        }

        Method method = getCachedGetMethodMap(target.getClass()).get(name);

        //方法优先
        if (method != null) {
            return method.invoke(target);
        }

        Field field = getCachedFieldMap(target.getClass()).get(name);

        if (field != null) {
            field.setAccessible(true);
            return field.get(target);
        }

        throw new IllegalArgumentException(target.getClass() + "." + name + " not found");

    }

    /**
     * 设置属性值
     *
     * @param target
     * @param name
     * @param value
     * @return
     */
    public static boolean setValue(Object target, String name, Object value) {

        if (target instanceof Map) {
            ((Map) target).put(name, value);
            return true;
        }

        boolean success = false;

        Method method = getCachedSetMethodMap(target.getClass()).get(name);

        //方法优先
        if (method != null) {
            try {
                if (value != null && value.getClass().isArray() && method.getParameterTypes().length > 1)
                    method.invoke(target, (Object[]) value);
                else
                    method.invoke(target, value);

                success = true;
            } catch (Exception e) {
                logger.warning("copyValue [" + name + "] error, " + e);
            }
            return success;
        }

        Field field = getCachedFieldMap(target.getClass()).get(name);

        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(target, value);
                success = true;
            } catch (Exception e) {
                logger.warning("copyValue [" + name + "] error, " + e);
            }
        }

        return success;
    }

}
