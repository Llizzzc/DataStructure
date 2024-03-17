import java.util.Arrays;
public class Main2 {
    public static void main(String[] args) {
        // 性能测试
        Integer[] arr = ArrayGenerator.generatorRandomArray(1000000, 1000000);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "MergeSort4");
        SortHelper.sortTest(arr2, "QuickSort5");
        SortHelper.sortTest(arr3, "QuickSort6");
        SortHelper.sortTest(arr4, "HeapSort");
        SortHelper.sortTest(arr5, "HeapSort2");
    }
}