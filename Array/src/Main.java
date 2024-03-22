public class Main {
    public static void main(String[] args) {
        // 功能测试
        Array<Integer> arr = new Array<>();
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        arr.addLast(2);
        System.out.println(arr);
        System.out.println();

        System.out.println(arr.find(2));
        System.out.println(arr.findAll(2));
        System.out.println();

        for (int i = 0; i < 5; i++) {
            arr.addLast(9);
        }
        System.out.println(arr);
        System.out.println();

        arr.findDeleteAll(9);
        System.out.println(arr);
        System.out.println();

        arr.findDelete(2);
        System.out.println(arr);
        System.out.println();

        Array<Student> sArr = new Array<>();
        sArr.addLast(new Student("dell", 12));
        sArr.addLast(new Student("lee", 23));
        System.out.println(sArr);
    }
}