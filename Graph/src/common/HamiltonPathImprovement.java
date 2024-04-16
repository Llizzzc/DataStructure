package common;

import graph.UndirectedAdjSet;
import graph.Graph;
import java.util.ArrayList;
import java.util.Arrays;

public class HamiltonPathImprovement {
    private Graph graph;
    private int s;
    private int[] pre;
    private int end = -1;

    public HamiltonPathImprovement(Graph graph, int s) {
        this.graph = graph;
        graph.validate(s);
        this.s = s;
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        int visited = 0;    // 状态压缩
        dfs(visited, s, s, graph.getV());
    }

    /**
     * 寻找哈密尔顿路径(从一个顶点出发, 沿着边行走, 经过每个顶点一次).
     *
     * @param v 顶点
     * @return 若存在返回true, 否则返回false
     */
    private boolean dfs(int visited, int v, int parent, int count) {
        visited += 1 << v;
        pre[v] = parent;
        count --;
        if (count == 0) {
            end = v;
            return true;
        }
        for (int w : graph.adj(v)) {
            if ((visited & (1 << w)) == 0) {
                if (dfs(visited, w, v, count)) {
                    return true;
                }
            }
        }
        visited -= 1 << v;
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
        HamiltonPathImprovement hamiltonPathImprovement = new HamiltonPathImprovement(new UndirectedAdjSet("g11.txt"), 0);
        System.out.println(hamiltonPathImprovement.path());
        hamiltonPathImprovement = new HamiltonPathImprovement(new UndirectedAdjSet("g11.txt"), 1);
        System.out.println(hamiltonPathImprovement.path());
    }
}