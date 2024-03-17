/**
 * 并查集实现类, 第六版, 继续优化查询操作.
 * <p>实现基本并查集接口.</p>
 *
 * @author lzc
 * @version 6.0
 * @version jdk17
 */
public class UnionFind6 implements UF {

    private int[] parent;
    private int[] rank;

    /**
     * 包含size的构造方法.
     *
     * @param size 容量
     */
    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
        }
        for (int i = 0; i < size; i ++) {
            rank[i] = 1;
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
        if (p != parent[p]) {
            parent[p] = find(parent[p]);    // 当前节点直接指向根节点
        }
        return parent[p];
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