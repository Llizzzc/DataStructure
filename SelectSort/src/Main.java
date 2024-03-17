import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 测试功能
        Student[] students = {new Student("bob", 11, 92),
                new Student("alice", 12, 85), new Student("dell", 12, 77)};
        SelectSort.sort(students);
        System.out.println(Arrays.toString(students));

        // 测试性能
        int[] dateSize = {10000, 100000};
        for (int n : dateSize) {
            Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
            SortHelper.sortTest(arr, "SelectSort2");
        }
    }
}