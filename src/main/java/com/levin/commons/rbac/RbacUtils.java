package com.levin.commons.rbac;

import cn.hutool.core.util.StrUtil;
import com.levin.commons.service.domain.SimpleIdentifiable;
import com.levin.commons.service.support.SpringContextHolder;
import com.levin.commons.ui.annotation.CRUD;
import com.levin.commons.utils.DisableApiOperationUtils;
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

import jakarta.validation.constraints.NotNull;

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
    public static List<SimpleIdentifiable> loadResTypeFromSpringCtx(@NotNull ApplicationContext context, @NotNull String pkgName, Function<String, String> nameMapper) {
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
                .collect(Collectors.toList());

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

        Assert.notNull(beanOrType, "beanOrType 参数不能为空");

        Class<?> beanType = (beanOrType instanceof Class) ? (Class<?>) beanOrType : AopProxyUtils.ultimateTargetClass(beanOrType);

        return resAuthorizeCache.computeIfAbsent(beanType, RbacUtils::loadClassResAuthorize);

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

        final Map<Method, ResAuthorize> methodResAuthorizeMap = new LinkedHashMap<>();

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

                        //没有被禁止
                        && DisableApiOperationUtils.isApiEnable(beanType, m)
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

            //如果没有忽略
            if (!fieldResAuthorize.ignored()) {

                Assert.hasText(fieldResAuthorize.domain(), "需要鉴权的控制器方法[ResAuthorize]注解" + ResPermission.Fields.domain + "属性未设置，方法：" + method);

                Assert.hasText(fieldResAuthorize.type(), "需要鉴权的控制器方法[ResAuthorize]注解" + ResPermission.Fields.type + "属性未设置，方法：" + method);

                Assert.hasText(fieldResAuthorize.action(), "需要鉴权的控制器方法[ResAuthorize]注解" + ResPermission.Fields.action + "属性未设置，方法：" + method);

                //Assert.notNull(fieldResAuthorize.res(), "需要鉴权的控制器方法[ResAuthorize]注解res属性未设置，方法：" + method);
            } else if (!loadAllMethod) {
                continue;
            }

            //检查特殊字符
            checkAllowChar(fieldResAuthorize.domain(), ResPermission.Fields.domain, method);
            checkAllowChar(fieldResAuthorize.type(), ResPermission.Fields.type, method);
            checkAllowChar(fieldResAuthorize.res(), ResPermission.Fields.res, method);
            checkAllowChar(fieldResAuthorize.action(), ResPermission.Fields.action, method);


            methodResAuthorizeMap.put(method, fieldResAuthorize);
        }

        return methodResAuthorizeMap;

    }

    private static void checkAllowChar(String txt, String section, Method method) {
        if (StringUtils.hasText(txt)
                && (txt.contains(Permission.OR_DELIMITER) || txt.contains(Permission.DELIMITER))) {
            throw new IllegalArgumentException(String.format("ResAuthorize注解的[%s]属性不能包含[%s%s]字符，关联方法：%s", section, Permission.OR_DELIMITER, Permission.DELIMITER, method));
        }
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
                res.getActionList().add(RbacBaseAuthorizeService.newResConditionAction(fieldResAuthorize));

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

        final String cacheKey = packageName + "#" + actionName;

        List<MenuItem> menuItems = menuCache.get(cacheKey);

        if (menuItems != null) {
            return (List<M>) menuItems;
        }

        synchronized (cacheKey.intern()) {

            List<Object> controllers = SpringContextHolder.findBeanByPkgName(context, Controller.class, packageName);

            menuItems = new LinkedList<>();

            for (Object controller : controllers) {

                Class<?> type = AopProxyUtils.ultimateTargetClass(controller);

                String defaultName = type.getSimpleName();

                if (defaultName.endsWith("Controller")) {
                    defaultName = defaultName.substring(0, defaultName.length() - "Controller".length());
                }

                CRUD crud = AnnotatedElementUtils.findMergedAnnotation(type, CRUD.class);
                if (crud == null) {
                    continue;
                }

                Tag tag = AnnotatedElementUtils.findMergedAnnotation(type, Tag.class);

                RequestMapping mapping = AnnotatedElementUtils.findMergedAnnotation(type, RequestMapping.class);

                if (mapping == null) {
                    continue;
                }

                SimpleMenu menuRes = new SimpleMenu();
                Map<Method, ResAuthorize> methodResAuthorizeMap = getClassResAuthorizeFormCache(type);

                //查找任意一个方法的权限
                ResAuthorize resAuthorize = methodResAuthorizeMap.values().stream()
                        .filter(r -> actionName.equalsIgnoreCase(r.action()))
                        .findFirst()
                        .orElse(null);

                //@todo 思考如果是不要权限的菜单呢？自答不要权限的菜单也必须标注不要权限，这样 resAuthorize 变量就不会为 null
                if (resAuthorize == null) {
                    continue;
                }

                final ResPermission permission = resAuthorize == null ? null :
                        new ResPermission()
                                .setDomain(resAuthorize.domain())
                                .setType(resAuthorize.type())

                                //.setRes(tagName)
                                //不标识具体的资源
                                .setRes(StringUtils.hasText(resAuthorize.res()) ? resAuthorize.res() : "")

                                .setAction(resAuthorize.action());
                //@todo 设置权限

                //设置默认权限
                menuRes.setRequireAuthorizations(permission == null ? null : Arrays.asList(permission.toString()))
                        .setDomain(packageName)
                        //设置路径
                        .setPath(buildRequestPath(mapping, null, defaultName))
                        //设置菜单名称
                        .setName(StrUtil.firstNonBlank(crud.title(), tag == null ? null : tag.name(), defaultName))
                        .setRemark(StrUtil.firstNonBlank(crud.desc(), tag == null ? null : tag.description(), ""))
                        .setOpButtonList(buildOpButtonList(packageName, mapping, methodResAuthorizeMap))
                ;

                menuItems.add(menuRes);

            }

            menuCache.put(cacheKey, menuItems);
        }

        return (List<M>) menuItems;
    }

    private static List<MenuItem.OpButton> buildOpButtonList(String packageName,
                                                             RequestMapping controllerMapping,
                                                             Map<Method, ResAuthorize> methodResAuthorizeMap) {
        if (methodResAuthorizeMap == null || methodResAuthorizeMap.isEmpty()) {
            return Collections.emptyList();
        }

        final List<MenuItem.OpButton> opButtonList = new ArrayList<>();

        methodResAuthorizeMap.forEach((method, resAuthorize) -> {
            if (method == null || resAuthorize == null) {
                return;
            }

            CRUD.Op op = AnnotatedElementUtils.findMergedAnnotation(method, CRUD.Op.class);
            if (op == null) {
                return;
            }

            Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);
            RequestMapping methodMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);

            final ResPermission permission = new ResPermission()
                    .setDomain(resAuthorize.domain())
                    .setType(resAuthorize.type())
                    .setRes(StringUtils.hasText(resAuthorize.res()) ? resAuthorize.res() : "")
                    .setAction(resAuthorize.action());

            final MenuItem.OpButton opButton = new MenuItem.OpButton()
                    .setApiUrl(buildRequestPath(controllerMapping, methodMapping, method.getName()))
                    .setLabel(StrUtil.firstNonBlank(op.label(), op.name(), operation == null ? null : operation.summary(), method.getName()))
                    .setRequireAuthorization(permission.toString())
                    .setRemark(StrUtil.firstNonBlank(op.desc(), operation == null ? null : operation.description(), resAuthorize.remark(), ""))
                    .setDisabled(false);

            opButtonList.add(opButton);
        });

        opButtonList.sort(Comparator.comparing(MenuItem.OpButton::getApiUrl, Comparator.nullsLast(String::compareTo)));

        return opButtonList.isEmpty() ? Collections.emptyList() : opButtonList;
    }

    private static String buildRequestPath(RequestMapping controllerMapping, RequestMapping methodMapping, String defaultPath) {
        final String controllerPath = firstMappingPath(controllerMapping);
        final String methodPath = firstMappingPath(methodMapping);

        if (!StringUtils.hasText(controllerPath) && !StringUtils.hasText(methodPath)) {
            return defaultPath;
        }

        if (!StringUtils.hasText(controllerPath)) {
            return normalizePath(methodPath);
        }

        if (!StringUtils.hasText(methodPath)) {
            return normalizePath(controllerPath);
        }

        return normalizePath(controllerPath) + "/" + normalizePath(methodPath).replaceFirst("^/+", "");
    }

    private static String firstMappingPath(RequestMapping mapping) {
        if (mapping == null) {
            return "";
        }

        return Arrays.stream(mapping.path())
                .filter(StringUtils::hasText)
                .findFirst()
                .orElseGet(() -> Arrays.stream(mapping.value())
                        .filter(StringUtils::hasText)
                        .findFirst()
                        .orElse(""));
    }

    private static String normalizePath(String path) {
        if (!StringUtils.hasText(path)) {
            return "";
        }

        String normalized = path.trim().replaceAll("/{2,}", "/");
        normalized = normalized.startsWith("/") ? normalized : "/" + normalized;
        return normalized.length() > 1 ? normalized.replaceAll("/+$", "") : normalized;
    }

}
