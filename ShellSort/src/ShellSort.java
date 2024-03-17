/**
 * 希尔排序算法, 原地排序.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class ShellSort {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private ShellSort() {}

    /**
     * 希尔排序, 第一版.
     *
     * @param data 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort(E[] data) {
        int h = data.length / 2;
        while (h >= 1) {
            // 对每个子数组进行插入排序
            for (int start = 0; start < h; start ++) {
                // 处理data[start, start + h, start + 2h, ...]
                for (int i = start + h; i < data.length; i += h) {
                    E t = data[i];
                    int j;
                    for (j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j -= h) {
                        data[j] = data[j - h];
                    }
                    data[j] = t;
                }
            }
            h /= 2;
        }
    }

    /**
     * 代码简化.
     *
     * @param data 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort2(E[] data) {
        int h = data.length / 2;
        while (h >= 1) {
            for (int i = h; i < data.length; i ++) {
                E t = data[i];
                int j;
                for (j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = t;
            }
            h /= 2;
        }
    }

    /**
     * 调整步长测试.
     *
     * @param data 待排序数组
     * @param <E> 支持泛型
     */
    public static <E extends Comparable<E>> void sort3(E[] data) {
        int h = 1;
        // 1, 4, 13, 40...
        while (h < data.length) h = h * 3 + 1;
        while (h >= 1) {
            // 对每个子数组进行插入排序
            for (int start = 0; start < h; start ++) {
                // 处理data[start, start + h, start + 2h, ...]
                for (int i = start + h; i < data.length; i += h) {
                    E t = data[i];
                    int j;
                    for (j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j -= h) {
                        data[j] = data[j - h];
                    }
                    data[j] = t;
                }
            }
            h /= 3;
        }
    }
}