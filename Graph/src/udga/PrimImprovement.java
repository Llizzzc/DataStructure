package udga;

import dfs.CC;
import graph.Weighted;
import graph.UndirectedWeightedGraph;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimImprovement {
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

    public PrimImprovement(UndirectedWeightedGraph graph) {
        this.graph = graph;
        CC cc = new CC(graph);
        if (cc.getCount() > 1) {
            return;
        }
        boolean visited[] = new boolean[graph.getV()];
        visited[0] = true;
        Queue<Edge> priorityQueue = new PriorityQueue<>();
        for (int w : graph.adj(0)) {
            priorityQueue.add(new Edge(0, w, graph.getWeight(0, w)));
        }
        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.remove();
            if (visited[minEdge.v] && visited[minEdge.w]) {
                continue;
            }
            mst.add(minEdge);
            int newV = visited[minEdge.v]? minEdge.w: minEdge.v;
            visited[newV] = true;
            for (int w : graph.adj(newV)) {
                priorityQueue.add(new Edge(w, newV, graph.getWeight(w, newV)));
            }
        }
    }

    public Iterable<Edge> result() {
        return mst;
    }

    public static void main(String[] args) {
        PrimImprovement primImprovement = new PrimImprovement(new Weighted("g15.txt"));
        System.out.println(primImprovement.result());
    }
}