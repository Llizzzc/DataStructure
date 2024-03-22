public interface Map<K, V> {
    /**
     * 添加键值对.
     *
     * @param key 键
     * @param value 值
     */
    void add(K key, V value);

    /**
     * 移除key指向的值.
     *
     * @param key 键
     * @return 返回key所指的值
     */
    V remove(K key);

    /**
     * 查看是否包含key所指的值.
     *
     * @param key 键
     * @return 若存在返回true, 否则返回false
     */
    boolean contains(K key);

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return 返回key指的值
     */
    V get(K key);

    /**
     * 设置key所指的值.
     *
     * @param key 键
     * @param value 值
     */
    void set(K key, V value);

    /**
     * 查询映射的大小.
     *
     * @return 返回映射大小
     */
    int getSize();

    /**
     * 查询映射是否为空.
     *
     * @return 若空返回true, 否则返回false
     */
    boolean isEmpty();
}