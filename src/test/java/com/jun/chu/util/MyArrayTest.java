package com.jun.chu.util;

import com.jun.chu.java.util.MyArrays;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author chujun
 * @date 2020-06-14
 */
public class MyArrayTest {
    @Test
    public void testJdkCorrectness() {
        int length = 100000;
        int[] data = generateData(length);
        Arrays.sort(data);
        validate(data);
    }

    @Test
    public void testCorrectness() {
        int length = 100000;
        int[] data = generateData(length);
        MyArrays.sort(data);
        validate(data);
    }

    private int[] generateData(int length) {
        int[] toBeSorted = new int[length];
        IntStream.range(0, length).forEach(item ->
            toBeSorted[item] = new Random().nextInt());
        return toBeSorted;
    }

    private void validate(int[] sorted) {
        Assert.assertTrue(isSorted(sorted));
    }

    private boolean isSorted(int[] sorted) {
        for (int i = 0; i < sorted.length-1; i++) {
            int j = i + 1;
            if (sorted[j] < sorted[i]) {
                return false;
            }
        }
        return true;
    }
}
