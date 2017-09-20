package martin;
/**
 * A Fenwick tree that allows for summing subseqences of the leaflist.
 */
public class SumFenwickTree extends AbstractFenwickTree {

	public SumFenwickTree(int leavesAmount) {
		super(leavesAmount);
	}
	
	/**
	 * Calculates the sum from the begining of the list to the leafIndex (inclusively).
	 */
	protected int sumLeft(int leafIndex) {
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
	
	/**
	 * Calculates the sum from the leafIndex (inclusively) to the end of the list.
	 */
	protected int sumRight(int leafIndex) {
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
	
	/**
	 * Calculates the sum from index "from" to index "to", inclusively
	 */
	public int sum(int from, int to) {
		return sumLeft(to) - sumLeft(from);
	}
}
