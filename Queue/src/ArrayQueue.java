/**
 * 数组队列实现.
 * <p>实现了基本队列接口.</p>
 *
 * @param <E> 支持泛型
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    /**
     * 传入capacity的构造方法, 调用Array对应构造方法.
     *
     * @param capacity 队列容量
     */
    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    /**
     * 无参构造方法, 调用Array对应构造方法.
     */
    public ArrayQueue() {
        array = new Array<>();
    }

    /**
     * 获取队列大小.
     *
     * @return 队列大小
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断队列是否为空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入队操作.
     *
     * @param e 入队元素
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队操作.
     *
     * @return 出队元素
     */
    @Override
    public E dequeue() {
        return array.deleteFirst();
    }

    /**
     * 获取队首.
     *
     * @return 队首元素
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含队列信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("ArrayQueue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i ++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>();
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