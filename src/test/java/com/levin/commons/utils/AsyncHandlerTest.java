package com.levin.commons.utils;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

class AsyncHandlerTest {


    @Test
    void addTask() throws InterruptedException {

        ScheduledExecutorService defaultExecutor = Executors.newScheduledThreadPool(5);

        ExecutorService newTaskExecutor = Executors.newFixedThreadPool(100);

        AsyncHandler<Runnable> asyncHandler = new AsyncHandler()
                .setName("模拟从redis更新到数据库")
                .setDefaultScheduler(defaultExecutor)
                .setTaskDelay(500)
                .setSkipNotProcessTask(true);


        Random random = new Random();

        AtomicLong atomicLong = new AtomicLong();

        final AtomicLong mockRedisBalance = new AtomicLong();

        AtomicLong mockDBBalance = new AtomicLong();

        final long maxRequests = 1 * 100 * 10000;

        long n = 0;
        while (n++ < maxRequests) {

            mockRedisBalance.addAndGet(2);

            int abs = Math.abs(random.nextInt(1000));
            if (abs == 0 || n % abs == 0) {
                Thread.sleep(1);
            }

            final long no = n;

            //增加任务
            asyncHandler.addTask(() -> {

                int millis = Math.abs(random.nextInt(100) + 1);

                try {
                    //   if (millis % 100 == 0)
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mockDBBalance.set(mockRedisBalance.longValue());

                System.out.println(atomicLong.incrementAndGet() + ". take " + millis / 1000 + "s ," + Thread.currentThread().getName() + " Do task " + no + " ok");

            });
        }


        Thread.sleep(15 * 1000);

        System.out.println(mockRedisBalance + " ---> " + mockDBBalance);

        Assert.isTrue(mockRedisBalance.get() == maxRequests * 2, "Redis更新正确");

        Assert.isTrue(mockRedisBalance.get() == mockDBBalance.get(), "数据库更新正确");

    }
}