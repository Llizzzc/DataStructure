import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        // 性能测试
        Integer[] arr = ArrayGenerator.generatorRandomArray(10000, 10000);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);
        Integer[] arr6 = Arrays.copyOf(arr, arr.length);
        Integer[] arr7 = Arrays.copyOf(arr, arr.length);
        Integer[] arr8 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "ShellSort");
        SortHelper.sortTest(arr2, "ShellSort2");
        SortHelper.sortTest(arr3, "InsertSort");
        SortHelper.sortTest(arr4, "SelectSort");
        SortHelper.sortTest(arr5, "BubbleSort");
        SortHelper.sortTest(arr6, "HeapSort");
        SortHelper.sortTest(arr7, "MergeSort");
        SortHelper.sortTest(arr8, "QuickSort");
    }
}