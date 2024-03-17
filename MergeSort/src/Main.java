import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 性能测试
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);
        Integer[] arr6 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "MergeSort");
        SortHelper.sortTest(arr2, "MergeSort2");
        SortHelper.sortTest(arr3, "MergeSort3");
        SortHelper.sortTest(arr4, "MergeSort4");
        SortHelper.sortTest(arr5, "MergeSortBU");
        SortHelper.sortTest(arr6, "MergeSortBU2");
    }
}