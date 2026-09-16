package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.MapValueContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author lilw
 */
public class FixedMapValueContext<K,V> implements MapValueContext<K, V> {


    private final Map<K,V> kvMap;

    private final boolean isThrowExWhenNotValue;


    public FixedMapValueContext() {
        this(true,false);
    }


    public FixedMapValueContext(boolean isStrongReference ,boolean isThrowExWhenNotValue) {
        this(isStrongReference?new ConcurrentHashMap<>() :new ConcurrentReferenceHashMap<>(10, ConcurrentReferenceHashMap.ReferenceType.SOFT),isThrowExWhenNotValue);
    }

    public FixedMapValueContext(Map<K,V> wrapperMap,boolean isThrowExWhenNotValue) {
        this.kvMap = Objects.requireNonNull(wrapperMap,"wrapperMap is null");
        this.isThrowExWhenNotValue = isThrowExWhenNotValue;
    }

    
    @Override
    public boolean hasValue(K key) {
        return kvMap.containsKey(key ) ;
    }

    @Override
    public MapValueContext<K, V> set(K key, V value) {

        kvMap.put(key, value);

        return this;
    }

    @Override
    public MapValueContext<K, V> clear(K key) {

        kvMap.remove(key );

        return this;
    }

    @Override
    public V get(K key) {

        Assert.isTrue( !isThrowExWhenNotValue || hasValue(key) , " value not found with key: " + key);

        return kvMap.get(key);
    }

}
