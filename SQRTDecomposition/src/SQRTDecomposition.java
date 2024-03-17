/**
 * SQRT分解实现.
 * <p>包括查询, 更新等操作.</p>
 *
 * @param <E> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class SQRTDecomposition<E> {

    private E[] data, blocks;
    private Merge<E> merge; // 合并操作

    private int N;  // 元素个数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    /**
     * 包括arr, merge的构造方法.
     *
     * @param arr 数组
     * @param merge 合并操作
     * @throws IllegalArgumentException 传入数组不能为空
     */
    public SQRTDecomposition(E[] arr, Merge<E> merge) {
        this.merge = merge;
        N = arr.length;
        B = (int)Math.sqrt(N);  // 每组有根号N个元素
        Bn = N / B + (N % B > 0? 1: 0);
        if (N == 0) {
            throw new IllegalArgumentException("The size of arr is zero.");
        }
        data = (E[])new Object[N];
        for (int i = 0; i < N; i ++) {
            data[i] = arr[i];
        }
        // 对每个组进行统计
        blocks = (E[])new Object[Bn];
        for (int i = 0; i < N; i ++) {
            if (i % B == 0) {
                blocks[i / B] = data[i];     // 将blocks[i]初始化为每组第一个元素, 然后进行merge
            } else {
                blocks[i / B] = merge.merge(arr[i], blocks[i / B]);
            }
        }
    }

    /**
     * 区间查询.
     *
     * @param x 左边界
     * @param y 右边界
     * @return 区间值
     * @throws IllegalArgumentException 索引不合法
     */
    public E queryRange(int x, int y) {
        if (x < 0 || x >= N || y >= N || x > y) {
            throw new IllegalArgumentException("Illegal index.");
        }
        E res = data[x];
        int bStart = x / B, bEnd = y / B;
        // 如果索引范围为同一个组
        if (bStart == bEnd) {
            for (int i = x + 1; i <= y; i ++) {
                res = merge.merge(res, data[i]);
            }
            return res;
        }

        // 如果不是同一个组
        // 先merge前一部分
        for (int i = x + 1; i < (bStart + 1) * B; i ++) {
            res = merge.merge(res, data[i]);
        }
        // merge中间部分
        for (int b = bStart + 1; b < bEnd; b ++) {
            res = merge.merge(res, blocks[b]);
        }
        // merge后一部分
        for (int i = bEnd * B; i <= y; i ++) {
            res = merge.merge(res, data[i]);
        }
        return res;
    }

    /**
     * 单个位置的更新.
     *
     * @param index 更新位置
     * @param var 新值
     * @throws IllegalArgumentException 索引不合法
     */
    public void update(int index, E var) {
        if (index < 0 || index >= N) {
            throw new IllegalArgumentException("Index out of range.");
        }
        int b = index / B;
        data[index] = var;
        // 重新统计该组
        blocks[b] = data[b * B];
        for (int i = b * B + 1; i < Math.min((b + 1) * B, N);  i ++) {
            blocks[b] = merge.merge(blocks[b], data[i]);
        }
    }
}