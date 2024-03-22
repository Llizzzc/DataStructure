public interface Deque<E> {

    int getSize();

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
     * @return 返回队首元素
     */
    E removeFront();

    /**
     * 队尾删除
     *
     * @return 返回队尾元素
     */
    E removeTail();

    /**
     * 获取队首.
     *
     * @return 返回队首
     */
    E getFront();

    /**
     * 获取队尾.
     *
     * @return 返回队尾
     */
    E getTail();
}