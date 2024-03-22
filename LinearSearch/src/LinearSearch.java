public class LinearSearch {

    private LinearSearch() {}

    /**
     * 在给定数组中查找某个元素的位置.
     *
     * @param arr 待查找数组
     * @param target 目标元素
     * @return 返回目标元素在待查找数组中的索引, 若没找到返回-1
     */
    public static <E> int search(E[] arr, E target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}