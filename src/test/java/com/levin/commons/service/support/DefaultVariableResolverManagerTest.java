package com.levin.commons.service.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Collections;

class DefaultVariableResolverManagerTest {

    @Test
    void shouldResolveThreadLocalResolverBeforeGlobalResolver() {
        // 业务规则：同名变量优先使用线程级解析器，避免跨请求污染。
        DefaultVariableResolverManager manager = new DefaultVariableResolverManager();

        VariableResolver globalResolver = resolver("same-key", "global");
        VariableResolver threadResolver = resolver("same-key", "thread");

        manager.add(Collections.singletonList(globalResolver));
        manager.add(true, Collections.singletonList(threadResolver));

        String value = manager.<String>resolve("same-key", null, false, true, new Type[0]).get("fallback");

        Assertions.assertEquals("thread", value, "同名变量应优先命中线程级解析器");
    }

    @Test
    void shouldReturnNotValueWhenNoResolverMatches() {
        // 业务规则：未命中解析器时应返回 notValue，并允许回退默认值。
        DefaultVariableResolverManager manager = new DefaultVariableResolverManager();

        ValueHolder<String> holder = manager.resolve("unknown", null, false, false);

        Assertions.assertFalse(holder.hasValue(), "未命中变量时 hasValue 应为 false");
        Assertions.assertEquals("default", holder.get("default"), "未命中变量时应返回调用方默认值");
    }

    private VariableResolver resolver(String expectName, String value) {
        return new VariableResolver() {
            @Override
            public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws VariableNotFoundException {
                if (expectName.equals(name)) {
                    return (ValueHolder<T>) new ValueHolder<String>().setName(name).setValue(value).setHasValue(true);
                }
                return ValueHolder.notValue(throwExWhenNotFound, name);
            }
        };
    }
}
