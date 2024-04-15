package bfs;

import graph.AdjSet;
import graph.Graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class CC {
    private Graph graph;
    private int visited[];  // 记录访问过的节点
    private int ccCount = 0;  // 连通分量的个数

    public CC(Graph graph) {
        this.graph = graph;
        visited = new int[graph.getV()];
        Arrays.fill(visited, -1);
        for (int i = 0; i < graph.getV(); i ++) {
            if (visited[i] == -1) {
                bfs(i, ccCount);
                ccCount ++;
            }
        }
    }

    private void bfs(int v, int ccid) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = ccid;
        while (!queue.isEmpty()) {
            int t = queue.removeFirst();
            for (int w : graph.adj(t)) {
                if (visited[w] == -1) {
                    queue.addLast(w);
                    visited[w] = ccid;
                }
            }
        }
    }

    /**
     * 判断两个顶点是否相连.
     *
     * @param v 顶点
     * @param w 顶点
     * @return 若相连返回ture, 否则返回false
     */
    public boolean isConnected(int v, int w) {
        graph.validate(v);
        graph.validate(w);
        return visited[v] == visited[w];
    }

    /**
     * 获取连通分量.
     *
     * @return 返回连通分量
     */
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i ++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.getV(); v ++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    /**
     * 获取图的连通分量个数.
     *
     * @return 返回连通分量个数
     */
    public int getCount() {
        return ccCount;
    }

    public static void main(String[] args) {
        CC cc = new CC(new AdjSet("g.txt"));
        System.out.println(cc.getCount());
        System.out.println(cc.isConnected(0, 1));
        System.out.println(cc.isConnected(0, 5));
        ArrayList<Integer>[] components = cc.components();
        for (int i = 0; i < components.length; i ++) {
            System.out.print(i + " : ");
            for (int w : components[i]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}