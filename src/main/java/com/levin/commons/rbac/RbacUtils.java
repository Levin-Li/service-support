package com.levin.commons.rbac;

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
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public abstract class RbacUtils {

    private static final LinkedMultiValueMap<String, Res> beanResCache = new LinkedMultiValueMap<>();

    private static final Map<String, List<MenuItem>> menuCache = new ConcurrentHashMap<>();

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


    private static void initBeanResCache(ApplicationContext context) {

        if (!beanResCache.isEmpty()) {
            return;
        }

        final Map<String, SimpleRes> cacheMap = new LinkedHashMap<>();

        context.getBeansWithAnnotation(Controller.class)
                .entrySet().stream().forEach((it) -> {

            String beanName = it.getKey();

            Class<?> beanType = AopProxyUtils.ultimateTargetClass(it.getValue());

//            Tag tag = beanType.getAnnotation(Tag.class);
//
//            if (tag == null || !StringUtils.hasText(tag.name())) {
//                log.warn("bean {} [ {} ] 无 Tag 注解或注解 name 属性 没有值，被被忽略. ", beanName, beanType.getName());
//                return;
//            }

            //获取类注解
            final ResAuthorize classResAuthorize = AnnotatedElementUtils.getMergedAnnotation(beanType, ResAuthorize.class);

            //final RequestMapping classRequestMapping = AnnotatedElementUtils.getMergedAnnotation(beanType, RequestMapping.class);

            final Map<String, Object> classResAuthorizeAttrs = classResAuthorize != null ? AnnotationUtils.getAnnotationAttributes(classResAuthorize) : Collections.emptyMap();

            //获取方法上的注解描述
            for (Method method : beanType.getMethods()) {

                //如果没有请求注解，将忽略
                if (AnnotatedElementUtils.getMergedAnnotation(method, RequestMapping.class) == null) {
                    continue;
                }

                //获取方法注解
                ResAuthorize fieldResAuthorize = AnnotatedElementUtils.getMergedAnnotation(method, ResAuthorize.class);

                if (classResAuthorize == null && fieldResAuthorize == null) {
                    log.warn("控制器方法 {} 没有可用的[ResAuthorize]注解，将不进行鉴权", method);
                    continue;
                }

                Operation operation = method.getAnnotation(Operation.class);

                Assert.notNull(operation, "需要鉴权的控制器方法必须定义[Operation]注解，控制器方法：" + method);
                Assert.isTrue(StringUtils.hasText(operation.summary()), "需要鉴权的控制器方法[Operation]注解的summary属性需要指定，控制器方法：" + method);
                Assert.isTrue(Arrays.stream(operation.tags()).anyMatch(StringUtils::hasText), "需要鉴权的控制器方法[Operation]注解的tags属性需要指定，控制器方法：" + method);

                //资源标识
                final String resId = Arrays.stream(operation.tags()).filter(StringUtils::hasText).findFirst().orElse(beanType.getSimpleName());

                String actionName = StringUtils.hasText(operation.summary()) ? operation.summary() : null;

                if (!StringUtils.hasText(actionName)) {
                    log.warn("控制器方法 {} 没有 Operation注解或是Operation注解的summary属性没有定义.", method);
                    actionName = method.getName();
                } else if (actionName.endsWith(resId)) {
                    //权限名称，去除实体名称，如：新建用户 变为 新建
                    actionName = actionName.substring(0, actionName.length() - resId.length());
                }

                //复制父类
                final Map<String, Object> fieldResAuthorizeAttrs = new LinkedHashMap<>(classResAuthorizeAttrs);

                //设置操作名称
                fieldResAuthorizeAttrs.put(ResPermission.Fields.action, actionName);
                fieldResAuthorizeAttrs.put(ResPermission.Fields.res, resId);

                if (fieldResAuthorize != null) {
                    Map<String, Object> tempAttrs = AnnotationUtils.getAnnotationAttributes(classResAuthorize);
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

                //重新定义
                fieldResAuthorize = AnnotationUtils.synthesizeAnnotation(fieldResAuthorizeAttrs, ResAuthorize.class, null);

                if (fieldResAuthorize.ignored()) {
                    continue;
                }

                Assert.isTrue(StringUtils.hasText(fieldResAuthorize.domain()), "需要鉴权的控制器方法[ResAuthorize]注解" + SimpleRes.Fields.domain + "属性未设置，方法：" + method);
                Assert.isTrue(StringUtils.hasText(fieldResAuthorize.type()), "需要鉴权的控制器方法[ResAuthorize]注解" + SimpleRes.Fields.type + "属性未设置，方法：" + method);
                Assert.isTrue(StringUtils.hasText(fieldResAuthorize.res()), "需要鉴权的控制器方法[ResAuthorize]注解res属性未设置，方法：" + method);

                final String key = fieldResAuthorize.domain() + fieldResAuthorize.type() + fieldResAuthorize.res();

                final ResAuthorize tempResAuthorize = fieldResAuthorize;

                final SimpleRes res = MapUtils.getAndAutoPut(cacheMap, key, null, () -> new SimpleRes()
                        .setDomain(tempResAuthorize.domain())
                        .setType(tempResAuthorize.type())
                        .setId(tempResAuthorize.res())
                        .setActionList(new ArrayList<>(10)));

                //加入操作列表
                res.getActionList().add(SimpleResAction.newAction(fieldResAuthorize).setRemark(operation.description()));
            }

            //加入缓存
            cacheMap.forEach((k, v) -> {
                if (StringUtils.hasText(v.getDomain())
                        && v.getActionList().size() > 0) {
                    beanResCache.add(v.getDomain(), v);
                }
            });

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
    public static <M extends MenuItem> List<M> getMenuItemByController(ApplicationContext context, @NonNull final String packageName, @NonNull final String actionName) {

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
                    defaultName = defaultName.toLowerCase().substring(0, defaultName.length() - "Controller".length());
                }

                MenuResTag menuResTag = AnnotatedElementUtils.getMergedAnnotation(type, MenuResTag.class);

                if (menuResTag == null || !menuResTag.value()) {
                    //忽略非菜单资源标记的控制器
                    continue;
                }

                RequestMapping mapping = AnnotatedElementUtils.getMergedAnnotation(type, RequestMapping.class);
                Tag tag = AnnotatedElementUtils.getMergedAnnotation(type, Tag.class);
                ResAuthorize resAuthorize = AnnotatedElementUtils.getMergedAnnotation(type, ResAuthorize.class);

//            if (mapping == null
//                    || tag == null
//                    || resAuthorize == null) {
//
//                continue;
//            }

                SimpleMenu menuRes = new SimpleMenu();

                //设置权限
                ResPermission permission = new ResPermission()
                        .setDomain(packageName)
                        .setType(resAuthorize != null ? resAuthorize.type() : "数据")
                        .setRes(tag != null ? tag.name() : defaultName)
                        .setAction(actionName);
                //@todo 设置权限

                //设置默认权限
                menuRes.setRequireAuthorizations(JsonStrArrayUtils.toStrArrayJson(permission))
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
