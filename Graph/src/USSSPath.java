import java.util.*;

// Unweighted Single Source Shortest Path
public class USSSPath {
    private Graph graph;
    private int s;
    private boolean[] visited;
    private int[] pre;  // 记录父节点
    private int[] dis;  // 记录距离

    public USSSPath(Graph graph, int s){
        graph.validate(s);
        this.graph = graph;
        this.s = s;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        dis = new int[graph.getV()];
        Arrays.fill(dis, -1);
        bfs(s);  // 顶点s的父节点设为s

        for(int i = 0; i < graph.getV(); i ++) {
            System.out.print(dis[i] + " ");
        }
        System.out.println();
    }

    private void bfs(int v){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = true;
        pre[v] = v;
        dis[v] = 0;
        while (!queue.isEmpty()) {
            int q = queue.removeFirst();
            for (int w : graph.adj(q)) {
                if (!visited[w]) {
                    queue.addLast(w);
                    visited[w] = true;
                    pre[w] = q;
                    dis[w] = dis[q] + 1;
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
        return visited[t];
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

    /**
     * 获取顶点s至顶点t的距离.
     *
     * @param t 顶点
     * @return 返回顶点s至顶点t的距离
     */
    public int dis(int t){
        graph.validate(t);
        return dis[t];
    }

    public static void main(String[] args){
        USSSPath usssPath = new USSSPath(new AdjSet("g.txt"),  0);
        System.out.println("0 -> 6: " + usssPath.path(6) + " -- " + "dis: " + usssPath.dis(6));
        System.out.println("0 -> 5: " + usssPath.path(5) + " -- " + "dis: " + usssPath.dis(5));
    }
}