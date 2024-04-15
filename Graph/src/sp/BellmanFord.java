package sp;

import graph.Weighted;
import graph.UndirectedWeightedGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BellmanFord {
    private UndirectedWeightedGraph graph;
    private int s;
    private int[] dis;
    private int[] pre;
    private boolean hasNegCycle = false;

    public BellmanFord(UndirectedWeightedGraph graph, int s) {
        this.graph = graph;
        graph.validate(s);
        this.s = s;
        dis = new int[graph.getV()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        for (int pass = 1; pass < graph.getV(); pass ++) {
            for (int v = 0; v < graph.getV(); v ++) {
                for (int w : graph.adj(v)) {
                    if (dis[v] != Integer.MAX_VALUE && dis[v] + graph.getWeight(v, w) < dis[w]) {
                        dis[w] = dis[v] + graph.getWeight(v, w);
                        pre[w] = v;
                    }
                }
            }
        }

        for (int v = 0; v < graph.getV(); v ++) {
            for (int w : graph.adj(v)) {
                if (dis[v] != Integer.MAX_VALUE && dis[v] + graph.getWeight(v, w) < dis[w]) {
                    hasNegCycle = true;
                }
            }
        }
    }

    public boolean hasNegativeCycle() {
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v) {
        graph.validate(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int distTo(int v) {
        graph.validate(v);
        if (hasNegCycle) {
            throw new RuntimeException("Exist negative cycle.");
        }
        return dis[v];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (!isConnectedTo(t)) {
            return res;
        }
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        UndirectedWeightedGraph g = new Weighted("g16.txt");
        BellmanFord bf = new BellmanFord(g, 0);
        if (!bf.hasNegativeCycle()){
            for (int v = 0; v < g.getV(); v ++) {
                System.out.print(bf.distTo(v) + " ");
            }
            System.out.println();
            System.out.println(bf.path(3));
        } else {
            System.out.println("Exist negative cycle.");
        }
    }
}