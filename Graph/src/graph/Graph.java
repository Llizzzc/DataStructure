package graph;

public interface Graph extends Cloneable {

    /**
     * 获取顶点数.
     *
     * @return 返回顶点数
     */
    int getV();

    /**
     * 获取边数.
     *
     * @return 返回边数
     */
    int getE();

    /**
     * 判断两个顶点之间是否存在边.
     *
     * @param v 顶点
     * @param w 顶点
     * @return 若有边返回true, 否则返回false
     */
    boolean hasEdge(int v, int w);

    /**
     * 获取与顶点v相邻的顶点.
     *
     * @param v 顶点
     * @return 返回相邻的顶点
     */
    Iterable<Integer> adj(int v);

    /**
     * 判断顶点合法性.
     *
     * @param v 顶点
     */
    void validate(int v);

    /**
     * 删除边v - w.
     *
     * @param v 顶点
     * @param w 顶点
     */
    void removeEdge(int v, int w);
}