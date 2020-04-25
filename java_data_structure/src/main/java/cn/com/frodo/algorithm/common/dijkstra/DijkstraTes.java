package cn.com.frodo.algorithm.common.dijkstra;

public class DijkstraTes {

    static final int maxvertices = 100;
    static Character[] a = {new Character('A'), new Character('B'), new Character('C'), new Character('D'),
            new Character('E'), new Character('F'), new Character('G'), new Character('H')};

    public static void createGraph(AdjMWGraph g, Object[] v, int n, RowColWeight[] rc, int e) throws Exception {
        for (int i = 0; i < n; i++) {
            g.insertVertex(v[i]);
        }
        for (int i = 0; i < n; i++) {
            g.insertEdge(rc[i].row, rc[i].col, rc[i].weight);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        AdjMWGraph g = new AdjMWGraph(maxvertices);
        RowColWeight[] rcw = {new RowColWeight(0, 1, 2), new RowColWeight(0, 6, 6), new RowColWeight(1, 2, 7),
                new RowColWeight(1, 4, 2), new RowColWeight(2, 3, 3), new RowColWeight(2, 5, 3),
                new RowColWeight(3, 7, 2), new RowColWeight(4, 5, 2), new RowColWeight(4, 6, 1),
                new RowColWeight(5, 7, 2), new RowColWeight(6, 7, 4)};
        int n = 8, e = 11;

        try {
            createGraph(g, a, n, rcw, e);

            int[] distance = new int[n];
            int[] path = new int[n];

            Dijkstra.dijkstra(g, 0, distance, path);

            for (int i = 0; i < n; i++) {
                System.out.print("从A到" + g.getValue(i) + "的最短路径为：");
                System.out.print(g.getValue(i));
                int j = i;
                while (j > 0) {
                    System.out.print(g.getValue(path[j]));
                    j = path[j];
                }
                System.out.println("--最短长度为：" + distance[i]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

}
