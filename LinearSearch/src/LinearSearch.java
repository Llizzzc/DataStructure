/**
 * 线性查找法.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinearSearch {

    /**
     * 私有无参数构造方法, 不允许创建类实例.
     */
    private LinearSearch() {}

    /**
     * 在给定数组中查找某个元素的位置.
     *
     * @param arr 待查找数组
     * @param target 目标元素
     * @param <E> 支持泛型
     * @return 目标元素在待查找数组中的索引, 没找到则为-1
     */
    public static <E> int search(E[] arr, E target) {
        for (int i = 0; i < arr.length; i++) {
            // 引用类型要使用equals进行比较
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}

