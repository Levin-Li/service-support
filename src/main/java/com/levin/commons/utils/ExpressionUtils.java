package com.levin.commons.utils;

import com.levin.commons.service.support.VariableInjector;
import com.levin.commons.service.support.VariableResolver;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import lombok.SneakyThrows;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.util.ReflectionUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.hasText;

/**
 * 表达式工具
 */
public abstract class ExpressionUtils {

    //线程安全的解析器
    private static final ExpressionParser expressionParser = new SpelExpressionParser();

    private static final ThreadLocal<List<VariableResolver>> currentThreadVariableResolvers = new ThreadLocal<List<VariableResolver>>() {
        @Override
        public List<VariableResolver> get() {

            List<VariableResolver> variableResolvers = super.get();

            if (variableResolvers == null) {
                variableResolvers = Collections.emptyList();
            }

            return variableResolvers;
        }
    };

    /**
     * 设置当前线程变量解析器
     *
     * @param variableResolvers 为空，则清空原有设置
     */
    public static void setVariableResolversForCurrentThread(List<VariableResolver> variableResolvers) {
        if (variableResolvers != null) {
            currentThreadVariableResolvers.set(variableResolvers);
        } else {
            currentThreadVariableResolvers.remove();
        }
    }


    /**
     * eval groovy
     *
     * @param cacheMap                  类缓存Map
     * @param groovyClassLoaderSupplier 类加载器
     * @param scriptText
     * @param fileName
     * @param contexts
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> T evalGroovy(@NotNull Map<String, Class<Object>> cacheMap, @Nullable Supplier<GroovyClassLoader> groovyClassLoaderSupplier,
                                   @NotNull String scriptText, @NotNull String fileName, Map<String, Object>... contexts) {

        Class<Object> scriptClass = cacheMap.get(scriptText);

        if (scriptClass == null) {

            final GroovyClassLoader groovyClassLoader = groovyClassLoaderSupplier == null ? new GroovyClassLoader() : groovyClassLoaderSupplier.get();

            //编译脚本，生成类定义
            scriptClass = groovyClassLoader.parseClass(scriptText, fileName);

            cacheMap.put(scriptText, scriptClass);
        }

        if (Script.class.isAssignableFrom(scriptClass)) {

            LinkedHashMap tempCtx = new LinkedHashMap();

            if (contexts != null) {
                for (Map<String, Object> context : contexts) {
                    if (context != null) {
                        tempCtx.putAll(context);
                    }
                }
            }

            Binding context = new Binding(tempCtx);

            return (T) InvokerHelper.newScript(scriptClass, context).run();

        } else {
            throw new UnsupportedOperationException("不支持的脚本");
        }
    }

    /**
     * groovy 脚本求值
     *
     * @param expression
     * @param consumer
     * @param contexts
     * @param <T>
     * @return
     */
    public static <T> T evalGroovy(String expression, BiConsumer<GroovyScriptEvaluator, Map<String, Object>> consumer, Map<String, Object>... contexts) {
        return evalGroovy(expression, Arrays.asList(contexts), consumer);
    }

    /**
     * groovy 脚本求值
     *
     * @param expression
     * @param contexts
     * @param consumers
     * @param <T>
     * @return
     */
    public static <T> T evalGroovy(String expression, List<Map<String, Object>> contexts, BiConsumer<GroovyScriptEvaluator, Map<String, Object>>... consumers) {

        GroovyScriptEvaluator groovyScriptEvaluator = new GroovyScriptEvaluator();

        Map<String, Object> arguments = new LinkedHashMap<>();

        Optional.ofNullable(contexts).ifPresent(
                maps ->
                        maps.stream().filter(Objects::nonNull)
                                .forEachOrdered(arguments::putAll)
        );

        //执行
        Optional.ofNullable(consumers).ifPresent(funs ->
                Stream.of(funs).filter(Objects::nonNull).forEachOrdered(fun ->
                        fun.accept(groovyScriptEvaluator, arguments)
                )
        );

        return (T) groovyScriptEvaluator.evaluate(new StaticScriptSource(expression), arguments);
    }

    /**
     * spring el 求值
     *
     * @param rootObject
     * @param expression
     * @param contexts
     * @param <T>
     * @return
     */
    public static <T> T evalSpEL(Object rootObject, String expression, Consumer<StandardEvaluationContext> consumer, Map<String, Object>... contexts) {
        return evalSpEL(rootObject, expression, consumer, Arrays.asList(contexts));
    }

    public static <T> T evalSpEL(Object rootObject, String expression, Consumer<StandardEvaluationContext> consumer, List<Map<String, Object>> contexts) {
        return evalSpEL(rootObject, null, expression, contexts, consumer);
    }

    /**
     * spring el 求值
     *
     * @param rootObject
     * @param expression
     * @param contexts
     * @param <T>
     * @return
     */
    public static <T> T evalSpEL(Object rootObject, VariableResolver variableResolver, String expression, List<Map<String, Object>> contexts, Consumer<StandardEvaluationContext>... consumers) {
        final StandardEvaluationContext ctx = new StandardEvaluationContext(rootObject) {

            Map<String, Object> variableNames = new HashMap<>();

            @Override
            public void setVariable(String name, Object value) {
                super.setVariable(name, value);
                variableNames.put(name, true);
            }

            @Override
            public Object lookupVariable(String name) {

                Object value = super.lookupVariable(name);

                //如果有这个变量名称，不管什么值，都直接返回
                if (variableNames.containsKey(name)) {
                    return value;
                }

                if (value == null && variableResolver != null
                        && variableResolver.isSupported(name)) {
                    value = variableResolver.resolve(name, false, null);
                }

                if (value == null) {
                    //线程变量
                    value = VariableInjector.eval(name, null, false, currentThreadVariableResolvers.get());
                }

                return value;
            }
        };

        //注册函数
        ctx.registerFunction("isEmpty", isEmptyMethod);
        ctx.registerFunction("isNotEmpty", isNotEmptyMethod);

        //设置参数
        Optional.ofNullable(contexts).ifPresent(
                maps ->
                        maps.stream()
                                .filter(Objects::nonNull)
                                //   .map(map -> (Map<String, Object>) map)
                                .forEachOrdered(ctx::setVariables)
        );

        //执行
        Optional.ofNullable(consumers).ifPresent(funs ->
                Stream.of(funs).filter(Objects::nonNull).forEachOrdered(fun ->
                        fun.accept(ctx)
                )
        );

        return (T) expressionParser.parseExpression(expression).getValue(ctx);

    }


    private static final Method isEmptyMethod;
    private static final Method isNotEmptyMethod;

    static {

        try {
            isEmptyMethod = ExpressionUtils.class.getDeclaredMethod("isEmpty", Object.class);
            isNotEmptyMethod = ExpressionUtils.class.getDeclaredMethod("isNotEmpty", Object.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean isEmpty(Object value) {
        return !isNotEmpty(value);
    }

    /**
     * 是否非空 null 或是空字符串
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(Object value) {

        if (value == null) {
            return false;
        } else if (value instanceof CharSequence) {
            return hasText((CharSequence) value);
        } else if (value.getClass().isArray()) {
            return (Array.getLength(value) > 0);
        } else if (value instanceof Collection) {
            return (((Collection) value).size() > 0);
        } else if (value instanceof Map) {
            return (((Map) value).size() > 0);
        }

        return true;
    }

}
