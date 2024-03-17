/**
 * 循环双端队列实现.
 * <p>实现基本双端队列接口.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LoopDeque<E> implements Deque<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    /**
     * 包含capacity的构造方法.
     *
     * @param capacity
     */
    public LoopDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * 无参数构造方法, LoopDeque(10).
     */
    public LoopDeque() {
        this(10);
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
     * 获取队列容量.
     *
     * @return  队列容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 队尾添加.
     *
     * @param e 待添加元素
     */
    @Override
    public void addTail(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    /**
     * 队首添加.
     *
     * @param e 待添加元素
     */
    @Override
    public void addFront(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        front = front == 0? data.length - 1: front - 1;
        data[front] = e;
        size ++;
    }

    /**
     * 队尾删除
     *
     * @return 队尾元素
     * @throws IllegalArgumentException 队列为空, 则出队失败
     */
    @Override
    public E removeTail() {
        if (isEmpty()) {
            throw new IllegalArgumentException("RemoveTail failed, deque is empty now.");
        }
        tail = tail == 0? data.length - 1: tail - 1;
        E ret = data[tail];
        data[tail] = null;
        size --;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    /**
     * 队首删除.
     *
     * @return 队首元素
     * @throws IllegalArgumentException 队列为空, 则出队失败
     */
    @Override
    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("RemoveFront failed, deque is empty now.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    /**
     * 获取队首.
     *
     * @return 队首
     * @throws IllegalArgumentException 队空时, 取队首失败
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed. Deque is empty now.");
        }
        return data[front];
    }

    /**
     * 获取队尾.
     *
     * @return 队尾
     * @throws IllegalArgumentException 队空时, 取队尾失败
     */
    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed. Deque is empty now.");
        }
        int index = (tail + (data.length - 1)) % data.length;
        return data[index];
    }

    /**
     * 数组变更容量.
     *
     * @param newCapacity 新容量
     */
    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含队列信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopDeque: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = 0; i < size; i ++) {
            res.append(data[(front + i) % data.length]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        Deque<Integer> deque = new LoopDeque<>();
        for (int i = 0; i < 16; i ++) {
            if (i % 2 == 0) deque.addTail(i);
            else deque.addFront(i);
            System.out.println(deque);
        }
        System.out.println();
        for (int i = 0; !deque.isEmpty(); i ++) {
            if (i % 2 == 0) deque.removeFront();
            else deque.removeTail();
            System.out.println(deque);
        }
    }
}