import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 图的深度优先遍历, 非递归.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.util.ArrayDeque
 * @see java.util.ArrayList
 */
public class GraphDFSNoR {
    private Graph graph;
    private boolean visited[];  // 记录访问过的节点
    private ArrayList<Integer> preOrder;

    /**
     * 根据传入的图获得DFS结果.
     *
     * @param graph 图
     */
    public GraphDFSNoR(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        preOrder = new ArrayList<>();
        for (int i = 0; i < graph.getV(); i ++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 深度优先遍历非递归实现.
     *
     * @param v 顶点
     */
    private void dfs(int v) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(v);
        visited[v] = true;
        while (!stack.isEmpty()) {
            int cur = stack.removeLast();
            preOrder.add(cur);
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    stack.addLast(w);
                    visited[w] = true;
                }
            }
        }
    }

    /**
     * 返回前序结果.
     *
     * @return 前序结果
     */
    public Iterable<Integer> getPreOrder() {
        return preOrder;
    }

    public static void main(String[] args) {
        GraphDFSNoR dfs = new GraphDFSNoR(new AdjSet("g.txt"));
        System.out.println(dfs.getPreOrder());
    }
}