public class TreeNode {
	private int id;
	private TreeNode parent;
	
	TreeNode(int id) {
		this.id = id;
	}
	
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	public TreeNode getParent() {
		return parent;
	}
	
	public int getId() {
		return id;
	}
}
