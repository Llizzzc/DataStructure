public class LoopDeque<E> implements Deque<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public LoopDeque() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public void addTail(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public void addFront(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        front = front == 0? data.length - 1: front - 1;
        data[front] = e;
        size ++;
    }

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

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed, deque is empty now.");
        }
        return data[front];
    }

    @Override
    public E getTail() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed, deque is empty now.");
        }
        int index = (tail + (data.length - 1)) % data.length;
        return data[index];
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

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