import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    // 可以处理数字数组
    private BucketSort() {}

    /**
     * 基于MSD算法, 递归.
     *
     * @param arr 待排序数组
     * @param B 桶个数
     * @throws IllegalArgumentException 桶个数不能小于等于1
     */
    public static void sort(Integer[] arr, int B) {
        if (B <= 1) {
            throw new IllegalArgumentException("B must > 1");
        }
        Integer[] temp = new Integer[arr.length];
        sort(arr, 0, arr.length - 1, B, temp);
    }

    /**
     * 递归过程, 对arr[left, right]进行排序.
     *
     * @param arr 待排序数组
     * @param left 左边界
     * @param right 右边界
     * @param B 桶个数
     * @param temp 临时数组
     */
    private static void sort(Integer[] arr, int left, int right, int B, Integer[] temp) {
        if (left >= right) return;
        int[] cnt = new int[B];
        int[] index = new int[B + 1];
        Integer maxV = Integer.MIN_VALUE, minV = Integer.MAX_VALUE;
        // O(n)
        for (int i = left; i <= right; i ++) {
            maxV = Math.max(arr[i], maxV);
            minV = Math.min(arr[i], minV);
        }
        if (maxV.equals(minV)) {
            return;
        }
        // 每个桶的元素数量
        int d = (maxV - minV + 1) / B + (((maxV - minV + 1) % B) > 0? 1: 0);

        // 将元素放入桶中
        for (int i = left; i <= right; i ++) {
            cnt[(arr[i] -  minV) / d] ++;
        }

        // O(R)
        for (int i = 0; i < B; i ++) {
            index[i + 1] = index[i] + cnt[i];
        }
        // O(n)
        for (int i = left; i <= right; i ++) {
            int p = (arr[i] - minV) / d;
            temp[left + index[p]] = arr[i];
            index[p] ++;
        }
        // O(n)
        for (int i = left; i <= right; i ++) {
            arr[i] = temp[i];
        }
        // 需要处理0号桶
        sort(arr, left, left + index[0] - 1, B, temp);
        for (int i = 0; i < B - 1; i ++) {
            sort(arr, left + index[i], left + index[i + 1] - 1, B, temp);
        }
    }

    /**
     * 非递归实现.
     *
     * @param arr 待排序数组
     * @param c 每个桶的元素个数
     * @throws IllegalArgumentException 每个桶中的元素个数必须大于0
     */
    public static void sort2(Integer[] arr, int c) {
        if (c < 0) {
            throw new IllegalArgumentException("c must > 0");
        }
        Integer maxV = Integer.MIN_VALUE, minV = Integer.MAX_VALUE;
        for (Integer e : arr) {
            maxV = Math.max(e, maxV);
            minV = Math.min(e, minV);
        }
        // 桶的个数
        int B = (maxV - minV + 1) / c + ((maxV - minV + 1) % c > 0? 1: 0);
        List<Integer>[] buckets = new LinkedList[B];
        for (int i = 0; i < B; i ++) {
            buckets[i] = new LinkedList<>();
        }
        for (Integer e : arr) {
            buckets[(e - minV) / c].add(e);
        }
        // 直接对每个桶进行排序
        for (int i = 0; i < B; i ++) {
            Collections.sort(buckets[i]);
        }
        int index = 0;
        for (int i = 0; i < B; i ++) {
            for (Integer e : buckets[i]) {
                arr[index++] = e;
            }
        }
    }
}