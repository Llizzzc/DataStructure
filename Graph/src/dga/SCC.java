package dga;

import common.GraphDFS;
import graph.DirectedAdjSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SCC {

    private DirectedAdjSet graph;
    private int[] visited;
    private int scccount = 0;

    public SCC(DirectedAdjSet graph) {
        this.graph = graph;
        visited = new int[graph.getV()];
        Arrays.fill(visited, -1);
        GraphDFS dfs = new GraphDFS(graph.reverseGraph());
        ArrayList<Integer> order = new ArrayList<>();
        for (int v : dfs.getPostOrder()) {
            order.add(v);
        }
        Collections.reverse(order);

        for (int v : order) {
            if (visited[v] == -1) {
                dfs(v, scccount);
                scccount++;
            }
        }
    }

    private void dfs(int v, int sccid) {
        visited[v] = sccid;
        for (int w : graph.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, sccid);
            }
        }
    }

    public int getCount() {
        return scccount;
    }

    public boolean isStronglyConnected(int v, int w) {
        graph.validate(v);
        graph.validate(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components() {

        ArrayList<Integer>[] res = new ArrayList[scccount];
        for (int i = 0; i < scccount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.getV(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        SCC scc = new SCC(new DirectedAdjSet("dg4.txt"));
        System.out.println(scc.getCount());
        ArrayList<Integer>[] comp = scc.components();
        for (int sccid = 0; sccid < comp.length; sccid++) {
            System.out.print(sccid + " : ");
            for (int w : comp[sccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}