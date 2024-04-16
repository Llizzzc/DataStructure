package common;

import graph.UndirectedWeighted;
import graph.UndirectedWeightedGraph;
import java.util.Arrays;

public class Floyed {

    private UndirectedWeightedGraph graph;
    private int[][] dis;
    private boolean hasNegCycle = false;

    public Floyed(UndirectedWeightedGraph graph) {
        this.graph = graph;
        dis = new int[graph.getV()][graph.getV()];
        for (int v = 0; v < graph.getV(); v ++) {
            Arrays.fill(dis[v], Integer.MAX_VALUE);
        }
        for (int v = 0; v < graph.getV(); v ++) {
            dis[v][v] = 0;
            for(int w: graph.adj(v)) {
                dis[v][w] = graph.getWeight(v, w);
            }
        }

        for (int t = 0; t < graph.getV(); t ++) {
            for (int v = 0; v < graph.getV(); v++) {
                for (int w = 0; w < graph.getV(); w++) {
                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE
                            && dis[v][t] + dis[t][w] < dis[v][w]) {
                        dis[v][w] = dis[v][t] + dis[t][w];
                    }
                }
            }
        }

        for(int v = 0; v < graph.getV(); v ++) {
            if (dis[v][v] < 0) {
                hasNegCycle = true;
            }
        }
    }

    public boolean hasNegativeCycle() {
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v, int w) {
        graph.validate(v);
        graph.validate(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int distTo(int v, int w) {
        graph.validate(v);
        graph.validate(w);
        return dis[v][w];
    }

    public static void main(String[] args) {
        UndirectedWeightedGraph g = new UndirectedWeighted("g16.txt");
        Floyed floyed = new Floyed(g);
        if (!floyed.hasNegativeCycle()) {
            for (int v = 0; v < g.getV(); v ++) {
                for (int w = 0; w < g.getV(); w ++) {
                    System.out.print(floyed.distTo(v, w) + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Exist negative cycle.");
        }
    }
}