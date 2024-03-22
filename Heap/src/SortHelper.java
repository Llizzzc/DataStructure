public class SortHelper {

    private SortHelper() {}

    /**
     * 判断数组是否排好序.
     *
     * @param arr 待判断数组
     * @return 若有序返回true, 否则返回false
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        // 相邻元素是否有序
        for (int i = 1; i < arr.length; i ++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试排序算法耗时.
     *
     * @param arr 待排序数组
     * @param sortName 排序算法名称
     * @throws RuntimeException 数组未排好序
     */
    public static <E extends Comparable<E>> void sortTest(E[] arr, String sortName) {
        long start = System.nanoTime();
        if  (sortName.equals("MergeSort")) {
            MergeSort.sort(arr);
        } else if (sortName.equals("MergeSort2")) {
            MergeSort.sort2(arr);
        } else if (sortName.equals("MergeSort3")) {
            MergeSort.sort3(arr);
        } else if (sortName.equals("MergeSort4")) {
            MergeSort.sort4(arr);
        } else if (sortName.equals("MergeSortBU")) {
            MergeSort.sortBU(arr);
        } else if (sortName.equals("MergeSortBU2")) {
            MergeSort.sortBU2(arr);
        } else if (sortName.equals("QuickSort")) {
            QuickSort.sort(arr);
        } else if (sortName.equals("QuickSort2")) {
            QuickSort.sort2(arr);
        } else if (sortName.equals("QuickSort3")) {
            QuickSort.sort3(arr);
        } else if (sortName.equals("QuickSort4")) {
            QuickSort.sort4(arr);
        } else if (sortName.equals("QuickSort5")) {
            QuickSort.sort5(arr);
        } else if (sortName.equals("QuickSort6")) {
            QuickSort.sort6(arr);
        } else if (sortName.equals("HeapSort")) {
            HeapSort.sort(arr);
        } else if (sortName.equals("HeapSort2")) {
            HeapSort.sort2(arr);
        }
        long end = System.nanoTime();
        double time = (end - start) / 1.0e9;
        if (!SortHelper.isSorted(arr)) {
            throw new RuntimeException(sortName + " failed.");
        }
        System.out.printf("%s: n = %d, time = %f s\n", sortName, arr.length, time);
    }
}