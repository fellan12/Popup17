
public class TurboList extends SumFenwickTree {

	int[] indexMap;
	
	public TurboList(int[] leaves) {
		super(leaves.length);
		indexMap = new int[leaves.length+1];
		for (int i = 0; i < leaves.length; i++) {
			this.add(i, 1);
			indexMap[leaves[i]] = i;
		}
	}

	public void remove(int object) {
		this.add(indexMap[object], -1);
	}
	
	public int amountRightOf(int object) {
		return this.sumRight(indexMap[object]);
	}
	
	public int amountLeftOf(int object) {
		return this.sumLeft(indexMap[object]);
	}
}
