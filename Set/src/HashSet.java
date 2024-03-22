public class HashSet<E> implements Set<E> {
    private HashTable<E, Object> hashTable;

    public HashSet() {
        hashTable = new HashTable<>();
    }

    @Override
    public void add(E e) {
        hashTable.add(e, null);
    }

    @Override
    public void remove(E e) {
        hashTable.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return hashTable.contains(e);
    }

    @Override
    public int getSize() {
        return hashTable.getSize();
    }

    @Override
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }
}