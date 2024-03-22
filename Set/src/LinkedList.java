public class LinkedList<E> {

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
    private Node dummyHead; // 虚拟头, 链头插入不需额外处理
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        add(0, e);
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
        Node prev = dummyHead;
        // 找到待插入位置的前一个节点
        for (int i = 0; i < index; i ++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
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
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
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
     * @return 若链表中存在返回true, 否则返回false
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
     * 根据下标删除元素.
     *
     * @param index 下标
     * @return 返回被删除的元素
     * @throws IllegalArgumentException 下标范围[0, size)
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, illegal index.");
        }
        Node prev = dummyHead;
        // 找到待删除元素的前一个节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;
        size --;
        return ret.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素.
     *
     * @param e 待删除元素
     */
    public void removeElement(E e) {
        LinkedList.Node prev = dummyHead;
        // 找到待删除位置的上一个节点
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            LinkedList.Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

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
}