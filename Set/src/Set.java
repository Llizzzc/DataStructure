/**
 * 集合接口, 不能存放重复元素.
 * <p>包括添加, 删除, 查找等操作.</p>
 *
 * @param <E>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public interface Set<E> {
    /**
     * 添加元素.
     *
     * @param e 待添加元素
     */
    void add(E e);

    /**
     * 删除元素.
     *
     * @param e 待删除元素
     */
    void remove(E e);

    /**
     * 查找元素.
     *
     * @param e 待查找元素
     * @return 存在为true, 否则false
     */
    boolean contains(E e);

    /**
     * 获取集合大小.
     *
     * @return 集合大小
     */
    int getSize();

    /**
     * 判断集合是否为空.
     *
     * @return 空为true, 否则false
     */
    boolean isEmpty();
}