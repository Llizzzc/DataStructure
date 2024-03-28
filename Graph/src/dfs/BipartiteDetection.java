package dfs;

import graph.AdjSet;
import graph.Graph;
import java.util.ArrayList;
import java.util.Arrays;

public class BipartiteDetection {
    private Graph graph;
    private int[] colors;   // -1无色, 0红色, 1绿色
    private boolean[] visited;
    private ArrayList<Integer> red = new ArrayList<>();
    private ArrayList<Integer> green = new ArrayList<>();
    private boolean isBipartite = true;

    public BipartiteDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        colors = new int[graph.getV()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < graph.getV(); v ++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
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
     * @param color 染色
     * @return 若是二分图返回true, 否则返回false
     */
    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
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
        BipartiteDetection bipartiteDetection = new BipartiteDetection(new AdjSet("g.txt"));
        System.out.println(bipartiteDetection.isBipartite());
        System.out.println("red: " + bipartiteDetection.getRed());
        System.out.println("green: " + bipartiteDetection.green());
        bipartiteDetection = new BipartiteDetection(new AdjSet("g4.txt"));
        System.out.println(bipartiteDetection.isBipartite());
        System.out.println("red: " + bipartiteDetection.getRed());
        System.out.println("green: " + bipartiteDetection.green());
    }
}