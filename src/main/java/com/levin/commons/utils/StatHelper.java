package com.levin.commons.utils;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class StatHelper {

    final AtomicLong count = new AtomicLong(0);

    private long lastStatTime = System.currentTimeMillis();

    //最后采样总数
    private long lastSampleCount = 0;

    private long defaultInterval = 60;

    public StatHelper(long defaultInterval) {
        this.defaultInterval = defaultInterval;
    }

    public StatHelper() {
    }

    public void doIntervalTask(Consumer<Long> consumer) {
        doIntervalTask(defaultInterval, consumer);
    }

    public void doIntervalTask(long interval, Consumer<Long> consumer) {

        long ratio = addAndGetRatio(interval);

        if (ratio > -1 && consumer != null) {
            consumer.accept(ratio);
        }
    }

    protected long addAndGetRatio() {
        return addAndGetRatio(defaultInterval);
    }

    protected long addAndGetRatio(long interval) {

        count.incrementAndGet();

        long takeSeconds = (System.currentTimeMillis() - lastStatTime) / 1000;

        if (takeSeconds > interval) {

            long ratio = (count.longValue() - lastSampleCount) / takeSeconds;

            lastSampleCount = count.longValue();

            lastStatTime = System.currentTimeMillis();

            return ratio;
        }

        return -1;
    }


    public long getLastStatTime() {
        return lastStatTime;
    }

    public long getDefaultInterval() {
        return defaultInterval;
    }

}
