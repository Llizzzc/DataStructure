package bfs;

import graph.AdjSet;
import graph.Graph;

public class AllPath2 {

    private Graph graph;
    private SingleSourcePathImprovement2[] path;

    public AllPath2(Graph graph) {
        this.graph = graph;
        path = new SingleSourcePathImprovement2[graph.getV()];
        for (int v = 0; v < graph.getV(); v ++) {
            path[v] = new SingleSourcePathImprovement2(graph, v);
        }
    }

    /**
     * 判断顶点s与顶点t是否连通.
     *
     * @param s 顶点
     * @param t 顶点
     * @return 若连通返回true, 否则返回false
     */
    public boolean isConnectTo(int s, int t) {
        graph.validate(s);
        graph.validate(t);
        return path[s].isConnectedTo(t);
    }

    /**
     * 顶点s至顶点t的路径.
     *
     * @param s 顶点
     * @param t 顶点
     * @return 返回顶点s至顶点t的路径
     */
    public Iterable<Integer> path(int s, int t) {
        graph.validate(s);
        graph.validate(t);
        return path[s].path(t);
    }

    public static void main(String[] args) {
        AllPath2 allPath2 = new AllPath2(new AdjSet("g.txt"));
        System.out.println("0 -> 6: " + allPath2.path(0, 6));
        System.out.println("1 -> 4: " + allPath2.path(1, 4));
        System.out.println("3 -> 6: " + allPath2.path(3, 6));
    }
}