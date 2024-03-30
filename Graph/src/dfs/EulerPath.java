package dfs;

import graph.AdjSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class EulerPath {
    private AdjSet graph;
    private int s;
    private int count = 0;

    public EulerPath(AdjSet graph, int s) {
        this.graph = graph;
        this.s = s;
    }

    /**
     * 判断是否存在欧拉路径(从一个顶点出发, 沿着边行走, 经过每个边一次).
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
                count ++;
            }
        }
        return count == 2 || count == 0;
    }

    /**
     * 寻找欧拉路径.
     *
     * @return 返回欧拉路径
     */
    public ArrayList<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEuler()) {
            return res;
        } else if (count == 2 && graph.degree(s) % 2 == 0) {
            return res;
        }
        AdjSet g = (AdjSet) graph.clone();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(s);
        while (!stack.isEmpty()) {
            int cur = stack.peekLast();
            if (g.degree(cur) != 0) {
                int w = g.adj(cur).iterator().next();
                g.removeEdge(cur, w);
                stack.addLast(w);
            } else {
                res.addFirst(stack.removeLast());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        EulerPath eulerPath = new EulerPath(new AdjSet("g12.txt"), 0);
        System.out.println(eulerPath.path());
        eulerPath = new EulerPath(new AdjSet("g12.txt"), 1);
        System.out.println(eulerPath.path());

        eulerPath = new EulerPath(new AdjSet("g14.txt"), 2);
        System.out.println(eulerPath.path());
        eulerPath = new EulerPath(new AdjSet("g14.txt"), 1);
        System.out.println(eulerPath.path());
    }
}