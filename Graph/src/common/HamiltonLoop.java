package common;

import graph.UndirectedAdjSet;
import graph.Graph;
import java.util.ArrayList;
import java.util.Arrays;

public class HamiltonLoop {
    private Graph graph;
    private boolean visited[];  // 记录访问过的节点
    private int[] pre;
    private int end = -1;

    public HamiltonLoop(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        dfs(0, 0, graph.getV());
    }

    /**
     * 寻找哈密尔顿回路(从一个顶点出发, 沿着边行走, 经过每个顶点一次, 再回到出发点).
     *
     * @param v 顶点
     * @return 若存在返回true, 否则返回false
     */
    private boolean dfs(int v, int parent, int count) {
        visited[v] = true;
        pre[v] = parent;
        count --;
        if (count == 0 && graph.hasEdge(0, v)) {
            end = v;
            return true;
        }
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, count)) {
                    return true;
                }
            }
        }
        visited[v] = false;
        return false;
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) {
            return res;
        }
        int cur = end;
        while (cur != 0) {
            res.addFirst(cur);
            cur = pre[cur];
        }
        res.addFirst(0);
        return res;
    }

    public static void main(String[] args) {
        HamiltonLoop hamiltonLoop = new HamiltonLoop(new UndirectedAdjSet("g9.txt"));
        System.out.println(hamiltonLoop.path());
        hamiltonLoop = new HamiltonLoop(new UndirectedAdjSet("g10.txt"));
        System.out.println(hamiltonLoop.path());
    }
}