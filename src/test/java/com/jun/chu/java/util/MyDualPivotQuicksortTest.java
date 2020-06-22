package com.jun.chu.java.util;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author chujun
 * @date 2020-06-22
 */
public class MyDualPivotQuicksortTest {
    @Test
    public void test7() {
        Stopwatch unstarted = Stopwatch.createUnstarted();
        unstarted.start();
        int length = 100000000;
        IntStream.range(47, length).forEach(e -> {
            int a = e / 7;
        });
        unstarted.stop();
        System.out.println(unstarted.elapsed(TimeUnit.MILLISECONDS));

        unstarted.start();
        IntStream.range(47, length).forEach(e -> {
            // Inexpensive approximation of length / 7
            int seventh = (e >> 3) + (e >> 6) + 1;
        });
        unstarted.stop();
        System.out.println(unstarted.elapsed(TimeUnit.MILLISECONDS));
        //说好不昂贵的近似计算length/7,为什么我单元测试的结果不太一样
    }
}
