import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/* ********************************
 * EECS2011 Assignment 4,         * 
 * by Pak Hang CHEUNG (218428953) *
 * and Ho Chun CHAN (217486846)   *
 *********************************/

public class Graphs {
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

		//TODO finish the implementation
        Vertex origin = new Vertex(from);     
        Vertex end = new Vertex(to);

		verticesPQ.add(from);
//        Map<Vertex, Vertex> parents = new HashMap<>();//for chasing the last vertex
        edgeTo.put(from, new Edge(origin, new Vertex("temp"), 0.0));//marked the origin
        
        while (!verticesPQ.isEmpty() && !edgeTo.containsKey(to)) {//while something in queue and not visited "to" yet
        	 Vertex current = new Vertex(verticesPQ.poll());
             for (Edge w : graph.adj(current)) {
            	 Vertex next = w.other(current);
                 if (!edgeTo.containsKey(next.name) || distTo.get(current.name) + w.weight() < distTo.get(next.name)) {//not visited or visited with a longer path
                     edgeTo.put(next.name, w);// mark visited location or update 
//                     parents.put(next, current);//use next as key and current as value, for chasing from end to origin
                     distTo.put(next.name, distTo.get(current.name) + w.weight());//distance from "from" to v = distance to w + weight of w 
                     verticesPQ.add(next.name);
                }
            }
        }
        
        List<Vertex> reverse = new ArrayList<Vertex>(); 
        Vertex temp = end;
        do {
        	reverse.add(temp);
        	temp = edgeTo.get(temp.name).other(temp);	
        }  while(!origin.equals(temp));
        
        path.add(from);
        for(int i = reverse.size() -1; i >= 0; i--) {
        	path.add(reverse.get(i).name);
        }
        path.add("" + distTo.get(to));
		return path;
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
		//DUMMY CODE (STUB) TODO
		/*		List <String>result = new ArrayList<>();
		result.add("Etobicoke 13");
		result.add("Mississauga 27.5");
		return result;		*/
		
		//exceptions
		if (distance < 0 ) throw new IllegalArgumentException();
		if (!((Set<String>) graph.vertices()).contains(origin)) throw new IllegalArgumentException(origin);
		
		Map <String, Double> distTo = new HashMap <>();
		Map <String, Edge> edgeTo = new HashMap <>();
		PriorityQueue<String> pq = new PriorityQueue<String>();

		for (String str: graph.vertices()) {
			distTo.put(str, Double.POSITIVE_INFINITY);
		}
		distTo.put(origin, 0.0);
		
		pq.add(origin);
		
		while(!pq.isEmpty()) {
			Vertex current = new Vertex(pq.poll());
			double d = distTo.get(current.name);
			if(d < distance) {//to see if the vertex is within the boundary				
				for(Edge w: graph.adj(current)) {
					double length = distTo.get(current.name) + w.weight();
					String next = w.other(current).name;
					if(!edgeTo.containsKey(next)) {
						edgeTo.put(next, w);//mark visited
						distTo.put(next, length);//add length
						pq.add(next);//put into pq
					}
				}
			}
		}
		edgeTo.remove(origin);
		List<String> marked = new ArrayList<String>();
		
		for(String name: edgeTo.keySet()) {
			marked.add(name + " " + distTo.get(name) );			
		}
        return marked;
	}
	
	//private methods go here

}
