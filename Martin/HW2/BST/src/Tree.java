
public class Tree {
	
	private Node root;
	
	public void addRoot(int i) {
		root = new Node(i, 0);
	}
	
	public int add(int i) {
		return root.add(i);
	}

	/**
	 * Class representing a node in the tree.
	 */
	private class Node {
		private int value;
		private Node lower;
		private Node higher;
		private int depth;
		
		Node(int value, int depth) {
			this.value = value;
			this.depth = depth;
			lower = null;
			higher = null;
		}
		
		public int add(int nodeValue) {
			if (nodeValue > this.value) {
				if (this.higher != null) {
					return higher.add(nodeValue);
				} else {
					higher = new Node(nodeValue, this.depth+1);
					return higher.depth;
				}
			} else if (nodeValue < this.value) {
				if (this.lower != null) {
					return lower.add(nodeValue);
				} else {
					lower = new Node(nodeValue, this.depth+1);
					return lower.depth;
				}
			}
			return this.depth;
		}
		
		public Node search(int nodeValue) {
			if (this.value > nodeValue) {
				return lower.search(nodeValue);
			} else if (this.value < nodeValue) {
				return higher.search(nodeValue);
			}
			return this;
		}
	}
}
