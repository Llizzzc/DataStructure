public class UnionFind5 implements UF {

    // 优化查询操作
    private int[] parent;
    private int[] rank; // 存储以i为根的集合的排名

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
        }
        for (int i = 0; i < size; i ++) {
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查询元素所属集合.
     *
     * @param p 元素
     * @return 返回元素所属集合
     * @throws IllegalArgumentException 非法元素p
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];  // 当前节点p指向其父亲的父亲
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}