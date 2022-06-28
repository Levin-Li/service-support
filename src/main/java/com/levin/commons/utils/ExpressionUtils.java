package com.levin.commons.utils;

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

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 表达式工具
 */
public abstract class ExpressionUtils {

    //线程安全的解析器
    private static final ExpressionParser expressionParser = new SpelExpressionParser();


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
    public static <T> T evalGroovy(@NotNull Map<String, Class<?>> cacheMap, @Nullable Supplier<GroovyClassLoader> groovyClassLoaderSupplier,
                                   @NotNull String scriptText, @NotNull String fileName, Map<String, ?>... contexts) {

        Class<?> scriptClass = cacheMap.get(scriptText);

        if (scriptClass == null) {

            final GroovyClassLoader groovyClassLoader = groovyClassLoaderSupplier == null ? new GroovyClassLoader() : groovyClassLoaderSupplier.get();

            //编译脚本，生成类定义
            scriptClass = groovyClassLoader.parseClass(scriptText, fileName);

            cacheMap.put(scriptText, scriptClass);
        }

        if (Script.class.isAssignableFrom(scriptClass)) {

            LinkedHashMap tempCtx = new LinkedHashMap();

            if (contexts != null) {
                for (Map<String, ?> context : contexts) {
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
        return evalSpEL(rootObject, expression, Arrays.asList(contexts), consumer);
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
    public static <T> T evalSpEL(Object rootObject, String expression, List<Map<String, Object>> contexts, Consumer<StandardEvaluationContext>... consumers) {

        final StandardEvaluationContext ctx = new StandardEvaluationContext(rootObject);

        Optional.ofNullable(contexts).ifPresent(
                maps ->
                        maps.stream().filter(Objects::nonNull)
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

}
