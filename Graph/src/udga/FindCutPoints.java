package udga;

import graph.UndirectedAdjSet;
import graph.UndirectedGraph;
import java.util.HashSet;

public class FindCutPoints {
    private UndirectedGraph graph;
    private boolean visited[];  // 记录访问过的节点
    private int[] ord;  // 记录节点访问顺序
    private int[] low;  // 记录该节点能够到达的顺序最靠前的节点
    private int count;
    private HashSet<Integer> res = new HashSet<>();

    public FindCutPoints(UndirectedGraph graph) {
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
     * 寻找图中的割点(删掉这个顶点, 图中连通分量会发生变化).
     *
     * @param v 顶点
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = count;
        low[v] = ord[v];
        count ++;
        int child = 0;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (v != parent && low[w] >= ord[v]) {
                    res.add(v);
                }
                child ++;
                if (v == parent && child > 1) {
                    res.add(v);
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], ord[w]);
            }
        }
    }

    public HashSet<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        FindCutPoints findBridges = new FindCutPoints(new UndirectedAdjSet("g5.txt"));
        System.out.println(findBridges.result());

        findBridges = new FindCutPoints(new UndirectedAdjSet("g6.txt"));
        System.out.println(findBridges.result());

        findBridges = new FindCutPoints(new UndirectedAdjSet("g8.txt"));
        System.out.println(findBridges.result());
    }
}