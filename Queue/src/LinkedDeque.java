public class LinkedDeque<E> implements Deque<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node head;
    private Node tail;
    private int size;

    public LinkedDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    @Override
    public E removeTail() {
        if (isEmpty()) {
            throw new IllegalArgumentException("RemoveTail failed, empty queue.");
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

    @Override
    public E getTail() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetTail failed, empty queue.");
        }
        return tail.e;
    }

    @Override
    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("RemoveFront failed, empty queue.");
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

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed, empty queue.");
        }
        return head.e;
    }

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
        System.out.println(deque.getTail());
        System.out.println();
        for (int i = 0; !deque.isEmpty(); i ++) {
            if (i % 2 == 0) deque.removeFront();
            else deque.removeTail();
            System.out.println(deque);
        }
    }
}