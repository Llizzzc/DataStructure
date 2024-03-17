import java.util.Arrays;

/**
 * 动态数组类.
 * <p>支持增, 删, 查, 改等操作.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 2.0
 * @version jdk17
 * @see java.util.Arrays
 */
public class Array<E> {
    private E[] data;
    private int size;

    /**
     * 包含capacity的构造方法.
     *
     * @param capacity 数组容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参数构造方法, 调用Array(10).
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组大小.
     *
     * @return 数组大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量.
     *
     * @return 数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空.
     *
     * @return 空true, 否则false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在数组最后添加.
     *
     * @param e 待插入元素
     */
    public void addLast(E e) {
        add(e, size);
    }

    /**
     * 在数组首部添加.
     *
     * @param e 待插入元素
     */
    public void addFirst(E e) {
        add(e, 0);
    }

    /**
     * 在数组指定位置插入.
     *
     * @param e 待插入元素
     * @param index 要插入的索引位置
     * @throws IllegalArgumentException index小于0或index大于size, 说明索引不合法
     */
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, require index >= 0 && index <= size.");
        }
        // 当数组大小等于当前数组容量时, 需要扩容
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
        return data[index];
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
     * 获取首元素.
     *
     * @return 首元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 根据索引修改元素.
     *
     * @param e 新元素
     * @param index 待修改位置的索引
     * @throws IllegalArgumentException index小于0或index大于等于size, 说明索引不合法
     */
    public void set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, require index >= 0 && index < size.");
        }
        data[index] = e;
    }

    /**
     * 查看是否包含指定元素.
     *
     * @param e 待查找元素
     * @return 数组中存在为true, 否则false
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
     * 查找数组中第一个指定元素并返回索引.
     *
     * @param e 待查找元素
     * @return 元素e在数组中的索引, 为找到则为-1
     */
    public int find(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找数组中所有的指定元素, 返回全部索引.
     *
     * @param e 待查找元素
     * @return 值为e的所有索引组成的数组
     */
    public int[] findAll(E e) {
        int[] indexArr = new int[size];
        int j = 0;
        // 将查找到的索引依次放入indexArr中
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                indexArr[j] = i;
                j ++;
            }
        }
        return j == 0? null: Arrays.copyOf(indexArr, j);
    }

    /**
     * 删除指定索引位置的元素.
     *
     * @param index 要删除的位置
     * @return 删除的元素
     * @throws IllegalArgumentException index小于0或index大于等于size, 说明索引不合法
     */
    public E delete(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Delete failed, require index >= 0 && index < size.");
        }
        E ret = data[index];
        // 从index + 1位置开始, 将元素依次向前移动
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size --;
        // 当数组大小为数组容量的1/4时, 进行缩容, 可以避免复杂度的震荡
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    /**
     * 删除第一个位置的元素.
     *
     * @return 删除的元素
     */
    public E deleteFirst() {
        return delete(0);
    }

    /**
     * 删除最后一个位置的元素.
     *
     * @return 删除的元素
     */
    public E deleteLast() {
        return delete(size - 1);
    }

    /**
     * 查找某个元素然后删除.
     *
     * @param e 待查找并删除的元素
     */
    public void findDelete(E e) {
        if (find(e) != -1) {
            delete(find(e));
        }
    }

    /**
     * 查找某个元素, 删除数组中的所有该元素.
     *
     * @param e 待查找并删除的元素
     */
    public void findDeleteAll(E e) {
        while (contains(e)) {
            findDelete(e);
        }
    }

    /**
     * 数组变更容量.
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

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含数组信息的字符串
     */
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