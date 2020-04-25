package cn.com.frodo.algorithm.common.dijkstra;

public class Dijkstra {
    static final int maxWeight = 9999;

    public static void dijkstra(AdjMWGraph g, int v0, int[] distance, int path[]) throws Exception {
        int n = g.getNumOfVertices();
        int[] s = new int[n];
        int minDis, u = 0;

        for (int i = 0; i < n; i++) {
            distance[i] = g.getWeight(v0, i);
            s[i] = 0;
            if (i != v0 && distance[i] < maxWeight) {
                path[i] = v0;
            } else {
                path[i] = -1;
            }
        }
        s[v0] = 1;

        for (int i = 0; i < n; i++) {
            minDis = maxWeight;
            for (int j = 0; j < n; j++) {
                if (s[j] == 0 && distance[j] < minDis) {
                    u = j;
                    minDis = distance[j];
                }
            }

            if (minDis == maxWeight)
                return;
            s[u] = 1;

            for (int j = 0; j < n; j++) {
                if (s[j] == 0 && g.getWeight(u, j) < maxWeight && distance[u] + g.getWeight(u, j) < distance[j]) {
                    distance[j] = distance[u] + g.getWeight(u, j);
                    path[j] = u;
                }
            }
        }

    }
}
