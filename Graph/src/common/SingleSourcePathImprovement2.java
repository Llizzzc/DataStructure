package common;

import graph.UndirectedAdjSet;
import graph.Graph;
import java.util.*;

public class SingleSourcePathImprovement2 {
    private Graph graph;
    private int s;
    private int[] pre;  // 记录父节点

    public SingleSourcePathImprovement2(Graph graph, int s){
        graph.validate(s);
        this.graph = graph;
        this.s = s;
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        bfs(s);  // 顶点s的父节点设为s
    }

    private void bfs(int v){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        pre[v] = v;
        while (!queue.isEmpty()) {
            int q = queue.removeFirst();
            for (int w : graph.adj(q)) {
                if (pre[w] == -1) {
                    queue.addLast(w);
                    pre[w] = q;
                }
            }
        }
    }

    /**
     * 判断从顶点s出发是否能够到达顶点t.
     *
     * @param t 顶点
     * @return 若可达返回true, 否则返回false
     */
    public boolean isConnectedTo(int t){
        graph.validate(t);
        return pre[t] != -1;
    }

    /**
     * 获取从顶点s至顶点t的单源路径.
     *
     * @param t 顶点
     * @return 返回顶点s至顶点t的单源路径
     */
    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) {
            return res;
        }
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        SingleSourcePathImprovement2 singleSourcePathImprovement = new SingleSourcePathImprovement2(new UndirectedAdjSet("g.txt"),  0);
        System.out.println("0 -> 6: " + singleSourcePathImprovement.path(6));
        System.out.println("0 -> 5: " + singleSourcePathImprovement.path(5));
    }
}