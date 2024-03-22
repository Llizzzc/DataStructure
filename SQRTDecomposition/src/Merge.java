public interface Merge<E> {
    /**
     * 对两个数据进行操作.
     *
     * @param a 操作数
     * @param b 操作数
     * @return 返回操作之后的结果
     */
    E merge(E a, E b);
}