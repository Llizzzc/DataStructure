import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 功能测试
        Array<Integer> arr = new Array<>();
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(1);
        arr.addLast(3);
        arr.addLast(2);
        arr.addLast(1);
        arr.addLast(1);
        System.out.println(arr);
        System.out.println(arr.get(4));
        arr.set(5, 4);
        System.out.println(arr);
        System.out.println(Arrays.toString(arr.findAll(1)));
        arr.findDelete(2);
        System.out.println(arr);
        arr.add(100, 1);
        System.out.println(arr);
        arr.addFirst(-1);
        System.out.println(arr);
        arr.delete(2);
        System.out.println(arr);
        arr.deleteFirst();
        System.out.println(arr);
        for (int i = 0; i < 9; i++) {
            arr.addLast(9);
            System.out.println(arr);
        }
        arr.findDeleteAll(9);
        System.out.println(arr);
        arr.findDeleteAll(1);
        System.out.println(arr);
        System.out.println();

        Array<Student> sArr = new Array<>();
        sArr.addLast(new Student("dell", 12));
        sArr.addLast(new Student("lee", 23));
        System.out.println(sArr);
    }
}