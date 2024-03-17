/**
 * 基于Trie的集合实现类.
 * <p>实现了集合接口基本操作.</p>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class TrieSet implements Set<String> {
    private Trie trie;

    /**
     * 无参构造方法, 调用Trie相应构造方法.
     */
    public TrieSet() {
        trie = new Trie();
    }

    /**
     * 添加元素.
     *
     * @param s 待添加元素
     */
    @Override
    public void add(String s) {
        trie.addR(s);
    }

    /**
     * 删除元素.
     *
     * @param s 待删除元素
     */
    @Override
    public void remove(String s) {
        trie.removeR(s);
    }

    /**
     * 查找元素.
     *
     * @param s 待查找元素
     * @return 存在为true, 否则false
     */
    @Override
    public boolean contains(String s) {
        return trie.containsR(s);
    }

    /**
     * 获取集合大小.
     *
     * @return 集合大小
     */
    @Override
    public int getSize() {
        return trie.getSize();
    }

    /**
     * 判断集合是否为空.
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        return trie.isEmpty();
    }
}