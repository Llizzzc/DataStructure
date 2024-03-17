/**
 * 平衡树实现映射.
 * <p>实现基本映射接口.</p>
 *
 * @param <K> 支持泛型
 * @param <V> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVLTree<K, V> avlTree;
    /**
     * 无参构造方法, 调用AVLTree相应构造函数.
     */
    public AVLMap() {
        avlTree = new AVLTree();
    }

    /**
     * 添加键值对.
     *
     * @param key 键
     * @param value 值
     */
    @Override
    public void add(K key, V value) {
        avlTree.add(key, value);
    }

    /**
     * 移除key指向的值.
     *
     * @param key 键
     * @return key所指的值
     */
    @Override
    public V remove(K key) {
        return avlTree.remove(key);
    }

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return key指的值.
     */
    @Override
    public boolean contains(K key) {
        return avlTree.contains(key);
    }

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return key指的值.
     */
    @Override
    public V get(K key) {
        return avlTree.get(key);
    }

    /**
     * 设置key所指的值.
     *
     * @param key 键
     * @param value 值
     */
    @Override
    public void set(K key, V value) {
        avlTree.set(key, value);
    }

    /**
     * 查询映射的大小.
     *
     * @return 映射大小
     */
    @Override
    public int getSize() {
        return avlTree.getSize();
    }

    /**
     * 查询映射是否为空.
     *
     * @return 空为true, 否则为false
     */
    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }
}