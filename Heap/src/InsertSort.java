/**
 * 插入排序算法, 原地排序.
 *
 * @author lzc
 * @version 2.0
 * @version jdk17
 */
public class InsertSort {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private InsertSort() {}

    /**
     * 从前向后进行排序.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        // 每次循环前, [0, i)已排好序, [i, n)未排序
        for (int i = 0; i < arr.length; i ++) {
            // 为arr[i]选择合适的位置
            for (int j = i; j - 1 >= 0; j --) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 小优化, 将交换操作改为元素平移.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            E t = arr[i];
            int j;
            // 寻找插入的位置j
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j --) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
        }
    }

    /**
     * 从后向前进行排序.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        // 每次循环前(i, n)已排序, [0, i]未排序
        for (int i = arr.length - 1; i >= 0; i --) {
            E t = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && t.compareTo(arr[j + 1]) > 0; j ++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = t;
        }
    }

    //

    /**
     * 优化归并排序, 对某个区间使用插入排序.
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort4(E[] arr, int l, int r) {
        for (int i = l; i <= r; i ++) {
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= l && t.compareTo(arr[j - 1]) < 0; j --) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
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
    public static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}