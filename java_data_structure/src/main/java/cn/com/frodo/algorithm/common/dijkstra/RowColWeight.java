package cn.com.frodo.algorithm.common.dijkstra;

public class RowColWeight {
    int row;
    int col;
    int weight;

    public RowColWeight(int row, int col, int weight) {
        this.row = row;
        this.col = col;
        this.weight = weight;
    }

    public static void createGraph(AdjMWGraph g, Object[] v, int n, RowColWeight[] rc, int e) throws Exception {
        for (int i = 0; i < n; i++) {
            g.insertVertex(v[i]);
        }
        for (int i = 0; i < e; i++) {
            g.insertEdge(rc[i].row, rc[i].col, rc[i].weight);
        }
    }

}
