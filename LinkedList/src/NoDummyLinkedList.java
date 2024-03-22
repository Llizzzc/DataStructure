public class NoDummyLinkedList<E> {

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

    public Node head;
    public int size;

    public NoDummyLinkedList() {
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
        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i ++) {
                prev = prev.next;
            }
            prev.next = new Node(e, prev.next);
            size ++;
        }
    }

    public void addFirst(E e) {
        head = new Node(e, head);
        size ++;
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
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
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
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查看是否包含指定元素.
     *
     * @param e 待查找元素
     * @return 若链表中存在返回true, 否则返回false
     */
    public boolean contains(E e) {
        Node cur = head;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
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
        if (index == 0) {
            return removeFirst();
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Node ret = prev.next;
            prev.next = ret.next;
            ret.next = null;
            size --;
            return ret.e;
        }
    }

    public E removeFirst() {
        Node ret = head;
        head = head.next;
        ret.next = null;
        size --;
        return ret.e;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LinkedList: size = %d\n", size));
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
        NoDummyLinkedList<Integer> linkedList = new NoDummyLinkedList<>();
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