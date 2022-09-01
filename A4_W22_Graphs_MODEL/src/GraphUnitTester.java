import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GraphUnitTester {

	@Test (timeout = 1000)
	public void shortestPathOntario_1_15pts() {
		Graph g = new DistanceGraph("Edges.csv");
		List <String> modelList = GraphsModel.shortestPath(g, "Aurora", "Guelph");
		List <String> actualList = Graphs.shortestPath(g, "Aurora", "Guelph");
		System.out.println(modelList);
		System.out.println(actualList);
		assertTrue("Sequences of cities are different", 
				actualList.subList(0, actualList.size()-1).equals(modelList.subList(0, modelList.size()-1)));
		assertEquals("Path lengths different", 
				Double.valueOf( modelList.get( modelList.size() - 1)),
				Double.valueOf(actualList.get(actualList.size() - 1)), 1.0);
	}

	@Test (timeout = 1000)
	public void shortestPathOntario_2_15pts() {
		Graph g = new DistanceGraph("Edges.csv");
		List <String> modelList = GraphsModel.shortestPath(g, "Grimsby", "Alliston");
		List <String> actualList = Graphs.shortestPath(g, "Grimsby", "Alliston");
		System.out.println(modelList);
		System.out.println(actualList);
		assertTrue("Sequences of cities are different", 
				actualList.subList(0, actualList.size()-1).equals(modelList.subList(0, modelList.size()-1)));
		assertEquals("Path lengths different", 
				Double.valueOf( modelList.get( modelList.size() - 1)),
				Double.valueOf(actualList.get(actualList.size() - 1)), 1.0);
	}

	@Test (timeout = 1000)
	public void shortestPathSetSimple_10pts() {
		Graph g = new DistanceGraph("Edges1.csv");
		List <String> modelList = GraphsModel.shortestPath(g, "0", "2");
		List <String> actualList = Graphs.shortestPath(g, "0", "2");
		//		System.out.println(modelList);
		//		System.out.println(actualList);

		Set <String> actualSet = new HashSet<>();
		Set <String> modelSet = new HashSet<>();
		for (String city : modelList.subList(0, modelList.size()-1)) {
			modelSet.add(city);
		}
		for (String city : actualList.subList(0, actualList.size()-1)) {
			actualSet.add(city);
		}

		System.out.println(actualSet);
		System.out.println(modelSet);
		assertTrue("Sets of cities in the path are different", actualSet.equals(modelSet));
	}

	@Test (timeout = 1000)
	public void shortestPathListSimple_15pts() {
		Graph g = new DistanceGraph("Edges1.csv");
		List <String> modelList = GraphsModel.shortestPath(g, "0", "2");
		List <String> actualList = Graphs.shortestPath(g, "0", "2");
		System.out.println(modelList);
		System.out.println(actualList);
		assertTrue("Sequences of cities are different", 
				actualList.subList(0, actualList.size()-1).equals(modelList.subList(0, modelList.size()-1)));
	}

	@Test (timeout = 1000)
	public void nearbyOntario_1_15pts() {
		Graph g = new DistanceGraph("Edges.csv");
		String origin = "Woodbridge";
		double distance = 20.0;
		List <String> modelList = GraphsModel.nearby(g, origin, distance);
		List <String> actualList = Graphs.nearby(g, origin, distance);
		Set <String> modelSet = new HashSet<>(modelList);
		Set <String> modelSet0 = new HashSet<>(modelSet);
			modelSet0.add(origin + " 0.0");
		Set <String> actualSet = new HashSet<>(actualList);
		System.out.println(modelList);
		System.out.println(actualList);
		assertTrue("Sets of cities are different", 
				actualSet.equals(modelSet) || actualSet.equals(modelSet0));
	}

	@Test (timeout = 1000)
	public void nearbyOntario_2_15pts() {
		Graph g = new DistanceGraph("Edges.csv");
		String origin = "Orangeville";
		double distance = 30.0;
		List <String> modelList = GraphsModel.nearby(g, origin, distance);
		List <String> actualList = Graphs.nearby(g, origin, distance);
		Set <String> modelSet = new HashSet<>(modelList);
		Set <String> modelSet0 = new HashSet<>(modelSet);
			modelSet0.add(origin + " 0.0");
		Set <String> actualSet = new HashSet<>(actualList);
		System.out.println(modelList);
		System.out.println(actualList);
		assertTrue("Sets of cities are different", 
				actualSet.equals(modelSet) || actualSet.equals(modelSet0));
	}

	@Test (timeout = 1000)
	public void nearbyOntario_3_15pts() {
		Graph g = new DistanceGraph("Edges.csv");
		String origin = "Markham";
		double distance = 32.0;
		List <String> modelList = GraphsModel.nearby(g, origin, distance);
		List <String> actualList = Graphs.nearby(g, origin, distance);
		Set <String> modelSet = new HashSet<>(modelList);
		Set <String> modelSet0 = new HashSet<>(modelSet);
			modelSet0.add(origin + " 0.0");
		Set <String> actualSet = new HashSet<>(actualList);
		System.out.println(modelList);
		System.out.println(actualList);
		assertTrue("Sets of cities are different", 
				actualSet.equals(modelSet) || actualSet.equals(modelSet0));
	}

}
