public class ArrayDeque<E> implements Deque<E> {

    private Array<E> data ;

    public ArrayDeque(int capacity) {
        data = new Array<>(capacity);
    }

    public ArrayDeque() {
        data = new Array<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int getCapacity() {
        return data.getCapacity();
    }

    @Override
    public void addTail(E e) {
       data.addLast(e);
    }

    @Override
    public void addFront(E e) {
        data.addFirst(e);
    }

    @Override
    public E removeTail() {
       return data.deleteLast();
    }

    @Override
    public E removeFront() {
       return data.deleteFirst();
    }

    @Override
    public E getFront() {
       return data.getFirst();
    }

    @Override
    public E getTail() {
       return data.getLast();
    }

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
        System.out.println(deque.getTail());
        System.out.println();
        for (int i = 0; !deque.isEmpty(); i ++) {
            if (i % 2 == 0) deque.removeFront();
            else deque.removeTail();
            System.out.println(deque);
        }
    }
}