/**
 * 图接口.
 */
public interface Graph {

    /**
     * 获取顶点数.
     *
     * @return 顶点数
     */
    int getV();

    /**
     * 获取边数.
     *
     * @return 边数
     */
    int getE();

    /**
     * 判断两个顶点之间是否存在边.
     *
     * @param v 顶点
     * @param w 顶点
     * @return 有边返回true, 否则false
     */
    boolean hasEdge(int v, int w);

    /**
     * 获取与顶点v相邻的顶点.
     *
     * @param v 顶点
     * @return 相邻的顶点
     */
    Iterable<Integer> adj(int v);

    /**
     * 获取顶点v的度数.
     *
     * @param v 顶点
     * @return 顶点v的度数
     */
    int degree(int v);

    /**
     * 判断顶点合法性.
     *
     * @param v 顶点
     */
    void validate(int v);
}