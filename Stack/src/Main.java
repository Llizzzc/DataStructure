import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 比较ArrayStack和LinkedStack的操作性能
        int[] opData = {1000000, 10000000};
        for (int opCount : opData) {
            Stack<Integer> arrayStack = new ArrayStack<>();
            double time = test(arrayStack, opCount);
            System.out.println("ArrayStack: " + time + " s");

            Stack<Integer> linkedStack = new LinkedStack<>();
            time = test(linkedStack, opCount);
            System.out.println("LinkedStack: " + time + " s");
            System.out.println();
        }
    }

    /**
     * 测试栈的操作耗时.
     *
     * @param stack 栈实现类
     * @param opCount 操作的数量
     * @return 操作耗时
     */
    private static double test(Stack<Integer> stack, int opCount) {
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < opCount; i ++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i ++) {
            stack.pop();
        }
        long end = System.nanoTime();
        return (end - start) / 1.0e9;
    }
}