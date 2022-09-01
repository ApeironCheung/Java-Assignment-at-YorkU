import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphsModel {
	/**
	 * Implements a shortest path algorithm based on Dijkstra's
	 * stops as soon as the path to the destination is computed
	 * (does NOT compute the complete shortest path tree)
	 * @param graph input graph
	 * @param from starting vertex
	 * @param to destination
	 * @return List of cities visited, in order, from source to the destination (both are included), 
	 * followed by distance in km 
	 */
	public static List<String> shortestPath(Graph graph, String from, String to) {
		List <String> path = new ArrayList<>();
		Map <String, Double> distTo = new HashMap <>();
		Map <String, Edge> edgeTo = new HashMap <>();
		PriorityQueue <String> verticesPQ = new PriorityQueue<>(new Comparator <String>() {
			@Override
			public int compare(String v1, String v2) {
				return distTo.get(v1)>distTo.get(v2) ? 1 : -1;
			}
		});

		for (String str: graph.vertices()) {
			distTo.put(str, Double.POSITIVE_INFINITY);
		}
		distTo.put(from, 0.0);

		for (verticesPQ.add(from); !verticesPQ.isEmpty();) {
			String currentV = verticesPQ.remove();
			if (currentV.equals(to)) {
				//System.out.println("FOUND!");
				if (!from.equals(to)) //if the path is not empty, go backwards and build the path to return
					do {
						path.add(0, edgeTo.get(currentV).either().name); //"either()" is the starting vertex
						currentV = edgeTo.get(currentV).either().name;
					}while (!currentV.equals(from));
				path.add(to);
				path.add(distTo.get(to).toString());		
				return path;
			}
			
			//relaxation
			for (Edge edge : graph.adj.get(currentV)) {
				if (distTo.get(currentV) + edge.weight() < distTo.get(edge.other(new Vertex(currentV)).name)){
					distTo.put(edge.other(new Vertex(currentV)).name, distTo.get(currentV) + edge.weight());
					edgeTo.put(edge.other(new Vertex(currentV)).name, edge);
					verticesPQ.add(edge.other(new Vertex(currentV)).name);
				}
			}
		}
		return null;//this line should never be reached
	}

	/**
	 * Utilizes the shortest path algorithm to output a list of cities that are within a specified 
	 * distance from the origin
	 * @param graph input graph
	 * @param origin the origin city
	 * @return List of cities within the specified distance from the origin, ordered from the closest
	 * to the farthest, followed by a distance in km each (quotes just illustrate that those are String objects)
	 * e.g., ["Etobicoke 13", "Mississauga 27.5"] 
	 */
	public static List<String> nearby(Graph graph, String origin, double distance) {
		List<String> result = new ArrayList<>();
		for (String city:graph.vertices()) {
			List<String> path = shortestPath(graph, origin, city);
			if (Double.valueOf(path.get(path.size()-1)) <= distance && !city.equals(origin)) {
				result.add(city + " " + path.get(path.size()-1));
			}
		}
		return result;
	}
	
}
