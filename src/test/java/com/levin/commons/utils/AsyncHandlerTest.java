package com.levin.commons.utils;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

class AsyncHandlerTest {

    @Test
    void addTask() throws InterruptedException {

        ExecutorService defaultExecutor = Executors.newFixedThreadPool(5);

        ExecutorService newTaskExecutor = Executors.newFixedThreadPool(100);

        AsyncHandler<Runnable> asyncHandler = new AsyncHandler().setDefaultExecutorService(defaultExecutor).setSkipNotProcessTask(true);


        Random random = new Random();

        AtomicLong atomicLong = new AtomicLong();

        long n = 0;
        while (n++ < 1 * 100 * 10000) {

            int abs = Math.abs(random.nextInt(1000));
            if (abs == 0 || n % abs == 0) {
                Thread.sleep(1);
            }

            final long no = n;

            //增加任务
            asyncHandler.addTask(() -> {

                int millis = Math.abs(random.nextInt(100) + 1);

                try {
                    if (millis % 100 == 0)
                        Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(atomicLong.incrementAndGet() + ". take " + millis / 1000 + "s ," + Thread.currentThread().getName() + " Do task " + no + " ok");

            });
        }

        Thread.sleep(10 * 1000);

        System.out.println(asyncHandler);


    }
}