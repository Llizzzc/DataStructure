package dfs;

import graph.AdjSet;
import graph.Graph;
import java.util.ArrayList;
import java.util.Arrays;

public class HamiltonLoopImprovement {
    private Graph graph;
    private int[] pre;
    private int end = -1;

    public HamiltonLoopImprovement(Graph graph) {
        this.graph = graph;
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        int visited = 0;    // 状态压缩, 使用二进制运算
        dfs(visited, 0, 0, graph.getV());
    }

    /**
     * 寻找哈密尔顿回路(从一个顶点出发, 沿着边行走, 经过每个顶点一次, 再回到出发点).
     *
     * @param v 顶点
     * @return 若存在返回true, 否则返回false
     */
    private boolean dfs(int visited, int v, int parent, int count) {
        visited += (1 << v);
        pre[v] = parent;
        count --;
        if (count == 0 && graph.hasEdge(0, v)) {
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
        visited -= (1 << v);
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
        HamiltonLoopImprovement hamiltonLoopImprovement = new HamiltonLoopImprovement(new AdjSet("g9.txt"));
        System.out.println(hamiltonLoopImprovement.path());
        hamiltonLoopImprovement = new HamiltonLoopImprovement(new AdjSet("g10.txt"));
        System.out.println(hamiltonLoopImprovement.path());
    }
}