package udga;

import graph.UndirectedAdjSet;
import graph.Graph;
import java.util.ArrayList;
import java.util.Arrays;

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
                dfs(i, ccCount);
                ccCount ++;
            }
        }
    }

    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        for (int w : graph.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
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
        CC cc = new CC(new UndirectedAdjSet("g.txt"));
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