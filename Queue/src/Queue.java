public interface Queue<E> {

    int getSize();

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
     * @return 返回出队元素
     */
    E dequeue();

    /**
     * 获取队首.
     *
     * @return 返回队首元素
     */
    E getFront();
}