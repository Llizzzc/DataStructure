/**
 * 数组栈实现类.
 * <p>实现了栈相关接口.</p>
 *
 * @param <E>支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    /**
     * 包含capacity的构造方法, 调用Array的该构造方法.
     *
     * @param capacity 栈容量
     */
    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    /**
     * 无参数构造方法, 调用Array的该构造方法.
     */
    public ArrayStack() {
        array = new Array<>();
    }

    /**
     * 获取栈大小.
     *
     * @return 栈大小
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断栈空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入栈操作.
     *
     * @param e 入栈元素
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 出栈操作.
     *
     * @return 出栈元素
     */
    @Override
    public E pop() {
        return array.deleteLast();
    }

    /**
     * 查看栈顶.
     *
     * @return 栈顶元素
     */
    public E peek() {
        return array.getLast();
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含栈信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i ++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.peek());
    }
}
