/**
 * 二叉堆实现, 最小堆, 是一颗完全二叉树.
 * <p>包括添加, 查找, 替换等操作.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class MinHeap<E extends Comparable<E>> {
    private Array<E> data;

    /**
     * 包括capacity的构造函数, 调用Array对应构造函数.
     *
     * @param capacity 容量
     */
    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * 无参数构造函数, 调用Array对应构造函数.
     */
    public MinHeap() {
        data = new Array<>();
    }

    /**
     * 包括arr的构造函数, 调用Array对应构造方法, 直接将任意arr整理为最大堆形式, 时间复杂度为O(n); 而add n次时间复杂度为O(n * log n).
     *
     * @param arr 任意数组
     */
    public MinHeap(E[] arr) {
        data = new Array<>(arr);
        if (arr.length != 1) {
            for (int i = parent(arr.length - 1); i >= 0; i --) {
                siftDown(i);
            }
        }
    }

    /**
     * 获取堆大小.
     *
     * @return 堆大小
     */
    public int getSize() {
        return data.getSize();
    }

    /**
     * 判断堆是否为空.
     *
     * @return 空为true, 否则false
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 获得index的父亲节点索引.
     *
     * @param index 索引
     * @return 返回父亲索引
     * @throws IllegalArgumentException index == 0不允许
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 获取index的左孩子索引.
     *
     * @param index 索引
     * @return 左孩子索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 获取index的右孩子索引.
     *
     * @param index 索引
     * @return 右孩子索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素.
     *
     * @param e 待添加元素
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 元素上浮过程.
     *
     * @param k 索引
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 查找最小元素.
     *
     * @return 最小元素
     * @throws IllegalArgumentException 堆为空时无法取出最小元素
     */
    private E findMin() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Empty heap now.");
        }
        return data.get(0);
    }

    /**
     * 从堆中取出最小元素.
     *
     * @return 最小元素
     */
    public E extractMin() {
        E ret = findMin();
        data.swap(0, data.getSize() - 1);
        data.deleteLast();
        siftDown(0);
        return ret;
    }

    /**
     * 元素下沉过程.
     *
     * @param k 索引
     */
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j).compareTo(data.get(j + 1)) > 0) {
                j = rightChild(k);
            }
            if (data.get(k).compareTo(data.get(j)) <= 0) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出最小元素, 并替换成e.
     *
     * @param e 待替换的元素
     * @return 最小元素
     */
    public E replace(E e) {
        E ret = data.get(0);
        data.set(e, 0);
        siftDown(0);
        return ret;
    }
}