import java.util.List;

public class KittenTree {
	
	private TreeNode[] nodes;
	
	public KittenTree() {
		nodes = new TreeNode[101];
	}

	public void addChildren(int parent, List<Integer> children) {
		addIfNotPresent(parent);
		for (int child : children) {
			addIfNotPresent(child);
			nodes[child].setParent(nodes[parent]);
		}
	}
	
	private void addIfNotPresent(int node) {
		if (nodes[node] == null) {
			nodes[node] = new TreeNode(node);
		}
	}
	
	public TreeNode get(int i) {
		return nodes[i];
	}
}
