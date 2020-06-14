package com.jun.chu.java.util;

import java.util.Comparator;

/**
 * @author chujun
 * @date 2020-06-10
 * @see java.util.Arrays
 */
public class MyArrays {
    /**
     * The minimum array length below which a parallel sorting
     * algorithm will not further partition the sorting task. Using
     * smaller sizes typically results in memory contention across
     * tasks that makes parallel speedups unlikely.
     */
    private static final int MIN_ARRAY_SORT_GRAN = 1 << 13;

    // Suppresses default constructor, ensuring non-instantiability.
    private MyArrays() {
    }

    static final class NaturalOrder implements Comparator<Object> {
        @Override
        @SuppressWarnings("unchecked")
        public int compare(Object first, Object second) {
            return ((Comparable<Object>) first).compareTo(second);
        }

        static final MyArrays.NaturalOrder INSTANCE = new MyArrays.NaturalOrder();
    }

    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    /**
     * 升序排序
     * 排序算法采用Dual-Pivot快速排序(2009年俄罗斯人提出来的，90年代快速排序就基本稳定了)
     * 创造性统计时间复杂度O(n log(n))，通常比传统快速排序算法(一个点)快
     * Sorts the specified array into ascending numerical order.
     *
     * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
     * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
     * offers O(n log(n)) performance on many data sets that cause other
     * quicksorts to degrade to quadratic performance, and is typically
     * faster than traditional (one-pivot) Quicksort implementations.
     *
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        //tbs
        // MyDualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
    }


    public static void sort(Object[] a, Comparator c) {
        //tbs
    }
}
