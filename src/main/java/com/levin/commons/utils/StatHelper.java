package com.levin.commons.utils;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 单位时间内速率的统计辅助类
 */
public class StatHelper {

    final AtomicLong count = new AtomicLong(0);

    private long lastSampleTime = System.currentTimeMillis();

    //最后采样总数
    private long lastSampleCount = 0;

    //最后采样速率
    private long lastSampleRatio = -1;


    private long defaultIntervalOfSeconds = 60;

    public StatHelper(long defaultIntervalOfSeconds) {
        this.defaultIntervalOfSeconds = defaultIntervalOfSeconds;
    }

    public StatHelper() {
    }

    public void onAlarm(Consumer<Long> consumer) {
        onAlarm(defaultIntervalOfSeconds, consumer);
    }

    /**
     * 指定的秒内，触发警告
     *
     * @param intervalOfSeconds
     * @param consumer
     */
    public void onAlarm(long intervalOfSeconds, Consumer<Long> consumer) {

        long ratio = addAndGetRatio(intervalOfSeconds, 1);

        if (ratio > -1 && consumer != null) {
            consumer.accept(ratio);
        }

    }

    /**
     * 指定的秒内满足2个阀值时，触发警告
     *
     * @param intervalOfSeconds 秒
     * @param ratioThreshold    当速率超过阀值
     * @param scaleThreshold    当速率的环比增长率的绝对值超过阀值，必须是大于0的数
     * @param consumer          第一个参数是变化率，第二个参数是当前速率
     */
    public void onAlarm(long intervalOfSeconds, long ratioThreshold, double scaleThreshold, BiConsumer<Double, Long> consumer) {

        if (consumer == null) {
            return;
        }

        if (ratioThreshold <= 0) {
            ratioThreshold = 0;
        }

        long lastRatio = this.addAndGetRatio(intervalOfSeconds, 1);


        if (lastRatio > -1 && lastRatio >= ratioThreshold) {

            //如果是首次
            if (lastSampleRatio < 0) {
                lastSampleRatio = lastRatio;
                consumer.accept(0d, lastRatio);
            } else {

                long changeRatio = lastRatio - lastSampleRatio;

                //有变化
                if (changeRatio != 0) {

                    double baseRatio = lastSampleRatio > 0 ? lastSampleRatio : 1;

                    //变化率
                    double rate = changeRatio / baseRatio;

                    //变化率超过阀值
                    if (Math.abs(rate) > scaleThreshold) {
                        consumer.accept(rate, lastRatio);
                    }
                }

                lastSampleRatio = lastRatio;
            }

        }
    }


    /**
     * 核心方法
     *
     * @param intervalOfSeconds
     * @param incrValue         增加量
     * @return
     */
    public long addAndGetRatio(long intervalOfSeconds, long incrValue) {

        count.addAndGet(incrValue);

        long takeSeconds = (System.currentTimeMillis() - lastSampleTime) / 1000;

        if (takeSeconds > intervalOfSeconds) {

            long ratio = (count.longValue() - lastSampleCount) / takeSeconds;

            lastSampleCount = count.longValue();

            lastSampleTime = System.currentTimeMillis();

            return ratio;
        }

        return -1;
    }


    public long getLastSampleTime() {
        return lastSampleTime;
    }

    public long getDefaultIntervalOfSeconds() {
        return defaultIntervalOfSeconds;
    }

}
