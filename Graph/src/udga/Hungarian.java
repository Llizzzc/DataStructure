package udga;

import graph.UndirectedAdjSet;
import graph.UndirectedGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Hungarian {

    private UndirectedGraph graph;
    private int maxMatching = 0;
    private int matching[];

    public Hungarian(UndirectedGraph graph) {

        BipartiteDetection bd = new BipartiteDetection(graph);
        if (!bd.isBipartite()) {
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
        }
        this.graph = graph;

        int[] colors = bd.getColors();

        matching = new int[graph.getV()];
        Arrays.fill(matching, -1);
        for (int v = 0; v < graph.getV(); v ++) {
            if (colors[v] == 0 && matching[v] == -1) {
                if (bfs(v)) {
                    maxMatching ++;
                }
            }
        }
    }

    private boolean bfs(int v) {

        Queue<Integer> q = new LinkedList<>();
        int[] pre = new int[graph.getV()];
        Arrays.fill(pre, -1);

        q.add(v);
        pre[v] = v;
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int next : graph.adj(cur)) {
                if (pre[next] == -1) {
                    if (matching[next] != -1) {
                        q.add(matching[next]);
                        pre[next] = cur;
                        pre[matching[next]] = next;
                    } else {
                        pre[next] = cur;
                        ArrayList<Integer> augPath = getAugPath(pre, v, next);
                        for (int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre, int start, int end) {

        ArrayList<Integer> res = new ArrayList<>();
        int cur = end;
        while (cur != start) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(start);
        return res;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == graph.getV();
    }

    public static void main(String[] args) {
        Hungarian hungarian = new Hungarian(new UndirectedAdjSet("g17.txt"));
        System.out.println(hungarian.maxMatching());

        hungarian  = new Hungarian(new UndirectedAdjSet("g18.txt"));
        System.out.println(hungarian.maxMatching());
    }
}