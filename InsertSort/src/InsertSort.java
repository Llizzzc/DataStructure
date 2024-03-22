public class InsertSort {

    // 稳定排序
    private InsertSort() {}

    /**
     * 从前向后进行排序.
     *
     * @param arr 待排序数组
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

    /**
     * 交换数组中两个元素的位置.
     *
     * @param arr 数组
     * @param i 位置i
     * @param j 位置j
     */
    public static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}