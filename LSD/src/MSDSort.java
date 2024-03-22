public class MSDSort {

    // 可以处理不等长字符串数组
    private MSDSort() {}

    /**
     * 排序过程.
     *
     * @param arr 待排序数组
     */
    public static void sort(String[] arr) {
        String[] temp = new String[arr.length];
        sort(arr, 0, arr.length - 1, 0, temp);
    }

    /**
     * 递归过程, 处理第r个位置的字符, arr[left, right].
     *
     * @param arr 待排序数组
     * @param left 左边界
     * @param right 右边界
     * @param r 第r个字符
     * @param temp 临时数组
     */
    private static void sort(String[] arr, int left, int right, int r, String[] temp) {
        if (left >= right) return;
        int R = 256;
        // 添加一种空字符情况
        int[] cnt = new int[R + 1];
        int[] index = new int[R + 2];
        // O(n)
        for (int i = left; i <= right; i ++) {
            cnt[r >= arr[i].length()? 0: (arr[i].charAt(r) + 1)] ++;
        }
        // O(R)
        for (int i = 0; i < R + 1; i ++) {
            index[i + 1] = index[i] + cnt[i];
        }
        // O(n)
        for (int i = left; i <= right; i ++) {
            int p = r >= arr[i].length()? 0: (arr[i].charAt(r) + 1);
            temp[left + index[p]] = arr[i];
            index[p] ++;
        }
        // O(n)
        for (int i = left; i <= right; i ++) {
            arr[i] = temp[i];
        }

        // 根据第r个字符分组完之后, 递归下去
        // 第一段为第r个位置为空的字符串, 所以不需要再递归排序了
        for (int i = 0; i < R; i ++) {
            sort(arr, left + index[i], left + index[i + 1] - 1, r + 1, temp);
        }
    }
}