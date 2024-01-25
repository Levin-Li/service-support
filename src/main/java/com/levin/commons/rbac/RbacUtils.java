package com.levin.commons.rbac;

import com.levin.commons.plugin.Res;
import com.levin.commons.service.domain.SimpleIdentifiable;
import com.levin.commons.service.support.SpringContextHolder;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.commons.utils.MapUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public abstract class RbacUtils {

    private static final LinkedMultiValueMap<String, Res> beanResCache = new LinkedMultiValueMap<>();

    private static final Map<String, List<MenuItem>> menuCache = new ConcurrentHashMap<>();

    private static final Map<Class<?>, Map<Method, ResAuthorize>> resAuthorizeCache = new ConcurrentHashMap<>();

    /**
     * 获取资源类型
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static Set<SimpleIdentifiable> loadResTypeFromSpringCtx(@NotNull ApplicationContext context, @NotNull String pkgName, Function<String, String> nameMapper) {
//        return
//                //获取 bean 清单
//                context.getBeansWithAnnotation(ResAuthorize.class)
//                        .entrySet().parallelStream()
//                        //获取 bean 类型
//                        .map(it -> context.getType(it.getKey()))
//                        //过滤包名
//                        .filter(cls -> cls.getPackage().getName().equals(pkgName))
//                        //获取注解
//                        .map(c -> c.getAnnotation(ResAuthorize.class))
//                        //获取类型
//                        .map(resAuthorize -> resAuthorize.type())
//                        .map(type -> new IdentifiableObject()
//                                .setId(type)
//                                //设置名称，试图映射名称
//                                .setName(nameMapper != null ? nameMapper.apply(type) : type))
//                        .collect(Collectors.toList());

        synchronized (beanResCache) {
            initBeanResCache(context);
        }

        return beanResCache.getOrDefault(pkgName, Collections.emptyList())
                .stream()
                .map(res -> new DefaultSimpleIdentifiableObject()
                        .setId(res.getType())
                        //设置名称，试图映射名称
                        .setName(nameMapper != null ? nameMapper.apply(res.getType()) : res.getType()))
                .collect(Collectors.toSet());

    }

    /**
     * 获取所有的资源
     *
     * @param context
     * @return
     */
    public static MultiValueMap<String, Res> loadAllResFromSpringCtx(@NotNull ApplicationContext context) {

        synchronized (beanResCache) {
            initBeanResCache(context);
        }

        return beanResCache.clone();
    }


    /**
     * 获取 Spring 资源
     *
     * @param context
     * @param pkgName
     * @param type
     * @return
     */
    public static List<Res> loadResFromSpringCtx(@NotNull ApplicationContext context, @NotNull String pkgName, String type) {

        synchronized (beanResCache) {
            initBeanResCache(context);
        }

        return beanResCache.getOrDefault(pkgName, Collections.emptyList())
                .stream()
                .filter(res -> !StringUtils.hasText(type) || type.equals(res.getType()))
                .collect(Collectors.toList());
    }

    /**
     * 获取方法上的权限注解
     *
     * @param bean
     * @param targetMethod
     * @return
     */
    public static ResAuthorize getMethodResAuthorize(Object bean, Method targetMethod) {

        Assert.notNull(targetMethod, "方法不能为空");

        Class<?> beanType = (bean instanceof Class) ? (Class<?>) bean : (bean != null ? AopProxyUtils.ultimateTargetClass(bean) : targetMethod.getDeclaringClass());

        Assert.isTrue(targetMethod.getDeclaringClass().isAssignableFrom(beanType), "方法的声明类" + beanType);

        return getClassResAuthorizeFormCache(beanType).get(targetMethod);
    }

    public static Map<Method, ResAuthorize> getClassResAuthorizeFormCache(Object beanOrType) {
        return getClassResAuthorizeFormCache(beanOrType, false);
    }

    public static Map<Method, ResAuthorize> getClassResAuthorizeFormCache(Object beanOrType, boolean loadAllMethod) {

        Assert.notNull(beanOrType, "beanOrType 参数不能为空");

        Class<?> beanType = (beanOrType instanceof Class) ? (Class<?>) beanOrType : AopProxyUtils.ultimateTargetClass(beanOrType);

        return resAuthorizeCache.computeIfAbsent(beanType, (type) -> loadClassResAuthorize(type, loadAllMethod));

    }

    /**
     * @param beanOrType
     * @return
     */
    public static Map<Method, ResAuthorize> loadClassResAuthorize(Object beanOrType) {
        return loadClassResAuthorize(beanOrType, false);
    }

    /**
     * 获取类上的权限注解
     *
     * @param beanOrType
     * @return
     */
    public static Map<Method, ResAuthorize> loadClassResAuthorize(Object beanOrType, boolean loadAllMethod) {

        Assert.notNull(beanOrType, "beanOrType 参数不能为空");

        final Class<?> beanType = (beanOrType instanceof Class) ? (Class<?>) beanOrType : AopProxyUtils.ultimateTargetClass(beanOrType);

        Map<Method, ResAuthorize> methodResAuthorizeMap = new ConcurrentHashMap<>();

        Tag clsTag = AnnotatedElementUtils.findMergedAnnotation(beanType, Tag.class);

        //获取类注解
        final ResAuthorize classResAuthorize = AnnotatedElementUtils.findMergedAnnotation(beanType, ResAuthorize.class);

        final Map<String, Object> classResAuthorizeAttrs = classResAuthorize != null ? AnnotationUtils.getAnnotationAttributes(classResAuthorize) : Collections.emptyMap();

        final String tagName = clsTag != null ? clsTag.name() : beanType.getSimpleName();

        //获取方法上的注解描述，自动去除重复的方法
        for (Method method : ReflectionUtils.getUniqueDeclaredMethods(beanType,
                m -> Modifier.isPublic(m.getModifiers())
                        && !ReflectionUtils.isObjectMethod(m)
                        && !Modifier.isStatic(m.getModifiers())
                        && AnnotatedElementUtils.hasAnnotation(m, RequestMapping.class)
        )) {

            //如果没有请求注解，将忽略
//            if (!Modifier.isPublic(method.getModifiers())
//                    //       || Modifier.isStatic(method.getModifiers())
//                    || ReflectionUtils.isObjectMethod(method)
//                    || !AnnotatedElementUtils.hasAnnotation(method, RequestMapping.class)) {
//                continue;
//            }

            //获取方法注解
            ResAuthorize fieldResAuthorize = AnnotatedElementUtils.findMergedAnnotation(method, ResAuthorize.class);

            if (classResAuthorize == null && fieldResAuthorize == null) {

                log.warn("控制器方法 {} 没有可用的[ResAuthorize]注解，将不进行鉴权", method);

                if (loadAllMethod) {
                    methodResAuthorizeMap.put(method, null);
                }

                continue;
            }

            Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);

            Assert.notNull(operation, "需要鉴权的控制器方法必须定义[Operation]注解，控制器方法：" + method);
            Assert.isTrue(StringUtils.hasText(operation.summary()), "需要鉴权的控制器方法[Operation]注解的summary属性需要指定，控制器方法：" + method);

            String[] tags = operation.tags();

            if (tags == null || tags.length == 0) {
                if (clsTag != null && StringUtils.hasText(clsTag.name())) {
                    tags = new String[]{clsTag.name()};
                }
            }

            Assert.isTrue(Arrays.stream(tags).anyMatch(StringUtils::hasText), "需要鉴权的控制器方法[Operation]注解的tags属性需要指定，控制器方法：" + method);

            //资源标识, 2023 优化表达式结构
            //final String resId = (fieldResAuthorize != null && StringUtils.hasText(fieldResAuthorize.res())) ? fieldResAuthorize.res() : "";// Arrays.stream(tags).filter(StringUtils::hasText).findFirst().orElse(beanType.getSimpleName());

            String actionName = StringUtils.hasText(operation.summary()) ? operation.summary() : null;

            if (!StringUtils.hasText(actionName)) {
                log.warn("控制器方法 {} 没有 Operation注解或是Operation注解的summary属性没有定义.", method);
                actionName = method.getName();
            }

            //复制父类
            final Map<String, Object> fieldResAuthorizeAttrs = new LinkedHashMap<>(classResAuthorizeAttrs);

            //设置操作名称
            fieldResAuthorizeAttrs.put(ResPermission.Fields.action, actionName);

            if (fieldResAuthorize != null) {
                //获取类注解
                Map<String, Object> tempAttrs = AnnotationUtils.getAnnotationAttributes(fieldResAuthorize);
                tempAttrs.forEach((k, v) -> {
                    //只有 domain  type   res  action
                    if (v == null
                            //空数组不覆盖
                            || (v.getClass().isArray() && Array.getLength(v) == 0)
                            //空字符串不覆盖，注意，字符串里面有空格不算
                            || ((v instanceof CharSequence) && ((CharSequence) v).length() == 0)) {
                        //nothing to do
                    } else {
                        //其它情况都覆盖
                        fieldResAuthorizeAttrs.put(k, v);
                    }
                });
            }

            String resType = (String) fieldResAuthorizeAttrs.get(ResPermission.Fields.type);

            //如果以-结尾，则加上tagName名称
            if (StringUtils.hasText(resType)
                    && resType.endsWith("-")) {
                resType += tagName;
            } else if (!StringUtils.hasText(resType)) {
                resType = tagName;
            }

            if (!fieldResAuthorizeAttrs.containsKey(ResPermission.Fields.remark)) {
                fieldResAuthorizeAttrs.put(ResPermission.Fields.remark, operation.description());
            }

            //
            fieldResAuthorizeAttrs.put(ResPermission.Fields.type, resType);

            //去除空格

            //重新定义
            fieldResAuthorize = AnnotationUtils.synthesizeAnnotation(fieldResAuthorizeAttrs, ResAuthorize.class, null);

            if (fieldResAuthorize.ignored()
                    && !loadAllMethod) {
                continue;
            }

            Assert.hasText(fieldResAuthorize.domain(), "需要鉴权的控制器方法[ResAuthorize]注解" + ResPermission.Fields.domain + "属性未设置，方法：" + method);

            Assert.hasText(fieldResAuthorize.type(), "需要鉴权的控制器方法[ResAuthorize]注解" + ResPermission.Fields.type + "属性未设置，方法：" + method);

            Assert.hasText(fieldResAuthorize.action(), "需要鉴权的控制器方法[ResAuthorize]注解" + ResPermission.Fields.action + "属性未设置，方法：" + method);

            //Assert.notNull(fieldResAuthorize.res(), "需要鉴权的控制器方法[ResAuthorize]注解res属性未设置，方法：" + method);

            methodResAuthorizeMap.put(method, fieldResAuthorize);
        }

        return methodResAuthorizeMap;

    }

    private static void initBeanResCache(ApplicationContext context) {

        if (!beanResCache.isEmpty()) {
            return;
        }

        final Map<String, SimpleRes> cacheMap = new LinkedHashMap<>();

        context.getBeansWithAnnotation(Controller.class).forEach((name, bean) -> {

            Map<Method, ResAuthorize> classResAuthorize = getClassResAuthorizeFormCache(bean);

            classResAuthorize.forEach((k, fieldResAuthorize) -> {

                final String key = fieldResAuthorize.domain() + fieldResAuthorize.type() + fieldResAuthorize.res();

                final SimpleRes res = MapUtils.getAndAutoPut(cacheMap, key, null, () -> new SimpleRes()
                        .setDomain(fieldResAuthorize.domain())
                        .setType(fieldResAuthorize.type())
                        .setId(fieldResAuthorize.res())
                        .setActionList(new ArrayList<>(10)));

                //加入操作列表
                res.getActionList().add(SimpleResAction.newAction(fieldResAuthorize).setRemark(fieldResAuthorize.remark()));

            });

        });

        //加入缓存
        cacheMap.forEach((k, v) -> {
            if (StringUtils.hasText(v.getDomain())
                    && v.getActionList().size() > 0) {
                beanResCache.add(v.getDomain(), v);
            }
        });

    }


    /**
     * 扫描控制器并构建菜单项
     * <p>
     * 构建的菜单，仅供参考使用
     *
     * @param context
     * @param packageName
     * @param <M>
     * @return
     */
    public static <M extends MenuItem> List<M> getMenuItemByController(ApplicationContext context,
                                                                       @NonNull final String packageName, @NonNull final String actionName) {

        List<MenuItem> menuItems = menuCache.get(packageName);

        if (menuItems != null) {
            return (List<M>) menuItems;
        }

        synchronized (packageName.intern()) {

            List<Object> controllers = SpringContextHolder.findBeanByPkgName(context, Controller.class, packageName);

            menuItems = new LinkedList<>();

            for (Object controller : controllers) {

                Class<?> type = AopProxyUtils.ultimateTargetClass(controller);

                String defaultName = type.getSimpleName();

                if (defaultName.endsWith("Controller")) {
                    defaultName = defaultName.substring(0, defaultName.length() - "Controller".length());
                }

                MenuResTag menuResTag = AnnotatedElementUtils.findMergedAnnotation(type, MenuResTag.class);
                Tag tag = AnnotatedElementUtils.findMergedAnnotation(type, Tag.class);

                if (menuResTag == null || !menuResTag.value()) {
                    //忽略非菜单资源标记的控制器
                    continue;
                }

                RequestMapping mapping = AnnotatedElementUtils.findMergedAnnotation(type, RequestMapping.class);

                if (mapping == null) {
                    continue;
                }

                SimpleMenu menuRes = new SimpleMenu();

                //查找任意一个方法的权限
                ResAuthorize resAuthorize = getClassResAuthorizeFormCache(type).values().stream()
                        .filter(r -> actionName.equalsIgnoreCase(r.action()))
                        .findFirst()
                        .orElse(null);

                ResPermission permission = resAuthorize == null ? null :
                        new ResPermission()
                                .setDomain(packageName)
                                .setType(resAuthorize.type())

                                //.setRes(tagName)
                                //不标识具体的资源
                                .setRes(StringUtils.hasText(resAuthorize.res()) ? resAuthorize.res() : "")

                                .setAction(actionName);
                //@todo 设置权限

                //设置默认权限
                menuRes.setRequireAuthorizations(permission == null ? null : JsonStrArrayUtils.toStrArrayJson(permission))
                        .setDomain(packageName)
                        //设置路径
                        .setPath(Arrays.asList(mapping != null ? mapping.path() : new String[0]).stream().filter(StringUtils::hasText).findFirst().orElse(defaultName))
                        //设置菜单名称
                        .setName(Arrays.asList(tag != null ? tag.description() : null).stream().filter(StringUtils::hasText).findFirst().orElse(defaultName))
                ;

                menuItems.add(menuRes);

            }

            menuCache.put(packageName, menuItems);
        }

        return (List<M>) menuItems;
    }

}
