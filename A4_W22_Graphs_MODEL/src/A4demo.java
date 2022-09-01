
public class A4demo {

	public static void main(String[] args) {
		Graph g = new DistanceGraph("Edges.csv");
		//System.out.println(g);

		String from = "Ajax";
		String to = "Toronto";
		System.out.println(Graphs.shortestPath(g, from, to));

		System.out.println(Graphs.nearby(g, "Toronto", 50.0));

	}

}
