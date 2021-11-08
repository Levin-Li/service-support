package com.levin.commons.rbac;

import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.MapUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public abstract class RbacUtils {

    private static final MultiValueMap<String, Res> beanResCache = new LinkedMultiValueMap<>();

    /**
     * 获取资源类型
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static Set<Identifiable> loadResTypesFromSpringCtx(@NotNull ApplicationContext context, @NotNull String pkgName, Function<String, String> nameMapper) {
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

        return beanResCache.get(pkgName).parallelStream()
                .map(res -> new IdentifiableObject()
                        .setId(res.getType())
                        //设置名称，试图映射名称
                        .setName(nameMapper != null ? nameMapper.apply(res.getType()) : res.getType()))
                .collect(Collectors.toSet());

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

        return beanResCache.get(pkgName).parallelStream()
                .filter(res -> !StringUtils.hasText(type) || type.equals(res.getType()))
                .collect(Collectors.toList());

    }


    private static void initBeanResCache(ApplicationContext context) {

        if (!beanResCache.isEmpty()) {
            return;
        }

        context.getBeansWithAnnotation(ResAuthorize.class)
                .entrySet().parallelStream().forEach((it) -> {

            String beanName = it.getKey();

            Class<?> beanType = AopProxyUtils.ultimateTargetClass(it.getValue());

            if (!beanName.contains(".")) {
                log.warn("忽略有ResAuthorize注解的 bean {} [ {} ]", beanName, beanType.getName());
                return;
            }


            Tag tag = beanType.getAnnotation(Tag.class);

            if (tag == null || !StringUtils.hasText(tag.name())) {
                log.warn("bean {} [ {} ] 无 Tag 注解或注解 name 属性 没有值，被被忽略. ", beanName, beanType.getName());
                return;
            }

            final ResAuthorize resAuthorize = AnnotatedElementUtils.getMergedAnnotation(beanType, ResAuthorize.class);

            //
            final String pkgName = beanName.substring(beanName.startsWith("plugin.") ? "plugin.".length() : 0, beanName.lastIndexOf('.'));

            SimpleRes res = new SimpleRes()
                    .setDomain(pkgName)
                    .setId(tag.name())
                    .setActionList(new ArrayList<>(10));

            //获取方法上的注解描述
            for (Method method : beanType.getMethods()) {

                Operation operation = method.getAnnotation(Operation.class);

                if (operation == null) {
                    continue;
                }

                String actionName = operation.summary();

                if (!StringUtils.hasText(actionName)) {
                    actionName = method.getName();
                }

                ResAuthorize fieldResAuthorize = getAnnotation(MapUtils
                                .putFirst(ResPermission.Fields.domain, pkgName)
                                .put(ResPermission.Fields.type, resAuthorize.type())
                                .put(ResPermission.Fields.res, res.getId())
                                .put(ResPermission.Fields.action, actionName)
                                .build()
                        , method, resAuthorize);

                if (fieldResAuthorize == null || fieldResAuthorize.ignored()) {
                    continue;
                }

                //设置资源类型
                res.setType(fieldResAuthorize.type());

                //加入操作列表
                res.getActionList()
                        .add(new SimpleResAction().setId(fieldResAuthorize.action()).setName(actionName));

            }

            //资源加入缓存
            if (res.getActionList().size() > 0) {
                beanResCache.add(pkgName, res);
            }

        });

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
