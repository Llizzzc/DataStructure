/**
 * 链式队列实现.
 * <p>实现了基本队列接口.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinkedQueue<E> implements Queue<E> {
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
    public LinkedQueue() {
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
     * 入队操作.
     *
     * @param e 入队元素
     */
    @Override
    public void enqueue(E e) {
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
     * 出队操作.
     *
     * @return 出队元素
     * @throws IllegalArgumentException 队列为空, 则出队失败
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue failed, empty queue.");
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
        res.append("LinkedQueue: front ");
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
        Queue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < 10; i ++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
        System.out.println(queue.getFront());
    }
}