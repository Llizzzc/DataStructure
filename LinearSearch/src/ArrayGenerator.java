/**
 * 数组生成类.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class ArrayGenerator {

    /**
     * 私有无参数构造方法, 不允许创建类实例.
     */
    private ArrayGenerator() {}

    /**
     * 生成顺序数组.
     *
     * @param n 数组长度
     * @return 长度为n的数组
     */
    public static Integer[] generatorOrderArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = i;
        }
        return arr;
    }
}
