/**
 * 数组双端队列实现.
 * <p>实现基本双端队列接口.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class ArrayDeque<E> implements Deque<E> {

    private Array<E> data ;

    /**
     * 传入capacity的构造方法, 调用Array对应构造方法.
     *
     * @param capacity 队列容量
     */
    public ArrayDeque(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * 无参构造方法, 调用Array对应构造方法.
     */
    public ArrayDeque() {
        data = new Array<>();
    }

    /**
     * 获取队列大小.
     *
     * @return 队列大小
     */
    @Override
    public int getSize() {
        return data.getSize();
    }

    /**
     * 判断队列是否为空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 获取队列容量.
     *
     * @return  队列容量
     */
    public int getCapacity() {
        return data.getCapacity();
    }

    /**
     * 队尾添加.
     *
     * @param e 待添加元素
     */
    @Override
    public void addTail(E e) {
       data.addLast(e);
    }

    /**
     * 队首添加.
     *
     * @param e 待添加元素
     */
    @Override
    public void addFront(E e) {
        data.addFirst(e);
    }

    /**
     * 队尾删除
     *
     * @return 队尾元素
     */
    @Override
    public E removeTail() {
       return data.deleteLast();
    }

    /**
     * 队首删除.
     *
     * @return 队首元素
     */
    @Override
    public E removeFront() {
       return data.deleteFirst();
    }

    /**
     * 获取队首.
     *
     * @return 队首
     */
    @Override
    public E getFront() {
       return data.getFirst();
    }

    /**
     * 获取队尾.
     *
     * @return 队尾
     */
    @Override
    public E getLast() {
       return data.getLast();
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含队列信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("ArrayDeque: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for (int i = 0; i < getSize(); i ++) {
            res.append(data.get(i));
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 16; i ++) {
            if (i % 2 == 0) deque.addTail(i);
            else deque.addFront(i);
            System.out.println(deque);
        }
        System.out.println(deque.getFront());
        System.out.println(deque.getLast());
        System.out.println();
        for (int i = 0; !deque.isEmpty(); i ++) {
            if (i % 2 == 0) deque.removeFront();
            else deque.removeTail();
            System.out.println(deque);
        }
    }
}