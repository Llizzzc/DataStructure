package graph;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import  java.util.Scanner;

public class DirectedWeighted implements DirectedWeightedGraph {

    private TreeMap<Integer, Integer>[] adj;
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
    public DirectedWeighted(String filename) {
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
            inDegree = new int[V];
            outDegree = new int[V];

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
                inDegree[b] ++;
                outDegree[a] ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DirectedWeighted(int V) {
        this.V = V;
        this.E = 0;
        adj = new TreeMap[V];
        for(int i = 0; i < V; i ++) {
            adj[i] = new TreeMap<>();
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

    public void addEdge(int a, int b, int v) {

        validate(a);
        validate(b);

        if(a == b) {
            throw new IllegalArgumentException("a can not equal b!");
        }
        if(adj[a].containsKey(b)) {
            throw new IllegalArgumentException("The side already exists!");
        }

        adj[a].put(b, v);
        this.E ++;
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
    public void removeEdge(int v, int w) {
        validate(v);
        validate(w);
        if(adj[v].containsKey(w)) {
            E --;
            outDegree[v] --;
            inDegree[w] --;
        }
        adj[v].remove(w);
    }

    @Override
    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        throw new IllegalArgumentException("No edge.");
    }

    public void setWeight(int v, int w, int newWeight) {
        if(!hasEdge(v, w)) {
            throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
        }
        adj[v].put(w, newWeight);
    }

    @Override
    public Object clone() {
        try{
            DirectedWeighted cloned = (DirectedWeighted) super.clone();
            cloned.adj = new TreeMap[V];
            for(int v = 0; v < V; v ++){
                cloned.adj[v] = new TreeMap<>();
                for(Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
                }
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
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                sb.append(String.format("(%d: %d)", entry.getKey(), entry.getValue())).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}