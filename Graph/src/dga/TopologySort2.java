package dga;

import dfs.GraphDFS;
import graph.DirectedAdjSet;
import graph.DirectedGraph;
import java.util.ArrayList;
import java.util.Collections;

public class TopologySort2 {

    private DirectedGraph graph;
    private ArrayList<Integer> res = new ArrayList<>();
    private boolean hasCycle = false;

    public TopologySort2(DirectedGraph graph) {
        this.graph = graph;
        hasCycle = (new DirectedCycleDetection(graph)).hasCycle();
        if(hasCycle) {
            return;
        }
        GraphDFS dfs = new GraphDFS(graph);
        for(int v: dfs.getPostOrder()) {
            res.add(v);

        }
        Collections.reverse(res);
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public Iterable<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        TopologySort2 topologySort2 = new TopologySort2(new DirectedAdjSet("dg.txt"));
        System.out.println(topologySort2.result());
    }
}