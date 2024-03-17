/**
 * 栈接口.
 * <p>包括入栈, 出栈等功能.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public interface Stack<E> {
    /**
     * 获取栈大小.
     *
     * @return 栈大小
     */
    int getSize();

    /**
     * 判断栈空.
     *
     * @return 空为true, 否则false
     */
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
     * @return 出栈元素
     */
    E pop();

    /**
     * 查看栈顶.
     *
     * @return 栈顶元素
     */
    E peek();
}
