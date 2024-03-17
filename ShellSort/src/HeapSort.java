/**
 * 堆排序算法, 原地排序.
 *
 * @author lzc
 * @version 2.0
 * @version jdk17
 */
public class HeapSort {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private HeapSort() {}

    /**
     * 堆排序, 原地排序.
     *
     * @param data 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort2(E[] data) {

        if (data.length <= 1) {
            return;
        }
        // 从最后一个非叶子节点开始, 直到根节点, 整理成最大堆
        for (int i = ((data.length - 1 - 1) / 2); i >= 0; i --) {
            siftDown(data, i, data.length);
        }
        // 从最后一个位置开始, 每次与第一个元素交换, 再进行siftDown操作
        for (int i = data.length - 1; i >= 0; i --) {
            swap(data, i, 0);
            siftDown(data, 0, i);
        }
    }

    /**
     * 对[0, n)形成的最大堆, k位置的元素执行下沉.
     *
     * @param data 排序数组
     * @param k 索引k
     * @param n 右边界, 左边界为0
     * @param <E> 支持泛型
     */
    private static <E extends Comparable<E>> void siftDown(E[] data, int k, int n) {
        while (2 * k + 1 < n) {
            // 首先获取左右孩子的较大值
            int j = 2 * k + 1;
            if (j + 1 < n && data[j + 1].compareTo(data[j]) > 0) {
                j = 2 * k + 2;
            }
            // 然后与k位置比较
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(data, k, j);
            k = j;
        }
    }

    /**
     * 交换数组中两个元素的位置.
     *
     * @param data 目标数组
     * @param i 位置i
     * @param j 位置j
     * @param <E> 支持泛型
     */
    private static <E> void swap(E[] data, int i, int j) {
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}