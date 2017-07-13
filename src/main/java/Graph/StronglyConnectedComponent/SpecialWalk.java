/**
 * 
 */
package Graph.StronglyConnectedComponent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


/*
 * @author vinod<vinodpal458@gmail.com
 * Algorithm
 * Create a order of vertices by finish time in decreasing order.
 * Reverse the graph
 * Do a DFS on reverse graph by finish time of vertex and created strongly connected
 * components.
 * Runtime complexity - O(V + E)
 * Space complexity - O(V)
 */

/*
Link:- https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/a-walk-to-remember-qualifier2/


Dilku was thinking about the first time he met his girl... It was indeed a walk to remember. The romantic weather and her silly talks. He was completely mesmarized. Those were the days!..

Today is his girl's birthday and he wants to make it special for her. He wants to again take her on a "special walk" that they would remember for the lifetime.

The city in which Dilku lives is represented as an unweighted directed graph with N nodes and M edges. A "special walk" in the graph starting at node u is a simple path that begins and ends at the same node u.

Formally, A special walk is path u , a1 , a2 , a3 ,..., ai ,.... , u where ai are distinct and not equal to u for all i.

Now since Dilku is really nervous about taking his girl out, he needs your help. For every node in the given graph, tell whether it is possible for Dilku to take his girl on a "special walk" starting at that node.

Input:

First line of a two space separated integers denoting N and M, the number of nodes and number of directed edges in the corresponding graph. 
Following M lines contain two space separated integers u v denoting a directed edge in the graph from vertex numbered u to vertex numbered v.

Output:

Print N space separated integers, where ith integer can be either 1 or 0 depicting whether it is possible to go on a special walk starting at node i or not.



Sample Input : 
5 5
1 2 
2 3 
3 4 
4 5
4 2


Sample Output :

0 1 1 1 0 
*/
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
		result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
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
public class SpecialWalk {
	
	public static int[]  scc(Graph<Integer> graph,int[] result){
		Set<Vertex<Integer>> visited = new HashSet<>();
		Deque<Vertex<Integer>> stack = new ArrayDeque<>();
		for(Vertex<Integer> vertex : graph.getAllVertex()){
			if(!visited.contains(vertex)){
				DFSUtil(vertex,visited,stack);
			}
		}
		Graph<Integer> reverseGraph = reverseGraph(graph);
		visited.clear();
		 
		
		while(!stack.isEmpty()){
			Vertex<Integer> v = reverseGraph.getVertex(stack.poll().getId());
			if(!visited.contains(v)){
				Set<Vertex<Integer>> set = new HashSet<>();
				reverseDFSUtil(v,visited,set);
				if(1<set.size())
					for(Iterator itr = set.iterator();itr.hasNext();){
						Vertex vertex = (Vertex) itr.next();
						result[((int)vertex.getId())-1]=1;
					}
						
				
			}
		}
		return result;
	}
	
public static void DFSUtilWithoutRecusive(Vertex<Integer> vertex,Set<Vertex<Integer>> visited,Deque<Vertex<Integer>> stack){
		
		Stack<Vertex<Integer>> s = new Stack<>();
		s.push(vertex);
		while (!s.isEmpty()) {
			vertex = s.pop();
			if (!visited.contains(vertex)) {
				stack.addLast(vertex);
				visited.add(vertex);
			}
			for (Vertex<Integer> v : vertex.getAdjacentVertexes()) {
				if (!visited.contains(v)) {
					s.push(v);
				}
			}

		}
	}
	public static Graph<Integer> reverseGraph(Graph<Integer> graph) {
		Graph<Integer> rGraph = new Graph<Integer>(true);
		for (Edge edge : graph.getAllEdges()) {
			rGraph.addEdge(edge.getVertex2().getId(), edge.getVertex1().getId(), edge.getWeight());
		}
		return rGraph;
	}
	public static void DFSUtil(Vertex<Integer> vertex,Set<Vertex<Integer>> visited,Deque<Vertex<Integer>> stack){
		visited.add(vertex);
		for(Vertex<Integer> v : vertex.getAdjacentVertexes()){
			if(!visited.contains(v)){
				DFSUtil(v,visited,stack);
			}
		}
		stack.push(vertex);
	}
	public static void reverseDFSUtil(Vertex<Integer> vertex,Set<Vertex<Integer>> visited,Set<Vertex<Integer>> set){
		visited.add(vertex);
		set.add(vertex);
		for(Vertex<Integer> v : vertex.getAdjacentVertexes()){
			if(!visited.contains(v)){
				reverseDFSUtil(v,visited,set);
			}
		}
		
	}
public static void reverseDFSUtilWithoutRecusive(Vertex<Integer> vertex,Set<Vertex<Integer>> visited,Set<Vertex<Integer>> set){
		
		
		Stack<Vertex<Integer>> stack = new Stack<>();
		stack.push(vertex);
		while (!stack.isEmpty()) {
			vertex = stack.pop();
			if(!visited.contains(vertex)){
				visited.add(vertex);
				set.add(vertex);
			}
			for (Vertex<Integer> v : vertex.getAdjacentVertexes()) {
				if (!visited.contains(v)) {
					stack.push(v);
				}
			}
		}
		
	}
	
	
public static void main(String args[]) {
		
		Graph<Integer> graph = new Graph<>(true);
		/*
1 2 
2 3 
3 4 
4 5
4 2*/
		//Scanner sc = new Scanner(System.in);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(4, 2);
		//sc.nextInt();
		int[] result = new int[5];
		SpecialWalk scc = new SpecialWalk();
		scc.scc(graph,result);
		Arrays.stream(result).forEach(x->System.out.print(x+" "));
	}
	
}
