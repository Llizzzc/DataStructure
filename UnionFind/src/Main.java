import java.util.Random;
public class Main {
    /**
     * 测试并查集操作耗时.
     *
     * @param uf 并查集
     * @param m 操作树
     * @return 操作耗时
     */
    private static double ufTest(UF uf, int m) {

        Random r = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < m; i ++) {
            int p = r.nextInt(uf.getSize());
            int q = r.nextInt(uf.getSize());
            uf.unionElements(p, q);
        }

        for (int i = 0; i < m; i ++) {
            int p = r.nextInt(uf.getSize());
            int q = r.nextInt(uf.getSize());
            uf.isConnected(p, q);
        }
        long end = System.nanoTime();
        return (end - start) / 1.0e9;
    }

    public static void main(String[] args) {
        // 性能测试
        int size = 100000;
        int m = 100000;
        UnionFind1 uf1 = new UnionFind1(size);
        UnionFind2 uf2 = new UnionFind2(size);
        UnionFind3 uf3 = new UnionFind3(size);
        UnionFind4 uf4 = new UnionFind4(size);
        UnionFind5 uf5 = new UnionFind5(size);
        UnionFind6 uf6 = new UnionFind6(size);
        System.out.println("uf1: " + ufTest(uf1, m) + " s");
        System.out.println("uf2: " + ufTest(uf2, m) + " s");
        System.out.println("uf3: " + ufTest(uf3, m) + " s");
        System.out.println("uf4: " + ufTest(uf4, m) + " s");
        System.out.println("uf5: " + ufTest(uf5, m) + " s");
        System.out.println("uf6: " + ufTest(uf6, m) + " s");
    }
}