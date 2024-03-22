public class CountingSort {

    private CountingSort() {}

    /**
     * 排序过程, O(n + R)
     *
     * @param nums 待排序数组
     * @param n 元素取值范围
     */
    public static void countingSort(int[] nums, int n) {
        // 处理元素的取值范围是[0, R)
        int R = n;
        int[] cnt = new int[R];
        for (int num : nums) {
            cnt[num] ++;
        }

        int[] index = new int[R + 1];
        // [index[i], index[i + 1])的值为i
        for (int i = 0; i < R; i ++) {
            index[i + 1] = index[i] + cnt[i];
        }

        int[] temp = new int[nums.length];
        for (int num : nums) {
            temp[index[num]] = num;
            index[num] ++;
        }
        for (int i = 0; i < nums.length; i ++) {
            nums[i] = temp[i];
        }
    }
}