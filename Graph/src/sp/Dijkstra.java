package sp;

import graph.Weighted;
import java.util.Arrays;

public class Dijkstra {

    private Weighted graph;
    private int s;
    private boolean[] visited;
    private int[] dis;

    public Dijkstra(Weighted graph, int s) {
        this.graph = graph;
        graph.validate(s);
        this.s = s;
        visited = new boolean[graph.getV()];
        dis = new int[graph.getV()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        while (true) {
            int cur = -1, curDis = Integer.MAX_VALUE;
            // 找到最小的未访问过节点的路径
            for (int v = 0; v < graph.getV(); v ++) {
                if (!visited[v] && dis[v] < curDis) {
                    curDis = dis[v];
                    cur = v;
                }
            }

            if (cur == -1) {
                break;
            }
            visited[cur] = true;

            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    if (dis[cur] + graph.getWeight(w, cur) < dis[w]) {
                        dis[w] = dis[cur] + graph.getWeight(w, cur);
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

    public static void main(String[] args) {
        Weighted weighted = new Weighted("g16.txt");
        Dijkstra dij = new Dijkstra(weighted, 0);
        for(int v = 0; v < weighted.getV(); v ++) {
            System.out.print(dij.distTo(v) + " ");
        }
        System.out.println();
    }
}