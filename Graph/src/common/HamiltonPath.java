package common;

import graph.UndirectedAdjSet;
import graph.Graph;
import java.util.ArrayList;
import java.util.Arrays;

public class HamiltonPath {
    private Graph graph;
    private int s;
    private boolean visited[];  // 记录访问过的节点
    private int[] pre;
    private int end = -1;

    public HamiltonPath(Graph graph, int s) {
        this.graph = graph;
        graph.validate(s);
        this.s = s;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        dfs(s, s, graph.getV());
    }

    /**
     * 寻找哈密尔顿路径(从一个顶点出发, 沿着边行走, 经过每个顶点一次).
     *
     * @param v 顶点
     * @return 若存在返回true, 否则返回false
     */
    private boolean dfs(int v, int parent, int count) {
        visited[v] = true;
        pre[v] = parent;
        count --;
        if (count == 0) {
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
        while (cur != s) {
            res.addFirst(cur);
            cur = pre[cur];
        }
        res.addFirst(s);
        return res;
    }

    public static void main(String[] args) {
        HamiltonPath hamiltonPath = new HamiltonPath(new UndirectedAdjSet("g11.txt"), 0);
        System.out.println(hamiltonPath.path());
        hamiltonPath = new HamiltonPath(new UndirectedAdjSet("g11.txt"), 1);
        System.out.println(hamiltonPath.path());
    }
}