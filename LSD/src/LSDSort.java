import java.util.Arrays;

/**
 * LSD基数排序算法.
 * <p>只能对等长字符串排序, 从最后一个字符开始进行计数排序.</p>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.util.Arrays
 */
public class LSDSort {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private LSDSort() {}

    /**
     * 排序过程.
     *
     * @param arr 待排序数组
     * @param M 字符串长度
     * @throws IllegalArgumentException 字符串长度不等
     */
    public static void sort(String[] arr, int M) {
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i].length() != M) {
                throw new IllegalArgumentException("String length must be equal.");
            }
        }
        int R = 256;    // 可打印字符范围
        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        String[] temp = new String[arr.length];
        // 从最后一个字符开始
        for (int r = M - 1; r >= 0 ;r --) {
            // O(n)
            Arrays.fill(cnt, 0);
            for (String s : arr) {
                cnt[s.charAt(r)] ++;
            }
            // O(R)
            for (int i = 0; i < R; i ++) {
                index[i + 1] = index[i] + cnt[i];
            }
            // O(n)
            for (String s : arr) {
                temp[index[s.charAt(r)]] = s;
                index[s.charAt(r)] ++;
            }
            // O(n)
            for (int i = 0; i < arr.length; i ++) {
                arr[i] = temp[i];
            }
        }
    }
}