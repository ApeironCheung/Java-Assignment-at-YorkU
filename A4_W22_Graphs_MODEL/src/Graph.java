import java.util.List;
import java.util.Map;

/**
 * Describes a graph with vertices V and edges E
 * @author Andriy
 *
 */
public abstract class Graph {
	protected int V; 		// number of vertices
	protected int E;		// number of edges
	protected Map <String, List<Edge>> adj; //Map of adjacency lists

	public int V() {return V;}
	public int E() {return E;}

	public Iterable<Edge> adj(Vertex v){
		return adj.get(v.name);
	}

	public Iterable<String> vertices(){
		return adj.keySet();
	}

//	public Iterable<Edge> edges (){
//		//TODO
//		return null;
//	}

	public String toString(){
		String s = V + " vertices, " + E + " edges\n";
		for (String vertex : this.adj.keySet()){
			s += vertex + ": ";
			for (Edge w : this.adj.get(vertex))
				s += w + " ";
			s += "\n";
		}
		return s;
	}
}
