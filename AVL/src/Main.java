public class Main {
    public static void main(String[] args) {
        AVLTree<Integer, Object> avlTree = new AVLTree<>();
        // 功能测试
        for (int i = 0; i < 10000; i ++) {
            avlTree.add(i, null);
            if (!avlTree.isBST()) {
                System.out.println("Not a BST.");
            }
            if (!avlTree.isBalanced()) {
                System.out.println("Not balanced.");
            }
        }
        for (int i = 0; i < 10000; i ++) {
            avlTree.remove(i);
            if (!avlTree.isBST()) {
                System.out.println("Not a BST.");
            }
            if (!avlTree.isBalanced()) {
                System.out.println("Not balanced.");
            }
        }
    }
}