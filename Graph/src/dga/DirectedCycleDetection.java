package dga;

import graph.DirectedAdjSet;
import graph.DirectedGraph;
import java.util.Arrays;

public class DirectedCycleDetection {
    private DirectedGraph graph;
    private int[] pre;  // 记录父节点
    private boolean hasCycle;
    private boolean[] path;

    public DirectedCycleDetection(DirectedGraph graph){
        this.graph = graph;
        pre = new int[graph.getV()];
        path = new boolean[graph.getV()];
        Arrays.fill(pre, -1);
        for (int v = 0; v < graph.getV(); v ++) {
            if (pre[v] == -1) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    /**
     * 从顶点v开始, 判断图中是否存在环.
     *
     * @param v 顶点
     * @param parent 父顶点
     * @return 若有环返回true, 否则返回false
     */
    private boolean dfs(int v, int parent){
        pre[v] = parent;
        path[v] = true;
        for(int w: graph.adj(v)) {
            if (pre[w] == -1) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if(path[w]) {
                return true;
            }
        }
        path[v] = false;
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args){
        DirectedCycleDetection directedCycleDetection = new DirectedCycleDetection(new DirectedAdjSet("dg.txt"));
        System.out.println(directedCycleDetection.hasCycle());
        directedCycleDetection = new DirectedCycleDetection(new DirectedAdjSet("dg2.txt"));
        System.out.println(directedCycleDetection.hasCycle());
    }
}