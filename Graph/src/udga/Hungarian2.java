package udga;

import graph.UndirectedAdjSet;
import graph.UndirectedGraph;
import java.util.Arrays;

public class Hungarian2 {

    private UndirectedGraph graph;
    private int maxMatching;
    private int matching[];
    private boolean visited[];

    public Hungarian2(UndirectedGraph graph) {

        BipartiteDetection bd = new BipartiteDetection(graph);
        if (!bd.isBipartite()) {
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");
        }
        this.graph = graph;

        int[] colors = bd.getColors();

        matching = new int[graph.getV()];
        Arrays.fill(matching, -1);

        visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v ++) {
            if (colors[v] == 0 && matching[v] == -1) {
                Arrays.fill(visited, false);
                if (dfs(v)) {
                    maxMatching ++;
                }
            }
        }
    }

    private boolean dfs(int v) {

        visited[v] = true;
        for (int u : graph.adj(v)) {
            if (!visited[u]) {
                visited[u] = true;
                if (matching[u] == -1 || dfs(matching[u])) {
                    matching[u] = v;
                    matching[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == graph.getV();
    }

    public static void main(String[] args) {
        Hungarian2 hungarian = new Hungarian2(new UndirectedAdjSet("g17.txt"));
        System.out.println(hungarian.maxMatching());

        hungarian  = new Hungarian2(new UndirectedAdjSet("g18.txt"));
        System.out.println(hungarian.maxMatching());
    }
}