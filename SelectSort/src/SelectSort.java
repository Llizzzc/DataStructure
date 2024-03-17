/**
 * 选择排序算法, 原地排序.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class SelectSort {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private SelectSort() {
    }

    /**
     * 从前向后进行排序.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        // 每次循环前[0, i)已排序, [i, n)未排序
        for (int i = 0; i < arr.length; i ++) {
            int minIndex = i;
            // 选择[i...n)中的最小值索引
            for (int j = i + 1; j < arr.length; j ++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 从后向前排序.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        // 每次循环前(i, n)已排序, [0, i]未排序
        for (int i = arr.length - 1; i >= 0; i --) {
            int maxIndex = i;
            // 选择[0, i]中的最大值索引
            for (int j = i - 1; j >= 0; j --) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }
    }

    /**
     * 交换数组中两个元素的位置.
     *
     * @param arr 目标数组
     * @param i 位置i
     * @param j 位置j
     * @param <E> 支持泛型
     */
    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}


