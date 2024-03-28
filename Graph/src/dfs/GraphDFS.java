package dfs;

import graph.AdjSet;
import graph.Graph;
import java.util.ArrayList;

public class GraphDFS {
    private Graph graph;
    private boolean visited[];  // 记录访问过的节点
    private ArrayList<Integer> preOrder;
    private ArrayList<Integer> postOrder;

    public GraphDFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        preOrder = new ArrayList<>();
        postOrder = new ArrayList<>();
        for (int i = 0; i < graph.getV(); i ++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 深度优先遍历.
     *
     * @param v 顶点
     */
    private void dfs(int v) {
        visited[v] = true;
        preOrder.add(v);
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        postOrder.add(v);
    }

    public Iterable<Integer> getPreOrder() {
        return preOrder;
    }

    public Iterable<Integer> getPostOrder() {
        return postOrder;
    }

    public static void main(String[] args) {
        GraphDFS dfs = new GraphDFS(new AdjSet("g.txt"));
        System.out.println(dfs.getPreOrder());
        System.out.println(dfs.getPostOrder());
    }
}