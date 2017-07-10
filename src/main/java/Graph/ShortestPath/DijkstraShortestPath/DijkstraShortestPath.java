/**
 * 
 */
package Graph.ShortestPath.DijkstraShortestPath;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import Graph.BinaryHeap.BinaryMinHeap;

/**
 * @author vinod<vinodpal458@gmail.com
 */
public class DijkstraShortestPath {
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String... args) {
		Graph<Integer> graph = new Graph<>(false);
		/*
		0 1 5
		0 2 2
		0 3 5
		1 4 5
		2 4 2
		3 4 5
		*/
		//The values of source Vertex and destination vertex.
			
		
		DijkstraShortestPath dsp = new DijkstraShortestPath();
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 2, 2);
		graph.addEdge(0, 3, 5);
		graph.addEdge(1, 4, 5);
		graph.addEdge(2, 4, 2);
		graph.addEdge(3, 4, 5);
		Integer sourceVertex = 0;
		Map<Vertex<Integer>, Integer> distanceWithBike = dsp.shortestPath(graph, graph.getVertex(sourceVertex));
		System.out.println(distanceWithBike);
		
		
	}
	
	
	
	
	
	
	public Map<Vertex<Integer>, Integer> shortestPath(Graph<Integer> graph, Vertex<Integer> sourceVertex) {
		// stores shortest distance from root to every vertex
		Map<Vertex<Integer>, Integer> distanceFromSource = new HashMap<>();
		// stores parent of every vertex in shortest distance
		Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();
		// heap + map data structure
		BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();
		// initialize all vertex with infinite distance from source vertex
		for (Vertex<Integer> vertes : graph.getAllVertex()) {
			minHeap.add(Integer.MAX_VALUE, vertes);
		}

		// set distance of source vertex to 0
		minHeap.decrease(sourceVertex, 0);
		// put it in map
		distanceFromSource.put(sourceVertex, 0);
		Vertex<Integer> currentVertex = null;// minHeap.extractMin();
		// iterate till heap is not empty
		while (!minHeap.empty()) {
			// get the min value from heap node which has vertex and distance of
			// that vertex from source vertex.
			BinaryMinHeap<Vertex<Integer>>.Node heapNode = minHeap.extractMinNode();
			currentVertex = heapNode.key;
			// update shortest distance of current vertex from source vertex
			distanceFromSource.put(currentVertex, heapNode.weight);
			// iterate through all edges of current vertex
			for (Edge<Integer> edge : currentVertex.getEdges()) {
				// get the adjacent vertex
				Vertex<Integer> adjacent = getVertexForEdge(currentVertex, edge);
				// if heap does not contain adjacent vertex means adjacent
				// vertex already has
				// shortest distance from source vertex
				if (!minHeap.containsData(adjacent))
					continue;
				// add distance of current vertex to edge weight to get distance
				// of adjacent
				// vertex from source vertex
				// when it goes through current vertex
				// see if this above calculated distance is less than current
				// distance stored
				// for adjacent vertex from source vertex
				if (distanceFromSource.get(currentVertex) + edge.getWeight() < minHeap.getWeight(adjacent)) {
					minHeap.decrease(adjacent, distanceFromSource.get(currentVertex) + edge.getWeight());
					parent.put(adjacent, currentVertex);
				}
			}
		}

		return distanceFromSource;
	}
	
	
	private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}
}
