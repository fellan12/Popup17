import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Authors Martin Engelin & Felix De Silva
 * 
 * Contains static method for solving a Eulerian Path problem.
 */
public class EulerianPath {

	/**
	 * Calculates a eulerian walk of the graph, and returns an array of integers 
	 * specifying the visited nodes in order.
	 * If no such walk exist, returns null.
	 * If there are no edges in the graph, returns an empty array.
	 */
	public static List<Integer> eulerianPath(EulerianGraph graph) {
		if (graph.edges() == 0)
			return new ArrayList<>();
		if (!graph.feasible())
			return null;
		Deque<Integer> eulerian = new ArrayDeque<>();
		Deque<Integer> subpath = new ArrayDeque<>();
		subpath.push(graph.getStart());
		while (!subpath.isEmpty()) {
			int next = subpath.poll();
			if (graph.hasUnvisitedEdges(next)) {
				nextSubpath(graph, subpath, next);
			} else {
				eulerian.push(next);
			}
		}
		if (eulerian.size() != graph.edges() + 1)
			return null;
		List<Integer> results = new ArrayList<>();
		while (!eulerian.isEmpty()) {
			results.add(eulerian.poll());
		}
		return results;
	}

	/**
	 * Takes a path and a node (int) that has unvisited edges.
	 * Pushes the new subpath from "current" to the argument path.
	 */
	private static void nextSubpath(EulerianGraph graph, Deque<Integer> path, int current) {
		while (true) {
			path.push(current);
			if (!graph.hasUnvisitedEdges(current))
				break;
			current = graph.removeUnvisitedEdge(current);
		}
	}
}
