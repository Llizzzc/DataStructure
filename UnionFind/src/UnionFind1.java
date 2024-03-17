/**
 * 并查集实现类, 第一版.
 * <p>实现基本并查集接口.</p>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class UnionFind1 implements UF {
    private int[] id;

    /**
     * 包含size的构造方法.
     *
     * @param size 容量
     */
    public UnionFind1(int size) {
        id = new int[size];
        // 初始每个元素属于不同集合
        for (int i = 0; i < size; i ++) {
            id[i] = i;
        }
    }

    /**
     * 查询并查集大小.
     *
     * @return 并查集大小
     */
    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查询元素所属集合.
     *
     * @param p 元素
     * @return 元素所属集合
     * @throws IllegalArgumentException 非法元素p
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }

    /**
     * 判断两个元素是否属于一个集合.
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
     * 连接两个元素.
     *
     * @param p 元素p
     * @param q 元素q
     */
    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qid = find(q);
        // 如果已经属于同一个集合
        if (pId == qid) {
            return;
        }
        for (int i = 0; i < id.length; i ++) {
            if (id[i] == pId) {
                id[i] = qid;
            }
        }
    }
}