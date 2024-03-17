/**
 * 链表类.
 * <p>包含增, 删, 查, 改等操作.</p>
 *
 * @param <E>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinkedList<E> {
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
    private Node dummyHead; // 虚拟头, 链头插入不需额外处理
    private int size;

    /**
     * 无参数构造方法, 调用Node的无参数构造方法.
     */
    public LinkedList() {
        dummyHead = new Node();
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
     * 在链表头添加.
     *
     * @param e 待插入元素
     */
    public void addFirst(E e) {
        add(0, e);
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
        Node prev = dummyHead;
        // 找到待插入位置的前一个节点
        for (int i = 0; i < index; i ++) {
            prev = prev.next;
        }
        /*
         * Node node = new Node(e, prev.next);
         * prev.next = node;
         */
        prev.next = new Node(e, prev.next);
        size ++;
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
        Node cur = dummyHead.next;  // dummyHead的下一个节点为第一个节点
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        return cur.e;
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
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查看是否包含指定元素.
     *
     * @param e 待查找元素
     * @return 链表中存在为true, 否则false
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
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
        Node prev = dummyHead;
        // 找到待删除元素的前一个位置
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;
        size --;
        return ret.e;
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
        Node cur = dummyHead.next;
        res.append(String.format("LinkedList: size = %d\n", size));
        while (cur != null) {
            res.append(cur).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
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
