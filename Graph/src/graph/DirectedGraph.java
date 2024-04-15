package graph;

public interface DirectedGraph extends Graph {

    /**
     * 获取顶点v的入度.
     *
     * @param v 顶点
     * @return 返回顶点v的入度
     */
    int inDegree(int v);

    /**
     * 获取顶点v的出度
     *
     * @param v 顶点
     * @return 返回顶点v的出度
     */
    int outDegree(int v);
}