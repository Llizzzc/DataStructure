public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4 ,5 ,6};
        SegmentTree<Integer> st = new SegmentTree<>(arr, (a, b) -> a + b);
        System.out.println(st);
        System.out.println(st.query(2, 4));
        st.set(2, 10);
        System.out.println(st.query(2, 4));
    }
}
/*

        21
       /  \
      6    15
     / \   / \
    3   3 9   6
   / \   / \
  1  2  4   5

*/