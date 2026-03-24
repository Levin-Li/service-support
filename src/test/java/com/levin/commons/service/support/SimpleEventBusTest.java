package com.levin.commons.service.support;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SimpleEventBusTest {

    @Test
    void sendEvent() throws InterruptedException {
        // 业务规则：主题匹配后应触发对应消费者，并且线程池能被正常回收。
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        SimpleEventBus simpleEventBus = new SimpleEventBus();
        simpleEventBus.setExecutor(executorService);

        CountDownLatch latch = new CountDownLatch(2);

        simpleEventBus.addEventConsumer("test", event -> latch.countDown(), String.class);
        simpleEventBus.addEventConsumer("test/**", event -> latch.countDown(), Number.class);

        simpleEventBus.sendEvent("test", "字符串数据");
        simpleEventBus.sendEvent("test/1", 100);

        assertTrue(latch.await(3, TimeUnit.SECONDS), "两个匹配事件都应在超时内被消费");

        executorService.shutdownNow();
        assertTrue(executorService.awaitTermination(3, TimeUnit.SECONDS), "测试结束后线程池应在超时内完成关闭");
    }

}
