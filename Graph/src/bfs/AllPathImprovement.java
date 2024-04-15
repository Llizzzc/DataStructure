package bfs;

import graph.AdjSet;
import graph.Graph;
import java.util.*;

public class AllPathImprovement {
    private Graph graph;
    private int s;
    private int t;
    private int[] pre;  // 记录父节点

    public AllPathImprovement(Graph graph, int s, int t){
        graph.validate(s);
        graph.validate(t);
        this.graph = graph;
        this.s = s;
        this.t = t;
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        bfs();  // 顶点s的父节点设为s
    }

    private void bfs(){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        pre[s] = s;
        if (s == t) return;
        while (!queue.isEmpty()) {
            int q = queue.removeFirst();
            if (q == t) {
                return;
            }
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
     * @return 若可达返回true, 否则返回false
     */
    public boolean isConnectedTo(){
        return pre[t] != -1;
    }

    /**
     * 获取从顶点s至顶点t的单源路径.
     *
     * @return 返回顶点s至顶点t的单源路径
     */
    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo()) {
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
        AllPathImprovement pathImprovement = new AllPathImprovement(new AdjSet("g.txt"),  0, 6);
        System.out.println("0 -> 6: " + pathImprovement.path());
        pathImprovement = new AllPathImprovement(new AdjSet("g.txt"), 1, 4);
        System.out.println("1 -> 4: " + pathImprovement.path());
        pathImprovement = new AllPathImprovement(new AdjSet("g.txt"), 3, 6);
        System.out.println("3 -> 6: " + pathImprovement.path());
    }
}