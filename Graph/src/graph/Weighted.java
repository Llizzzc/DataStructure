package graph;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import  java.util.Scanner;

public class Weighted implements Graph, Cloneable{

    private TreeMap<Integer, Integer>[] adj;
    private int V;  // 顶点数
    private int E;  // 边数

    /**
     * 根据file构建邻接表.
     *
     * @param filename 文件名
     * @throws IllegalArgumentException V && E need to > 0, 两个顶点间不存在多条边, 不存在自环
     */
    public Weighted(String filename) {
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
            adj = new TreeMap[V];
            for (int i = 0; i < V; i ++) {
                adj[i] = new TreeMap<>();
            }
            for (int i = 0; i < E; i ++) {
                int a = scanner.nextInt();
                validate(a);
                int b = scanner.nextInt();
                validate(b);
                int weight = scanner.nextInt();
                if (a == b) {
                    throw new IllegalArgumentException("a can not equal b!");
                }
                if (adj[a].containsKey(b)) {
                    throw new IllegalArgumentException("The side already exists!");
                }
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return adj[v].containsKey(w);
    }

    @Override
    public Iterable<Integer> adj(int v) {
        validate(v);
        return adj[v].keySet();
    }

    @Override
    public int degree(int v) {
        validate(v);
        return adj[v].size();
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
        if(adj[v].containsKey(w)) {
            E --;
        }
        adj[v].remove(w);
        adj[w].remove(v);
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        throw new IllegalArgumentException("No edge.");
    }

    @Override
    public Object clone(){
        try{
            Weighted cloned = (Weighted) super.clone();
            cloned.adj = new TreeMap[V];
            for(int v = 0; v < V; v ++){
                cloned.adj[v] = new TreeMap<>();
                for(Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
                }
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
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                sb.append(String.format("(%d: %d)", entry.getKey(), entry.getValue())).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Weighted weighted = new Weighted("g15.txt");
        System.out.println(weighted);
    }
}