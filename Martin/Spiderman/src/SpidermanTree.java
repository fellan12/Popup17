
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpidermanTree {

	private int sum;
	private List<Node> leaves;
	private Node root;
	private Level[] levels;

	public SpidermanTree(int[] distances) {
		if (distances.length == 0) {
			//TODO: Edge case
			return;
		}
		this.leaves = new ArrayList<>();
		this.sum = setupSum(distances);
		if (sum % 2 != 0)
			return;
		this.levels = setupLevels(distances);
		this.root = setupRoot(distances);
		populateTree();
	}

	public List<Node> getLeaves() {
		return leaves;
	}

	private void populateTree() {
		levels[0].addNode(root);
		for (int i = 0; i < levels.length; i++) {
			for (Node n : levels[i].getNodeArray()) {
				populateUpAndDown(n);
			}
		}
	}

	/**
	 * Populates the up and down nodes of the argument node.
	 */
	private void populateUpAndDown(Node node) {
		if (node == null)
			return;
		Level next = nextLevel(node.getLevel());
		if (next == null) {
			// We have reached the last level. Add argument node to leaves
			leaves.add(node);
			return;
		}
		Node up = getNode(node, next, node.getHeight()+next.getDistance());
		Node down = getNode(node, next, node.getHeight()-next.getDistance());
		if (up != null) {
			node.setUp(up);
			next.addNode(up);
		}
		if (down != null) {
			node.setDown(down);
			next.addNode(down);
		}
	}

	/**
	 * Calculates and sets the up and down node of the argument node.
	 */


	/**
	 * Creates a new node based on arguments and checks if it is valid.
	 * If not, returns null
	 */
	private Node getNode(Node source, Level level, int height) {
		if (height < 0)
			return null;
		if (height > level.getRemainder())
			return null;
		int pathHeight = height > source.getPathHeight() ? height : source.getPathHeight();
		return new Node(source, level, height, pathHeight);
	}

	/**
	 * Get the next level. Returns null if current level is the last.
	 */
	private Level nextLevel(Level level) {
		if (level.depth == levels.length-1)
			return null;
		return levels[level.depth+1];
	}

	/**
	 * Returns the sum of distances
	 */
	private int setupSum(int[] distances) {
		int sum = 0;
		for (int i : distances) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Setup Levels array.
	 * Assumes that the field "sum" is already set.
	 */
	private Level[] setupLevels(int[] distances) {
		Level[] levels = new Level[distances.length];
		int remainder = sum;
		for (int i = 0; i < distances.length; i++) {
			remainder -= distances[i];
			levels[i] = new Level(i, remainder, distances[i]);
		}
		return levels;
	}

	/**
	 * Returns the root of the tree.
	 * Assumes that the levels have already been setup, and that the amount of distances > 0
	 */
	private Node setupRoot(int[] distances) {
		int height = distances[0];
		return new Node(null, levels[0], height, height);
	}

	/**
	 * Represents a "level" in the tree. Each level has a depth (where
	 * the root of the tree has depth 0), a remainder (the total distance
	 * left in the array of distances) and a distance.
	 */
	protected class Level {
		private int depth;
		private int remainder;
		private int distance;
		//private List<Node> nodes;
		private HashMap<Integer, Node> nodes;

		public Level(int depth, int remainder, int distance) {
			this.depth = depth;
			this.remainder = remainder;
			this.distance = distance;
			this.nodes = new HashMap<Integer, Node>();
		}

		public int getDepth() {
			return depth;
		}

		public int getRemainder() {
			return remainder;
		}

		public int getDistance() {
			return distance;
		}

		public void addNode(Node node) {
			int key = node.getHeight();
			if (nodes.containsKey(key) && nodes.get(key).getPathHeight() < node.getPathHeight()) {
				return;
			}
			nodes.put(key, node);
		}

		public Node[] getNodeArray() {
			Node[] nodeArray = new Node[nodes.size()];
			return nodes.values().toArray(nodeArray);
		}
	}

	/**
	 * Representation of a node in the tree.
	 */
	protected class Node {
		private Node source;
		private Node up;
		private Node down;
		private int height;
		private Level level;
		private int pathHeight;

		public Node(Node source, Level level, int height, int pathHeight) {
			this.source = source;
			this.level = level;
			this.height = height;
			this.pathHeight = pathHeight;
		}

		public void setUp(Node up) {
			this.up = up;
		}

		public void setDown(Node down) {
			this.down = down;
		}

		public int getHeight() {
			return height;
		}

		public Node getSource() {
			return source;
		}

		public Level getLevel() {
			return level;
		}

		public Node getUp() {
			return up;
		}

		public Node getDown() {
			return down;
		}

		public int getPathHeight() {
			return pathHeight;
		}

		public void setSource(Node source) {
			this.source = source;
		}
	}
}