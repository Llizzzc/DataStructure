import java.util.ArrayList;
public class Main {
    /**
     * 测试不同映射实现类的耗时.
     *
     * @param map 映射实现类
     * @param filename 文件名
     * @return 操作耗时
     */
    private static double testMap(Map<String, Integer> map, String filename){
        double res = 0.0;
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());
            long startTime = System.nanoTime();
            for (String word : words){
                if(map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            long endTime = System.nanoTime();
            res = (endTime - startTime) / 1.0e9;
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        return res;
    }

    public static void main(String[] args) {
        // 性能测试
        String filename = "pride-and-prejudice.txt";

        Map<String, Integer> bstMap = new BSTMap<>();
        double time = testMap(bstMap, filename);
        System.out.println("BSTMap: " + time + " s");
        System.out.println();

        Map<String, Integer> linkedListMap = new LinkedListMap<>();
        time = testMap(linkedListMap, filename);
        System.out.println("LinkedListMap: " + time + " s");
        System.out.println();

        Map<String, Integer> avlMap = new AVLMap<>();
        time = testMap(avlMap, filename);
        System.out.println("AVLMap: " + time + " s");
        System.out.println();

        Map<String, Integer> hashMap = new HashMap<>();
        time = testMap(hashMap, filename);
        System.out.println("HashMap: " + time + " s");
    }
}