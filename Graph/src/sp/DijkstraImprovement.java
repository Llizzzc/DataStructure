package sp;

import graph.Weighted;
import graph.UndirectedWeightedGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class DijkstraImprovement {

    private UndirectedWeightedGraph graph;
    private int s;
    private boolean[] visited;
    private int[] dis;
    private int[] pre;

    private class Node implements Comparable<Node> {
        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }

    public DijkstraImprovement(UndirectedWeightedGraph graph, int s) {
        this.graph = graph;
        graph.validate(s);
        this.s = s;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        dis = new int[graph.getV()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        pre[s] = s;
        dis[s] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(s, 0));
        while (!queue.isEmpty()) {
            int cur = queue.remove().v;
            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    if (dis[cur] + graph.getWeight(w, cur) < dis[w]) {
                        dis[w] = dis[cur] + graph.getWeight(w, cur);
                        queue.add(new Node(w, dis[w]));
                        pre[w] = cur;
                    }
                }
            }
        }
    }

    public boolean isConnectedTo(int v) {
        graph.validate(v);
        return visited[v];
    }

    public int distTo(int v) {
        graph.validate(v);
        return dis[v];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) {
            return res;
        }
        int cur = t;
        while(cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        UndirectedWeightedGraph g = new Weighted("g16.txt");
        DijkstraImprovement dij = new DijkstraImprovement(g, 0);
        for (int v = 0; v < g.getV(); v ++) {
            System.out.print(dij.distTo(v) + " ");
        }
        System.out.println();
        System.out.println(dij.path(3));
    }
}