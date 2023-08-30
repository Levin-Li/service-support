package com.levin.commons.utils;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 单位时间内速率的统计辅助类
 * <p>
 * 主要用于打印统计
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
     * <p>
     * 如第1次采样50 第2次采样150，那么增长量是100，增长率是200%，也就是2
     *
     * @param intervalOfSeconds    秒
     * @param ratioThreshold       速率
     * @param growthRatioThreshold 环比增长率，必须是大于0的数，增长率可以是正负值
     * @param consumer             第一个参数是增长率，第二个参数是当前速率
     */
    public void onAlarm(long intervalOfSeconds, long ratioThreshold, double growthRatioThreshold, BiConsumer<Double, Long> consumer) {

        if (consumer == null) {
            return;
        }

        if (ratioThreshold <= 0) {
            ratioThreshold = 0;
        }

        if (growthRatioThreshold < 0) {
            growthRatioThreshold = Math.abs(growthRatioThreshold);
        }

        long lastRatio = this.addAndGetRatio(intervalOfSeconds, 1);


        if (lastRatio > -1 && lastRatio >= ratioThreshold) {

            //如果是首次
            if (lastSampleRatio < 0) {
                lastSampleRatio = lastRatio;
                consumer.accept(0d, lastRatio);
            } else {

                //速率的增长量
                long growthNum = lastRatio - lastSampleRatio;

                //有变化
                if (growthNum != 0) {

                    //防止出现除数为0
                    double baseRatio = lastSampleRatio > 0 ? lastSampleRatio : 1;

                    //增长率
                    double growthRatio = growthNum / baseRatio;

                    //如果增长率的绝对值超过阀值
                    if (Math.abs(growthRatio) > growthRatioThreshold) {
                        consumer.accept(growthRatio, lastRatio);
                    }
                }

                //最后采样值
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
