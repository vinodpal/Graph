/**
 * 
 */
package Graph.ShortestPath.DijkstraShortestPath.Problems;

import java.util.Scanner;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

public class CostlyPhoneNumber {
	public static void main(String ...args){
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int [] input = new int[10];
		int targetNumber;
		int lengthOfTargetNumber;
		
		Graph<Integer> graph = new Graph<>(false);
		while((t--)>0){
			
			for(int i=0;i<10;++i){
				input[i]=sc.nextInt();
				graph.addEdge(10, i,input[i]);
			}
			boolean checkNonRepeat = true;
			while (checkNonRepeat) {
				checkNonRepeat = false;
				Vertex<Integer> vertex = graph.getVertex(10);
				/*for (int i = 0; i < 10; ++i) {
					for (int j = 0; j < 10; ++j) {
						vertex = graph.getVertex(10);
						if(vertex.getAdjacentVertexes()){
							
						}
					}
				}*/
				for(Edge<Integer> edge : vertex.getEdges()){
					for(Edge<Integer> edgeInner : vertex.getEdges()){
						
					}
				}
			}
			
			
		}
		
	}
	
	Vertex<Integer> getAdjecentVertex(Edge<Integer> edge, Vertex<Integer> vertex) {
		return (edge.getVertex1().equals(vertex) ? edge.getVertex2() : edge.getVertex1());
	}
}
