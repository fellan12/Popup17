import java.util.ArrayList;
import java.util.List;

/**
 * Authors Martin Engelin & Felix De Silva
 */
public class EulerianWalk {

	/**
	 * Calculates a eulerian walk of the graph, and returns an array of integers 
	 * specifying the visited nodes in order.
	 * If no such walk exist, returns null.
	 */
	public static List<Integer> eulerianWalk(Graph graph) {
		if (!graph.feasible())
			return null;
		List<Integer> eulerian = new ArrayList<>();
		int inputIndex = 0;
		int current = graph.getStart();
		while (true) {
			boolean[] visited = new boolean[graph.size()];
			List<Integer> subtour = new ArrayList<>();
			int out = -1;
			while (true) {
				if (visited[current]) {
					out = subtour.size();
					subtour.add(current);
					break;
				}
				visited[current] = true;
				subtour.add(current);
				if (!graph.hasUnvisitedEdges(current))
					break;
				int next = graph.getUnvisitedEdge(current);
				if (out == -1 && graph.hasUnvisitedEdges(current))
					out = subtour.size()-1;
				current = next;
			}
			if (!eulerian.isEmpty())
				eulerian.remove(inputIndex);
			eulerian.addAll(inputIndex, subtour);
			inputIndex = inputIndex+out;
			if (out == -1)
				break; //CHECK: Maybe needs to check length of path
			current = subtour.get(out);
		}
		return eulerian;
	}

	public static void main(String[] args) {
		Graph graph;
		StringBuilder sb = new StringBuilder();
		while ((graph = InputParser.parseGraph()) != null) {
			List<Integer >results = eulerianWalk(graph);
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
