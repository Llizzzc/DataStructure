package udga;

import dfs.CC;
import graph.Weighted;
import graph.UndirectedWeightedGraph;
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
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

    public Kruskal(UndirectedWeightedGraph graph) {
        this.graph = graph;
        CC cc = new CC(graph);
        if (cc.getCount() > 1) {
            return;
        }
        ArrayList<Edge> edges = new ArrayList<>();
        for (int v = 0; v < graph.getV(); v ++) {
            for (int w : graph.adj(v)) {
                if (v < w) {
                    edges.add(new Edge(v, w, graph.getWeight(v, w)));
                }
            }
        }
        Collections.sort(edges);
        UF uf = new UnionFind1(graph.getV());
        for (Edge edge : edges) {
            if (!uf.isConnected(edge.v, edge.w)) {
                mst.add(edge);
                uf.unionElements(edge.v, edge.w);
            }
        }
    }

    public Iterable<Edge> result() {
        return mst;
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal(new Weighted("g15.txt"));
        System.out.println(kruskal.result());
    }
}