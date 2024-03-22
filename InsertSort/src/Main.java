import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] dateSize = {10000, 100000};
        for (int n : dateSize) {
            System.out.println("Random Array: ");
            // 查看常数级优化效果
            Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);
            SortHelper.sortTest(arr, "InsertSort");
            SortHelper.sortTest(arr2, "InsertSort2");
            System.out.println();

            // 在数据有序情况下, 比较插入和选择排序
            System.out.println("Order Array: ");
            arr = ArrayGenerator.generatorOrderArray(n);
            arr2 = Arrays.copyOf(arr, arr.length);
            SortHelper.sortTest(arr, "InsertSort2");
            SortHelper.sortTest(arr2, "SelectSort");
            System.out.println();
        }
    }
}