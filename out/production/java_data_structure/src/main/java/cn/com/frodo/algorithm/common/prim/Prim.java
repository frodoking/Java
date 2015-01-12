package cn.com.frodo.algorithm.common.prim;

import java.util.ArrayList;
import java.util.List;

import cn.com.frodo.algorithm.common.IAlgorithm;

public class Prim implements IAlgorithm {
	public List<Vertex> vertexList = null;
	public List<Edge> edgeQueue = null;
	public List<Vertex> newVertex = null;
	public List<Edge> newEdge = null;

	public Prim() {
		vertexList = new ArrayList<Vertex>();// 所有顶点
		edgeQueue = new ArrayList<Edge>();// 所有边集
		newVertex = new ArrayList<Vertex>();// 已经访问过的顶点
	}

	@Override
	public void exec() {
		primTree();
	}

	private void primTree() {
		buildGraph();
		Vertex start = vertexList.get(0);
		newVertex.add(start);
		for (int n = 0; n < vertexList.size(); n++) {
			Vertex temp = new Vertex(start.key);
			Edge tempedge = new Edge(start, start, Integer.MAX_VALUE);
			for (Vertex v : newVertex) {
				for (Edge e : edgeQueue) {
					if ((e.start == v && !containVertex(e.end)) || (e.end == v && !containVertex(e.start))) {
						if (e.weight < tempedge.weight) {
							temp = e.end;
							tempedge = e;
						}

					}
				}
			}
			newVertex.add(temp);
		}

		for (int i = 0; i < newVertex.size()-1; i++) {
			Vertex a = newVertex.get(i);
			Vertex b = newVertex.get(i + 1);
			for (Edge e : edgeQueue) {
				if (e.start.key == a.key && e.end.key == b.key) {
					System.out.println("第 " + (i + 1) + " 步  ==> " + a.key + " >>>  " + b.key + "  边权  ");
				}
			}
		}
	}

	private void buildGraph() {
		Vertex v1 = new Vertex("a");
		vertexList.add(v1);
		Vertex v2 = new Vertex("b");
		vertexList.add(v2);
		Vertex v3 = new Vertex("c");
		vertexList.add(v3);
		Vertex v4 = new Vertex("d");
		vertexList.add(v4);
		Vertex v5 = new Vertex("e");
		vertexList.add(v5);
		addEdge(v1, v2, 6);
		addEdge(v1, v3, 7);
		addEdge(v2, v3, 8);
		addEdge(v2, v5, 4);
		addEdge(v2, v4, 5);
		addEdge(v3, v4, 3);
		addEdge(v3, v5, 9);
		addEdge(v5, v4, 7);
		addEdge(v5, v1, 2);
		addEdge(v4, v2, 2);
	}

	private void addEdge(Vertex a, Vertex b, int w) {
		Edge e = new Edge(a, b, w);
		edgeQueue.add(e);
	}

	private boolean containVertex(Vertex vte) {
		for (Vertex v : newVertex) {
			if (v.key.equals(vte.key))
				return true;
		}
		return false;
	}
}

/**
 * 顶点
 */
class Vertex {
	String key;

	Vertex(String key) {
		this.key = key;
	}
}

/**
 * 边权
 */
class Edge {
	Vertex start;
	Vertex end;
	int weight;

	Edge(Vertex start, Vertex end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public boolean contain(Vertex a, Vertex b) {
		if ((start.key == a.key && end.key == b.key) || (end.key == a.key && start.key == b.key)) {
			return true;
		}
		return false;
	}
}
