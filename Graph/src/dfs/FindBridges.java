package dfs;

import graph.AdjSet;
import graph.Graph;
import java.util.ArrayList;

public class FindBridges {
    private Graph graph;
    private boolean visited[];  // 记录访问过的节点
    private int[] ord;  // 记录节点访问顺序
    private int[] low;  // 记录该节点能够到达的顺序最靠前的节点
    private int count;
    private ArrayList<Edge> res = new ArrayList<>();

    public class Edge {
        private int v;
        private int w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return v + " - " + w;
        }
    }

    public FindBridges(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        ord = new int[graph.getV()];
        low = new int[graph.getV()];
        for (int i = 0; i < graph.getV(); i ++) {
            if (!visited[i]) {
                dfs(i, i);
            }
        }
    }

    /**
     * 寻找图中的桥(删掉这条边, 图中连通分量会发生变化).
     *
     * @param v 顶点
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = count;
        low[v] = ord[v];
        count ++;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]) {
                    res.add(new Edge(v, w));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> result() {
        return res;
    }

    public static void main(String[] args) {
        FindBridges findBridges = new FindBridges(new AdjSet("g5.txt"));
        System.out.println(findBridges.result());

        findBridges = new FindBridges(new AdjSet("g6.txt"));
        System.out.println(findBridges.result());

        findBridges = new FindBridges(new AdjSet("g7.txt"));
        System.out.println(findBridges.result());
    }
}