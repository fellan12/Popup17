import java.util.List;
/**
 * Authors Martin Engelin & Felix De Silva
 *
 * Main class for solving the Kattis problem Eulerian Path.
 */
public class EulerianPathSolver {
	public static void main(String[] args) {
		EulerianGraph graph;
		StringBuilder sb = new StringBuilder();
		while ((graph = InputParser.parseGraph()) != null) {
			List<Integer> results = EulerianPath.eulerianPath(graph);			
			if (results == null) {
				sb.append("Impossible");
			} else {
				for (int n : results) {
					sb.append(n).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
