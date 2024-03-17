import java.util.Arrays;

/**
 * 归并排序算法, 非原地排序, 稳定.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.util.Arrays
 */
public class MergeSort {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private MergeSort() {}

    /**
     * 排序过程, 第一版.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 递归过程, 将[l ,r]区间排好序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        // 基本问题
        if (l >= r) {
            return;
        }
        // 使用减法, 避免产生整型溢出
        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    /**
     * 数据有序下的优化.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    /**
     * 递归过程, 将[l ,r]区间排好序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        sort2(arr, l, mid);
        sort2(arr, mid + 1, r);
        // 两个区间之间无序才执行merge
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    /**
     * 使用插入排序优化.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        sort3(arr, 0, arr.length - 1);
    }

    /**
     * 递归过程, 将[l ,r]区间排好序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r) {
        if (r - l <= 15) {
            InsertSort.sort4(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        sort3(arr, l, mid);
        sort3(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    /**
     * 优化临时数组.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort4(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort4(arr, 0, arr.length - 1, temp);
    }

    /**
     * 递归过程, 将[l ,r]区间排好序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param temp 临时数组
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void sort4(E[] arr, int l, int r, E[] temp) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        sort4(arr, l, mid, temp);
        sort4(arr, mid + 1, r, temp);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge2(arr, l, mid, r, temp);
        }
    }

    /**
     * 自底向上的归并.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sortBU(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        // 每次对大小为sz的两个区间进行合并, 每次翻倍
        for (int sz = 1; sz < n; sz += sz) {
            // 每次合并[i, i + sz - 1]和[i + sz, i + sz + sz - 1]
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge2(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    /**
     * 使用插入排序优化自底向上的归并.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sortBU2(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        // 对arr[i, i + 15]的所有区间使用插入排序先优化一下
        for (int i = 0; i < n; i += 16) {
            InsertSort.sort4(arr, i, Math.min(i + 15, n - 1));
        }
        // 每次对大小为sz的两个区间进行合并, 每次翻倍
        for (int sz = 16; sz < n; sz += sz) {
            // 每次合并[i, i + sz - 1]和[i + sz, i + sz + sz - 1]
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge2(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    /**
     * 归并过程, 合并两个有序区间arr[l, mid]和arr[mid + 1, r].
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param mid 第一个区间的右边界
     * @param r 右边界
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        // 临时数组
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        // 两个区间的第一个索引
        int i = l, j = mid + 1;
        // 每轮循环为arr[k]赋值
        for (int k = l; k <= r; k ++) {
            // 第一个区间没有元素了
            if (i > mid) {
                // arr与temp存在一个下标偏移
                arr[k] = temp[j - l];
                j ++;
            } else if (j > r) { // 第二个区间没有元素了
                arr[k] = temp[i - l];
                i ++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i ++;
            } else {
                arr[k] = temp[j - l];
                j ++;
            }
        }
    }

    /**
     * 归并过程, 合并两个有序区间arr[l, mid]和arr[mid + 1, r], 只使用公共临时数组.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param mid 第一个区间的右边界
     * @param r 右边界
     * @param temp 临时数组
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void merge2(E[] arr, int l, int mid, int r, E[] temp) {
        // 为temp的[l, r]区间赋值
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i = l, j = mid + 1;
        // 每轮循环为arr[k]赋值
        for (int k = l; k <= r; k ++) {
            if (i > mid) {
                arr[k] = temp[j];
                j ++;
            } else if (j > r) {
                arr[k] = temp[i];
                i ++;
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i ++;
            } else {
                arr[k] = temp[j];
                j ++;
            }
        }
    }
}