import java.util.Random;

public class ArrayGenerator {

    private ArrayGenerator() {}

    /**
     * 生成顺序数组.
     *
     * @param n 数组长度
     * @return 返回长度为n的顺序数组
     */
    public static Integer[] generatorOrderArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * 生成随机数组.
     *
     * @param n 数组长度
     * @param bound 限定元素范围[0, bound)
     * @return 返回长度为n的随机数组
     */
    public static Integer[] generatorRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i ++) {
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }

    /**
     * 生成随机等长字符串数组.
     *
     * @param n 数组长度
     * @param M 字符串长度
     * @return 返回长度为n的随机等长字符串数组
     */
    public static String[] generatorRandomEqualLengthStringArray(int n, int M) {
        String[] arr = new String[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i ++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < M; j ++) {
                sb.append((char) (rnd.nextInt(94) + 33));
            }
            arr[i] = sb.toString();
        }
        return arr;
    }

    /**
     * 生成随机非等长字符串数组.
     *
     * @param n 数组长度
     * @param bound 限定元素范围[0, bound)
     * @return 返回长度为n的随机非等长数组
     */
    public static String[] generatorRandomStringArray(int n, int bound) {
        String[] arr = new String[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i ++) {
            int M = rnd.nextInt(bound);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < M; j ++) {
                sb.append((char) (rnd.nextInt(94) + 33));
            }
            arr[i] = sb.toString();
        }
        return arr;
    }
}