public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        add(e, size);
    }

    public void addFirst(E e) {
        add(e, 0);
    }

    /**
     * 在指定数组下标处插入.
     *
     * @param e 待插入元素
     * @param index 数组下标
     * @throws IllegalArgumentException index范围[0, size]
     */
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, illegal index.");
        }
        if (size == getCapacity()) {
            resize(2 * getCapacity());
        }
        // 将index位置及其后面元素依次向后移动一个位置, 接着在index位置插入元素e
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    /**
     * 根据数组下标获取元素.
     *
     * @param index 数组下标
     * @return 返回index位置的元素
     * @throws IllegalArgumentException index范围[0, size)
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, illegal index");
        }
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    /**
     * 根据数组下标修改元素.
     *
     * @param e 新元素
     * @param index 数组下标
     * @throws IllegalArgumentException index范围[0, size)
     */
    public void set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, illegal index.");
        }
        data[index] = e;
    }

    /**
     * 查看是否包含指定元素.
     *
     * @param e 待查找元素
     * @return 若数组中存在该元素返回true, 否则返回false
     */
    public Boolean contains(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找给定元素首次在数组中出现的下标.
     *
     * @param e 待查找元素
     * @return 返回元素在数组中的下标, 若未找到返回-1
     */
    public int find(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public Array<Integer> findAll(E e) {
        Array<Integer> indexList = new Array<>();
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                indexList.addLast(i);
            }
        }
        return indexList;
    }

    /**
     * 根据数组下标删除元素.
     *
     * @param index 数组下标
     * @return 返回删除的元素
     * @throws IllegalArgumentException index范围[0, size)
     */
    public E delete(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Delete failed, illegal index.");
        }
        E ret = data[index];
        // 从index + 1位置开始, 将元素依次向前移动
        for (int i = index + 1; i < size; i ++) {
            data[i - 1] = data[i];
        }
        size --;
        // 当数组大小为数组容量的1/4时, 进行缩容, 可以避免复杂度的震荡
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E deleteFirst() {
        return delete(0);
    }

    public E deleteLast() {
        return delete(size - 1);
    }

    /**
     * 查找并删除首次出现的指定元素.
     *
     * @param e 待删除元素
     * @throws IllegalArgumentException 元素不存在
     */
    public void findDelete(E e) {
        if (find(e) != -1) {
            delete(find(e));
            return;
        }
        throw new IllegalArgumentException("No such element.");
    }

    public void findDeleteAll(E e) {
        while (contains(e)) {
            findDelete(e);
        }
    }

    /**
     * 调整数组容量.
     *
     * @param newCapacity 新容量
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append('[');
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}