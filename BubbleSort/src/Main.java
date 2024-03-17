import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        // 性能测试
        System.out.println("Random Array:");
        Integer[] arr = ArrayGenerator.generatorRandomArray(10000, 10000);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "BubbleSort");
        SortHelper.sortTest(arr2, "BubbleSort2");
        SortHelper.sortTest(arr3, "BubbleSort3");
        SortHelper.sortTest(arr4, "BubbleSort4");
        System.out.println();
        System.out.println("Order Array:");
        arr = ArrayGenerator.generatorOrderArray(10000);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        arr4 = Arrays.copyOf(arr, arr.length);
        SortHelper.sortTest(arr, "BubbleSort");
        SortHelper.sortTest(arr2, "BubbleSort2");
        SortHelper.sortTest(arr3, "BubbleSort3");
        SortHelper.sortTest(arr4, "BubbleSort4");
    }
}