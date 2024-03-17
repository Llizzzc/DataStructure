/**
 * 双端队列接口.
 * <p>包括获取两端元素, 从两边添加删除元素等.</p>
 *
 * @param <E>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public interface Deque<E> {
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
     * 队首添加.
     *
     * @param e 待添加元素
     */
    void addFront(E e);

    /**
     * 队尾添加.
     *
     * @param e 待添加元素
     */
    void addTail(E e);

    /**
     * 队首删除.
     *
     * @return 队首元素
     */
    E removeFront();

    /**
     * 队尾删除
     *
     * @return 队尾元素
     */
    E removeTail();

    /**
     * 获取队首.
     *
     * @return 队首
     */
    E getFront();

    /**
     * 获取队尾.
     *
     * @return 队尾
     */
    E getLast();
}