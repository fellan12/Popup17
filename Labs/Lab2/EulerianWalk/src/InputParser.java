/**
 * Authors Martin Engelin & Felix De Silva
 */
public class InputParser {
	
	private static Kattio io = new Kattio(System.in);

	/**
	 * Returns the next graph, or null if end of input.
	 */
	public static Graph parseGraph() {
		int vertices = io.getInt();
		int edges = io.getInt();
		Graph graph = new Graph(vertices, edges);
		if (vertices == 0 && edges == 0)
			return null;
		for (int i = 0; i < edges; i++) {
			graph.addEdge(io.getInt(), io.getInt());
		}
		return graph;
	}
}
