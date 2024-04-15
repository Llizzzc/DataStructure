package graph;

import java.io.File;
import java.util.TreeSet;
import  java.util.Scanner;

public class DirectedAdjSet implements DirectedGraph {

    private TreeSet<Integer>[] adj;    // 邻接表, 使用TreeSet优化查找
    private int V;  // 顶点数
    private int E;  // 边数
    private int[] inDegree;
    private int[] outDegree;

    /**
     * 根据file构建邻接表.
     *
     * @param filename 文件名
     * @throws IllegalArgumentException V && E need to > 0, 两个顶点间不存在多条边, 不存在自环
     */
    public DirectedAdjSet(String filename) {
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
            adj = new TreeSet[V];
            inDegree = new int[V];
            outDegree = new int[V];

            for (int i = 0; i < V; i ++) {
                adj[i] = new TreeSet<>();
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
                inDegree[b] ++;
                outDegree[a] ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DirectedAdjSet(TreeSet<Integer>[] adj) {
        this.adj = adj;
        this.V = adj.length;
        this.E = 0;
        inDegree = new int[V];
        outDegree = new int[V];
        for(int v = 0; v < V; v ++) {
            for (int w : adj[v]) {
                outDegree[v] ++;
                inDegree[w] ++;
                this.E ++;
            }
        }
    }

    @Override
    public int getV() {
        return V;
    }

    @Override
    public int getE() {
        return E;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        validate(v);
        validate(w);
        return adj[v].contains(w);
    }

    @Override
    public Iterable<Integer> adj(int v) {
        validate(v);
        return adj[v];
    }

    @Override
    public int inDegree(int v) {
        validate(v);
        return inDegree[v];
    }

    @Override
    public int outDegree(int v) {
        validate(v);
        return outDegree[v];
    }

    @Override
    public void validate(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("v must be belong [0, V)!");
        }
    }

    @Override
    public void removeEdge(int v, int w){
        validate(v);
        validate(w);
        if(adj[v].contains(w)) {
            E --;
            outDegree[v] --;
            inDegree[w] --;
        }
        adj[v].remove(w);
    }

    public DirectedGraph reverseGraph() {
        TreeSet<Integer>[] rAdj = new TreeSet[V];
        for(int i = 0; i < V; i ++) {
            rAdj[i] = new TreeSet<>();
        }
        for(int v = 0; v < V; v ++) {
            for (int w : adj(v)) {
                rAdj[w].add(v);
            }
        }
        return new DirectedAdjSet(rAdj);
    }

    @Override
    public Object clone(){
        try{
            DirectedAdjSet cloned = (DirectedAdjSet) super.clone();
            cloned.adj = new TreeSet[V];
            for(int v = 0; v < V; v ++){
                cloned.adj[v] = new TreeSet<>();
                for(int w: adj[v])
                    cloned.adj[v].add(w);
            }
            cloned.inDegree = new int[V];
            for(int v = 0; v < V; v ++){
                cloned.inDegree[v] = inDegree[v];
            }
            cloned.outDegree = new int[V];
            for(int v = 0; v < V; v ++){
                cloned.outDegree[v] = outDegree[v];
            }
            return cloned;
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
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
        DirectedAdjSet directedAdjSet = new DirectedAdjSet("dg.txt");
        System.out.println(directedAdjSet);

        for (int i = 0; i < directedAdjSet.getV(); i ++) {
            System.out.print(directedAdjSet.inDegree(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < directedAdjSet.getV(); i ++) {
            System.out.print(directedAdjSet.outDegree(i) + " ");
        }
    }
}