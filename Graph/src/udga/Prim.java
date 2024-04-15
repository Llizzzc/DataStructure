package udga;

import dfs.CC;
import graph.Weighted;
import graph.UndirectedWeightedGraph;

import java.util.ArrayList;

public class Prim {
    private UndirectedWeightedGraph graph;
    private ArrayList<Edge> mst = new ArrayList<>();

    public class Edge implements Comparable<Edge> {
        private int v;
        private int w;
        private int weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + v + " - " + w + ": " + weight + ")";
        }

        @Override
        public int compareTo(Edge other) {
            return weight - other.weight;
        }
    }

    public Prim(UndirectedWeightedGraph graph) {
        this.graph = graph;
        CC cc = new CC(graph);
        if (cc.getCount() > 1) {
            return;
        }

        boolean[] visited = new boolean[graph.getV()];
        visited[0] = true;
        for (int i = 1; i < graph.getV(); i ++) {
            Edge minEdge = new Edge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < graph.getV(); v ++) {
                if (visited[v]) {
                    for (int w : graph.adj(v)) {
                        if (!visited[w] && graph.getWeight(v, w) < minEdge.weight) {
                            minEdge = new Edge(v, w, graph.getWeight(v, w));
                        }
                    }
                }
            }
            mst.add(minEdge);
            visited[minEdge.w] = true;
            visited[minEdge.v] = true;
        }
    }

    public Iterable<Edge> result() {
        return mst;
    }

    public static void main(String[] args) {
        Prim prim = new Prim(new Weighted("g15.txt"));
        System.out.println(prim.result());
    }
}