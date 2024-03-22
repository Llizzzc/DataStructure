public class Main {
    /**
     * 测试堆排序.
     *
     * @param arr 待排序数组
     * @param isHeap 是否在堆中直接整理了数组
     * @return 返回操作耗时
     * @throws IllegalArgumentException 排序失败
     */
    private static double testHeap(Integer[] arr, boolean isHeap) {
        long start = System.nanoTime();
        MaxHeap<Integer> heap;
        if (isHeap) {
            heap = new MaxHeap<>(arr);
        } else {
            heap =  new MaxHeap<>();
            for (Integer num : arr) {
                heap.add(num);
            }
        }

        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i ++) {
            temp[i] = heap.extractMax();
        }

        for (int i = 1; i < arr.length; i ++) {
            if (temp[i - 1] < temp[i]) {
                throw new IllegalArgumentException("Sort error.");
            }
        }

        System.out.println("Test MaxHeap completed.");
        long end = System.nanoTime();
        return (end - start) / 1.0e9;
    }

    public static void main(String[] args) {
        // 性能测试
        Integer[] arr = ArrayGenerator.generatorRandomArray(10000000, 10000000);
        System.out.printf("cost %f s with heap.\n", testHeap(arr, true));
        System.out.println();
        System.out.printf("cost %f s without heap.\n", testHeap(arr, false));
    }
}