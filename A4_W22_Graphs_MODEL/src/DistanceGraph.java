import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DistanceGraph extends Graph {
	/**
	 * read the graph from a file
	 * file format:
	 * number of vertices
	 * number of edges 
	 * -followed by the edges in format-
	 * From,To,"Distance, km","Time, min"
	 */
	public DistanceGraph(String filename) {
		super();
		Scanner myReader;
		try {
			myReader = new Scanner(new File(filename)).useDelimiter("[\n\r,]+");
			V = myReader.nextInt();
			E = myReader.nextInt(); 
			this.adj = new HashMap<String, List<Edge>>();

			for (int i = 0; i < E; i++) {
				String str1 = myReader.next();
				String str2 = myReader.next();
				double distance = myReader.nextDouble();
				myReader.nextDouble();//skip the time
				if (!adj.containsKey(str1)) adj.put(str1, new ArrayList<Edge>());
				if (!adj.containsKey(str2)) adj.put(str2, new ArrayList<Edge>());
				adj.get(str1).add(new Edge(new Vertex(str1), new Vertex(str2), distance));
				adj.get(str2).add(new Edge(new Vertex(str2), new Vertex(str1), distance));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
