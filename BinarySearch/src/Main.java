public class Main {
    public static void main(String[] args) {
        // 功能测试
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.search(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.search2(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.searchR(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.upper(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.lower(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.upperCeil(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.upperFloor(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.lowerCeil(arr, i));
        }
        System.out.println();
        for (int i = 0; i <= 6; i ++) {
            System.out.printf("%d ", BinarySearch.lowerFloor(arr, i));
        }
    }
}