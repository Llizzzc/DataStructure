package common;

import graph.UndirectedAdjSet;
import graph.Graph;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class GraphDFS2 {
    private Graph graph;
    private boolean visited[];  // 记录访问过的节点
    private ArrayList<Integer> preOrder;

    public GraphDFS2(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        preOrder = new ArrayList<>();
        for (int i = 0; i < graph.getV(); i ++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 深度优先遍历非递归实现.
     *
     * @param v 顶点
     */
    private void dfs(int v) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(v);
        visited[v] = true;
        while (!stack.isEmpty()) {
            int cur = stack.removeLast();
            preOrder.add(cur);
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    stack.addLast(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> getPreOrder() {
        return preOrder;
    }

    public static void main(String[] args) {
        GraphDFS2 dfs = new GraphDFS2(new UndirectedAdjSet("g.txt"));
        System.out.println(dfs.getPreOrder());
    }
}