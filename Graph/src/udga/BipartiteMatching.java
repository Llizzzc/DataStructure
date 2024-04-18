package udga;

import dga.MaxFlow;
import graph.DirectedWeighted;
import graph.UndirectedAdjSet;
import graph.UndirectedGraph;

public class BipartiteMatching {

    private UndirectedGraph graph;
    private int maxMatching;

    public BipartiteMatching(UndirectedGraph graph) {

        BipartiteDetection bd = new BipartiteDetection(graph);
        if (!bd.isBipartite()) {
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");
        }
        this.graph = graph;

        int[] colors = bd.getColors();

        // 源点为 V, 汇点为 V + 1
        DirectedWeighted network = new DirectedWeighted(graph.getV() + 2);
        for (int v = 0; v < graph.getV(); v ++) {
            if (colors[v] == 0) {
                network.addEdge(graph.getV(), v, 1);
            } else {
                network.addEdge(v, graph.getV() + 1, 1);
            }

            for (int w : graph.adj(v)) {
                if (v < w) {
                    if (colors[v] == 0) {
                        network.addEdge(v, w, 1);
                    } else {
                        network.addEdge(w, v, 1);
                    }
                }
            }
        }

        MaxFlow maxFlow = new MaxFlow(network, graph.getV(), graph.getV() + 1);
        maxMatching = maxFlow.result();
    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == graph.getV();
    }

    public static void main(String[] args) {
        BipartiteMatching bipartiteMatching = new BipartiteMatching(new UndirectedAdjSet("g17.txt"));
        System.out.println(bipartiteMatching.maxMatching());

        bipartiteMatching  = new BipartiteMatching(new UndirectedAdjSet("g18.txt"));
        System.out.println(bipartiteMatching.maxMatching());
    }
}