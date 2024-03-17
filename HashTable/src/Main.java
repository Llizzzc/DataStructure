import java.util.ArrayList;
import java.util.Collections;
public class Main {
    public static void main(String[] args) {
        // 性能测试
        String filename = "pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        AVLTree<String, Integer> avlTree = new AVLTree<>();
        BST<String, Integer> bstTree = new BST<>();
        RBTree<String, Integer> rbTree = new RBTree<>();
        HashTable<String, Integer> hashTable = new HashTable<>();
        if(FileOperation.readFile(filename, words)) {
            Collections.sort(words);
            long startTime = System.nanoTime();
            for (String word : words){
                if(avlTree.contains(word))
                    avlTree.set(word, avlTree.get(word) + 1);
                else
                    avlTree.add(word, 1);
            }

            for (String word : words) {
                avlTree.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1.0e9;
            System.out.println("Total different words: " + avlTree.getSize());
            System.out.println("Frequency of PRIDE: " + avlTree.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avlTree.get("prejudice"));
            System.out.println("AVLTree: " + time + " s");
            System.out.println();

            startTime = System.nanoTime();
            for (String word : words){
                if(bstTree.contains(word))
                    bstTree.set(word, bstTree.get(word) + 1);
                else
                    bstTree.add(word, 1);
            }

            for (String word : words) {
                bstTree.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1.0e9;
            System.out.println("Total different words: " + bstTree.getSize());
            System.out.println("Frequency of PRIDE: " + bstTree.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + bstTree.get("prejudice"));
            System.out.println("BSTTree: " + time + " s");
            System.out.println();

            startTime = System.nanoTime();
            for (String word : words){
                if(rbTree.contains(word))
                    rbTree.set(word, rbTree.get(word) + 1);
                else
                    rbTree.add(word, 1);
            }

            for (String word : words) {
                rbTree.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1.0e9;
            System.out.println("Total different words: " + rbTree.getSize());
            System.out.println("Frequency of PRIDE: " + rbTree.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + rbTree.get("prejudice"));
            System.out.println("RBTree: " + time + " s");
            System.out.println();

            startTime = System.nanoTime();
            for (String word : words){
                if(hashTable.contains(word))
                    hashTable.set(word, hashTable.get(word) + 1);
                else
                    hashTable.add(word, 1);
            }

            for (String word : words) {
                hashTable.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1.0e9;
            System.out.println("Total different words: " + hashTable.getSize());
            System.out.println("Frequency of PRIDE: " + hashTable.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + hashTable.get("prejudice"));
            System.out.println("HashTable: " + time + " s");
        }
    }
}