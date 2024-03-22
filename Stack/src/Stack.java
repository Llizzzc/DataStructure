public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    /**
     * 入栈操作.
     *
     * @param e 入栈元素
     */
    void push(E e);

    /**
     * 出栈操作.
     *
     * @return 返回出栈元素
     */
    E pop();

    /**
     * 查看栈顶.
     *
     * @return 返回栈顶元素
     */
    E peek();
}