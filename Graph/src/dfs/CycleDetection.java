package dfs;

import graph.AdjSet;
import graph.Graph;
import java.util.Arrays;

public class CycleDetection {
    private Graph graph;
    private int[] pre;  // 记录父节点
    private boolean hasCycle;

    public CycleDetection(Graph graph){
        this.graph = graph;
        pre = new int[graph.getV()];
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
        for(int w: graph.adj(v)) {
            if (pre[w] == -1) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if(w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args){
        CycleDetection cycleDetection = new CycleDetection(new AdjSet("g.txt"));
        System.out.println(cycleDetection.hasCycle());
        cycleDetection = new CycleDetection(new AdjSet("g2.txt"));
        System.out.println(cycleDetection.hasCycle());
    }
}