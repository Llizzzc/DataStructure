/**
 * 链表实现映射.
 * <p>实现映射基本接口.</p>
 *
 * @param <K> 支持泛型
 * @param <V> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinkedListMap<K ,V> implements Map<K, V> {
    /**
     * 内部节点类.
     */
    private class Node {
        public K key;
        public V value;
        public Node next;
        /**
         * 包含key, value, next的构造方法.
         *
         * @param key 键
         * @param value 值
         * @param next 下一个节点
         */
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * 包含key, value, next的构造方法, 调用this(key, null, null).
         *
         * @param key 键
         */
        public Node(K key) {
            this(key, null, null);
        }

        /**
         * 无参构造方法, 调用this(null, null, null).
         */
        public Node() {
            this(null, null, null);
        }

        /**
         * 覆写Object的toString方法.
         *
         * @return 包含映射信息的字符串
         */
        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }
    private Node dummyHead;
    private int size;
    /**
     *  无参数构造方法.
     */
    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 查询映射的大小.
     *
     * @return 映射大小
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 查询映射是否为空.
     *
     * @return 空为true, 否则为false
     */
    @Override
    public boolean isEmpty() {
        return  size == 0;
    }

    /**
     * 获取key所指的node.
     *
     * @param key 键
     * @return key所指的node
     */
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 查看是否包含key所指的值.
     *
     * @param key 键
     * @return 存在为true, 否则false
     */
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return key指的值.
     */
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null? null: node.value;
    }

    /**
     * 添加键值对.
     *
     * @param key 键
     * @param value 值
     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size ++;
        } else {
            node.value = value; // 存在则覆盖
        }
    }

    /**
     * 设置key所指的值.
     *
     * @param key 键
     * @param newValue 新值
     * @throws IllegalArgumentException 不存在key则设置失败
     */
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist.");
        }
        node.value = newValue;
    }

    /**
     * 移除key指向的值.
     *
     * @param key 键
     * @return key所指的值
     */
    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.key.equals(key)) {
                break;
            }
            pre = pre.next;
        }
        if (pre.next != null) {
            Node del = pre.next;
            pre.next = del.next;
            del.next = null;
            size --;
            return del.value;
        }
        return null;
    }
}