import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 无向无权图邻接表.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.io.File
 * @see java.util.LinkedList
 * @see java.util.Scanner
 */
public class AdjList implements Graph {

    private LinkedList<Integer>[] adj;    // 邻接表
    private int V;  // 顶点数
    private int E;  // 边数

    /**
     * 根据file构建邻接表.
     *
     * @param filename 文件名
     * @throws IllegalArgumentException V && E need to > 0, 两个顶点间不存在多条边, 不存在自环
     */
    public AdjList(String filename) {
        File f = new File(filename);
        try (Scanner scanner = new Scanner(f)) {
            V = scanner.nextInt();
            if (V <= 0) {
                throw new IllegalArgumentException("V must > 0!");
            }
            E = scanner.nextInt();
            if (E <= 0) {
                throw new IllegalArgumentException("E must > 0!");
            }
            adj = new LinkedList[V];
            for (int i = 0; i < V; i ++) {
                adj[i] = new LinkedList<>();
            }
            for (int i = 0; i < E; i ++) {
                int a = scanner.nextInt();
                validate(a);
                int b = scanner.nextInt();
                validate(b);
                if (a == b) {
                    throw new IllegalArgumentException("a can not equal b!");
                }
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("The side already exists!");
                }
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取顶点数.
     *
     * @return 顶点数
     */
    @Override
    public int getV() {
        return V;
    }

    /**
     * 获取边数.
     *
     * @return 边数
     */
    @Override
    public int getE() {
        return E;
    }

    /**
     * 判断两个顶点之间是否存在边.
     *
     * @param v 顶点
     * @param w 顶点
     * @return 有边返回true, 否则false
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validate(v);
        validate(w);
        return adj[v].contains(w);
    }

    /**
     * 获取与顶点v相邻的顶点.
     *
     * @param v 顶点
     * @return 相邻的顶点
     */
    @Override
    public Iterable<Integer> adj(int v) {
        validate(v);
        return adj[v];
    }

    /**
     * 获取顶点v的度数.
     *
     * @param v 顶点
     * @return 顶点v的度数
     */
    @Override
    public int degree(int v) {
        validate(v);
        return adj[v].size();
    }

    /**
     * 判断顶点合法性.
     *
     * @param v 顶点
     * @throws IllegalArgumentException 顶点属于[0, V)
     */
    @Override
    public void validate(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("v must be belong [0, V)!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数: %d, 边数: %d\n", V, E));
        for (int v = 0; v < V; v ++) {
            sb.append(v).append(" : ");
            for (int w : adj[v]) {
               sb.append(w).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjList adjList = new AdjList("g.txt");
        System.out.println(adjList);
    }
}