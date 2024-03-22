public class ArrayGenerator {

    private ArrayGenerator() {}

    /**
     * 生成顺序数组.
     *
     * @param n 数组长度
     * @return 返回长度为n的数组
     */
    public static Integer[] generatorOrderArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = i;
        }
        return arr;
    }
}