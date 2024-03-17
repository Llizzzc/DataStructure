/**
 * 循环队列实现.
 * <p>实现了基本队列接口.</p>
 *
 * @param <E> 支持泛型
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;  // 指向队首
    private int tail;   // 指向队尾的下一个位置
    private int size;

    /**
     * 包含capacity的构造方法, 多开一个空间用于判断队满.
     *
     * @param capacity
     */
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
    }

    /**
     * 无参数构造方法, 调用LoopQueue(10).
     */
    public LoopQueue() {
        this(10);
    }

    /**
     * 获取队列容量.
     *
     * @return  队列容量
     */
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 判断队列是否为空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
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
     * 入队操作.
     *
     * @param e 入队元素
     */
    @Override
    public void enqueue(E e) {
        // 队满, 需要扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
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
            throw new IllegalArgumentException("Dequeue failed, queue is empty now.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        // 当数组大小为数组容量的1/4时, 进行缩容, 可以避免复杂度的震荡
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
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
            throw new IllegalArgumentException("GetFront failed, queue is empty now.");
        }
        return data[front];
    }

    /**
     * 数组变更容量.
     *
     * @param newCapacity 新容量
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[(front + i) % data.length];
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
        res.append(String.format("LoopQueue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
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