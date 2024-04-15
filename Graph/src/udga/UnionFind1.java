package udga;

public class UnionFind1 implements UF {
    
    // 第一版
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        // 初始每个元素属于不同集合
        for (int i = 0; i < size; i ++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查询元素所属集合.
     *
     * @param p 元素
     * @return 返回元素所属集合
     * @throws IllegalArgumentException 非法元素p
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

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