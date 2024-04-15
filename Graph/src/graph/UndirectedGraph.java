package graph;

public interface UndirectedGraph extends Graph {

    /**
     * 获取顶点v的度数.
     *
     * @param v 顶点
     * @return 返回顶点v的度数
     */
    int degree(int v);
}
