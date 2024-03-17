import java.util.ArrayList;

public class Main {
    /**
     * 测试集合操作耗时.
     *
     * @param set 集合实现类
     * @param filename 文件名
     * @return 操作耗时
     */
    private static double testSet(Set<String> set, String filename){
        double res = 0.0;
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());
            long startTime = System.nanoTime();
            for (String word : words)
                set.add(word);
            long endTime = System.nanoTime();
            res = (endTime - startTime) / 1.0e9;
            System.out.println("Total different words: " + set.getSize());
        }
        return res;
    }

    public static void main(String[] args) {
        // 不同实现类的性能测试
        String filename = "pride-and-prejudice.txt";
        Set<String> bstSet = new BSTSet<>();
        double time = testSet(bstSet, filename);
        System.out.println("BSTSet: " + time + " s");
        System.out.println();

        Set<String> linkedListSet = new LinkedListSet<>();
        time = testSet(linkedListSet, filename);
        System.out.println("LinkedListSet: " + time + " s");
        System.out.println();

        Set<String> trieSet = new TrieSet();
        time = testSet(trieSet, filename);
        System.out.println("TrieSet: " + time + " s");
        System.out.println();

        Set<String> avlSet = new AVLSet<>();
        time = testSet(avlSet, filename);
        System.out.println("AVLSet: " + time + " s");
        System.out.println();

        Set<String> hashSet = new HashSet<>();
        time = testSet(hashSet, filename);
        System.out.println("HashSet: " + time + " s");
    }
}