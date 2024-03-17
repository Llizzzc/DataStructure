import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 无向无权图邻接矩阵.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.io.File
 * @see java.util.ArrayList
 * @see java.util.Scanner
 */
public class AdjMatrix implements Graph {

    private int[][] adj;    // 邻接矩阵
    private int V;  // 顶点数
    private int E;  // 边数

    /**
     * 根据file构建邻接矩阵.
     *
     * @param filename 文件名
     * @throws IllegalArgumentException V && E need to > 0, 两个顶点间不存在多条边, 不存在自环
     */
    public AdjMatrix(String filename) {
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
            adj = new int[V][V];
            for (int i = 0; i < E; i ++) {
                int a = scanner.nextInt();
                validate(a);
                int b = scanner.nextInt();
                validate(b);
                if (a == b) {
                    throw new IllegalArgumentException("a can not equal b!");
                }
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("The side already exists!");
                }
                adj[a][b] = 1;
                adj[b][a] = 1;
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
        return adj[v][w] == 1;
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
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i ++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
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
        int degree = 0;
        for (Integer w : adj(v)) {
            degree ++;
        }
        return degree;
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
        for (int i = 0; i < V; i ++) {
            for (int j = 0; j < V; j ++) {
                sb.append(adj[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}