/**
 * 循环队列实现, 不使用size变量.
 * <p>实现了基本队列接口.</p>
 *
 * @param <E> 支持泛型
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class NoSizeLoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;

    /**
     * 包含capacity的构造方法, 多开一个空间用于判断队满.
     *
     * @param capacity
     */
    public NoSizeLoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
    }

    /**
     * 无参数构造方法, NoSizeLoopQueue(10).
     */
    public NoSizeLoopQueue() {
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
        return tail > front ? tail - front : data.length - (front - tail);
    }

    /**
     * 入队操作.
     *
     * @param e 入队元素
     */
    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
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
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
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
        int i;
        for (i = 0; i < getSize(); i ++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = i;
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含队列信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("NoSizeLoopQueue: size = %d, capacity = %d\n", getSize(), getCapacity()));
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
        Queue<Integer> queue = new NoSizeLoopQueue<>();
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
