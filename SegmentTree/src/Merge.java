/**
 * 合并接口, 决定数据之间采取什么操作.
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public interface Merge<E> {
    /**
     * 对两个数据进行操作.
     *
     * @param a 操作数
     * @param b 操作数
     * @return 操作之后的结果
     */
    E merge(E a, E b);
}
