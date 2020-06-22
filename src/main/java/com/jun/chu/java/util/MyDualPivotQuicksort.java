package com.jun.chu.java.util;

/**
 * 排序说明文档
 * http://www.west.cn/info/html/chengxusheji/Javajishu/20180802/4417089.html
 * <p>
 * 双枢轴快速排序
 * All exposed methods are package-private, designed to be invoked
 * from public methods (in class Arrays) after performing any
 * necessary array bounds checks and expanding parameters into the
 * required forms.
 * 使用到的排序算法:
 * 传统插入排序
 * 成对插入排序
 *
 * @author chujun
 * @date 2020-06-10
 * @see java.util.DualPivotQuicksort
 */
final class MyDualPivotQuicksort {

    private MyDualPivotQuicksort() {
    }

    /*
     * Tuning parameters.
     */

    /**
     * The maximum number of runs in merge sort.
     */
    private static final int MAX_RUN_COUNT = 67;

    /**
     * The maximum length of run in merge sort.
     */
    private static final int MAX_RUN_LENGTH = 33;

    /**
     * If the length of an array to be sorted is less than this
     * constant, Quicksort is used in preference to merge sort.
     */
    private static final int QUICKSORT_THRESHOLD = 286;

    /**
     * If the length of an array to be sorted is less than this
     * constant, insertion sort is used in preference to Quicksort.
     */
    private static final int INSERTION_SORT_THRESHOLD = 47;

    /**
     * If the length of a byte array to be sorted is greater than this
     * constant, counting sort is used in preference to insertion sort.
     */
    private static final int COUNTING_SORT_THRESHOLD_FOR_BYTE = 29;

    /**
     * If the length of a short or char array to be sorted is greater
     * than this constant, counting sort is used in preference to Quicksort.
     */
    private static final int COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR = 3200;

    /**
     * Sorts the specified range of the array using the given
     * workspace array slice if possible for merging
     *
     * @param a        the array to be sorted
     * @param left     the index of the first element, inclusive, to be sorted
     * @param right    the index of the last element, inclusive, to be sorted
     * @param work     a workspace array (slice)
     * @param workBase origin of usable space in work array
     * @param workLen  usable size of work array
     */
    static void sort(int[] a, int left, int right,
                     int[] work, int workBase, int workLen) {
        //tbs
        //低于快速排序阀值,使用快速排序
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(a, left, right, true);
            return;
        }
    }


    /**
     * 使用双驱轴快速排序
     * Sorts the specified range of the array by Dual-Pivot Quicksort.
     *
     * @param a        the array to be sorted
     * @param left     the index of the first element, inclusive, to be sorted
     * @param right    the index of the last element, inclusive, to be sorted
     * @param leftmost 表示指定的范围是否在数组的最左边
     *                 排序为什么和最左部分有关系
     */
    private static void sort(int[] a, int left, int right, boolean leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny arrays
        //插入排序适用于数量较小的数组排序
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Traditional (without sentinel) insertion sort,
                 * optimized for server VM, is used in case of
                 * the leftmost part.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    int ai = a[i + 1];
                    while (ai < a[j]) {
                        a[j + 1] = a[j];
                        if (j-- == left) {
                            break;
                        }
                    }
                    a[j + 1] = ai;
                }
            } else {
                //跳过最长的上升序列
                do {
                    if (left >= right) {
                        return;
                    }
                } while (a[++left] >= a[left - 1]);

                /**
                 * 因为每个元素的相邻部分都扮演了哨兵的角色(这里怎么理解)，因此可以允许我们避免进行数组左边越界检查
                 * （tbs:没有研究出来为何没有越界，这里就跟leftmost有关系了)
                 * 相比较传统插入排序，成对插入排序(在快速排序上下文中比传统插入排序算法快）
                 * ​具体执行过程：上面的do-while循环已经排好的最前面的数据
                 *
                 *（1）将要插入的数据，第一个值赋值a1,第二个值赋值a2,
                 *
                 * (2)然后判断a1与a2的大小，使a1要大于a2
                 *
                 * ​(3)接下来，首先是插入大的数值a1，将a1与k之前的数字一一比较，直到数值小于a1为止，把a1插入到合适的位置，注意：这里的相隔距离为2
                 *
                 * (4)接下来，插入小的数值a2,将a2与此时k之前的数字一一比较，直到数值小于a2为止，将a2插入到合适的位置，注意：这里的相隔距离为1
                 *
                 * (5)最后把最后一个没有遍历到的数据插入到合适位置
                 *
                 */

                /*
                 * Every element from adjoining part plays the role
                 * of sentinel, therefore this allows us to avoid the
                 * left range check on each iteration. Moreover, we use
                 * the more optimized algorithm, so called pair insertion
                 * sort, which is faster (in the context of Quicksort)
                 * than traditional implementation of insertion sort.
                 */
                //left最小为1，经过上面的跳过最大上升序列后
                for (int k = left; ++left <= right; k = ++left) {
                    //取a[k],a[left]大者给a1，小者给a2
                    int a1 = a[k], a2 = a[left];
                    if (a1 < a2) {
                        a1 = a[left];
                        a2 = a[k];
                    }
                    /**
                     * tbs
                     * 正常调用来说这里存在数据越界，
                     * 可能在快速排序算法里面这儿不存在越界问题了
                     */
                    while (a1 < a[--k]) {
                        a[k + 2] = a[k];
                    }
                    a[++k + 1] = a1;

                    while (a2 < a[--k]) {
                        a[k + 1] = a[k];
                    }
                    a[k + 1] = a2;
                }
                //最后一个元素没有参与排序
                int last = a[right];
                while (last < a[--right]) {
                    a[right + 1] = a[right];
                }
                a[right + 1] = last;
            }
            return;
        }
        // Inexpensive approximation of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;
    }
}
