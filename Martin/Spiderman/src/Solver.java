import java.util.ArrayList;
import java.util.List;

public class Solver {
	
	private static final String NOT_POSSIBLE = "IMPOSSIBLE";
	private static final String UP = "U";
	private static final String DOWN = "D";

	private Kattio kattio;
	private List<Problem> problems;

	public Solver() {
		problems = parseInput();
		for (Problem p : problems) {
			solve(p);
		}
	}
	
	private void solve(Problem problem) {
		SpidermanTree tree = new SpidermanTree(problem.getDistanceArray());
		if (tree.getLeaves().isEmpty()) {
			kattio.println(NOT_POSSIBLE);
		} else {
			SpidermanTree.Node node = getOptimumNode(tree.getLeaves());
			printPath(node);
		}
	}
	
	/**
	 * Returns the Node of the leaves with the optimum path
	 */
	private SpidermanTree.Node getOptimumNode(List<SpidermanTree.Node> nodes) {
		SpidermanTree.Node best = null;
		int bestHeight = Integer.MAX_VALUE;
		for (SpidermanTree.Node n : nodes) {
			int height = n.getHeight(); // Should be 0
			SpidermanTree.Node traverse = n;
			while (traverse.getSource() != null) {
				if (traverse.getSource().getHeight() > height)
					height = traverse.getSource().getHeight();
				traverse = traverse.getSource();
			}
			if (best == null || bestHeight > height)
				best = n;
		}
		return best;
	}
	
	/**
	 * Traverses and prints the path of the node.
	 */
	private void printPath(SpidermanTree.Node node) {
		StringBuilder sb = new StringBuilder();
		while (node.getSource() != null) {
			SpidermanTree.Node source = node.getSource();
			String next;
			if (source.getDown().equals(node)) {
				next = DOWN;
			} else {
				next = UP;
			}
			sb.insert(0, next);
			node = source;
		}
		kattio.println(sb.toString());
	}
	
	/**
	 * Parses the entire input and returns a list of Problems
	 */
	private List<Problem> parseInput() {
		int amount = kattio.getInt();
		List<Problem> problems = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			problems.add(parseProblem());
		}
		return problems;
	}
	
	/**
	 * Parses a single problem and returns it
	 */
	private Problem parseProblem() {
		int amount = kattio.getInt();
		Problem p = new Problem();
		for (int i = 0; i < amount; i++) {
			p.addDistance(kattio.getInt());
		}
		return p;
	}
	
	/**
	 * Represents a problem for Spiderman
	 */
	private class Problem {
		private List<Integer> distances;
		public Problem() {
			distances = new ArrayList<>();
		}
		
		public void addDistance(int d) {
			distances.add(d);
		}
		
		public int[] getDistanceArray() {
			int[] array = new int[distances.size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = distances.get(i);
			}
			return array;
		}
	}
	
	public static void main(String[] args) {
		new Solver();
	}
}
