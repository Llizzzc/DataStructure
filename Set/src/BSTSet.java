public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) {
        bst.addR(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.containsR(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }
}