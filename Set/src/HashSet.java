/**
 * 基于HashTable的集合实现类.
 * <p>实现了集合接口基本操作.</p>
 *
 * @param <E>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class HashSet<E> implements Set<E> {
    private HashTable<E, Object> hashTable;

    /**
     * 无参构造方法, 调用HashTable相应构造方法.
     */
    public HashSet() {
        hashTable = new HashTable<>();
    }

    /**
     * 添加元素.
     *
     * @param e 待添加元素
     */
    @Override
    public void add(E e) {
        hashTable.add(e, null);
    }

    /**
     * 删除元素.
     *
     * @param e 待删除元素
     */
    @Override
    public void remove(E e) {
        hashTable.remove(e);
    }

    /**
     * 查找元素.
     *
     * @param e 待查找元素
     * @return 存在为true, 否则false
     */
    @Override
    public boolean contains(E e) {
        return hashTable.contains(e);
    }

    /**
     * 获取集合大小.
     *
     * @return 集合大小
     */
    @Override
    public int getSize() {
        return hashTable.getSize();
    }

    /**
     * 判断集合是否为空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }
}