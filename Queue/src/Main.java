import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 不同队列实现出入队时间消耗的比较
        int[] opData = {10000, 100000};
        for (int opCount : opData) {
            Queue<Integer> arrayQueue = new ArrayQueue<>();
            double time = test(arrayQueue, opCount);
            System.out.println("ArrayQueue: " + time + " s");

            Queue<Integer> loopQueue = new LoopQueue<>();
            time = test(loopQueue, opCount);
            System.out.println("LoopQueue: " + time + " s");

            Queue<Integer> linkedQueue = new LinkedQueue<>();
            time = test(linkedQueue, opCount);
            System.out.println("LinkedQueue: " + time + " s");

            Queue<Integer> priorityQueue = new PriorityQueue<>();
            time = test(priorityQueue, opCount);
            System.out.println("PriorityQueue: " + time + " s");
            System.out.println();
        }
    }

    /**
     * 队列操作耗时计算.
     *
     * @param q 队列
     * @param opCount 操作数
     * @return 操作耗时
     */
    private static double test(Queue<Integer> q, int opCount) {
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < opCount; i ++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i ++) {
            q.dequeue();
        }
        long end = System.nanoTime();
        return (end - start) / 1.0e9;
    }
}