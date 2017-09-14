
public class FenwickTree {
	private int[] binaryTree;
	private int leaves;
	
	public FenwickTree(int leavesAmount) {
		this.leaves = getNextPowOfTwo(leavesAmount);
		this.binaryTree = new int[this.leaves*2-1];
	}
	
	public int getNextPowOfTwo(int n) {
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
	
	public void add(int leafIndex, int value) {
		int index = getLeafIndex(leafIndex);
		while (index != 0) {
			binaryTree[index] += value;
			index = parent(index);
		}
		binaryTree[0] += value;
	}
	
	public int sumLeft(int leafIndex) {
		int index = getLeafIndex(leafIndex);
		int sum = binaryTree[index]; // add leaf value
		while (index != 0) {
			int last = index;
			index = parent(index);
			if (last % 2 == 0)
				sum += binaryTree[leftChild(index)];
		}
		return sum;
	}
	
	public int sumRight(int leafIndex) {
		int index = getLeafIndex(leafIndex);
		int sum = binaryTree[index]; // add leaf value
		while (index != 0) {
			int last = index;
			index = parent(index);
			if (last % 2 != 0)
				sum += binaryTree[rightChild(index)];
		}
		return sum;
	}
	
	private int getLeafIndex(int index) {
		return binaryTree.length-leaves+index;
	}
	
	private int parent(int index) {
		int ret = (index/2);
		if (index % 2 == 0)
			ret -= 1;
		return ret;
	}
	
	private int leftChild(int index) {
		return rightChild(index)-1;
	}
	
	private int rightChild(int index) {
		return (index+1)*2;
	}
}
