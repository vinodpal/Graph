/**
 * 
 */
package Graph.ShortestPath.DijkstraShortestPath.Problems;

import java.util.Scanner;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

public class CostlyPhoneNumber {
	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int[] input = new int[10];
		int targetNumber;
		int lengthOfTargetNumber;

		Graph<Integer> graph = new Graph<>(false);
		while ((t--) > 0) {

			for (int i = 0; i < 10; ++i) {
				input[i] = sc.nextInt();
				graph.addEdge(10, i, input[i]);
			}
			boolean checkNonRepeat = true;
			Vertex<Integer> vertex = graph.getVertex(10);
			while (checkNonRepeat) {
				checkNonRepeat = false;
				/*
				 * for (int i = 0; i < 10; ++i) { for (int j = 0; j < 10; ++j) {
				 * vertex = graph.getVertex(10);
				 * if(vertex.getAdjacentVertexes()){
				 * 
				 * } } }
				 */
				for (Edge<Integer> edge : vertex.getEdges()) {
					for (Edge<Integer> edgeInner : vertex.getEdges()) {
						int sumOfTwoVertex = (int) ((getAdjecentVertex(edge, graph.getVertex(10)).getId()
								+ getAdjecentVertex(edgeInner, graph.getVertex(10)).getId()));
						Vertex<Integer> vertexCompare = graph.getVertex(sumOfTwoVertex % 10);
						if ((vertexCompare.getEdges().get(0))
								.getWeight() > ((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0))
										.getWeight())
										+ ((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0))
												.getWeight())) {
							(vertexCompare.getEdges().get(0)).setWeight(
									((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0)).getWeight())
											+ ((getAdjecentVertex(edge, graph.getVertex(10)).getEdges().get(0))
													.getWeight()));
							checkNonRepeat = true;
						}
					}
				}
			}
			
			int lengthOfTarget = sc.nextInt();
			sc.nextLine();
			String Target = sc.nextLine();
			int ans = 0;
			int currentNumber=0;
			for(Character ch : Target.toCharArray()){
				currentNumber=Integer.parseInt(ch.toString());
				ans+=graph.getVertex(currentNumber).getEdges().get(0).getWeight();
			}
			
			System.out.println(ans);
		}
	}
	
	static Vertex<Integer> getAdjecentVertex(Edge<Integer> edge, Vertex<Integer> vertex) {
		return (edge.getVertex1().equals(vertex) ? edge.getVertex2() : edge.getVertex1());
	}
}
