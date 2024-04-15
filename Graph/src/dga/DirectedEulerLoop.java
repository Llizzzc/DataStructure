package dga;

import graph.DirectedAdjSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DirectedEulerLoop {
    private DirectedAdjSet graph;

    public DirectedEulerLoop(DirectedAdjSet graph) {
        this.graph = graph;
    }

    /**
     * 判断是否存在欧拉回路(从一个顶点出发, 沿着边行走, 经过每个边一次, 再回到出发点).
     *
     * @return 若存在返回true, 否则返回false
     */
    private boolean hasEuler() {
        SCC scc = new SCC(graph);
        if (scc.getCount() > 1) {
            return false;
        }
        for (int v = 0; v < graph.getV(); v ++) {
            if (graph.inDegree(v) != graph.outDegree(v)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 寻找欧拉回路.
     *
     * @return 返回欧拉回路
     */
    public ArrayList<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEuler()) {
            return res;
        }
        DirectedAdjSet g = (DirectedAdjSet) graph.clone();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        while (!stack.isEmpty()) {
            int cur = stack.peekLast();
            if (g.outDegree(cur) != 0) {
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
        DirectedEulerLoop eulerLoop = new DirectedEulerLoop(new DirectedAdjSet("dg.txt"));
        System.out.println(eulerLoop.path());
        eulerLoop = new DirectedEulerLoop(new DirectedAdjSet("dg3.txt"));
        System.out.println(eulerLoop.path());
    }
}