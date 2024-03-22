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
     * @throws IllegalArgumentException 字符串长度为0
     * @throws RuntimeException 数组未排好序
     */
    public static <E extends Comparable<E>> void sortTest(E[] arr, String sortName) {
        long start = System.nanoTime();
        if (sortName.equals("MergeSort4")) {
            MergeSort.sort4(arr);
        } else if (sortName.equals("QuickSort5")) {
            QuickSort.sort5(arr);
        } else if (sortName.equals("QuickSort6")) {
            QuickSort.sort6(arr);
        } else if (sortName.equals("LSDSort")) {
            String[] newArr = (String[])arr;
            if (newArr[0].length() == 0) {
                throw new IllegalArgumentException("Arr can not be empty.");
            }
            LSDSort.sort(newArr, newArr[0].length());
        } else if (sortName.equals("MSDSort")) {
            String[] newArr = (String[])arr;
            MSDSort.sort(newArr);
        } else if (sortName.equals("BucketSort")) {
            Integer[] newArr = (Integer[]) arr;
            BucketSort.sort(newArr, 200);
        } else if (sortName.equals("BucketSort2")) {
            Integer[] newArr = (Integer[]) arr;
            BucketSort.sort2(newArr, 100);
        }
        long end = System.nanoTime();
        double time = (end - start) / 1.0e9;
        if (!SortHelper.isSorted(arr)) {
            throw new RuntimeException(sortName + " failed.");
        }
        System.out.printf("%s: n = %d, time = %f s\n", sortName, arr.length, time);
    }
}