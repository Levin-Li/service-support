package com.levin.commons.service.support;

import org.springframework.lang.NonNull;

import java.lang.reflect.Type;
import java.util.function.Consumer;

/**
 * 简单事件总线
 */
public interface EventBus {

    /**
     * 发送事件
     *
     * @param topic
     * @param event
     */
    default void sendEvent(String topic, Object event) {
        sendEvent(topic, 45 * 1000, true, event);
    }

    /**
     * 发送事件
     *
     * @param topic       /test/new/
     * @param timeoutMs
     * @param isBroadcast 非广播事件，只会给第一个匹配的消费者
     * @param event nonull
     */
    void sendEvent(String topic, long timeoutMs, boolean isBroadcast, Object event);

    /**
     * 增加事件处理器
     *
     * @param topicExpr        ant path 匹配
     * @param eventConsumer    事件处理器, @NonNull
     * @param expectEventTypes
     * @return 处理器
     */
    void addEventConsumer(String topicExpr,Consumer eventConsumer, Type... expectEventTypes);

    /**
     * 移除消费者
     *
     * @param eventConsumer
     */
    boolean removeEventConsumer(Consumer eventConsumer);

}
