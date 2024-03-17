/**
 * 链栈实现类.
 * <p>实现了栈相关接口.</p>
 *
 * @param <E>支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> list;

    /**
     * 无参数构造方法, 调用LinkedList的该构造方法.
     */
    public LinkedStack() {
        list = new LinkedList<>();
    }

    /**
     * 获取栈大小.
     *
     * @return 栈大小
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 判断栈空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 查看栈顶.
     *
     * @return 栈顶元素
     */
    @Override
    public E peek() {
        return list.getFirst();
    }

    /**
     * 出栈操作.
     *
     * @return 出栈元素
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    /**
     * 入栈操作.
     *
     * @param e 入栈元素
     */
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含栈信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("stack: top ");
        res.append(list);
        return res.toString();
    }

    // 功能测试
    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.peek());
    }
}
