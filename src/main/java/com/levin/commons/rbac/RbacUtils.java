package com.levin.commons.rbac;

import com.levin.commons.service.domain.SimpleIdentifiable;
import com.levin.commons.service.support.SpringContextHolder;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.commons.utils.MapUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
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

        context.getBeansWithAnnotation(Controller.class)
                .entrySet().stream().forEach((it) -> {

            String beanName = it.getKey();

            Class<?> beanType = AopProxyUtils.ultimateTargetClass(it.getValue());

            Tag tag = beanType.getAnnotation(Tag.class);

            if (tag == null || !StringUtils.hasText(tag.name())) {
                log.warn("bean {} [ {} ] 无 Tag 注解或注解 name 属性 没有值，被被忽略. ", beanName, beanType.getName());
                return;
            }

            SimpleRes res = new SimpleRes().setActionList(new ArrayList<>(10));

            //获取类注解
            final ResAuthorize classResAuthorize = AnnotatedElementUtils.getMergedAnnotation(beanType, ResAuthorize.class);

            //获取方法上的注解描述
            for (Method method : beanType.getMethods()) {

                Operation operation = method.getAnnotation(Operation.class);

                if (classResAuthorize == null && (operation == null || !StringUtils.hasText(operation.summary()))) {
                    //log.warn("bean 方法 [ {} ] 无 Operation 注解或注解 summary 属性没有值，被被忽略.", method);
                    continue;
                }

                String actionName = operation != null && StringUtils.hasText(operation.summary()) ? operation.summary() : null;

                if (!StringUtils.hasText(actionName)) {
                    RequestMapping mapping = AnnotatedElementUtils.getMergedAnnotation(method, RequestMapping.class);
                    if (mapping == null) {
                        continue;
                    } else {
                        log.warn("控制器方法 {} 没有 Operation注解或是Operation注解的summary属性没有定义.", method);
                        actionName = method.getName();
                    }
                } else if (actionName.endsWith(tag.name())) {
                    //权限名称，去除实体名称，如：新建用户 变为 新建
                    actionName = actionName.substring(0, actionName.length() - tag.name().length());
                }

                ResAuthorize fieldResAuthorize = getAnnotation(MapUtils
                                .putFirst(ResPermission.Fields.domain, classResAuthorize != null ? classResAuthorize.domain() : "")
                                .put(ResPermission.Fields.type, classResAuthorize != null ? classResAuthorize.type() : "")
                                .put(ResPermission.Fields.res, tag.name())
                                .put(ResPermission.Fields.action, actionName)
                                .build()
                        , method, classResAuthorize);

                if (fieldResAuthorize == null
                        || fieldResAuthorize.ignored()) {
                    continue;
                }

                //同一个类，以第一个为准
                if (StringUtils.hasText(fieldResAuthorize.domain())
                        && !StringUtils.hasText(res.getDomain())) {
                    res.setDomain(fieldResAuthorize.domain())
                            .setType(fieldResAuthorize.type())
                            .setId(fieldResAuthorize.res());
                }

                //加入操作列表
                res.getActionList().add(SimpleResAction.newAction(fieldResAuthorize));
            }

            //资源加入缓存
            if (StringUtils.hasText(res.getDomain())
                    && res.getActionList().size() > 0) {
                beanResCache.add(res.getDomain(), res);
            } else {
                log.warn("bean {} [ {} ] => {} ，被被忽略. ", beanName, beanType.getName(), res);
                return;
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


    private static ResAuthorize getAnnotation(Map<String, Object> initProps, Method method, ResAuthorize parentResAuthorize) {

        ResAuthorize resAuthorize = ClassUtils.merge(initProps
                //默认条件为不空或是不是空字符串则覆盖
                , (k, v) -> v != null && (!(v instanceof CharSequence) || StringUtils.hasText((CharSequence) v))

                , ResAuthorize.class,
                parentResAuthorize,
                AnnotatedElementUtils.getMergedAnnotation(method, ResAuthorize.class)
        );

        return resAuthorize;
    }

}
