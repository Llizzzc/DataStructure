package dfs;

import graph.AdjSet;
import java.util.ArrayList;

public class EulerLoopR {
    private AdjSet graph;
    private ArrayList<Integer> path = new ArrayList<>();

    public EulerLoopR(AdjSet graph) {
        this.graph = (AdjSet) graph.clone();
        if (!hasEuler()) {
            return;
        }
        path(0);
    }

    /**
     * 判断是否存在欧拉回路(从一个顶点出发, 沿着边行走, 经过每个边一次, 再回到出发点).
     *
     * @return 若存在返回true, 否则返回false
     */
    private boolean hasEuler() {
        CC cc = new CC(graph);
        if (cc.getCount() > 1) {
            return false;
        }
        for (int v = 0; v < graph.getV(); v ++) {
            if (graph.degree(v) % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 寻找欧拉回路, 递归.
     */
    public void path(int v) {
        if (graph.degree(v) == 0) {
            path.addFirst(v);
        } else {
            for (; graph.degree(v) != 0; ) {
                int w = graph.adj(v).iterator().next();
                graph.removeEdge(w, v);
                path(w);
            }
            path.addFirst(v);
        }
    }

    public Iterable<Integer> result() {
        return path;
    }

    public static void main(String[] args) {
        EulerLoopR eulerLoopr = new EulerLoopR(new AdjSet("g12.txt"));
        System.out.println(eulerLoopr.result());
        eulerLoopr = new EulerLoopR(new AdjSet("g13.txt"));
        System.out.println(eulerLoopr.result());
    }
}