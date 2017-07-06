/**
 * 
 */
package Graph;


/**
 * @author vinod<vinodpal458@gmail.com
 */

public class Edge<T> {
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