/**
 * 
 * 
Given tha there are 'n' (Indexed from 0 to n-1) restaurants and 'm' edges. Each route (or edge)
would be either classified to be used by (Motor-bikes, Bicycle) or only bicycles. Length of each edge is given as well (in kms).

Now, we need to reach from restaurant x to y in minimum time. Bicyle moves at a speed of v1 km/hr and mortor bike moves at a speed of v2 km/hr. Tell us which vehicle to use if we want to reach the target restaurant in minimum time.

Input Format : First line gives the number of restaurant and number of edges (2 <=n <=1000, 0<m<=n(n-1)/2).

Next line tells the speed of both the vehicles(0<v1<v2<60)
Next Line give the values (source and destination) of x and y ( 0<=x,y<=n-1)
Next m lines contains edges from restaurant.

n m
v1 v2
x y
r11 r12 d1 b1
r11 r12 d1 b1
r11 r12 d1 b1
...
...
...

Output: Motor Bike Or Bicycle



Sample Input:-

5 6
12 25
0 4
0 1 5 0
0 2 2 1
0 3 5 0
1 4 5 0
2 4 2 1
3 4 5 0


Sample Output:-
Bicyle

 * 
 */
package Graph.ShortestPath.DijkstraShortestPath.Problems;
/**
 * @author vinod<vinodpal458@gmail.com
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Graph.BinaryHeap.BinaryMinHeap;

public class BikeOrBicycle {

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
			public void addEdge(long id1, long id2, int weight,boolean vehicle) {
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

				Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight,vehicle);
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
		BikeOrBicycle dsp = new BikeOrBicycle();
		BikeOrBicycle.Graph<Integer> graph = dsp.new Graph<>(false);
		/*
		5 6
		12 25
		0 4
		0 1 5 0
		0 2 2 1
		0 3 5 0
		1 4 5 0
		2 4 2 1
		3 4 5 0
		*/
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		//The speed of both the vehicles 
		Integer speedWithBike = scanner.nextInt();  //12
		Integer speedWithoutBike = scanner.nextInt(); //25
		
		//The values of source Vertex and destination vertex.
		Integer sourceV = scanner.nextInt();	//0
		Integer destinationV = scanner.nextInt();  //4
		
		// M lines contains edges from one restaurant to others.
		// true for (Bike/Bicycle) and false for Bicycle
		for(int itr=0;itr<m;++itr) {
			graph.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()==0?true:false);
		}
		
		
		
		Vertex<Integer> sourceVertex = graph.getVertex(sourceV); //0 Vertex
		Vertex<Integer> destination = graph.getVertex(destinationV); //4 Vertex 
		
		Map<Vertex<Integer>, Integer> distanceWithBike = dsp.shortestPathViaVehicle(graph, sourceVertex, true);
		Map<Vertex<Integer>, Integer> distanceWithoutBike = dsp.shortestPathViaVehicle(graph, sourceVertex, false);
		if (distanceWithBike.get(destination) == Integer.MAX_VALUE) {
			if (distanceWithoutBike.get(destination) == Integer.MAX_VALUE) {
				System.out.println("NOT POSSIBLE!!!");
			} else {
				speedWithoutBike = distanceWithoutBike.get(destination)/speedWithoutBike;
			}
		} else {
			speedWithBike = distanceWithoutBike.get(destination) / speedWithBike;
			if (distanceWithoutBike.get(destination) == Integer.MAX_VALUE) {
				System.out.println("NOT POSSIBLE!!!");
			} else {
				speedWithoutBike = distanceWithoutBike.get(destination) / speedWithoutBike;
			}
		}
		if(speedWithoutBike>speedWithBike) {
			System.out.println(/*speedWithBike+*/"Motor Bike");
		}else
			System.out.println(/*speedWithoutBike+*/"Bicycle");
		
	}
	public Map<Vertex<Integer>, Integer> shortestPathViaVehicle(Graph<Integer> graph, Vertex<Integer> sourceVertex,
			boolean vehicle) {

		// heap + map data structure
		BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();

		// stores shortest distance from root to every vertex
		Map<Vertex<Integer>, Integer> distance = new HashMap<>();

		/*
		 * //stores parent of every vertex in shortest distance Map<Vertex<Integer>,
		 * Vertex<Integer>> parent = new HashMap<>();
		 */

		// initialize all vertex with infinite distance from source vertex
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			minHeap.add(Integer.MAX_VALUE, vertex);
		}

		// set distance of source vertex to 0
		minHeap.decrease(sourceVertex, 0);

		// put it in map
		distance.put(sourceVertex, 0);
		/*
		 * //source vertex parent is null parent.put(sourceVertex, null);
		 */

		// iterate till heap is not empty
		while (!minHeap.empty()) {
			// get the min value from heap node which has vertex and distance of that vertex
			// from source vertex.
			BinaryMinHeap<Vertex<Integer>>.Node heapNode = minHeap.extractMinNode();
			Vertex<Integer> current = heapNode.key;

			// update shortest distance of current vertex from source vertex
			distance.put(current, heapNode.weight);

			// iterate through all edges of current vertex
			for (Edge<Integer> edge : current.getEdges()) {

				if (edge.isVehicle() != vehicle) {
					continue;
				}
				// get the adjacent vertex
				Vertex<Integer> adjacent = getVertexForEdge(current, edge);

				// if heap does not contain adjacent vertex means adjacent vertex already has
				// shortest distance from source vertex
				if (!minHeap.containsData(adjacent)) {
					continue;
				}

				// add distance of current vertex to edge weight to get distance of adjacent
				// vertex from source vertex
				// when it goes through current vertex
				int newDistance = distance.get(current) + edge.getWeight();

				// see if this above calculated distance is less than current distance stored
				// for adjacent vertex from source vertex
				if (minHeap.getWeight(adjacent) > newDistance) {
					minHeap.decrease(adjacent, newDistance);
					// parent.put(adjacent, current);
				}
			}
		}
		return distance;
	}

	private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}
}
