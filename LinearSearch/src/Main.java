public class Main {
    public static void main(String[] args) {
        // 查找测试
        Student[] students = {
                new Student("alice", 202111),
                new Student("bob", 202112),
                new Student("dell", 202113)};
        Student bob1 = new Student("bob", 202112);
        Student bob2 = new Student("bob", 202114);
        System.out.println(LinearSearch.search(students, bob1));
        System.out.println(LinearSearch.search(students, bob2));

        // 性能测试
        int[] size = {1000000, 10000000};
        for (int n : size) {
            Integer[] arr = ArrayGenerator.generatorOrderArray(n);
            long start = System.nanoTime();
            for (int k = 0; k < 100; k++) {
                LinearSearch.search(arr, n); // 查找最坏情况
            }
            long end = System.nanoTime();
            double time = (end - start) / 1.0e9;
            System.out.printf("%d: time = %f s\n", n, time);
        }
    }
}