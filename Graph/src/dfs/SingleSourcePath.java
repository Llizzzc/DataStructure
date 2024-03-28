package dfs;

import graph.AdjSet;
import graph.Graph;
import java.util.ArrayList;
import java.util.Collections;

public class SingleSourcePath {

    private Graph graph;
    private int s;

    private boolean[] visited;
    private int[] pre;  // 记录父节点

    public SingleSourcePath(Graph graph, int s){
        graph.validate(s);
        this.graph = graph;
        this.s = s;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        dfs(s, s);  // 顶点s的父节点设为s
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        for(int w: graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    /**
     * 判断从顶点s出发是否能够到达顶点t.
     *
     * @param t 顶点
     * @return 若可达返回true, 否则返回false
     */
    public boolean isConnectedTo(int t){
        graph.validate(t);
        return visited[t];
    }

    /**
     * 获取从顶点s至顶点t的单源路径.
     *
     * @param t 顶点
     * @return 返回顶点s至顶点t的单源路径
     */
    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) {
            return res;
        }
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        SingleSourcePath singleSourcePath = new SingleSourcePath(new AdjSet("g.txt"),  0);
        System.out.println("0 -> 6: " + singleSourcePath.path(6));
        System.out.println("0 -> 5: " + singleSourcePath.path(5));
    }
}