package com.levin.commons.utils;

import com.levin.commons.service.domain.DisableApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class DisableApiOperationUtils {

    /**
     * 判断某个API方法是否可以访问
     *
     * @param beanType
     * @param method
     * @return
     */
    public static boolean isApiEnable(Class<?> beanType, Method method) {

        if (beanType == null) {
            beanType = method.getDeclaringClass();
        }

        DisableApiOperation disableApi = AnnotatedElementUtils.findMergedAnnotation(method, DisableApiOperation.class);

        if (disableApi != null) {
            return false;
        }

        disableApi = AnnotatedElementUtils.findMergedAnnotation(beanType, DisableApiOperation.class);

        if (disableApi != null && (disableApi.value().length > 0 || disableApi.excludes().length > 0)) {

            final Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);
            final RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);


            Predicate<String[]> predicate = patterns -> Stream.of(patterns).filter(StringUtils::hasText).map(String::trim).anyMatch(

                    txt -> txt.equals(method.toGenericString())
                            //方法名，匹配
                            || txt.toLowerCase().startsWith("method:") && PatternMatchUtils.simpleMatch(txt.substring("method:".length()), method.getName())
                            || PatternMatchUtils.simpleMatch(txt, operation != null ? operation.summary() : null)
                            || txt.toLowerCase().startsWith("path:") && requestMapping != null && Stream.of(requestMapping.value()).filter(StringUtils::hasText).anyMatch(path -> PatternMatchUtils.simpleMatch(txt.substring("path:".length()), path))
            );

            //如果匹配禁止的
            if (disableApi.value().length > 0
                    && predicate.test(disableApi.value())) {
                return false;
            }

            //如果匹配排除的
            if (disableApi.excludes().length > 0) {
                return predicate.test(disableApi.excludes());
            }

        }

        return true;
    }

}
