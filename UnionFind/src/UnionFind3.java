/**
 * 并查集实现类, 第三版, 继续优化合并操作.
 * <p>实现基本并查集接口.</p>
 *
 * @author lzc
 * @version 3.0
 * @version jdk17
 */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz; // 存储以i为根的集合中的元素个数

    /**
     * 包含size的构造方法.
     *
     * @param size 容量
     */
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

    /**
     * 查询并查集大小.
     *
     * @return 并查集大小
     */
    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查询元素所属集合.
     *
     * @param p 元素
     * @return 元素所属集合
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

    /**
     * 判断两个元素是否属于一个集合, O(h).
     *
     * @param p 元素p
     * @param q 元素q
     * @return 属于一个集合为true, 否则false
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接两个元素, O(h).
     *
     * @param p 元素p
     * @param q 元素q
     */
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