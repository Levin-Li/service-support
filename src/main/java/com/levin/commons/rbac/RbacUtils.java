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
import org.springframework.stereotype.Controller;
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

        context.getBeansWithAnnotation(Controller.class)
                .entrySet().parallelStream().forEach((it) -> {

            String beanName = it.getKey();

            Class<?> beanType = AopProxyUtils.ultimateTargetClass(it.getValue());


            Tag tag = beanType.getAnnotation(Tag.class);

            if (tag == null || !StringUtils.hasText(tag.name())) {
                log.warn("bean {} [ {} ] 无 Tag 注解或注解 name 属性 没有值，被被忽略. ", beanName, beanType.getName());
                return;
            }


            //获取包名
            // final String pkgName = beanName.substring(beanName.startsWith("plugin.") ? "plugin.".length() : 0, beanName.lastIndexOf('.'));


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

                String actionName = operation != null && StringUtils.hasText(operation.summary()) ? operation.summary() : method.getName();

                ResAuthorize fieldResAuthorize = getAnnotation(MapUtils
                                .putFirst(ResPermission.Fields.domain, classResAuthorize != null ? classResAuthorize.domain() : "")
                                .put(ResPermission.Fields.type, classResAuthorize != null ? classResAuthorize.type() : "")
                                .put(ResPermission.Fields.res, tag.name())
                                .put(ResPermission.Fields.action, actionName)
                                .build()
                        , method, classResAuthorize);

                if (fieldResAuthorize == null || fieldResAuthorize.ignored()) {
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
                res.getActionList()
                        .add(new SimpleResAction().setId(fieldResAuthorize.action()).setName(fieldResAuthorize.action()));

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
