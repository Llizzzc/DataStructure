import java.util.Random;
/**
 * 快速排序算法, 原地排序.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.util.Random
 */
public class QuickSort {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private QuickSort() {}

    /**
     * 为三路快速排序设计，返回两个索引.
     */
    private static class Pair {
        public int indexI;
        public int indexJ;

        /**
         * 包含i, j的构造方法.
         *
         * @param i 小于标定点的最小索引
         * @param j 大于标定点的最小索引
         */
        public Pair(int i, int j) {
            this.indexI = i;
            this.indexJ = j;
        }
    }

    /**
     * 第一版, 在数组完全有序时会退化成n方的算法，且递归深度为n.
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
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 使用插入排序优化.
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
        if (r - l <= 15) {
            InsertSort.sort4(arr, l ,r);
            return;
        }
        int p = partition(arr, l, r);
        sort2(arr, l, p - 1);
        sort2(arr, p + 1, r);
    }

    /**
     * 针对数组有序情况下性能退化的改进, 添加随机化.
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
        if (l >= r) {
            return;
        }
        int p = partition2(arr, l, r);
        sort3(arr, l, p - 1);
        sort3(arr, p + 1, r);
    }

    /**
     * 只使用一个Random对象.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort4(E[] arr) {
        Random t = new Random();
        sort4(arr, 0, arr.length - 1, t);
    }

    /**
     * 递归过程, 将[l ,r]区间排好序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param rdm Random实例
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void sort4(E[] arr, int l, int r, Random rdm) {
        if (l >= r) {
            return;
        }
        int p = partition3(arr, l, r, rdm.nextInt(r - l + 1));
        sort4(arr, l, p - 1, rdm);
        sort4(arr, p + 1, r, rdm);
    }

    /**
     * 双路快速排序, 针对元素相同的数组进行性能优化.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort5(E[] arr) {
        Random t = new Random();
        sort5(arr, 0, arr.length - 1, t);
    }

    /**
     * 递归过程, 将[l ,r]区间排好序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param rdm Random实例
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void sort5(E[] arr, int l, int r, Random rdm) {
        if (l >= r) {
            return;
        }
        int p = partition4(arr, l, r, rdm.nextInt(r - l + 1));
        sort5(arr, l, p - 1, rdm);
        sort5(arr, p + 1, r, rdm);
    }

    /**
     * 三路快速排序, 针对等于标定点的元素过多时进行性能优化.
     *
     * @param arr 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort6(E[] arr) {
        Random t = new Random();
        sort6(arr, 0, arr.length - 1, t);
    }

    /**
     * 递归过程, 将[l ,r]区间排好序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param rdm Random实例
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void sort6(E[] arr, int l, int r, Random rdm) {
        if (l >= r) {
            return;
        }
        Pair p = partition5(arr, l, r, rdm.nextInt(r - l + 1));
        sort6(arr, l, p.indexI, rdm);
        sort6(arr, p.indexJ, r, rdm);
    }

    /**
     * 将区间按照选择的标定点进行整理.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @return 标定点所在的索引
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        // arr[l + 1, j] < v, arr[j + 1, i - 1] >= v
        int j = l;
        // 遍历区间, 将第一个元素视为标定点
        for (int i = j + 1; i <= r; i ++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j ++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 针对数组有序情况下性能退化的改进, 添加随机化.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @return 标定点所在的索引
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r) {
        // 生成[l, r]之间的索引
        int t = l + (new Random()).nextInt(r - l + 1);
        swap(arr, l, t);
        // arr[l + 1, j] < v, arr[j + 1, i - 1] >= v
        int j = l;
        for (int i = j + 1; i <= r; i ++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j ++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 只使用一个Random对象.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param rdm 随机索引
     * @return 标定点所在的索引
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> int partition3(E[] arr, int l, int r, int rdm) {
        int t = l + rdm;
        swap(arr, l, t);
        // arr[l + 1, j] < v, arr[j + 1, i - 1] >= v
        int j = l;
        for (int i = j + 1; i <= r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 双路快速排序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param rdm 随机索引
     * @return 标定点所在的索引
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> int partition4(E[] arr, int l, int r, int rdm) {
        int t = l + rdm;
        swap(arr, l, t);
        // arr[l + 1, i - 1] <= v, arr[j + 1, r] >=v
        int i = l + 1, j = r;
        while (true) {
            while (i <= j && arr[i].compareTo(arr[l]) < 0) {
                i ++;
            }
            while (j >= i && arr[j].compareTo(arr[l]) > 0) {
                j --;
            }
            if (i >= j) break;
            swap(arr, i, j);
            i ++;
            j --;
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 三路快速排序.
     *
     * @param arr 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @param rdm 随机索引
     * @return 小于标定点的最大索引和大于标定点的最小索引
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> Pair partition5(E[] arr, int l, int r, int rdm) {
        int t = l + rdm;
        swap(arr, l, t);
        // arr[l + 1, lt] < v, arr[lt + 1, i - 1] == v, arr[gt, r] > v
        int lt = l, i = l + 1, gt = r + 1;
        while (i < gt) {
            if (arr[i].compareTo(arr[l]) < 0) {
                lt ++;
                swap(arr, i, lt);
                i ++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                gt --;
                swap(arr, i, gt);
            } else {
                i ++;
            }
        }
        swap(arr, l, lt);
        // arr[l, lt - 1] < v, arr[lt, gt - 1] == v, arr[gt, r] > v
        return new QuickSort.Pair(lt - 1, gt);
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