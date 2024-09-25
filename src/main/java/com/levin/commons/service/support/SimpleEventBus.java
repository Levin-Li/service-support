package com.levin.commons.service.support;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.util.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
@Accessors(chain = true)
public class SimpleEventBus implements EventBus {

    @Setter
    @Getter
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    static class Event<T> implements Serializable {

        String topic;
        Long expireTime;
        boolean isBroadcast;
        T data;
    }

    @Setter
    @Getter
    @Accessors(chain = true)
    @AllArgsConstructor
    static class EventConsumer<T> implements Serializable {

        String topicPattern;

        Type[] expectEventTypes;

        Consumer<T> consumer;
    }

    private final BlockingDeque<Event> eventQueue;

    private final BlockingDeque<EventConsumer> consumerQueue;

    private final AtomicBoolean stop = new AtomicBoolean(false);

    private final PathMatcher matcher = new AntPathMatcher();

    private final AtomicBoolean working = new AtomicBoolean(false);

    @Setter
    @Getter
    Executor executor;

    public SimpleEventBus() {
        this(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    //构造方法
    public SimpleEventBus(int maxEvents, int maxConsumers) {
        eventQueue = new LinkedBlockingDeque<>(maxEvents);
        consumerQueue = new LinkedBlockingDeque<>(maxConsumers);
    }

    public void wake() {

        //如果已经在工作，则退出
        if (working.get()) {
            return;
        }

        executor.execute(() -> {
            try {
                working.set(true);

                log.info("事件总线工作中...");

                while (!Thread.currentThread().isInterrupted()) {

                    Event event = null;

                    try {
                        event = eventQueue.poll(30, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        break;
                    }

                    if (event == null) {
                        //没有事件，退出线程
                        return;
                    }

                    //异步处理事件
                    processEvent(event);

                }

                //如果线程被中断，停止工作，则清除所有的数据

                stop.set(true);

                //清除所有的数据
                eventQueue.clear();

                log.info("事件总线停止");

            } finally {
                working.set(false);
            }

        });
    }

    protected void processEvent(Event event) {

        if (event == null) {
            return;
        }

        if (event.getExpireTime() != null && event.getExpireTime() < System.currentTimeMillis()) {
            //事件已经过期
            return;
        }

        //查找消费者
        for (EventConsumer consumer : consumerQueue) {

            //如果有topic表达式，但是topic不匹配，则跳过
            if (StringUtils.hasText(consumer.topicPattern)
                    && !matcher.match(consumer.topicPattern, event.getTopic())) {
                continue;
            }

            //如果指定了事件类型，但事件类型不匹配，则跳过
            if (consumer.expectEventTypes != null
                    && Stream.of(consumer.expectEventTypes).noneMatch(et -> TypeUtils.isAssignableBound(et, AopProxyUtils.ultimateTargetClass(event.getData())))) {
                //指定了事件类型，但事件类型不匹配
                continue;
            }

            //异步消费事件
            executor.execute(() -> consumer.consumer.accept(event.getData()));

            //如果不是广播事件，则只给第一个匹配的消费者
            if (!event.isBroadcast) {
                break;
            }
        }
    }

    /**
     * 发送事件
     *
     * @param topic       /test/new/
     * @param timeoutMs
     * @param isBroadcast 非广播事件，只会给第一个匹配的消费者
     * @param event
     */

    @SneakyThrows
    @Override
    public void sendEvent(String topic, long timeoutMs, boolean isBroadcast, Object event) {

        checkStatus();

        Assert.hasText(topic, "事件主题为空");

        Assert.notNull(event, "事件对象为空");

        if (timeoutMs <= 0) {
            timeoutMs = 45 * 1000;
        }

        boolean ok = eventQueue.offer(new Event(topic, System.currentTimeMillis() + timeoutMs, isBroadcast, event), 3, TimeUnit.SECONDS);

        Assert.isTrue(ok, "发送事件失败");

        //顺带唤醒工作线程
        wake();
    }

    private void checkStatus() {
        Assert.isTrue(!stop.get(), "总线已经停止工作");
        Assert.notNull(executor, "事件总线未初始化完成，执行器未配置");
    }

    /**
     * 增加事件处理器
     *
     * @param topicPattern        ant path 匹配
     * @param eventConsumer
     * @param expectEventTypes
     * @return 处理器
     */
    @SneakyThrows
    @Override
    public <T> void addEventConsumer(String topicPattern, Consumer<T> eventConsumer, Type... expectEventTypes) {

        checkStatus();

        //Assert.hasText(topicPattern, "事件主题匹配表达式为空");
        Assert.notNull(eventConsumer, "事件处理器为空");

        boolean ok = consumerQueue.offer(new EventConsumer(topicPattern, expectEventTypes, eventConsumer), 3, TimeUnit.SECONDS);

        Assert.isTrue(ok, "增加事件处理器失败");
    }

    /**
     * 移除消费者
     *
     * @param eventConsumer
     */
    @Override
    public boolean removeEventConsumer(Consumer eventConsumer) {
        return consumerQueue.removeIf(ec -> eventConsumer == ec.consumer);
    }

}
