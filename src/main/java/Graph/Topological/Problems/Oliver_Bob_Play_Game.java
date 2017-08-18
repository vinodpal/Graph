/**
 * https://www.hackerearth.com/practice/algorithms/graphs/topological-sort/practice-problems/algorithm/oliver-and-the-game-3/description/
 */
package Graph.Topological.Problems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Graph.Topological.Problems.Oliver_Bob_Play_Game.Graph;


public class Oliver_Bob_Play_Game {
	class Graph<T> {

		private List<Edge<T>> allEdges;
		private Map<Long, Vertex<T>> allVertex;
		boolean isDirected = false;

		public Graph(boolean isDirected) {
			allEdges = new ArrayList<Edge<T>>();
			allVertex = new HashMap<Long, Vertex<T>>();
			this.isDirected = isDirected;
		}

		public void addEdge(long id1, long id2) {
			addEdge(id1, id2, 0);
		}

		// This works only for directed graph because for undirected graph we can
		// end up
		// adding edges two times to allEdges
		public void addVertex(Vertex<T> vertex) {
			if (allVertex.containsKey(vertex.getId())) {
				return;
			}
			allVertex.put(vertex.getId(), vertex);
			for (Edge<T> edge : vertex.getEdges()) {
				allEdges.add(edge);
			}
		}

		public Vertex<T> addSingleVertex(long id) {
			if (allVertex.containsKey(id)) {
				return allVertex.get(id);
			}
			Vertex<T> v = new Vertex<T>(id);
			allVertex.put(id, v);
			return v;
		}

		public Vertex<T> getVertex(long id) {
			return allVertex.get(id);
		}

		public void addEdge(long id1, long id2, int weight) {
			Vertex<T> vertex1 = null;
			if (allVertex.containsKey(id1)) {
				vertex1 = allVertex.get(id1);
			} else {
				vertex1 = new Vertex<T>(id1);
				allVertex.put(id1, vertex1);
			}
			Vertex<T> vertex2 = null;
			if (allVertex.containsKey(id2)) {
				vertex2 = allVertex.get(id2);
			} else {
				vertex2 = new Vertex<T>(id2);
				allVertex.put(id2, vertex2);
			}

			Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
			allEdges.add(edge);
			vertex1.addAdjacentVertex(edge, vertex2);
			if (!isDirected) {
				vertex2.addAdjacentVertex(edge, vertex1);
			}

		}

		public List<Edge<T>> getAllEdges() {
			return allEdges;
		}

		public Collection<Vertex<T>> getAllVertex() {
			return allVertex.values();
		}

		public void setDataForVertex(long id, T data) {
			if (allVertex.containsKey(id)) {
				Vertex<T> vertex = allVertex.get(id);
				vertex.setData(data);
			}
		}

