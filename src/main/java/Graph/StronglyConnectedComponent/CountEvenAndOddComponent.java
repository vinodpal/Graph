/**
 * 
 */
package Graph.StronglyConnectedComponent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

/**
 @author vinod<vinodpal458@gmail.com
 */

/*You are given a graph with N nodes and M directed edges. Find C−D.

Where,

C= Sum of number of nodes in all Strongly Connected Components with odd number of nodes.

D= Sum of number of nodes in all Strongly Connected Components with even number of nodes.

Input:

First line contains 2 integers, 
N and M, denoting the number of nodes and the number of edges. 
Next M lines contain 2 integers 
A and B, meaning that there is a directed edge from A to B.


Output:

Output the number C-D.
*/
public class CountEvenAndOddComponent {

	public List<Set<Vertex<Integer>>> scc(Graph<Integer> graph) {

		// it holds vertices by finish time in reverse order.
		Deque<Vertex<Integer>> stack = new ArrayDeque<>();
		// holds visited vertices for DFS.
		Set<Vertex<Integer>> visited = new HashSet<>();

		// populate stack with vertices with vertex finishing last at the top.
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			if (visited.contains(vertex)) {
				continue;
			}
			DFSUtil(vertex, visited, stack);
		}

		// reverse the graph.
		Graph<Integer> reverseGraph = reverseGraph(graph);

		// Do a DFS based off vertex finish time in decreasing order on reverse
		// graph..
		visited.clear();
		List<Set<Vertex<Integer>>> result = new ArrayList<>();
		while (!stack.isEmpty()) {
			Vertex<Integer> vertex = reverseGraph.getVertex(stack.poll().getId());
			if (visited.contains(vertex)) {
				continue;
			}
			Set<Vertex<Integer>> set = new HashSet<>();
			DFSUtilForReverseGraph(vertex, visited, set);
			result.add(set);
		}
		return result;
	}

	private Graph<Integer> reverseGraph(Graph<Integer> graph) {
		Graph<Integer> reverseGraph = new Graph<>(true);
		for (Edge<Integer> edge : graph.getAllEdges()) {
			reverseGraph.addEdge(edge.getVertex2().getId(), edge.getVertex1().getId(), edge.getWeight());
		}
		return reverseGraph;
	}

	private void DFSUtil(Vertex<Integer> vertex, Set<Vertex<Integer>> visited, Deque<Vertex<Integer>> stack) {
		visited.add(vertex);
		for (Vertex<Integer> v : vertex.getAdjacentVertexes()) {
			if (visited.contains(v)) {
				continue;
			}
			DFSUtil(v, visited, stack);
		}
		stack.offerFirst(vertex);
	}

	private void DFSUtilForReverseGraph(Vertex<Integer> vertex, Set<Vertex<Integer>> visited,
			Set<Vertex<Integer>> set) {
		visited.add(vertex);
		set.add(vertex);
		for (Vertex<Integer> v : vertex.getAdjacentVertexes()) {
			if (visited.contains(v)) {
				continue;
			}
			DFSUtilForReverseGraph(v, visited, set);
		}
	}

	public static void main(String args[]) {
		
		Graph<Integer> graph = new Graph<>(true);
		/*graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(1, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 3);
		graph.addEdge(5, 6);*/
		graph.addEdge(1, 4);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 1);

		CountEvenAndOddComponent scc = new CountEvenAndOddComponent();
		List<Set<Vertex<Integer>>> result = scc.scc(graph);

		// print the result of Strongly Connected Component
		int oddCoponent = 0;
		int evenCoponent = 0;
		Iterator itr= result.iterator();
		while(itr.hasNext()){
			Set currentComponent = (HashSet) itr.next();
			if((currentComponent.size()&1)==0)
				evenCoponent+=currentComponent.size();
			else oddCoponent+=currentComponent.size();;
		}
		System.out.println(oddCoponent-evenCoponent);
	}
}
