package martin;
/**
 * Abstract Fenwick Tree. no operation is specified. 
 * Implemented to build the tree, add to leaves and get parent and childs.
 */
public abstract class AbstractFenwickTree {
	protected int[] binaryTree;
	private int[] leafIndexes;
	
	public AbstractFenwickTree(int leavesAmount) {
		int leavesSize = getNextPowOfTwo(leavesAmount);
		this.leafIndexes = new int[leavesSize];
		this.binaryTree = new int[leavesSize*2-1];
	}
	
	private int getNextPowOfTwo(int n) {
		// Bit shifting magic...
		n--;
		n |= n >> 1;
		n |= n >> 2;
		n |= n >> 4; 
		n |= n >> 8;
		n |= n >> 16;
		n++;
		return n;
	}
	
	/**
	 * add the value to the leaf with the specified index.
	 */
	public void add(int leafIndex, int value) {
		int index = getLeafIndex(leafIndex);
		while (index != 0) {
			binaryTree[index] += value;
			index = parent(index);
		}
		binaryTree[0] += value;
	}
	
	/**
	 * Maps the index of the array to the index of the binaryTree.
	 */
	protected int getLeafIndex(int index) {
		if (leafIndexes[index] == 0)
			leafIndexes[index] = binaryTree.length-leafIndexes.length+index;
		return leafIndexes[index];
	}
	
	/**
	 * Get the binary tree index of the parent of the node with the argument index
	 */
	protected int parent(int index) {
		int ret = (index/2);
		if (index % 2 == 0)
			ret -= 1;
		return ret;
	}
	
	/**
	 * Get the binary tree index of the left child of the node with the argument index
	 */
	protected int leftChild(int index) {
		return rightChild(index)-1;
	}
	
	/**
	 * Get the binary tree index of the right child of the node with the argument index
	 */
	protected int rightChild(int index) {
		return (index+1)*2;
	}
}
