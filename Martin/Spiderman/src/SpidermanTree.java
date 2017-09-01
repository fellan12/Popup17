import java.util.ArrayList;
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
		this.sum = setupSum(distances);
		this.levels = setupLevels(distances);
		this.root = setupRoot(distances);
		this.leaves = new ArrayList<>();
		populateTree(root);
	}
	
	public List<Node> getLeaves() {
		return leaves;
	}
	
	/**
	 * Populates the tree with correct nodes.
	 */
	private void populateTree(Node node) {
		if (node == null)
			return;
		Level next = nextLevel(node.getLevel());
		if (next == null) {
			// We have reached the last level. Add argument node to leaves
			leaves.add(node);
		}
		node.setUp(getNode(node, next, node.getHeight()+next.getDistance()));
		node.setDown(getNode(node, next, node.getHeight()-next.getDistance()));
		populateTree(node.getUp());
		populateTree(node.getDown());
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
		return new Node(source, level, height);
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
			levels[i] = new Level(i, remainder, distances[i]);
			remainder -= levels[i].getDistance();
		}
		return levels;
	}
	
	/**
	 * Returns the root of the tree.
	 * Assumes that the levels have already been setup, and that the amount of distances > 0
	 */
	private Node setupRoot(int[] distances) {
		int height = distances[0];
		return new Node(null, levels[0], height);
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
		
		public Level(int depth, int remainder, int distance) {
			this.depth = depth;
			this.remainder = remainder;
			this.distance = distance;
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
		
		public Node(Node source, Level level, int height) {
			this.source = source;
			this.level = level;
			this.height = height;
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
	}
}
