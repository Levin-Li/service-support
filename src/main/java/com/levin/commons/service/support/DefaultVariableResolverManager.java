package com.levin.commons.service.support;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Supplier;

@Slf4j
public class DefaultVariableResolverManager
        implements VariableResolverManager {

    private final List<VariableResolver> defaultVariableResolvers = new LinkedList<>();

    private final ThreadLocal<List<VariableResolver>> threadLevelVariableResolvers = new ThreadLocal<>();

    private final VariableInjector variableInjector;

    public DefaultVariableResolverManager(VariableInjector variableInjector) {
        this.variableInjector = variableInjector;
    }

    /**
     * @param isThreadLevel
     * @return
     */
    @Override
    public List<VariableResolver> getVariableResolvers(boolean isThreadLevel) {

        List<VariableResolver> temp = isThreadLevel ? threadLevelVariableResolvers.get() : defaultVariableResolvers;

        return temp == null ? Collections.emptyList() : Collections.unmodifiableList(temp);

    }

    /**
     * @param isThreadLevel
     * @param variableResolvers
     */
    @Override
    public synchronized VariableResolverManager addVariableResolvers(boolean isThreadLevel, VariableResolver... variableResolvers) {

        if (isThreadLevel) {

            List<VariableResolver> resolverList = threadLevelVariableResolvers.get();

            if (resolverList == null) {
                resolverList = new LinkedList<>();
                threadLevelVariableResolvers.set(resolverList);
            }

            resolverList.addAll(Arrays.asList(variableResolvers));

        } else {
            defaultVariableResolvers.addAll(Arrays.asList(variableResolvers));
        }

        return this;
    }

    /**
     * @param isThreadLevel
     * @param ctxs
     */
    @Override
    public VariableResolverManager addVariableResolverByCtx(boolean isThreadLevel, Map<String, Object>... ctxs) {

        return addVariableResolversByCtx(isThreadLevel, () -> Arrays.asList(ctxs));
    }

    /**
     * @param isThreadLevel
     * @param suppliers
     */
    @Override
    public VariableResolverManager addVariableResolversByCtx(boolean isThreadLevel, Supplier<List<Map<String, Object>>>... suppliers) {

        variableInjector.getVariableResolvers(suppliers).forEach(variableResolver -> addVariableResolvers(isThreadLevel, variableResolver));

        return this;
    }
}
