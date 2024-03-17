public class Main {
    public static void main(String[] args) {
        // 功能测试
        BST<Integer> bst = new BST<>();
        Integer[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.addR(num);
        }

        System.out.println(bst);
        System.out.println(bst.containsR(99));
        System.out.println(bst.containsR(5));
        System.out.println();

        bst.preOrderR();
        System.out.println();
        bst.inOrderR();
        System.out.println();
        bst.postOrderR();
        System.out.println();
        bst.levelOrder();
        System.out.println();

        System.out.println(bst.maximumR());
        System.out.println(bst.minimumR());
        System.out.println();

        System.out.println(bst.floor(7) + " " + bst.ceil(7));
        System.out.println(bst.floor(3) + " " + bst.ceil(3));
        System.out.println();

        bst.removeMaxR();
        bst.removeMinR();
        System.out.println(bst.maximumR());
        System.out.println(bst.minimumR());
        System.out.println();

        System.out.println(bst);
        bst.remove(5);
        System.out.println(bst);
    }
}
