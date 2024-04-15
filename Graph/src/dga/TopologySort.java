package dga;

import graph.DirectedAdjSet;
import graph.DirectedGraph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TopologySort {

    private DirectedGraph graph;
    private ArrayList<Integer> res = new ArrayList<>();
    private boolean hasCycle = false;

    public TopologySort(DirectedGraph graph) {
        this.graph = graph;
        int[] inDegrees = new int[graph.getV()];
        Deque<Integer> q = new ArrayDeque<>();
        for(int v = 0; v < graph.getV(); v ++) {
            inDegrees[v] = graph.inDegree(v);
            if(inDegrees[v] == 0) q.addLast(v);
        }

        while(!q.isEmpty()) {
            int cur = q.removeFirst();
            res.add(cur);
            for(int next: graph.adj(cur)) {
                inDegrees[next] --;
                if(inDegrees[next] == 0) q.addLast(next);
            }
        }

        if(res.size() != graph.getV()) {
            hasCycle = true;
            res.clear();
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public Iterable<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        TopologySort topologySort = new TopologySort(new DirectedAdjSet("dg.txt"));
        System.out.println(topologySort.result());
    }
}