
import java.util.ArrayList;
import java.util.List;

public class Solver {

	private static final String NOT_POSSIBLE = "IMPOSSIBLE";
	private static final String UP = "U";
	private static final String DOWN = "D";

	private Kattio kattio;
	private List<Problem> problems;

	public Solver() {
		kattio = new Kattio(System.in);
		problems = parseInput();
		for (Problem p : problems) {
			solve(p);
		}
		kattio.flush();
		kattio.close();
	}

	private void solve(Problem problem) {
		int[] dist = problem.getDistanceArray();
		if (dist.length == 1) {
			kattio.println(NOT_POSSIBLE);
			return;
		}
		SpidermanTree tree = new SpidermanTree(dist);
		if (tree.getLeaves() == null) {
			//kattio.println();
		} else if (tree.getLeaves().isEmpty()) {
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
		SpidermanTree.Node best = nodes.get(0);
		for (SpidermanTree.Node n : nodes) {
			if (n.getPathHeight() < best.getPathHeight()) {
				best = n;
			}
		}
		return best;
	}

	/**
	 * Traverses and prints the path of the node.
	 */
	private void printPath(SpidermanTree.Node node) {
		StringBuilder sb = new StringBuilder();
		// Always end with going down
		node = node.getSource();
		sb.append(DOWN);
		if (node == null)
			throw new MyException();
		while (node.getSource() != null) {
			SpidermanTree.Node source = node.getSource();
			String next;
			if (source.getDown() != null && source.getDown().equals(node)) {
				next = DOWN;
			} else {
				next = UP;
			}
			sb.insert(0, next);
			node = source;
		}
		// Always start by going up
		sb.insert(0, UP);
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
			if (d > 0)
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
	
	private class MyException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		
	}
}
