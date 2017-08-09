/**
 * 
 */
package Graph.ShortestPath.DijkstraShortestPath.Problems;

/*https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/smart-travel-agent/description/
 * 
 * Our smart travel agent, Mr. X's current assignment is to show a group of tourists a distant city. As in all countries, certain pairs of cities are connected by two-way roads. Each pair of neighboring cities has a bus service that runs only between those two cities and uses the road that directly connects them. Each bus service has a particular limit on the maximum number of passengers it can carry. Mr. X has a map showing the cities and the roads connecting them, as well as the service limit for each bus service.

It is not always possible for him to take all tourists to the destination city in a single trip. For example, consider the following road map of seven cities, where the edges represent roads and the number written on each edge indicates the passenger limit of the associated bus service.

In the diagram below, 
It will take at least five trips for Mr. X. to take 99 tourists from city 1 to city 7, 
since he has to ride the bus with each group. The best route to take is 1 - 2 - 4 - 7.



Problem:
What is the best way for Mr. X to take all tourists to the destination city in the minimum number of trips?

[Input]:
The first line will contain two integers: N (N ≤ 100) and R, representing the number of cities and the number of road segments, respectively. Each of the next R lines will contain three integers (C1, C2, and P) where C1 and C2 are the city numbers and P (P > 1) is the maximum number of passengers that can be carried by the bus service between the two cities. City numbers are positive integers ranging from 1 to N. The (R+1)th line will contain three integers (S, D, and T) representing, respectively, the starting city, the destination city, and the number of tourists to be guided.

[Output]:
The output should contain 2 lines - the first line giving the route taken and the second line giving the minimum number of trips

[Note]: If multiple solutions exist . Print lexicographically-smallest path .
Agent is also travelling in the same path so he has to be counted while travelling in that path for each trip.


Input:-					Output:-
					
7 10					1 2 4 7
1 2 30					5
1 3 15
1 4 10
2 4 25
2 5 60
3 4 40
3 6 20
4 7 35
5 7 20
6 7 30
1 7 99


 * */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class SmartTravelAgent {

	class BinaryMaxHeap<T> {

		private List<Node> allNodes = new ArrayList<>();
		private Map<T, Integer> nodePosition = new HashMap<>();

		public class Node {
			public int weight;
			public T key;
		}

		/**
		 * Checks where the key exists in heap or not
		 */
		public boolean containsData(T key) {
			return nodePosition.containsKey(key);
		}

		/**
		 * Add key and its weight to they heap
		 */
		public void add(int weight, T key) {
			Node node = new Node();
			node.weight = weight;
			node.key = key;
			allNodes.add(node);
			int size = allNodes.size();
			int current = size - 1;
			int parentIndex = (current - 1) / 2;
			nodePosition.put(node.key, current);

			while (parentIndex >= 0) {
				Node parentNode = allNodes.get(parentIndex);
				Node currentNode = allNodes.get(current);
				if (parentNode.weight < currentNode.weight) {
					swap(parentNode, currentNode);
					updatePositionMap(parentNode.key, currentNode.key, parentIndex, current);
					current = parentIndex;
					parentIndex = (parentIndex - 1) / 2;
				} else {
					break;
				}
			}
		}

		/**
		 * Get the heap min without extracting the key
		 */
		public T max() {
			return allNodes.get(0).key;
		}

		/**
		 * Checks with heap is empty or not
		 */
		public boolean empty() {
			return allNodes.size() == 0;
		}

		/**
		 * Decreases the weight of given key to newWeight
		 */
		public void decrease(T data, int newWeight) {
			Integer position = nodePosition.get(data);
			allNodes.get(position).weight = newWeight;
			int parent = (position - 1) / 2;
			while (parent >= 0) {
				if (allNodes.get(parent).weight < allNodes.get(position).weight) {
					swap(allNodes.get(parent), allNodes.get(position));
					updatePositionMap(allNodes.get(parent).key, allNodes.get(position).key, parent, position);
					position = parent;
					parent = (parent - 1) / 2;
				} else {
					break;
				}
			}
		}

		/**
		 * Get the weight of given key
		 */
		public Integer getWeight(T key) {
			Integer position = nodePosition.get(key);
			if (position == null) {
				return null;
			} else {
				return allNodes.get(position).weight;
			}
		}

		/**
		 * Returns the min node of the heap
		 */
		public Node extractMaxNode() {
			int size = allNodes.size() - 1;
			Node minNode = new Node();
			minNode.key = allNodes.get(0).key;
			minNode.weight = allNodes.get(0).weight;

			int lastNodeWeight = allNodes.get(size).weight;
			allNodes.get(0).weight = lastNodeWeight;
			allNodes.get(0).key = allNodes.get(size).key;
			nodePosition.remove(minNode.key);
			nodePosition.remove(allNodes.get(0));
			nodePosition.put(allNodes.get(0).key, 0);
			allNodes.remove(size);

			int currentIndex = 0;
			size--;
			while (true) {
				int left = 2 * currentIndex + 1;
				int right = 2 * currentIndex + 2;
				if (left > size) {
					break;
				}
				if (right > size) {
					right = left;
				}
				int smallerIndex = allNodes.get(left).weight >= allNodes.get(right).weight ? left : right;
				if (allNodes.get(currentIndex).weight < allNodes.get(smallerIndex).weight) {
					swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
					updatePositionMap(allNodes.get(currentIndex).key, allNodes.get(smallerIndex).key, currentIndex,
							smallerIndex);
					currentIndex = smallerIndex;
				} else {
					break;
				}
			}
			return minNode;
		}

		/**
		 * Extract min value key from the heap
		 */
		public T extractMax() {
			Node node = extractMaxNode();
			return node.key;
		}

		private void printPositionMap() {
			System.out.println(nodePosition);
		}

		private void swap(Node node1, Node node2) {
			int weight = node1.weight;
			T data = node1.key;

			node1.key = node2.key;
			node1.weight = node2.weight;

			node2.key = data;
			node2.weight = weight;
		}

		private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
			nodePosition.remove(data1);
			nodePosition.remove(data2);
			nodePosition.put(data1, pos1);
			nodePosition.put(data2, pos2);
		}

		public void printHeap() {
			for (Node n : allNodes) {
				System.out.println(n.weight + " " + n.key);
			}
		}

	}
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

		// This works only for directed graph because for undirected graph we
		// can
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

	class Edge<T> {
		private boolean isDirect = false;
		private int weight;
		private Vertex<T> vertex1;
		private Vertex<T> vertex2;
		private boolean vehicle;

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

		Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirect, int weight, boolean vehicle) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.isDirect = isDirect;
			this.weight = weight;
			this.vehicle = vehicle;
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

		public boolean isVehicle() {
			return vehicle;
		}

		public void setVehicle(boolean vehicle) {
			this.vehicle = vehicle;
		}
	}

	public static void main(String... args) {
		SmartTravelAgent dsp = new SmartTravelAgent();
		SmartTravelAgent.Graph<Integer> graph = dsp.new Graph<>(false);
		/*
		 * 5 6 12 25 0 4 0 1 5 0 0 2 2 1 0 3 5 0 1 4 5 0 2 4 2 1 3 4 5 0
		 */
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		// M lines contains edges from one restaurant to others.
		// true for (Bike/Bicycle) and false for Bicycle
		for (int itr = 0; itr < m; ++itr) {
			graph.addEdge(scanner.nextLong(), scanner.nextLong(), scanner.nextInt());
		}

		// The values of source Vertex and destination vertex.
		Integer sourceV = scanner.nextInt(); // 0
		Integer destinationV = scanner.nextInt(); // 4
		int passengers = scanner.nextInt();
		Vertex<Integer> sourceVertex = graph.getVertex(sourceV); // 0 Vertex
		Vertex<Integer> destination = graph.getVertex(destinationV); // 4 Vertex
		Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();
		Map<Vertex<Integer>, Integer> traveling = dsp.MaximumMinimumWeightPathTravelAgent(graph, sourceVertex,parent);
		int minimumPath = traveling.get(destination);
		int ans = passengers / ( minimumPath- 1);
		if (passengers % ( minimumPath- 1) > 0) {
			++ans;
		}
		Vertex<Integer> iteratorVertex = destination;
		List<Long> listPath = new ArrayList<>();
		while(iteratorVertex.getId()!=sourceVertex.getId()){
			listPath.add(iteratorVertex.getId());
			iteratorVertex = parent.get(iteratorVertex);
		}
		listPath.add(sourceVertex.getId());
		Collections.reverse(listPath);
		listPath.forEach(x-> System.out.print(x+" "));
		System.out.println();
		System.out.println(ans);
	}

	public Map<Vertex<Integer>, Integer> MaximumMinimumWeightPathTravelAgent(Graph<Integer> graph,
			Vertex<Integer> sourceVertex,Map<Vertex<Integer>, Vertex<Integer>> parent) {

		// heap + map data structure
		BinaryMaxHeap<Vertex<Integer>> maxHeap = new BinaryMaxHeap<>();

		// stores shortest distance from root to every vertex
		Map<Vertex<Integer>, Integer> distance = new HashMap<>();

		
		// initialize all vertex with infinite distance from source vertex
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			maxHeap.add(Integer.MIN_VALUE, vertex);
		}

		// set distance of source vertex to 0
		maxHeap.decrease(sourceVertex, Integer.MAX_VALUE);

		// put it in map
		distance.put(sourceVertex, Integer.MAX_VALUE);
		/*
		 * //source vertex parent is null parent.put(sourceVertex, null);
		 */

		// iterate till heap is not empty
		while (!maxHeap.empty()) {
			// get the min value from heap node which has vertex and distance of
			// that vertex
			// from source vertex.
			BinaryMaxHeap<Vertex<Integer>>.Node heapNode = maxHeap.extractMaxNode();
			Vertex<Integer> current = heapNode.key;

			// update shortest distance of current vertex from source vertex
			distance.put(current, heapNode.weight);

			// iterate through all edges of current vertex
			for (Edge<Integer> edge : current.getEdges()) {

				// get the adjacent vertex
				Vertex<Integer> adjacent = getVertexForEdge(current, edge);

				// if heap does not contain adjacent vertex means adjacent
				// vertex already has
				// shortest distance from source vertex
				if (!maxHeap.containsData(adjacent)) {
					continue;
				}

				// add distance of current vertex to edge weight to get distance
				// of adjacent
				// vertex from source vertex
				// when it goes through current vertex
				int newDistance = Math.max(maxHeap.getWeight(adjacent),Math.min((distance.get(current)), edge.getWeight()));

				// see if this above calculated distance is less than current
				// distance stored
				// for adjacent vertex from source vertex
				if (maxHeap.getWeight(adjacent) < newDistance) {
					maxHeap.decrease(adjacent, newDistance);
					parent.put(adjacent, current);
				}
			}
		}
		return distance;
	}

	private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}
}
