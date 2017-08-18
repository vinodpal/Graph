/**
 * 
 */
package Graph.Topological;

import java.util.ArrayList;
import java.util.List;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

public class Topological<T> {
	public void topologicalSort(Vertex<T> vertex,ArrayList<Vertex> sortedVertex, 
			ArrayList<Vertex> visited){
		visited.add(vertex);
		List<Edge<T>> edges = vertex.getEdges();
		Vertex<T> adjacentVertex = null;
		for(Edge edge : edges){
			adjacentVertex = getAdjacentVertex(edge,vertex);
			if(!visited.contains(adjacentVertex)){
				topologicalSort(adjacentVertex, sortedVertex, visited);
			}
		}
		sortedVertex.add(vertex);
	}
	public static Vertex getAdjacentVertex(Edge edge, Vertex vertex){
		return edge.getVertex1().equals(vertex)?edge.getVertex2():edge.getVertex1();
	}
	public static void main(String ...ags){
		Topological<Integer> topological = new Topological();
		Graph<Integer> graph = new Graph(false);
		graph.addEdge(1,2);
		graph.addEdge(1,3);
		graph.addEdge(2,3);
		graph.addEdge(2,7);
		graph.addEdge(3,4);
		graph.addEdge(4,5);
		ArrayList<Vertex> sortedVertex = new ArrayList<Vertex>();  
		ArrayList<Vertex> visited =  new ArrayList<Vertex>();
		topological.topologicalSort(graph.getVertex(1),sortedVertex, visited);
		sortedVertex.forEach(x->System.out.print(x+" , "));
	}
	
}
