package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.ExpressionUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.ResolvableType;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 变量注入器
 */
public interface SimpleVariableInjector extends VariableInjector {

    SimpleVariableInjector defaultSimpleVariableInjector = new SimpleVariableInjector() {
    };

    /**
     * 获取变量解析器列表
     *
     * @param suppliers 上下文列表
     * @return
     */
    default List<VariableResolver> getVariableResolvers(Supplier<List<Map<String, Object>>>... suppliers) {
        return Arrays.asList(getSpelVariableResolver(suppliers)
                , getGroovyVariableResolver(suppliers)
                , getSimpleVariableResolver(suppliers));
    }

    /**
     * 获取简单变量解析器
     *
     * @param suppliers 上下文列表
     * @return
     */
    default VariableResolver getSimpleVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
        return new SimpleVariableResolver(suppliers);
    }

    /**
     * 获取 Spel 变量解析器
     *
     * @param suppliers 上下文列表
     * @return
     */
    default VariableResolver getSpelVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
        return new SpelVariableResolver(suppliers);
    }

    /**
     * groovy 变量解析器 上下文列表
     *
     * @param suppliers
     * @return
     */
    default VariableResolver getGroovyVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
        return new GroovyVariableResolver(suppliers);
    }


    /**
     * @param targetBean
     * @param suppliers  环境变量支持
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Override
    default void inject(Object targetBean, Supplier<List<Map<String, Object>>>... suppliers) throws VariableInjectException, VariableNotFoundException {
        injectByVariableResolver(targetBean, getVariableResolvers(suppliers));
    }


    /**
     * 按字段注解注入变量
     * <p>
     * 本方法式功能实现的核心方法
     *
     * @param targetBean
     * @param suppliers  变量解析器列表
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Override
    default void injectByVariableResolver(Object targetBean, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException {

        ResolvableType resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());

        //提取所有的变量解析器
        List<VariableResolver> variableResolvers = Stream.of(suppliers)
                .filter(Objects::nonNull)
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .reduce(new LinkedList<>(), (first, second) -> {
                    first.addAll(second);
                    return first;
                });


        for (Field field : ClassUtils.getFields(targetBean.getClass(), InjectVar.class)) {

            InjectVar injectVar = field.getAnnotation(InjectVar.class);

            ResolvableType forField = ResolvableType.forField(field, resolvableTypeRoot);

            //1、获取字段类型
            Class<?> fieldType = forField.resolve(field.getType());

            field.setAccessible(true);

            ValueHolder<Object> valueHolder = ValueHolder.NOT_VALUE;

            String varName = injectVar.value();

            //默认等于变量名称
            if (!StringUtils.hasText(varName)) {
                varName = field.getName();
            }

            Object originalValue = null;//  field.get(targetBean);

            try {
                //2、获取原字段值
                originalValue = field.get(targetBean);
            } catch (IllegalAccessException e) {
                throw new VariableInjectException(field.getDeclaringClass().getName()
                        + "." + field.getName() + " can't get originalValue ," + injectVar.remark(), e);
            }

            int variableResolverCnt = 0;

            //3、尝试从多个解析器中解析变量
            for (VariableResolver variableResolver : variableResolvers) {
                if (variableResolver != null) {
                    variableResolverCnt++;
                    valueHolder = variableResolver.resolve(varName, originalValue, false, fieldType);
                    if (valueHolder != null && valueHolder.isHasValue()) {
                        break;
                    }
                }
            }


            if (valueHolder != null && valueHolder.isHasValue()) {
                //4、如果变量获取成功
                try {
                    //注入变量
                    field.set(targetBean, valueHolder.getValue());
                } catch (IllegalAccessException e) {
                    throw new VariableInjectException(field.getDeclaringClass().getName()
                            + "." + field.getName() + " inject var [" + varName + "] can't inject," + injectVar.remark(), e);
                }
            } else if (injectVar.isRequired()=="") {
                //如果变量是必须的，则抛出异常
                throw new VariableNotFoundException(injectVar.remark() + " --> " + field.getDeclaringClass().getName()
                        + "." + field.getName() + " inject var [" + varName + "] can't resolve in " + variableResolverCnt + " variableResolvers");
            }

        }
    }

    ///////////////////////////////////////////////////////////////

    @AllArgsConstructor
    class SimpleVariableResolver implements VariableResolver {

        Supplier<List<Map<String, Object>>>[] suppliers;

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException {

            for (Supplier<List<Map<String, Object>>> supplier : suppliers) {

                for (Map<String, Object> context : supplier.get()) {

                    if (context.containsKey(name)) {

                        Object value = context.get(name);

                        Class type = value != null ? value.getClass() : null;

                        if (expectTypes == null
                                || expectTypes.length < 1
                                || (type != null && Stream.of(expectTypes).anyMatch(c -> c.isAssignableFrom(type)))) {
                            return new ValueHolder<T>().setValue((T) value).setHasValue(true);
                        }
                    }
                }

            }

            if (throwExWhenNotFound) {
                throw new VariableNotFoundException("variable " + name + " not found");
            }

            return ValueHolder.NOT_VALUE;
        }

    }

    ///////////////////////////////////////////////////////////////////////
    @AllArgsConstructor
    abstract class ScriptResolver implements VariableResolver {

        protected Supplier<List<Map<String, Object>>>[] suppliers;

        abstract String getScriptPrefix();

        abstract Object eval(String scriptText, Object originalValue);

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException {

            if (name != null
                    && name.trim().toLowerCase().startsWith(getScriptPrefix().toLowerCase())) {

                //截取前缀
                name = name.substring(getScriptPrefix().length());

                Object value = eval(name, originalValue);

                Class type = value != null ? value.getClass() : null;

                if (expectTypes == null
                        || expectTypes.length < 1
                        //如果有要求类型
                        || (type != null && Stream.of(expectTypes).anyMatch(c -> c.isAssignableFrom(type)))) {

                    return new ValueHolder<T>()
                            .setValue((T) value)
                            .setHasValue(true);
                }
            }

            if (throwExWhenNotFound) {
                throw new VariableNotFoundException("variable " + name + " not found");
            }

            return ValueHolder.NOT_VALUE;
        }

    }


    /**
     * spel 解析器
     */
    class SpelVariableResolver extends ScriptResolver {

        public SpelVariableResolver(Supplier<List<Map<String, Object>>>[] suppliers) {
            super(suppliers);
        }

        @Override
        String getScriptPrefix() {
            return InjectVar.SPEL_PREFIX;
        }

        @Override
        Object eval(String scriptText, Object originalValue) {

            return ExpressionUtils.evalSpEL(null, scriptText, (ctx) -> {

                ctx.setBeanResolver(new BeanFactoryResolver(SpringContextHolder.getBeanFactory()));

                Stream.of(suppliers)
                        .filter(Objects::nonNull)
                        .map(Supplier::get)
                        .filter(Objects::nonNull)
                        .forEachOrdered(
                                //设置变量到 spel 的上下文
                                list -> list.stream()
                                        .filter(Objects::nonNull)
                                        .forEachOrdered(ctx::setVariables)
                        );
            });

        }
    }

    /**
     * groovy 解析器
     */
    class GroovyVariableResolver extends ScriptResolver {

        public GroovyVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
            super(suppliers);
        }

        @Override
        String getScriptPrefix() {
            return InjectVar.GROOVY_PREFIX;
        }

        @Override
        Object eval(String scriptText, Object originalValue) {

            return ExpressionUtils.evalGroovy(scriptText, (groovyScriptEvaluator, ctx) -> {

                //注入 Spring 上下文
                ctx.putIfAbsent("springContext", SpringContextHolder.getApplicationContext());

                Stream.of(suppliers)
                        .filter(Objects::nonNull)
                        .map(Supplier::get)
                        .filter(Objects::nonNull)
                        .forEachOrdered(
                                //设置变量到 spel 的上下文
                                list -> list.stream()
                                        .filter(Objects::nonNull)
                                        .forEachOrdered(ctx::putAll)
                        );

                //设置
                groovyScriptEvaluator.setBeanClassLoader(SpringContextHolder.getClassLoader());
            });
        }

    }


}