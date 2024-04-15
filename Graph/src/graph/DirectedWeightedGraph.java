package graph;

public interface DirectedWeightedGraph extends DirectedGraph {

    /**
     * 获取边v-w的权值.
     *
     * @param v 顶点
     * @param w 顶点
     * @return 返回边v-w的权值
     */
    int getWeight(int v, int w);
}