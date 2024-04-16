package udga;

import graph.UndirectedAdjSet;
import graph.Graph;

public class IsTree {

    private Graph graph;

    public IsTree(Graph graph) {
        this.graph = graph;
    }

    public boolean isTree() {
        CC cc  = new CC(graph);
        CycleDetection cycleDetection = new CycleDetection(graph);
        return cc.getCount() == 1 && !cycleDetection.hasCycle();
    }

    public static void main(String[] args) {
        IsTree isTree = new IsTree(new UndirectedAdjSet("g.txt"));
        System.out.println(isTree.isTree());
        isTree = new IsTree(new UndirectedAdjSet("g3.txt"));
        System.out.println(isTree.isTree());
    }
}