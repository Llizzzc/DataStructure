import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CycleDetection2 {
    private Graph graph;
    private int[] pre;  // 记录父节点
    private boolean hasCycle;

    public CycleDetection2(Graph graph){
        this.graph = graph;
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        for (int v = 0; v < graph.getV(); v ++) {
            if (pre[v] == -1) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    /**
     * 从顶点v开始, 判断图中是否存在环.
     *
     * @param v 顶点
     * @return 若有环返回true, 否则返回false
     */
    private boolean bfs(int v){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        pre[v] = v;
        while (!queue.isEmpty()) {
            int q = queue.removeFirst();
            for (int w : graph.adj(q)) {
                if (pre[w] == -1) {
                    queue.addLast(w);
                    pre[w] = q;
                } else if (w != pre[q]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args){
        CycleDetection2 cycleDetection2 = new CycleDetection2(new AdjSet("g.txt"));
        System.out.println(cycleDetection2.hasCycle());
        cycleDetection2 = new CycleDetection2(new AdjSet("g2.txt"));
        System.out.println(cycleDetection2.hasCycle());
    }
}