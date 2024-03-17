/**
 * 链式双端队列实现.
 * <p>实现了基本双端队列接口.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinkedDeque<E> implements Deque<E> {
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
    private Node head;
    private Node tail;
    private int size;

    /**
     * 无参数构造方法, head = tail = null.
     */
    public LinkedDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 获取队列大小.
     *
     * @return 队列大小
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断队列是否为空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 队尾入队操作.
     *
     * @param e 入队元素
     */
    @Override
    public void addTail(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    /**
     * 队首入队操作.
     *
     * @param e 入队元素
     */
    @Override
    public void addFront(E e) {
        if (head == null) {
            Node newNode = new Node(e, head);
            tail = head = newNode;
        } else {
            head = new Node(e, head);
        }
        size ++;
    }

    /**
     * 队尾出队操作.
     *
     * @return 出队元素
     * @throws IllegalArgumentException 队列为空, 则出队失败
     */
    @Override
    public E removeTail() {
        if (isEmpty()) {
            throw new IllegalArgumentException("removeTail failed, empty queue.");
        }
        if (getSize() == 1) {
            Node ret = tail;
            head = tail = null;
            size --;
            return ret.e;
        } else {
            Node pre = head;
            for (int i = 0; i < getSize() - 2; i ++) {
                pre = pre.next;
            }
            Node ret = tail;
            pre.next = ret.next;
            tail = pre;
            size --;
            return ret.e;
        }
    }

    /**
     * 获取队尾.
     *
     * @return 队尾元素
     * @throws IllegalArgumentException 队空时, 取队尾失败
     */
    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetTail failed, empty queue.");
        }
        return tail.e;
    }

    /**
     * 队首出队操作.
     *
     * @return 出队元素
     * @throws IllegalArgumentException 队列为空, 则出队失败
     */
    @Override
    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("removeFront failed, empty queue.");
        }
        Node ret = head;
        head = head.next;
        ret.next = null;
        if (head == null) {
            tail = null;
        }
        size --;
        return ret.e;
    }

    /**
     * 获取队首.
     *
     * @return 队首元素
     * @throws IllegalArgumentException 队空时, 取队首失败
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed, empty queue.");
        }
        return head.e;
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含队列信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedDeque: front ");
        Node cur = head;
        while (cur != null) {
            res.append(cur).append("->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedDeque<>();
        for (int i = 0; i < 16; i ++) {
            if (i % 2 == 0) deque.addTail(i);
            else deque.addFront(i);
            System.out.println(deque);
        }
        System.out.println(deque.getFront());
        System.out.println(deque.getLast());
        System.out.println();
        for (int i = 0; !deque.isEmpty(); i ++) {
            if (i % 2 == 0) deque.removeFront();
            else deque.removeTail();
            System.out.println(deque);
        }
    }
}