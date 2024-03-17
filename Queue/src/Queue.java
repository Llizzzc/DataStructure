/**
 * 队列接口.
 * <p>包括入队, 出队, 获取队首等操作.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public interface Queue<E> {
    /**
     * 获取队列大小.
     *
     * @return 队列大小
     */
    int getSize();

    /**
     * 判断队列是否为空.
     *
     * @return 空为true, 否则false
     */
    boolean isEmpty();

    /**
     * 入队操作.
     *
     * @param e 入队元素
     */
    void enqueue(E e);

    /**
     * 出队操作.
     *
     * @return 出队元素
     */
    E dequeue();

    /**
     * 获取队首.
     *
     * @return 队首元素
     */
    E getFront();
}
