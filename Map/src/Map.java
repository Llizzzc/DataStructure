/**
 * 映射接口.
 * <p>包括添加, 删除, 查找, 修改等操作.</p>
 *
 * @param <K> 支持泛型
 * @param <V> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
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
     * @return key所指的值
     */
    V remove(K key);

    /**
     * 查看是否包含key所指的值.
     *
     * @param key 键
     * @return 存在为true, 否则false
     */
    boolean contains(K key);

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return key指的值.
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
     * @return 映射大小
     */
    int getSize();

    /**
     * 查询映射是否为空.
     *
     * @return 空为true, 否则为false
     */
    boolean isEmpty();
}