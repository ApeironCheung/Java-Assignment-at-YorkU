
/**
 * Implements a graph edge.
 * The edge consists of two Vertex elements and also has a weight
 * @author Andriy
 */

public class Edge{
	private final Vertex v; 		// one vertex
	private final Vertex w; 		// the other vertex
	private final double weight; 	// edge weight

	public Edge(Vertex v, Vertex w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight(){ 
		return weight; 
	}
	
	public Vertex either(){ 
		return v; 
	}
	
	public Vertex other(Vertex vertex){
		if (vertex.name.equals(v.name)) return w;
		else if (vertex.name.equals(w.name)) return v;
		else throw new RuntimeException("Inconsistent edge");
	}
	
//	/**
//	 * Compares an edge to another edge based on its weight 
//	 */
//	public int compareTo(Edge that)
//	{
//		if (this.weight() < that.weight()) return -1;
//		else if (this.weight() > that.weight()) return +1;
//		else return 0;
//	}
//
//	
	public String toString(){ 
		return String.format("%s-%s=%.1f km", v, w, weight); 
	}
}
