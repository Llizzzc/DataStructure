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
     * @return 若存在返回true, 否则返回false
     */
    boolean contains(E e);

    /**
     * 获取集合大小.
     *
     * @return 返回集合大小
     */
    int getSize();

    /**
     * 判断集合是否为空.
     *
     * @return 若空返回true, 否则返回false
     */
    boolean isEmpty();
}