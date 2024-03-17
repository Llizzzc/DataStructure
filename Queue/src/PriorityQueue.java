/**
 * 优先队列实现.
 * <p>实现了基本队列接口.</p>
 *
 * @param <E> 支持泛型
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap;

    /**
     * 无参数构造方法, 调用MaxHeap对应构造方法.
     */
    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    /**
     * 获取队列大小.
     *
     * @return 队列大小
     */
    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    /**
     * 判断队列是否为空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /**
     * 获取队首.
     *
     * @return 队首元素
     */
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    /**
     * 入队操作.
     *
     * @param e 入队元素
     */
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    /**
     * 出队操作.
     *
     * @return 出队元素
     */
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }
}