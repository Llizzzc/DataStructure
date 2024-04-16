package udga;

import graph.UndirectedAdjSet;
import graph.Graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class BipartiteDetection2 {
    private Graph graph;
    private int[] colors;   // -1无色, 0红色, 1绿色
    private boolean[] visited;
    private ArrayList<Integer> red = new ArrayList<>();
    private ArrayList<Integer> green = new ArrayList<>();
    private boolean isBipartite = true;

    public BipartiteDetection2(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        colors = new int[graph.getV()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < graph.getV(); v ++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    /**
     * 判断图是不是二分图(所有边的两个端点隶属不同的颜色).
     *
     * @param v 顶点
     * @return 若是二分图返回true, 否则返回false
     */
    private boolean bfs(int v){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = true;
        colors[v] = 0;
        while (!queue.isEmpty()) {
            int q = queue.removeFirst();
            for (int w : graph.adj(q)) {
                if (!visited[w]) {
                    queue.addLast(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[q];
                } else if (colors[w] == colors[q]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite() {
        if (isBipartite) {
            for (int v = 0; v < colors.length; v ++) {
                if (colors[v] == 0) {
                    red.add(v);
                } else if (colors[v] == 1) {
                    green.add(v);
                }
            }
        }
        return isBipartite;
    }

    public Iterable<Integer> getRed() {
        return red;
    }

    public Iterable<Integer> green() {
        return green;
    }

    public static void main(String[] args) {
        BipartiteDetection2 bipartiteDetection = new BipartiteDetection2(new UndirectedAdjSet("g.txt"));
        System.out.println(bipartiteDetection.isBipartite());
        System.out.println("red: " + bipartiteDetection.getRed());
        System.out.println("green: " + bipartiteDetection.green());
        bipartiteDetection = new BipartiteDetection2(new UndirectedAdjSet("g4.txt"));
        System.out.println(bipartiteDetection.isBipartite());
        System.out.println("red: " + bipartiteDetection.getRed());
        System.out.println("green: " + bipartiteDetection.green());
    }
}