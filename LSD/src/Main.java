import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        // 性能测试
        String[] arr = ArrayGenerator.generatorRandomStringArray(1000000, 20);
        String[] arr2 = Arrays.copyOf(arr, arr.length);
        String[] arr3 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "MSDSort");
        SortHelper.sortTest(arr2, "QuickSort6");
        SortHelper.sortTest(arr3, "MergeSort4");
        System.out.println();

        Integer[] arr4 = ArrayGenerator.generatorRandomArray(10000000, 10000000);
        Integer[] arr5 = Arrays.copyOf(arr4, arr4.length);
        Integer[] arr6 = Arrays.copyOf(arr4, arr4.length);
        Integer[] arr7 = Arrays.copyOf(arr4, arr4.length);
        SortHelper.sortTest(arr4, "BucketSort");
        SortHelper.sortTest(arr5, "BucketSort2");
        SortHelper.sortTest(arr6, "QuickSort6");
        SortHelper.sortTest(arr7, "MergeSort4");
    }
}