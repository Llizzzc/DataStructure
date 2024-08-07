package dga;

import graph.DirectedWeighted;
import java.util.*;

public class MaxFlow {
    private DirectedWeighted network;
    private int s, t;

    private DirectedWeighted rG;
    private int maxFlow = 0;

    public MaxFlow(DirectedWeighted network, int s, int t) {

        if(network.getV() < 2) {
            throw new IllegalArgumentException("The network should hs at least 2 vertices.");
        }

        network.validate(s);
        network.validate(t);
        if(s == t) {
            throw new IllegalArgumentException("s and t should be differrent.");
        }
        this.network = network;
        this.s = s;
        this.t = t;

        this.rG = new DirectedWeighted(network.getV());
        for (int v = 0; v < network.getV(); v ++) {
            for (int w : network.adj(v)) {
                int c = network.getWeight(v, w);
                rG.addEdge(v, w, c);
                rG.addEdge(w, v, 0);
            }
        }
        while (true) {
            ArrayList<Integer> augPath = getAugmentingPath();
            if(augPath.size() == 0) {
                break;
            }

            int f = Integer.MAX_VALUE;
            for (int i = 1; i < augPath.size(); i ++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                f = Math.min(f, rG.getWeight(v, w));
            }
            maxFlow += f;

            for (int i = 1; i < augPath.size(); i ++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);

                rG.setWeight(v, w, rG.getWeight(v, w) - f);
                rG.setWeight(w, v, rG.getWeight(w, v) + f);
            }
        }
    }

    private ArrayList<Integer> getAugmentingPath() {

        Queue<Integer> q = new LinkedList<>();
        int[] pre = new int[network.getV()];
        Arrays.fill(pre, -1);

        q.add(s);
        pre[s] = s;
        while (!q.isEmpty()) {
            int cur = q.remove();
            if(cur == t) break;
            for(int next: rG.adj(cur)) {
                if (pre[next] == -1 && rG.getWeight(cur, next) > 0) {
                    pre[next] = cur;
                    q.add(next);
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        if(pre[t] == -1) return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public int result() {
        return maxFlow;
    }

    public int flow(int v, int w) {

        if(!network.hasEdge(v, w)) {
            throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
        }
        return rG.getWeight(w, v);
    }

    public static void main(String[] args) {
        DirectedWeighted network = new DirectedWeighted("network.txt");
        MaxFlow maxflow = new MaxFlow(network, 0, 3);
        System.out.println(maxflow.result());
        for (int v = 0; v < network.getV(); v ++) {
            for (int w : network.adj(v)) {
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxflow.flow(v, w), network.getWeight(v, w)));
            }
        }
        network = new DirectedWeighted("network2.txt");
        maxflow = new MaxFlow(network, 0, 5);
        System.out.println(maxflow.result());
        for (int v = 0; v < network.getV(); v ++) {
            for (int w : network.adj(v)) {
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxflow.flow(v, w), network.getWeight(v, w)));
            }
        }
    }
}