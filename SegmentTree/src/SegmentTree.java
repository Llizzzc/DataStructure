public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merge<E> merge;

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.merge = merge;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i ++) {
            data[i] = arr[i];
        }
        // 将arr.length长度的数组整理成线段树需要4倍大小的空间
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 递归函数, 在treeIndex的位置, 创建表示区间[l, r]的线段树.
     *
     * @param treeIndex 根索引
     * @param l 左边界
     * @param r 右边界
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归到底, 叶子节点
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 先创建左右子树
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        // 进行合并操作
        tree[treeIndex] = merge.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 获取左孩子索引.
     *
     * @param index 索引
     * @return 返回左孩子索引
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 获取右孩子索引.
     *
     * @param index 索引
     * @return 返回右孩子索引
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 查询某个区间的值.
     *
     * @param queryL 左边界
     * @param queryR 右边界
     * @return 返回区间的值
     * @throws IllegalArgumentException 查询索引不合法
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
        queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Illegal index.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为根的线段树中[l, r]范围, 搜索[queryL, queryR]的值.
     *
     * @param treeIndex 根索引
     * @param l treeIndex的左边界
     * @param r treeIndex的右边界
     * @param queryL 左边界
     * @param queryR 右边界
     * @return 返回区间的值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 如果查询的区间就是treeIndex表示的区间
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        // 如果左边界在右子树范围
        if (queryL >= mid + 1) {
            return query(rightChild, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) { // 右边界在左子树范围
            return query(leftChild, l, mid, queryL, queryR);
        }
        // 如果分散在左右孩子中
        E leftResult = query(leftChild, l, mid, queryL, mid);
        E rightResult = query(rightChild, mid + 1, r, mid + 1, queryR);
        return merge.merge(leftResult, rightResult);
    }

    /**
     * 更新线段树.
     *
     * @param index 索引
     * @param e 新元素
     * @throws IllegalArgumentException 索引越界
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Illegal index.");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在以treeIndex为根的线段树中更新index位置的值为e.
     *
     * @param treeIndex 根索引
     * @param l treeIndex的左边界
     * @param r treeIndex的右边界
     * @param index 索引
     * @param e 新元素
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        // 递归到底
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (index >= mid + 1) {
            set(rightChild, mid + 1, r, index, e);
        } else {
            set(leftChild, l, mid, index, e);
        }

        tree[treeIndex] = merge.merge(tree[leftChild], tree[rightChild]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}