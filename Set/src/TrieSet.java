public class TrieSet implements Set<String> {
    private Trie trie;

    public TrieSet() {
        trie = new Trie();
    }

    @Override
    public void add(String s) {
        trie.addR(s);
    }

    @Override
    public void remove(String s) {
        trie.removeR(s);
    }

    @Override
    public boolean contains(String s) {
        return trie.containsR(s);
    }

    @Override
    public int getSize() {
        return trie.getSize();
    }

    @Override
    public boolean isEmpty() {
        return trie.isEmpty();
    }
}