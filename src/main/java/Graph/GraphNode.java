
/**
 * @author vinod<vinodpal458@gmail.com
 */
/**
 * This class is useful generate vertex and related to edges for getters and setters. 
 */
package Graph;

import java.util.ArrayList;
import java.util.Comparator;

public class GraphNode {
	private String vertex;
	private ArrayList<String> edgesVertex;

	public String getVertex() {
		return vertex;
	}

	public void setVertex(String vertex) {
		this.vertex = vertex;
	}

	public ArrayList<String> getEdgesVertex() {
		return edgesVertex;
	}

	public void setEdgesVertex(ArrayList<String> edgesVertex) {
		if (edgesVertex == null) {
			edgesVertex = new ArrayList<String>();
		}
		this.edgesVertex = edgesVertex;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		else if (this == object)
			return true;
		else if (!(object instanceof GraphNode)) {
			return this.vertex.equals(((GraphNode) object).vertex);
		} else
			return false;
	}

	@Override
	public String toString() {
		return "Vertex : " + this.getVertex() + " Connected Edges Vertex : " + this.getEdgesVertex().toString();
	}

	/**
	 * Comparator for sorting the list by graph vertex of Graph Node
	 */
	public static Comparator<GraphNode> graphNodeComparator = new Comparator<GraphNode>() {
		public int compare(GraphNode object1, GraphNode object2) {
			return object1.getVertex().compareTo(object2.getVertex());
		}
	};
}
