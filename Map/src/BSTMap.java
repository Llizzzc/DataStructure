public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private BST<K, V> bstTree;

    public BSTMap() {
        bstTree = new BST<>();
    }

    @Override
    public void add(K key, V value) {
        bstTree.add(key, value);
    }

    @Override
    public V remove(K key) {
        return bstTree.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return bstTree.contains(key);
    }

    @Override
    public V get(K key) {
        return bstTree.get(key);
    }

    @Override
    public void set(K key, V value) {
        bstTree.set(key, value);
    }

    @Override
    public int getSize() {
        return bstTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bstTree.isEmpty();
    }
}