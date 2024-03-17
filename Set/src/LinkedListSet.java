/**
 * 基于链表的集合实现类.
 * <p>实现了集合接口基本操作.</p>
 *
 * @param <E>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    /**
     * 无参构造方法, 调用LinkedList相应构造方法.
     */
    public LinkedListSet() {
        list = new LinkedList<>();
    }

    /**
     * 获取集合大小.
     *
     * @return 集合大小
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 查找元素.
     *
     * @param e 待查找元素
     * @return 存在为true, 否则false
     */
    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    /**
     * 判断集合是否为空
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 添加元素.
     *
     * @param e 待添加元素
     */
    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addFirst(e);
        }
    }

    /**
     * 删除元素.
     *
     * @param e 待删除元素
     */
    @Override
    public void remove(E e) {
        list.removeElement(e);
    }
}