		@Override
		public String toString() {
			StringBuffer buffer = new StringBuffer();
			for (Edge<T> edge : getAllEdges()) {
				buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
				buffer.append("\n");
			}
			return buffer.toString();
		}
	}
	
	
	 class Edge<T> {
			private boolean isDirect = false;
			private int weight;
			private Vertex<T> vertex1;
			private Vertex<T> vertex2;

			Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
				this.vertex1 = vertex1;
				this.vertex2 = vertex2;
			}

			Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirect, int weight) {
				this.vertex1 = vertex1;
				this.vertex2 = vertex2;
				this.isDirect = isDirect;
				this.weight = weight;
			}

			Edge(Vertex<T> vertex1, Vertex<T> vertex2, int weight) {
				this.vertex1 = vertex1;
				this.vertex2 = vertex2;
				this.weight = weight;
			}

			Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirect) {
				this.vertex1 = vertex1;
				this.vertex2 = vertex2;
				this.isDirect = isDirect;
			}

			public boolean isDirect() {
				return isDirect;
			}

			public void setDirect(boolean isDirect) {
				this.isDirect = isDirect;
			}

			public int getWeight() {
				return weight;
			}

			public void setWeight(int weight) {
				this.weight = weight;
			}

			public Vertex<T> getVertex1() {
				return vertex1;
			}

			public void setVertex1(Vertex<T> vertex1) {
				this.vertex1 = vertex1;
			}

			public Vertex<T> getVertex2() {
				return vertex2;
			}

			public void setVertex2(Vertex<T> vertex2) {
				this.vertex2 = vertex2;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
				result = prime * result + ((vertex2 == null)||(result==0) ? 0 : vertex2.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (obj == null)
					return false;
				if (obj == this)
					return true;
				if (getClass() != obj.getClass())
					return false;
				Edge edge = (Edge) obj;
				if (vertex1 == null) {
					if (edge.getVertex1() != null) {
						return false;
					}
				} else if (!vertex1.equals(edge.vertex1))
					return false;
				if (vertex2 == null) {
					if (edge.getVertex2() != null) {
						return false;
					}
				} else if (!vertex2.equals(edge.vertex2))
					return false;
				return true;
			}

		}
	 
	 class Vertex<T> {
			long id;
			private T data;
			private List<Edge<T>> edges = new ArrayList<>();
			private List<Vertex<T>> adjacentVertex = new ArrayList<>();

			Vertex(long id) {
				this.id = id;
			}

			public long getId() {
				return id;
			}

			public void setData(T data) {
				this.data = data;
			}

			public T getData() {
				return data;
			}

			public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
				edges.add(e);
				adjacentVertex.add(v);
			}

			public String toString() {
				return String.valueOf(id);
			}

			public List<Vertex<T>> getAdjacentVertexes() {
				return adjacentVertex;
			}

			public List<Edge<T>> getEdges() {
				return edges;
			}

			public int getDegree() {
				return edges.size();
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + (int) (id ^ (id >>> 32));
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Vertex other = (Vertex) obj;
				if (id != other.id)
					return false;
				return true;
			}
		}
	static int count = 0;

	public static void topologicalSort(Vertex<Integer> vertex, Map<Vertex<Integer>,Integer> inVertex,
			Map<Vertex<Integer>,Integer> outVertex, ArrayList<Vertex> visited) {
		visited.add(vertex);
		List<Edge<Integer>> edges = vertex.getEdges();
		Vertex<Integer> adjacentVertex = null;
		inVertex.put(vertex,++count);
		for (Edge edge : edges) {
			adjacentVertex = getAdjacentVertex(edge, vertex);
			if (!visited.contains(adjacentVertex)) {
				topologicalSort(adjacentVertex, inVertex, outVertex, visited);
			}
		}
		outVertex.put(vertex,++count);
	}

	public static Vertex getAdjacentVertex(Edge edge, Vertex vertex) {
		return edge.getVertex1().equals(vertex) ? edge.getVertex2() : edge.getVertex1();
	}

	public static void main(String... ags) {
		Oliver_Bob_Play_Game object= new Oliver_Bob_Play_Game();
		Oliver_Bob_Play_Game.Graph<Integer> graph =object.new Graph<>(false);
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n - 1; ++i) {
			graph.addEdge(sc.nextInt(), sc.nextInt());
		}
		Map<Vertex<Integer>, Integer> inVertex = new HashMap<Vertex<Integer>, Integer>();
		Map<Vertex<Integer>, Integer> outVertex = new HashMap<Vertex<Integer>, Integer>();
		ArrayList<Vertex> visited = new ArrayList<Vertex>();
		topologicalSort(graph.getVertex(1), inVertex, outVertex, visited);
		int q = sc.nextInt();
		int direction = 0;
		int x = 0;
		int y = 0;
		int countConnectedKingsHome = 0;
		for (int i = 0; i < q; ++i) {
			direction = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();
			if ((direction == 0 && getInsideOfRoot(x, y, inVertex, outVertex, graph))
					|| (direction == 1 && getInsideOfRoot(y, x, inVertex, outVertex, graph)))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	static boolean getInsideOfRoot(int x, int y,Map<Vertex<Integer>,Integer> inVertex ,
			Map<Vertex<Integer>, Integer> outVertex, Graph<Integer> graph) {
		if (inVertex.get(graph.getVertex(x)) <= inVertex.get(graph.getVertex(y))
				&& (outVertex.get(graph.getVertex(x)) >= outVertex.get(graph.getVertex(y)))) {
			return true;
		} else
			return false;
	}
}
