package bfs;

import graph.AdjSet;
import graph.Graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class GraphBFS {

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v ++) {
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    /**
     * 广度优先遍历.
     *
     * @param v 顶点
     */
    private void bfs(int v) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = true;
        while (!queue.isEmpty()) {
            int t = queue.removeFirst();
            order.add(t);
            for (int w : graph.adj(t)) {
               if (!visited[w]) {
                   queue.addLast(w);
                   visited[w] = true;
               }
            }
        }
    }

    public Iterable<Integer> getBFS() {
        return order;
    }

    public static void main(String[] args) {
        GraphBFS graphBFS = new GraphBFS(new AdjSet("g.txt"));
        System.out.println(graphBFS.getBFS());
    }
}