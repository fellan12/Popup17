/**
 * Authors Martin Engelin & Felix De Silva
 * 
 * Parser for EularianGraphs.
 */
public class InputParser {
	
	private static Kattio io = new Kattio(System.in);

	/**
	 * Returns the next graph.
	 * If end of output, returns null.
	 */
	public static EulerianGraph parseGraph() {
		int vertices = io.getInt();
		int edges = io.getInt();
		EulerianGraph graph = new EulerianGraph(vertices, edges);
		if (vertices == 0 && edges == 0)
			return null;
		for (int i = 0; i < edges; i++) {
			graph.addEdge(io.getInt(), io.getInt());
		}
		return graph;
	}
}
