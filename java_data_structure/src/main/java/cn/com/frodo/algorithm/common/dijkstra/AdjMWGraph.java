package cn.com.frodo.algorithm.common.dijkstra;

public class AdjMWGraph {
    static final int maxWeight = 1000;

    private SeqList vertices; // 存储节点的顺序表
    private int[][] edge; // 存储边的二维数组
    private int numOfEdges; // 边的个数

    public AdjMWGraph(int maxV) {
        vertices = new SeqList(maxV);
        edge = new int[maxV][maxV];
        for (int i = 0; i < maxV; i++) {
            for (int j = 0; j < maxV; j++) {
                if (i == j)
                    edge[i][j] = 0;
                else
                    edge[i][j] = maxWeight;
            }
        }
        numOfEdges = 0;
    }

    /**
     * @return 返回结点个数
     */
    public int getNumOfVertices() {
        return vertices.size;
    }

    /**
     * @return 返回边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * @param v
     * @return 返回节点v的数据元素
     * @throws Exception
     */
    public Object getValue(int v) throws Exception {
        return vertices.getData(v);
    }

    /**
     * @param v1
     * @param v2
     * @return 边<v1, v2>的权值
     * @throws Exception
     */
    public int getWeight(int v1, int v2) throws Exception {
        if (v1 < 0 || v1 >= vertices.size || v2 < 0 || v2 >= vertices.size) {
            throw new Exception("参数v1 或者 v2 越界！");
        }
        return edge[v1][v2];
    }

    public void insertVertex(Object vertex) throws Exception {
        vertices.insert(vertices.size, vertex);
    }

    /**
     * 插入边<v1,v2>，权值为weight
     *
     * @param v1
     * @param v2
     * @param weight
     * @throws Exception
     */
    public void insertEdge(int v1, int v2, int weight) throws Exception {
        if (v1 < 0 || v1 >= vertices.size || v2 < 0 || v2 >= vertices.size) {
            throw new Exception("参数v1 或者 v2 越界！");
        }
        edge[v1][v2] = weight;
        numOfEdges++;
    }

    /**
     * 删除边<v1,v2>
     *
     * @param v1
     * @param v2
     * @throws Exception
     */
    public void deleteEdge(int v1, int v2) throws Exception {
        if (v1 < 0 || v1 >= vertices.size || v2 < 0 || v2 >= vertices.size) {
            throw new Exception("参数v1 或者 v2 越界！");
        }
        if (edge[v1][v2] == maxWeight || v1 == v2) {
            throw new Exception("该边不存在！");
        }
        edge[v1][v2] = maxWeight;
        numOfEdges++;
    }

    /**
     * 取结点v的第一个邻接结点。
     *
     * @param v
     * @return 若存在返回该结点的下标序号，否则返回-1
     * @throws Exception
     */
    public int getFirstNeighbor(int v) throws Exception {
        if (v < 0 || v >= vertices.size) {
            new Exception("参数v越界出错！");
        }
        for (int col = 0; col < vertices.size; col++) {
            if (edge[v][col] > 0 && edge[v][col] < maxWeight) {
                return col;
            }
        }
        return -1;
    }

    /**
     * 取结点v1的邻接结点v2后的邻接结点
     *
     * @param v1
     * @param v2
     * @return 若存在返回该结点的下标序号，否则返回-1
     * @throws Exception
     */
    public int getNextNeighbor(int v1, int v2) throws Exception {
        if (v1 < 0 || v1 >= vertices.size || v2 < 0 || v2 >= vertices.size) {
            throw new Exception("参数v1或v2越界出错！");
        }

        for (int col = v2 + 1; col < vertices.size; col++) {
            if (edge[v1][col] > 0 && edge[v1][col] < maxWeight) {
                return col;
            }
        }
        return -1;
    }

}
