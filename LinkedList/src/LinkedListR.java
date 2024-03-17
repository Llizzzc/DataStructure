/**
 * 链表类, 非虚拟头递归实现.
 * <p>包含增, 删, 查, 改等操作.</p>
 *
 * @param <E>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinkedListR<E> {
    /**
     * 内部节点类.
     */
    private class Node {
        public E e;
        public Node next;

        /**
         * 包含e, next的构造方法.
         *
         * @param e 节点元素
         * @param next 节点引用
         */
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        /**
         * 包含e的构造方法, 调用Node(e, null).
         *
         * @param e 节点元素
         */
        public Node(E e) {
            this(e, null);
        }

        /**
         * 无参数构造, 调用Node(null, null).
         */
        public Node() {
            this(null, null);
        }

        /**
         * 覆写Object类的toString方法.
         *
         * @return 包含节点信息的字符串
         */
        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 用于递归删除设计的类.
     */
    private class Pair {
        public Node node;
        public E value;

        /**
         * 包含e, next的构造方法.
         *
         * @param node 节点
         * @param value 节点值
         */
        public Pair(Node node, E value) {
            this.node = node;
            this.value = value;
        }
    }

    private Node head;
    private int size;

    /**
     * 无参数构造方法.
     */
    public LinkedListR() {
        head = null;
        size = 0;
    }

    /**
     * 获取链表大小.
     *
     * @return 链表大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空.
     *
     * @return 空true, 否则false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表指定位置插入.
     *
     * @param e 待插入元素
     * @param index 要插入的索引位置
     * @throws IllegalArgumentException index小于0或index大于size, 说明索引不合法
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, require index >= 0 && index <= size.");
        }
        head = add(index, e, head);
        size ++;
    }

    /**
     * 递归函数, 在以node为头的链表中尝试添加e.
     *
     * @param index 递归过程变化的索引
     * @param e 待插入元素
     * @param node 以node为头的链表
     * @return 已经添加上e的, 以node为头的新链表
     */
    private Node add(int index, E e, Node node) {
        // 递归到底的基本问题
        if (index == 0) {
            return new Node(e, node);
        } else {
            // 重新连接链表
            node.next = add(index - 1, e, node.next);
            return node;
        }
    }

    /**
     * 在链表头添加.
     *
     * @param e 待插入元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表尾添加.
     *
     * @param e 待插入元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 根据索引获取元素.
     *
     * @param index 待获取元素的索引
     * @return index位置的元素
     * @throws IllegalArgumentException index小于0或index大于等于size, 说明索引不合法
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, require index >= 0 && index < size.");
        }
        return get(index, head);
    }

    /**
     * 递归函数, 在node为头的链表中尝试获取元素.
     *
     * @param index 递归过程变化的索引
     * @param node 以node为头的链表
     * @return index位置的元素
     */
    private E get(int index, Node node) {
        if (index == 0) {
            return node.e;
        } else {
            return get(index - 1, node.next);
        }
    }

    /**
     * 获取首元素.
     *
     * @return 首元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取末尾元素.
     *
     * @return 末尾元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 根据索引修改元素.
     *
     * @param e 新元素
     * @param index 待修改位置的索引
     * @throws IllegalArgumentException index小于0或index大于等于size, 说明索引不合法
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, require index >= 0 && index < size.");
        }
        set(index, e, head);
    }

    /**
     * 递归函数, 在node为头的链表中尝试修改元素.
     *
     * @param index 递归中变化的索引
     * @param e 新元素
     * @param node 以node为头的链表
     */
    private void set(int index, E e, Node node) {
        if (index == 0) {
            node.e = e;
        } else {
            set(index - 1, e, node.next);
        }
    }

    /**
     * 查看是否包含指定元素.
     *
     * @param e 待查找元素
     * @return 链表中存在为true, 否则false
     */
    public boolean contains(E e) {
        return contains(e, head);
    }

    /**
     * 递归函数, 在以node为头的链表中尝试查找.
     *
     * @param e 待查找元素
     * @param node 以node为头的链表
     * @return 链表中存在为true, 否则false
     */
    private boolean contains(E e, Node node) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        } else {
            return contains(e, node.next);
        }
    }

    /**
     * 删除指定索引位置的元素.
     *
     * @param index 要删除的位置
     * @return 删除的元素
     * @throws IllegalArgumentException index小于0或index大于等于size, 说明索引不合法
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, require index >= 0 && index < size.");
        }
        Pair res = remove(index, head);
        head = res.node;
        size --;
        return res.value;
    }

    /**
     * 递归函数, 在以node为头的链表中尝试删除元素.
     *
     * @param index 递归中变化的索引
     * @param node 以node为头的链表
     * @return 删除完元素的以node为头的新链表, 以及删除的元素
     */
    private Pair remove(int index, Node node) {
        if (index == 0) {
            return new Pair(node.next, node.e);
        }
        Pair res = remove(index - 1, node.next);
        node.next = res.node;
        return new Pair(node, res.value);
    }

    /**
     * 删除第一个位置的元素.
     *
     * @return 删除的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个位置的元素.
     *
     * @return 删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含链表信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            res.append(cur).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        LinkedListR<Integer> linkedList = new LinkedListR<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(3, 5);
        System.out.println(linkedList);
        linkedList.remove(5);
        System.out.println(linkedList);
        linkedList.remove(1);
        System.out.println(linkedList);
        linkedList.set(2, 99);
        System.out.println(linkedList);
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.contains(1));
        System.out.println(linkedList.contains(-1));
    }
}