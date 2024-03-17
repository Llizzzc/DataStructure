public class Main {
    public static void main(String[] args) {
        // 功能测试
        Trie trie = new Trie();
        trie.addR("dell");
        trie.addR("dellLzc");
        trie.addR("lee");
        System.out.println(trie.containsR("dell"));
        System.out.println(trie.containsR("dellLzc"));
        System.out.println(trie.isPrefixR("de"));
        trie.removeR("dellLzc");
        System.out.println(trie.containsR("dell"));
        System.out.println(trie.containsR("dellLzc"));

    }
}