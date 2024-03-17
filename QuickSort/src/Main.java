import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 不同版本性能测试
        System.out.println("Random Array:");
        Integer[] arr = ArrayGenerator.generatorRandomArray(1000000, 1000000);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "QuickSort4");
        SortHelper.sortTest(arr2, "QuickSort5");
        SortHelper.sortTest(arr3, "QuickSort6");
        System.out.println();

        System.out.println("Order Array:");
        arr = ArrayGenerator.generatorOrderArray(10000);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "QuickSort2");
        SortHelper.sortTest(arr2, "QuickSort5");
        SortHelper.sortTest(arr3, "QuickSort6");
        System.out.println();

        System.out.println("Special Array:");
        arr = ArrayGenerator.generatorRandomArray(25000, 1);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "QuickSort4");
        SortHelper.sortTest(arr2, "QuickSort5");
        SortHelper.sortTest(arr3, "QuickSort6");
        System.out.println();
    }
}