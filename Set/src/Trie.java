import java.util.TreeMap;

/**
 * 字典树.
 * <p>包括添加, 删除等操作.</p>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.util.TreeMap
 */
public class Trie {
    /**
     * 内部节点类.
     */
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        /**
         * 包括isWork的构造方法.
         *
         * @param isWord 是否是单词
         */
        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        /**
         * 无参数构造方法, isWord为false.
         */
        public Node() {
            this.isWord = false;
            this.next = new TreeMap<>();
        }
    }
    private Node root;
    private int size;

    /**
     * 无参构造方法.
     */
    public Trie() {
        root = new Node();
        size = 0;
    }

    /**
     * 获得存储的单词数量.
     *
     * @return 单词数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断字典树是否为空.
     *
     * @return 空为true, 否则false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加单词.
     *
     * @param word 单词
     */
    public void add(String word) {
        Node cur = root;
        // 每次取出一个字符
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            // 如果不存在对于c的映射
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 如果该单词不存在
        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 添加单词, 递归.
     *
     * @param word 待添加单词.
     */
    public void addR(String word) {
        root = addR(root, 0, word);
    }

    /**
     * 在以node为头的字典树尝试添加单词.
     *
     * @param node 以node为头的字典树
     * @param index 索引
     * @param word 待添加单词
     */
    private Node addR(Node node, int index, String word) {
        // 已经添加完最后一个字符了
        if (index == word.length()) {
            if (!node.isWord) {
                node.isWord = true;
                size ++;
            }
            return node;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            node.next.put(c, new Node());
        }
        // 将添加完之后返回的节点更新
        node.next.put(c, addR(node.next.get(c), index + 1, word));
        return node;
    }

    /**
     * 查询单词.
     *
     * @param word 待查找单词
     * @return 存在为true, 否则false
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询单词, 递归.
     *
     * @param word 待查找单词
     * @return 存在为true, 否则false
     */
    public boolean containsR(String word) {
        return containsR(root, 0, word);
    }

    /**
     * 在以node为头的字典树尝试查询单词.
     *
     * @param node 以node为头的字典树
     * @param index 索引
     * @param word 待查找单词
     */
    private boolean containsR(Node node, int index, String word) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        }
        return containsR(node.next.get(c), index + 1, word);
    }

    /**
     * 查询str是否为前缀.
     *
     * @param str 前缀
     * @return 存在为true, 否则false
     */
    public boolean isPrefix(String str) {
        Node cur = root;
        for (int i = 0; i < str.length(); i ++) {
            char c = str.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    /**
     * 查询str是否为前缀, 递归.
     *
     * @param str 前缀
     * @return 存在为true, 否则false
     */
    public boolean isPrefixR(String str) {
        return isPrefixR(root, 0, str);
    }

    /**
     * 在以node为头的字典树尝试查询前缀.
     *
     * @param node 以node为头的字典树
     * @param index 索引
     * @param str 前缀
     */
    private boolean isPrefixR(Node node, int index, String str) {
        if (index == str.length()) {
            return true;
        }
        char c = str.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        }
        return isPrefixR(node.next.get(c), index + 1, str);
    }

    /**
     * 删除单词, 递归.
     *
     * @param word 待删除单词
     */
    public void removeR(String word) {
        root = removeR(root, word, 0);
    }

    /**
     * 在以node为头的字典树尝试删除单词.
     *
     * @param node 以node为头的字典树
     * @param index 索引
     * @param word 待删除单词
     */
    private Node removeR(Node node, String word, int index) {
        if (index == word.length()) {
            // 该节点为一个单词
            if (node.isWord) {
                size --;
                node.isWord = false;
                return node.next.size() == 0? null: node;
            }
            return node;
        }
        char c = word.charAt(index);
        // 不存在c的映射
        if (node.next.get(c) == null) {
            return node;
        }
        // c的映射为空时, 删除c的映射
        if (removeR(node.next.get(c), word, index + 1) == null) {
            node.next.remove(c);
            // 如果该节点不存在任何映射, 且不代表单词, 返回null
            if (node.next.size() == 0 && !node.isWord) {
                return null;
            }
        }
        return node;
    }
}