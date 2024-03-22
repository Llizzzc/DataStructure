public class UnionFind3 implements UF {

    // 继续优化合并操作
    private int[] parent;
    private int[] sz; // 存储以i为根的集合中的元素个数

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        // 每个元素初始指向自己
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
        }
        // 以每个元素为根的集合初始个数为1
        for (int i = 0; i < size; i ++) {
            sz[i] = 1;
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
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}