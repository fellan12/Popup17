import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;

public class TopoSort {
	/**
	 * Returns null if not a DAG.
	 */
	public static int[] topoSort(HashMap<Integer, List<Integer>> edges, int[] inedges) {
		int[] results = new int[inedges.length];
		int resIndex = 0;
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < inedges.length; i++) {
			if (inedges[i] == 0)
				stack.push(i);
		}
		while (!stack.isEmpty()) {
			int current = stack.poll();
			results[resIndex] = current;
			resIndex++;
			for (int neighbor : edges.remove(current)) {
				inedges[neighbor]--;
				if (inedges[neighbor] == 0) {
					stack.push(neighbor);
				}
			}
		}
		if (!edges.isEmpty()) {
			return null;
		}
		return results;
	}
}
