package com.levin.commons.utils;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;


@Slf4j
public class CpuUtils {

    private static long lastCollectTime = -1;
    private static int lastCollectValue = -1;

    private static final CentralProcessor processor = new SystemInfo().getHardware().getProcessor();

    private static StatHelper statHelper = new StatHelper();

    static {

        new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {

                collectCpuLoadRate(300);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    /**
     * 采集CPU利用率
     *
     * @return
     */
    public static int collectCpuLoadRate(long collectInterval) {

        //最小不能低于100毫秒
        if (collectInterval < 100) {
            collectInterval = 100L;
        }

        //超过1秒
        if (lastCollectTime < 1 || (System.currentTimeMillis() - lastCollectTime) > collectInterval) {

            lastCollectTime = System.currentTimeMillis();

            // 获取系统范围的cpu负载技计数
//            long[] prevTicks = processor.getSystemCpuLoadTicks();

            try {
                Thread.sleep(collectInterval);
            } catch (InterruptedException e) {
                return lastCollectValue;
            }

            //  long[] ticks = processor.getSystemCpuLoadTicks();

            lastCollectValue = (int) (processor.getSystemCpuLoadBetweenTicks() * 100);
        }

        return lastCollectValue;
    }


    public static int getCpuLoad() {
        return lastCollectValue;
    }

    public static int sleepIfCpuLoadOverThreshold(int cpuThreshold, long sleepMs) {

        statHelper.onAlarm(15, 50, 0
                , (growthRatio, lastRatio) -> log.warn("*** 当前CPU负载：{}, 阈值：{}，处理速率：{}，环比变化率：{}", getCpuLoad(), cpuThreshold, lastRatio, growthRatio));

        if (getCpuLoad() >= cpuThreshold) {
            try {
                Thread.sleep(sleepMs < 1 ? 1 : sleepMs);
            } catch (InterruptedException e) {
                return getCpuLoad();
            }
        }

        return getCpuLoad();
    }

}
