public class Main {
    public static void main(String[] args) {
        // 功能测试
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SQRTDecomposition<Integer> sqrtDecomposition = new SQRTDecomposition<>(arr, (a, b) -> a + b);
        System.out.println(sqrtDecomposition.queryRange(2, 5));
        sqrtDecomposition.update(2, 11);
        System.out.println(sqrtDecomposition.queryRange(2, 5));
    }
}