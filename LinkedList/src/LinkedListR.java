public class LinkedListR<E> {

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

    /**
     * 用于递归删除设计的类.
     */
    private class Pair {
        public Node node;
        public E value;

        public Pair(Node node, E value) {
            this.node = node;
            this.value = value;
        }
    }

    private Node head;
    private int size;

    public LinkedListR() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表指定位置插入.
     *
     * @param e 待插入元素
     * @param index 下标
     * @throws IllegalArgumentException 下标范围[0, size]
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, illegal index.");
        }
        head = add(index, e, head);
        size ++;
    }

    /**
     * 递归函数, 在以node为头的链表中尝试添加e.
     *
     * @param index 下标
     * @param e 待插入元素
     * @param node 以node为头的链表
     * @return 返回已经添加上e的, 以node为头的新链表
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

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 根据下标获取元素.
     *
     * @param index 下标
     * @return 返回index位置的元素
     * @throws IllegalArgumentException 下标范围[0, size)
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, illegal index.");
        }
        return get(index, head);
    }

    /**
     * 递归函数, 在node为头的链表中尝试获取元素.
     *
     * @param index 下标
     * @param node 以node为头的链表
     * @return 返回index位置的元素
     */
    private E get(int index, Node node) {
        if (index == 0) {
            return node.e;
        } else {
            return get(index - 1, node.next);
        }
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    /**
     * 根据下标修改元素.
     *
     * @param e 新元素
     * @param index 下标
     * @throws IllegalArgumentException 下标范围[0, size)
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, illegal index.");
        }
        set(index, e, head);
    }

    /**
     * 递归函数, 在node为头的链表中尝试修改元素.
     *
     * @param index 下标
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
     * @return 若链表中存在返回true, 否则返回false
     */
    public boolean contains(E e) {
        return contains(e, head);
    }

    /**
     * 递归函数, 在以node为头的链表中尝试查找.
     *
     * @param e 待查找元素
     * @param node 以node为头的链表
     * @return 若链表中存在返回true, 否则返回false
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
     * 根据下标删除元素.
     *
     * @param index 下标
     * @return 返回删除的元素
     * @throws IllegalArgumentException 下标范围[0, size)
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, illegal index.");
        }
        Pair res = remove(index, head);
        head = res.node;
        size --;
        return res.value;
    }

    /**
     * 递归函数, 在以node为头的链表中尝试删除元素.
     *
     * @param index 下标
     * @param node 以node为头的链表
     * @return 返回删除完元素的以node为头的新链表, 以及删除的元素
     */
    private Pair remove(int index, Node node) {
        if (index == 0) {
            return new Pair(node.next, node.e);
        }
        Pair res = remove(index - 1, node.next);
        node.next = res.node;
        return new Pair(node, res.value);
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

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